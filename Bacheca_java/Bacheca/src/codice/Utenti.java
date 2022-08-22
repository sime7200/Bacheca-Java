package codice;

import java.util.ArrayList;

import eccezioni.EccezioneUtenti;

/**  
 *@author Giulia Sonsino 20036727
 *@author Simone Garau 20034352
 */
public class Utenti {
	public static ArrayList<Utente> utenti=new ArrayList<Utente>();
	
	public Utenti() {
		utenti=new ArrayList<Utente>();
	}
	
	/**
	 * <p>
	 * inserisce un utente nell'ArrayList di utenti, controllando che non sia già presente
	 * </p>
	 */
	public   void inserisciUtente(String email) throws EccezioneUtenti  {
		for (Utente tmp : utenti)
			if (tmp.getEmail().equals(email))
				return;
		Utente p=new Utente(email);
		utenti.add(p);
	}
	
	public void inserisciUtente(Utente u) throws EccezioneUtenti {
		for (Utente tmp : utenti)
			if (tmp.getEmail().equals(u.getEmail()))
				return;
		Utente p=new Utente(u.getEmail());
		utenti.add(p);
	}
	
	public ArrayList<Utente> getUtenti() {
		return utenti;
	}
	
	
}
