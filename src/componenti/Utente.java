package componenti;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class Utente extends Persona{

	
public Utente(String ID) {
		super(ID);
		// TODO Auto-generated constructor stub
	}

public String getNome(){
	return nome;
	
}


//SELEZIONA I PRESTITI ATTIVI DELL' UTENTE


public ResultSet getPrestiti(){
	
	String q = "SELECT prestiti.ID, libri.titolo, date_format(prestiti.data, '%d-%m-%Y'), case sollecito when true then 'ritardo' else '' end 'Stato' FROM PRESTITI,libri WHERE libri.ID=PRESTITI.libro AND prestiti.utente='"+id+"' ";
	Statement ps;
	try {
		ps = con.createStatement();
		ResultSet rs = ps.executeQuery(q);
		return rs;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Errore",JOptionPane.ERROR_MESSAGE);
		return null;
	}
	
	
}

//RESTITUISCE IL NUMERO DEI PRESTISTI DELL' UTENTE


public int getNumPrestiti(){
	
	String numprest = "SELECT COUNT(*) FROM prestiti WHERE Utente='"+ id + "'";
	Statement psprest;
	try {
		psprest = con.createStatement();
		ResultSet rs= psprest.executeQuery(numprest);
	rs.next();
		return rs.getInt(1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Errore",JOptionPane.ERROR_MESSAGE);
	return -1;
	}
	
}


//RESTITUISCE IL NUMERO DISOLLECITI

public boolean getSolleciti(){
	
	String numprest = "SELECT COUNT(*) FROM prestiti WHERE Utente='"+ id + "'";
	Statement psprest;
	try {
		String nsoll = "SELECT COUNT(*) FROM prestiti WHERE Utente='"+ id + "'AND sollecito=true";
		Statement psnsoll= con.createStatement();
		ResultSet rssoll= psnsoll.executeQuery(nsoll);
		
		rssoll.next();
		return (rssoll.getInt(1)>0);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Errore",JOptionPane.ERROR_MESSAGE);
	return true;
	}
	
}

//FUNZIONE RICHIESTA NUOVO PRESTITO

public void AggiuntaPrestito(String idLibro, String titolo, String scaffale){

	try {
	JFileChooser file = new JFileChooser();
	file.setSelectedFile(new File(".pdf"));
	int x=file.showSaveDialog(null);
	if(x==0){
		String q = "insert into Prestiti(Utente, Libro) values('"+ id + "','" + idLibro + "')";
		Statement ps= con.createStatement();
		ps.executeUpdate(q);
		
		String qu = "UPDATE `biblioteca`.`libri` SET `Disponibile`=0 WHERE `ID`= " + idLibro+";";
		Statement us= con.createStatement();
		us.executeUpdate(qu);
		
		String idp = "SELECT id FROM prestiti WHERE Utente='"+ id + "'AND libro='"+idLibro+"'";
		Statement psidp= con.createStatement();
		ResultSet rsidp= psidp.executeQuery(idp);
		rsidp.next();
		int nidp = rsidp.getInt(1);
	
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		
		
//	CREA UN PDF CON I DATI DELLA PRENOTAZIONE	
		
		
	PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(file.getSelectedFile()));
	document.open();
	document.add(new Paragraph("Biblioteca"));
	document.add(new Paragraph("Richiesta di prestito libro"));
	document.add(new Paragraph("ID prenotazione:"+nidp));
	document.add(new Paragraph("ID Utente:"+id));
	document.add(new Paragraph("Utente: "+nome+" "+cognome));
	document.add(new Paragraph("ID Libro:" + idLibro));
	document.add(new Paragraph("Titolo libro:"+ titolo));
	document.add(new Paragraph("Scaffale:"+ scaffale));
	document.add(new Paragraph(""));
	document.add(new Paragraph("Firma"));

	
	document.close();
	JOptionPane.showMessageDialog(null,"La prenotazione è avvenuta con successo.","Conferma",JOptionPane.INFORMATION_MESSAGE);
	}} catch (DocumentException | SQLException | FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Errore",JOptionPane.ERROR_MESSAGE);
	}
	}
}

	




