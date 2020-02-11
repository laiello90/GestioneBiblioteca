package gui;
/*questa classe visualizza un'interfaccia dove l'utente può visualizzare i suoi prestiti e nel caso
 * in cui abbia dei solleciti gli viene mostrato un avviso*/
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;

import componenti.Utente;


import java.sql.Statement;

public class PrestitiUtente extends JFrame {
	private JLabel label1, label2;
	private JTextField text1,text2;
	private JButton button1,button2;
	private JTable table;
	private JFrame frame;
	public PrestitiUtente(Utente user){
		super("Prestiti utente");
		frame = this.frame;
		Container container = getContentPane();
		getContentPane().setLayout(null);

		label1 = new JLabel( "Prestiti" );
		label1.setBounds(137, 11, 48, 14);
		container.add(label1);

		
		
		
		table = new JTable();
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.yellow);
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(10, 36, 303, 231);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		container.add(pane);
		
		
		try{
			
			//richiama il metodo di user getPrestiti()
			
		ResultSet rs = user.getPrestiti(); 
		
		String[] tableColumnsName = { "ID Prenotazione", "Titolo", "Data",
				"sollecito"};
		DefaultTableModel aModel = new DefaultTableModel();
		aModel.setColumnIdentifiers(tableColumnsName);
		java.sql.ResultSetMetaData rsmd = rs.getMetaData();
		int colNo = rsmd.getColumnCount();
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
		
		
		setSize( 336, 310 );
		setVisible( true );
		
		//queste due righe effettuano il controllo sul risultato di getPrestiti() 
		//e visualizzano un messaggio se si ha almeno un sollecito
		
		for(int i =0;i<table.getRowCount();i++)
			if((table.getValueAt(i, 3).equals("ritardo")))
				JOptionPane.showMessageDialog(frame,"Attenzione. L'utente ha almeno un sollecito.","Attenzione",JOptionPane.INFORMATION_MESSAGE);
	
	}
}
