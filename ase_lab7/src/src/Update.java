import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;

@Path("/password")
public class Update extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Update() {
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		MongoClientURI uri =  new MongoClientURI("mongodb://chanti1913:santhosh@1993@ds019068.mlab.com:19068/blood");
		MongoClient client = new MongoClient(uri);
		
		DB db = client.getDB(uri.getDatabase());
		DBCollection students = db.getCollection("studentrecord");

		BasicDBObject query = new BasicDBObject();
		query.put("username", username);

		BasicDBObject updated = new BasicDBObject();
		updated.append("username", username);
		updated.append("password", password);
	
		BasicDBObject setQuery = new BasicDBObject();
		setQuery.append("$set", updated);
		
		students.update(query, updated);

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, DELETE, OPTIONS, HEAD");
		response.setHeader("Access-Control-Allow-Headers", "ContentType");
		response.setHeader("Access-Control-Max-Age", "86400");
	}
	
	@Override
	protected void doOptions(HttpServletRequest arg0, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doOptions(arg0, response);

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, DELETE, OPTIONS, HEAD");
		
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");
	}

}
