package pastelpink.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pastelpink.project.Entity.Rooms;


@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Integer> {
    @Query("SELECT r FROM Rooms r WHERE r.TenPhong = :roomname ORDER BY r.IdRoom DESC LIMIT 1")
    public Rooms getIdByRoomName(@Param("roomname") String roomname);
}
