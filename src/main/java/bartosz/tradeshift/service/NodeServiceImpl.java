package bartosz.tradeshift.service;


import bartosz.tradeshift.exception.NodeNotFoundException;
import bartosz.tradeshift.model.Node;
import bartosz.tradeshift.repository.DBRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class NodeServiceImpl implements NodeService {

    public static final Logger log = LoggerFactory.getLogger(NodeServiceImpl.class);

    private final DBRepository dbRepository;

    @Autowired
    public NodeServiceImpl(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    private void validate(long nodeID) {
        if (!dbRepository.existsById(nodeID)){
            throw new NodeNotFoundException("Node not found. ID = " + nodeID);
        }
    }

    @Override
    public void changeParent(long nodeID, long newParentID) {
        validate(nodeID);
        validate(newParentID);

        Node parentNode = dbRepository.getOne(newParentID);
        dbRepository.findById(nodeID)
                .map(node -> {
                            node.setParent(parentNode);
                            return dbRepository.save(node);
                        }
                )
                .orElseThrow(() -> new NodeNotFoundException("Node not found. ID = " + nodeID));
    }

    @Override
    public List<Node> getChildrenNodes(long nodeID) {
        validate(nodeID);
        return dbRepository.findById(nodeID).
                orElseThrow(() -> new NodeNotFoundException("Node not found. ID = " + nodeID))
                .getChildren();

    }
}
