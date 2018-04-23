import java.io.IOException;
import javax.swing.JOptionPane;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class Server {

	public static void main(String[] args) throws IllegalArgumentException, IOException {
		HttpServer server = HttpServerFactory.create( "http://localhost:8443/parcel" );
		server.start();
		//JOptionPane.showMessageDialog( null, "Ende" );
		//server.stop( 0 );

	}

}