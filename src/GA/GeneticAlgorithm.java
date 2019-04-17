package GA;

import java.util.Random;

public class GeneticAlgorithm {

    public Chromossome[] population = new Chromossome[30];
    Random r = new Random();
    private int fitness;

    public GeneticAlgorithm(){
        for (int i = 0; i < population.length; i++) {
            population[i] = new Chromossome();
            for(int j=0; j<12; j++){
                population[i].lightTimes[j] = r.nextInt(300);
            }
        }
    }

    public void sort(){
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
    }

    public void run(){


        //System.out.println(population[0].congsNum);

        int len = this.population.length/2;

        Chromossome[] newPop = new Chromossome[population.length];
        for(int i =0; i < population.length; i++){
            newPop[i] = crossover(population[r.nextInt(len)], population[r.nextInt(len)+len]);
        }

        population = newPop;


        for (Chromossome c: population) {
            c.congsNum = 0;
            if(r.nextBoolean()){
                mutation(c);
            }
        }

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


        return population[roulette[r.nextInt(100)]];
    }

    public Chromossome crossover(Chromossome c1, Chromossome c2){
        Chromossome newChr = new Chromossome();
        newChr.congsNum = 0;
        for(int i = 0; i < 12; i++){
            if(r.nextInt(100)<70){
                newChr.lightTimes[i] = c1.lightTimes[i];
            }else{
                newChr.lightTimes[i] = c2.lightTimes[i];
            }
        }
        return newChr;
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
