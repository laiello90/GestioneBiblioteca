package gui;
/*visualizza un'interfaccia con tutti i libri presenti nel sistema e si da la possibilità di eliminare o aggiungere un libro*/
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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


public class RiepilogoLibri extends JFrame{
	
	private JTable table;
	private JButton button1, button2,button3;
	private JFrame frame;
	
	public RiepilogoLibri(final Amministratore amministratore){
		super("Riepilogo Libri");
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
		JScrollPane pane = new JScrollPane(table);
		pane.setBounds(12, 33, 461, 250);
		pane.setPreferredSize(new Dimension(600, 250));
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		container.add(pane);
		LoadTable();
		
		
		button1 = new JButton("Aggiungi Libro");
		button1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent actionevent){
		NuovoLibro nuovolibro = new NuovoLibro(amministratore);
        nuovolibro.setVisible(true);
		nuovolibro.toFront();
		frame.dispose();
		}} );
		container.add(button1);
		
		button2 = new JButton("Rimuovi Libro");
		button2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent actionevent){
			String idlibro= table.getValueAt(table.getSelectedRow(), 0).toString();
			amministratore.RimuoviLibro(idlibro);
			LoadTable();
			}} );
		
		button3 = new JButton("Stampa lista");
		button3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent actionevent){
			JFileChooser file = new JFileChooser();
			file.setSelectedFile(new File(".pdf"));
			int x=file.showSaveDialog(null);
			if(x==0){
				
				Document document = new Document(PageSize.A4, -65F, -65F, 100F, 80F);
				
				
				
			try {
				PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(file.getSelectedFile()));
				document.open();
		        PdfPTable pdftable = new PdfPTable(7);//num colonne

		        PdfPCell cellID = new PdfPCell(new Paragraph("ID"));
		        cellID.setColspan(1);
		        PdfPCell cellTitle = new PdfPCell(new Paragraph("Titolo"));
		        cellTitle.setColspan(1);
		        PdfPCell cellAuthor = new PdfPCell(new Paragraph("Autore"));
		        cellAuthor.setColspan(1);
		        PdfPCell cellEditor = new PdfPCell(new Paragraph("Pagine"));
		        cellEditor.setColspan(1);
		        PdfPCell cellPrice = new PdfPCell(new Paragraph("ISBN"));
		        cellPrice.setColspan(1);
		        PdfPCell cellIsbn = new PdfPCell(new Paragraph("Scaffale"));
		        cellIsbn.setColspan(1);
		        PdfPCell cellNote = new PdfPCell(new Paragraph("Disponibile"));
		        cellNote.setColspan(1);

		        pdftable.addCell(cellID);
		        pdftable.addCell(cellTitle);
		        pdftable.addCell(cellAuthor);
		        pdftable.addCell(cellEditor);
		        pdftable.addCell(cellPrice);
		        pdftable.addCell(cellIsbn);
		        pdftable.addCell(cellNote);
		    	
				 
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
		container.add(button2);
		container.add(button3);
		setSize(700, 400);
		setVisible(true);
	}

	public void LoadTable(){
		try {
			// Creo la connessione col database
			Database db=new Database();
			Connection con = db.connessione();
			String q = "SELECT id, titolo, autore, pagine, isbn, scaffale,case disponibile when true then 'si' else 'no' end 'Stato' FROM Libri";
			Statement ps = con.createStatement();
			
			
			ResultSet rs = ps.executeQuery(q);

			String[] tableColumnsName = { "ID", "Titolo", "Autore",
					"Pagine", "ISBN", "Scaffale","Disponibile" };
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
