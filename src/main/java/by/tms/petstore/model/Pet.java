package by.tms.petstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @NotEmpty
    @NotBlank
    private Category category;

    @NotEmpty
    @NotBlank
    @Size(min = 3)
    private String name;

    @NotEmpty
    @NotBlank
    private PetStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    @NotEmpty
    @NotBlank
    private List<Tag> tags;
}
