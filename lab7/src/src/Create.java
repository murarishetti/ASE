import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;

@Path("/new")
public class Create extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Create() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**/
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		System.out.println(data);
		
		JSONObject params = new JSONObject(data);
		BasicDBObject user = new BasicDBObject();
		
		for(Object key:params.keySet().toArray())
		{
			user.put(key.toString(),params.get(key.toString()));
		}
		System.out.println(user.toJson());
		
		MongoClientURI uri = new MongoClientURI("mongodb://chanti1913:santhosh@1993@ds019068.mlab.com:19068/blood");
		MongoClient client = new MongoClient(uri);
		
		DB db = client.getDB(uri.getDatabase());
		DBCollection students = db.getCollection("studentrecord");
		WriteResult result = students.insert(user);
	
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, HEAD, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");
		response.getWriter().write(result.toString());
	}
	
	@Override
	protected void doOptions(HttpServletRequest arg0, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doOptions(arg0, response);

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, HEAD, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");
	}
}
