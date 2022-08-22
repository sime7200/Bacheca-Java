package codice;

import eccezioni.EccezioneUtenti;



/**  
 *@author Giulia Sonsino 20036727
 *@author Simone Garau 20034352
 */
public class Utente {

	
	private String email;
	
	
	public Utente( String email) throws EccezioneUtenti {	
		this.setEmail(email);
		
	}
	
	
	/**
	 * <p>
	 * controlla il formato dell'email
	 * </p>
	 * @exception lancia un'eccezione in caso in cui il formato non sia valido
	 */
	public void setEmail(String email) throws EccezioneUtenti {
		if(!(email.matches("[a-z[A-Z[0-9]]]+@[a-z[A-Z]]+\\.[a-z]+"))){
				throw new EccezioneUtenti("formato email non valido");
		}
		else {
			this.email=email;
		}
		
		
	}

	
	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Utente [email=" + email + "]";
	}
	
	
	
	
}
