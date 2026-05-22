package gt.edu.umg.app.controller;

import gt.edu.umg.app.repository.TreeRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nodes")
public class NodeController {

    private final TreeRepository repository;

    public NodeController(TreeRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/root")
    public void createRoot(@RequestParam("id") String id,
                           @RequestParam("value") String value) {

        repository.createRoot(id, value);
    }

    @PostMapping("/{parentId}/children")
    public void addChild(@PathVariable String parentId,
                         @RequestParam("id") String id,
                         @RequestParam("value") String value) {

        repository.addChild(parentId, id, value);
    }

    @GetMapping("/{nodeId}/path")
    public Object getPath(@PathVariable String nodeId) {
        return repository.getPathFromRoot(nodeId);
    }

    @GetMapping("/{nodeId}/depth")
    public int getDepth(@PathVariable String nodeId) {
        return repository.getDepth(nodeId);
    }

    @GetMapping("/{nodeId}/ancestors")
    public Object getAncestors(@PathVariable String nodeId) {
        return repository.getAncestors(nodeId);
    }

    @GetMapping("/{nodeId}/subtree")
    public Object getSubtree(@PathVariable String nodeId) {
        return repository.getSubtree(nodeId);
    }
}