package com.ifelseelif.touristapp.service.interfaces;

import com.ifelseelif.touristapp.models.dto.AuthResponse;
import com.ifelseelif.touristapp.models.dto.UserDto;

public interface IAuthService {

    void register(UserDto userDto);
    AuthResponse login(UserDto userDto);

}
