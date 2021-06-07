package com.ifelseelif.responseservice.service.interfaces;


import com.ifelseelif.responseservice.models.domain.User;

public interface IUserService {
    User findByLogin(String username);

    void saveUser(User u);

    User findByLoginAndPassword(String login, String password);
}
