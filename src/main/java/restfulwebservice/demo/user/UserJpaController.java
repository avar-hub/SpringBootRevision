package restfulwebservice.demo.user;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import restfulwebservice.demo.exception.UserNotFoundException;

import java.net.URI;
import java.util.List;
import java.util.Optional;


//Same as user controller but it is using repository


@RestController
@RequestMapping("/jpa/users")
public class UserJpaController {

    @Autowired
    private  UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;
    @GetMapping("/all")
    public List<Users> retriveAllUsers() {
        return userRepo.findAll();
    }

    //HATEOAS is implemented in this method

    @GetMapping("/one")
    public EntityModel<Users> retriveUser(@RequestParam Integer id) {
        Users users = userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("User not found") );
        if(users ==null)
            throw new UserNotFoundException("USER MISSING!");

        // We are returning the link of all user endpoint

        EntityModel<Users> entityModel= EntityModel.of(users);
        WebMvcLinkBuilder linkTo= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retriveAllUsers());
        entityModel.add(linkTo.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addUser( @Valid @RequestBody Users users) {
        Users savedUsers = userRepo.save(users);

        //We are returning the status code as 201 -> created
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUsers.getId())
                .toUri();
        System.out.printf(ServletUriComponentsBuilder
                .fromCurrentRequest().toUriString());
        return ResponseEntity.created(location).body(savedUsers);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUserById(@RequestParam Integer id) {
        userRepo.deleteById(id);
        return ResponseEntity.ok().body("User Deleted");
    }

    // We are fetching posts on the basis of userId
    @GetMapping("/postOfUsers")
    public ResponseEntity<List<Post>> retriveAllUsers(@RequestParam Integer id) {
       Optional<Users> usersOptional= userRepo.findById(id);
       if(!usersOptional.isPresent())
           throw new UserNotFoundException("User not available");

       return ResponseEntity.ok().body(usersOptional.get().getPosts()) ;
    }

    // we are adding posts on the basis of userId and returning 201 status created
    @PostMapping("/postPost")
    public ResponseEntity<Post> addPosts(@RequestParam Integer id , @RequestBody Post post) {
        Optional<Users> usersOptional= userRepo.findById(id);
        if(!usersOptional.isPresent())
            throw new UserNotFoundException("User not available");
        post.setUsers(usersOptional.get());
        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}").buildAndExpand(post.getId()).toUri();
       return ResponseEntity.created(location).body(postRepo.save(post));
    }

}
