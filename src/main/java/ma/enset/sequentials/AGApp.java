package ma.enset.sequentials;

import java.util.Arrays;

public class AGApp {
    public static void main(String[] args) {
        Population population=new Population();
        population.initializePopulation();
        population.calculateIndividualFitness();
        population.sortPopulation();
        int it=0;
        System.out.println("Chromosome :"+ Arrays.toString(population.getBestFitnessInd().getGenes())+" fitness :"+population.getBestFitnessInd().getFitness());

        while (it<GAUtils.MAX_IT && population.getBestFitnessInd().getFitness()<GAUtils.CHROMOSOME_SIZE){
            population.selection();
            population.crossover();
            population.mutation();
            population.calculateIndividualFitness();
            population.sortPopulation();
            System.out.println("It :"+it+"Chromosome :"+Arrays.toString(population.getBestFitnessInd().getGenes())+" fitness :"+population.getBestFitnessInd().getFitness());

            it++;
        }
    }
}
