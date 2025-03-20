package cat.institutmarianao.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cat.institutmarianao.model.Order;
import cat.institutmarianao.model.User;
import cat.institutmarianao.repository.OrderRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class OrderRepositoryImpl implements OrderRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Order get(Long reference) {
		// TODO get order by reference
		return null;
	}

	@Override
	public List<Order> getAll() {
		// TODO get all orders
		return null;
	}

	@Override
	public List<Order> findByUser(User client) {
		// TODO find order by client
		return null;
	}

	@Override
	public void save(Order order) {
		// TODO save order
	}

	@Override
	public Order update(Order order) {
		// TODO update order
		return null;
	}
}
