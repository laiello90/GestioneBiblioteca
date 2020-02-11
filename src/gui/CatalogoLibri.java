package gui;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import componenti.Database;
import componenti.Utente;

import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Component;

public class CatalogoLibri extends JFrame {

	private JLabel label1, label2;
	private JTextField text1, text2;
	private JButton button1, button2;
	private JTable table;
	private Utente utente; 
	private JFrame frame;
	private int x;
	
//FUNZIONE RICERCA NUOVO LIBRO DAI CATALOGHI
	
	
	public CatalogoLibri(final Utente user) {
		super("Catalogo libri");
		utente=user;
		Container container = getContentPane();
		frame=this.frame;
		getContentPane().setLayout(null);
		label1 = new JLabel("Titolo");
		label1.setBounds(68, 9, 49, 14);
		container.add(label1);
		text1 = new JTextField(25);
		text1.setBounds(122, 6, 206, 20);
		container.add(text1);
		
		
		final JButton button1 = new JButton("Cerca");
		button1.setBounds(333, 5, 82, 23);
		container.add(button1);
		
		
		table = new JTable();
		
		
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.yellow);
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(12, 33, 461, 250);
		pane.setPreferredSize(new Dimension(461, 250));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		container.add(pane);
		LoadTable();
		//All'evento click del button1 si crea una connessione al database per scaricare la lista dei libri cercati
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {

				LoadTable();

			}
		});
		
//		funzione prenotazione libro
		
		 JButton button2 = new JButton("Stampa richiesta prestito");
		 button2.setBounds(167, 288, 184, 23);
		 
		 //All'evento click del button2 il programma dopo aver effettuato un controllo 
		 //registra la richiesta di prestito e stampa il modulo pdf
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				
				
				
					if(utente.getNumPrestiti()>2){//verifica che l'utente non abbia 3 prestiti
						
						JOptionPane.showMessageDialog(frame,"Errore. L'utente ha già richiesto tre prestiti.","Errore",JOptionPane.ERROR_MESSAGE);
					}else
					{
						
						if(utente.getSolleciti()){//verifica che l'utente non abbia un sollecito
							
							JOptionPane.showMessageDialog(frame,"Errore. L'utente ha almeno un sollecito.","Errore",JOptionPane.ERROR_MESSAGE);
						}else{
						
						//queste tre righe registrano le informazione del libro selezionato nella tabella
					String idLibro = table.getValueAt(table.getSelectedRow(), 0).toString();
					String titolo =table.getValueAt(table.getSelectedRow(), 1).toString();
					String scaffale=table.getValueAt(table.getSelectedRow(),5).toString();
					
					//questo metodo aggiunge il prestito e stampa il modulo
					utente.AggiuntaPrestito(idLibro, titolo, scaffale);
						LoadTable();
						}}}
				

			
		});

		container.add(button2);

		setSize(501, 372);
		setVisible(true);
	}
public void LoadTable(){
	try {
		
		// Creo la connessione col database
		Database db=new Database();
		Connection con = db.connessione();
		String q = "SELECT * FROM libri where Titolo LIKE \"%\" ? \"%\" AND Disponibile = true ";
		PreparedStatement ps = con.prepareStatement(q);
		String id = text1.getText();
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();

		String[] tableColumnsName = { "ID libro", "Titolo", "Autore",
				"ISDN", "Scaffale N.", "Sezione" };
		DefaultTableModel aModel = new DefaultTableModel();
		aModel.setColumnIdentifiers(tableColumnsName);
		java.sql.ResultSetMetaData rsmd = rs.getMetaData();
		int colNo = rsmd.getColumnCount();
		
		//questa parte carica nella tabella i risultati della ricerca
		while (rs.next()) {
			Object[] objects = new Object[colNo];
			for (int i = 0; i < colNo; i++) {
				objects[i] = rs.getObject(i + 1);
			}
			aModel.addRow(objects);
		}
		table.setModel(aModel);

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Errore",JOptionPane.ERROR_MESSAGE);
	}
	
}

}
