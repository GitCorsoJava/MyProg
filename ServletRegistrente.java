package it.corso.mercury;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletRegistrente
 */
@WebServlet("/ServletRegistrente")
public class ServletRegistrente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistrente() {
        super();
        // TODO Auto-generated constructor stub
    }
       
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
    	PrintWriter out=response.getWriter();
    	
    	  
    	response.setContentType("text/html;charset=UTF-8");
    	
    	
    	
    	
    	String nomente = request.getParameter("nomente");
    	String pIva = request.getParameter("codfisc");
    	String email = request.getParameter("email");
    	String password = request.getParameter("pwd1");
    	
    	Ente e = new Ente();
    	e.setEmail(email);
    	e.setPassword(password);
    	e.setNomeEnte(nomente);
    	e.setPiCf(pIva);
    	
    	System.out.println(e.getPassword());
    	
    	EnteDAO.insertEnte(e);
    	response.sendRedirect("okente.jsp");
    	
    		
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
