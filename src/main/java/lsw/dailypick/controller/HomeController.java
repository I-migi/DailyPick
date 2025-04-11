package lsw.dailypick.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lsw.dailypick.entity.User;
import lsw.dailypick.service.user.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String mainPage(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            model.addAttribute("isLoggedIn", false);
            return "mainPage";
        }

        model.addAttribute("userName", loginUser.getName());
        model.addAttribute("isLoggedIn", true);
        model.addAttribute("userEmail", loginUser.getEmail());

        return "mainPage";
    }


    @GetMapping("/survey")
    public String servicePage() {
        return "survey";
    }

    @GetMapping(value = "/movie")
    public String getMovieDetailPage(@RequestParam("movieId") String  movieId, Model model, HttpSession session) {

        model.addAttribute("loginUser", (User) session.getAttribute("loginUser"));
        model.addAttribute("movieId", movieId);

        return "detail";
    }

    @GetMapping("/movies/explore")
    public String exploreMovies() {
        return "explore";
    }


}
