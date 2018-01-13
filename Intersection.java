package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Intersection {
	// Nodo del grafo. Este nodo es la intersección y la lista de segmentos a los que puede ir ??
	private String id;
	private List<Segment> segmentIn;
	private List<Segment> segmentOut;
	private Map<String, ArrayList<String>> allowedWays;
	
	public Intersection(String id){
		this.id = id;
		this.segmentIn = new ArrayList<Segment>();
		this.segmentOut = new ArrayList<Segment>();
		this.allowedWays = new HashMap<String, ArrayList<String>>();
	}
	
	public Intersection(String id, List<Segment> segmentIn, List<Segment> segmentOut) {
		this.id = id;
		this.segmentIn = segmentIn;
		this.segmentOut = segmentOut;
		this.allowedWays = new HashMap<String, ArrayList<String>>();
	}
	
	public void addSegmentIn(Segment s){
		this.segmentIn.add(s);
	}
	
	public void addSegmentOut(Segment s){
		this.segmentOut.add(s);
	}
	
	public void addAllowedWay(String source, String target){
		if(!this.allowedWays.containsKey(source)){
			ArrayList<String> aux = new ArrayList<String>();
			aux.add(target);
			this.allowedWays.put(source, aux);
		} else {
			this.allowedWays.get(source).add(target);
		}
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public List<Segment> getSegmentIn() {
		return segmentIn;
	}

	public void setSegmentIn(List<Segment> segmentIn) {
		this.segmentIn = segmentIn;
	}
	
	public List<Segment> getAllowedSegments(Segment source){
		ArrayList<Segment> res = new ArrayList<Segment>();
		for(String s: this.allowedWays.get(source.getIdSegment())){
			res.add(this.getSegmentById(s));
		}
		System.out.println("--- GetAllowedSegment " + source.getIdSegment() + " : " +res);
		return res;
	}

	public List<Segment> getSegmentOut() {
		return segmentOut;
	}

	public void setSegmentOut(List<Segment> segmentOut) {
		this.segmentOut = segmentOut;
	}

	public Map<String, ArrayList<String>> getAllowedWays() {
		return allowedWays;
	}

	public void setAllowedWays(Map<String, ArrayList<String>> allowedWays) {
		this.allowedWays = allowedWays;
	}
	
	public Segment getSegmentById(String id){
		for(Segment s: this.segmentOut){
			if(s.getIdSegment().compareTo(id)==0){
				return s;
			}
		}
		for(Segment s: this.segmentIn){
			if(s.getIdSegment().compareTo(id)==0){
				return s;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Inte (id=" + id + ", ways=" + allowedWays.toString() + ")";
		//return "INTERSECTION";
	}
	
	
}
