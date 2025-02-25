package lk.Ijse.springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;
}
/*
ALTER TABLE item
MODIFY COLUMN id INT AUTO_INCREMENT;
ALTER TABLE customer
MODIFY COLUMN id INT AUTO_INCREMENT;
ALTER TABLE order_detail
MODIFY COLUMN id INT AUTO_INCREMENT;
ALTER TABLE orders
MODIFY COLUMN id INT AUTO_INCREMENT;
*/
