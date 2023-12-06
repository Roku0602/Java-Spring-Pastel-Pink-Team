package pastelpink.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pastelpink.project.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
