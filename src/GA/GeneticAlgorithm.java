package GA;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {

    private Chromossome[] population = new Chromossome[10];
    Random r = new Random();
    private int fitness;

    public GeneticAlgorithm(){
        for (int i = 0; i < 10; i++) {
            population[i] = new Chromossome();
            for(int j=0; j<12; j++){
                population[i].lightTimes[j] = r.nextInt(1000);
            }
        }

        for (Chromossome c: population) {
            c.congsNum = r.nextInt(100);
            System.out.println(c.congsNum);
        }
    }


    public void run(){
        //ORDENAÇÃO
        for (int i = 0; i < population.length; i++) {
            for(int j = 0; j < population.length; j++){
                if(population[i].congsNum > population[j].congsNum){
                    Chromossome aux = population[i];
                    population[i] = population[j];
                    population[j] = aux;
                }
            }
        }

        for(int i =0; i < population.length; i++){
            crossover(population[r.nextInt(5)], population[r.nextInt(5)+5]);
        }

//        int roulette = r.nextInt(population.length/2);
//
//        for(int i = 0; i < elite; i++){
//
//        }
    }

    public Chromossome roulette(){

        calcFitness();

        int[] roulette = new int[100];
        int idx = 0;
        int num = 0;

        System.out.println("");
        for (Chromossome c: population) {
            System.out.print(100*((fitness - (float)c.congsNum)/(float)fitness) + " ");
            for(int i =idx; i < 100 -((c.congsNum/fitness)*100); i++){
                roulette[i] = num;
                idx++;
            }
            num++;
            System.out.println("");
        }

//        for (int i: roulette) {
//            System.out.println(i);
//        }


        return population[roulette[r.nextInt(100)]];
    }

    public void crossover(Chromossome c1, Chromossome c2){
        int point = r.nextInt(12);
        for(int i = point; i < 12; i++){
            int aux = c1.lightTimes[i];
            c1.lightTimes[i] = c2.lightTimes[i];
            c2.lightTimes[i] = aux;
        }
    }

    public void mutation(Chromossome c){
        c.lightTimes[r.nextInt(12)] = r.nextInt(1000);
    }

    public void newGeneration(){

    }

    public void calcFitness(){
        this.fitness = 0;
        for (Chromossome c: population) {
            this.fitness += c.congsNum;
        }
    }


}
