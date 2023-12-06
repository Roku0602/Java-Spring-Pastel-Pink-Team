package pastelpink.project.Entity;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.Data;

@Data
@Entity
@Table(name = "BattleHistory")
public class BattleHistory {
    @Id
    private String IdHistory;

    @ManyToOne
    @JoinColumn(name = "IdTurn", referencedColumnName = "IdTurn")
    private TurnMovement IdTurn;

    @Column(name = "NgayThiDau")
    @Valid()
    private LocalDate NgayThiDau;

    @ManyToMany
    @JoinTable(name = "BattleHistoryDetail", joinColumns = @JoinColumn(name = "id_history"), inverseJoinColumns = @JoinColumn(name = "id_room"))
    private Set<Rooms> RoomData;
}
