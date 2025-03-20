package cat.institutmarianao.service.impl;

import java.util.List;

import cat.institutmarianao.model.Order;
import cat.institutmarianao.model.User;
import cat.institutmarianao.service.OrderService;
import jakarta.validation.constraints.NotNull;

//TODO Convert it in a service
public class OrderServiceImpl implements OrderService {

	@Override
	public Order get(@NotNull Long reference) {
		// TODO get order by id
		return null;
	}

	@Override
	public List<Order> getAll() {
		// TODO get all orders
		return null;
	}

	@Override
	public List<Order> findByUser(@NotNull User client) {
		// TODO find orders by client
		return null;
	}

	@Override
	public void save(@NotNull Order order) {
		// TODO save order
	}

	@Override
	public Order update(@NotNull Order order) {
		// TODO update order
		return null;
	}
}
