package com.securityModel.controllers;




import com.securityModel.payload.request.UserRequest;
import com.securityModel.payload.response.UserResponse;
import com.securityModel.service.imple.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public UserResponse addUser(@RequestBody UserRequest userRequest) {
        return userService.createuser(userRequest);
    }


    @GetMapping("/all")
    public List<UserResponse> AllUser() {
        return userService.allUser();
    }

    @GetMapping("/getone/{id}")
    public UserResponse userbyid(@PathVariable Long id) {
        return userService.userById(id);
    }

    @PutMapping("/update/{id}")
    public UserResponse updateuser(@RequestBody UserRequest userRequest, @PathVariable Long id) {
        return userService.updateuser(userRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteuser(@PathVariable Long id) {
        return userService.deleteuser(id);
    }
    
}
