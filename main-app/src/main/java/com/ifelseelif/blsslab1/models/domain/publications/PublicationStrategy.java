package com.ifelseelif.blsslab1.models.domain.publications;

import com.ifelseelif.blsslab1.models.domain.Material;
import com.ifelseelif.blsslab1.models.dto.TypeMaterial;

public interface PublicationStrategy {
    TypeMaterial getTypeMaterial();

    void setPublicationInMaterial(Material material);
}
