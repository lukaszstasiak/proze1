import java.io.Serializable;


public class Wyniki implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4257299745728119954L;
	private String id;
	private int wynik;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getWynik() {
		return wynik;
	}

	public void setWynik(int wynik) {
		this.wynik = wynik;
	}

	public Wyniki(String id, int wynik){
		this.id = id;
		this.wynik = wynik;
		
	}

	@Override
	public String toString() {
		return "" + id + ":\t" + wynik;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + wynik;
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
		Wyniki other = (Wyniki) obj;
		if (wynik != other.wynik)
			return false;
		return true;
	}
}
