package componenti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class Bibliotecario extends Persona {
	public Bibliotecario(String ID) {
		super(ID);
		// TODO Auto-generated constructor stub
	}
	

//FUNZIONE CONCEDI PRESTITO	
	
public void consegna(String idprestito){
	try {
		
	       
	    String q = "UPDATE prestiti SET `consegnato`=1 WHERE `ID`='"+idprestito+"';";
		Statement ps= con.createStatement();
		ps.executeUpdate(q);
		
		JOptionPane.showMessageDialog(null,"Libro consegnato.","Conferma",JOptionPane.INFORMATION_MESSAGE);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Errore",JOptionPane.ERROR_MESSAGE);
	}
	
}


//FUNZIONE REGISTRA RESO

public void restituzione(String idlibro){
	try {
		 
	        
	        //si devei aggiornare lo stato del libro a disponibile
	    String q = "DELETE FROM `biblioteca`.`prestiti` WHERE `libro`='"+idlibro+"'";
		Statement ps= con.createStatement();
		ps.executeUpdate(q);
		
		  String q2 = "UPDATE `biblioteca`.`libri` SET `Disponibile`=1 WHERE `ID`='"+idlibro+"'";
			Statement ps2= con.createStatement();
			ps2.executeUpdate(q2);
			JOptionPane.showMessageDialog(null,"Libro restituito.","Conferma",JOptionPane.INFORMATION_MESSAGE);
			
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Errore",JOptionPane.ERROR_MESSAGE);
	}
}

}
