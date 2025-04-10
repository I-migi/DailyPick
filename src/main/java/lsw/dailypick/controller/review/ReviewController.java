package lsw.dailypick.controller.review;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lsw.dailypick.entity.Review;
import lsw.dailypick.entity.User;
import lsw.dailypick.service.review.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/review")
    public String review(@RequestParam("movieId") Long movieId, String comment, Double rating, HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/login";
        }

        Review review = new Review(loginUser, movieId, comment, rating);
        reviewService.save(review);

        return "redirect:/movie/" + movieId;
    }
}
