package it.corso.mercury;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Dao {
	private static Dao dao = null;
    private static Connection conn = null;
    
    private Dao(){
        InitialContext initContext =null;
        
        try{
        initContext = new InitialContext();
        
        Context envContext = (Context)initContext.lookup("java:/comp/env");

        DataSource ds = (DataSource)envContext.lookup("jdbc/db_mercury");

        conn=ds.getConnection();
        }
        catch (NamingException ex) {
            ex.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        } 
    }
    
    public static Dao getDao(){
        if(dao==null){
            dao=new Dao();
        }
        return dao;
    }
    public static Connection getConnection(){
        if(conn==null){
            dao=new Dao();
        }
        return conn;
    }

}
