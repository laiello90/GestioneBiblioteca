package componenti;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Database {
	
	public Database(){

	}
	
// Creo la connessione col database
	
public Connection connessione(){
	try {
		String[] user = {"root","root"};
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca?user="+user[0]+"&password="+user[1]+"");
		return con;
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Errore",JOptionPane.ERROR_MESSAGE);
		return null;
	}
}

}
