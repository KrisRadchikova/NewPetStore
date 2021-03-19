package by.tms.petstore.service;

import by.tms.petstore.model.Category;
import by.tms.petstore.model.Pet;
import by.tms.petstore.model.PetStatus;
import by.tms.petstore.model.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
//resources
class PetServiceTest {

    private List<Pet> PETS = new ArrayList<>();

    public PetServiceTest() {
        PETS.add(new Pet(new Category("Cat"), "Dik", PetStatus.AVAILABLE,
                new ArrayList<Tag>(Arrays.asList(new Tag("sphinx"), new Tag("cat")))));
    }

    @Autowired
    public PetService petService;


    @Test
    void contextLoads() {
        assertThat(petService).isNotNull();
    }

    @Test
    void addNewPet() {
        List<Pet> saved = PETS.stream()
                .map(petService::addNewPet)
                .collect(Collectors.toList());
        for (int i = 0; i < PETS.size(); i++) {
            Pet pet = PETS.get(i);
            Pet savedPet = saved.get(i);
            assertNotNull(savedPet);
            assertEquals(pet.getName(), savedPet.getName());
            assertEquals(pet.getCategory(), savedPet.getCategory());
            assertEquals(pet.getStatus(), savedPet.getStatus());
            assertEquals(pet.getTags(), savedPet.getTags());
/*
            assertPetFieldsEquals(pet, savedPet,
                    Pet::getName, Pet::getCategory, Pet::getStatus, Pet::getTags);
*/
        }
    }

    /*private void assertPetFieldsEquals(Pet first, Pet second, Function<Pet, Object>... getters){
        for(Function<Pet, Object> getter: getters){
            assertEquals(getter.apply(first), getter.apply(second));
        }
    }*/

    @Test
    void findPetByStatus() {
        Pet petAVAILABLE = petService.findPetByStatus(PetStatus.AVAILABLE);
        Pet petPENDING = petService.findPetByStatus(PetStatus.PENDING);
        Pet petSOLD = petService.findPetByStatus(PetStatus.SOLD);

    }

    @Test
    void findPetById() {
        Optional<Pet> pet = petService.findPetById(1);
        assertTrue(pet.isPresent());
    }

    @Test
    void getAllPets() {
        List<Pet> pets = petService.getAllPets();
        assertEquals(PETS.size(), pets.size());
    }

    @Test
    void updatePet() {
    }

    @Test
    void deletePetById() {
        String pet = petService.deletePetById(1);
        assertNull(pet);
    }
}
