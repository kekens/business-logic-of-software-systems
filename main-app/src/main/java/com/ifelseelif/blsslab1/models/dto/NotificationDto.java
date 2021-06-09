package com.ifelseelif.blsslab1.models.dto;

import com.ifelseelif.blsslab1.models.domain.User;
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
