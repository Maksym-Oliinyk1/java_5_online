package ua.com.alevel.service;

import ua.com.alevel.edge.Edge;

import java.io.*;
import java.util.*;

public class Service {
    static final int INFINITY = 200000;

    static int algorithmOfDijkstra(int start, int end, List<List<Edge>> graph) {
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, INFINITY);
        dist[start] = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(v -> dist[v]));
        pq.offer(start);
        while (!pq.isEmpty()) {
            int u = pq.poll();
            if (u == end) {
                return dist[end];
            }
            for (Edge e : graph.get(u)) {
                int v = e.to;
                int newDist = dist[u] + e.cost;
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.offer(v);
                }
            }
        }
        return INFINITY;
    }

    public void start() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("module_2_text_files/input.txt"));
        PrintWriter out = new PrintWriter(new FileWriter("module_2_text_files/output.txt"));

        int n = Integer.parseInt(in.readLine());
        Map<String, Integer> cityIndexMap = new HashMap<>();
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String cityName = in.readLine();
            cityIndexMap.put(cityName, i);
            graph.add(new ArrayList<>());
            int p = Integer.parseInt(in.readLine());
            for (int j = 0; j < p; j++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int nr = Integer.parseInt(st.nextToken()) - 1;
                int cost = Integer.parseInt(st.nextToken());
                graph.get(i).add(new Edge(nr, cost));
            }
        }
        int r = Integer.parseInt(in.readLine());
        for (int i = 0; i < r; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int start = cityIndexMap.get(st.nextToken());
            int end = cityIndexMap.get(st.nextToken());
            int minCost = algorithmOfDijkstra(start, end, graph);
            out.println(minCost);
        }
        in.close();
        out.close();
    }
}

