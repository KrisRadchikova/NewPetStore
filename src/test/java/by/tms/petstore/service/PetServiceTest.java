package by.tms.petstore.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
//resources
class PetServiceTest {

    @Autowired
    public PetService petService;


    @Test
    void contextLoads() {
        assertThat(petService).isNotNull();
    }

    @Test
    void addNewPet() {

    }

    @Test
    void findPetByStatus() {
    }

    @Test
    void findPetById() {
    }

    @Test
    void getAllPets() {
    }

    @Test
    void updatePet() {
    }

    @Test
    void deletePetById() {
    }
}