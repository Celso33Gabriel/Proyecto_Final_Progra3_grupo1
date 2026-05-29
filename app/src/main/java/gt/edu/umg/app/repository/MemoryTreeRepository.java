package gt.edu.umg.app.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryTreeRepository {

    // Usamos un Map para guardar los datos en la Memoria
    private final Map<String, Object> storage = new HashMap<>();
    private final CustomTreeStrategy customTree = new CustomTreeStrategy();

    public void save(String id, Object node) {
        // Guardamos en el mapa original de tus compañeros
        storage.put(id, node);
        
        // Ejecutamos tu lógica de árbol manual dentro del método
        try {
            int valorNumerico = Integer.parseInt(id);
            customTree.insert(valorNumerico);
        } catch (NumberFormatException e) {
            customTree.insert(node.hashCode());
        }
    } // <-- ESTA es la llave que cierra el método save() correctamente

    public Object findById(String id) {
        return storage.get(id);
    }

    public Map<String, Object> findAll() {
        return storage;
    }

    // =========================================================================
    // 🚀 LÓGICA COMPLETA DEL ÁRBOL QUE FALTA (LO QUE TE PIDIÓ TU AMIGO)
    // =========================================================================

    public void ejecutarRecorridoDFS() {
        System.out.println("--- Ejecutando DFS (Profundidad) en Memoria ---");
        customTree.dfs();
    }

    public void ejecutarRecorridoBFS() {
        System.out.println("--- Ejecutando BFS (Amplitud) en Memoria ---");
        customTree.bfs();
    }

    public int obtenerAlturaArbol() {
        return customTree.getAbsoluteHeight();
    }

    public int obtenerProfundidadNodo(int valor) {
        return customTree.getDepth(valor);
    }

    public void mostrarAncestros(int valor) {
        System.out.println("--- Ancestros de " + valor + " ---");
        customTree.printAncestors(valor);
    }

    public boolean verificarSiTieneCiclos() {
        return customTree.hasCycle();
    }
}