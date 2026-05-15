package gt.edu.umg.app.model;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private String id;
    private String value;
    private String parentId;
    private List<TreeNode> children = new ArrayList<>();

    public TreeNode() {}

    public TreeNode(String id, String value, String parentId) {
        this.id = id;
        this.value = value;
        this.parentId = parentId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getParentId() { return parentId; }
    public void setParentId(String parentId) { this.parentId = parentId; }

    public List<TreeNode> getChildren() { return children; }
    public void setChildren(List<TreeNode> children) { this.children = children; }

    public void addChild(TreeNode child) { this.children.add(child); }
}