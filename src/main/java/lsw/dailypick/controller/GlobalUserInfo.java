package lsw.dailypick.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@ControllerAdvice
public class GlobalUserInfo {

    @ModelAttribute("name")
    public String loginUser(@AuthenticationPrincipal OAuth2User user, HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (uri.startsWith("/swagger") || uri.startsWith("/v3")) {
            return null;
        }
        return user != null ? user.getAttribute("name") : null;
    }

}
