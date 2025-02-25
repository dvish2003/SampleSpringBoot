package lk.Ijse.springboot.dto;

import lombok.*;

import java.util.List;

/**
 * Author: vishmee
 * Date: 2/23/25
 * Time: 11:55 AM
 * Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class PlaceOrderDTO {
    private OrderDTO orderDTO;
    private List<OrderDetailDTO> orderDetailsDTO;
}
