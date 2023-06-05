package ma.enset.smaParallel;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;

public class MainContainer {
    public static void main(String[] args) throws jade.wrapper.ControllerException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.GUI, "true");
        AgentContainer agentContainer = runtime.createMainContainer(profile);
        agentContainer.start();

    }
}
