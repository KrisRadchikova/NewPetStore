package by.tms.petstore.repository;

import by.tms.petstore.model.Order;
import by.tms.petstore.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Order, Long> {
    Order findOrderByOrderStatus(OrderStatus orderStatus);
}
