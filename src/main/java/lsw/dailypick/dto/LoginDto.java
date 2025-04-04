package lsw.dailypick.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {

    @NotBlank(message = "이메일은 공백일 수 없습니다")
    private String email;

    @NotBlank(message = "비밀번호는 공백일 수 없습니다")
    private String password;
}
