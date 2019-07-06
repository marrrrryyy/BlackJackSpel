import java.util.Scanner;

public class BlackJack {

	public static void main(String[] args) {
		System.out.println("BlackJack");

		// een nieuw deck wordt gecreerd en altijd weer opnieuw de volgorde random bepaald
		Deck spelDeck = new Deck();
		spelDeck.makenDeck();
		spelDeck.nieuweVolgorde();
		
		// er wordt ruimte gecreerd voor de speler en de bank zodat deze kaarten in hun "hand" kunnen nemen
		Deck spelerHand = new Deck();
		Deck bankHand = new Deck();
		
		// er wordt gevraagd wat de speler wil doen en afhankelijk van de keuze wordt of gespeld of het spel gestopt
		System.out.println("----------");
		System.out.println("Wat wil je doen? Typ een van de letters in: ");
		System.out.println("'s' om te SPELEN");
		System.out.println("'q' om te STOPPEN");
		
		// de keuze kan worden ingetypt
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();

		// zolang de speler niet "q" intypt wordt gespeeld
		while( !input.equals("q")) {
			
			boolean eindRonde = false;
			
			// zowel de speler als ook de bank nemen 2 kaarten
			spelerHand.neemKaart(spelDeck);
			spelerHand.neemKaart(spelDeck);
			
			bankHand.neemKaart(spelDeck);
			bankHand.neemKaart(spelDeck);
			
			while( true ) {
				// er wordt getoond wat de speler en de bank hebben gekregen en vervolgens wat de speler wil doen
				System.out.println("Je hebt de volgende kaarten in je hand: ");
				System.out.println(spelerHand.toString());
				System.out.println("Je hebt " + spelerHand.puntenKaart() + " punten");
				
				System.out.println("De bank heeft de volgende kaarten: " + bankHand.getKaart(0).toString() + " en een tweede verdekte Kaart.");
				
				// er wordt gevraagd wat de speler wil doen en afhankelijk van de keuze gaat de speler of nog een kaart nemen, afwachten of kan het spel beeindigen
				System.out.println("----------");
				System.out.println("Wat wil je doen?");
				System.out.println("k. Hit");
				System.out.println("p. Pas");
				System.out.println("q. Quit Game");
				
				String keuze = scanner.nextLine();
				
				// als de speler ervoor kiest om nog een kaart te pakken wordt een kaart van het deck toegevoegd aan de hand van de speler en getoond wat de speler nu in de hand heeft
				// verder worden de punten van de nieuwe kaart bijgeteld
				// verder wordt gecontroleerd of de punten boven de 21 zijn
				if( keuze.equals("k")) {
					spelerHand.neemKaart(spelDeck);
					System.out.println("Je hebt een nieuwe kaart: " + spelerHand.getKaart(spelerHand.grotteDeck() - 1 ).toString());
					// zodra de punten over 21 zijn verliest de speler, de ronde wordt beeindigd en het spel begint opnieuw
					if(spelerHand.puntenKaart() > 21) {
						System.out.println("Je punten zijn: " + spelerHand.puntenKaart());
						System.out.println("Dit is over 21. Dus je hebt helaas verloren!");
						eindRonde = true;
						break;
					}
				}
				// als de speler geen nieuwe kaart wil dan wordt afgewacht wat de bank heeft om te bepalen wie meer punten heeft
				if( keuze.equals("p")) {
					break;
				}
				// als de speler voor "q" kiest dan wordt de ronde beeindigd
				if( keuze.equals("q")) {
					break;
				}
				
			}
			// als de bank meer punten heeft dan de speler dan wint de bank en de ronde wordt beeindigd
			System.out.println("Kaarten van de bank: " + bankHand.toString());
			
			if( (bankHand.puntenKaart() > spelerHand.puntenKaart()) && eindRonde == false) {
				System.out.println("Bank heeft gewonnen!");
				eindRonde = true;
			}
			// zolang de bank onder 17 punten is gaat het voor een nieuwe kaart kiezen
			while((bankHand.puntenKaart() < 17) && eindRonde == false ) {
				bankHand.neemKaart(spelDeck);
				System.out.println("Bank heeft een nieuwe kaart genomen: " + bankHand.getKaart(bankHand.grotteDeck() - 1).toString());
			}
			System.out.println("De bank heeft " + bankHand.puntenKaart() + " punten.");
			
			// als de bank meer dan 21 punten heeft wint de speler
			if((bankHand.puntenKaart() > 21) && eindRonde == false) {
				System.out.println("De bank heeft meer dan 21 punten! Dus jij wint!");
				eindRonde = true;
			}
			// als de bank net zoveel punten heeft als de speler dan wint niemand
			if((spelerHand.puntenKaart() == bankHand.puntenKaart()) && eindRonde == false) {
				System.out.println("De bank heeft net zoveel punten als jij. Dus niemand wint.");
				eindRonde = true;
			}
			// de speler wint als hij of zij meer punten heeft dan de bank
			if((spelerHand.puntenKaart() > bankHand.puntenKaart()) && eindRonde == false) {
				System.out.println("Je hebt meer punten dan de bank! Je hebt gewonnen!");
				eindRonde = true;
			}
			
			// de kaarten worden weer teruggestopt in het deck en een nieuwe ronde begint
			spelerHand.alleKaartenTerug(spelDeck);
			bankHand.alleKaartenTerug(spelDeck);
			System.out.println("Einde");
			System.out.println("----------");
			System.out.println("Nog een keer spelen!");
		} 
	}

}
