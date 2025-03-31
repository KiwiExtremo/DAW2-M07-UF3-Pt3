package cat.institutmarianao.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import cat.institutmarianao.RepositoryTestContext;
import cat.institutmarianao.model.User;
import cat.institutmarianao.utils.Mock;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RepositoryTestContext.class })
@Transactional
class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;

	@Test
	void saveAndGetShouldSaveAndThenGet() {
		User client = Mock.createUser("SaveUserAndGetTest");

		assertNull(userRepository.get("SaveUserAndGetTest"));

		userRepository.save(client);

		User clientFromDB = userRepository.get("SaveUserAndGetTest");

		assertEquals(client, userRepository.get("SaveUserAndGetTest"));
		assertEquals(client, clientFromDB);
	}
}
