package iaae.agent.creator;

import iaae.ws.IAAEService;
import jadex.bdi.runtime.IGoal;
import jadex.bdi.runtime.Plan;

public class CheckDiscussionPlan extends Plan {
	IAAEService service = new IAAEService();

	public CheckDiscussionPlan() {
		service = ((AgentCreatorSettings) this.getBeliefbase()
				.getBelief("settings").getFact()).getService();
	}

	@Override
	public void body() {
		System.out.println("Verificando se existem discussões a serem geradas");
		if (!service.getDiscussions().isEmpty()) {
			this.getBeliefbase().getBelief("has_discussions").setFact(true);

			/*IGoal go = createGoal("go");
			go.getParameter("pos").setValue(newpos);*/
		}

	}
}
