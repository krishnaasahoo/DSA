package Graph_Representation;

import java.util.*;


/**
 *
 * Problem Statement: Given an undirected graph, return a vector of all nodes by traversing
 * the graph using depth-first search (DFS).
 *
 * Examples
 *
 */
public class DepthFirstSearchDFS {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        // Prepare adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        System.out.println("Enter edges (from to):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            // Undirected Graph
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        // ---------------- Recursive DFS ----------------
        boolean[] visitedRec = new boolean[V];
        List<Integer> dfsRec = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            if (!visitedRec[i]) {
                dfsWithRecursion(i, adjList, visitedRec, dfsRec);
            }
        }

        System.out.println("\nRecursive DFS:");
        System.out.println(dfsRec);

        // ---------------- Stack DFS ----------------
        boolean[] visitedStack = new boolean[V];
        List<Integer> dfsStack = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            if (!visitedStack[i]) {
                dfsWithStack(i, adjList, visitedStack, dfsStack);
            }
        }

        System.out.println("Iterative DFS:");
        System.out.println(dfsStack);
    }

    // Recursive DFS
    private static void dfsWithRecursion(int node, List<List<Integer>> adjList, boolean[] visited, List<Integer> result) {

        visited[node] = true;
        result.add(node);

        for (int neighbour : adjList.get(node)) {
            if (!visited[neighbour]) {
                dfsWithRecursion(neighbour, adjList, visited, result);
            }
        }
    }

    // Iterative DFS using Stack
    private static void dfsWithStack(int start, List<List<Integer>> adjList, boolean[] visited, List<Integer> result) {

        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {

            int node = stack.pop();

            if (!visited[node]) {

                visited[node] = true;
                result.add(node);

                // Reverse traversal so output matches recursive DFS
                List<Integer> neighbours = adjList.get(node);

                for (int i = neighbours.size() - 1; i >= 0; i--) {

                    int neighbour = neighbours.get(i);

                    if (!visited[neighbour]) {
                        stack.push(neighbour);
                    }
                }
            }
        }
    }
}

//Time Complexity: O(V+E), each vertex is visited once and every edge is checked once in the adjacency list.
//Space Complexity: O(V) , additional amount of space required for recursion stack.
