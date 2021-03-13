package shop.repos;

import org.springframework.data.repository.CrudRepository;
import shop.entity.Item;

public interface PhoneRepo extends CrudRepository<Item,Long> {

}
