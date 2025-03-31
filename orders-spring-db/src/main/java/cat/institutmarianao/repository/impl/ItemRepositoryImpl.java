package cat.institutmarianao.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cat.institutmarianao.model.Item;
import cat.institutmarianao.repository.ItemRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class ItemRepositoryImpl implements ItemRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Item get(Long reference) {
		return getSession().get(Item.class, reference);
	}

	@Override
	public List<Item> getAll() {
		CriteriaBuilder cb = getSession().getCriteriaBuilder();
		CriteriaQuery<Item> query = cb.createQuery(Item.class);

		Root<Item> itemRoot = query.from(Item.class);

		query.select(itemRoot);

		return getSession().createQuery(query).list();
	}

	@Override
	public void save(Item item) {
		getSession().persist(item);
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
