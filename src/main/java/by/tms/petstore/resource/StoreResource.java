package by.tms.petstore.resource;

import by.tms.petstore.model.Order;
import by.tms.petstore.model.Pet;
import by.tms.petstore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/store")
public class StoreResource {

    private StoreService storeService;


    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(storeService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping(path = "/inventory/{petId}")
    public Pet returnsPetInventoriesByPetStatus(@PathVariable long petId) {
        return storeService.returnsPetInventoriesByStatus(petId);
    }

    @GetMapping(path = "/order/{orderId}")
    public Optional<Order> findPurchase(@PathVariable long orderId) {
        return storeService.findPurchaseOrderById(orderId);
    }

    @PostMapping(path = "/placeAnOrder")
    public Order placeAnOrderForAPet(@RequestBody Order order) {
        return storeService.placeAnOrderForAPet(order);
    }

    @DeleteMapping(path = "/delete/{orderId}")
    public void deletePurchase(@PathVariable long orderId) {
        storeService.deletePurchaseOrderById(orderId);
    }


    @Autowired
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }
}
