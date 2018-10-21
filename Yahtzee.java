package Yahtzee;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 Volgorde van doelstellingen:
x 1. Maak een loop in de public static void main die loopt zolang je op enter drukt, en stopt zodra je op q drukt.
x 2. Maak een losse klasse YahtzeeSpel met een methode spelen(). Verhuis de loop van 1. naar de methode spelen().
  	 Instantieer een YahtzeeSpel in de psvm en start de methode spelen.
x 3. Maak een losse methode werpen() in het YahtzeeSPel. Deze methode heeft int als return type.
  	 Zorg dat de aanroep van deze methode er voor zorgt dat er een getal tussen 1 en 6 terug komt.

 Echte dobbelstenen:
x 4. Maak de methode een onderdeel van de klasse Dobbelsteen. Maak in de klasse YahtzeeSpel een Arraylist van Dobbelstenen.
 	 In de constructor van het YahtzeeSpel voeg je 5 dobbelstenen toe aan deze ArrayList.
 	 In de loop van spelen wordt over de Arraylist heengegaan met een enhanced for-loop en werp je iedere dobbelsteen.
 	 Hou in je dobbelsteen het resultaat van de worp vast.
x 5. Maak een losse methode vasthouden(). Via een Scanner vraagt het programma om invoer.
 	 De invoer wordt via Integer.parseInt() omgezet tot een blokkeerarray van 0 en 1 bijvoorbeeld 11001.
 	 Dit blokkeerarray is een field van het YahtzeeSpel.
x 6. Loop in de tweede worp over de arraylist heen en alleen als in de blokkeerarray een 0 staat wordt de dobbelsteen opnieuw geworpen. 

 Nog meer objecten:
x 7. Maak een klasse Worp, met een veld van het type int array met lengte 5. Een methode die de worp uitslag weergeeft.
x 8. Maak een klasse Speler, met een veld worp-geschiedenis, dit is een array met een flinke lengte of een arraylist.
 9. Zorg dat aan het einde van een worp-cyclus zoals gemaakt tot stap zes, opgeslagen wordt in een Worp-object.
 	Voeg deze toe aan de worp-geschiedenis van de speler.

Meerdere spelers:
10. Maak het spel geschikt voor het spelen met twee spelers.
*/

public class Yahtzee {
	public static void main(String[] args) {
		boolean doorspelen = true;
		Scanner scanner = new Scanner(System.in);
		
		while (doorspelen) {
			System.out.println("Welkom bij Yahtzee. Wat wil je doen?\n\n1 Spelen\n2 Stoppen");
			int keuze = scanner.nextInt();				//Veranderen in enter=doorgaan en q=stoppen
			if(keuze == 1){
				YahtzeeSpel spel = new YahtzeeSpel();
				spel.spelen();
				doorspelen = false;
			} else if (keuze == 2) {
				doorspelen = false;
				System.out.println("Tot ziens");
			} else {
				System.out.println("Maak een keuze");
			}
		}
	}
}

class YahtzeeSpel{
	static ArrayList<Dobbelsteen> dobbelstenen = new ArrayList<Dobbelsteen>();
	int[] blokkeren = {0,0,0,0,0};
	
	YahtzeeSpel(){
		for(int x = 0; x<5; x++) {
			Dobbelsteen ds = new Dobbelsteen();
			dobbelstenen.add(ds);
		}
//		System.out.println(dobbelstenen);
	}
	
	void spelen(){
		System.out.println("\nWe gaan beginnen!\n\nWorp 1:");
		for(int x = 0; x<5;x++) {
			dobbelstenen.get(x).waarde = dobbelstenen.get(x).werpen();
		}
		System.out.println(dobbelstenen);
		vasthouden();
		System.out.println("\nWorp 2:");
		vervolg();
		System.out.println(dobbelstenen);
		vasthouden();
		System.out.println("\nWorp 3:");
		vervolg();
		System.out.println(dobbelstenen);
		System.out.println("\nJe beurt is voorbij. Je hebt gegooid:");
		System.out.println(dobbelstenen);
		Speler speler = new Speler();
		Worp.worpUitslag();
//		System.out.println("\nWorpgeschiedenis:\n" + speler.worpGeschiedenis);		//Komt niet goed in beeld
	}

	void vasthouden() {
		System.out.println("\nWelke dobbelstenen wil je vasthouden?");
		Scanner sc = new Scanner(System.in);
		String keuze = sc.nextLine();
		for(int x = 0; x<keuze.length(); x++) {
			char c = keuze.charAt(x);
			String s = Character.toString(c);
			int y = Integer.parseInt(s)-1;
			for(int a = 0; a<blokkeren.length; a++) {
				if(y == a) {
					blokkeren[a] = 1;
				}
			}
		}
	}
	
	void vervolg() {
		for(int x = 0; x<5;x++) {
			if(blokkeren[x] == 0) {
				dobbelstenen.get(x).waarde = dobbelstenen.get(x).werpen();
			}	
		}
	}
}

class Dobbelsteen{
	int waarde;
	
	int werpen() {
		Random random = new Random();
		waarde = random.nextInt(6)+1;
		return waarde;
	}
	@Override
	public String toString() {
		return waarde + "";
	}
}

class Worp{
	static int[] worpen = new int[5];
	
	static Worp worpUitslag() {
		Worp worp = new Worp();
		for (int x = 0 ; x < 5 ; x++) {
			worp.worpen[x] = YahtzeeSpel.dobbelstenen.get(x).waarde;
		}
		Speler speler = new Speler();
		speler.worpGeschiedenis.add(worp);
		return worp;
	}
}

class Speler{
	static ArrayList<Worp> worpGeschiedenis = new ArrayList<Worp>();
}
