package bartosz.tradeshift.controller;

import bartosz.tradeshift.model.Node;
import bartosz.tradeshift.service.NodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class NodeController {

    public static final Logger log = LoggerFactory.getLogger(NodeController.class);

    private final NodeService nodeService;

    @Autowired
    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping("/node/{nodeID}/children")
    public ResponseEntity<List<Node>> getChildrenNodes(@PathVariable long nodeID) {
        return ResponseEntity.ok(nodeService.getChildrenNodes(nodeID));
    }

    @PutMapping("/node/{nodeID}/updateParent/{newParentID}")
    public ResponseEntity<String> updateParent(@PathVariable long nodeID,
                             @PathVariable long newParentID) {
        nodeService.changeParent(nodeID, newParentID);
        return ResponseEntity.ok("Succesfully updated parent node");
    }
}
