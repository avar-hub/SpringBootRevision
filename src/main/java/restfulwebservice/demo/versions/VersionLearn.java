package restfulwebservice.demo.versions;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionLearn {

//Versioning is done using headers

//    @GetMapping(value = "/version", headers = "X=1")
//    public String getStr(){
//        return "hello";
//    }
//    @GetMapping(value = "/version", headers = "X=2")
//    public String getStr2() {
//        return "Bye";
//
//    }


    @GetMapping(value = "/version", produces = "application/vnd.company.app-v1+json")
    public String getStr(){
        return "hello";
    }
    @GetMapping(value = "/version", produces = "application/vnd.company.app-v2+json")
    public String getStr2() {
        return "Bye";

    }
}
