package com.bilgeadam.services;

import com.bilgeadam.entity.Follow;
import com.bilgeadam.entity.User;
import com.bilgeadam.model.UserModel;
import com.bilgeadam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FollowService followService;

    public List<User> findAll(){
        return this.userRepository.findAll();
    }
    public List<User> findUserBySearch(String key)
    {
        List<User> searchList = userRepository.findUsersByUsernameContainingIgnoreCase(key);
        for (User user :
                userRepository.findUsersByFirstnameContainingIgnoreCase(key)) {
            boolean found = false;
            for (User temp :
                    searchList) {
                if (temp.getUsername() == user.getUsername())
                    found=true;
            }
            if(!found)
                searchList.add(user);
        }
        for (User user :
                userRepository.findUsersByLastnameContainingIgnoreCase(key)) {
            boolean found = false;
            for (User temp :
                    searchList) {
                if (temp.getUsername() == user.getUsername())
                    found=true;
            }
            if(!found)
                searchList.add(user);
        }
        return searchList;
    }
    public void updateUser(String username, User user)
    {
        User temp = userRepository.findByUsername(username);
        temp.setEmail(user.getEmail());
        temp.setFirstname(user.getFirstname());
        temp.setLastname(user.getLastname());
        temp.setPassword(user.getPassword());
        temp.setUsername(user.getUsername());

        userRepository.save(temp);
    }
    public void save(User user){
        userRepository.save(user);
        followService.follow(new Follow(null,user.getUsername(),user.getUsername()));
    }
    public User getUserByUserName(String username)
    {
        return this.userRepository.findByUsername(username);
    }
    public UserModel getUserModel(String username)
    {
        User user = userRepository.findByUsername(username);
        UserModel userModel = new UserModel();
        userModel.setUsername(username);
        userModel.setFirstName(user.getFirstname());
        userModel.setLastName(user.getLastname());
        userModel.setFollowings(user.getPerson().size() - 1);
        userModel.setFollowers(followService.getFollowersCount(username) - 1);
        userModel.setTweetCount(user.getTweets().size());
        return userModel;
    }
}
