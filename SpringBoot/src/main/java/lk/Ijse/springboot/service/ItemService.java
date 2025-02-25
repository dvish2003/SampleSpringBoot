package lk.Ijse.springboot.service;

import lk.Ijse.springboot.dto.CustomerDTO;
import lk.Ijse.springboot.dto.ItemDTO;
import lk.Ijse.springboot.entity.Customer;
import lk.Ijse.springboot.entity.Item;
import lk.Ijse.springboot.repo.ItemRepo;
import lk.Ijse.springboot.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
private ItemRepo itemRepo;
    @Autowired
    private ModelMapper modelMapper;
    public String saveItem(ItemDTO itemDTO){
        if (itemRepo.existsById(itemDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }else {
            itemRepo.save(modelMapper.map(itemDTO, Item.class));
            return VarList.RSP_SUCCESS;
        }

    }
    public List<ItemDTO> getAllItem(){
        List<Item> itemList = itemRepo.findAll();
        return modelMapper.map(itemList,new TypeToken<ArrayList<ItemDTO>>(){
        }.getType());
    }

    public String UpdateItem(ItemDTO itemDTO) {
        if (itemRepo.existsById(itemDTO.getId())){
            itemRepo.save(modelMapper.map(itemDTO, Item.class));
            return VarList.RSP_SUCCESS;

        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public String DeleteItem(String id) {
        if (itemRepo.existsById(Integer.valueOf(id))){
            itemRepo.deleteById(Integer.valueOf(id));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }

    }
    public ItemDTO FindByID(int id) {
        if (itemRepo.existsById(id)){
            return modelMapper.map(itemRepo.findById(id).get(), ItemDTO.class);
        } else {
            return null;
        }
    }
}
