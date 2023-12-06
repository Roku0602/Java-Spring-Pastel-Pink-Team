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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdUser;

    @Column(name = "Ten", length = 30)
    @Size(max = 30)
    private String Ten;

    @Column(name = "Email", length = 50)
    private String Email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "IdGoogle")
    private String IdGoogle;

    @ManyToMany(mappedBy = "fromUsers")
    private Set<Rooms> likedRooms;

}
