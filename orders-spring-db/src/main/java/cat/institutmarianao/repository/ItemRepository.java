package cat.institutmarianao.repository;

import java.util.List;

import cat.institutmarianao.model.Item;

public interface ItemRepository {
	Item get(Long reference);

	List<Item> getAll();

	void save(Item item);
}
