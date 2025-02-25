package lk.Ijse.springboot.dto;

/**
 * Author: vishmee
 * Date: 2/22/25
 * Time: 10:44 PM
 * Description:
 */

import lk.Ijse.springboot.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDTO {
    private int id;
    private int qty;
    private double price;
    private OrderDTO order;
    private ItemDTO item;
}
