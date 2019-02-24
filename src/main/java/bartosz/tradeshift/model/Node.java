package bartosz.tradeshift.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "node")
@EqualsAndHashCode(of = {"id"})
public class Node {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent", referencedColumnName = "id")
    private Node parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Node> children;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "root", referencedColumnName = "id")
    @JsonIgnore
    private Node root;

    private long height;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getParentID() {
        return parent.getId();
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
        // set the parent reference for each child
        for (Node child : children) {
            setChildReference(child);
        }
    }

    public void addChild(Node child) {
        this.getChildren().add(child);
        setChildReference(child);
    }

    private void setChildReference(Node child) {
        child.setParent(this);
        child.setRoot(this.getRoot());
    }

    public void setParent(Node parent) {
        this.parent = parent;
        this.setHeight(parent.getHeight() + 1);
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
        if (getChildren() == null) {
            return;
        }
        for (Node child : getChildren()) {
            child.setHeight(height + 1);
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Long getRootID() {
        return this.getRoot().getId();
    }
}
