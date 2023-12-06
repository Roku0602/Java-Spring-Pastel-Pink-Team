package pastelpink.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pastelpink.project.Entity.Rooms;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Integer> {

}
