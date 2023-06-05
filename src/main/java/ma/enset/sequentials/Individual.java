package ma.enset.sequentials;

import java.util.Random;

public class Individual implements Comparable{
    //chromosome
    private char genes[] = new char[GAUtils.CHROMOSOME_SIZE];
    private int fitness;

    public Individual() {
        Random rnd = new Random();
        for (int i = 0; i < genes.length; i++) {
            genes[i] = GAUtils.CHARACTERS.charAt(rnd.nextInt(GAUtils.CHARACTERS.length()));
        }
    }

    public void calculateFitness() {
        fitness = 0;
        for (int i = 0; i < genes.length; i++) {
            if (genes[i] == GAUtils.SOLUTION.charAt(i))
                fitness++;
        }
    }

    @Override
    public int compareTo(Object o) {
        Individual individual = (Individual) o;

        if (this.fitness > individual.fitness)
            return 1;
        else if (this.fitness < individual.fitness)
            return -1;
        return 0;
    }

    public char[] getGenes() {
        return genes;
    }

    public void setGenes(char[] genes) {
        this.genes = genes;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
}
