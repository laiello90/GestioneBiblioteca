package componenti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Persona {
	protected static String id;
	protected String nome;
	protected String cognome;
	protected String telefono;
	protected String email;
	protected String città;
	protected String indirizzo;
	protected String password;
	protected String tipo;
	protected Connection con;

	public Persona(String ID) {
		try {

			// Creo la connessione col database
			Database db = new Database();
			con = db.connessione();

			String q = "SELECT * FROM Utenti where ID = ? ";
			PreparedStatement ps = con.prepareStatement(q);

			ps.setString(1, ID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getString(1);
				nome = rs.getString(2);
				cognome = rs.getString(3);
				telefono = rs.getString(4);
				email = rs.getString(5);
				città = rs.getString(6);
				indirizzo = rs.getString(7);
				password = rs.getString(8);
				tipo = rs.getString(9);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getPassword() {
		return password;

	}

	public String getTipo() {
		return tipo;
	}

	public static String getId() {
		return id;
	}

}
