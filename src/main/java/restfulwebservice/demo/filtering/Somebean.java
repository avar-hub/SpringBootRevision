package restfulwebservice.demo.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//It is created for learning @JsonIgnoreProperties
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"field1"}) --> static filtering
@JsonFilter("someBeanFilter") //--> Dynamic filtering
public class Somebean {

    private String field1;
    private String field2;
    private String field3;

}
