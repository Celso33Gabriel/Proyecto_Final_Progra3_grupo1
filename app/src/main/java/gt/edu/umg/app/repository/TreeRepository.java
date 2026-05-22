package gt.edu.umg.app.repository;

import gt.edu.umg.app.model.TreeNode;
import java.util.List;

public interface TreeRepository {
    void createRoot(String id, String value);
    void addChild(String parentId, String id, String value);
    List<TreeNode> getFullTree();
    TreeNode getSubtree(String nodeId);
    List<String> getPathFromRoot(String nodeId);
    List<TreeNode> getDFS();
    List<TreeNode> getBFS();
    int getHeight();
    int getDepth(String nodeId);
    List<TreeNode> getAncestors(String nodeId);
    boolean validateNoCycles();
}