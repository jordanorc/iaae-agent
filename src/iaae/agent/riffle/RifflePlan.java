package iaae.agent.riffle;

import java.util.Arrays;

import iaae.agent.creator.AgentCreatorSettings;
import iaae.models.Answer;
import iaae.ws.IAAEService;
import jadex.bdi.runtime.Plan;

public class RifflePlan  extends Plan {

	public RifflePlan() {

	}

	@Override
	public void body() {
		System.out.println("Riffle Plan");

		AgentRiffleSttings settings = ((AgentRiffleSttings) this.getBeliefbase()
				.getBelief("settings").getFact());
		
		System.out.println(settings.getAnswers());
		// inicia compatação
		for (Answer answer : settings.getAnswers()) {
			
			for (Answer to_compare : settings.getAnswers()) {
				
				System.out.println(new Similarity().calculateSimilarity(answer.toArray(), to_compare.toArray()));
			}
		}
	}
}