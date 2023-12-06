package pastelpink.project.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Room_detailCompositeKey implements Serializable {

    @Column(name = "id_room")
    Integer id_room;

    @Column(name = "id_user")
    Integer id_user;
}
