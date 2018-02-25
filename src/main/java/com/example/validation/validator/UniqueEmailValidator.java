package com.example.validation.validator;

import com.example.validation.constraint.UniqueEmail;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String>{
    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UniqueEmail uniqueEmail) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return !checkIfEmailAlreadyExists(value);
    }

    /**
     * Returns true if email exists in database
     */
    private boolean checkIfEmailAlreadyExists(String value) {
        try{
            List<String> emails = new ArrayList<>();
            userRepository.findAll().forEach(user -> emails.add(user.getEmail().toLowerCase().trim()));
            for (String email : emails){
                if(email.equals(value.toLowerCase().trim()))
                    return true;
            }
        } catch (NullPointerException ex){}
        return false;
    }
}
