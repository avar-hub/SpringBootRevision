package restfulwebservice.demo.user;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user_details")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min = 2)
    private String name;
    @Past
    private Date birthDate;

    @OneToMany(mappedBy = "users") // -> linked with the name in post entity
    private List<Post> posts; //-> One user many post
}
