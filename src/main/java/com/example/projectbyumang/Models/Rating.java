package com.example.projectbyumang.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rating extends BaseModel{
    private Integer rate;
    private Integer count;
}
