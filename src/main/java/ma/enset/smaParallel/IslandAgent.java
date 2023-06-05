package ma.enset.smaParallel;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.lang.acl.ACLMessage;
import ma.enset.sequentials.GAUtils;

import java.util.*;

public class IslandAgent extends Agent {
    List<Individual> individuals = new ArrayList<>();
    Random random = new Random();
    private Individual firstFinest;
    private Individual secondFinest;
    @Override
    protected void setup() {

        initialize_population();
        calculateIndividualFitness();
        sortPopulation();

        SequentialBehaviour sequentialBehaviour = new SequentialBehaviour();

        sequentialBehaviour.addSubBehaviour(new OneShotBehaviour() {
            int it = 0;
            @Override
            public void action() {
                while (it < GAUtils.MAX_IT || getBestFitnessInd().getFitness() != GAUtils.MAX_FITNESS){
                    selection();
                    crossover();
                    mutation();
                    calculateIndividualFitness();
                    sortPopulation();
                    System.out.println("it === "+ it);
                    System.out.println(getBestFitnessInd().getGenes());
                    System.out.println(getBestFitnessInd().getFitness());
                    it++;
                }
            }
        });

        sequentialBehaviour.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                ACLMessage aclMessage = new ACLMessage(ACLMessage.QUERY_IF);
                System.out.println(Arrays.toString(getBestFitnessInd().getGenes()));

                aclMessage.setContent(Arrays.toString(getBestFitnessInd().getGenes()) + "===" + getBestFitnessInd().getFitness());
                aclMessage.addReceiver(new AID("master", AID.ISLOCALNAME));
                send(aclMessage);
            }
        });

        addBehaviour(sequentialBehaviour);


    }

    public void initialize_population(){
        for (int i = 0; i< GAUtils.POPULATION_SIZE; i++){
            individuals.add(new Individual());
        }
    }

    // Cette méthode va permettre de calculer le fitness d'un individu
    public void calculateIndividualFitness() {
        for (int i = 0; i < GAUtils.POPULATION_SIZE; i++) {
            individuals.get(i).calculateFitness();
        }
    }

    public void sortPopulation() {
        Collections.sort(individuals, Collections.reverseOrder());
    }

    // selection des meilleures individus
    public void selection() {
        firstFinest = individuals.get(0);
        secondFinest = individuals.get(1);
    }

    // croisement des meilleurs individus
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
