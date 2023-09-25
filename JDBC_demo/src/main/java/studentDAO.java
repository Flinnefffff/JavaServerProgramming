import java.sql.Statement;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class studentDAO {
	public static void main(String args[])  {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mariadb://mariadb.vamk.fi/e2101065_java_demo", "e2101065", "DZCtWC5pEC2");
			System.out.println(conn);
			Statement statement = conn.createStatement();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO student VALUE(?, ?, ?)");
			ps.setInt(1,  1);
			ps.setString(2, "nguyen");
			ps.setString(3, "quoc huy");
			int result = ps. executeUpdate();
			
			
			//int result = statement.executeUpdate("INSERT INTO student VALUE(1, 'quoc huy', 'nguyen')");
			//int result = statement.executeUpdate("UPDATE student SET firstname='something' WHERE number=1");
			//int result = statement.executeUpdate("DELETE FROM student WHERE number = 1");
			ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
			while(resultSet.next()) {
				System.out.println("number: " + resultSet.getString(1));
				System.out.println("lastname: " + resultSet.getString(2));
				System.out.println("firstname: " + resultSet.getString(3));
			}
			System.out.println("result: " + result);
			ps.close();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
}