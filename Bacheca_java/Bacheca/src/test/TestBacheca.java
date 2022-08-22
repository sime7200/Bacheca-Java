package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import codice.Annuncio;
import codice.Bacheca;
import codice.Utenti;
import eccezioni.EccezioneAnnuncio;
import eccezioni.EccezioneBacheca;
import eccezioni.EccezioneUtenti;



/**  
 *@author Giulia Sonsino 20036727
 *@author Simone Garau 20034352
 */
class TestBacheca {

	
	@AfterEach
	void TestsvuotaBacheca() {
		Bacheca.svuotaBacheca();
	}
	
	

	@Test
	void TestInserisciAnnuncio() throws EccezioneAnnuncio, EccezioneBacheca{
		Bacheca b=new Bacheca();
		assertTrue(b.dimensioneBacheca()==0);
		b.inserisciAnnuncio("Automobile", 7000, "autoveicolo-rosso", 60, false, true, 1, "giorgio@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		assertEquals(1, b.dimensioneBacheca());
		EccezioneBacheca ecc= assertThrows(EccezioneBacheca.class,()->{
			Annuncio an=new Annuncio("Annuncio", 10,"chiave1-chiave2", 30, false,true, 1,"silvia@gmail.com",LocalDate.of(2022, Month.JUNE, 2));
			b.inserisciAnnuncio(an);
		});
		assertEquals("impossibile aggiungere tale annuncio poichè il suo codice identificativo è già presente nella bacheca",ecc.getMessage());			
	}
	
	@Test
	void TestDimensioneBacheca() throws EccezioneAnnuncio, EccezioneBacheca {
		Bacheca b=new Bacheca();
		Annuncio c=new Annuncio("Automobile", 7000, "autoveicolo-pietra-rosso", 60, false, true, 0001, "giorgio@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio d=new Annuncio("Tavolo", 10, "legno-artigianato", 60, false, true, 0002, "fabio@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio h=new Annuncio("comodino", 10, "artigianato", 60, true, false, 0004, "massimo@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio e=new Annuncio("Sedia", 10, "pietra-artigianato", 60, true, false, 0005, "massimo@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		b.inserisciAnnuncio(h);
		b.inserisciAnnuncio(e);
		b.inserisciAnnuncio(c);
		b.inserisciAnnuncio(d);
		assertTrue(b.dimensioneBacheca()==4);
	}
	
	@Test
	void TestRitornaArticoliConParole() throws EccezioneAnnuncio, EccezioneBacheca {
		Bacheca b=new Bacheca();
		b.articoliConParoleSimili=new ArrayList<Annuncio>();
		Annuncio c=new Annuncio("Automobile", 7000, "autoveicolo-pietra-rosso", 60, false, true, 0001, "giorgio@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio d=new Annuncio("Tavolo", 10, "legno-artigianato", 60, false, true, 0002, "fabio@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio h=new Annuncio("comodino", 10, "artigianato", 60, true, false, 0004, "massimo@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio e=new Annuncio("Sedia", 10, "pietra-artigianato", 60, true, false, 0005, "massimo@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		b.inserisciAnnuncio(h);
		b.inserisciAnnuncio(e);
		b.inserisciAnnuncio(c);
		b.inserisciAnnuncio(d);
		assertTrue(b.dimensioneBacheca()==4);
		ArrayList<Annuncio> parolesimili=b.ritornaArticoliConParoleUguali(h);
		assertEquals(parolesimili.size(),2);
		assertTrue(b.ritornaArticoliConParoleUguali(e).size()==3);


		
	}
	@Test
	void TestSvuotaBacheca() throws EccezioneAnnuncio, EccezioneBacheca{
		Bacheca b=new Bacheca();
		Annuncio c=new Annuncio("Automobile", 7000, "autoveicolo-rosso", 60, false, true, 0001, "giorgio@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio d=new Annuncio("Appartemento", 1000, "immobile-casa-città", 30, false, true, 0002, "giulia@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		b.inserisciAnnuncio(c);
		b.inserisciAnnuncio(d);
		assertTrue(b.dimensioneBacheca()==2);
		Bacheca.svuotaBacheca();
		assertTrue(b.dimensioneBacheca()==0);

	}
	
	@Test 
	void TestEliminaAnnuncio() throws EccezioneBacheca,EccezioneAnnuncio {
		Bacheca b=new Bacheca();
		Annuncio a=new Annuncio("Annuncio1",222, "parola1-parola2", 30, false, true, 3333, "utente@libero.it",LocalDate.of(2022, Month.JUNE, 2));
		b.inserisciAnnuncio(a);
		b.elimina_annuncio(3333,"utente@libero.it");
		assertTrue(b.dimensioneBacheca()==0);		
		Annuncio c=new Annuncio("Automobile", 7000, "autoveicolo-rosso", 60, false, true, 1, "giorgio@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio d=new Annuncio("Appartemento", 1000, "immobile-casa-città", 30, false, true, 2, "giuli@lib.it",LocalDate.of(2022, Month.JUNE, 2));
		b.inserisciAnnuncio(c);
		b.inserisciAnnuncio(d);
		b.elimina_annuncio(1, "giorgio@gmail.it");
		b.elimina_annuncio(2, "giuli@lib.it");
		assertTrue(b.dimensioneBacheca()==0);
		EccezioneBacheca ecc= assertThrows(EccezioneBacheca.class,()->{
			Annuncio an=new Annuncio("Annuncio", 10,"chiave1-chiave2", 30, false,true, 1010,"silvia@gmail.com",LocalDate.of(2022, Month.JUNE, 2));
			b.inserisciAnnuncio(an);
			b.elimina_annuncio(1010, "utente@libero.it");
		});
		assertEquals("Nessun annuncio trovato",ecc.getMessage());
		
	}
	
	@Test
	void TestEliminaParolaChiave() throws EccezioneAnnuncio, EccezioneBacheca{
		Bacheca b=new Bacheca();
		Annuncio a=new Annuncio("Automobile", 7000, "autoveicolo-rosso", 60, false, true, 1, "giorgio@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio d=new Annuncio("Appartemento", 1000, "immobile-casa-città", 30, false, true, 2, "giulia@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio c=new Annuncio("Annuncio3", 7000, "pianta-rosso", 60, false, true, 12, "0000@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio e=new Annuncio("Annuncio4", 1000, "rosso-estate", 30, false, true, 23, "1111@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		b.inserisciAnnuncio(a);
		b.inserisciAnnuncio(d);
		b.inserisciAnnuncio(c);
		b.inserisciAnnuncio(e);
		assertTrue(b.dimensioneBacheca()==4);
		b.eliminaParolaChiave("rosso");
		assertTrue(a.getParole_chiave().size()==1);
		assertTrue(e.getParole_chiave().size()==1);
		assertTrue(d.getParole_chiave().size()==3);
	}
	
	@Test
	void TestInserisciParolaChiave() throws EccezioneAnnuncio, EccezioneBacheca {
		Bacheca b=new Bacheca();
		Annuncio a=new Annuncio("Automobile", 7000, "autoveicolo-rosso", 60, false, true, 0001, "giorgio@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio d=new Annuncio("Appartemento", 1000, "immobile-casa-città", 30, false, true, 0002, "giulia@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio c=new Annuncio("Annuncio3", 7000, "pianta-rosso", 60, false, true, 0004, "0000@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio e=new Annuncio("Annuncio4", 1000, "rosso-estate", 30, false, true, 0006, "1111@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		b.inserisciAnnuncio(a);
		b.inserisciAnnuncio(d);
		b.inserisciAnnuncio(c);
		b.inserisciAnnuncio(e);
		b.inserisciParolaChiave("prova", "1111@gmail.it", 0006);
		assertTrue(e.getParole_chiave().size()==3);
		EccezioneBacheca ecc= assertThrows(EccezioneBacheca.class,()->{
			b.inserisciParolaChiave("prova", "giorgio@gmail.it", 0002);
		});
		assertEquals("impossibile inserire parola chiave: id e mailAutore non corrispondono a nessun annuncio",ecc.getMessage());		
	}
	
	
	@Test
	void testCercaPerParola() throws EccezioneAnnuncio, EccezioneBacheca {
		Bacheca b=new Bacheca();
		Annuncio a=new Annuncio("Automobile", 7000, "autoveicolo-rosso", 60, false, true, 1, "giorgio@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio d=new Annuncio("Appartemento", 1000, "immobile-casa-città", 30, false, true, 2, "giulia@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio c=new Annuncio("Annuncio3", 7000, "pianta", 60, false, true, 3, "0000@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio e=new Annuncio("Annuncio4", 1000, "rosso-estate", 30, false, true, 6, "1111@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		b.inserisciAnnuncio(a);
		b.inserisciAnnuncio(d);
		b.inserisciAnnuncio(c);
		b.inserisciAnnuncio(e);
		ArrayList<Annuncio>lista=b.cercaParolaChiave("rosso-pianta");
		assertTrue(lista.size()==3);
		ArrayList<Annuncio>lista2=b.cercaParolaChiave("città");
		assertTrue(lista2.size()==1);
		ArrayList<Annuncio>lista3=b.cercaParolaChiave("cane");
		assertTrue(lista3.size()==0);
		
	}
	
	@Test
	void testEliminaArticoliScaduti() throws EccezioneAnnuncio, EccezioneBacheca  {
		Bacheca b=new Bacheca();
		Annuncio a=new Annuncio("Automobile", 7000, "autoveicolo-rosso", 90, false, true, 1, "giorgio@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		Annuncio d=new Annuncio("Appartemento", 1000, "immobile-casa-città", 30, false, true, 2, "giulia@gmail.it",LocalDate.of(2022, Month.APRIL, 2));
		Annuncio c=new Annuncio("Annuncio3", 7000, "pianta", 60, false, true, 5, "0000@gmail.it",LocalDate.of(2021, Month.MARCH, 2));
		Annuncio e=new Annuncio("Annuncio4", 1000, "rosso-estate", 90, false, true, 67, "1111@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		b.inserisciAnnuncio(a);
		b.inserisciAnnuncio(d);
		b.inserisciAnnuncio(c);
		b.inserisciAnnuncio(e);
		assertTrue(b.dimensioneBacheca()==4);
		b.EliminaArticoliScaduti();
		assertTrue(b.dimensioneBacheca()==2);
	}
	
	@Test
	void testLeggiBacheca() throws IOException, EccezioneAnnuncio, EccezioneUtenti, EccezioneBacheca {
		Bacheca b=new Bacheca(); 
		b.leggiBacheca("file1.txt");
		assertTrue(b.dimensioneBacheca()!=0);
		assertTrue(Utenti.utenti.size()==3);
		
	}
	
	@Test
	void testScritturaAnnunci() throws EccezioneAnnuncio, EccezioneBacheca {
		Bacheca b=new Bacheca();
		Annuncio a=new Annuncio("Auto", 700, "autoveicolo", 90, false, true, 1, "giorgio@gmail.it",LocalDate.of(2022, Month.JUNE, 2));
		b.inserisciAnnuncio(a);
		String s=b.scritturaAnnunci();
		String str =s.substring(0, s.length()-1);
		assertEquals(str,"Auto,700.0,autoveicolo,90,false,true,1,giorgio@gmail.it,2022-06-02");
	}
	
	
}
