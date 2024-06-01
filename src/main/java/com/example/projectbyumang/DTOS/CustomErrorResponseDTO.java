package com.example.projectbyumang.DTOS;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomErrorResponseDTO {
        private String message;
        private int status;
}
