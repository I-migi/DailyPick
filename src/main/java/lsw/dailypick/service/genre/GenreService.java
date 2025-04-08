package lsw.dailypick.service.genre;

import lombok.RequiredArgsConstructor;
import lsw.dailypick.entity.Genre;
import lsw.dailypick.repository.genre.GenreRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public Genre findByName(String name){
        return genreRepository.findByName(name);
    }

    public int findApiIdByName(String name){
        return genreRepository.findApiIdByName(name);
    }
}
