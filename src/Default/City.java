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
    }
    
    
    public String getCity(){
        String exit = "<html><p>";
        for(int i = 0; i < 19; i++){
            for(int j=0; j<19; j++){
                if(this.moviment[i][j] == 'C'){
                    exit+="C";
               }else if(this.city[i][j]<=11){
                    exit += "#";
                }else if(this.city[i][j]=='<'){
                    exit += "&lt";
                }else if(this.city[i][j]=='>'){
                    exit += "&gt";
                }else if(this.city[i][j]=='*'){
                    exit += "--";
                }
                else{
                    exit += ""+this.city[i][j];
                }
                exit+="  ";
            }
            exit += "<br/>";
        }
        exit += "</p></html>";
       // System.out.println(exit);
        return exit;
    }


    public int moving(int currentTime){
        //DECREMENTANDO TEMPO DOS SEMAFOROS
        for (TrafficLight tl :lights) {
            tl.decreaseTime();
        }

        int congs = 0;

        int carro = 0;
        //DECIDINDO O Q CADA CARRO FARÁ
        for(Car car : cars){
            
            if(car.finished)
                continue;
            
            //System.out.println("NAO FINISHOU");
            
            if(!car.canLeave(currentTime))
                continue;
            
            ///System.out.println("NAO PODE LEAVAR");
            
            if(!car.started && this.moviment[car.getSource()[0]][car.getSource()[1]]!= 'C'){
                this.moviment[car.current[0]][car.current[1]] = 'C';
                car.started = true;
                //System.out.println("e... começou!");
                //printCity();
            }
            
            //System.out.println("JA DEVIA ESTAR NA MATRIZ");
            
            //PEGANDO A PROXIMA LOCALIZAÇÃO;
            int[] next = car.go();

            //VENDO PROXIMO CHAR
            char c = this.city[next[0]][next[1]];

            //DE CARA COM UM SEMAFORO VERMELHO
            if(c <= 11 && lights.get(c).isRed(car.direction)){
                continue;
            }

            //SE TIVER UM CARRO ALI
            if(this.moviment[next[0]][next[1]] == 'C'){
                if(isCongs(next, car.direction))
                    congs++;
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
            
            //vendo se chegou ao destino/esta proximo ao quarteirao
            int x = car.current[0], y = car.current[1];
            car.arrived(x-1<0?this.city[19+(x-1)][y]:this.city[x-1][y], this.city[(x+1)%19][y], 
                    this.city[x][(y+1)%19], y-1<0?this.city[x][19+(y-1)]:this.city[x][y-1]);
            if(car.finished)
                this.moviment[x][y] = ' ';
            
        }
        return congs;
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

        //inidcadores de semaforo
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
        
        //identificando o quarteirao
        for(int i =1; i<6; i++){
            for(int j =1; j<6; j++){
                this.city[i][j] = '1';
            }
        }
        
        for(int i =1; i<6; i++){
            for(int j =7; j<12; j++){
                this.city[i][j] = '2';
            }
        }
        
        for(int i =1; i<6; i++){
            for(int j =13; j<18; j++){
                this.city[i][j] = '3';
            }
        }
        
        for(int i =7; i<12; i++){
            for(int j =1; j<6; j++){
                this.city[i][j] = '4';
            }
        }
        
        for(int i =7; i<12; i++){
            for(int j =7; j<12; j++){
                this.city[i][j] = '5';
            }
        }
        
        for(int i =7; i<12; i++){
            for(int j =13; j<18; j++){
                this.city[i][j] = '6';
            }
        }
        
        for(int i =13; i<18; i++){
            for(int j =1; j<6; j++){
                this.city[i][j] = '7';
            }
        }
        
        for(int i =13; i<18; i++){
            for(int j =7; j<12; j++){
                this.city[i][j] = '8';
            }
        }
        
        for(int i =13; i<18; i++){
            for(int j =13; j<18; j++){
                this.city[i][j] = '9';
            }
        }
        
        printCity();
        
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
                    
                    int[] source = {x, y};
                    Car newCar = new Car(source);
                    newCar.direction = this.city[x][y];
                    cars.add(newCar);
                    //
//                    System.out.println("Carro " + i + " em: "+ x +" "+ y);
//                    System.out.println("Carro " + i + " vai para: "+ newCar.direction);
                    break;
                }
            }
        }
    }
    
    public ArrayList<Car> getCars(){
        return cars;
    }

    public void resetMoviment(){
        for (Car c: cars) {
            this.moviment[c.current[0]][c.current[1]] =' ';
            c.finished = false;
            c.started = false;
            //this.moviment[c.getSource()[0]][c.getSource()[1]] ='C';
        }
        //printCity();
    }

    public boolean isCongs(int[] coords, char dir){
//        System.out.println(coords[0] + " " + coords[1]);
////        System.out.println(dir + "");
        char c = this.city[coords[0]][coords[1]];
        if(c<=11 && lights.get(c).isRed(dir)){
            return true;
        }else if(c<=11 && !lights.get(c).isRed(dir)){
            return false;
        }else if(this.moviment[coords[0]][coords[1]]=='C'){
            if(dir=='>'){
                coords[0] = coords[0];
                coords[1] = (coords[1]+1)%19;
            }else if(dir=='<'){
                coords[0] = coords[0];
                coords[1] = coords[1]-1;
                if(coords[1]<0)
                    coords[1] += 19;
            }else if(dir=='^'){
                coords[0] = coords[0]-1;
                coords[1] = coords[1];
                if(coords[0]<0)
                    coords[0] += 19;
            }else if(dir=='v'){
                coords[0] = (coords[0]+1)%19;
                coords[1] = coords[1];
            }
            return isCongs(coords, this.city[coords[0]][coords[1]]);
        }else{
            return false;
        }
    }

    public void putTrafficLights(int[] lightTimes){
        //criando semaforos
        lights.add(new TrafficLight(lightTimes[0], 0, 6, '<', 'v'));
        lights.add(new TrafficLight(lightTimes[1], 0, 12, '<', '<')); //rever
        lights.add(new TrafficLight(lightTimes[2], 6, 0, 'v', '>'));
        lights.add(new TrafficLight(lightTimes[3], 6, 6, 'v', '>'));
        lights.add(new TrafficLight(lightTimes[4], 6, 12, '^', '>'));
        lights.add(new TrafficLight(lightTimes[5], 6, 18, '^', '^'));//rever
        lights.add(new TrafficLight(lightTimes[6], 12, 0, 'v', 'v'));//rever
        lights.add(new TrafficLight(lightTimes[7], 12, 6, 'v', '<'));
        lights.add(new TrafficLight(lightTimes[8], 12, 12, '^', '<'));
        lights.add(new TrafficLight(lightTimes[9], 12, 18, '^', '<'));
        lights.add(new TrafficLight(lightTimes[10], 18, 6, '>', '>'));//rever
        lights.add(new TrafficLight(lightTimes[11], 18, 12, '>', '^'));
    }

}
