package it.corso.mercury;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



public class EnteDAO

{
	
	public static void insertEnte(Ente e) 
	{
		Connection conn = Dao.getConnection();
		
		PreparedStatement st;
		try {
			String s="";
			s+= "insert into arc_utenti (email_ente, password, tipologia, stato_utente) values ('"+e.getEmail()+"','"+e.getPassword()+"','u','a');";
			st = conn.prepareStatement(s);
			int r= st.executeUpdate ();   
			s= "insert into tab_anagrafica_utenti (id_utente, nome_utente, p_iva_cod_fis) values (last_insert_id(),'"+e.getNomeEnte()+"','"+e.getPiCf()+"' );"; 
			st = conn.prepareStatement(s);
			r= st.executeUpdate ();  
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		}
	}
	



