package com.icareer.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.icareer.entity.User;
import com.icareer.entity.UserRequest;
import com.icareer.repository.UserRepository;
import com.icareer.repository.UserRequestRepository;

@Service
public class AdminService {

	private final UserRepository userRepository;

	private final UserRequestRepository userRequestRepository;

	public AdminService(UserRepository userRepository, UserRequestRepository userRequestRepository) {
		this.userRepository = userRepository;
		this.userRequestRepository = userRequestRepository;
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User addUsers(User user) {
		if (user == null || user.getId() == null || user.getName() == null) {
			throw new IllegalArgumentException("User ID and Name cannot be null");
		}

		if (userRepository.existsById(user.getId())) {
			throw new IllegalArgumentException("User with ID " + user.getId() + " already exists");
		}
		return userRepository.save(user);
	}

	public List<UserRequest> getUserHistory(String userId) {
		if (userId == null || userId.isEmpty()) {
			throw new IllegalArgumentException("User ID cannot be null or empty");
		}
		if (!userRepository.existsById(UUID.fromString(userId))) {
			throw new IllegalArgumentException("User with ID " + userId + " does not exist");
		}
//		return userRepository.findUserRequestsById(UUID.fromString(userId));
		return userRequestRepository.findUserRequestsByUser_Id(UUID.fromString(userId));
	}

	public Boolean deleteUser(String userId) {
		if (userId == null || userId.isEmpty()) {
			throw new IllegalArgumentException("User ID cannot be null or empty");
		}
		if (!userRepository.existsById(UUID.fromString(userId))) {
			throw new IllegalArgumentException("User with ID " + userId + " does not exist");
		}
		userRepository.deleteById(UUID.fromString(userId));
		return true;
	}

	public Boolean deleteUserHistory(String historyId) {
		if (historyId == null || historyId.isEmpty()) {
			throw new IllegalArgumentException("History ID cannot be null or empty");
		}
		if (!userRequestRepository.existsById(UUID.fromString(historyId))) {
			throw new IllegalArgumentException("History with ID " + historyId + " does not exist");
		}
		userRequestRepository.deleteById(UUID.fromString(historyId));
		return true;
	}

}
