package cat.institutmarianao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.institutmarianao.model.Order;
import cat.institutmarianao.model.User;
import cat.institutmarianao.repository.OrderRepository;
import cat.institutmarianao.service.OrderService;
import jakarta.validation.constraints.NotNull;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order get(@NotNull Long reference) {
		return orderRepository.get(reference);
	}

	@Override
	public List<Order> getAll() {
		return orderRepository.getAll();
	}

	@Override
	public List<Order> findByUser(@NotNull User client) {
		return orderRepository.findByUser(client);
	}

	@Override
	public void save(@NotNull Order order) {
		orderRepository.save(order);
	}

	@Override
	public Order update(@NotNull Order order) {
		return orderRepository.update(order);
	}
}
