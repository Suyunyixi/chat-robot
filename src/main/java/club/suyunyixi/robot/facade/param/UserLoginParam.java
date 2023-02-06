package club.suyunyixi.robot.facade.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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