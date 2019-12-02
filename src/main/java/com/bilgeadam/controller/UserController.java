package com.bilgeadam.controller;

import com.bilgeadam.entity.User;
import com.bilgeadam.model.PostModel;
import com.bilgeadam.model.UserModel;
import com.bilgeadam.repository.UserRepository;
import com.bilgeadam.services.FollowService;
import com.bilgeadam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private FollowService followService;

    @RequestMapping(method = RequestMethod.GET,value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<User> allUsers(){
        return userService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/search/{key}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> searchUser(@PathVariable String key){
        return userService.findUserBySearch(key);
    }

    @RequestMapping(method = RequestMethod.GET,value = {"/{username}"})
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable String username)
    {
        return userService.getUserByUserName(username);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String username,@RequestBody User user){
        userService.updateUser(username,user);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/userinfo/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserModel getUserModel(@PathVariable String username)
    {
        return userService.getUserModel(username);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/recommend/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserModel> recommendFollows(@PathVariable String username){
        List<User> users = userService.findAll();
        List<User> selectedList = new ArrayList<User>();
        List<UserModel> returnList = new ArrayList<UserModel>();
        for (User user :
                users) {
            if(selectedList.size() >= 3)
                break;
            if (!user.getUsername().equals(username)) {
                if(!followService.isFollowing(username,user.getUsername())){
                    selectedList.add(user);
                }
            }
        }
        for (User user :
                selectedList) {
            UserModel userModel = new UserModel();
            userModel.setUsername(user.getUsername());
            userModel.setFirstName(user.getFirstname());
            userModel.setLastName(user.getLastname());
            returnList.add(userModel);
        }
        return returnList;
    }
}
