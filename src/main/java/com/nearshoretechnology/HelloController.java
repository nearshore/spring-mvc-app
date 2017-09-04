package com.nearshoretechnology;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

  @RequestMapping("/hello")
  public String greeting(@RequestParam(value="name",
                                       required=false,
                                       defaultValue="World")
                         String name,
                         Model model) {
    
    model.addAttribute("name", name);
    return "hello";
  }

  @RequestMapping("/hello-cd")
  public String greetingCd(@RequestParam(value="name",
                                       required=false,
                                       defaultValue="Continuous Delivery")
                         String name,
                         Model model) {
    
    model.addAttribute("name", name);
    return "hello";
  }

}
