package com.ifelseelif.touristapp.models.dto;

import com.ifelseelif.touristapp.models.domain.MaterialRequest;
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
