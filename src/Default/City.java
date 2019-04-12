package Default;

import java.util.Random;

public class City {

    private char[][] city = new char[19][19];

    //LEGENDA DA MATRIZ
    //U - up
    //D - down
    //R - right
    //L - left
    //B - block
    //T - traffic light
    //

    public City(){
        for(int i = 0; i < 19; i++){
            for (int j=0; j < 19; j++){
                if(i%6==0 && j%6==0){
                    this.city[i][j] = '#';
                }else if(i%6==0 || j%6==0){
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
        this.city[0][0] = 'v';
        this.city[0][18] = '<';
        this.city[18][0] = '>';
        this.city[18][18] = '^';

        Random r = new Random();
        for (int i = 0; i<20; i++){
            while(true){
                int x = r.nextInt(19);
                int y = r.nextInt(19);

                if(this.city[x][y] != 'C' && this.city[x][y] != '*'){
                    this.city[x][y] = 'C';
                    System.out.println("Carro " + i + " em: "+ x +" "+ y);
                    break;
                }
            }
        }

        for(int i = 0; i < 19; i++){
            for (int j=0; j < 19; j++){
                System.out.print(this.city[i][j] + " ");
            }
            System.out.println("");
        }

    }
}
