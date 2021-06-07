package com.ifelseelif.responseservice.service.interfaces;

import com.ifelseelif.responseservice.models.dto.AuthResponse;
import com.ifelseelif.responseservice.models.dto.UserDto;

public interface IAuthService {

    void register(UserDto userDto);
    AuthResponse login(UserDto userDto);

}
