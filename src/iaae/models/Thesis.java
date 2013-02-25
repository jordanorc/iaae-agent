package iaae.models;

public class Thesis {

	private String description;
	private boolean valid;

	public Thesis(String description, boolean valid) {
		this.description = description;
		this.valid = valid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
