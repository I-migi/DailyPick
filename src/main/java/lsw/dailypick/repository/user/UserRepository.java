package lsw.dailypick.repository.user;

import lsw.dailypick.entity.Genre;
import lsw.dailypick.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

}
