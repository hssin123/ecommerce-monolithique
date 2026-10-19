package com.securityModel.service.imple;






import com.securityModel.payload.request.UserRequest;
import com.securityModel.payload.response.UserResponse;

import java.util.HashMap;
import java.util.List;

public interface UserService {


    UserResponse createuser(UserRequest user);
    List<UserResponse> allUser();
    UserResponse userById(Long id);
    UserResponse updateuser(UserRequest userRequest, Long id);
    HashMap<String, String> deleteuser(Long id);


}
