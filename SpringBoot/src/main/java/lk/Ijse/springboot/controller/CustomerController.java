
package lk.Ijse.springboot.controller;

import lk.Ijse.springboot.dto.CustomerDTO;
import lk.Ijse.springboot.dto.ResponseDTO;
import lk.Ijse.springboot.service.CustomerService;
import lk.Ijse.springboot.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")  // cors ek aniwaren dnn oni
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping("/get")
    public ResponseEntity getCustomer(){
        try {
            List<CustomerDTO> customerDTOList = customerService.getAllCustomer();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(customerDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PostMapping("/save")
    public ResponseEntity saveCustomer(@RequestBody  CustomerDTO customerDTO){
     try{
         String response = customerService.saveCustomer(customerDTO);
         if(response.equals("00")){
             responseDTO.setMessage("Success");
             responseDTO.setContent(customerDTO);
             return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
         } else if (response.equals("06")) {
             responseDTO.setCode(VarList.RSP_DUPLICATED);
             responseDTO.setMessage("Already Registered");
             responseDTO.setContent(customerDTO);
             return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

         } else {
             responseDTO.setCode(VarList.RSP_FAIL);
             responseDTO.setMessage("Error");
             responseDTO.setContent(null);
             return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
         }
         
     } catch (Exception e) {
         responseDTO.setCode(VarList.RSP_ERROR);
         responseDTO.setMessage(e.getMessage());
         responseDTO.setContent(null);
         return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
     }
    }
    
    
    
 @PutMapping("/update")
    public ResponseEntity Update(@RequestBody  CustomerDTO customerDTO){
     try{
         String response = customerService.updateCustomer(customerDTO);
         if(response.equals("00")){
             responseDTO.setMessage("Success");
             responseDTO.setContent(customerDTO);
             return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
         } else if (response.equals("01")) {
             responseDTO.setCode(VarList.RSP_DUPLICATED);
             responseDTO.setMessage("Not a Registered");
             responseDTO.setContent(customerDTO);
             return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

         } else {
             responseDTO.setCode(VarList.RSP_FAIL);
             responseDTO.setMessage("Error");
             responseDTO.setContent(null);
             return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
         }

     } catch (Exception e) {
         responseDTO.setCode(VarList.RSP_ERROR);
         responseDTO.setMessage(e.getMessage());
         responseDTO.setContent(null);
         return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
     }
 }

 @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable  String id){
      try {
          String res = customerService.DeleteCustomer(id);
          if (res.equals("00")) {
              responseDTO.setCode(VarList.RSP_SUCCESS);
              responseDTO.setMessage("Success");
              responseDTO.setContent(null);
              return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
          } else {
              responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
              responseDTO.setMessage("No Customer Available For this CustomerID");
              responseDTO.setContent(null);
              return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
          }
      } catch (Exception e) {
          responseDTO.setCode(VarList.RSP_ERROR);
          responseDTO.setMessage(e.getMessage());
          responseDTO.setContent(e);
          return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);      }
 }


}
