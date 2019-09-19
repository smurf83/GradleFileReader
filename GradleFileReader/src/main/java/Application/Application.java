package Application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;


public class Application {

	public static void main(String[] args) throws SQLException {
		//Establish DB Connection
		Connection con = HSQLDBConnection.getConnection();
		System.out.println("Connection Obtained");
		Statement stmt = null;
		
		JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader("src/main/resources/logfile.txt"));
            Gson gson = new GsonBuilder().create();
            reader.setLenient(true);
          //Parse to Event Class
            Type listTypeRole = new TypeToken<List<Event>>() {}.getType();
			List<Event> listEvents = gson.fromJson(reader, listTypeRole);
            
            for(Event evt: listEvents) {
            	System.out.println("Object mode: " + evt);
            }
            
            insertEvents(con, listEvents);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Gson.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(Gson.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
		
		
		
		//Process List of Events
		
		//Event event = new Event("scsmbstgra", "STARTED", "APPLICATION_LOG", "12345", "1491377495212");
		
		
		//selectStatement(con);
		
	}
	
	protected static void createTable(Connection con, Statement stmt) {
		  int result = 0;
			
	      try {
	         
	         stmt = con.createStatement();
	         
	         result = stmt.executeUpdate("CREATE TABLE events_tbl (id VARCHAR(500) NOT NULL, state VARCHAR(50) NOT NULL, type VARCHAR(100) NOT NULL, host VARCHAR(100) NOT NULL, duration TIMESTAMP, PRIMARY KEY (id)); ");
				
	      }  catch (Exception e) {
	         e.printStackTrace(System.out);
	      }
	      System.out.println("HSQLDB: Table created successfully");
	}

	public static long insertEvents(Connection conn, List<Event> eventList) {
		
		
        long id = 0;
 
        for (Event event: eventList) {
			String SQL = "INSERT INTO events_tbl ('id','state', 'type', 'host', 'duration') VALUES (?, ?, ?, ?, ?)";
			
			try (PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

				pstmt.setString(1, event.getId());
				pstmt.setString(2, event.getState());
				pstmt.setString(3, event.getType());
				pstmt.setString(4, event.getHost());
				pstmt.setString(5, event.getDuration());

				int affectedRows = pstmt.executeUpdate();
				// check the affected rows 
				if (affectedRows > 0) {
					// get the ID back
					try (ResultSet rs = pstmt.getGeneratedKeys()) {
						if (rs.next()) {
							id = rs.getLong(1);
						}
					} catch (SQLException ex) {
						System.out.println(ex.getMessage());
					}
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			} 
		}
		System.out.println(id+" rows effected"); 
	    System.out.println("Rows inserted successfully");
	    
        return id;
    }

	/*
	 * protected static void selectStatement(Connection con) { try {
	 * 
	 * PreparedStatement ps = con.
	 * prepareStatement("select id, state, type, host, timestamp from events_tbl");
	 * ResultSet rs = ps.executeQuery(); while (rs.next()) {
	 * System.out.println("ID = " + rs.getInt("id") + ", state = " +
	 * rs.getString("state") + ", type = " + rs.getString("type") +
	 * ", Submission Date = " + rs.getDate("timestamp")); } rs.close(); con.close();
	 * } catch (SQLException e1) { e1.printStackTrace(); } }
	 */
}
