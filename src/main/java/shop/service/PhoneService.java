package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.entity.Item;
import shop.repos.PhoneRepo;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepo phoneRepo;

    @Transactional
    public Iterable<Item> loadAllPhone(){
        return phoneRepo.findAll();
    }

    @Transactional
    public Item saveItems(Item item){
        return phoneRepo.save(item);
    }

    @Transactional
    public void deleteItems(Item item){
        phoneRepo.delete(item);
    }
}
