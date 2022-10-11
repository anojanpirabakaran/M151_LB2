package com.example.webshop_be.config.generic;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class ExtendedEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Generated(GenerationTime.ALWAYS)
    @Column(name = "id")
    private String id;

    public ExtendedEntity() {
    }

    public ExtendedEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public ExtendedEntity setId(String id) {
        this.id = id;
        return this;
    }
}
