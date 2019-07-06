import java.util.ArrayList;
import java.util.Random;

public class Deck {

	// met behulp van een arraylist wordt een kaartenset gecreerd
	private ArrayList<Kaart> kaarten;
	
	public Deck() {
		kaarten = new ArrayList<Kaart>();
	}
	
	public void makenDeck() {
		// iedere kleur wordt samengevoegd met een waarde om alle 52 kaarten te krijgen
		for( Kleur kaartKleur : Kleur.values()) {
			for( Waarde kaartWaarde : Waarde.values()) {
				kaarten.add(new Kaart(kaartWaarde, kaartKleur));
			}
			
		}
	}
	
	public void nieuweVolgorde() {
		// een deck veranderd de volgorde door random kaarten in een nieuwe deck te stoppen en dan de nieuwe deck als deck te gebruiken wat voor het spel wordt gebruikt
		ArrayList<Kaart> randomDeck = new ArrayList<Kaart>();
		Random random = new Random();
		int randomKaarten = 0;
		int aantalKaarten = kaarten.size();
		for( int i = 0; i < aantalKaarten; i++) {
			// Random volgorde van kaarten door nextInt((max - min) + 1) + min
			randomKaarten = random.nextInt((kaarten.size() - 1 - 0) + 1) + 0;
			randomDeck.add(kaarten.get(randomKaarten));
			kaarten.remove(randomKaarten);
		}
		kaarten = randomDeck;
	}
	
	public void verwijderKaart( int i) {
		kaarten.remove(i);
	}
	
	public Kaart getKaart(int i) {
		return this.kaarten.get(i);
	}
	
	public int grotteDeck() {
		return this.kaarten.size();
	}

	public void voegtoeKaart( Kaart voegtoeKaart ) {
		this.kaarten.add( voegtoeKaart );
	}
	
	public void neemKaart(Deck weggenomen) {
		this.kaarten.add(weggenomen.getKaart(0));
		weggenomen.verwijderKaart(0);
	}
	
	// om alle kaarten naar een spel terug te brengen naar het deck
	public void alleKaartenTerug( Deck terug ) {
		int oudeDeckGrote = this.kaarten.size();
	
		for( int i = 0; i < oudeDeckGrote; i++) {
			terug.voegtoeKaart(this.getKaart(i));
		}
		
		for( int i = 0; i < oudeDeckGrote; i++) {
			this.verwijderKaart(0);
		}
	}
	
	// hierbij worden de punten van de kaarten bepaald en toegevoegd aan de totaal aantal punten van een speler
	public int puntenKaart() {
		int totaalPunten = 0;
		int aas = 0;
		
		for(Kaart kaart : kaarten) {
			switch( kaart.getWaarde()) {
			case TWEE: totaalPunten +=2; break;
			case DRIE: totaalPunten +=3; break;
			case VIER: totaalPunten +=4; break;
			case VIJF: totaalPunten +=5; break;
			case ZES: totaalPunten +=6; break;
			case ZEVEN: totaalPunten +=7; break;
			case ACHT: totaalPunten +=8; break;
			case NEGEN: totaalPunten +=9; break;
			case TIEN: totaalPunten +=10; break;
			case BOER: totaalPunten +=10; break;
			case VROUW: totaalPunten +=10; break;
			case HEER: totaalPunten +=10; break;
			case AAS: aas +=1; break;
			}
		}
	// voor aas wordt ernaar gekeken of de totaal aantal punten niet al 11 of groter is omdat de speler anders boven 21 gaat scoren.
	// als totaal punten al 11 of groter zijn dan wordt aas als 1 punt gezien en anders als 11
	for( int i = 0; i < aas; i++) {
		
		if(totaalPunten >= 11) {
			totaalPunten += 1;
		}
		else {
			totaalPunten += 11;
		}
	}
	
	return totaalPunten;
	}

// hiermee worden de kaarten uitgeprint
	public String toString() {
		String alleKaarten = "";
		for(Kaart kaart : kaarten) {
			alleKaarten += "\n" + kaart.toString();
		}
		return alleKaarten;
	}
}
