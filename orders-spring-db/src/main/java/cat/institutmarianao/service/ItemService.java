package cat.institutmarianao.service;

import java.util.List;

import cat.institutmarianao.model.Item;
import jakarta.validation.constraints.NotNull;

public interface ItemService {

	Item get(@NotNull Long reference);

	List<Item> getAll();

}
