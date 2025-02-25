package lk.Ijse.springboot.repo;

import lk.Ijse.springboot.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: vishmee
 * Date: 2/23/25
 * Time: 11:20 AM
 * Description:
 */

public interface OrderDetailRepo extends JpaRepository<OrderDetail,Integer> {
}
