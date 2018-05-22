package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConn {




    public String getSize(double gurt){
	    try{
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con=DriverManager.getConnection("jdbc:mysql://database:3306/ParcelService?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","reader","penis");
		    Statement stmt=con.createStatement();
		    ResultSet rs=stmt.executeQuery("select distinct size from ParcelSize where min <= " + gurt + " and max >= " + gurt);
		    rs.next();
		    String size =rs.getString(1);
		    con.close();
		    return size;
	    }
      catch(Exception e)
      {
        System.out.println(e);
      }
	    	return "";
    }

}
