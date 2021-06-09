package com.ifelseelif.responseservice.models.dto;

import com.ifelseelif.responseservice.models.domain.MaterialRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeratorAnswer {

    private MaterialRequest materialRequest;
    private String reason;
    private RequestStatus requestStatus;

    @Override
    public String toString() {
        return "ModeratorAnswer{" +
                "materialRequest=" + materialRequest +
                ", reason='" + reason + '\'' +
                ", requestStatus=" + requestStatus +
                '}';
    }
}