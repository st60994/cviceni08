package cz.upce.fei.cviceni01;

import org.springframework.web.bind.annotation.*;

/*

 */
@RestController
public class HelloController {
    @GetMapping("")
    public String helloWorld() {
        return "Hello from Spring Boot!";
    }

    @GetMapping(path = "a/{id}")
    public String endPoint1(@PathVariable int id) {
        return "Your id is: " + id;
    }

    @GetMapping(path = "b")
    public String endPoint2(@RequestParam String name) {
        return "Your name is: " + name;
    }

    @GetMapping(path = "c")
    public String endPoint3(@RequestBody Person person) {
        return person.toString();
    }
}
