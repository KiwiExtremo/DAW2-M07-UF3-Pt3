package cat.institutmarianao.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cat.institutmarianao.ServiceTestContext;
import cat.institutmarianao.model.Item;
import cat.institutmarianao.repository.ItemRepository;

@ExtendWith(SpringExtension.class)

@ContextConfiguration(classes = { ServiceTestContext.class })
class ItemServiceTest {
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemService itemService;

	@Test
	void getItemShouldCallDaoWithSameReference() {
		/* Setup reference and item */
		Long reference = new Random().nextLong();
		Item item = mock(Item.class);

		/* When call dao with same reference, get the item */
		when(itemRepository.get(reference)).thenReturn(item);

		/* Test get item by reference */
		Item itemFromDb = itemService.get(reference);

		/* Verification */
		/* Service returns item user as dao */
		assertSame(item, itemFromDb);
		/* Dao was called once */
		verify(itemRepository, times(1)).get(reference);
	}

	@Test
	void getAllShouldCallDao() {
		/* Setup items */
		@SuppressWarnings("unchecked")
		List<Item> items = mock(List.class);

		/* When call dao, get all items */
		when(itemRepository.getAll()).thenReturn(items);

		/* Test get all items */
		List<Item> itemsFromDb = itemService.getAll();

		/* Verification */
		/* Service returns item user as dao */
		assertSame(items, itemsFromDb);
		/* Dao was called once */
		verify(itemRepository, times(1)).getAll();
	}

}
