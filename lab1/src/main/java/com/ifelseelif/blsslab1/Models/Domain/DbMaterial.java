package com.ifelseelif.blsslab1.Models.Domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class DbMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;

    protected DbMaterial() { }

    public DbMaterial(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "DbMaterial{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
