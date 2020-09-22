package com.nju.miaosha.request;

import com.nju.miaosha.annotation.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: jaggerw
 * @Description: TODO
 * @Date: 2020/9/22
 */
@Data
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = -6334359108431884757L;

    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32, max = 32)
    private String password;
}
