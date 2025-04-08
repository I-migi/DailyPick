package lsw.dailypick.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;

@Getter
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

    @Setter
    private Boolean prefersDomestic;

    @Setter
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
