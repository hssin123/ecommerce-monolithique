package com.securityModel.payload.response;





import com.securityModel.models.User;
import com.securityModel.payload.request.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String username;


    public static UserResponse fromEntity(User user) {
        UserResponse subUserResponse = new UserResponse();
        BeanUtils.copyProperties(user, subUserResponse);

        return subUserResponse;
    }

    public static User toEntity(UserRequest userRequest) {
        User s = new User();
        BeanUtils.copyProperties(userRequest, s);
        return s;
    }
}
