package ma.enset.sequentials;

import java.util.*;

public class Population {
    List<Individual> individuals = new ArrayList<>();
    Individual firstFinest;
    Individual secondFinest;

    Random random = new Random();

    public void initializePopulation() {
        for (int i = 0; i < GAUtils.POPULATION_SIZE; i++) {
            individuals.add(new Individual());
        }
    }

    public void calculateIndividualFitness() {
        for (int i = 0; i < GAUtils.POPULATION_SIZE; i++) {
            individuals.get(i).calculateFitness();
        }
    }

    public void selection() {
        firstFinest = individuals.get(0);
        secondFinest = individuals.get(1);
    }

    public void sortPopulation() {
        Collections.sort(individuals, Collections.reverseOrder());
    }

    //croisement
    public void crossover() {
        int pointCroisement = random.nextInt(GAUtils.CHROMOSOME_SIZE-3);
        Individual individual1 = new Individual();
        Individual individual2 = new Individual();
        for (int i = 0; i < individual2.getGenes().length; i++) {
            individual1.getGenes()[i] = firstFinest.getGenes()[i];
            individual2.getGenes()[i] = secondFinest.getGenes()[i];
        }

        for (int i = 0; i < pointCroisement; i++) {
            individual1.getGenes()[i] = secondFinest.getGenes()[i];
            individual2.getGenes()[i] = firstFinest.getGenes()[i];
        }
        individuals.set(individuals.size() - 1, individual1);
        individuals.set(individuals.size() - 2, individual2);

        System.out.println(Arrays.toString(individual1.getGenes()));
        System.out.println(Arrays.toString(individual2.getGenes()));

    }

    public void mutation() {
        int index=random.nextInt(GAUtils.CHROMOSOME_SIZE);
        /*while(individuals.get(individuals.size()-2).getGenes()[index]==GAUtils.SOLUTION.charAt(index)||individuals.get(individuals.size()-1).getGenes()[index]==GAUtils.SOLUTION.charAt(index)){
            index=random.nextInt(GAUtils.CHROMOSOME_SIZE);
        }*/
        if (random.nextDouble()<GAUtils.MUTATION_PROB){
            individuals.get(individuals.size()-1).getGenes()[index]=GAUtils.CHARACTERS.charAt(random.nextInt(GAUtils.CHARACTERS.length()));
        }
        index=random.nextInt(GAUtils.CHROMOSOME_SIZE);
        if (random.nextDouble()<GAUtils.MUTATION_PROB){
            individuals.get(individuals.size()-1).getGenes()[index]=GAUtils.CHARACTERS.charAt(random.nextInt(GAUtils.CHARACTERS.length()));
        }
    }

    public Individual getBestFitnessInd() {
        return individuals.get(0);
    }
}
