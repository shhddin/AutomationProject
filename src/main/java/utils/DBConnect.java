package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DBConnect {
    public static Configuration configuration = Configuration.getInstance("configuration/configure.properties");

public static List<List<String>> dbList() {
	/*
	 * 1. Register the driver with the database 
	 * 2. Create a Connection - user name, pass word, localhost:port
	 * 3. Connection will return statement object 
	 * 4. We can pass sql query in the statement object 
	 * 5. Statement object will return ResultSet 
	 * 6. We can iterate the ResultSet and parse the data
	 */
	
	List<List<String>> lists = new ArrayList<>();
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Class.forName("org.postgresql.Driver"); //Mac PostgeSQL
		//DriverManager.registerDriver(new oracle.jdbc.OracleDriver());  // Alternative way of registering jdbc driver
		String dbConnectURL = configuration.getConfiguration("dbHost")+":"+configuration.getConfiguration("dbPort")+":"+configuration.getConfiguration("dbName");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@"+dbConnectURL, configuration.getConfiguration("dbUser"), configuration.getConfiguration("dbPass"));
		// instead of jdbc:oracle:thin:@"+dbConnectURL , use - jdbc:postgresql://host:port/database
		Statement statement = connection.createStatement();
		statement.executeQuery(configuration.getConfiguration("dbQuery"));
		ResultSet resultSet = statement.getResultSet();
		ResultSetMetaData metaData = resultSet.getMetaData();
		while (resultSet.next()) {
			List<String> list = new ArrayList<>();
			for (int i = 0; i < metaData.getColumnCount(); i++) {
				list.add(resultSet.getString(i + 1));
			}
			lists.add(list);
		}
		resultSet.close();
		statement.close();
		connection.close();
	} catch (SQLException | ClassNotFoundException e) {
		e.printStackTrace();
	}
	return lists;
}

public static void main(String[] args) {
	for (List<String> list : dbList()) {
		System.out.println(list);
	}
}
}

	