package cz.upce.fei.cviceni01.controller;

import cz.upce.fei.cviceni01.Person;
import org.springframework.web.bind.annotation.*;

/*
@RestController je kombinací @Controller a @ResponseBody slouží přímo pro REST služby
oproti tomu @Controller značkuje třídy pouze jako MVC controller

Syntaxe různých typů souborů:
---------------XML----------
<root>
  <child>
    <subchild>.....</subchild>
  </child>
</root>
--------------JSON------------
{"name":"John", "id":123}
------------YAML-------------
martin:
  name: Martin D'vloper
  job: Developer
  skill: Elite

u YAMLu jsou důležité odsazování (tabulátory) podobně jako tomu je u pythonu
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
