package com.securityModel.service.imple;




import com.securityModel.models.User;
import com.securityModel.payload.request.UserRequest;
import com.securityModel.payload.response.UserResponse;
import com.securityModel.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImple implements UserService {



    private final UserRepository userRepository;

    public UserServiceImple(UserRepository userRepositoy) {

        this.userRepository = userRepositoy;
    }

    @Override
    public UserResponse createuser(UserRequest user) {
        User c =UserResponse.toEntity(user);
        User saveduser= userRepository.save(c);
        return UserResponse.fromEntity(saveduser);
    }

    @Override
    public List<UserResponse> allUser() {
        return userRepository.findAll().stream()
                .map(UserResponse::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public UserResponse userById(Long id) {

        return userRepository.findById(id)
                .map(UserResponse::fromEntity)
                .orElseThrow(() ->new RuntimeException("User not found with this id:" +id));

    }

    @Override
    public UserResponse updateuser(UserRequest userRequest, Long id) {

        User user= userRepository.findById(id).orElseThrow(()->
                new RuntimeException("User not found with this id:" +id));
        if (user != null) {
            User c = UserResponse.toEntity(userRequest);
            c.setId(id);
            c.setEmail(c.getEmail() == null ? user.getEmail() : c.getEmail());
            User saveduser = userRepository.save(c);
            return UserResponse.fromEntity(saveduser);
        }else{
            return null;
        }

    }

    @Override
    public HashMap<String, String> deleteuser(Long id) {

        HashMap message =new HashMap<>();
        User c = userRepository.findById(id).orElse(null);
        if (c != null) {
            try {
                userRepository.deleteById(id);
                message.put("message", "User deleted successfully");
            }catch (Exception a){
                message.put("message", a.getMessage());
            }
        }else{
            message.put("message", "User not found: " +id);
        }
        return message;

    }
}
