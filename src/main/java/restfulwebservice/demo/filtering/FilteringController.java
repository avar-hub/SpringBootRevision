package restfulwebservice.demo.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {


    //In this I want field 1 and field 2 from Somebean
    @GetMapping("/tryFilter")
    public ResponseEntity<MappingJacksonValue> retriveFields() {
        Somebean somebean = new Somebean("avar", "mittal", "yess");
        SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("someBeanFilter", propertyFilter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(somebean);
        mappingJacksonValue.setFilters(filterProvider);
        return ResponseEntity.ok().body(mappingJacksonValue);

    }

    //In this I want field 1 and field 3 from Somebean
    @GetMapping("/tryFilter2")
    public ResponseEntity<MappingJacksonValue> retiveFields2() {
        Somebean somebean = new Somebean("avar", "mittal", "yess");
        SimpleBeanPropertyFilter propertyFilter= SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
        FilterProvider filterProvider=new SimpleFilterProvider().addFilter("someBeanFilter",propertyFilter);
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(somebean);
        mappingJacksonValue.setFilters(filterProvider);
        return ResponseEntity.ok().body(mappingJacksonValue);
    }

}
