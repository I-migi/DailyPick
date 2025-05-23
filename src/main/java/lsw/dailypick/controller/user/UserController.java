package lsw.dailypick.controller.user;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lsw.dailypick.dto.AnalyzeDto;
import lsw.dailypick.dto.GenreDto;
import lsw.dailypick.dto.LoginDto;
import lsw.dailypick.dto.UserDto;
import lsw.dailypick.entity.Genre;
import lsw.dailypick.entity.User;
import lsw.dailypick.service.genre.GenreService;
import lsw.dailypick.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final GenreService genreService;


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
            User user = userService.findByEmail(loginDto.getEmail()).orElse(null);
            session.setAttribute("loginUser", user);
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

    @GetMapping("/myPage")
    public String myPage(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        log.info("loginUser: {}", loginUser);
        model.addAttribute("loginUser", loginUser);
        return "myPage";

    }

    @GetMapping("/analyzePage")
    public String analyzePage(Model model, HttpSession session) {
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/user/login";
        }
        return "analyze";
    }

    @ResponseBody
    @PostMapping("/analyze")
    public ResponseEntity<String> analyze(@RequestBody AnalyzeDto analyzeDto, Model model, HttpSession session) {

        if (analyzeDto.getGenres() == null || analyzeDto.getGenres().isEmpty() || analyzeDto.getPrefersDomestic() == null) {
            return ResponseEntity.ok("fail");
        }

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.ok("fail");
        }

        Set<Genre> genreList = analyzeDto.getGenres().stream()
                .map(genreService::findByName)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());


        loginUser.setGenres(genreList);
        loginUser.setPrefersDomestic(analyzeDto.getPrefersDomestic());
        userService.save(loginUser);

        return ResponseEntity.ok("success");
    }







}
