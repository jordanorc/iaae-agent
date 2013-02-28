package iaae.agent.riffle;

import iaae.models.Answer;
import iaae.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AgentRiffleSttings {

	public AgentRiffleSttings() {
	}

	public List<Answer> getAnswers() {
		List<Answer> answers = new ArrayList<Answer>();
		Random generator = new Random();

		for (int i = 1; i <= 10; i++) {
			int[] choices = new int[10];

			for (int x = 0; x < 10; x++) {
				choices[x] = generator.nextInt(2);
			}

			answers.add(new Answer(new Student(i), choices));
		}

		return answers;
	}
}
