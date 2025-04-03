package lsw.dailypick.controller.user;

import lombok.RequiredArgsConstructor;
import lsw.dailypick.dto.GenreDto;
import lsw.dailypick.entity.User;
import lsw.dailypick.repository.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;


    @PostMapping("/genre")
    public String genre(@RequestBody GenreDto genreDto, @AuthenticationPrincipal User principal) {
        User user = userRepository.findByEmail(principal.getEmail());

        user.setGenre1(genreDto.genre1());
        user.setGenre2(genreDto.genre2());
        user.setGenre3(genreDto.genre3());

        userRepository.save(user);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

}
