package com.mj.graph;

public class Main {

    public static void main(String args[]) {
        ListGraph<Object, Integer> graph = new ListGraph<>(new Graph.WeightManager<Integer>() {
            @Override
            public int compare(Integer w1, Integer w2) {
                return w1.compareTo(w2);
            }

            @Override
            public Integer add(Integer w1, Integer w2) {
                return w1 + w2;
            }

            @Override
            public Integer zero() {
                return 0;
            }
        });
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);
        graph.addEdge("V0", "V4", 6);

        //graph.removeEdge("V0", "V4");
        //graph.removeVertex("V0");

        //graph.print();
        graph.bfs("V1", (Object value) -> {
            System.out.println(value);
            return false;
        });
    }
}
