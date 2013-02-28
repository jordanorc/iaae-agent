package iaae.models;

import java.util.ArrayList;
import java.util.List;

import com.google.common.primitives.Doubles;

public class Answer {

	private Student student;
	private List<Integer> answers;

	public Answer(Student student, int[] answers) {
		this.student = student;
		this.answers = new ArrayList<Integer>();

		for (int answer : answers) {
			this.addAnswer(answer);
		}

	}

	public void addAnswer(int answer) {
		this.answers.add(answer);
	}

	public List<Integer> getAnswers() {
		return answers;
	}

	public double[] toArray() {
		List<Integer> answers = this.getAnswers();
		return Doubles.toArray(answers);
	}

	public double checkSimilarity(Answer toCompare) {
		int total = this.getAnswers().size();
		int similar = total;
		for (int count = 0; count < total; count++) {
			if (this.getAnswers().get(count) != toCompare.getAnswers().get(
					count)) {
				similar--;
			}
		}
		return new Double(similar) / new Double(total);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

	public Student getStudent() {
		return this.student;
	}
}
