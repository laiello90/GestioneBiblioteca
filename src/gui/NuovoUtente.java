package gui;
/*Questa classe visualizza un form da compilare per aggiungere un utente al sistema*/
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import componenti.Amministratore;

public class NuovoUtente extends JFrame{
	private JLabel label1, label2,label3,label4,label5,label6,label7,label8,label9;
	private JTextField text1,text2,text3,text4,text5,text6,text7,text8;
	private JButton button1;
	private JComboBox comboBox;
	private JFrame frame;
	public NuovoUtente(final Amministratore amministratore){
		super("Aggiunta nuovo utente");

		Container container = getContentPane();
		getContentPane().setLayout(null);
		frame=this;
		label1 = new JLabel( "CF" );
		label1.setBounds(11, 14, 27, 14);
		container.add( label1 );
		text1=new JTextField(25);
		text1.setBounds(76, 8, 206, 20);
		container.add(text1);

		label2 = new JLabel( "Nome");
		label2.setBounds(11, 39, 45, 14);
		container.add( label2 );

		text2=new JTextField(25);
		text2.setBounds(76, 33, 206, 20);
		container.add(text2); 

		label3 = new JLabel( "Cognome" );
		label3.setBounds(11, 64, 55, 14);
		container.add( label3 );
		text3=new JTextField(25);
		text3.setBounds(76, 58, 206, 20);
		container.add(text3);

		label4 = new JLabel( "Telefono" );
		label4.setBounds(11, 89, 55, 14);
		container.add( label4 );
		text4=new JTextField(25);
		text4.setBounds(76, 83, 206, 20);
		container.add(text4);

		label5 = new JLabel( "e-mail" );
		label5.setBounds(11, 114, 45, 14);
		container.add( label5 );
		text5=new JTextField(25);
		text5.setBounds(76, 108, 206, 20);
		container.add(text5);

		label6 = new JLabel( "città" );
		label6.setBounds(11, 139, 45, 14);
		container.add( label6 );
		text6=new JTextField(25);
		text6.setBounds(76, 133, 206, 20);
		
		container.add(text6);

		label7 = new JLabel( "indirizzo" );
		label7.setBounds(11, 164, 55, 14);
		container.add( label7 );
		text7=new JTextField(25);
		text7.setBounds(76, 158, 206, 20);
		container.add(text7);

		label8 = new JLabel( "password" );
		label8.setBounds(10, 189, 68, 14);
		container.add( label8 );
		text8=new JTextField(25);
		text8.setBounds(76, 183, 206, 20);
		container.add(text8);
		
		label9 = new JLabel( "tipo" );
		label9.setBounds(11, 214, 42, 14);
		container.add( label9 );
		comboBox = new JComboBox();
		comboBox.setBounds(76, 211, 96, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"utente", "bibliotecario", "admin"}));
		getContentPane().add(comboBox);
		
		button1 = new JButton("Registra utente");
		button1.setBounds(67, 239, 162, 23);
		//al click del button1 viene richiamato un metodo di amministratore che aggiunge al sistema le
		//info del nuovo utente passate al metodo tramite il vettore di stringhe info
		button1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent actionevent){
			final String[] info={text1.getText(),text2.getText(),text3.getText(),text4.getText(),text5.getText(),text6.getText(),text7.getText(),text8.getText(),comboBox.getSelectedItem().toString()};
			amministratore.AggiungiUtente(info);
			RiepilogoUtenti riepilogoutenti=new RiepilogoUtenti(amministratore);
			riepilogoutenti.setVisible(true);
			frame.dispose();
		}});
		
		container.add(button1);
		
	
		
		setSize( 310, 325 );
		setVisible( true );
	}
}
