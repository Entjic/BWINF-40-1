package de.entjic;

import de.entjic.file.FileReader;
import de.entjic.model.Hotel;
import de.entjic.model.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader("hotels0");
        Hotel[] hotels = fileReader.getHotels();
        Main main = new Main();

        System.out.println("Graph generated at " + System.currentTimeMillis());
        List<Node> nodes = main.hotelsToNodes(hotels);
        Node start = new Node(0, 5.0f);
        Node end = new Node(fileReader.getTravelTime(), 5.0f);
        nodes.add(end);
        System.out.println(nodes.size());
        main.solveGreedy(nodes, start, end);
        System.out.println("solved");
        Node current = end;
        System.out.println(current);
        while (current.getPredecessor() != null) {
            current = current.getPredecessor();
            System.out.println(current);
        }
    }


    private void solveGreedy(List<Node> input, Node start, Node end) {
        int travelTime = end.time;
        List<Node> openList = new ArrayList<>();
        start.setIterationDepth(0);
        openList.add(start);
        while (! openList.isEmpty()) {
            Node current = openList.get(0);
            // System.out.println("current: " + current);
            if (current.getIterationDepth() > 4) {
                openList.remove(current);
                continue;
            }
            List<Node> children = getMathematicallyPossibleChildren(current, input, travelTime);
            //if(children.isEmpty()){
            // System.out.println("There are no mathematically possible children for " + current);
            //}
            for (Node child : children) {
                // System.out.println(child);
                if (child.getInheritedRating() >= current.getInheritedRating()) {
                    child.setInheritedRating(current.getInheritedRating());
                }
                child.setPredecessor(current);
                child.setIterationDepth(current.getIterationDepth() + 1);
                openList.add(child);
                // System.out.println(child + " an " + current);
                if (child.equals(end)) {
                    return;
                }
                openList.sort(Comparator.comparing(Node::getInheritedRating).reversed());
            }

            openList.remove(current);
            // System.out.println("openList: " + openList);
        }
    }

    private List<Node> getMathematicallyPossibleChildren(Node current, List<Node> input, int travelTime) {
        List<Node> children = getPossibleChildren(current, input);
        if (children.isEmpty()) {
            // System.out.println("No possible children for " + current);
        }
        List<Node> mathematicallyPossibleChildren = new ArrayList<>();
        for (Node child : children) {
            // System.out.println("Time: " + child.time + " Iteration depth: " + (current.getIterationDepth() + 1) + " Distanz zum Ziel: " + (travelTime - child.time) + " Maximalstrecke: " + ((5 - (current.getIterationDepth() + 1)) * 360));
            if (isMathematicallyPossible(child, travelTime, current.getIterationDepth() + 1)) {
                mathematicallyPossibleChildren.add(child);
            }
        }
        return mathematicallyPossibleChildren;
    }

    private boolean isMathematicallyPossible(Node node, int travelTime, int iterationDepth) {
        return ((travelTime - node.time) - (5 - iterationDepth) * 360) <= 0;
    }

    private List<Node> getPossibleChildren(Node current, List<Node> input) {
        List<Node> possibleChildren = new ArrayList<>();
        for (Node node : input) {
            int timeDifference = node.time - current.time;
            if (timeDifference > 0 && timeDifference <= 360) {
                possibleChildren.add(node);
            }
        }
        return possibleChildren;
    }


    private List<Node> hotelsToNodes(Hotel[] hotels) {
        //System.out.println(Arrays.toString(hotels));
        List<Node> nodes = new ArrayList<>();
        for (Hotel hotel : hotels) {
            Node node = new Node(hotel.time, hotel.rating);
            nodes.add(node);
        }
        return nodes;
    }


}
