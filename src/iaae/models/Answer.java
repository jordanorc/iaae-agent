package iaae.models;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.atlas.lib.ArrayUtils;

import com.google.common.primitives.Doubles;

public class Answer {

	private List<Double> answers;

	public Answer(int[] answers) {
		this.answers = new ArrayList<Double>();
		
		for (int answer : answers) {
			this.addAnswer(answer);
		}
		
	}

	public void addAnswer(int answer) {
		this.answers.add((double) answer);
	}

	public List<Double> getAnswers() {
		return answers;
	}
	
	public double[] toArray() {
		List<Double> answers = this.getAnswers();
		return Doubles.toArray(answers);
	}
}
