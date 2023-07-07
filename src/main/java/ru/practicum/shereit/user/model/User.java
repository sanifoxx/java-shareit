package ru.practicum.shereit.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class User {

    private Long id;

    @NotBlank(message = "The 'name' field cannot be empty")
    private String name;

    @NotBlank(message = "The 'email' field cannot be empty")
    @Email(message = "The 'email'='${validatedValue}' field must match the E-mail format")
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
