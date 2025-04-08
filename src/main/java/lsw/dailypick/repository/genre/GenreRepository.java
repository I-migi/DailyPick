package lsw.dailypick.repository.genre;

import lsw.dailypick.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);

    int findApiIdByName(String name);




}
