package gui;

/*Questa finestra viene visualizzata all'avvio del programma e serve ad affettuare il login di tutti gli utenti
 * del sistema. Nel caso in cui l'utente non sia registrato, il sistema permette di stampare un modulo pdf da consegnare
 * all'amministratore del sistema che aggiungerà l'utente*/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import componenti.Persona;

public class Login extends JFrame {
	private JLabel label1, label2;
	private JTextField text1, text2;
	private JButton button1, button2;
	private JPasswordField pf1;

	public Login() {
		super("Login Biblioteca");

		Container container = getContentPane();
		getContentPane().setLayout(null);

		label1 = new JLabel("User ID   ");
		label1.setBounds(17, 8, 68, 14);
		container.add(label1);
		text1 = new JTextField(25);
		text1.setBounds(85, 8, 206, 20);
		text1.setHorizontalAlignment(SwingConstants.LEFT);
		container.add(text1);

		label2 = new JLabel("Password");
		label2.setBounds(17, 33, 68, 14);
		container.add(label2);

		// pulsante per effettuare il login al sistema
		// questo pulsante richiama la classe Main e le passa un argomento
		// Persona
		button1 = new JButton("Login");
	      button1.setBounds(41, 64, 113, 23);
			button1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent actionevent){
					
					//dentro l'action listener
				String pass= String.valueOf(pf1.getPassword());
				
				Persona user = new Persona(text1.getText());
				
			
				
				if(pass.equals(user.getPassword())){
				Main main = new Main(user);
				main.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
				main.setVisible(true);
				setVisible(false);}else{
					JOptionPane.showMessageDialog(null,"Username e/o password errati.","Errore",JOptionPane.ERROR_MESSAGE);
					button1.setBackground(Color.red);
				}
				
				}});

		// Pulsante che, se cliccato, permette di accedere al form di stampa del
		// modulo di registrazione
		button2 = new JButton("Registra");
		button2.setBounds(164, 64, 113, 23);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				Registrazione reg = new Registrazione();
				reg.setVisible(true);
				reg.toFront();
			}
		});

		pf1 = new JPasswordField();
	       pf1.setBounds(85, 33, 206, 20);
	       container.add(pf1);

		container.add(button1);
		container.add(button2);
		setSize(320, 170);
		setVisible(true);

	}

	public static void main(String args[]) {

		Login application = new Login();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
