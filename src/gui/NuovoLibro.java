package gui;
/*Questa classe visualizza un form da compilare per aggiungere un libro al sistema*/
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import componenti.Amministratore;


public class NuovoLibro extends JFrame{
private JLabel label1, label2,label3,label4,label5,label6,label7,label8,label9;
		private JTextField text1,text2,text3,text4,text5,text6,text7,text8;
		private JButton button1;
		private JComboBox comboBox;
		private JFrame frame;
	public NuovoLibro(final Amministratore amministratore){
		
		super("Nuovo Libro");
			Container container = getContentPane();
			getContentPane().setLayout(null);
			frame=this;
			label1 = new JLabel( "Titolo" );
			label1.setBounds(11, 14, 45, 14);
			container.add( label1 );
			text1=new JTextField(25);
			text1.setBounds(76, 8, 206, 20);
			container.add(text1);

			label2 = new JLabel( "Autore");
			label2.setBounds(11, 39, 55, 14);
			container.add( label2 );

			text2=new JTextField(25);
			text2.setBounds(76, 33, 206, 20);
			container.add(text2); 

			label3 = new JLabel( "Pagine" );
			label3.setBounds(11, 64, 62, 14);
			container.add( label3 );
			text3=new JTextField(25);
			text3.setBounds(76, 58, 206, 20);
			container.add(text3);

			label4 = new JLabel( "ISBN" );
			label4.setBounds(11, 89, 55, 14);
			container.add( label4 );
			text4=new JTextField(25);
			text4.setBounds(76, 83, 206, 20);
			container.add(text4);

			label5 = new JLabel( "Scaffale" );
			label5.setBounds(11, 114, 55, 14);
			container.add( label5 );
			text5=new JTextField(25);
			text5.setBounds(76, 108, 206, 20);
			container.add(text5);

			
			
			

			
			
			button1 = new JButton("Registra Libro");
			button1.setBounds(64, 154, 162, 23);
			
			//al click del button1 viene richiamato un metodo di amministratore che aggiunge al sistema le
			//info del libro passate al metodo tramite il vettore di stringhe info
			
			button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionevent){
				final String[] info={text1.getText(),text2.getText(),text3.getText(),text4.getText(),text5.getText()};
				amministratore.AggiungiLibro(info);
				RiepilogoLibri riepilogolibri=new RiepilogoLibri(amministratore);
				riepilogolibri.setVisible(true);
				frame.dispose();
			}});
			
			container.add(button1);
			
		
			
			setSize( 310, 240 );
			setVisible( true );
		
	}
}
