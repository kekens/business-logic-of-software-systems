package com.ifelseelif.responseservice.models.dto;

import com.ifelseelif.responseservice.models.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

    private String message;
    private User user;

}
