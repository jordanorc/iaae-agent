package iaae;

import iaae.models.Answer;
import jadex.base.Starter;

import java.util.Comparator;

/**
 * Example that shows how an agent model can be created via the editable model
 * api. This model can be registered with a name at the BDI agent factory and
 * instances can be created based on this new model.
 */
class AnswerComparator implements Comparator<Answer> {
	private Answer toCompare;

	public AnswerComparator(Answer toCompare) {
		this.toCompare = toCompare;
	}

	@Override
	public int compare(Answer o1, Answer o2) {
		// TODO Auto-generated method stub
		return new Double(o2.checkSimilarity(this.toCompare)).compareTo(o1
				.checkSimilarity(this.toCompare));
	}
}

public class AppStarter {
	/**
	 * Main for starting hello world agent.
	 */
	public static void main(String[] args) {

		Starter.main(new String[] { "-gui", "false", "-component",
				"iaae/agent/riffle/AgentRiffle.agent.xml" });

	}
}
