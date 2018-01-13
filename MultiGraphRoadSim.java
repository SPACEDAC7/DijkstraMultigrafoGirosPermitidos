package main;

import java.util.ArrayList;
import java.util.List;

public class MultiGraphRoadSim {
	
	private List<Intersection> nodes;
	private List<Segment> edges;
	
	public MultiGraphRoadSim(){
		this.nodes = new ArrayList<Intersection>();
		this.edges = new ArrayList<Segment>();
	}
	
	
	public void addNode(Intersection n){
		this.nodes.add(n);
	}
	
	public void addEdge(Segment e){
		this.edges.add(e);
	}
	
	public void addEdge(Intersection nodeI, Intersection nodeF, String id, double weight){
		Segment e = new Segment(nodeI, nodeF, id, weight);
		this.edges.add(e);
	}
	
	public Intersection getNodeById(String n){
		for(Intersection node:nodes){
			if(node.getId().compareTo(n) == 0){
				return node;
			}
		}
		return null;
	}
	
	public Segment getSegmentById(String n){
		for(Segment s:edges){
			if(s.getIdSegment().compareTo(n) == 0){
				return s;
			}
		}
		return null;
	}

	public List<Intersection> getNodes() {
		return nodes;
	}


	public void setNodes(List<Intersection> nodes) {
		this.nodes = nodes;
	}


	public List<Segment> getEdges() {
		return edges;
	}


	public void setEdges(List<Segment> edges) {
		this.edges = edges;
	}


	@Override
	public String toString() {
		return "MultiGraphRoadSim [nodes=" + nodes.toString() + ", edges=" + edges.toString() + "]";
	}
	
	

}
