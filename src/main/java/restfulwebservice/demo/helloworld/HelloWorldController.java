package restfulwebservice.demo.helloworld;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@Api(tags = {"Hello world Api"})
@RequestMapping("/hello")
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;
    @GetMapping("/restfulwebservice/demo/helloworld")
    public String helloWorld() {
        return "hello world";
    }
    @GetMapping("/helloworldBean/{value}")
    public HelloWorldBean helloWorldBean(@PathVariable Integer value) {
        return new HelloWorldBean("Han bhai");
    }

    @GetMapping("/good")
    public  String goodMorningInternationalized(
            @ApiParam(value = "bmnnbbbb", required = true, allowableValues = "x,y,z", example = "this defines our swagger")
            @RequestHeader(name = "Accept-Language",required = true) Locale locale) {
        return messageSource.getMessage("good.morning.message",null,locale);
    }
}
