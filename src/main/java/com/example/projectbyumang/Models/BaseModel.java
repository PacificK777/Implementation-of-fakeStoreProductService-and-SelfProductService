package com.example.projectbyumang.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;
    private Boolean isDeleted;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
        this.lastUpdated = new Date();
        this.isDeleted = false;
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdated = new Date();
    }
}

//@Temporal is a JPA annotation used to specify the actual type to be used when
// mapping a java.util.Date or java.util.Calendar - DATE, TIME, or TIMESTAMP.

// @PrePersist and @PreUpdate are JPA annotations used to configure callback
// methods that are automatically called when an entity is about to be persisted (saved)
// or updated, respectively.

/*
The @Temporal annotation specifies the SQL temporal type (DATE, TIME, or TIMESTAMP)
for a Java Date or Calendar field in a JPA entity, for example,
@Temporal(TemporalType.DATE) maps a date field to SQL DATE.

The @PrePersist
annotation marks a method to be called before an entity is inserted into the database,
such as setting a createdAt field to the current date.

@PreUpdate
annotation marks a method to be called before an entity is updated, like updating
an updatedAt field to the current date.
 */