package by.tms.petstore.resource;

import by.tms.petstore.model.Pet;
import by.tms.petstore.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/pet")
@Slf4j
public class PetResource {

    private PetService petService;

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Pet>> getAllPets() {
        log.info("Get all pets");
        return new ResponseEntity<>(petService.getAllPets(), HttpStatus.OK);
    }

    @GetMapping(path = "/find/{petId}")
    public Optional<Pet> findById(@PathVariable long petId) {
        log.info("Add new pet with ID " + petId);
        return petService.findPetById(petId);
    }

    @PostMapping(path = "/add")
    public Pet addNewPet(@RequestBody Pet pet) {
        log.info("Add new pet " + pet.getName());
        return petService.addNewPet(pet);
    }

    @PutMapping(path = "/update")
    public Pet updatePet(@RequestBody Pet pet) {
        log.info("Update pet " + pet.getName());
        return petService.updatePet(pet);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deletePet(@PathVariable long id) {
        log.info("Delete pet with ID " + id);
        petService.deletePetById(id);
    }


    @Autowired
    public void setPetService(PetService petService) {
        this.petService = petService;
    }
}
