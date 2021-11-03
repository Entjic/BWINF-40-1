package de.entjic.model;

public class Node extends Hotel {
    private Node predecessor;
    private int iterationDepth;
    private float inheritedRating;

    public Node(int time, float rating) {
        super(time, rating);
        inheritedRating = rating;
    }

    public Node getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

    public int getIterationDepth() {
        return iterationDepth;
    }

    public void setIterationDepth(int iterationDepth) {
        this.iterationDepth = iterationDepth;
    }

    public float getInheritedRating() {
        return inheritedRating;
    }

    public void setInheritedRating(float inheritedRating) {
        this.inheritedRating = inheritedRating;
    }

    @Override
    public String toString() {
        return "Node{" +
                "time=" + time +
                ", rating=" + rating +
                ", iterationDepth=" + iterationDepth +
                ", inheritedRating=" + inheritedRating +
                '}';
    }
}
