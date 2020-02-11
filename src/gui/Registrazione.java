package gui;
/*questa classe permette la stampa di un modulo da consegnare all'amministratore per poter 
 * esser inseriti nel sistema*/
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class Registrazione extends JFrame {

	private JLabel label1, label2,label3,label4,label5,label6,label7,label8,label9;
	private JTextField text1,text2,text3,text4,text5,text6,text7,text8;
	private JButton button1;

	public Registrazione(){
		super("Registrazione");

		Container container = getContentPane();
		getContentPane().setLayout(null);

		
		label1 = new JLabel( "CF" );
		label1.setBounds(4, 8, 54, 14);
		container.add( label1 );
		text1=new JTextField(25);
		text1.setBounds(60, 5, 206, 20);
		container.add(text1);

		label2 = new JLabel( "Nome");
		label2.setBounds(4, 36, 54, 14);
		container.add( label2 );

		text2=new JTextField(25);
		text2.setBounds(60, 30, 206, 20);
		container.add(text2); 

		label3 = new JLabel( "Cognome" );
		label3.setBounds(4, 61, 54, 14);
		container.add( label3 );
		text3=new JTextField(25);
		text3.setBounds(60, 55, 206, 20);
		container.add(text3);

		label4 = new JLabel( "Telefono" );
		label4.setBounds(4, 86, 54, 14);
		container.add( label4 );
		text4=new JTextField(25);
		text4.setBounds(60, 80, 206, 20);
		container.add(text4);

		label5 = new JLabel( "e-mail" );
		label5.setBounds(4, 111, 54, 14);
		container.add( label5 );
		text5=new JTextField(25);
		text5.setBounds(60, 105, 206, 20);
		container.add(text5);

		label6 = new JLabel( "città" );
		label6.setBounds(4, 136, 54, 14);
		container.add( label6 );
		text6=new JTextField(25);
		text6.setBounds(60, 130, 206, 20);
		
		container.add(text6);

		label7 = new JLabel( "indirizzo" );
		label7.setBounds(4, 161, 54, 14);
		container.add( label7 );
		text7=new JTextField(25);
		text7.setBounds(60, 155, 206, 20);
		container.add(text7);

		label8 = new JLabel( "password" );
		label8.setBounds(4, 186, 66, 14);
		container.add( label8 );
		text8=new JTextField(25);
		text8.setBounds(60, 180, 206, 20);
		container.add(text8);

		
		
		button1 = new JButton("Stampa Modulo");
		button1.setBounds(85, 205, 137, 23);
		button1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent actionevent){
		try {
			JFileChooser file = new JFileChooser();
			file.setSelectedFile(new File(".pdf"));
			int x=file.showSaveDialog(null);
			if(x==0){
				
				Document document = new Document(PageSize.A4, 50, 50, 50, 50);
				
			PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(file.getSelectedFile()));
			document.open();
			document.add(new Paragraph("Biblioteca"));
			document.add(new Paragraph("Richiesta di registrazione"));
			document.add(new Paragraph("CF: "+text1.getText()));
			document.add(new Paragraph("Nome: "+text2.getText()));
			document.add(new Paragraph("Cognome: "+text3.getText()));
			document.add(new Paragraph("Telefono: " + text4.getText()));
			document.add(new Paragraph("e-mail: " + text5.getText()));
			document.add(new Paragraph("Città: "+ text6.getText()));
			document.add(new Paragraph("Indirizzo: "+ text7.getText()));
			document.add(new Paragraph("Password: "+text8.getText()));

			
			document.close();
			}} catch (DocumentException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}}); 
		container.add(button1);
		
		setSize( 292, 300 );
		setVisible( true );
	}


}
