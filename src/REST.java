import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement; 

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path( "size" )
@Consumes( MediaType.APPLICATION_JSON  )
@Produces( MediaType.APPLICATION_JSON  )
public class REST
{
  @GET 
  public Response message()
  {
	Package parcel = new Package();
	parcel.length = 0;
	parcel.width = 0;
	parcel.depth = 7;
	parcel.cat = "penis";
	Gson gs = new Gson();
    //return parcel.toString();
	return Response.ok(gs.toJson(parcel, Package.class)).header("Access-Control-Allow-Origin", "*")
		      .header("Access-Control-Allow-Credentials", "true")
		      .header("Access-Control-Allow-Headers",
		         "origin, content-type, accept, authorization")
		       .header("Access-Control-Allow-Methods",
		         "GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
  }
  @POST
  public Response size(String json)
  {
	  Gson gs = new Gson();
	  Package parcel = gs.fromJson(json,Package.class);
	  if(parcel.length>parcel.width&&parcel.length>parcel.depth)
	  {
		  
	  }
	  else if(parcel.width>parcel.length&&parcel.width>parcel.depth)
	  {
		  int tmp = parcel.length;
		  parcel.length=parcel.width;
		  parcel.width=tmp;
	  }
	  else if(parcel.depth>parcel.length&&parcel.depth>parcel.width)
	  {
		  int tmp = parcel.length;
		  parcel.length=parcel.depth;
		  parcel.depth=tmp;
	  }
	  double gurt = parcel.length+2*parcel.width+2*parcel.depth;
	  try
	  {  
	    Class.forName("com.mysql.jdbc.Driver");  
	    Connection con=DriverManager.getConnection(  
	    "jdbc:mysql://localhost:3306/ParcelService","reader","penis");   
	    Statement stmt=con.createStatement();  
	    ResultSet rs=stmt.executeQuery("select distinct size from ParcelSize where min <= " + gurt + " and max >= " + gurt);  
	    rs.next();
	    String size =rs.getString(1);  
	    con.close();
	    parcel.cat=size;
	  }
	  catch(Exception e)
	  {
	    System.out.println(e);
	  } 
	  System.out.println("Send package: "+parcel);
	  return Response.status(200).entity(gs.toJson(parcel, Package.class)).header("Access-Control-Allow-Origin", "*")
		      .header("Access-Control-Allow-Credentials", "true")
		      .header("Access-Control-Allow-Headers",
		         "origin, content-type, accept, authorization")
		       .header("Access-Control-Allow-Methods",
		"GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
  }
  @OPTIONS
  public Response getOptions()
  {
    return Response.ok()
      .header("Access-Control-Allow-Origin", "*")
      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
  }
  
}