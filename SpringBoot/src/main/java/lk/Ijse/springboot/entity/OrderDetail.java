package lk.Ijse.springboot.entity;

/**
 * Author: vishmee
 * Date: 2/22/25
 * Time: 10:44 PM
 * Description:
 */
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int qty;
    private double price;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Item item;
}
