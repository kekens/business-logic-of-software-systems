package com.ifelseelif.blsslab1.service.interfaces;

import com.ifelseelif.blsslab1.models.dto.AuthResponse;
import com.ifelseelif.blsslab1.models.dto.UserDto;

public interface IAuthService {

    void register(UserDto userDto);
    AuthResponse login(UserDto userDto);

}
