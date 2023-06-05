package ma.enset.smaParallel;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MasterAgent extends jade.core.Agent {
    private static List<Individual> individus = new ArrayList<>();

    public static List<Individual> getIndividus() {
        return individus;
    }


    @Override
    protected void setup() {
        List<IndividualsAID> individualsAIDS = new ArrayList<>();
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receiver = receive();

                if (receiver != null){
                    String[] messageRecu = receiver.getContent().split("===");
                    String genes = messageRecu[0];
                    int fitness = Integer.parseInt(messageRecu[1]);
                    AID sender = receiver.getSender();

                    IndividualsAID individuals = new IndividualsAID(genes, fitness, sender);
                    individualsAIDS.add(individuals);

                    Collections.sort(individualsAIDS, Collections.reverseOrder());

                    System.out.println("The best individus is === " + individualsAIDS.get(0).getGenes() +
                            " with Fitness === " + individualsAIDS.get(0).getFitness() +
                            " give by Agent === "+ individualsAIDS.get(0).getAid().getLocalName());

                } else block();
            }
        });



    }
}
