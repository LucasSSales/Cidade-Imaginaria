package Default;

public class Main {


    public static void main(String args[]){
        City c = new City();
        char[][] city;

        for (int i =0; i < 20; i++){
            System.out.println("\n\n");
            //c.buildCity();
            c.moving();
            c.printCity();
        }
    }
}
