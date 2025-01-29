package mg.tife.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.tife.entity.User;

public interface UserRepository  extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
}