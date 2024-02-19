package restfulwebservice.demo.helloworld;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("testswagger")
public class HelloWorldBean {

    @ApiModelProperty(value = "abc", name = "xyz", required = true, example = "helloworld")
    private String message;
}
