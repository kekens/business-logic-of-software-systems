package com.ifelseelif.blsslab1.service;

import com.ifelseelif.blsslab1.database.UserRepository;
import com.ifelseelif.blsslab1.models.domain.DbUser;
import com.ifelseelif.blsslab1.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService implements IUserService {

    private final
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DbUser findByLogin(String username) {
        return userRepository.findByLogin(username);
    }
}
