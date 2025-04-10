package lsw.dailypick.service.review;

import lombok.RequiredArgsConstructor;
import lsw.dailypick.entity.Review;
import lsw.dailypick.repository.review.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void save(Review review) {
        reviewRepository.save(review);
    }


}
