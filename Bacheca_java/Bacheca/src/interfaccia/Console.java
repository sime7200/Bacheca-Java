package interfaccia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import codice.Annuncio;
import codice.Bacheca;
import codice.Utente;
import codice.Utenti;
import eccezioni.EccezioneAnnuncio;
import eccezioni.EccezioneBacheca;
import eccezioni.EccezioneUtenti;
import jbook.util.Input;


/**  
 *@author Giulia Sonsino 20036727
 *@author Simone Garau 20034352
 */
public class Console {
	public static Bacheca bacheca;
	public static int scelta;
	
	public static void main(String[] args) throws EccezioneUtenti, EccezioneAnnuncio, EccezioneBacheca, FileNotFoundException, InterruptedException {
		
       Bacheca b=new Bacheca();
	   Utenti ut=new Utenti();
	   System.out.println("----BACHECA----");
	   String mailUser=null;
			
		int i=0;
		do {
		 scelta=0;
		  do {
			try { 
				System.out.println("Desideri:\n1)Sono un utente già registrato in un file\n2)Registrazione nuovo utente");
				scelta=Input.readInt();
				}catch(NumberFormatException e) {
					System.out.println("inserire un intero");
				}			
		  }while(scelta!=1 && scelta!=2);
		  
		  switch(scelta) {
			case 1:			
				try {
					System.out.println("Inserisci nome del file dove sei registrato(includere estensione .txt se presente):");
					String nomefile=Input.readString();
				    b.leggiBacheca(nomefile);
					System.out.println("Inserisci la mail con cui ti sei registrato:");
					mailUser=Input.readString();
					
					int x=0;
					for (Utente u : Utenti.utenti) {
						if(u.getEmail().equals(mailUser)) {
							System.out.println("Email verificata. Benvenuto/a nella bacheca " + u.getEmail());
							x=1;
							i=1;							
						}
					}
					if(x==0) {
						System.out.println("Email non trovata nella bacheca, devi prima registrarti");
						Bacheca.svuotaBacheca();
					}					
				}
				catch(IOException e) {
					System.out.println("File non trovato!");
				}
				catch(EccezioneAnnuncio e) {
					System.out.println("Impossibile leggere il file: durata o formato email non validi");
				}
				catch(EccezioneBacheca e) {
					System.out.println("Impossibile leggere il file: stesso codice identificativo presente più volte o parametri acquistare e vendere non validi");
				}			
				break;
				
			case 2:
				try {					
					System.out.println("Inserire email:");
					mailUser=Input.readString();
					Utente u=new Utente(mailUser);										
					ut.inserisciUtente(u);
					System.out.println("Account creato! Ciao "+mailUser);
					i=1;
				}
				catch(EccezioneUtenti e) {
					System.out.println("formato email non valido");
				}		
				break;			
			}
			}while(i==0);
				
			
			do{
				menu();
				
				do {
					try {
						scelta=Input.readInt("\nscelta: ");
						break;
					}catch(NumberFormatException e) {
						System.out.println("scelta non valida: inserire un intero");
					}
				}while(true);
				
				
				switch(scelta){
				case 0:
					try{
						System.out.println(b.elencoAnnunci());					
					}
					catch (StringIndexOutOfBoundsException e) {
						System.out.println("\nNon sono presenti annunci!\n--------------");
					}
					break;
					
				case 1: 
					try{
						System.out.println("Per inserire una parola chiave è necessario inserire identificativo dell'annuncio in cui si desidera inserirla\n(Si può inserire la parola chiave solo in un proprio annuncio)");					
						System.out.println("Inserire una parola chiave:");					
						String parola=Input.readString();
						System.out.println("Inserire identficiativo dell'annuncio in cui si desidera inserire la parola:");
						int id=Input.readInt();
						b.inserisciParolaChiave(parola,mailUser, id);
						
					}catch(EccezioneBacheca ecc) {
						System.out.println("impossibile inserire parola chiave: id e mailAutore non corrispondono a nessun annuncio");
					}
					break;
				case 2:
					System.out.println("Attenzione: la rimozione di una parola chiave comporta la rimozione di tale parola da tutti gli annunci che la contengono!");
					System.out.println("Inserire una parola chiave:");
					String parolaChiave=Input.readString();
					b.eliminaParolaChiave(parolaChiave);
					break;
				case 3:
					i=0;
					do {					
						try {
							ArrayList<Annuncio> articoliConParoleSimili=new ArrayList<Annuncio>();
							System.out.println("Inserire nome dell'articolo:");
							String nomeArticolo=Input.readString();
							System.out.println("Inserire prezzo:");
							Double prezzo=Input.readDouble();
							System.out.println("Inserire zero o piu' parole chiave separate dal simbolo -");
							String parolechiave=Input.readString();					
							int counterDurata=0;
							System.out.println("Inserire durata? Se non viene inserita viene impostata in automatico di 30 giorni. Rispondere y/n ");
							char c=Input.readChar();
							int durata=0;
							while(c!='y'&& c!='n') {
									System.out.println("Risposta non corretta: inserire y per si e n per no");
									System.out.println("Inserire durata? Se non viene inserita viene impostata in automatico di 30 giorni. Rispondere y/n ");
									c=Input.readChar();
							}
							if(c=='n') {
								counterDurata=1;
							}
							else {
								System.out.println("Inserire durata(in numero di giorni):");
								durata=Input.readInt();
							}						
							System.out.println("Inserire true se si vuole acquistare, false se si vuole vendere");
							boolean acquistare=Input.readBool();
							boolean vendere;
							if(acquistare==true) {
								vendere=false;
							}
							else {
								vendere=true;
							}	
							System.out.println("Inserire identificativo annuncio. Deve essere diverso dagli altri identificativi presenti nella bacheca e deve essere un numero intero");
							int idAnnuncio=Input.readInt();
							System.out.println("Inserire anno corrente:");
							int anno=Input.readInt();
							System.out.println("Inserire numero del mese corrente:");
							int mese=Input.readInt();
							System.out.println("Inserire numero del giorno corrente:");
							int giorno=Input.readInt();
							if(LocalDate.of(anno, mese, giorno).isEqual(LocalDate.now())==false) {
								System.out.println("Data errata! Deve essere la data di oggi");
								break;
							}
							Annuncio a;
							if(counterDurata==1) {
								a=new Annuncio(nomeArticolo, prezzo, parolechiave,acquistare, vendere, idAnnuncio, mailUser, LocalDate.of(anno, mese, giorno));
								b.inserisciAnnuncio(a);
								System.out.println("L'annuncio è stato inserito correttamente!");
							}
							else {
								a=new Annuncio(nomeArticolo, prezzo, parolechiave, durata, acquistare, vendere, idAnnuncio, mailUser, LocalDate.of(anno, mese, giorno));
								b.inserisciAnnuncio(a);
								System.out.println("L'annuncio è stato inserito correttamente!");}
							if(acquistare==true) {
								System.out.println("Lista degli articoli che hanno parole chiave che intersecano quelle specificate nell'ultimo inserimento:\n");
								articoliConParoleSimili=b.ritornaArticoliConParoleUguali(a);
								for (Annuncio a1 : articoliConParoleSimili) {
									System.out.println(a1.toString());
								}
							}			
							i=1;
							break;
					    }
						catch(NumberFormatException e) {
							System.out.println("ultimo parametro inserito non valido");							
						}
						catch(EccezioneAnnuncio ec) {
							System.out.println("impossibile aggiungere annuncio:durata o data non validi");		
						}
						catch(EccezioneBacheca e) {
							System.out.println("impossibile aggiungere annuncio: codice identificativo già presente nella bacheca");
						}
									
					}while(i==0);
					break;	
					
				case 4:
					i=0;
					do {		
						try {
							System.out.println("Inserire identificativo annuncio (si può eliminare solo propri annunci):");					
							int idAnn=Input.readInt();
							b.elimina_annuncio(idAnn, mailUser);
							i=1;
							break;
						}				
						catch(EccezioneBacheca e) {
						System.out.println("Nessun annuncio trovato");
						}				
					}while(i==0);
					break;
					
				case 5: 
					System.out.println("Inserire una o più parole chiave da voler cercare (se più di una inserirle separate dal simbolo -)");
					String parolaa=Input.readString();
					ArrayList<Annuncio> arr=b.cercaParolaChiave(parolaa);
					System.out.println("Gli annunci con la/e parola/e chiave cercata/e sono:");
					for (Annuncio an : arr) {
						System.out.println(an.toString());
					}
					break;
				case 6:
					b.EliminaArticoliScaduti();
					System.out.println("Eliminazione annunci scaduti effettuata! Gli articoli rimasti sono:\n");
					System.out.println(b.elencoAnnunci());
					break;
				case 7:
					ArrayList<Utente> listautenti=ut.getUtenti();
					System.out.println("Gli utenti registrati nella bacheca:");
					System.out.println(listautenti.toString());
					System.out.println("Ti ricordo che l'email con cui ti sei registrato è: " + mailUser);
					break;
				case 8:
					System.out.println("Inserire nome del file che verrà creato e quindi in cui verrà scritta la bacheca");
					String file=Input.readString();
					b.scriviBacheca(file);
					System.out.println("Bacheca scritta nel file correttamente!");
					break;
				case 9:					
					try {
						ut=new Utenti();
						Bacheca.svuotaBacheca();
						System.out.println("Inserisci nome del file(includere estensione .txt se presente)\n");
						String nomefile=Input.readString();					
						b.leggiBacheca(nomefile);
						System.out.println(b.elencoAnnunci());
					}
					catch(IOException e) {
						System.out.println("file non trovato");
					}
					catch(EccezioneAnnuncio e) {
						System.out.println("impossibile leggere il file: durata o formato email non validi");
					}
					catch(EccezioneBacheca e) {
						System.out.println("impossibile leggere il file: stesso codice identificativo presente più volte o parametri acquistare e vendere non validi");
					}				
					break;
					
				case 10:
					System.out.println("Sicuro di voler uscire? (y/n) ");
					do {
						String s=Input.readString();
						if(s.equalsIgnoreCase("y"))	break;
						else if(s.equalsIgnoreCase("n")) {
							scelta=0;
							break;
						}
						else {
							System.out.println("carattere non corretto, riprova");
						}
					}while(true);
					break;
				default:
					System.out.println("inserire un numero valido");
					break;
				}
			}while(scelta!=10);
			System.out.println("----Chiusura bacheca in corso----\n");
			Thread.sleep(600);
			System.out.println(".");
			Thread.sleep(700);
			System.out.println(".	.");
			Thread.sleep(800);
			System.out.println(".	.	.");
			Thread.sleep(900);
			System.out.println("----Arrivederci----\n");
			
			
			}
	

	
			private static void menu() {
				System.out.println("\nMENU:");
				System.out.println("0) visualizza annunci della bacheca\n"
						+ "1) inserire una parola chiave nella bacheca\n"
						+ "2) rimuovere una parola chiave dalla bacheca\n"
						+ "3) inserire annuncio nella bacheca\n"
						+ "4) rimuovere annuncio dalla bacheca\n"
						+ "5) cercare articolo per parola chiave\n"
						+ "6) pulire bacheca da annunci scaduti\n"
						+ "7) visualizza utenti registrati nella bacheca\n"
						+ "8) scrivere bacheca su file\n"
						+ "9) leggere bacheca da file\n"
						+ "10) esci");
			
	
}
			
			
			

			}


