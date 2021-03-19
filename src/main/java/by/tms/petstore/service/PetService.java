package by.tms.petstore.service;

import by.tms.petstore.model.Pet;
import by.tms.petstore.model.PetStatus;
import by.tms.petstore.repository.PetRepository;
import by.tms.petstore.resource.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private PetRepository petRepository;

    //add new pet
    public Pet addNewPet(Pet pet) {
        return petRepository.save(pet);
    }

    //find pet by status
    public Pet findPetByStatus(PetStatus petStatus) {
        return petRepository.findPetByStatus(petStatus);
    }

    //find pet by id
    public Optional<Pet> findPetById(long id) {
        if (petRepository.findById(id).isPresent()) {
            return petRepository.findById(id);
        }
        throw new NotFoundException("Pet with id " + id + " not found");
    }

    //get all pets
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    //update pet
    public Pet updatePet(Pet petRequest) {
        petRepository.findById(petRequest.getId()).map(pet -> {
            pet.setName(petRequest.getName());
            pet.setCategory(petRequest.getCategory());
            pet.setStatus(petRequest.getStatus());
            pet.setTags(petRequest.getTags());
            return petRepository.save(pet);
        });
        return petRequest;
    }

    //delete pet
    public String deletePetById(long id) {
        if (petRepository.findById(id).isPresent()) {
            petRepository.deleteById(id);
        }
        throw new NotFoundException("Pet with id " + id + " not found");
    }

    @Autowired
    public void setPetRepository(PetRepository petRepository) {
        this.petRepository = petRepository;
    }
}
