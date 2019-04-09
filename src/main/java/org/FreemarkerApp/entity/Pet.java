package org.FreemarkerApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Pet {
    protected long id;
    protected String name;
    private long owner_id;
    @JsonIgnore
    private String ownerName;

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public Pet(String name) {
        this.name = name;
    }

    public Pet(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Pet(long id, String name, long owner_id) {
        this.id = id;
        this.name = name;
        this.owner_id = owner_id;
    }

    public Pet() {

    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner_id=" + owner_id +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}