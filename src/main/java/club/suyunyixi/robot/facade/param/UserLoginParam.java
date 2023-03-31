package club.suyunyixi.robot.facade.param;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author Suyunyixi
 * @date 2023/2/6 22:24
 **/
@Data
public class UserLoginParam {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotNull
    private Boolean auto = Boolean.TRUE;
}