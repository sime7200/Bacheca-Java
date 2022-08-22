package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;


import org.junit.jupiter.api.Test;

import codice.Annuncio;
import eccezioni.EccezioneAnnuncio;

/**  
 *@author Giulia Sonsino 20036727
 *@author Simone Garau 20034352
 */
class TestAnnuncio {

	
	@Test
	void TestCostruttore() throws EccezioneAnnuncio {
		Annuncio a=new Annuncio("A1",20,"parola1-parola2",10,false,true,0,"giulia@gmail.com",LocalDate.of(2020, Month.JUNE, 2));
		assertEquals(a.toString(),"Annuncio [nome=A1, prezzo=20.0, parole_chiave=[parola1, parola2], durata=10, acquistare=false, vendere=true, id_annuncio=0, mailAutore=giulia@gmail.com, data=2020-06-02]");

	}
	
	@Test
	void TestCostruttoreSenzaDurata() throws EccezioneAnnuncio {
		Annuncio a=new Annuncio("A2",20,"parola1",false,true,1,"giulia@gmail.com",LocalDate.of(2020, Month.JUNE, 2));
		assertEquals(a.toString(),"Annuncio [nome=A2, prezzo=20.0, parole_chiave=[parola1], durata=30, acquistare=false, vendere=true, id_annuncio=1, mailAutore=giulia@gmail.com, data=2020-06-02]");
	}
	
	@Test
	void TestData() throws EccezioneAnnuncio {
		Annuncio a=new Annuncio("A1",20,"parola1",10,false,true,0,"giulia@gmail.com",LocalDate.of(2020, Month.JUNE, 2));
		assertTrue(a.getData().getMonthValue()==06);
		assertFalse(a.getData().getYear()==2023);
		EccezioneAnnuncio ecc=assertThrows(EccezioneAnnuncio.class,()-> {
			Annuncio e=new Annuncio("A1",20,"parola1,parola2",9,false,true,1010,"giulia@gmail.it",LocalDate.of(2033, Month.JUNE, 2));
			});
			assertEquals("impossibile inserire data successiva a quella attuale", ecc.getMessage());
		
	}
	
	
	@Test
	void TestDurata() throws EccezioneAnnuncio {
		EccezioneAnnuncio ecc=assertThrows(EccezioneAnnuncio.class,()-> {
		Annuncio a=new Annuncio("A1",20,"parola1,parola2",-5,false,true,1010,"giulia@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		});
		assertEquals("durata non valida", ecc.getMessage());
	}
	
	

}
