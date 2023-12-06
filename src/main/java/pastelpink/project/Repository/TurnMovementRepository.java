package pastelpink.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pastelpink.project.Entity.TurnMovement;

@Repository
public interface TurnMovementRepository extends JpaRepository<TurnMovement, Integer> {

}
