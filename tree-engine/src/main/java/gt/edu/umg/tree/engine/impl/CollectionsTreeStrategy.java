package gt.edu.umg.tree.engine.impl;

import gt.edu.umg.tree.engine.TreeAlgorithmStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(
        name = "app.tree-strategy",
        havingValue = "collections"
)
public class CollectionsTreeStrategy
        implements TreeAlgorithmStrategy<Object> {

    @Override
    public void createRoot(Object value) {

    }

    @Override
    public void addChild(String parentId, Object value) {

    }

    @Override
    public List<Object> getDFS() {
        return new ArrayList<>();
    }

    @Override
    public List<Object> getBFS() {
        return new ArrayList<>();
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
    public boolean validateNoCycles() {
        return true;
    }
}