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
		return getSession().get(Order.class, reference);
	}

	@Override
	public List<Order> getAll() {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Order> query = cb.createQuery(Order.class);

		Root<Order> orderRoot = query.from(Order.class);

		query.select(orderRoot);

		return getSession().createQuery(query).list();
	}

	@Override
	public List<Order> findByUser(User client) {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Order> query = cb.createQuery(Order.class);

		Root<Order> orderRoot = query.from(Order.class);

		query.select(orderRoot).where(cb.equal(orderRoot.get("client"), client));

		return getSession().createQuery(query).list();

	}

	@Override
	public void save(Order order) {
		getSession().persist(order);
	}

	@Override
	public Order update(Order order) {
		return getSession().merge(order);
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
