package pastelpink.project.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.Valid;
import lombok.Data;

@Data
@Entity
public class Room_detail {
    @EmbeddedId
    private Room_detailCompositeKey Id;

    @ManyToOne
    @MapsId("id_room")
    @JoinColumn(name = "id_room", referencedColumnName = "IdRoom")
    Rooms Room;

    @ManyToOne
    @MapsId("id_user")
    @JoinColumn(name = "id_user", referencedColumnName = "IdUser")
    User User;

    @Column(name = "NgayTao")
    @Valid()
    private LocalDate NgayTao;
}
