package lsw.dailypick.controller.movie;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lsw.dailypick.dto.MovieDetailDto;
import lsw.dailypick.entity.Genre;
import lsw.dailypick.entity.User;
import lsw.dailypick.service.movie.TMDbService;
import lsw.dailypick.service.user.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final TMDbService tmDbService;
    private final UserService userService;

    @GetMapping("/genre")
    public ResponseEntity<?> getMoviesByGenre(@RequestParam("genreId") String genreId) {
        return ResponseEntity.ok(tmDbService.getMoviesByGenre(genreId));
    }

    @GetMapping("/popular")
    public ResponseEntity<?> getPopularMovies() {
        return ResponseEntity.ok(tmDbService.getPopularTop10Movies());
    }


    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDetailDto> getMovieDetail(@RequestParam("movieId") String  movieId) {

        return ResponseEntity.ok(tmDbService.getMovieDetail(movieId));
    }


    @GetMapping("/recommend")
    public ResponseEntity<?> getRecommendMovies(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.ok(Map.of("error", "로그인하고 추천 콘텐츠를 확인해보세요!"));
        }

        Set<Genre> genres = userService.getUserGenres(loginUser.getEmail());
        int size = genres.size();

        if (size == 0) {
            return ResponseEntity.ok(Map.of("error","취향 분석을 하고 추천 콘텐츠를 확인해보세요!"));
        }

        List<Genre> genreList = new ArrayList<>(genres);
        Genre randomGenre = genreList.get(new Random().nextInt(genreList.size()));

        return ResponseEntity.ok(tmDbService.getPopularMoviesByGenreAndCountry(randomGenre.getApiId(), loginUser.getPrefersDomestic()));
    }

    @GetMapping("/explore")
    public ResponseEntity<?> exploreMovies(@RequestParam(value = "genreId", required = false) String genreId, @RequestParam(value = "sort", required = false) String sort) {

        return ResponseEntity.ok(tmDbService.getMovies(genreId, sort));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchMovie(@RequestParam(value = "keyword") String keyword) {

        return ResponseEntity.ok(tmDbService.searchMovies(keyword));
    }

}
