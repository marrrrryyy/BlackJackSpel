// een kaart wordt samengesteld uit een kleur en een waarde
public class Kaart {

	private Waarde waarde;
	private Kleur kleur;
	
	public Kaart( Waarde waarde, Kleur kleur ) {
		this.waarde = waarde;
		this.kleur = kleur;
	}
	public String toString() {
		return kleur.toString() + " " + waarde.toString();
	}

	public Waarde getWaarde() {
		return this.waarde;
	}
}
