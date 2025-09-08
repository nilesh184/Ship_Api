package com.example.ships.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ShipRequest {
    @NotBlank(message = "name is required")
    private String name;

    private String description;

    @Email(message = "ownerEmail must be a valid email")
    private String ownerEmail;
}
