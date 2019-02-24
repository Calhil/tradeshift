package bartosz.tradeshift.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void getChildrenTest() {
        Node rootNode = new Node();
        rootNode.setRoot(rootNode);
        Node nodeA = new Node();
        Node nodeB = new Node();
        Node nodeC = new Node();
        rootNode.setChildren(Arrays.asList(nodeA, nodeB));
        nodeA.setChildren(Arrays.asList(nodeC));

        int numChildren = rootNode.getChildren().size();

        Assert.assertEquals(2, numChildren);
    }

    @Test
    void correctHeightWhenChangingParentNodes() {
        Node rootNode = new Node();
        rootNode.setRoot(rootNode);
        Node nodeA = new Node();
        Node nodeB = new Node();
        Node nodeC = new Node();
        rootNode.setChildren(Arrays.asList(nodeA, nodeB));
        nodeA.setChildren(Arrays.asList(nodeC));

        nodeA.setParent(nodeB);
        long heightA = nodeA.getHeight();
        long heightC = nodeC.getHeight();

        Assert.assertEquals(2, heightA);
        Assert.assertEquals(3, heightC);
    }

    @Test
    void correctHeightWhenAddingChildNodes() {
        Node rootNode = new Node();
        rootNode.setRoot(rootNode);
        Node nodeA = new Node();
        Node nodeB = new Node();
        Node nodeC = new Node();
        rootNode.setChildren(Arrays.asList(nodeA, nodeB));
        nodeA.setChildren(Arrays.asList(nodeC));

        long heightA = nodeA.getHeight();
        long heightC = nodeC.getHeight();

        Assert.assertEquals(1, heightA);
        Assert.assertEquals(2, heightC);
    }
}