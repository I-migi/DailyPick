package lsw.dailypick.controller.movie;

import lombok.RequiredArgsConstructor;
import lsw.dailypick.dto.MovieDetailDto;
import lsw.dailypick.service.movie.TMDbService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final TMDbService tmDbService;

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





}
