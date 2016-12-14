package it.corso.mercury;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.corso.mercury.Evento;

public class EventoDao {
	
	public static long getIdCategoriaByNome(String nomeCategoria){
		Connection conn = Dao.getConnection();
		String query = "SELECT * FROM tab_categorie WHERE nome_categoria=?";
		        	
		try {
			
			PreparedStatement ps = conn.prepareStatement(query);	
			ps.setString(1, nomeCategoria);
			ResultSet rst = ps.executeQuery();
			rst.next();
			return rst.getLong("id_categoria");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	
		
	}
	public static void insertEvento(Evento e)
	    {
	        Connection conn=Dao.getConnection();
	        try {
	        	String s = "";
	        	
	        	//s += "SELECT * FROM arc_eventi ";
	        	//s += "JOIN tab_categorie ON(arc_eventi.id_categoria = tab_categorie.id_categoria) ";
	        	//s += "JOIN tab_comuni ON (arc_eventi.id_comune = tab_comuni.id_comune)";
	        	
	        	s+= "SELECT * FROM tab_categorie WHERE nome_categoria=?";
	        	PreparedStatement ps = conn.prepareStatement(s);	        	
	       	    ps.setString(1, e.getTipologia());
	        	ResultSet rst = ps.executeQuery();
	        	
	        	rst.next();
	        	long id_categoria = rst.getLong("id_categoria");
	        	
	        	s = "SELECT * FROM tab_comuni WHERE nome_comune=?";
	        	ps = conn.prepareStatement(s);        	
	        	ps.setString(1, e.getComune());
	        	
	            rst = ps.executeQuery();
	            rst.next();
	            long id_comune = rst.getLong("id_comune");
	            
	            s = "INSERT INTO arc_eventi (id_categoria,id_comune,nome_evento, descrizione,data_inizio,data_fine) VALUES (?,?,?,?,?,?)";
	            ps = conn.prepareStatement(s);
	            ps.setLong(1,id_categoria);
	            ps.setLong(2, id_comune);
	            ps.setString(3, e.getNome());
	            ps.setString(4, e.getDescrizione());
	            ps.setDate(5, new java.sql.Date(e.getDataInizio().getTime()));
	            ps.setDate(6, new java.sql.Date(e.getDataFine().getTime()));
	            
	            ps.executeUpdate();
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	
	//SCELTA 1 O 2 O 3 , RISPETTIVAMENTE id_locazione = id_comune O = id_provincia O = id_regione;
	public static ArrayList<Evento> searchEventoByLocazione(long id_categoria,long id_locazione,Date inizio, Date fine, int scelta){
		String query = "";
		ArrayList<Evento> returnList = new ArrayList<>();
		
		switch(scelta){
		
			case 1: query = "SELECT * FROM arc_eventi JOIN tab_categorie ON(arc_eventi.id_categoria = tab_categorie.id_categoria) WHERE arc_eventi.id_categoria = ? AND arc_eventi.id_comune = ? AND data_inizio >= ? AND data_fine <= ?";
					break;
				
			case 2: query = "SELECT * FROM arc_eventi JOIN tab_categorie ON(arc_eventi.id_categoria = tab_categorie.id_categoria) JOIN tab_comuni ON (arc_eventi.id_comune = tab_comuni.id_comune) ";
					query += "JOIN  tab_provincie ON (tab_comuni.id_provincia = tab_provincie.id_provincia) ";
					query += "WHERE arc_eventi.id_categoria = ? AND tab_provincie.id_provincia = ? AND data_inizio >= ? AND data_fine <= ?";
					break;
				
			case 3:	query = "SELECT * FROM arc_eventi JOIN tab_categorie ON(arc_eventi.id_categoria = tab_categorie.id_categoria) JOIN tab_comuni ON (arc_eventi.id_comune = tab_comuni.id_comune) ";
					query += "JOIN tab_provincie ON (tab_comuni.id_provincia = tab_provincie.id_provincia) ";
					query += "JOIN tab_regioni ON (tab_provincie.id_regione = tab_regioni.id_regione) ";
					query += "WHERE arc_eventi.id_categoria = ? AND tab_regioni.id_regione = ? AND data_inizio >= ? AND data_fine <= ?";
					break;
			case 4: return searchEventoAllLocazioni(id_categoria, inizio, fine, scelta);

			}
		
		PreparedStatement ps;
		try {
			
			ps = Dao.getConnection().prepareStatement(query);
			ps.setLong(1, id_categoria);
			ps.setLong(2, id_locazione);
			ps.setDate(3, inizio);
			ps.setDate(4, fine);
			ResultSet rst = ps.executeQuery();
			while(rst.next()){
				Evento e = new Evento();
				e.setNome(rst.getString("nome_evento"));
				e.setTipologia(rst.getString("nome_categoria"));
				e.setDescrizione(rst.getString("descrizione_evento"));
				e.setDataInizio(rst.getDate("data_inizio"));
				e.setDataFine(rst.getDate("data_fine"));
				e.setComune(rst.getString("nome_comune"));
				returnList.add(e);
			}
			return returnList;
			
		} catch (SQLException e) {
			e.printStackTrace();
			returnList.clear();
			return returnList;
		}	

		
	}
	public static ArrayList<Evento> searchEventoAllLocazioni(long id_categoria, Date inizio, Date fine, int scelta){
		ArrayList<Evento> returnList = new ArrayList<>();
		String 	query = "SELECT * FROM arc_eventi JOIN tab_categorie ON(arc_eventi.id_categoria = tab_categorie.id_categoria) JOIN tab_comuni ON (arc_eventi.id_comune = tab_comuni.id_comune) ";
				query += "JOIN tab_provincie ON (tab_comuni.id_provincia = tab_provincie.id_provincia) ";
				query += "JOIN tab_regioni ON (tab_provincie.id_regione = tab_regioni.id_regione) ";
				query += "WHERE arc_eventi.id_categoria = ?  AND data_inizio >= ? AND data_fine <= ?";
		
				PreparedStatement ps;
				try {
					
					ps = Dao.getConnection().prepareStatement(query);
					ps.setLong(1, id_categoria);
					ps.setDate(2, inizio);
					ps.setDate(3, fine);
					ResultSet rst = ps.executeQuery();
					rst = ps.executeQuery();
					while(rst.next()){
						Evento e = new Evento();
						e.setNome(rst.getString("nome_evento"));
						e.setTipologia(rst.getString("nome_categoria"));
						e.setDescrizione(rst.getString("descrizione_evento"));
						e.setDataInizio(rst.getDate("data_inizio"));
						e.setDataFine(rst.getDate("data_fine"));
						e.setComune(rst.getString("nome_comune"));
						returnList.add(e);
					}
					return returnList;
					
				} catch (SQLException e) {
					e.printStackTrace();
					returnList.clear();
					return returnList;
				}	
	}
	
	public static ArrayList<Evento> getEventiByNewsletterEmail(String email){
		String query = "SELECT * FROM arc_newsletter WHERE email_newsletter=?";
		Connection conn = Dao.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rst = ps.executeQuery();
			
			if(rst.next()){
				java.util.Date today = new java.util.Date();
				Date init = new Date(today.getTime());
				Date end;
				int giornoMax = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
				System.out.println(giornoMax);
				long millisMaxDay = TimeUnit.MILLISECONDS.convert(giornoMax, TimeUnit.DAYS);
				switch(rst.getInt("id_cadenza")){
				case 1 : 	end = init;
							break;
				case 2 : 	end = new Date((init.getTime() + TimeUnit.MILLISECONDS.convert(7, TimeUnit.DAYS)));
							break;
				case 3 :	end = new Date(today.getTime() + (millisMaxDay - today.getTime()));
							break;
				default : 	end = init;
							break;
				}
				
				System.out.println("da "+init+" a "+end);
				if(rst.getString("id_regione").equals("99999")) return EventoDao.searchEventoAllLocazioni(rst.getLong("id_categoria"), init, end, 4);
					if(rst.getString("id_provincia").equals("99999")) return  EventoDao.searchEventoByLocazione(rst.getLong("id_categoria"), rst.getLong("id_regione"), init, end, 3);
						if(rst.getString("id_comune").equals("99999")) return  EventoDao.searchEventoByLocazione(rst.getLong("id_categoria"), rst.getLong("id_provincia"), init, end, 2);
						else return EventoDao.searchEventoByLocazione(rst.getLong("id_categoria"), rst.getLong("id_comune"), init, end, 1);
			}
			return new ArrayList<Evento>();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Evento>();
		}
		
	}

}


