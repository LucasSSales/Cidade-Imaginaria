package Default;

import java.util.Random;

public class Car {

    //coordenadas do carro: onde começa, onde termina e onde esta no momento
    private int[] source;
    private int destiny;
    int[] current;
    char direction;
    private boolean waiting = false;

    public Car(int[] source, int destiny){
        this.source = source;
        this.destiny = destiny;
        this.current = source.clone();
    }

    //segue na direção da rua
    public int[] go(){
        int[] exit = new int[2];

        if(this.direction=='>'){
            exit[0] = this.current[0];
            exit[1] = (this.current[1]+1)%19;
        }else if(this.direction=='<'){
            exit[0] = this.current[0];
            exit[1] = this.current[1]-1;
            if(exit[1]<0)
                exit[1] += 19;
        }else if(this.direction=='^'){
            exit[0] = this.current[0]-1;
            exit[1] = this.current[1];
            if(exit[0]<0)
                exit[0] += 19;
        }else if(this.direction=='v'){
            exit[0] = (this.current[0]+1)%19;
            exit[1] = this.current[1];
        }else{
            return current;
        }
        return exit;
    }


    public void updateLocation(int[] newLocation){
        this.current[0] = newLocation[0];
        this.current[1] = newLocation[1];
    }


    //decide se muda a direção no semaforo ou continua
    public char changeDirection(TrafficLight tl){
        Random coin = new Random();
        if(coin.nextInt(2) == 0){
            System.out.println("coin 0");
            return direction;
        }else{
            System.out.println("coin 1");
            if(tl.getDir01() == direction){
                return tl.getDir02();
            }else{
                return tl.getDir01();
            }
        }
    }

    public boolean isWaiting() {
        return waiting;
    }

    @Override
    public String toString() {
        return "Current Location: "+current[0] + "  " + current[1] + "" +
                "\n Current Direction: "+direction;
    }
}
