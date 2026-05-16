package gt.edu.umg.app.repository;

import java.util.HashMap;
import java.util.Map;


public class MemoryTreeRepository {
    // Usamos un Map para guardar los datos en la Memoria
    private final Map<String, Object> storage = new HashMap<>();

    public void save(String id, Object node) {
        storage.put(id, node);
    }

    public Object findById(String id) {
        return storage.get(id);
    }

    public Map<String, Object> findAll() {
        return storage;
    }
}