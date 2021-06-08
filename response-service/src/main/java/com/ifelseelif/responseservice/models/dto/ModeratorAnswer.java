package com.ifelseelif.responseservice.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeratorAnswer {

    private Long id;
    private String reason;
    private RequestStatus requestStatus;

    @Override
    public String toString() {
        return "ModeratorAnswer{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                '}';
    }
}
