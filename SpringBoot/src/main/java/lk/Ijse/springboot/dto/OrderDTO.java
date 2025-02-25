package lk.Ijse.springboot.dto;

import jakarta.persistence.*;
import lk.Ijse.springboot.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author: vishmee
 * Date: 2/22/25
 * Time: 10:44 PM
 * Description:
 */

@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrderDTO {
    private int id;
    private double total;
    private CustomerDTO customer;
}
