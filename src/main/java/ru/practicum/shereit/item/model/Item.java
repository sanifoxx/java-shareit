package ru.practicum.shereit.item.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shereit.user.model.User;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @NotNull
    @NotBlank(message = "The 'name' field cannot be empty")
    @Max(value = 255)
    @Column(name = "name")
    private String name;

    @NotNull
    @NotBlank
    @Max(value = 512)
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "available")
    private Boolean available;
}
