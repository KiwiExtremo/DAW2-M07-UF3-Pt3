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
import cat.institutmarianao.model.Order;
import cat.institutmarianao.model.User;
import cat.institutmarianao.repository.OrderRepository;

@ExtendWith(SpringExtension.class)

@ContextConfiguration(classes = { ServiceTestContext.class })
class OrderServiceTest {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderService orderService;

	@Test
	void getOrderShouldCallDaoWithSameReference() {
		Long reference = new Random().nextLong();
		Order order = mock(Order.class);

		when(orderRepository.get(reference)).thenReturn(order);

		Order orderFromDB = orderService.get(reference);

		assertSame(order, orderFromDB);

		verify(orderRepository, times(1)).get(reference);
	}

	@Test
	void getAllShouldCallDao() {
		@SuppressWarnings("unchecked")
		List<Order> orders = mock(List.class);

		when(orderRepository.getAll()).thenReturn(orders);

		List<Order> ordersFromDB = orderService.getAll();

		assertSame(orders, ordersFromDB);

		verify(orderRepository, times(1)).getAll();
	}

	@Test
	void findByUserShouldCallDaoWithSameUser() {
		@SuppressWarnings("unchecked")
		List<Order> orders = mock(List.class);
		User user = mock(User.class);

		when(orderRepository.findByUser(user)).thenReturn(orders);

		List<Order> ordersFromDB = orderService.findByUser(user);

		assertSame(orders, ordersFromDB);

		verify(orderRepository, times(1)).findByUser(user);
	}

	@Test
	void saveShouldCallDao() {
		Order order = mock(Order.class);

		orderService.save(order);

		verify(orderRepository, times(1)).save(order);
	}

	@Test
	void updateShouldCallDao() {
		Order order = mock(Order.class);
		Order order2 = mock(Order.class);

		when(orderRepository.update(order)).thenReturn(order2);

		Order updatedOrder = orderService.update(order);

		assertSame(order2, updatedOrder);

		verify(orderRepository, times(1)).update(order);
	}
}
