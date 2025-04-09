package lsw.dailypick.service.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lsw.dailypick.dto.LoginDto;
import lsw.dailypick.dto.UserDto;
import lsw.dailypick.entity.Genre;
import lsw.dailypick.entity.User;
import lsw.dailypick.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(UserDto userDto) {

        LocalDate birthDay = LocalDate.of(
                Integer.parseInt(userDto.getBirthYear()),
                Integer.parseInt(userDto.getBirthMonth()),
                Integer.parseInt(userDto.getBirthDay())
        );


        User newUser = new User(userDto.getEmail(), userDto.getPassword(), userDto.getName(), userDto.getGender(), birthDay);
        userRepository.save(newUser);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean validateLogin(@Valid LoginDto loginDto) {
        return userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword()).isPresent();
    }

    public Set<Genre> getUserGenres(String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null) {
            return user.getGenres();
        }
        return null;

    }
}
