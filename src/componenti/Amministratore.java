package componenti;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Amministratore extends Persona{

	public Amministratore(String ID) {
		super(ID);
		// TODO Auto-generated constructor stub
	}
	

//FUNZIONE AGGIUNGI UTENTE	
	
public void AggiungiUtente(String[] info){
	
	String q = "INSERT INTO `biblioteca`.`utenti` (`ID`, `Nome`, `Cognome`, `Telefono`, `Email`, `Citta`, `Indirizzo`, `Password`, `Tipo`) " +
			"VALUES ('"+info[0]+"', '"+info[1]+"', '"+info[2]+"', '"+info[3]+"', '"+info[4]+"', '"+info[5]+"', '"+info[6]+"', '"+info[7]+"', '"+info[8]+"')";
	Statement ps;
	
	try {
		ps = con.createStatement();
		ps.executeUpdate(q);
		JOptionPane.showMessageDialog(null,"Utente inserito con successo.","Conferma",JOptionPane.INFORMATION_MESSAGE);
		Email email=new Email(info);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Errore",JOptionPane.ERROR_MESSAGE);
	}
	
	
	
	
	
}

//FUNZIONE AGGIUNTA NUOVO LIBRO

public void AggiungiLibro(String[] info){
	
	String q = "INSERT INTO `biblioteca`.`libri` (`Titolo`, `Autore`, `Pagine`, `ISBN`, `Scaffale`, `Disponibile`) " +
			"VALUES ('"+info[0]+"', '"+info[1]+"', '"+info[2]+"', '"+info[3]+"', '"+info[4]+"', '1')";
	Statement ps;
	try {
		ps = con.createStatement();
		ps.executeUpdate(q);
		JOptionPane.showMessageDialog(null,"Libro aggiunto con successo.","Conferma",JOptionPane.INFORMATION_MESSAGE);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Errore",JOptionPane.ERROR_MESSAGE);
	}
}


//FUNZIONE RIMUOVI UTENTE

public void RimuoviUtente(String idUtente){
	
	String q = "DELETE FROM `biblioteca`.`utenti` WHERE `ID`='"+idUtente+"';";
	Statement ps;
	try {
		ps = con.createStatement();
		ps.executeUpdate(q);
		JOptionPane.showMessageDialog(null,"Utente rimosso con successo.","Conferma",JOptionPane.INFORMATION_MESSAGE);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Errore",JOptionPane.ERROR_MESSAGE);
	}
	
	
	
}


//FUNZIONE RIMUOVI LIBRO

public void RimuoviLibro(String idlibro){
	
	String q = "DELETE FROM `biblioteca`.`libri` WHERE `ID`='"+idlibro+"';";
	Statement ps;
	try {
		ps = con.createStatement();
		ps.executeUpdate(q);
		JOptionPane.showMessageDialog(null,"Libro rimosso con successo.","Conferma",JOptionPane.INFORMATION_MESSAGE);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Errore",JOptionPane.ERROR_MESSAGE);
	}
	
	
	
}


//FUNZIONE INVIO SOLLECITI

public void inviosolleciti(){
	
	try {
		String q = "SELECT ID FROM PRESTITI WHERE (DAYOFYEAR(Data)+30)<DAYOFYEAR(CURDATE())";
		Statement ps;
		ps = con.createStatement();
		ResultSet rs = ps.executeQuery(q);
		while (rs.next()) {
			String q2 = "UPDATE `biblioteca`.`prestiti` SET `sollecito`=1 WHERE `ID`='"+rs.getInt(1)+"';";
			Statement ps2;
			
				ps2 = con.createStatement();
				ps2.executeUpdate(q2);
			
		}
		
		String q3 = "SELECT Email,Titolo FROM PRESTITI,UTENTI,LIBRI WHERE sollecito=1 and UTENTI.ID=PRESTITI.Utente and Prestiti.libro=libri.id";
		Statement ps3;
		ps = con.createStatement();
		ResultSet rs3 = ps.executeQuery(q3);
		
		while (rs3.next()) {
			Email email=new Email(rs3.getString(1),rs3.getString(2));
			
		}
		JOptionPane.showMessageDialog(null,"Tutti i sollecti sono stati inviati.","Conferma",JOptionPane.INFORMATION_MESSAGE);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Errore",JOptionPane.ERROR_MESSAGE);
		
	}
	
}
}
