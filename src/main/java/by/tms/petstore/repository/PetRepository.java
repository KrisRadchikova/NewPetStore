package by.tms.petstore.repository;

import by.tms.petstore.model.Pet;
import by.tms.petstore.model.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Pet findPetByStatus(PetStatus petStatus);
}
