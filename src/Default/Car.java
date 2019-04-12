package Default;

import java.util.Random;

public class Car {

    //coordenadas do carro: onde começa, onde termina e onde esta no momento
    private int[] source;
    private int[] destiny;
    private int[] current;
    private String direction;

    public Car(int[] source, int[] destiny){
        this.source = source;
        this.destiny = destiny;
        this.current = source.clone();
    }

    //segue na direção da rua
    public void go(String direction){
        if(direction.equals("Right")){
            this.current[1]++;
        }else if(direction.equals("Left")){
            this.current[1]--;
        }else if(direction.equals("Up")){
            this.current[0]--;
        }else if(direction.equals("Down")){
            this.current[0]++;
        }
    }

    //para no sinal vermelho
//    public void stop(){
//
//    }

    //decide se muda a direção no semaforo ou continua
    public String changeDirection(String dir1, String dir2){
        Random coin = new Random();
        if(coin.nextInt(2) == 0){
            return dir1;
        }else{
            return dir2;
        }
    }
}
