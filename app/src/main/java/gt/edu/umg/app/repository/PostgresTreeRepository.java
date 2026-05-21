package gt.edu.umg.app.repository;

import gt.edu.umg.app.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PostgresTreeRepository implements TreeRepository {

    @Autowired
    private SpringDataTreeRepository jpaRepository;

    @Override
    public void createRoot(String id, String value) {
        TreeNode root = new TreeNode(id, value, null);
        jpaRepository.save(root);
    }

    @Override
    public void addChild(String parentId, String id, String value) {
        TreeNode child = new TreeNode(id, value, parentId);
        jpaRepository.save(child);
    }

    @Override
    public TreeNode getFullTree() {
        List<TreeNode> all = jpaRepository.findAll();
        return buildTree(all);
    }

    @Override
    public TreeNode getSubtree(String nodeId) {
        // Obtenemos todos los nodos usando JPA
        List<TreeNode> allNodes = jpaRepository.findAll();
        Map<String, TreeNode> map = new HashMap<>();
        for (TreeNode n : allNodes) map.put(n.getId(), n);

        // Buscamos el nodo que pidieron como raíz del subárbol
        TreeNode rootOfSubtree = map.get(nodeId);
        if (rootOfSubtree == null) return null;

        // Limpiamos los hijos viejos para reconstruir solo las ramas que pertenecen a este subárbol
        for (TreeNode n : allNodes) {
            n.getChildren().clear();
        }

        // Reconstruimos las relaciones de hijos solo para este subárbol
        for (TreeNode n : allNodes) {
            if (n.getParentId() != null) {
                TreeNode parent = map.get(n.getParentId());
                if (parent != null) {
                    parent.addChild(n);
                }
            }
        }
        return rootOfSubtree;
    }

    @Override
    public List<String> getPathFromRoot(String nodeId) {
        List<TreeNode> allNodes = jpaRepository.findAll();
        Map<String, TreeNode> map = new HashMap<>();
        for (TreeNode n : allNodes) map.put(n.getId(), n);

        List<String> path = new ArrayList<>();
        TreeNode current = map.get(nodeId);
        
        while (current != null) {
            path.add(0, current.getValue()); // Lo inserta al inicio para mantener el orden correcto
            current = (current.getParentId() != null) ? map.get(current.getParentId()) : null;
        }
        return path;
    }

    @Override
    public int getDepth(String nodeId) {
        return getAncestors(nodeId).size();
    }

    @Override
    public List<TreeNode> getAncestors(String nodeId) {
        List<TreeNode> allNodes = jpaRepository.findAll();
        Map<String, TreeNode> map = new HashMap<>();
        for (TreeNode n : allNodes) map.put(n.getId(), n);

        List<TreeNode> ancestors = new ArrayList<>();
        TreeNode current = map.get(nodeId);
        
        if (current != null && current.getParentId() != null) {
            TreeNode parent = map.get(current.getParentId());
            while (parent != null) {
                ancestors.add(parent);
                parent = (parent.getParentId() != null) ? map.get(parent.getParentId()) : null;
            }
        }
        return ancestors;
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
        List<TreeNode> all = jpaRepository.findAll();
        Set<String> visited = new HashSet<>();
        for (TreeNode node : all) {
            if (!visited.contains(node.getId())) {
                if (hasCycle(node.getId(), visited, new HashSet<>(), all))
                    return false;
            }
        }
        return true;
    }

    // ========== Métodos auxiliares privados organizados en memoria ==========

    private TreeNode buildTree(List<TreeNode> nodes) {
        Map<String, TreeNode> map = new HashMap<>();
        for (TreeNode n : nodes) {
            map.put(n.getId(), n);
            n.getChildren().clear(); // Limpiamos listas viejas de memoria antes de enlazar
        }
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