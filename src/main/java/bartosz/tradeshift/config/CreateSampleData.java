package bartosz.tradeshift.config;

import bartosz.tradeshift.model.Node;
import bartosz.tradeshift.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
class CreateSampleData {

    private final DBRepository dbRepository;

    @Autowired
    public CreateSampleData(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadDataIntoDB() {
        Node rootNode = new Node();
        rootNode.setRoot(rootNode);
        Node nodeA = new Node();
        Node nodeB = new Node();
        Node nodeC = new Node();

        rootNode.setChildren(Arrays.asList(nodeA, nodeB));
        nodeA.setChildren(Arrays.asList(nodeC));

        dbRepository.save(rootNode);
    }
}
