package restfulwebservice.demo.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

//A user can do many post (many to one) relationship
//A foreign key column is created here
@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"users"}) // It is addded because it will print in loop user have post and post have user , so it would be a never ending loop
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;

    @ManyToOne()
    private Users users;
}
