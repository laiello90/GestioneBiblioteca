package gui;
/*visualizza un'interfaccia con tutti gli utenti presenti nel sistema e si da la possibilità di eliminare o aggiungere un utente*/
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import componenti.Amministratore;
import componenti.Database;





public class RiepilogoUtenti extends JFrame {

	
	private JTable table;
	private JButton button1, button2,button3;
	private JFrame frame;
	
	
//	COSTRUTTORE RIEPILOGOUTENTI
	
	public RiepilogoUtenti(final Amministratore amministratore){
		super("Riepilogo utente");
		setPreferredSize(new Dimension(700, 400));
		getContentPane().setPreferredSize(new Dimension(700, 400));
		setSize(new Dimension(700, 500));
		frame=this;
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		table = new JTable();
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.yellow);
		final JScrollPane pane = new JScrollPane(table);
		pane.setBounds(12, 33, 461, 250);
		pane.setPreferredSize(new Dimension(600, 250));
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		container.add(pane);
		LoadTable();
		
		
		button1 = new JButton("Aggiungi Utente");
		button1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent actionevent){
		NuovoUtente nuovoutente = new NuovoUtente(amministratore);
        nuovoutente.setVisible(true);
		nuovoutente.toFront();
		frame.dispose();
		}} );
		container.add(button1);
		
		button2 = new JButton("Rimuovi Utente");
		button2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent actionevent){
			String idUtente = table.getValueAt(table.getSelectedRow(), 0).toString();
			amministratore.RimuoviUtente(idUtente);
			LoadTable();
			}} );
		
		container.add(button2);
		button3 = new JButton("Stampa lista");
		button3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent actionevent){
			JFileChooser file = new JFileChooser();
			
			file.setSelectedFile(new File(".pdf"));
			int x=file.showSaveDialog(null);
			if(x==0){
				
//				ORIENTAMENTO E DIMENSIONI DOCUMENTO
				
				Document document = new Document(PageSize.A4.rotate(), -65F, -65F, 100F, 80F);
				
				
				
			try {
				PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(file.getSelectedFile()));
				document.open();
		        PdfPTable pdftable = new PdfPTable(9);//num colonne
		      
		        PdfPCell cellID = new PdfPCell(new Paragraph("CF"));
		        cellID.setColspan(1);
		        PdfPCell cellTitle = new PdfPCell(new Paragraph("Nome"));
		        cellTitle.setColspan(1);
		        PdfPCell cellAuthor = new PdfPCell(new Paragraph("Cognome"));
		        cellAuthor.setColspan(1);
		        PdfPCell cellEditor = new PdfPCell(new Paragraph("Telefono"));
		        cellEditor.setColspan(1);
		        PdfPCell cellPrice = new PdfPCell(new Paragraph("E-mail"));
		        cellPrice.setColspan(1);
		        PdfPCell cellIsbn = new PdfPCell(new Paragraph("Città"));
		        cellIsbn.setColspan(1);
		        PdfPCell cellNote = new PdfPCell(new Paragraph("Indirizzo"));
		        cellNote.setColspan(1);
		        PdfPCell cell8 = new PdfPCell(new Paragraph("Password"));
		        cellIsbn.setColspan(1);
		        PdfPCell cell9 = new PdfPCell(new Paragraph("Tipo"));
		        cellNote.setColspan(1);

		        pdftable.addCell(cellID);
		        pdftable.addCell(cellTitle);
		        pdftable.addCell(cellAuthor);
		        pdftable.addCell(cellEditor);
		        pdftable.addCell(cellPrice);
		        pdftable.addCell(cellIsbn);
		        pdftable.addCell(cellNote);
		        pdftable.addCell(cell8);
		        pdftable.addCell(cell9);
		    	
				 
		   for (int j = 0; j < table.getRowCount(); j++) {
		            
		            for (int k = 0; k < table.getColumnCount(); k++) {
		            	
		            	
		            	if (table.getValueAt(j,k)==null){
		            	PdfPCell cellMap = new PdfPCell(new Paragraph(""));
		            	cellMap.setColspan(1);
		                pdftable.addCell(cellMap);
		            	}else{
		            	PdfPCell cellMap = new PdfPCell(new Paragraph(table.getValueAt(j,k).toString()));
		            	cellMap.setColspan(1);
		                pdftable.addCell(cellMap);}
		            }
		        }
		        document.add(pdftable);
		        document.close();
			}
			 catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		        
			
			}
			}} );
		container.add(button3);
		

		
		setSize(700, 400);
		setVisible(true);
	}

	public void LoadTable(){
		try {
			// Creo la connessione col database
			Database db=new Database();
			Connection con = db.connessione();
			String q = "SELECT * FROM utenti";
			Statement ps = con.createStatement();
			
			
			ResultSet rs = ps.executeQuery(q);

			String[] tableColumnsName = { "CF", "Nome", "Cognome",
					"Telefono", "e-mail", "Città","Indirizzo","Password","Tipo" };
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
	}
	
}