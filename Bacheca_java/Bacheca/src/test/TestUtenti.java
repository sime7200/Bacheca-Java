package test;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;


import codice.Utente;
import codice.Utenti;
import eccezioni.EccezioneUtenti;



/**  
 *@author Giulia Sonsino 20036727
 *@author Simone Garau 20034352
 */
class TestUtenti {

	@
	Test
	void TestInserisciUtente() throws EccezioneUtenti {
		Utenti u=new Utenti();		
		assertTrue(Utenti.utenti.size()==0);
		u.inserisciUtente("giulia@gmail.com");
		assertTrue(Utenti.utenti.size()==1);
		Utente a=new Utente("giulia@gmail.com");
		u.inserisciUtente(a);
		assertTrue(Utenti.utenti.size()==1);	
	}
	
	@Test
	void TestGetUtenti() throws EccezioneUtenti {
		Utenti u=new Utenti();		
		u.inserisciUtente("giulia@gmail.com");
		u.inserisciUtente("giulia@libero.com");
		assertEquals(u.getUtenti().toString(),"[Utente [email=giulia@gmail.com], Utente [email=giulia@libero.com]]");
	}
	
	

}
