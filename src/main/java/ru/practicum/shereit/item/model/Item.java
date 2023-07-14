package ru.practicum.shereit.item.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shereit.user.model.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @NotBlank(message = "The 'name' field cannot be empty")
    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(max = 512)
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "available")
    private Boolean available;
}
