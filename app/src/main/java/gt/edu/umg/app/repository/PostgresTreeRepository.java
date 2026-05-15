package gt.edu.umg.app.repository;

import gt.edu.umg.app.model.TreeNode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.*;

public class PostgresTreeRepository implements TreeRepository {

    private final JdbcTemplate jdbc;

    public PostgresTreeRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<TreeNode> nodeMapper = (rs, rowNum) ->
            new TreeNode(
                    rs.getString("id"),
                    rs.getString("value"),
                    rs.getString("parent_id")
            );

    @Override
    public void createRoot(String id, String value) {
        jdbc.update(
                "INSERT INTO nodes(id, value, parent_id) VALUES (?, ?, NULL)",
                id, value
        );
    }

    @Override
    public void addChild(String parentId, String id, String value) {
        jdbc.update(
                "INSERT INTO nodes(id, value, parent_id) VALUES (?, ?, ?)",
                id, value, parentId
        );
    }

    @Override
    public TreeNode getFullTree() {
        List<TreeNode> all = jdbc.query("SELECT * FROM nodes", nodeMapper);
        return buildTree(all);
    }

    @Override
    public TreeNode getSubtree(String nodeId) {
        String sql = """
            WITH RECURSIVE subtree AS (
                SELECT * FROM nodes WHERE id = ?
                UNION ALL
                SELECT n.* FROM nodes n
                INNER JOIN subtree s ON n.parent_id = s.id
            )
            SELECT * FROM subtree
            """;
        List<TreeNode> nodes = jdbc.query(sql, nodeMapper, nodeId);
        return buildTree(nodes);
    }

    @Override
    public List<String> getPathFromRoot(String nodeId) {
        String sql = """
            WITH RECURSIVE path AS (
                SELECT id, value, parent_id FROM nodes WHERE id = ?
                UNION ALL
                SELECT n.id, n.value, n.parent_id FROM nodes n
                INNER JOIN path p ON n.id = p.parent_id
            )
            SELECT * FROM path
            """;
        List<TreeNode> nodes = jdbc.query(sql, nodeMapper, nodeId);
        List<String> path = new ArrayList<>();
        for (int i = nodes.size() - 1; i >= 0; i--) {
            path.add(nodes.get(i).getValue());
        }
        return path;
    }

    @Override
    public int getDepth(String nodeId) {
        return getAncestors(nodeId).size();
    }

    @Override
    public List<TreeNode> getAncestors(String nodeId) {
        String sql = """
            WITH RECURSIVE ancestors AS (
                SELECT n.* FROM nodes n
                INNER JOIN nodes child ON child.parent_id = n.id
                AND child.id = ?
                UNION ALL
                SELECT n.* FROM nodes n
                INNER JOIN ancestors a ON n.id = a.parent_id
            )
            SELECT * FROM ancestors
            """;
        return jdbc.query(sql, nodeMapper, nodeId);
    }

    @Override
    public List<TreeNode> getDFS() {
        TreeNode root = getFullTree();
        List<TreeNode> result = new ArrayList<>();
        if (root != null) dfsHelper(root, result);
        return result;
    }

    @Override
    public List<TreeNode> getBFS() {
        TreeNode root = getFullTree();
        List<TreeNode> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            result.add(current);
            queue.addAll(current.getChildren());
        }
        return result;
    }

    @Override
    public int getHeight() {
        TreeNode root = getFullTree();
        return root == null ? 0 : heightHelper(root);
    }

    @Override
    public boolean validateNoCycles() {
        List<TreeNode> all = jdbc.query("SELECT * FROM nodes", nodeMapper);
        Set<String> visited = new HashSet<>();
        for (TreeNode node : all) {
            if (!visited.contains(node.getId())) {
                if (hasCycle(node.getId(), visited, new HashSet<>(), all))
                    return false;
            }
        }
        return true;
    }

    //  Métodos auxiliares privados

    private TreeNode buildTree(List<TreeNode> nodes) {
        Map<String, TreeNode> map = new HashMap<>();
        for (TreeNode n : nodes) map.put(n.getId(), n);
        TreeNode root = null;
        for (TreeNode n : nodes) {
            if (n.getParentId() == null) {
                root = n;
            } else {
                TreeNode parent = map.get(n.getParentId());
                if (parent != null) parent.addChild(n);
            }
        }
        return root;
    }

    private void dfsHelper(TreeNode node, List<TreeNode> result) {
        result.add(node);
        for (TreeNode child : node.getChildren()) dfsHelper(child, result);
    }

    private int heightHelper(TreeNode node) {
        if (node.getChildren().isEmpty()) return 0;
        int max = 0;
        for (TreeNode child : node.getChildren())
            max = Math.max(max, heightHelper(child));
        return max + 1;
    }

    private boolean hasCycle(String id, Set<String> visited,
                             Set<String> inStack, List<TreeNode> all) {
        visited.add(id);
        inStack.add(id);
        for (TreeNode n : all) {
            if (id.equals(n.getParentId())) {
                if (!visited.contains(n.getId()) &&
                        hasCycle(n.getId(), visited, inStack, all)) return true;
                else if (inStack.contains(n.getId())) return true;
            }
        }
        inStack.remove(id);
        return false;
    }
}