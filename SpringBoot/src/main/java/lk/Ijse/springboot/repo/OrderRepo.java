package lk.Ijse.springboot.repo;

import lk.Ijse.springboot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Author: vishmee
 * Date: 2/23/25
 * Time: 11:19 AM
 * Description:
 */

public interface OrderRepo extends JpaRepository<Order,Integer> {

    @Query(value = "SELECT * FROM orders ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    Order getLastOrder();

}
