package gt.edu.umg.app.repository;

import gt.edu.umg.app.model.TreeNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataTreeRepository extends JpaRepository<TreeNode, String> {
    // JpaRepository ya incluye por defecto los métodos .save(), .findAll(), .delete(), etc.
}