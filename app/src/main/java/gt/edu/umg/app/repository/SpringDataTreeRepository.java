package gt.edu.umg.app.repository;

import gt.edu.umg.app.model.TreeNode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(
        name = "app.storage",
        havingValue = "postgres"
)
public interface SpringDataTreeRepository
        extends JpaRepository<TreeNode, String> {

}