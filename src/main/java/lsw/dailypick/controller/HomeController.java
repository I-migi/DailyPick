package lsw.dailypick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String mainPage() {
        return "mainPage";
    }



    @GetMapping("/survey")
    public String servicePage() {
        return "survey";
    }

}
