package iaae.models;

public class Tuple<T1, T2> {
	private T1 f1;
	private T2 f2;

	public Tuple(T1 f1, T2 f2) {
		this.f1 = f1;
		this.f2 = f2;
	}

	public T1 getF1() {
		return f1;
	}

	public T2 getF2() {
		return f2;
	}

	public void setF1(T1 f1) {
		this.f1 = f1;
	}

	public void setF2(T2 f2) {
		this.f2 = f2;
	}

	@Override
	public String toString() {
		return "Tuple [f1=" + f1 + ", f2=" + f2 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((f1 == null) ? 0 : f1.hashCode());
		result = prime * result + ((f2 == null) ? 0 : f2.hashCode());
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
		Tuple other = (Tuple) obj;
		if (f1 == null) {
			if (other.f1 != null)
				return false;
		} else if (!f1.equals(other.f1))
			return false;
		if (f2 == null) {
			if (other.f2 != null)
				return false;
		} else if (!f2.equals(other.f2))
			return false;
		return true;
	}
}
