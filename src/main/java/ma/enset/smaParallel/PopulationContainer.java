package ma.enset.smaParallel;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;


public class PopulationContainer {
    public static void main(String[] args) throws jade.wrapper.ControllerException {

        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);
        AgentController island1 = agentContainer.createNewAgent("island10", "ma.enset.smaParallel.IslandAgent", new Object[]{});
        island1.start();


    }
}
