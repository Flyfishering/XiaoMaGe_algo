package com.mj.graph;

import com.mj.MinHeap;
import com.mj.UnionFind;

import java.util.*;

@SuppressWarnings("unchecked")
public class ListGraph<V, E> extends Graph<V, E>{
    private final Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private final Set<Edge<V, E>> edges = new HashSet<>();
    private Comparator<Edge<V, E>> edgeComparator = (Edge<V, E> e1, Edge<V, E> e2) -> {
        return weightManager.compare(e1.weight, e2.weight);
    };

    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

    public ListGraph() {}

    public void print() {
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            System.out.println(v);
            System.out.println("-------in--------");
            System.out.println(vertex.inEdges);
            System.out.println("-------out--------");
            System.out.println(vertex.outEdges);
        });

        edges.forEach((Edge<V, E> edge) -> {
            System.out.println(edge);
        });
    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        // 判断from，to顶点是否存在
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;

        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }

        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    @Override
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex == null) return;

        // 如果要一边遍历一边删除的话，用迭代器
        for (Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator(); iterator.hasNext();) {
            Edge<V, E> edge = iterator.next();
            edge.to.inEdges.remove(edge);
            iterator.remove(); // 将当前遍历到的元素从集合中删掉
            edges.remove(edge);
        }

        for (Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator(); iterator.hasNext();) {
            Edge<V, E> edge = iterator.next();
            edge.from.outEdges.remove(edge);
            iterator.remove(); // 将当前遍历到的元素从集合中删掉
            edges.remove(edge);
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            return;
        }

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            return;
        }

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }

    }

    @Override
    public void bfs(V v, vertexVisitor<V> visitor) {
        if (visitor == null) return;
        Vertex<V, E> beginVertex = vertices.get(v);
        if (beginVertex == null) return;

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);

        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            if (visitor.visit(vertex.value)) return;

            for (Edge<V, E> edge : vertex.outEdges) {
                if (visitedVertices.contains(edge.to)) continue;
                queue.offer(edge.to);
                visitedVertices.add(edge.to);
            }
        }
    }

    @Override
    public void dfs(V v, vertexVisitor<V> visitor) {
        if (visitor == null) return;
        Vertex<V, E> beginVertex = vertices.get(v);
        if (beginVertex == null) return;

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();

        stack.push(beginVertex);
        if (visitor.visit(beginVertex.value)) return;
        visitedVertices.add(beginVertex);

        while (!stack.isEmpty()) {
            Vertex<V, E> vertex = stack.pop();
            for (Edge<V, E> edge : vertex.outEdges) {
                if (!visitedVertices.contains(edge.to)) {
                    stack.push(vertex);
                    stack.push(edge.to);
                    if (visitor.visit(edge.to.value)) return;
                    visitedVertices.add(edge.to);
                    break;
                }
            }
        }
    }

//    @Override
//    public void bfs(V v) {
//        Vertex<V, E> beginVertex = vertices.get(v);
//        if (beginVertex == null) return;
//
//        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
//        Queue<Vertex<V, E>> queue = new LinkedList<>();
//        queue.offer(beginVertex);
//        visitedVertices.add(beginVertex);
//
//        while (!queue.isEmpty()) {
//            Vertex<V, E> vertex = queue.poll();
//            System.out.println(vertex);
//
//            for (Edge<V, E> edge : vertex.outEdges) {
//                if (visitedVertices.contains(edge.to)) continue;
//                queue.offer(edge.to);
//                visitedVertices.add(edge.to);
//            }
//        }
//    }

//    @Override
//    public void dfs(V v) {
//        Vertex<V, E> beginVertex = vertices.get(v);
//        if (beginVertex == null) return;
//        dfs(beginVertex, new HashSet<>());
//
//    }
//
//    private void dfs(Vertex<V, E> vertex, Set<Vertex<V, E>> visitedVertices) {
//        System.out.println(vertex.value);
//        visitedVertices.add(vertex);
//        for (Edge<V, E> edge : vertex.outEdges) {
//            if (visitedVertices.contains(edge.to)) continue;
//            dfs(edge.to, visitedVertices);
//        }
//    }
//
//    // 迭代版dfs
//    public void dfs2(V v) {
//        Vertex<V, E> beginVertex = vertices.get(v);
//        if (beginVertex == null) return;
//
//        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
//        Stack<Vertex<V, E>> stack = new Stack<>();
//
//        stack.push(beginVertex);
//        System.out.println(beginVertex.value);
//        visitedVertices.add(beginVertex);
//
//        while (!stack.isEmpty()) {
//            Vertex<V, E> vertex = stack.pop();
//            for (Edge<V, E> edge : vertex.outEdges) {
//                if (!visitedVertices.contains(edge.to)) {
//                    stack.push(vertex);
//                    stack.push(edge.to);
//                    System.out.println(edge.to.value);
//                    visitedVertices.add(edge.to);
//                    break;
//                }
//            }
//        }
//    }

    // 拓扑排序(不断的找入度为0的vertex)
    @Override
    public List<V> topologicalSort() {
        List<V> list = new ArrayList<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>(); // 用来存放入度为0的点
        Map<Vertex<V, E>, Integer> ins = new HashMap<>();

        // 初始化(将入度为0的点放入队列序列)
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            int in = vertex.inEdges.size();
            if (in == 0) {
                queue.offer(vertex);
            } else { // 记录一下最初入度不为0的点
                ins.put(vertex, in);
            }
        });

        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            list.add(vertex.value);
            for (Edge<V, E> edge : vertex.outEdges) {
                int toIn = ins.get(edge.to) - 1;
                if (toIn == 0) {
                    queue.offer(edge.to);
                } else {
                    ins.put(edge.to, toIn);
                }
            }
        }

        return list;
    }

    @Override
    public Set<EdgeInfo<V, E>> mst() {
        return prim();
    }

    private Set<EdgeInfo<V, E>> prim() {
        Iterator<Vertex<V, E>> it = vertices.values().iterator();
        if (!it.hasNext()) return null;

        Set<EdgeInfo<V, E>> edgeInfo = new HashSet<>();
        Set<Vertex<V, E>> addedVertices = new HashSet<>();

        Vertex<V, E> vertex = it.next();
        addedVertices.add(vertex);
        MinHeap<Edge<V, E>> heap = new MinHeap<>(vertex.outEdges, edgeComparator);

        int verticesSize = vertices.size();
        while (!heap.isEmpty() && addedVertices.size() < verticesSize) {
            Edge<V, E> edge = heap.remove();
            if (addedVertices.contains(edge.to)) continue;

            edgeInfo.add(edge.info());
            addedVertices.add(edge.to);
            heap.addAll(edge.to.outEdges); // 这边是全加入堆，然后从堆取的时候判断是否已经有了。也可以加的时候就先判断是否重复了
        }
        return edgeInfo;
    }

//    @Override
//    public Map<V, PathInfo<V, E>> shortestPath(V begin) {
//        Vertex<V, E> beginVertex = vertices.get(begin);
//        if (beginVertex == null) return null;
//
//        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();
//        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
//
//        // 初始化paths
//        for (Edge<V, E> edge : beginVertex.outEdges) {
//            PathInfo<V, E> path = new PathInfo<>();
//            path.weight = edge.weight;
//            path.edgeInfos.add(edge.info());
//            paths.put(edge.to, path);
//        }
//
//        while (!paths.isEmpty()) {
//            Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = getMinPath(paths);
//            // minVertex离开桌面
//            Vertex<V, E> minVertex = minEntry.getKey();
//            selectedPaths.put(minVertex.value, minEntry.getValue());
//            paths.remove(minVertex);
//
//            // 对minVertex的outEdges进行松弛操作(找出更短的最短路径)
//            for (Edge<V, E> edge : minVertex.outEdges) {
//                // 若edge.to已经离开桌面，就没必要进行松弛操作
//                if (selectedPaths.containsKey(edge.to.value)) continue;
//
//                E newWeight = weightManager.add(minEntry.getValue().weight, edge.weight);
//                PathInfo<V, E> oldPath = paths.get(edge.to);
//
////                if (oldPath == null || weightManager.compare(newWeight, oldPath.weight) < 0) {
////                    PathInfo<V, E> path = new PathInfo<>();
////                    path.weight = newWeight;
////                    path.edgeInfos.addAll(minEntry.getValue().edgeInfos);
////                    path.edgeInfos.add(edge.info());
////                    paths.put(edge.to, path);
////                }
//                // 对上面代码的优化
//                if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) continue;
//
//                if (oldPath == null) {
//                    oldPath = new PathInfo<>();
//                    paths.put(edge.to, oldPath);
//                } else {
//                    oldPath.edgeInfos.clear();
//                }
//                oldPath.weight = newWeight;
//                oldPath.edgeInfos.addAll(minEntry.getValue().edgeInfos);
//                oldPath.edgeInfos.add(edge.info());
//            }
//        }
//        selectedPaths.remove(begin);
//        return selectedPaths;
//    }

//    @Override
//    public Map<V, E> shortestPath(V begin) {
//        Vertex<V, E> beginVertex = vertices.get(begin);
//        if (beginVertex == null) return null;
//
//        Map<Vertex<V, E>, E> paths = new HashMap<>();
//        Map<V, E> selectedPaths = new HashMap<>();
//
//        // 初始化paths
//        for (Edge<V, E> edge : beginVertex.outEdges) {
//            paths.put(edge.to, edge.weight);
//        }
//
//        while (!paths.isEmpty()) {
//            Map.Entry<Vertex<V, E>, E> minEntry = getMinPath(paths);
//            // minVertex离开桌面
//            Vertex<V, E> minVertex = minEntry.getKey();
//            selectedPaths.put(minVertex.value, minEntry.getValue());
//            paths.remove(minVertex);
//
//            // 对minVertex的outEdges进行松弛操作(找出更短的最短路径)
//            for (Edge<V, E> edge : minVertex.outEdges) {
//                if (selectedPaths.containsKey(edge.to.value)) continue;
//
//                E newWeight = weightManager.add(minEntry.getValue(), edge.weight);
//                E oldWeight = paths.get(edge.to);
//
//                if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0) {
//                    paths.put(edge.to, newWeight);
//                }
//            }
//        }
//        selectedPaths.remove(begin);
//        return selectedPaths;
//    }

    @Override
    public Map<V, PathInfo<V, E>> shortestPath(V begin) {
        return dijkstra(begin);
    }

    @Override
    public Map<V, Map<V, PathInfo<V, E>>> shortestPath() {
        Map<V, Map<V, PathInfo<V, E>>> paths = new HashMap<>();
        // 初始化
        for (Edge<V, E> edge : edges) {
            Map<V, PathInfo<V, E>> map = paths.get(edge.from);
            if (map == null) {
                map = new HashMap<>();
                paths.put(edge.from.value, map);
            }
            PathInfo<V, E> pathInfo = new PathInfo<>(edge.weight);
            pathInfo.edgeInfos.add(edge.info());
            map.put(edge.to.value, pathInfo);
        }

        vertices.forEach((V v2, Vertex<V, E> vertex2) -> {
            vertices.forEach((V v1, Vertex<V, E> vertex1) -> {
                vertices.forEach((V v3, Vertex<V, E> vertex3) -> {
                    if (v1.equals(v2) || v1.equals(v3) || v2.equals(v3)) return;

                    // v1 -> v2
                    PathInfo<V, E> path12 = getPathInfo(v1, v2, paths);
                    if (path12 == null) return;
                    // v2 -> v3
                    PathInfo<V, E> path23 = getPathInfo(v2, v3, paths);
                    if (path23 == null) return;
                    // v1 -> v3
                    PathInfo<V, E> path13 = getPathInfo(v1, v3, paths);
                    E newWeight = weightManager.add(path12.weight, path23.weight);
                    if (path13 != null && weightManager.compare(newWeight, path13.weight) >= 0) return;

                    if (path13 == null) {
                        path13 = new PathInfo<>();
                        paths.get(v1).put(v3, path13);
                    } else {
                        path13.edgeInfos.clear();
                    }

                    path13.weight = newWeight;
                    path13.edgeInfos.addAll(path12.edgeInfos);
                    path13.edgeInfos.addAll(path23.edgeInfos);

                });
            });
        });
        return paths;
    }

    private PathInfo<V, E> getPathInfo(V from, V to, Map<V, Map<V, PathInfo<V, E>>> paths) {
        Map<V, PathInfo<V, E>> map = paths.get(from);
        return map == null ? null : map.get(to);
    }

    private Map<V, PathInfo<V, E>> bellmanFord(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;

        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        selectedPaths.put(begin, new PathInfo<>(weightManager.zero()));

        int count = vertices.size() - 1;
        for (int i = 0; i < count; i++) { // v-1次
            // 对所有的边进行松弛
            for (Edge<V, E> edge : edges) {
                // 起点信息
                PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
                // 如果要松弛的边的起点还没有确定，则无法松弛
                if (fromPath == null) continue;
                // 松弛
                relaxForBellmanFord(edge, fromPath, selectedPaths);
            }

        }
        selectedPaths.remove(begin);
        return selectedPaths;
    }

    private Map<V, PathInfo<V, E>> dijkstra(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;

        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();

        paths.put(beginVertex, new PathInfo<>(weightManager.zero())); // 类似添加哨兵统一逻辑的思路

        // 初始化paths
//        for (Edge<V, E> edge : beginVertex.outEdges) {
//            PathInfo<V, E> path = new PathInfo<>();
//            path.weight = edge.weight;
//            path.edgeInfos.add(edge.info());
//            paths.put(edge.to, path);
//        }

        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = getMinPath(paths);
            // minVertex离开桌面
            Vertex<V, E> minVertex = minEntry.getKey();
            PathInfo<V, E> minPath = minEntry.getValue();
            selectedPaths.put(minVertex.value, minPath);
            paths.remove(minVertex);

            // 对minVertex的outEdges进行松弛操作(找出更短的最短路径)
            for (Edge<V, E> edge : minVertex.outEdges) {
                // 若edge.to已经离开桌面，就没必要进行松弛操作
                if (selectedPaths.containsKey(edge.to.value)) continue;

                relaxForDijkstra(edge, minPath, paths);
            }
        }
        selectedPaths.remove(begin);
        return selectedPaths;
    }

    private Map.Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        Iterator<Map.Entry<Vertex<V, E>, PathInfo<V, E>>> it =  paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = it.next();

        while (it.hasNext()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> entry = it.next();
            if (weightManager.compare(entry.getValue().weight, minEntry.getValue().weight) < 0) {
                minEntry = entry;
            }
        }
        return minEntry;
    }

    /**
     * 松弛操作
     * @param edge 需要进行松弛的边
     * @param fromPath edge的from的最短路径信息
     * @param paths 存放着其他点(对于dijkstra来说是还没有离开桌面的点)的最短路径信息
     */
    private void relaxForDijkstra(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        PathInfo<V, E> oldPath = paths.get(edge.to);

        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) return;

        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to, oldPath);
        } else {
            oldPath.edgeInfos.clear();
        }
        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());
    }

    /**
     * 松弛操作
     * @param edge 需要进行松弛的边
     * @param fromPath edge的from的最短路径信息
     * @param paths 存放着其他点(对于dijkstra来说是还没有离开桌面的点)的最短路径信息
     */
    private boolean relaxForBellmanFord(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<V, PathInfo<V, E>> paths) {

        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        PathInfo<V, E> oldPath = paths.get(edge.to.value);

        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) return false;

        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to.value, oldPath);
        } else {
            oldPath.edgeInfos.clear();
        }
        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.info());

        return true;
    }

    private Set<EdgeInfo<V, E>> kruskal() {
        int edgeSize = vertices.size() - 1;
        if (edgeSize == -1) return null;

        Set<EdgeInfo<V, E>> edgeInfo = new HashSet<>();
        MinHeap<Edge<V, E>> heap = new MinHeap<>(edges, edgeComparator);

        UnionFind<Vertex<V, E>> uf = new UnionFind<>();
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            uf.makeSet(vertex);
        });
        while (!heap.isEmpty() && edgeInfo.size() < edgeSize) {
            Edge<V, E> edge = heap.remove();
            if (uf.isSame(edge.from, edge.to)) continue;

            edgeInfo.add(edge.info());
            uf.union(edge.from, edge.to);
        }

        return edgeInfo;
    }

    private static class Vertex<V, E> {
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();
        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Vertex)) return false;
            return Objects.equals(value, ((Vertex<V, E>)o).value);
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }

        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
        }
    }

    private static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        EdgeInfo<V, E> info() {
            return new EdgeInfo<>(from.value, to.value, weight);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Edge)) return false;
            Edge<V, E> edge = (Edge<V, E>) o;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            int result = from != null ? from.hashCode() : 0;
            result = 31 * result + (to != null ? to.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
}
