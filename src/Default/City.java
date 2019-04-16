package Default;

import java.util.ArrayList;
import java.util.Random;

public class City {

    private char[][] city = new char[19][19];
    private char[][] moviment = new char[19][19];
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<TrafficLight> lights = new ArrayList<>();
    private Random r = new Random();

    //LEGENDA DA MATRIZ
    //^ - sul-norte
    //v - norte-sul
    //> - oeste-leste
    //< - leste-oeste
    //* - quarteirao
    //0 a 11 - semaforos

    public City(){
        //cidade
        buildCity();
        //semaforos
        putTrafficLights();
        //carros
        putCars();
        printCity();
    }


    public void moving(){
        //DECREMENTANDO TEMPO DOS SEMAFOROS
        for (TrafficLight tl :lights) {
            tl.decreaseTime();
        }

        int carro = 0;
        //DECIDINDO O Q CADA CARRO FARÁ
        for(Car car : cars){
            //PEGANDO A PROXIMA LOCALIZAÇÃO
            System.out.println("INFOS SOBRE O CARRO: "+ (carro++));
          //  System.out.println("Sua direção: "+car.direction);
            int[] next = car.go();
            //TORNANDO A MATRIZ CIRCULAR
            if(next[0] < 0 || next[1] < 0)
                System.out.println("vai toma no meio do olho do seu cu fdp arrombado desgraçado!");


            //VENDO PROXIMO CHAR
            char c = this.city[next[0]][next[1]];

            //DE CARA COM UM SEMAFORO VERMELHO
            if(c <= 11 && lights.get(c).isRed(car.direction)){
                System.out.println("O SINAL ESTA VERMELHO\n");
                continue;
            }

            //SE TIVER UM CARRO ALI
            if(this.moviment[next[0]][next[1]] == 'C'){
                System.out.println("CONGESTIONAMENTO\n");
                continue;
            }

            this.moviment[car.current[0]][car.current[1]] = ' ';
            car.updateLocation(next);
            this.moviment[car.current[0]][car.current[1]] = 'C';

            //ATUALIZANDO A DIREÇÃO
            if(c > 11){
                car.direction = c;
            }else{
                car.direction = car.changeDirection(lights.get(c));
            }
        }
    }

    public void buildCity(){
        for(int i = 0; i < 19; i++){
            for (int j=0; j < 19; j++){
                if(i%6==0 || j%6==0){
                    if(j==0 || j==6){
                        this.city[i][j] = 'v';
                    }else if(j==12 || j==18){
                        this.city[i][j] = '^';
                    }else if(i==0 || i==12){
                        this.city[i][j] = '<';
                    }
                    else if(i==6 || i==18){
                        this.city[i][j] = '>';
                    }
                }else{
                    this.city[i][j] = '*';
                }
            }
        }
        //bordas da matriz
        this.city[0][0] = 'v';
        this.city[0][18] = '<';
        this.city[18][0] = '>';
        this.city[18][18] = '^';
    }

    public void printCity(){
        for (int i =0; i < 19; i++){
            for(int j=0; j<19; j++){
                if(this.moviment[i][j] == 'C'){
                    System.out.print(this.moviment[i][j] + " ");
                }else if(this.city[i][j]>=0 && this.city[i][j]<=11){
                    System.out.print("# ");
                }else if(this.city[i][j]=='*'){
                    System.out.print("  ");
                }else{
                    System.out.print(this.city[i][j] + " ");
                }
            }
            System.out.println("");
        }
    }

    public void putCars(){
        Random r = new Random();
        for (int i = 0; i<20; i++){
            while(true){
                int x = r.nextInt(19);
                int y = r.nextInt(19);

                if(this.moviment[x][y]!='C' && (x%6==0 || y%6==0) && (this.city[x][y]>11)){
                    this.moviment[x][y] = 'C';
                    int[] source = {x, y};
                    Car newCar = new Car(source, r.nextInt(9));
                    newCar.direction = this.city[x][y];
                    cars.add(newCar);
                    //
                    System.out.println("Carro " + i + " em: "+ x +" "+ y);
                    System.out.println("Carro " + i + " vai para: "+ newCar.direction);
                    break;
                }
            }
        }
    }

    public void putTrafficLights(){
        //criando semaforos
        lights.add(new TrafficLight(r.nextInt(15), 0, 6, '<', 'v'));
        lights.add(new TrafficLight(r.nextInt(15), 0, 12, '<', '<')); //rever
        lights.add(new TrafficLight(r.nextInt(15), 6, 0, 'v', '>'));
        lights.add(new TrafficLight(r.nextInt(15), 6, 6, 'v', '>'));
        lights.add(new TrafficLight(r.nextInt(15), 6, 12, '^', '>'));
        lights.add(new TrafficLight(r.nextInt(15), 6, 18, '^', '^'));//rever
        lights.add(new TrafficLight(r.nextInt(15), 12, 0, 'v', 'v'));//rever
        lights.add(new TrafficLight(r.nextInt(15), 12, 6, 'v', '<'));
        lights.add(new TrafficLight(r.nextInt(15), 12, 12, '^', '<'));
        lights.add(new TrafficLight(r.nextInt(15), 12, 18, '^', '<'));
        lights.add(new TrafficLight(r.nextInt(15), 18, 6, '>', '>'));//rever
        lights.add(new TrafficLight(r.nextInt(15), 18, 12, '>', '^'));
        //adicionando na matriz
        this.city[0][6] = 0;
        this.city[0][12] = 1;
        this.city[6][0] = 2;
        this.city[6][6] = 3;
        this.city[6][12] = 4;
        this.city[6][18] = 5;
        this.city[12][0] = 6;
        this.city[12][6] = 7;
        this.city[12][12] = 8;
        this.city[12][18] = 9;
        this.city[18][6] = 10;
        this.city[18][12] = 11;
    }

}
