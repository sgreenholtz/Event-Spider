package eventspider.database;
import java.sql.*;
import org.apache.log4j.Logger;
import eventspider.beans.User;

public class DBTest {
		
	public void run(User user) {
	  try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://138.68.1.193:3306/eventspider", "root", "TheHistoryOfAllHithert0!");
            String sql = "select * from Users where email='" + user.getEmail() + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("email"));
            }
          } catch (Exception e) {
          	e.printStackTrace();
          }
	}
}
