package pastelpink.project.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.Valid;
import lombok.Data;

@Data
@Entity
public class BattleHistoryDetail {

    @EmbeddedId
    private BattleHistoryDetailCompositeKey IdHistoryDetail;

    @ManyToOne
    @MapsId("id_history")
    @JoinColumn(name = "id_history")
    private BattleHistory History;

    @ManyToOne
    @MapsId("id_room")
    @JoinColumn(name = "id_room")
    private Rooms Room;

    @Column(name = "NgayThamGia")
    @Valid
    private LocalDate NgayThamGia;

    @ManyToOne
    @JoinColumn(name = "IdUser", referencedColumnName = "IdUser")
    private User IdUser;
}
