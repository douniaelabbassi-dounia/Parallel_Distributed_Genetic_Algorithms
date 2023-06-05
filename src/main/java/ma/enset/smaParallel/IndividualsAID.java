package ma.enset.smaParallel;


import jade.core.AID;

public class IndividualsAID implements Comparable {
    private String genes;

    private int fitness;

    private AID aid;

    public IndividualsAID() {
    }

    public IndividualsAID(String genes, int fitness, AID aid) {
        this.genes = genes;
        this.fitness = fitness;
        this.aid = aid;
    }

    public String getGenes() {
        return genes;
    }

    public void setGenes(String genes) {
        this.genes = genes;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public AID getAid() {
        return aid;
    }

    public void setAid(AID aid) {
        this.aid = aid;
    }

    @Override
    public int compareTo(Object o) {
        IndividualsAID individual = (IndividualsAID) o;

        if (this.fitness > individual.fitness)
            return 1;
        else if (this.fitness < individual.fitness)
            return -1;
        return 0;
    }
}
