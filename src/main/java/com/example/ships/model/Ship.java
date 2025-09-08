package com.example.ships.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ship {
    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    private String description;

    @Email(message = "ownerEmail must be a valid email")
    private String ownerEmail;

    @NotNull
    private LocalDateTime timestamp;
}
