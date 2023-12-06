package pastelpink.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pastelpink.project.Entity.BattleHistoryDetail;
import pastelpink.project.Entity.BattleHistoryDetailCompositeKey;

@Repository
public interface BattleHistoryDetailRepository
        extends JpaRepository<BattleHistoryDetail, BattleHistoryDetailCompositeKey> {

}
