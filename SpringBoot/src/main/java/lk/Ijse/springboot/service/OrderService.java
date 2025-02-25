package lk.Ijse.springboot.service;

import lk.Ijse.springboot.dto.OrderDTO;
import lk.Ijse.springboot.dto.OrderDetailDTO;
import lk.Ijse.springboot.dto.PlaceOrderDTO;
import lk.Ijse.springboot.entity.Customer;
import lk.Ijse.springboot.entity.Item;
import lk.Ijse.springboot.entity.Order;
import lk.Ijse.springboot.entity.OrderDetail;
import lk.Ijse.springboot.repo.CustomerRepo;
import lk.Ijse.springboot.repo.ItemRepo;
import lk.Ijse.springboot.repo.OrderDetailRepo;
import lk.Ijse.springboot.repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private OrderDetailRepo orderDetailRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void placeOrder(PlaceOrderDTO placeOrderDTO) {
        try {
            OrderDTO orderDTO = placeOrderDTO.getOrderDTO();
            Customer customer = customerRepo.findById(orderDTO.getCustomer().getId())
                    .orElseThrow(() -> new RuntimeException("can not find customer: " + orderDTO.getCustomer().getId()));

            Order order = new Order();
            order.setTotal(orderDTO.getTotal());
            order.setCustomer(customer);

            // Save Order and Genarate id
            Order savedOrder = orderRepo.save(order);

            //  Order Details save
            List<OrderDetailDTO> orderDetailDTOs = placeOrderDTO.getOrderDetailsDTO();

            for (OrderDetailDTO orderDetailDTO : orderDetailDTOs) {
                Item item = itemRepo.findById(orderDetailDTO.getItem().getId())
                        .orElseThrow(() -> new RuntimeException("can not find item: " + orderDetailDTO.getItem().getId()));

                if (item.getQty() < orderDetailDTO.getQty()) {
                    throw new RuntimeException("not available stock: " + item.getId());
                }

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(savedOrder);
                orderDetail.setItem(item);
                orderDetail.setQty(orderDetailDTO.getQty());
                orderDetail.setPrice(orderDetailDTO.getPrice());


                orderDetailRepo.save(orderDetail);

                // update item stock
                item.setQty(item.getQty() - orderDetailDTO.getQty());
                itemRepo.save(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error placing order: " + e.getMessage());
        }
    }
}

