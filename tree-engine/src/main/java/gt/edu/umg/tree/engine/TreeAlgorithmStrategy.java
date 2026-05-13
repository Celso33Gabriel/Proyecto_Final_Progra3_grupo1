package gt.edu.umg.tree.engine;

import java.util.List;

public interface TreeAlgorithmStrategy<T> {
    void createRoot(T value);
    void addChild(String parentId, T value);
    List<T> getDFS();
    List<T> getBFS();
    int getHeight(); 
    int getDepth(String nodeId);
    boolean validateNoCycles();
}