package iaae.models;

public class Property {

	private String property;
	private String template;

	@Override
	public String toString() {
		return "Property [property=" + property + ", template=" + template
				+ "]";
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}
