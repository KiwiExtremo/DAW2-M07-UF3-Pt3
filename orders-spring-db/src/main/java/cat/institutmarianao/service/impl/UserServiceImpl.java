package cat.institutmarianao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.institutmarianao.model.User;
import cat.institutmarianao.repository.UserRepository;
import cat.institutmarianao.service.UserService;
import jakarta.validation.constraints.NotBlank;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User get(@NotBlank String username) {
		return userRepository.get(username);
	}

}
