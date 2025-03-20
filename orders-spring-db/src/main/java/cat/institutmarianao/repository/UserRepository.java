package cat.institutmarianao.repository;

import cat.institutmarianao.model.User;

public interface UserRepository {
	User get(String username);

	void save(User user);
}
