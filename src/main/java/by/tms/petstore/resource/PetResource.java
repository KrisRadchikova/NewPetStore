package by.tms.petstore.resource;

import by.tms.petstore.model.Pet;
import by.tms.petstore.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/pet")
public class PetResource {

    private PetService petService;

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Pet>> getAllPets() {
        return new ResponseEntity<>(petService.getAllPets(), HttpStatus.OK);
    }

    @GetMapping(path = "/find/{petId}")
    public Optional<Pet> findById(@PathVariable long petId) {
        return petService.findPetById(petId);
    }

    @PostMapping(path = "/add")
    public Pet addNewPet(@RequestBody Pet pet) {
        return petService.addNewPet(pet);
    }

    @PutMapping(path = "/update")
    public Pet updatePet(@RequestBody Pet pet) {
        return petService.updatePet(pet);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deletePet(@PathVariable long id) {
        petService.deletePetById(id);
    }


    @Autowired
    public void setPetService(PetService petService) {
        this.petService = petService;
    }
}
