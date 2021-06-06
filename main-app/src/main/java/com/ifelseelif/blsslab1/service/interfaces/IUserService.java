package com.ifelseelif.blsslab1.service.interfaces;

import com.ifelseelif.blsslab1.models.domain.User;

public interface IUserService {
    User findByLogin(String username);

    void saveUser(User u);

    User findByLoginAndPassword(String login, String password);
}
