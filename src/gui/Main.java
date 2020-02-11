package gui;



import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import componenti.Amministratore;
import componenti.Bibliotecario;
import componenti.Persona;
import componenti.Utente;


public class Main extends JFrame {
	
	private String user , tipo;
	private JButton button1,button2,button3,button4,button5,button6,button7,button8;
	// set up GUI
	public Main(final Persona user)
	{
		super( "Biblioteca" );
		
		Container container = getContentPane() ;
		container.setLayout( null);
		
		//questa parte di programma serve a stabilire che tipo di utente ha effettuato l'accesso al sistema
		//per ogni tipo di utente verrà caricata un'interfaccia diversa
		
		switch(user.getTipo()){
		case "utente":
			//questa riga trasforma l'oggetto della superclasse Persona in un oggetto della classe Utente
			final Utente utente=new Utente(user.getId());//verificare questa parte
			
			
			
		    JButton button6= new JButton("Catalogo Libri");
		  //Richiamo della classe CatalogoLibri tramite click del button6
		 	button6.addActionListener(new ActionListener() {public void actionPerformed( ActionEvent event ) {
		 		CatalogoLibri catalogo=new CatalogoLibri(utente);
		 		
		 		catalogo.setVisible(true);
			}});
		    
		    container.add(button6);
		    button6.setBounds(140, 100, 130, 30);
		   
		    JButton button7= new JButton("Prestiti/Solleciti");
		    
		     //Richiamo della classe PrestitiUtente tramite click del sottomenù
		    
		 	button7.addActionListener(new ActionListener() {public void actionPerformed( ActionEvent event ) {
		 	PrestitiUtente prestitiutente=new PrestitiUtente(utente);
		 	
		 	prestitiutente.setVisible(true);
		 	}});
		 	
            container.add(button7);
            button7.setBounds(140, 140, 130, 30);
            
		  
		    
			break;
		
		case "bibliotecario":
			//questa riga trasforma l'oggetto della superclasse Persona in un oggetto della classe Bibliotecario
			final Bibliotecario bibliotecario=new Bibliotecario(user.getId());
			
			button1 = new JButton("Concessione Prestito");
			 //Richiamo della classe NuovoPrestito tramite click del button
			button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionevent){
			NuovoPrestito nuovoprestito = new NuovoPrestito(bibliotecario);
	        nuovoprestito.setVisible(true);
			nuovoprestito.toFront();
			}}); 
			
			button2 = new JButton("Registra Reso");
			 //Richiamo della classe RestituisciLibro tramite click del button
			button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionevent){
			RestituisciLibro restituzione = new RestituisciLibro(bibliotecario);
			restituzione.setVisible(true);
			restituzione.toFront();
			}}); 
			
			   button1.setBounds(120, 100, 170, 30);
			   button2.setBounds(120, 140, 170, 30);
			container.add( button1);
			container.add( button2);
			
		break;
		
		case "admin":
			//questa riga trasforma l'oggetto della superclasse Persona in un oggetto della classe Amministratore
			final Amministratore amministratore=new Amministratore(user.getId());
			
			button3 = new JButton("Riepilogo Utente");
			//Richiamo della classe RiepilogoUtenti tramite click del button
			button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionevent){
			RiepilogoUtenti riepilogoutenti = new RiepilogoUtenti(amministratore);
	      
			riepilogoutenti.setVisible(true);
			riepilogoutenti.toFront();
			}} );
			
			button4 = new JButton("Riepilogo Libri");
			//Richiamo della classe RiepilogoLibri tramite click del button
			button4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionevent){
			RiepilogoLibri riepilogolibri = new RiepilogoLibri(amministratore);
	      
			riepilogolibri.setVisible(true);
			riepilogolibri.toFront();
			}} );
			
			button8 = new JButton("Riepilogo Prestiti");
			//Richiamo della classe RiepilogoLibri tramite click del button
			button8.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionevent){
			RiepilogoPrestiti riepilogoprestiti = new RiepilogoPrestiti(amministratore);
	      
			riepilogoprestiti.setVisible(true);
			riepilogoprestiti.toFront();
			}} );
			
		
			
			
			button5=new JButton("Invio Solleciti");
			//Richiamo della classe RiepilogoUtenti tramite click del button
			button5.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent actionevent){
				amministratore.inviosolleciti();
				}} );
			  button3.setBounds(140, 100, 130, 30);
			   button4.setBounds(140, 140, 130, 30);
			   button5.setBounds(140, 220, 130, 30);
			   button8.setBounds(140, 180, 130, 30);
			  
			
			container.add( button3);
			container.add(button4);
			container.add(button8);
			container.add(button5);
		
			
			
	    break;
			
		}

		
      


		

		

		
		setSize( 411, 353 );
		setVisible( true );

	} 

} 
