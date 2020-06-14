package JavaRestMaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import JavaRestMaster.entity.Basic;
import JavaRestMaster.entity.User;

@RepositoryRestResource(path = "basics")
public interface BasicRepository extends JpaRepository<Basic, Integer> 
{
	public List<Basic> findByUser(User user);
}
