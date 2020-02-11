package componenti;
import javax.mail.*;
import java.util.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;
import javax.activation.*;
import java.io.*;
public class Email {
	


	
	  String host = "smtp.gmail.com"; //tuo smtp
	  String from = "bibliotecaavc@gmail.com"; //tuo indirizzo email
	  String ToAddress = "bibliotecaavc@gmail.com"; //destinatario
	  String user = "bibliotecaavc@gmail.com";
	  String pass = "biblioteca15";
	  
	  public Email(String email,String titolo) {
	    try {
	    	ToAddress=email;
	    	
	      //initialize the StringBuffer object within the try/catch loop
	      StringBuffer sb = new StringBuffer( );
	      
	      //Get system properties
	      Properties props = System.getProperties( );
	    
	      //Setup mail server
	      props.put("mail.smtp.host", host);
	      props.put("mail.debug", "true");
	      props.put("mail.smtp.auth","true");
	      props.put("mail.smtp.starttls.enable","true");
	      //Get session
	      Session session = Session.getDefaultInstance(props, null);
	      session.setDebug(true);
	      session.setPasswordAuthentication(new URLName("smtp",host,25,"INBOX",user,pass), new PasswordAuthentication(user,pass));
	      
	      //Define message
	      MimeMessage msg = new MimeMessage(session);
	      //Set the from address
	      msg.setFrom(new InternetAddress(from));
	      //Set the to address
	      msg.addRecipient(Message.RecipientType.TO, new InternetAddress(ToAddress));
	      //Set the subject
	      msg.setSubject("Sollecito bilioteca.");
	      //Set the text content for body
	      sb.append("Gentile utente la preghiamo di restituire al più presto il seguente volume da lei preso in prestito.\n\n");
	      sb.append(""+titolo+" . \n\n");
	      sb.append("questo messaggio è generato automaticamente.\n\n");
	     
	      msg.setText(sb.toString( ));  
	      //Send message
	      Transport tr = session.getTransport("smtp");
	      tr.connect(host, user, pass);
	      msg.saveChanges(); // don't forget this
	      tr.sendMessage(msg, msg.getAllRecipients());
	      tr.close();
	    }
	    catch (MessagingException e) {
	      System.out.println(e);
	  	JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Errore",JOptionPane.ERROR_MESSAGE);
	    }
	  } 
	  
	 
	  public Email(String [] info) {
		    try {
		    	ToAddress=info[4];
		    	
		      //initialize the StringBuffer object within the try/catch loop
		      StringBuffer sb = new StringBuffer( );
		      
		      //Get system properties
		      Properties props = System.getProperties( );
		    
		      //Setup mail server
		      props.put("mail.smtp.host", host);
		      props.put("mail.debug", "true");
		      props.put("mail.smtp.auth","true");
		      props.put("mail.smtp.starttls.enable","true");
		      //Get session
		      Session session = Session.getDefaultInstance(props, null);
		      session.setDebug(true);
		      session.setPasswordAuthentication(new URLName("smtp",host,25,"INBOX",user,pass), new PasswordAuthentication(user,pass));
		      
		      //Define message
		      MimeMessage msg = new MimeMessage(session);
		      //Set the from address
		      msg.setFrom(new InternetAddress(from));
		      //Set the to address
		      msg.addRecipient(Message.RecipientType.TO, new InternetAddress(ToAddress));
		      //Set the subject
		      msg.setSubject("Sollecito bilioteca.");
		      //Set the text content for body
		      sb.append("Gentile utente le diamo il benvenuto nella nostra biblioteca.\n\n");
		      sb.append("le sue credenziali sono \n\n");
		      sb.append("Username: "+info[0]+" Password: "+info[7]+".\n\n");
		     
		      msg.setText(sb.toString( ));  
		      //Send message
		      Transport tr = session.getTransport("smtp");
		      tr.connect(host, user, pass);
		      msg.saveChanges(); // don't forget this
		      tr.sendMessage(msg, msg.getAllRecipients());
		      tr.close();
		    }
		    catch (MessagingException e) {
		      System.out.println(e);
		  	JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Errore",JOptionPane.ERROR_MESSAGE);
		    }
		  } 
}
