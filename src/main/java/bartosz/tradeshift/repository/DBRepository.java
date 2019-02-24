package bartosz.tradeshift.repository;

import bartosz.tradeshift.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBRepository extends JpaRepository<Node, Long> {

}
