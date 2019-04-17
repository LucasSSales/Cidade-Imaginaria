package Default;

import GA.Chromossome;
import GA.GeneticAlgorithm;


public class Main {

    public static void main(String args[]){
        GeneticAlgorithm ga = new GeneticAlgorithm();
        City c = new City();
        c.buildCity();
        c.putCars();
        Chromossome best = new Chromossome();
        best.congsNum = Integer.MAX_VALUE;
        int var = Integer.MAX_VALUE;
        int cont = 0;

        for(int gen=0; gen<150; gen++){
            for (Chromossome chromossome: ga.population) {
                c.putTrafficLights(chromossome.lightTimes);
                int congs = 0;
                for (int i =0; i < 1000; i++){
                    congs += c.moving();
                }
                chromossome.congsNum = congs;
                c.resetMoviment();
            }
            ga.sort();
            if(best.congsNum > ga.population[0].congsNum){
                best = ga.population[0];
            }else{
                cont++;
            }
            ga.run();
            if(cont == 50)
                break;
        }

        System.out.println("BEST");
        System.out.println(best.congsNum);
    }
}
