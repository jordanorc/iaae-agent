package iaae.agent.riffle;

import iaae.models.Answer;

import java.util.ArrayList;
import java.util.List;

public class AgentRiffleSttings {

	public AgentRiffleSttings() {
	}

	public List<Answer> getAnswers() {
		List<Answer> answers = new ArrayList<Answer>();
		answers.add(new Answer(new int[]{0, 1, 1, 1, 1, 2, 0, 0, 1, 1}));
		answers.add(new Answer(new int[]{0, 0, 0, 0, 2, 0, 0, 0, 1, 1}));
		answers.add(new Answer(new int[]{1, 1, 1, 1, 0, 1, 1, 0, 1, 1}));
		answers.add(new Answer(new int[]{2, 2, 0, 0, 2, 2, 0, 2, 2, 1}));
		answers.add(new Answer(new int[]{0, 0, 2, 1, 2, 1, 1, 2, 0, 1}));
		answers.add(new Answer(new int[]{0, 0, 2, 0, 0, 1, 2, 1, 0, 1}));
		answers.add(new Answer(new int[]{2, 1, 2, 0, 2, 1, 2, 1, 0, 1}));
		answers.add(new Answer(new int[]{0, 0, 0, 1, 0, 1, 1, 0, 2, 1}));
		answers.add(new Answer(new int[]{1, 1, 2, 0, 2, 0, 2, 0, 2, 1}));
		answers.add(new Answer(new int[]{1, 0, 0, 1, 0, 2, 1, 0, 2, 1}));
		
		return answers;
	}
}
