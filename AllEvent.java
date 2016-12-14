package it.corso.mercury;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AllEvent")
public class AllEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
		response.setContentType("Text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter())
		{
			//ArrayList<String> evento = new ArrayList<>();
			//String[] revento=request.getParameterValues("remail[]");
			Connection conn=DaoEnte.getConnection();
			
			String nome_utente = "Agencyea";
			//String query="select  a.email_ente, c.id_evento, c.nome_evento From  arc_utenti AS a JOIN asc_utenti_eventi AS b ON (a.id_utente = b.id_utente) JOIN arc_eventi AS c ON (b.id_evento = c.id_evento) WHERE email_ente = 'agencyea@libero.it' ";
			//String query="select  a.email_ente, c.id_evento, d.nome, c.nome_evento From  arc_utenti AS a JOIN asc_utenti_eventi AS b ON (a.id_utente = b.id_utente) JOIN arc_eventi AS c ON (b.id_evento = c.id_evento) JOIN tab_anagrafica_utenti AS d ON (a.id_utente = d.id_utente) WHERE d.nome = 'Agencyea' ";
			String query="select  a.email_ente, c.id_evento, d.nome, c.nome_evento From  arc_utenti AS a JOIN asc_utenti_eventi AS b ON (a.id_utente = b.id_utente) JOIN arc_eventi AS c ON (b.id_evento = c.id_evento) JOIN tab_anagrafica_utenti AS d ON (a.id_utente = d.id_utente) WHERE d.nome = '"+nome_utente+"' ";
			//String query="select  a.email_ente, c.id_evento, d.nome, c.nome_evento From  arc_utenti AS a JOIN asc_utenti_eventi AS b ON (a.id_utente = b.id_utente) JOIN arc_eventi AS c ON (b.id_evento = c.id_evento) JOIN tab_anagrafica_utenti AS d ON (a.id_utente = d.id_utente) WHERE d.nome = ' "+ nome_utente +" ' ";
			
			PreparedStatement ps;
			
			ResultSet rst;
			
			
			
			ps= conn.prepareStatement(query);
			rst = ps.executeQuery();
			
			while(rst.next())
			{ //scorre i volori della query
				out.println(rst.getString("email_ente"));
				out.println(rst.getString("nome"));
				
				out.println(rst.getString("nome_evento") + "<br>"); 
				
				
			}
			
			
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
	
	
	
	
	
    public AllEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
