
package lk.Ijse.springboot.controller;

import lk.Ijse.springboot.dto.CustomerDTO;
import lk.Ijse.springboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/get")
    public List<CustomerDTO> getCustomer(){
        List<CustomerDTO> customers = customerService.getAllEmployee();
        return customers;
    }
    @PostMapping("/save")
    public boolean saveCustomer(@RequestBody  CustomerDTO customerDTO){
       boolean resp = customerService.saveCustomer(customerDTO);
       if (resp){
           System.out.println("Customer saved successfully");
           return resp;
       }
       return false;
    }
 @PutMapping("/update")
    public boolean Update(@RequestBody  CustomerDTO customerDTO){
       boolean resp = customerService.updateCustomer(customerDTO);
       if(resp){
           System.out.println("Customer updated successfully");
           return resp;
       }
       return false;
    }
 @DeleteMapping("/delete")
    public boolean delete(@PathVariable  String id){
       boolean resp = customerService.DeleteCustomer(id);
       if (resp){
           System.out.println("Customer deleted");
           return resp;

       }
       return false;
    }


}
