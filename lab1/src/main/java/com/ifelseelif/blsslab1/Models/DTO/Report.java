package com.ifelseelif.blsslab1.Models.DTO;

import javax.validation.constraints.NotNull;

public class Report {

    @NotNull
    private TypeMaterial typeMaterial;
    @NotNull
    private long id;
    @NotNull
    private String text;

    public Report() {
    }

    public TypeMaterial getTypeMaterial() {
        return typeMaterial;
    }

    public void setTypeMaterial(TypeMaterial typeMaterial) {
        this.typeMaterial = typeMaterial;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
