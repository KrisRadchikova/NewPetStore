package by.tms.petstore.service;

import by.tms.petstore.model.Order;
import by.tms.petstore.model.Pet;
import by.tms.petstore.model.PetStatus;
import by.tms.petstore.repository.PetRepository;
import by.tms.petstore.repository.StoreRepository;
import by.tms.petstore.resource.exception.ExistsException;
import by.tms.petstore.resource.exception.InvalidSuppliedException;
import by.tms.petstore.resource.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class StoreService {

    private StoreRepository storeRepository;

    private PetService petService;

    private PetRepository petRepository;

    //place an order for a pet
    public Order placeAnOrderForAPet(Order order) {
        ExampleMatcher placeAnOrderMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("petId", ignoreCase())
                .withMatcher("userId", ignoreCase())
                .withMatcher("quantity", ignoreCase())
                .withMatcher("shipDate", ignoreCase())
                .withMatcher("petStatus", ignoreCase())
                .withMatcher("orderStatus", ignoreCase());
        Example<Order> orderExample = Example.of(order, placeAnOrderMatcher);
        if (!storeRepository.exists(orderExample)) {
            return storeRepository.save(order);
        }
        throw new ExistsException("This order exists");
    }

    //find purchase order by id
    public Optional<Order> findPurchaseOrderById(long id) {
        if (storeRepository.findById(id).isPresent()) {
            return storeRepository.findById(id);
        }
        throw new NotFoundException("Order with id " + id + " not found");
    }

    //delete purchase order by id
    public void deletePurchaseOrderById(long id) {
        if (storeRepository.findById(id).isPresent()) {
            storeRepository.deleteById(id);
        }
        throw new NotFoundException("Order with id " + id + " not found");
    }

    //returns pet inventories by status
    public Pet returnsPetInventoriesByStatus(long petId) {
        if (petRepository.findById(petId).isPresent()) {
            Optional<Pet> pet = petService.findPetById(petId);
            pet.map(p -> {
                p.setStatus(PetStatus.AVAILABLE);
                return petRepository.save(p);
            });
        }
        throw new InvalidSuppliedException("Invalid id supplied for pet with id" + petId);
    }

    public List<Order> getAllOrders() {
        return storeRepository.findAll();
    }

    @Autowired
    public void setStoreRepository(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Autowired
    public void setPetService(PetService petService) {
        this.petService = petService;
    }

    @Autowired
    public void setPetRepository(PetRepository petRepository) {
        this.petRepository = petRepository;
    }
}
