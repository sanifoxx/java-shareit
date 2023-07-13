package ru.practicum.shereit.item.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.shereit.user.model.User;

import java.util.Objects;

@Data
@AllArgsConstructor
@Builder
public class Item {

    private Long id;

    private User owner;

    @NotBlank(message = "The 'name' field cannot be empty")
    private String name;

    private String description;

    private Boolean available;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!Objects.equals(id, item.id)) return false;
        return Objects.equals(owner, item.owner);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }
}
