package lk.Ijse.springboot.controller;

import lk.Ijse.springboot.dto.CustomerDTO;
import lk.Ijse.springboot.dto.ItemDTO;
import lk.Ijse.springboot.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/get")
    public List<ItemDTO> getItem(){
        List<ItemDTO> item = itemService.getAllItem();
        return item;
    }
    @PostMapping("/save")
    public boolean save(@RequestBody ItemDTO itemDTO){
        boolean resp = itemService.saveItem(itemDTO);
        if (resp){
            System.out.println("Item saved successfully");
            return resp;
        }
        return false;
    }
    @PutMapping("/update")
    public boolean Update(@RequestBody  ItemDTO itemDTO){
        boolean resp = itemService.UpdateItem(itemDTO);
        if(resp){
            System.out.println("Item updated successfully");
            return resp;
        }
        return false;
    }
    @DeleteMapping("/delete")
    public boolean delete(@PathVariable  String id){
        boolean resp = itemService.DeleteItem(id);
        if (resp){
            System.out.println("Item deleted");
            return resp;

        }
        return false;
    }
}
