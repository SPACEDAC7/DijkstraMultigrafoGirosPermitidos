
package main;

import java.util.LinkedList;

public class main {

	public static void main(String[] args) {
		MultiGraphRoadSim graph = new MultiGraphRoadSim();
		
		String[] nodes = {"1","2","3","4","5","6","7","8"};
		
		String[][] edges = {{"1","2","E1","2"},{"2","1","E2","2"},{"2","3","E3","2"},{"3","2","E4","2"},
							{"3","4","E5","2"},{"4","3","E6","2"},{"4","5","E7","2"},{"5","4","E8","2"},
							{"5","6","E9","2"},{"6","5","E10","2"},{"2","7","E11","2"},{"7","2","E12","2"},
							{"7","8","E13","2"},{"8","7","E14","2"},{"8","5","E15","2"},{"5","8","E16","2"},
							{"7","5","E17","2"},{"5","7","E18","2"},{"4","5","E19","2"}};
		
		
		for(String n :nodes){
			graph.addNode(new Intersection(n));
		}
		
		for(String[] edge:edges){
			Segment e = new Segment(graph.getNodeById(edge[0]),graph.getNodeById(edge[1]),edge[2],Double.parseDouble(edge[3]));
			graph.getNodeById(edge[0]).addSegmentOut(e);
			graph.getNodeById(edge[1]).addSegmentIn(e);
			graph.addEdge(graph.getNodeById(edge[0]),graph.getNodeById(edge[1]),edge[2],Double.parseDouble(edge[3]));
		}
		
		//Nodo 2
		graph.getNodeById("2").addAllowedWay("E1","E3");
		graph.getNodeById("2").addAllowedWay("E1","E11");
		graph.getNodeById("2").addAllowedWay("E4","E2");
		graph.getNodeById("2").addAllowedWay("E4","E11");
		graph.getNodeById("2").addAllowedWay("E12","E2");
		graph.getNodeById("2").addAllowedWay("E12","E3");
		//Nodo 3
		graph.getNodeById("3").addAllowedWay("E3","E5");
		graph.getNodeById("3").addAllowedWay("E6","E4");
		//Nodo 4
		graph.getNodeById("4").addAllowedWay("E5","E7");
		graph.getNodeById("4").addAllowedWay("E5","E19");
		graph.getNodeById("4").addAllowedWay("E8","E6");
		//Nodo 5
		graph.getNodeById("5").addAllowedWay("E19","E9");
		graph.getNodeById("5").addAllowedWay("E7","E9");
		graph.getNodeById("5").addAllowedWay("E10","E8");
		graph.getNodeById("5").addAllowedWay("E18","E9");
		graph.getNodeById("5").addAllowedWay("E18","E8");
		graph.getNodeById("5").addAllowedWay("E10","E17");
		//Node 7
		graph.getNodeById("7").addAllowedWay("E14","E12");
		graph.getNodeById("7").addAllowedWay("E14","E18");
		graph.getNodeById("7").addAllowedWay("E11","E13");
		graph.getNodeById("7").addAllowedWay("E11","E18");
		graph.getNodeById("7").addAllowedWay("E17","E13");
		graph.getNodeById("7").addAllowedWay("E17","E12");
		//Node 8
		graph.getNodeById("8").addAllowedWay("E16","E14");
		graph.getNodeById("8").addAllowedWay("E13","E15");
		
		
		System.out.println("////////////GRAPH/////////////");
		System.out.println(graph.toString() + "\n");
		System.out.println("-------------- EMPIEZA EL CAMINO ------------------");
		
		DijkstraGirosPermitidos dijkstra = new DijkstraGirosPermitidos(graph);
        dijkstra.execute(graph.getNodeById("1"));
        LinkedList<Intersection> path = dijkstra.getPath(graph.getNodeById("6"));
        System.out.println(path.toString());
        for (Intersection vertex : path) {
            System.out.println(vertex.toString());
        }
	}
}
