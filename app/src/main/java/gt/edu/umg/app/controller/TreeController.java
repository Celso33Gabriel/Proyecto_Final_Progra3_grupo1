package gt.edu.umg.app.controller;

import gt.edu.umg.app.repository.TreeRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import gt.edu.umg.app.model.TreeNode;

@RestController
@RequestMapping("/tree")
public class TreeController {

    private final TreeRepository repository;

    public TreeController(TreeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TreeNode> getTree() {
        return repository.getFullTree();
    }

    @GetMapping("/traversal")
    public Object traversal(@RequestParam("type") String type) {
        if (type.equalsIgnoreCase("DFS")) {
            return repository.getDFS();
        }
        return repository.getBFS();
    }

    @GetMapping("/validate")
    public boolean validateTree() {
        return repository.validateNoCycles();
    }

    @GetMapping("/height")
    public int getHeight() {
        return repository.getHeight();
    }
}