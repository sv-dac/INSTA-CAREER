package com.icareer.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.icareer.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	List<User> findByuName(String uName);

	Optional<User> findById(String id);
}