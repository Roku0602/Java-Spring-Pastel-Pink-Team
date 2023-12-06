package pastelpink.project.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "TurnMovement")
public class TurnMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdTurn;

    @Column(name = "TurnDetail", length = 30)
    @Size(max = 30)
    private String TurnDetail;
}
