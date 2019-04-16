package Default;

import GA.Chromossome;
import GA.GeneticAlgorithm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {


    public static void main(String args[]){
        //City c = new City();
        GeneticAlgorithm ga = new GeneticAlgorithm();
        Chromossome best;

        ga.roulette();


        for (int i =0; i < 100; i++){
            //System.out.println("\n\n");
            //c.moving();
            //c.printCity();
        }
    }
}
