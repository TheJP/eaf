package ch.fhnw.edu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.edu.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
