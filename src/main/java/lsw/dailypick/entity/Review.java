package lsw.dailypick.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Long movieId;

    private String comment;

    private Double rating;

    public Review(User user, Long movieId, String comment, Double rating) {
        this.user = user;
        this.movieId = movieId;
        this.comment = comment;
        this.rating = rating;
    }

}
