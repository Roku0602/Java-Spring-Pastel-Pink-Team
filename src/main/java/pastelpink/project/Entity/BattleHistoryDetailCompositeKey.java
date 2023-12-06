package pastelpink.project.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class BattleHistoryDetailCompositeKey implements Serializable {
    @Column(name = "id_history")
    String id_history;

    @Column(name = "id_room")
    Integer id_room;
}
