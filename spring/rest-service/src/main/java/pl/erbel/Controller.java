package pl.erbel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping("/hello")
    public Greeting helloWorld(@RequestParam(
            value = "name", defaultValue = "world") String name) {
        return new Greeting(name);
    }


}
