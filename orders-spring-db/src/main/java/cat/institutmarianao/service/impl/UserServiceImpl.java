package cat.institutmarianao.service.impl;

import cat.institutmarianao.model.User;
import cat.institutmarianao.service.UserService;
import jakarta.validation.constraints.NotBlank;

//TODO Convert it in a service
public class UserServiceImpl implements UserService {
	// TODO Inject repository

	@Override
	public User get(@NotBlank String username) {
		// TODO get user by username
		return null;
	}

}
