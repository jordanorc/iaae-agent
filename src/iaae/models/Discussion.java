package iaae.models;

public class Discussion {

	private int pk;
	private String category;
	private int number_theses;

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public int getNumber_theses() {
		return number_theses;
	}

	public void setNumber_theses(int number_theses) {
		this.number_theses = number_theses;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
