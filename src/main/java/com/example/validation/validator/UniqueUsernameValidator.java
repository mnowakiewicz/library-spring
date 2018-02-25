package com.example.validation.validator;

import com.example.validation.constraint.UniqueUsername;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueUsername uniqueUsername) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
           return !checkIfUsernameAlreadyExists(value);
    }

    /**
     *Returns true if username exists in database
     */
    private boolean checkIfUsernameAlreadyExists(String value) {
        try{
            List<String> usernames = new ArrayList<>();
            userRepository.findAll().forEach(user -> usernames.add(user.getUsername().toLowerCase().trim()));
            for (String username : usernames){
                if(username.equals(value.toLowerCase().trim()))
                    return true;
            }
        } catch (NullPointerException ex){}
        return false;
    }
}
