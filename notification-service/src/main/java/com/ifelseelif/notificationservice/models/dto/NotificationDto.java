package com.ifelseelif.notificationservice.models.dto;

import com.ifelseelif.notificationservice.models.domain.User;
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
