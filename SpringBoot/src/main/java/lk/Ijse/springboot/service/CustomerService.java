package lk.Ijse.springboot.service;

import lk.Ijse.springboot.dto.CustomerDTO;
import lk.Ijse.springboot.entity.Customer;
import lk.Ijse.springboot.repo.CustomerRepo;
import lk.Ijse.springboot.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service  // service eke service annotation ek aniwaren dnn oni
@Transactional
public class CustomerService {
@Autowired
    private CustomerRepo customerRepo;
@Autowired
    private ModelMapper modelMapper;
    public String saveCustomer(CustomerDTO customerDTO){
        if (customerRepo.existsById(customerDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }else {
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
            return VarList.RSP_SUCCESS;
        }

    }
    public List<CustomerDTO> getAll(){
        List<Customer> customerList = customerRepo.findAll();
        return modelMapper.map(customerList,new TypeToken<ArrayList<CustomerDTO>>(){
        }.getType());
    }

    public String updateCustomer(CustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getId())){
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
            return VarList.RSP_SUCCESS;

        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public String DeleteCustomer(String id) {
        if (customerRepo.existsById(Integer.valueOf(id))){
            customerRepo.deleteById(Integer.valueOf(id));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
