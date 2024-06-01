package com.example.projectbyumang.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"id", "createdAt", "lastUpdated", "isDeleted"}) // Ignore specified properties during serialization
public class Rating extends BaseModel{
    private Double rate;
    private Integer count;
}
