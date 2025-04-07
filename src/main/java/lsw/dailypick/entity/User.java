package lsw.dailypick.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String gender;

    private LocalDate birthday;

    private String profileImageUrl;

    @ManyToMany
    private Set<Genre> genres;

    public User(String email, String password, String name, String gender, LocalDate birthday) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;

    }

}
