import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    private static Coordinates[] Input_Points(int Size , Scanner sc){
        Coordinates[] arr =  new Coordinates[Size];
         for(int i = 0 ; i < Size; ++i){
            arr[i] = new Coordinates(sc.nextInt() , sc.nextFloat() , sc.nextFloat() , 0 , 0 , 0);
        }
        return  arr;
    }
    private static void Connect(Vector<Coordinates>[]adj , int number_of_Connections ,int Size  , Coordinates[] arr, Scanner sc){
        for(int i = 0 ; i < Size; ++i)
            adj[i] = new Vector<>();
        for(int i = 0 ; i < number_of_Connections ; ++i){
            int A = sc.nextInt();
            int B = sc.nextInt();
            float distance = sc.nextFloat();
            int speed = sc.nextInt();
            Coordinates tmp = new Coordinates(arr[A].getNumber() , arr[A].getX() , arr[A].getY() , speed , distance , (float)distance / speed);
            adj[B].add(tmp);
            tmp =  new Coordinates(arr[B].getNumber() , arr[B].getX() , arr[B].getY() , speed , distance , (float)distance / speed);
            adj[A].add(tmp);
        }

    }
    private static void Queries(int number_of_Queries , int Size , Vector<Coordinates>[]adj , Coordinates[]arr, Scanner sc){
        long AK47 = System.currentTimeMillis();
        for(int i = 0 ; i < number_of_Queries ; ++i){
            long lStartTime = System.currentTimeMillis();
            float Xsource , Ysource , Xdestination , Ydestination , R;
            Xsource = sc.nextFloat();
            Ysource = sc.nextFloat();
            Xdestination = sc.nextFloat();
            Ydestination = sc.nextFloat();
            R = sc.nextFloat();
            Vector<Coordinates>[]tmpadj = new Vector[Size+2];
            for(int j = 0 ; j < Size+2 ; ++j)
                tmpadj[j] = new Vector<>();
            for(int j = 0 ; j < Size; ++j){
                for(int k = 0 ; k < adj[j].size() ; ++k){
                    tmpadj[j].add(adj[j].get(k));
                }
            }
            tmpadj = new Coordinates().ReadyStart(Xsource , Ysource , R , tmpadj, arr , Size+2);
            tmpadj = new Coordinates().ReadyEnds(Xdestination , Ydestination , R ,tmpadj , arr , Size+2);
            Solved OB = new Solved();
            int [] road = OB.Dijkstra(Size , tmpadj , Size+2);
            if(road[Size+1] == -1)System.out.println("No Road");
            else {
                System.out.printf("%.2f mins\n", OB.ShortestTime * 60);
                System.out.printf("%.2f km\n", OB.Distance);
                List<String> PATH = new LinkedList<>();
                int Index = Size + 1;
                ((LinkedList<String>) PATH).addFirst("END");
                while (Index != Size) {

                    Index = road[Index];
                    if (Index == Size) break;
                    ((LinkedList<String>) PATH).addFirst(String.valueOf(Index));
                }
                ((LinkedList<String>) PATH).addFirst("START");
                float F1 = new Coordinates().Calculate_Distance(Xsource, Ysource, arr[Integer.parseInt(PATH.get(1))].getX(), arr[Integer.parseInt(PATH.get(1))].getY());
                float F2 = new Coordinates().Calculate_Distance(Xdestination, Ydestination, arr[Integer.parseInt(PATH.get(PATH.size() - 2))].getX(), arr[Integer.parseInt(PATH.get(PATH.size() - 2))].getY());
                System.out.printf("%.2f km\n", (F1 + F2));
                System.out.printf("%.2f km\n", OB.Distance - (F1 + F2));
                System.out.print("Path : ");
                for (int j = 0; j < PATH.size(); ++j) {
                    System.out.print(PATH.get(j) + " ");
                }
                System.out.println();
                long lEndTime = System.currentTimeMillis();
                long output = lEndTime - lStartTime;
                System.out.println(output + " ms");
                System.out.println();
            }

        }
        long SCAR_L = System.currentTimeMillis();
        long XDD = SCAR_L - AK47;
        System.out.println(XDD + " ms");
    }
    public  static  void main(String[]args) throws IOException{
        Scanner sc = new Scanner(new File("input"));
        int n = sc.nextInt();
        Coordinates [] arr = Input_Points(n , sc);
        int m = sc.nextInt();
        Vector<Coordinates>[] adj = new Vector[n];
        Connect(adj , m , n, arr , sc);
        int l = sc.nextInt();
        Queries(l , n , adj , arr , sc);
    }
}
