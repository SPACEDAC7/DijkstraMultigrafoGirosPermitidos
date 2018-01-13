package main;

public class Segment {

	private String idSegment;
	private double weight;
	private Intersection initialNode;
	private Intersection finalNode;

	
	public Segment(Intersection initialNode, Intersection finalNode, String idSegment, double length) {
		this.initialNode = initialNode;
		this.finalNode = finalNode;
		this.idSegment = idSegment;
		this.weight = length;
	}

	public String getIdSegment() {
		return idSegment;
	}

	public void setIdSegment(String idSegment) {
		this.idSegment = idSegment;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public Intersection getSource(){
		return initialNode;
	}
	
	public Intersection getDestination(){
		return finalNode;
	}

	@Override
	public String toString() {
		return "Seg (id=" + idSegment + ", DE=" + initialNode + ", A=" + finalNode + ")";
		//return "SEGMENT";
	}
	
	
	
	
}