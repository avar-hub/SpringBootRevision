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

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private  UserDaoService userDaoService;
    @GetMapping("/all")
    public List<Users> retriveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/one")
    public EntityModel<Users> retriveUser(@RequestParam Integer id) {
        Users users = userDaoService.findOne(id);
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
       Users savedUsers = userDaoService.save(users);

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
    public ResponseEntity<Users> deleteUserById(@RequestParam Integer id) {
        return ResponseEntity.ok().body(userDaoService.deleteOne(id));
    }
}
