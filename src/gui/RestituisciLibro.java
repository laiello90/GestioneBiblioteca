package gui;
/*Questa classe visualizza l'interfaccia per poter effettuare la restituzione di un libro
 * Il bibliotecario inserirà il codice del libro nel sistema e verrà eliminato il rpestito*/

	import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

	import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import componenti.Bibliotecario;
import componenti.Database;

	public class RestituisciLibro extends JFrame{

		private JLabel label1, label2;
		private JTextField text1,text2;
		private JButton button1,button2;
		private JTable table;

		public RestituisciLibro( final Bibliotecario bibliotecario){
		super("Restituisci libro");

			Container container = getContentPane();
			container.setLayout( new FlowLayout() );
			table = new JTable();
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JTableHeader header = table.getTableHeader();
			header.setBackground(Color.yellow);
			JScrollPane pane = new JScrollPane(table);
			pane.setBounds(12, 33, 461, 250);
			pane.setPreferredSize(new Dimension(600, 250));
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			container.add(pane);
			LoadTable();
			label1 = new JLabel( "Codice libro" );
			container.add( label1 );
			text1=new JTextField(25);
			container.add(text1);
			
			button1 = new JButton("Registra restituzione");
			button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionevent){
				bibliotecario.restituzione(text1.getText());
				LoadTable();
			}});
			container.add(button1);
			setSize( 620, 400 );
			setVisible( true );
		}


	
	public void LoadTable(){
		try {
			// Creo la connessione col database
			Database db=new Database();
			Connection con = db.connessione();
			String q = "SELECT id, utente, libro,  date_format(prestiti.data, '%d-%m-%Y'),case consegnato when true then 'si' else 'no' end 'consegnato',case sollecito when true then 'ritardo' else '' end 'Stato' FROM prestiti ";
			Statement ps = con.createStatement();
			
			
			ResultSet rs = ps.executeQuery(q);

			String[] tableColumnsName = { "ID", "ID utente", "ID libro",
					"Data prenotazione", "Consegnato", "Sollecito"};
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
	}}
