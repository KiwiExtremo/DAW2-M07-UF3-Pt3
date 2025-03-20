package cat.institutmarianao;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cat.institutmarianao.repository.ItemRepository;
import cat.institutmarianao.repository.OrderRepository;
import cat.institutmarianao.repository.UserRepository;
import cat.institutmarianao.repository.impl.ItemRepositoryImpl;
import cat.institutmarianao.repository.impl.OrderRepositoryImpl;
import cat.institutmarianao.repository.impl.UserRepositoryImpl;

/**
 * Provides the datasource configured in application-test.properties (a h2 one)
 * to hibernate (loaded thorught
 * {@link cat.institutmarianao.HibernateConfiguration}
 *
 * Used by DAO Tests
 *
 * @author Toni
 *
 */
@Configuration
@EnableTransactionManagement
public class RepositoryTestContext {

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return builder.setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean
	@Autowired
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("cat.institutmarianao.model");
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.hbm2ddl.auto", "create-drop");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.show_sql", "true");
		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

	@Bean
	public ItemRepository itemDao() {
		return new ItemRepositoryImpl();
	}

	@Bean
	public OrderRepository orderDao() {
		return new OrderRepositoryImpl();
	}

	@Bean
	public UserRepository userDao() {
		return new UserRepositoryImpl();
	}
}