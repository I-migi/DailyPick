package lsw.dailypick.dto;

import com.nimbusds.openid.connect.sdk.claims.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto{

    @NotBlank(message = "이메일은 필수입니다")
    @Email(message = "이메일 형식이 아닙니다")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다")
    private String password;

    @NotBlank(message = "비밀번호는 필수입니다")
    private String name;

    private String birthYear;

    private String birthMonth;

    private String birthDay;

    private String gender;
}
