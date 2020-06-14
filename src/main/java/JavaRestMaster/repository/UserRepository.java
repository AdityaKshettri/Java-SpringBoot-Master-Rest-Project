package JavaRestMaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import JavaRestMaster.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
	public User findByUsername(String username);
}
