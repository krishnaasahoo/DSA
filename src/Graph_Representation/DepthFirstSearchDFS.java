package Graph_Representation;

import java.util.*;


/**

 *
 *
 * Depth First Search (DFS)
 *
 *
 * 6
 *
 * Problem Statement: Given an undirected graph, return a vector of all nodes by traversing the graph using depth-first search (DFS).
 *
 * Examples
 *
 */
public class DepthFirstSearchDFS {

    public static void main(String[]args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of vertices : ");
        int V = sc.nextInt();

        System.out.println("Enter number of edges : ");
        int E = sc.nextInt();

        System.out.println("Enter edges from and to : ");
        int[][]edges = new int[E][2];
        for(int i=0 ; i<E ; i++){
            edges[i][0]=sc.nextInt();
            edges[i][1]=sc.nextInt();
        }

        List<Integer> dfsPrint = new ArrayList<>();
        boolean [] visited = new boolean[V];

        //prepared adjacent List
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0 ; i<V ; i++){
            adjList.add(new ArrayList<>());
        }
        for(int[]e:edges){
            adjList.get(e[0]).add(e[1]);
            adjList.get(e[1]).add(e[0]);
        }

        dfsWithQueue(V, adjList, dfsPrint, visited);

        for(int i : dfsPrint){
            System.out.print(i + " ");
        }

    }

    private static void dfsWithQueue(int V, List<List<Integer>> adjList, List<Integer> dfsPrint, boolean [] visited){
        for(int i=0 ; i<V ; i++){
            if(!visited[i]){

                Queue<Integer> q = new LinkedList<>();
                visited[i]=true;
                dfsPrint.add(i);
                q.add(i);
                while(!q.isEmpty()){
                    int curr = q.poll();
                    for(int j : adjList.get(curr)){
                        if(!visited[j]){
                            visited[j]=true;
                            dfsPrint.add(j);
                            q.add(j);
                        }
                    }
                }
            }
        }
    }
}
