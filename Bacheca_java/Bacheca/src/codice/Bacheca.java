package codice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import eccezioni.EccezioneAnnuncio;
import eccezioni.EccezioneBacheca;
import eccezioni.EccezioneUtenti;

/**  
 *@author Giulia Sonsino 20036727
 *@author Simone Garau 20034352
 */

public class Bacheca implements Iterable<Annuncio> {

	private static ArrayList<Annuncio> bacheca;
	public ArrayList<Annuncio> articoliConParoleSimili;
	Utenti utenti=new Utenti();
	

	public Bacheca() {
		bacheca=new ArrayList<Annuncio>();	
	}
	
	/**
	 * <p>
	 * restituisce il numero di annunci presenti nella bacheca
	 * </p>
	 */
	public int dimensioneBacheca() {
		return bacheca.size();
	}



	public static ArrayList<Annuncio> getBacheca() {
		return bacheca;
	}
	
	
	/**
	 * <p>
	 * inserisce un annuncio nella bacheca
	 * </p>
	 * @param a è l'annuncio che si vuole inserire
	 * @exception lancia un'eccezione se è impossibile aggiungere tale annuncio
	 */
	public void inserisciAnnuncio(Annuncio a) throws EccezioneBacheca {
		for (Annuncio ann : bacheca) {
			if(ann.getId_annuncio()==a.getId_annuncio()) {
				throw new EccezioneBacheca("impossibile aggiungere tale annuncio poichè il suo codice identificativo è già presente nella bacheca");
				
			}
			if(ann.isAcquistare()==ann.isVendere()) {
				throw new EccezioneBacheca("impossibile aggiungere tale annuncio poichè i parametri acquistare e vendere sono errati");
			}
		}
		bacheca.add(a);
	}
	
	public void inserisciAnnuncio(String nome,double prezzo,String parole_chiave, int durata, boolean acquistare, boolean vendere,int id_annuncio, String autore_annuncio, LocalDate data) throws EccezioneAnnuncio, EccezioneBacheca{
		for (Annuncio ann : bacheca) {
			if(ann.getId_annuncio()==id_annuncio) {
				throw new EccezioneBacheca("impossibile aggiungere tale annuncio poichè il suo codice identificativo è già presente nella bacheca");
				
			}
			if(ann.isAcquistare()==ann.isVendere()) {
				throw new EccezioneBacheca("impossibile aggiungere tale annuncio poichè i parametri acquistare e vendere sono errati");
			}
		}
		bacheca.add(new Annuncio(nome,prezzo,parole_chiave,durata, acquistare, vendere, id_annuncio, autore_annuncio, data));
	}
	
	/**
	 * <p>
	 *  ritorna una lista avente articoli con parole chiave che intersecano quelle dell'annuncio passato come input
	 * </p>
	 * @param a è l'annuncio di cui si vogliono trovare gli annunci aventi parole chiave uguali
	 */
	public ArrayList<Annuncio> ritornaArticoliConParoleUguali(Annuncio a) {	
		articoliConParoleSimili=new ArrayList<Annuncio>();
		for (String s : a.getParole_chiave()) {
			for (Annuncio x: bacheca) {
				if(x.getParole_chiave().contains(s) && x.getId_annuncio()!=a.getId_annuncio()) {
					articoliConParoleSimili.add(x);
			    }
			}
		}
		return articoliConParoleSimili;
	}

	public static void svuotaBacheca() {
		bacheca.clear();
	}
	
	/**
	 * <p>
	 * elimina un annuncio dalla bacheca
	 * </p>
	 * @param identificatore è il codice dell'annuncio da voler rimuovere
	 * @param mail è l'email dell'autore che ha creato l'annuncio e che ora desidera rimuoverlo
	 * @exception lancia un'eccezione se è impossibile eliminare tale annuncio
	 */
	public void elimina_annuncio(int identificatore, String mail) throws EccezioneBacheca 
	{
		for(int i=0;i<bacheca.size();i++){
			Annuncio c=bacheca.get(i);
			if(c.getId_annuncio()==identificatore && c.getMailAutore().equals(mail)){
				bacheca.remove(c);
				return;
			}
		}
		throw new EccezioneBacheca("Nessun annuncio trovato");
	}
	
	/**
	 * <p>
	 * elimina una parola chiave da tutti gli annunci che la contengono
	 * </p>
	 * @param parolaChiave è la parola da voler eliminare
	 */
	public void eliminaParolaChiave(String parolaChiave) {
		for (Annuncio a : bacheca) {			
			a.getParole_chiave().remove(parolaChiave);  
		}
	} 
	
	
	/**
	 * <p>
	 * inserisce una parola chiave in un annuncio
	 * </p>
	 * @param parolaChiave è la parola da voler inserire
	 * @param mailAutore è l' email dell'autore di tale annuncio
	 * @param id è il codice identificativo dell'annuncio in cui si desidera inserire la parola
	 * @exception lancia un'eccezione se è impossibile inserire tale parola
	 */
	public void inserisciParolaChiave(String parolaChiave, String mailAutore, int id) throws EccezioneBacheca {
		for (Annuncio a : bacheca) {
			if(a.getId_annuncio()==id && a.getMailAutore().equals(mailAutore)) {
				a.getParole_chiave().add(parolaChiave);
				return;
			}
			
		}
		throw new EccezioneBacheca("impossibile inserire parola chiave: id e mailAutore non corrispondono a nessun annuncio");
	}
	
	/**
	 * <p>
	 * restituisce una lista di annunci che contengono una o più parole chiave
	 * </p>
	 * @param parole è la stringa contenente le parole da voler cercare 
	 */
	public ArrayList<Annuncio> cercaParolaChiave(String parole){
		ArrayList<Annuncio> listaAnnunci=new ArrayList<Annuncio>();
		String parolechiave[]= parole.split("-");
		for (String s :parolechiave) {
			for (Annuncio a : bacheca) {
				if(a.getParole_chiave().contains(s)) {
					listaAnnunci.add(a);
				}
			}
		}
		return listaAnnunci;
	}
	
	
	/**
	 * <p>
	 * elimina gli annunci scaduti
	 * </p>
	 */
	public void EliminaArticoliScaduti() {	
		LocalDate DataOggi=LocalDate.now();
		Iterator<Annuncio> it = Bacheca.getBacheca().iterator();
		  while(it.hasNext()) {
			  Annuncio an = it.next();
			  if(DataOggi.isAfter(an.getData().plusDays(an.getDurata()))) {
					it.remove();
				}
		  }	
	}
	
	/**
	 * <p>
	 * legge una bacheca da un file
	 * </p>
	 * @param fileName è il nome del file
	 * @exception lancia un'eccezione nel caso si verifichino problemi con la lettura del file
	 */
	public void leggiBacheca(String fileName) throws IOException, EccezioneAnnuncio, EccezioneUtenti, EccezioneBacheca {
		 BufferedReader in=new BufferedReader(new FileReader(fileName));
		 String s = in.readLine();
		 while(s!=null)
		 {
			 String[] dati=s.split(",");
			 if(dati.length>0)
			 {
				 
				    
					String nome = dati[0].trim();
					double prezzo=Double.parseDouble(dati[1]);
					String parolechiave= dati[2].trim();
					int durata= Integer.parseInt(dati[3]);
					boolean acquistare=Boolean.parseBoolean(dati[4]);
					boolean vendere=Boolean.parseBoolean(dati[5]);
					int id_annuncio= Integer.parseInt(dati[6]);
					String mailUtente = dati[7].trim();
					LocalDate data=LocalDate.parse(dati[8]);
					Utente u = new Utente(mailUtente);
					utenti.inserisciUtente(u);
					this.inserisciAnnuncio(new Annuncio(nome,prezzo,parolechiave,durata,acquistare,vendere,id_annuncio,mailUtente,data));
			 }			 
			 s=in.readLine();		 
		 }
		 in.close();
	}
	
	/**
	 * <p>
	 * restituisce l'elenco degli annunci presenti nella bacheca
	 * </p>
	 */
	public String elencoAnnunci()
	{
		String s = new String();
		for(Annuncio tmp : bacheca)
			s += tmp.toString()+"\n";
		return s.substring(0, s.length()-1);
	}
	
	
	/**
	 * <p>
	 * restituisce gli annunci con parametri separati da virgola. Metodo usato da scriviBacheca()
	 * </p>
	 */
	public String scritturaAnnunci() {
		String s=new String();
		for(Annuncio tmp: bacheca) {
			String nomee=tmp.getNome();
			Double prezzo=tmp.getPrezzo();
			ArrayList<String> parole=tmp.getParole_chiave();
			String parole2=new String();
			for (String st : parole) {
				parole2+=st+"-";
			}
			String str =parole2.substring(0, parole2.length()-1);
			int durata=tmp.getDurata();
			boolean ac=tmp.isAcquistare();
			boolean ve=tmp.isVendere();
			int id=tmp.getId_annuncio();
			String mail=tmp.getMailAutore();
			LocalDate data=tmp.getData();
			s+=nomee+","+prezzo+","+str+","+durata+","+ac+","+ve+","+id+","+mail+","+data+"\n";
					
		}
		return s;
			
	}
	
	/**
	 * <p>
	 * scrive una bacheca su un file
	 * </p>
	 * @param nomeFile è il nome del file su cui si vuole scrivere la bacheca
	 */

	public void scriviBacheca(String nomeFile) throws FileNotFoundException  {

		PrintWriter out = new PrintWriter(new File(nomeFile));
		out.printf(this.scritturaAnnunci());
		out.close();
	
}


	@Override
	public Iterator<Annuncio> iterator() {
		return Bacheca.bacheca.iterator();
		
	}
	
}
	
	
 
