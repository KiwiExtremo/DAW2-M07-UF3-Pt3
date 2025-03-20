package cat.institutmarianao.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cat.institutmarianao.ServiceTestContext;
import cat.institutmarianao.model.User;
import cat.institutmarianao.repository.UserRepository;
import cat.institutmarianao.utils.Mock;

@ExtendWith(SpringExtension.class)

@ContextConfiguration(classes = { ServiceTestContext.class })
class UserServiceTest {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Test
	void getUserShouldCallDaoWithSameUsername() {
		/* Setup username and user */
		String username = Mock.createRandomString(User.MAX_USERNAME);
		User user = mock(User.class);

		/* When call dao with same username, get the user */
		when(userRepository.get(username)).thenReturn(user);

		/* Test get user by username */
		User userFromDb = userService.get(username);

		/* Verification */
		/* Service returns same user as dao */
		assertSame(user, userFromDb);
		/* Dao was called once */
		verify(userRepository, times(1)).get(username);
	}
}
