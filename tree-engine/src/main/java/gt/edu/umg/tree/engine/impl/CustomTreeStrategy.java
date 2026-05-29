package gt.edu.umg.tree.engine.impl;

import gt.edu.umg.tree.engine.TreeAlgorithmStrategy;
import java.util.ArrayList;
import java.util.List;

public class CustomTreeStrategy implements TreeAlgorithmStrategy<Object> {

    @Override
    public void createRoot(Object value) {
        // Tu lógica manual para inicializar la raíz del árbol
    }

    @Override
    public void addChild(String parentId, Object value) {
        // Tu lógica manual para insertar un hijo en el nodo correspondiente
    }

    @Override
    public List<Object> getDFS() {
        // Tu lógica manual para el recorrido en Profundidad (DFS)
        return new ArrayList<>();
    }

    @Override
    public List<Object> getBFS() {
        // Tu lógica manual para el recorrido en Amplitud (BFS)
        return new ArrayList<>();
    }

    @Override
    public int getHeight() {
        // Tu lógica manual para calcular la altura del árbol
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