package bartosz.tradeshift.service;

import bartosz.tradeshift.model.Node;

import java.util.List;

public interface NodeService {

    /**
     * Changes the parentID of a node with specified nodeID
     * Also updates the height of subnodes
     *
     * @param nodeID
     * @param newParentID
     */
    void changeParent(long nodeID, long newParentID);

    /**
     * Fetches the children nodes of a node specified by nodeID
     *
     * @param nodeID
     * @return list of child nodes
     */
    List<Node> getChildrenNodes(long nodeID);
}
