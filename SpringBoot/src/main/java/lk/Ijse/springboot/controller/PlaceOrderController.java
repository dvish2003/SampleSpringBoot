package lk.Ijse.springboot.controller;

import lk.Ijse.springboot.dto.*;
import lk.Ijse.springboot.service.CustomerService;
import lk.Ijse.springboot.service.ItemService;
import lk.Ijse.springboot.service.OrderService;
import lk.Ijse.springboot.util.VarList;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: vishmee
 * Date: 2/23/25
 * Time: 11:27 AM
 * Description:
 */

@RestController
@RequestMapping("api/v1/orders")
@CrossOrigin
public class PlaceOrderController {
    @Autowired
    private ResponseDTO responseDTO;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity placeOrder(@RequestBody PlaceOrderDTO placeOrderDTO){
        System.out.println("sadassad" +placeOrderDTO.getOrderDTO().getCustomer().getName());
        System.out.println("sadassad" +placeOrderDTO.getOrderDTO().getCustomer().getName());
        System.out.println("sadasssdasdasad" +placeOrderDTO);
        try{
            CustomerDTO customerDTO = customerService.FindByID(placeOrderDTO.getOrderDTO().getCustomer().getId());
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(1);
            orderDTO.setCustomer(customerDTO);
            orderDTO.setTotal(placeOrderDTO.getOrderDTO().getTotal());

            List<OrderDetailDTO> orderDetailDTO = new ArrayList<OrderDetailDTO>();
            for (OrderDetailDTO orderDetailDTO1 : placeOrderDTO.getOrderDetailsDTO()) {
                ItemDTO itemDTO = itemService.FindByID(orderDetailDTO1.getItem().getId());
                System.out.println("Item    "+itemDTO);
                orderDetailDTO.add(new OrderDetailDTO(orderDetailDTO1.getId(),orderDetailDTO1.getQty(),orderDetailDTO1.getPrice(),orderDTO,itemDTO));}
            PlaceOrderDTO placeOrderDTO1 = new PlaceOrderDTO();
            placeOrderDTO1.setOrderDTO(orderDTO);
            placeOrderDTO1.setOrderDetailsDTO(orderDetailDTO);

            System.out.println("sadassad     " +placeOrderDTO1.getOrderDTO().getCustomer().getName());
            System.out.println("sadasssdasdasad      " +placeOrderDTO1);

            orderService.placeOrder(placeOrderDTO1);
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Order placed successfully");
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
