import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    
    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, LinkedList<Integer>[]cities) {

         long cost=0;
        boolean visited[]=new boolean[n+1];
        for(int i=1;i<=n;i++){
             long s=getClusterSize(cities,i,visited,cost,c_lib);
             if(s>0){
                 cost+=c_lib;
                 if(c_road<c_lib){
                     cost+=((s-1)*c_road);
                   }
                 else{
                     cost+=((s-1)*c_lib);
                   }   
                }
        }     
     return cost;
    }

     static long getClusterSize(LinkedList<Integer>[]cities,int start,boolean                   visited[],long cost,int c_lib){
         Stack<Integer>s=new Stack<Integer>();
         s.push(start);
         long size=0;
         while(!s.isEmpty()){
             int parent=s.pop();
             if(!visited[parent]){
                 visited[parent]=true;
                 size++;
                 if(cities[parent]!=null)
                   for(int i=0;i<cities[parent].size();i++){
                     s.push(cities[parent].get(i));
                    }
            }
        }
    return size;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];
            LinkedList<Integer>list[]=new LinkedList[n+1];
            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                if(list[Integer.parseInt(citiesRowItems[0])]==null){
                  list[Integer.parseInt(citiesRowItems[0])]=new LinkedList<Integer>();
                }

               if(list[Integer.parseInt(citiesRowItems[1])]==null){
                 list[Integer.parseInt(citiesRowItems[1])]=new LinkedList<Integer>();
                }
              list[Integer.parseInt(citiesRowItems[0])].add(Integer.parseInt(citiesRowItems[1]));

              list[Integer.parseInt(citiesRowItems[1])].add(Integer.parseInt(citiesRowItems[0]));
            }
          

            long result = roadsAndLibraries(n, c_lib, c_road, list);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
