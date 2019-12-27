import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Vector;

public class Solved {
    public float ShortestTime ;
    public float Distance ;
    public int []Path ;
    public float [] Cost;
    public float [] dis;

    public int [] Dijkstra(int Source , Vector<Coordinates>[]graph , int Size){
        Path = new int[Size];
        Cost = new float[Size];
        dis = new float[Size];
        for(int i = 0 ; i < Size; ++i)
            Path[i] = -1;
        for(int i = 0 ; i < Size ; ++i)
            Cost[i] = (int)1e9;
        for(int i = 0 ; i < Size ; ++i)
            dis[i] = (int)1e9;
        PriorityQueue<Coordinates>que = new PriorityQueue<>();
        que.add(new Coordinates(Source , 0 , 0 ,0,0,0));
        while (que.size()!=0){
            Coordinates cur = que.peek();
            que.remove();
            for(int i = 0 ; i < graph[cur.getNumber()].size() ; ++i){
                if(graph[cur.getNumber()].get(i).getTime() + cur.getTime() < Cost[graph[cur.getNumber()].get(i).getNumber()]){

                    Cost[graph[cur.getNumber()].get(i).getNumber()] = graph[cur.getNumber()].get(i).getTime() + cur.getTime();
                    Path[graph[cur.getNumber()].get(i).getNumber()] = cur.getNumber();
                    dis[graph[cur.getNumber()].get(i).getNumber()] = graph[cur.getNumber()].get(i).getDistance() + cur.getDistance();
                    que.add(new Coordinates(graph[cur.getNumber()].get(i).getNumber() , 0 , 0 , 0 ,graph[cur.getNumber()].get(i).getDistance() + cur.getDistance(), graph[cur.getNumber()].get(i).getTime() + cur.getTime() ));
                }
            }

        }
        ShortestTime = Cost[Size-1];
        Distance = dis[Size-1];
        return Path;
    }
}
