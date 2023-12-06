package pastelpink.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pastelpink.project.Entity.Room_detail;
import pastelpink.project.Entity.Room_detailCompositeKey;

@Repository
public interface Room_detailRepository extends JpaRepository<Room_detail, Room_detailCompositeKey> {

}
