package dao;

import java.util.List;
import vo.Item;

public interface ItemDao {
	List<Item> listByUserId(int userid);
	Boolean save(Item item);
	Item get(int id);
	Boolean update(Item item);
}
