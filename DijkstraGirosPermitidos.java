package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraGirosPermitidos {

	  private final List<Intersection> nodes;
	    private final List<Segment> edges;
	    private Segment lastSegment;
	    private Segment lastSegmentAux;
	    private Set<Intersection> settledNodes;
	    private Set<Intersection> unSettledNodes;
	    private Map<Intersection, Intersection> predecessors;
	    private Map<Intersection, Double> distance;
	    private Intersection source;

	    public DijkstraGirosPermitidos(MultiGraphRoadSim graph) {
	        // create a copy of the array so that we can operate on this array
	        this.nodes = new ArrayList<Intersection>(graph.getNodes());
	        this.edges = new ArrayList<Segment>(graph.getEdges());
	    }

	    public void execute(Intersection source) {
	    	this.source = source;
	        this.settledNodes = new HashSet<Intersection>();
	        this.unSettledNodes = new HashSet<Intersection>();
	        this.lastSegment = null;
	        this.distance = new HashMap<Intersection, Double>();
	        this.predecessors = new HashMap<Intersection, Intersection>();
	        distance.put(source, 0.0);
	        unSettledNodes.add(source);
	        while (unSettledNodes.size() > 0) {
	            Intersection node = getMinimum(unSettledNodes);
	            settledNodes.add(node);
	            unSettledNodes.remove(node);
	            findMinimalDistances(node);
	            this.lastSegment = this.lastSegmentAux;
                System.out.println("Tenemos otro nodo puesto y el último segmento es " + this.lastSegment.getIdSegment());
	        }
	    }

	    private void findMinimalDistances(Intersection node) {
	    	System.out.println("FindMinimalDistance de " + node.toString());
	        List<Intersection> adjacentNodes = getNeighbors(node);
	        for (Intersection target : adjacentNodes) {
	        	System.out.println("ShortestDistanceDe " + target.getId() + " : " + getShortestDistance(target));
	        	System.out.println("ShortestDistanceDe " + node.getId() + " : " + getShortestDistance(node));
	        	System.out.println("ShortestDistanceDe " + node.getId() + " A " + target.getId() + " : " + getDistance(node,target));
	            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
	                distance.put(target, getShortestDistance(node) + getDistance(node, target));
	                predecessors.put(target, node);
	                unSettledNodes.add(target);
	            }
	        }

	    }

	    private double getDistance(Intersection node, Intersection target) {
	    	double minDistance = Double.MAX_VALUE;
	    	ArrayList<Segment> neighborsSegments = new ArrayList<Segment>();
	    	if(node.getId().compareTo(this.source.getId()) == 0){
	    		neighborsSegments = (ArrayList<Segment>) node.getSegmentOut();
	    	}else{
	    		neighborsSegments = (ArrayList<Segment>) node.getAllowedSegments(lastSegment);
	    	}
	    	System.out.println("getDistance::Vecinos -- " + neighborsSegments);
	        for (Segment edge : neighborsSegments) {
	            System.out.println("Source: " + node + " EdgeSource: " + edge.getSource());
	            System.out.println("Target: " + target + " EdgeTarget: " + edge.getDestination());
	            System.out.println("Peso a minimizar: " + minDistance + " EdgeDistance: " + edge.getWeight());
	        	if (edge.getSource().equals(node) && edge.getDestination().equals(target) && edge.getWeight() < minDistance) {
	            	System.out.println("Segment Vecino: " + edge.getIdSegment() + " de " + node.getId() + " a " + target.getId());
	            	this.lastSegmentAux = edge;
	            	System.out.println("lastSegment: " + this.lastSegment);
	            	minDistance = edge.getWeight();
	                
	            } else {
	            	//System.out.println("No cumple los requisitos el seg " + edge.getIdSegment());
	            }
	        }
	        return minDistance;
	    }

	    private List<Intersection> getNeighbors(Intersection node) {
	        List<Intersection> neighbors = new ArrayList<Intersection>();
	        List<Segment> candidates = new ArrayList<Segment>();
	        System.out.println("/////////// Get Neighbors of " + node.toString());
	        if(node.getId().compareTo(this.source.getId()) == 0){
	        	candidates = node.getSegmentOut();
	        }else{
	        	System.out.println("++No es el primero. Antecesor " + lastSegment.getIdSegment());
	        	for(Segment s : node.getAllowedSegments(lastSegment)){
	        		candidates.add(s);
	        	} 
	        }
	        
	        for (Segment edge : candidates) {
	            if (!isSettled(edge.getDestination())) {
	            	System.out.println("Dijk::getNeighbors " + edge.getDestination());
	                neighbors.add(edge.getDestination());
	            }
	        }
	        return neighbors;
	    }

	    private Intersection getMinimum(Set<Intersection> Nodes) {
	        Intersection minimum = null;
	        for (Intersection Node : Nodes) {
	            if (minimum == null) {
	                minimum = Node;
	            } else {
	                if (getShortestDistance(Node) < getShortestDistance(minimum)) {
	                    minimum = Node;
	                }
	            }
	        }
	        return minimum;
	    }

	    private boolean isSettled(Intersection Node) {
	        return settledNodes.contains(Node);
	    }

	    private double getShortestDistance(Intersection destination) {
	    	Double d = distance.get(destination);
	        if (d == null) {
	            return Double.MAX_VALUE;
	        } else {
	            return d;
	        }
	    }

	    /*
	     * This method returns the path from the source to the selected target and
	     * NULL if no path exists
	     */
	    public LinkedList<Intersection> getPath(Intersection target) {
	        System.out.println("GetPath");
	    	LinkedList<Intersection> path = new LinkedList<Intersection>();
	        Intersection step = target;
	        // check if a path exists
	        if (predecessors.get(step) == null) {
	        	System.out.println("Precesores es null");
	            return null;
	        }
	        path.add(step);
	        while (predecessors.get(step) != null) {
	            step = predecessors.get(step);
	            path.add(step);
	        }
	        // Put it into the correct order
	        Collections.reverse(path);
	        return path;
	    }

}
