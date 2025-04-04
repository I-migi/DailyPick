package lsw.dailypick.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lsw.dailypick.entity.User;
import lsw.dailypick.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String mainPage(HttpSession session, Model model) {
        Object loginUser = session.getAttribute("loginUser");

        if (loginUser == null) {
            model.addAttribute("isLoggedIn", false);
            return "mainPage";
        }


        if (userService.findByEmail(loginUser.toString()).isPresent()) {
            model.addAttribute("userName", userService.findByEmail(loginUser.toString()).get().getName());
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("userEmail", loginUser);
        }


        return "mainPage";
    }


    @GetMapping("/survey")
    public String servicePage() {
        return "survey";
    }

}
