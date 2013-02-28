package iaae.models;

public class Student {

	private int id;

	@Override
	public String toString() {
		return "Student [id=" + id + "]";
	}

	public Student(int id) {
		this.id = id;
	}

}
