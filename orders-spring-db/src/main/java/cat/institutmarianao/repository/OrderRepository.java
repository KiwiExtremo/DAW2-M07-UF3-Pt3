package cat.institutmarianao.repository;

import java.util.List;

import cat.institutmarianao.model.Order;
import cat.institutmarianao.model.User;

public interface OrderRepository {
	Order get(Long reference);

	List<Order> getAll();

	List<Order> findByUser(User client);

	void save(Order order);

	Order update(Order order);
}
