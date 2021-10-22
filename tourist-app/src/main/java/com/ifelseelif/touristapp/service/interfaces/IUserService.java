package com.ifelseelif.touristapp.service.interfaces;

import com.ifelseelif.touristapp.models.domain.User;

public interface IUserService {
    User findByLogin(String username);

    void saveUser(User u);

    User findByLoginAndPassword(String login, String password);
}
