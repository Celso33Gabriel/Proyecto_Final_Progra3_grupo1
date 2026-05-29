package gt.edu.umg.tree.engine.impl;

import gt.edu.umg.tree.engine.TreeAlgorithmStrategy;
import java.util.ArrayList;
import java.util.List;

public class CollectionsTreeStrategy implements TreeAlgorithmStrategy<Object> {

    @Override
    public void createRoot(Object value) {
        // Lógica para crear la raíz usando colecciones estándar
    }

    @Override
    public void addChild(String parentId, Object value) {
        // Lógica para añadir nodos hijos
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean validateNoCycles() {
		// TODO Auto-generated method stub
		return false;
	}
}