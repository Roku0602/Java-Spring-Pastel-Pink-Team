package pastelpink.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pastelpink.project.Entity.BattleHistory;

@Repository
public interface BattleHistoryRepository extends JpaRepository<BattleHistory, String> {

}
