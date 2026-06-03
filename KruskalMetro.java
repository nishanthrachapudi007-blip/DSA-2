import java.util.*;

class Edge implements Comparable<Edge> {
    char src, dest;
    int weight;

    Edge(char s, char d, int w) {
        src = s;
        dest = d;
        weight = w;
    }

    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class KruskalMetro {

    static Map<Character, Character> parent = new HashMap<>();
    static Map<Character, Integer> rank = new HashMap<>();

    // Make Set
    static void makeSet(char v) {
        parent.put(v, v);
        rank.put(v, 0);
    }

    // Find
    static char find(char v) {
        if (parent.get(v) != v) {
            parent.put(v, find(parent.get(v)));
        }
        return parent.get(v);
    }

    // Union
    static void union(char a, char b) {
        char rootA = find(a);
        char rootB = find(b);

        if (rootA != rootB) {

            if (rank.get(rootA) > rank.get(rootB)) {
                parent.put(rootB, rootA);
            } else {
                parent.put(rootA, rootB);

                if (rank.get(rootA).equals(rank.get(rootB))) {
                    rank.put(rootB, rank.get(rootB) + 1);
                }
            }
        }
    }

    public static void main(String[] args) {

        char[] vertices = {'H', 'K', 'M', 'W', 'Y', 'E', 'S'};

        ArrayList<Edge> edges = new ArrayList<>();

        edges.add(new Edge('E', 'S', 4));
        edges.add(new Edge('K', 'W', 5));
        edges.add(new Edge('S', 'W', 6));
        edges.add(new Edge('M', 'E', 7));
        edges.add(new Edge('M', 'K', 8));
        edges.add(new Edge('Y', 'E', 8));
        edges.add(new Edge('H', 'Y', 9));
        edges.add(new Edge('Y', 'M', 9));
        edges.add(new Edge('M', 'S', 10));
        edges.add(new Edge('H', 'M', 11));
        edges.add(new Edge('M', 'W', 12));
        edges.add(new Edge('H', 'K', 14));

        // Sort edges
        Collections.sort(edges);

        // Initialize sets
        for (char v : vertices) {
            makeSet(v);
        }

        ArrayList<Edge> mst = new ArrayList<>();
        int totalCost = 0;

        // Kruskal Algorithm
        for (Edge edge : edges) {

            if (find(edge.src) != find(edge.dest)) {

                union(edge.src, edge.dest);

                mst.add(edge);

                totalCost += edge.weight;
            }
        }

        // Display MST
        System.out.println("Minimum Spanning Tree:");

        for (Edge e : mst) {
            System.out.println(
                e.src + " - " + e.dest + " : " + e.weight + " crore"
            );
        }

        System.out.println("\nTotal Construction Cost = "
                + totalCost + " crore");

        // Redundancy augmentation
        System.out.println("\nAdditional Edge for Redundancy:");
        System.out.println("Y - M : 9 crore");

        System.out.println("Final Augmented Cost = "
                + (totalCost + 9) + " crore");
    }
}