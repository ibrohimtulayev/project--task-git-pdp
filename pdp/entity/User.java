package uz.pdp.entity;

import java.io.Serializable;
import java.util.TimeZone;
import java.util.UUID;

public class User implements Serializable {

    private final UUID id = UUID.randomUUID();
    private String name;
    private String phone;



    public User(String name, String phone, String password) {
        this.name = name;
        this.phone = phone;



    }

    public User(String phone, String password) {
        this.phone = phone;

    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }



    @Override
    public String toString() {
        return name;
    }
}
