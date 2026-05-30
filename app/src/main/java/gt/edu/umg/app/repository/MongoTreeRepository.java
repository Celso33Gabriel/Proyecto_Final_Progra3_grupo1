package gt.edu.umg.app.repository;

import gt.edu.umg.app.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@ConditionalOnProperty(
        name = "app.storage",
        havingValue = "mongo"
)
public class MongoTreeRepository implements TreeRepository {

    @Autowired
    private MongoNodeRepository mongoRepository;

    @Override
    public void createRoot(String id, String value) {
        mongoRepository.save(new TreeNode(id, value, null));
    }

    @Override
    public void addChild(String parentId, String id, String value) {
        mongoRepository.save(new TreeNode(id, value, parentId));
    }

    @Override
    public List<TreeNode> getFullTree() {
        return mongoRepository.findAll();
    }

    @Override
    public TreeNode getSubtree(String nodeId) {
        return mongoRepository.findById(nodeId).orElse(null);
    }

    @Override
    public List<String> getPathFromRoot(String nodeId) {
        return new ArrayList<>();
    }

    @Override
    public List<TreeNode> getDFS() {
        return mongoRepository.findAll();
    }

    @Override
    public List<TreeNode> getBFS() {
        return mongoRepository.findAll();
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getDepth(String nodeId) {
        return 0;
    }

    @Override
    public List<TreeNode> getAncestors(String nodeId) {
        return new ArrayList<>();
    }

    @Override
    public boolean validateNoCycles() {
        return true;
    }
}