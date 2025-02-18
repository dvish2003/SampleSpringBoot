package lk.Ijse.springboot.service;

import lk.Ijse.springboot.dto.ItemDTO;
import lk.Ijse.springboot.entity.Item;
import lk.Ijse.springboot.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
private ItemRepo itemRepo;
    public List<ItemDTO> getAllItem() {
        List<Item> items = itemRepo.findAll();
        return items.stream().map(item -> new ItemDTO(item.getId(), item.getName(), item.getPrice(), item.getQty())).collect(Collectors.toList());
    }

    public boolean saveItem(ItemDTO itemDTO) {
        Item item = new Item(itemDTO.getId(), itemDTO.getName(), itemDTO.getPrice(),itemDTO.getQty());
        itemRepo.save(item);
        return true;
    }

    public boolean UpdateItem(ItemDTO itemDTO) {
        if(itemRepo.existsById(itemDTO.getId())){
            Item item = new Item(itemDTO.getId(), itemDTO.getName(), itemDTO.getPrice(),itemDTO.getQty());
            itemRepo.save(item);
            return true;
        }
        return false;
    }

    public boolean DeleteItem(String id) {
        if(itemRepo.existsById(Integer.valueOf(id))){
            itemRepo.deleteById(Integer.parseInt(id));
            return true;
        }
        return false;
    }
}
