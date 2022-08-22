package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import codice.Utente;
import eccezioni.EccezioneUtenti;

/**  
 *@author Giulia Sonsino 20036727
 *@author Simone Garau 20034352
 */
class TestUtente {

	@Test
	void testCostruttore() throws EccezioneUtenti {
		EccezioneUtenti ecc=assertThrows(EccezioneUtenti.class,()->{
			Utente a=new Utente("c9fi.it");
		});
		assertEquals("formato email non valido",ecc.getMessage());
	}
	
	

}
