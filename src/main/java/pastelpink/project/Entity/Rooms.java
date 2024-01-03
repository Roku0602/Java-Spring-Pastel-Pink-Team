package pastelpink.project.Entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "rooms")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdRoom;

    @Column(name = "Ten", length = 30)
    @Size(max = 30)
    private String TenPhong;

    @Column(name = "room_password", nullable = true)
    private String RoomPassword;

    @ManyToMany
    @JoinTable(name = "Room_detail", joinColumns = @JoinColumn(name = "id_room"), inverseJoinColumns = @JoinColumn(name = "id_user"))
    private Set<User> fromUsers;

    @ManyToMany(mappedBy = "RoomData")
    private Set<BattleHistory> Histories;
}
