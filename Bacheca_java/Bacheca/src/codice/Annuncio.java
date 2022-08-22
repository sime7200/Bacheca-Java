package codice;

import java.time.LocalDate;
import java.util.ArrayList;

import eccezioni.EccezioneAnnuncio;

/**  
 *@author Giulia Sonsino 20036727
 *@author Simone Garau 20034352
 */

public class Annuncio {

	
	private String nome;
	private double prezzo; 	
	private ArrayList<String> parole_chiave;
	private int durata;
	private boolean acquistare; 
	private boolean vendere;
	private int id_annuncio;
	private String mailAutore;
	private LocalDate data;
	
	
	/**
	 * <p>
	 * costruttore di annuncio
	 * </p>
	 */
	public Annuncio(String nome,double prezzo,String parolechiave, int durata, boolean acquistare, boolean vendere, int id_annuncio, String autore_annuncio, LocalDate data) throws EccezioneAnnuncio{
		this.nome = nome;
		this.prezzo = prezzo;
		this.ArrayListParole(parolechiave);
		this.setDurata(durata);
		this.acquistare = acquistare;
		this.vendere = vendere;
		this.id_annuncio= id_annuncio;
		this.mailAutore=autore_annuncio;
		setData(data);
	}
	
	/**
	 * <p>
	 * costruttore di annunio senza durata, la quale viene impostata a 30 giorni automaticamente
	 * </p>
	 */
	public Annuncio(String nome, double prezzo,String parolechiave, boolean acquistare, boolean vendere, int id_annuncio, String autore_annuncio, LocalDate data) throws EccezioneAnnuncio {
		this.nome = nome;
		this.prezzo = prezzo;
		this.ArrayListParole(parolechiave);
		this.durata = 30;
		this.acquistare = acquistare;
		this.vendere = vendere;
		this.id_annuncio= id_annuncio;
		this.mailAutore=autore_annuncio;
		setData(data);
	}
	
	/**
	 * <p>
	 * controlla la data inserita
	 * </p>
	 * @exception lancia un'eccezione se la data è futura
	 */
	public void setData(LocalDate data) throws EccezioneAnnuncio{
		LocalDate dataOdierna=LocalDate.now();
		if(data.isAfter(dataOdierna)) {
			throw new EccezioneAnnuncio("impossibile inserire data successiva a quella attuale");
		}
		this.data=data;
	}
	
	/**
	 * <p>
	 * controlla la durata
	 * </p>
	 * @exception lancia un'eccezione se la durata è minore o uguale a 0
	 */
	public void setDurata(int durata) throws EccezioneAnnuncio{
		if(durata<=0) {
			throw new EccezioneAnnuncio("durata non valida");
		}
		
		else {
			this.durata=durata;
		}
		
	}
	
	/**
	 * <p>
	 * trasforma la stringa di parole chiave, separate dal separatore -, in un ArrayList di stringhe
	 * </p>
	 */
	public void ArrayListParole(String parolechiave) {
		String[] parole=parolechiave.split("-");
		ArrayList<String> ris= new ArrayList<String>();
		for (String s : parole) {
			ris.add(s);
		}
		this.parole_chiave=ris;
	}
		
	public String getNome() {
		return nome;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public String getMailAutore() {
		return mailAutore;
	}	
	
	public ArrayList<String> getParole_chiave() {
		return parole_chiave;
	}

	public int getDurata() {
		return durata;
	}

	public boolean isAcquistare() {
		return acquistare;
	}

	public boolean isVendere() {
		return vendere;
	}

	public int getId_annuncio() {
		return id_annuncio;
	}

	public LocalDate getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return "Annuncio [nome=" + nome + ", prezzo=" + prezzo + ", parole_chiave=" + parole_chiave
				+ ", durata=" + durata + ", acquistare=" + acquistare + ", vendere=" + vendere + ", id_annuncio="
				+ id_annuncio + ", mailAutore=" + mailAutore + ", data=" + data + "]";
	}
	
	
	
}
	

