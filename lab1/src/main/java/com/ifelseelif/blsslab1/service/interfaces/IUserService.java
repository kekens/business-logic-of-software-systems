package com.ifelseelif.blsslab1.service.interfaces;

import com.ifelseelif.blsslab1.models.domain.DbUser;

public interface IUserService {
    DbUser findByLogin(String username);
}
