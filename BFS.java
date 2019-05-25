import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    
    static int V;
    static LinkedList<Integer> [] adj;
    static int [] distance;
    static boolean [] visited;
    static void addEdge(int u,int v){
        adj[u].add(v);
        adj[v].add(u);
    }
    static void initialize(int V,int m,int [][] edges){
        //Solution.V=V;
        visited=new boolean[V+1];
        distance=new int[V+1];
        adj=(LinkedList<Integer> [] ) new LinkedList[V+1];
        for(int i=0;i<=V;i++){
            adj[i]=new LinkedList<Integer>();
        }
        for(int i=0;i<m;i++){
            addEdge(edges[i][0],edges[i][1]);
        }
    }
    // Complete the bfs function below.
    static int[] bfs(int n, int m, int[][] edges, int s) {
      initialize(n,m,edges);
        Queue<Integer> q=new LinkedList<>();
        int result []=new int[n-1];
        q.add(s);
        visited[s]=true;
        distance[s]=0;
        while(q.size()!=0){
            int parent=q.remove();
            for(int w:Solution.adj[parent]){
                if(!visited[w])
                {
                    q.add(w);   
                    distance[w]=distance[parent]+6;
                    visited[w]=true;
                }
            }
            
        }
        int original=1;
        int trimmed=0;
        while(original!=n+1){
            if(original==s){
                original+=1;
                continue;
            }
            result[trimmed]=distance[original];
            if(distance[original]==0){
                result[trimmed]=-1;
            }
            original+=1;
            trimmed+=1;
        }

        return result;
    }
    

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, m, edges, s);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
