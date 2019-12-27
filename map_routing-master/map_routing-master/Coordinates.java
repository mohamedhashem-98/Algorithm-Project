import org.jetbrains.annotations.NotNull;

import java.util.Vector;

public class Coordinates implements Comparable<Coordinates>{
    private float time;
    private float x , y ;
    private int number ;
    private float Distance ;
    private int Speed;
    Coordinates(){

    }
    Coordinates(int number , float x , float y , int Speed , float Distance , float time){
        this.x = x ;
        this.y = y;
        this.number = number;
        this.Distance = Distance;
        this.Speed = Speed;
        this.time  = time;
    }
    public float getX() {
        return  this.x;
    }
    public float getY(){
        return this.y;
    }
    public float getDistance(){
        return this.Distance;
    }
    public int getSpeed(){
        return this.Speed;
    }
    public float getTime(){
        return this.time;
    }
    public int getNumber(){
        return this.number;
    }
    public Vector<Coordinates>[] ReadyStart(float X , float Y , float R , Vector<Coordinates>[]adj ,Coordinates [] arr , int size){
        for(int i = 0 ; i < size-2; ++i){
            float Tmp = Calculate_Distance(X , Y , arr[i].getX() , arr[i].getY());

            if(Tmp <= (R/1000)){

                adj[size-2].add(new Coordinates(arr[i].getNumber() , arr[i].getX() , arr[i].getY() , 5, Tmp , Tmp /(float) 5));
            }
        }
        return adj;

    }
    public Vector<Coordinates>[] ReadyEnds(float X , float Y , float R , Vector<Coordinates>[]adj ,Coordinates [] arr , int size){
        for(int i = 0 ; i < size-2; ++i){
            float Tmp = Calculate_Distance(X , Y , arr[i].getX() , arr[i].getY());
            if(Tmp <= (R/1000)){

                adj[arr[i].getNumber()].add(new Coordinates(size-1 , X , Y , 5 , Tmp , Tmp/(float)5));
            }
        }
        return adj;

    }
    public float Calculate_Distance(float X1 , float Y1 , float X2 , float Y2){
        return (float) Math.sqrt(Math.pow(X1 - X2 , 2) + Math.pow(Y1 - Y2 , 2));
    }

    @Override
    public int compareTo(@NotNull Coordinates o) {
        if(time < o.time)return -1;
        if(time > o.time)return  1 ;
        return -1;
    }
}
