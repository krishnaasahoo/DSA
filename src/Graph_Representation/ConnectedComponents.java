package Graph_Representation;

import java.util.*;

/**
 *
 * Leetcode Problem : 323 Number of Connected Components in an Undirected Graph
 *
 * Problem Statement: Given an undirected Graph consisting of V vertices numbered from 0 to V-1 and E edges.
 * The ith edge is represented by [ai,bi], denoting a edge between vertex ai and bi.
 * We say two vertices u and v belong to a same component if there is a path from u to v or v to u.
 * Find the number of connected components in the graph.
 *
 * A connected component is a subgraph of a graph in which there exists a path between any two vertices,
 * and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.
 *
 *
 *
 *
 * Input: V=4, edges=[[0,1],[1,2]]
 * Output: 2
 * Explanation: Vertices {0,1,2} forms the first component and vertex 3 forms the second component.
 * Input:V = 7, edges = [[0, 1], [1, 2], [2, 3], [4, 5]]
 * Output: 3
 * Explanation: The edges [0, 1], [1, 2], [2, 3] form a connected component with vertices {0, 1, 2, 3}
 * The edge [4, 5] forms another connected component with vertices {4, 5}.
 * Therefore, the graph has 3 connected components: {0, 1, 2, 3}, {4, 5}, and the isolated vertices {6}.
 *
 */
public class ConnectedComponents {

    public static void main(String[]args){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of vertices : ");
        int V = sc.nextInt();

        System.out.println("Enter number of edges : ");
        int E = sc.nextInt();

        int [][] edges = new int [E][2];
        for(int i=0 ; i<E ; i++){
            edges[i][0]=sc.nextInt();
            edges[i][1]=sc.nextInt();
        }

        int noOfComponents = countComponents(V,edges);
        System.out.println(noOfComponents);
    }

    private static int countComponents(int V, int [][] edges){
        // Variable to count the number of connected components
        int components=0;

        // Create adjacent list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }


        for(int[]e:edges){
            adjList.get(e[0]).add(e[1]);
            adjList.get(e[1]).add(e[0]);
        }

        //boolean Array to track which Nodes are visited
        boolean [] visited = new boolean[V];//0,1,2,3

        for(int i=0 ; i<V ; i++){
            //traverse not visited node
            if(!visited[i]){
                components++;

                //Start BFS
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                visited[i]=true;
                while(!q.isEmpty()){
                    int curr = q.poll();
                    for(int j: adjList.get(curr)){
                        if(!visited[j]) {
                            visited[j] = true;
                            q.add(j);
                        }
                    }
                }

            }
        }
        return components;
    }
}

//Time Complexity: O(V+E),Each vertex is visited exactly once, and each edge is processed at most twice (once from each end).
//Space Complexity: O(V+E), To build Adjacency List.
