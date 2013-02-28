package iaae.agent.riffle;

import iaae.models.Answer;
import iaae.models.Student;
import jadex.bdi.runtime.Plan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AnswerComparator implements Comparator<Answer> {
	private Answer toCompare;

	public AnswerComparator(Answer toCompare) {
		this.toCompare = toCompare;
	}

	@Override
	public int compare(Answer o1, Answer o2) {
		// TODO Auto-generated method stub
		return new Double(o1.checkSimilarity(this.toCompare)).compareTo(o2
				.checkSimilarity(this.toCompare));
	}
}

public class RifflePlan extends Plan {

	public RifflePlan() {

	}

	@Override
	public void body() {

		System.out.println("Riffle Plan");

		AgentRiffleSttings settings = ((AgentRiffleSttings) this
				.getBeliefbase().getBelief("settings").getFact());

		int toEvaluate = ((Integer) this.getBeliefbase()
				.getBelief("toEvaluate").getFact());
		List<Answer> answers = settings.getAnswers();

		Map<Answer, Integer> totalRiffle = new HashMap<Answer, Integer>();
		for (Answer answer : answers) {
			totalRiffle.put(answer, 0);
		}

		// inicia comparação
		for (Answer answer : answers) {
			List<Answer> temp = new ArrayList(answers);
			temp.remove(answer);

			Collections.sort(temp, new AnswerComparator(answer));

			List<Answer> toCompare = new ArrayList();
			// verifica quais ainda não possuem quantidade total sorteada
			for (Answer a : temp) {
				if (totalRiffle.get(a) < toEvaluate) {
					toCompare.add(a);
					int new_total = totalRiffle.get(a) + 1;
					totalRiffle.remove(a);
					totalRiffle.put(a, new_total);
				}
				if (toCompare.size() >= toEvaluate) {
					break;
				}
			}

			Student student = answer.getStudent();

			System.out.print(student);
			System.out.print(" - ");

			for (Answer a : toCompare) {
				System.out.print(a.getStudent());
				System.out.print(" [" + a.checkSimilarity(answer) + "]");
				System.out.print(" / ");
			}
			System.out.println(toCompare.size());

		}
	}
}