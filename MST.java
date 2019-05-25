import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    static int cost = 0;
    static int findMin(int [][] edges,HashSet<Integer> visited){
        int min = 99999;
        int next = 0;
        int j = 0;
        for(int start:visited){    // have to restart array to iterate from begining 
            for(int i = 0; i<edges.length; i++){
                if(edges[start][i]>=0 && edges[start][i] < min && !visited.contains(i)){
                    min = edges[start][i];
                    next = i;
                    j = start;
                }
            }
        }
        edges[j][next] = -1;
        edges[next][j] = -1;
        cost+=min;
        return next;
        
    }
    // Complete the prims function below.
    static int prims(int n, int[][] adj, int start) {

      int edges [][] = new int [n][n];
        HashSet<Integer> visited = new HashSet<Integer> ();
        for(int x = 0; x<n; x++){
            for(int y = 0; y<n; y++){
                edges[x][y] = Integer.MAX_VALUE;
           
            }
        }
        for(int i = 0; i<adj.length; i++){
            int x = adj[i][0]-1;
            int y = adj[i][1]-1;
            int cost = adj[i][2];
            edges[x][y] = cost;
            edges[y][x] = cost;
            
        }
        visited.add(start-1);
        while(visited.size()!=n){
            int next = findMin(edges,visited);
            visited.add(next);
        }
        return cost;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] edges = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] edgesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int edgesItem = Integer.parseInt(edgesRowItems[j]);
                edges[i][j] = edgesItem;
            }
        }

        int start = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = prims(n, edges, start);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
