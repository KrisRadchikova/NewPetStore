package by.tms.petstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "store")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @NotBlank
    private long petId;

    @NotEmpty
    @NotBlank
    private long userId;

    @NotEmpty
    @NotBlank
    private int quantity;

    @NotEmpty
    @NotBlank
    private LocalDateTime shipDate;

    @NotEmpty
    @NotBlank
    private PetStatus petStatus;

    @NotEmpty
    @NotBlank
    private OrderStatus orderStatus;

    @NotEmpty
    @NotBlank
    private boolean complete;
}
