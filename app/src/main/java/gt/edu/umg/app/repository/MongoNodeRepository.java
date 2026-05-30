package gt.edu.umg.app.repository;

import gt.edu.umg.app.model.TreeNode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@ConditionalOnProperty(
        name = "app.storage",
        havingValue = "mongo"
)
public interface MongoNodeRepository
        extends MongoRepository<TreeNode, String> {
}