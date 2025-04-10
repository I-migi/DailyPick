package lsw.dailypick.controller.review;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lsw.dailypick.dto.ReviewDto;
import lsw.dailypick.entity.Review;
import lsw.dailypick.entity.User;
import lsw.dailypick.service.review.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/review")
    public String review(@RequestParam("movieId") Long movieId, String comment, Double rating, HttpSession session) {

        log.info("review movieId: {}, comment: {}, rating: {}", movieId, comment, rating);
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/user/login";
        }

        Review review = new Review(loginUser, movieId, comment, rating);
        reviewService.save(review);

        return "redirect:/movie?movieId=" + movieId;
    }

    @ResponseBody
    @GetMapping("/reviews")
    public List<ReviewDto> findByMovieId(@RequestParam Long movieId) {
        List<ReviewDto> list = reviewService.findByMovieId(movieId).stream()
                .map(review -> new ReviewDto(review.getUser().getName(), review.getRating(), review.getComment()))
                .toList();

        for (ReviewDto reviewDto : list) {
            log.info("reviewDto: {}", reviewDto);
        }
        return list;
    }
}
