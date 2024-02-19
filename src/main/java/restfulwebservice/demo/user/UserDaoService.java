package restfulwebservice.demo.user;

import org.springframework.stereotype.Component;
import restfulwebservice.demo.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//It is created because we are not using database so we are creating a list which act as a db


@Component
public class UserDaoService {

    public static List<Users> users =new ArrayList<>();
    public static int userCount=3;
    static {
        users.add(Users.builder().name("Avar").build());
        users.add(Users.builder().name("abcd").build());
        users.add(Users.builder().name("playboy").build());
    }

    public List<Users> findAll() {
        return users;
    }

    public Users save(Users users)
    {
        if(users.getId()==null)
            users.setId(++userCount);
        UserDaoService.users.add(users);
        return users;
    }

    public Users findOne(Integer id) {
        for(Users users : UserDaoService.users) {
            if(users.getId()==id)
                return users;
        }
        return null;
    }
    public Users deleteOne(Integer id) {
        for (Users users : UserDaoService.users) {
            if(users.getId()==id) {
                UserDaoService.users.remove(users);
                return users;
            }
        }
        throw new UserNotFoundException("USER MISSING!");
    }
}
