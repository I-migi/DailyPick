package lsw.dailypick.controller.user;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lsw.dailypick.dto.GenreDto;
import lsw.dailypick.dto.LoginDto;
import lsw.dailypick.dto.UserDto;
import lsw.dailypick.entity.User;
import lsw.dailypick.repository.user.UserRepository;
import lsw.dailypick.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult) {

        log.info("signup");

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        if (userService.existsByEmail(userDto.getEmail())) {
            bindingResult.rejectValue("email", "duplicate", "이미 사용 중인 이메일입니다");
            return "signup";
        }

        userService.save(userDto);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute @Valid LoginDto loginDto, Model model, HttpSession session) {

        if (userService.validateLogin(loginDto)) {
            session.setAttribute("loginUser", loginDto.getEmail());
            return "redirect:/";
        }

        model.addAttribute("loginError", "이메일 또는 비밀번호가 일치하지 않습니다.");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }



}
