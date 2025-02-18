package lk.Ijse.springboot.repo;

import lk.Ijse.springboot.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item,Integer> {
}
