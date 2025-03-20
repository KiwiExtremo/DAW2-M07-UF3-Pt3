package cat.institutmarianao;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cat.institutmarianao.repository.ItemRepository;
import cat.institutmarianao.repository.OrderRepository;
import cat.institutmarianao.repository.UserRepository;
import cat.institutmarianao.service.ItemService;
import cat.institutmarianao.service.OrderService;
import cat.institutmarianao.service.UserService;
import cat.institutmarianao.service.impl.ItemServiceImpl;
import cat.institutmarianao.service.impl.OrderServiceImpl;
import cat.institutmarianao.service.impl.UserServiceImpl;

@Configuration
public class ServiceTestContext {

	@Bean
	public ItemRepository itemRepository() {
		return Mockito.mock(ItemRepository.class);
	}

	@Bean
	public OrderRepository orderRepository() {
		return Mockito.mock(OrderRepository.class);
	}

	@Bean
	public UserRepository userRepository() {
		return Mockito.mock(UserRepository.class);
	}

	@Bean
	public ItemService itemService() {
		return new ItemServiceImpl();
	}

	@Bean
	public OrderService orderService() {
		return new OrderServiceImpl();
	}

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
}
