package cat.institutmarianao.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import cat.institutmarianao.RepositoryTestContext;
import cat.institutmarianao.model.Order;
import cat.institutmarianao.model.Order.Status;
import cat.institutmarianao.model.User;
import cat.institutmarianao.utils.Mock;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RepositoryTestContext.class })
@Transactional
class OrderRepositoryTest {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	void saveAndGetShouldSaveAndThenGet() {
		User client = Mock.createUser("SaveAndGetTest");
		userRepository.save(client);

		Order order = Mock.createOrder(client);

		assertNull(order.getReference());
		assertEquals(0, orderRepository.getAll().size());

		orderRepository.save(order);

		Order orderFromDB = orderRepository.get(order.getReference());

		assertNotNull(orderFromDB);
		assertEquals(order, orderFromDB);
	}

	@Test
	void findAllShouldReturnAllItems() {
		User client = Mock.createUser("findAllTest");
		userRepository.save(client);

		List<Order> orders = Arrays.asList(Mock.createOrder(client), Mock.createOrder(client), Mock.createOrder(client),
				Mock.createOrder(client));

		for (Order order : orders) {
			assertNull(order.getReference());
		}

		assertEquals(0, orderRepository.getAll().size());

		for (Order order : orders) {
			orderRepository.save(order);
		}

		List<Order> ordersFromDB = orderRepository.getAll();

		assertEquals(orders.size(), ordersFromDB.size());
		assertTrue(orders.containsAll(ordersFromDB));
	}

	@Test
	void findByUsernameShouldReturnAllUsernameItems() {
		User client = Mock.createUser("findByUserTest");
		User client2 = Mock.createUser("findByUserTest2");
		userRepository.save(client);
		userRepository.save(client2);

		// 4 orders from client, and 2 orders from client2
		List<Order> orders = Arrays.asList(Mock.createOrder(client), Mock.createOrder(client), Mock.createOrder(client),
				Mock.createOrder(client), Mock.createOrder(client2), Mock.createOrder(client2));

		for (Order order : orders) {
			assertNull(order.getReference());
		}

		assertEquals(0, orderRepository.getAll().size());

		for (Order order : orders) {
			orderRepository.save(order);
		}

		List<Order> ordersFromUserFromDB = orderRepository.findByUser(client);

		assertEquals(4, ordersFromUserFromDB.size());
		assertTrue(orders.containsAll(ordersFromUserFromDB));
	}

	@Test
	void findByUsernameNullShouldReturnNull() {
		User client = Mock.createUser("findByUserTest");
		User client2 = Mock.createUser("findByUserTest2");
		userRepository.save(client);

		// 4 orders from client, and 2 orders from client2
		List<Order> orders = Arrays.asList(Mock.createOrder(client), Mock.createOrder(client), Mock.createOrder(client),
				Mock.createOrder(client));

		for (Order order : orders) {
			assertNull(order.getReference());
		}

		assertEquals(0, orderRepository.getAll().size());

		for (Order order : orders) {
			orderRepository.save(order);
		}

		assertNull(userRepository.get(client2.getUsername()));

		List<Order> ordersFromUserFromDB = orderRepository.findByUser(client2);

		assertEquals(0, ordersFromUserFromDB.size());
	}

	@Test
	void updateShouldSaveChanges() {
		User client = Mock.createUser("updateTest");
		userRepository.save(client);

		Order order = Mock.createOrder(client);

		assertNull(order.getReference());
		assertEquals(0, orderRepository.getAll().size());

		orderRepository.save(order);

		order.setStatus(Status.TRANSIT);

		Order orderFromDB = orderRepository.update(order);

		assertEquals(order, orderFromDB);
	}

}
