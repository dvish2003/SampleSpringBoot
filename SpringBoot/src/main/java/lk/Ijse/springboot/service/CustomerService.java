package lk.Ijse.springboot.service;

import lk.Ijse.springboot.dto.CustomerDTO;
import lk.Ijse.springboot.entity.Customer;
import lk.Ijse.springboot.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service  // service eke service annotation ek aniwaren dnn oni
public class CustomerService {
@Autowired
    private CustomerRepo customerRepo;
    public Boolean saveCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress());
        customerRepo.save(customer);
        return true;

    }
    public List<CustomerDTO> getAllEmployee(){
        List<Customer> employeeList = customerRepo.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customerDTO : employeeList){
            CustomerDTO customerDTO1 = new CustomerDTO(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress());
            customerDTOS.add(customerDTO1);
        }
        return customerDTOS;
    }

    public boolean updateCustomer(CustomerDTO customerDTO) {
        if(customerRepo.existsById(customerDTO.getId())){
            Customer customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress());
            customerRepo.save(customer);
        }
        return true;
    }

    public boolean DeleteCustomer(String id) {
if(customerRepo.existsById(Integer.valueOf(id))){
    customerRepo.deleteById(Integer.parseInt(id));

}        return true;
    }
}
