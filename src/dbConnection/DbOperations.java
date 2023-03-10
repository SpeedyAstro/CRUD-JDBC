package dbConnection;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class DbOperations {
//	Connection con = null;
//	static Connection con = DbConnection.getConnection();
	static PreparedStatement statement = null;
	static ResultSet resultset = null;
	public static void insertOperation(Connection con) throws SQLException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name :");
		String name = sc.next();
		System.out.println("Enter age :");
		int age = sc.nextInt();
		System.out.println("Enter gender - [M/F] :");
		String gender = sc.next();
		statement = con.prepareStatement("insert student (sname,sage,gender) values (?,?,?)");
		statement.setString(1, name);
		statement.setInt(2, age);
		statement.setString(3, gender);
		int rowaffected = statement.executeUpdate();
		if(rowaffected == 0) System.out.println("record Insertion Failed!!");
		else System.out.println("record Inserted Successfully , rowaffected :"+rowaffected);
	}
	
	public static void selectOperation(Connection con,int sid) throws SQLException {
		statement = con.prepareStatement("select sname,sage,gender from student where sid=?");
		statement.setInt(1, sid);
		resultset = statement.executeQuery();
		if(resultset.next()) {
			System.out.println("sname\tsage\tgender");
			System.out.println(resultset.getString(1)+"\t"+resultset.getInt(2)+"\t"+resultset.getString(3));
		}else {
			System.out.println("record not available of given ID");
		}
	}
	public static void updateOpertation(Connection con,int sid) throws SQLException {
		selectOperation(con,sid);
		Scanner sc = new Scanner(System.in);
		String query = "update student set ";
		System.out.println("want to change name ? [y/n]");
		String choice1 = sc.next();
		if(choice1.equals("y")) {
			System.out.println("Enter new Name :");
			String name = sc.next()	;
			query = String.format(query+"sname='%s'", name);
		}
		System.out.println("want to change age ? [y/n]");
		String choice2 = sc.next();
		if(choice2.equals("y")) {
			if(choice1.equals("y")) query += ", ";
			System.out.println("Enter new age :");
			int age = sc.nextInt();
			query = String.format(query+"sage=%d", age);
		}
		System.out.println("want to change gender ? [y/n]");
		String choice3 = sc.next();
		if(choice3.equals("y")) {
			if(choice2.equals("y")) query += ", ";
			System.out.println("Enter gender -> [M/F] :");
			String gender = sc.next();
			query = String.format(query+"gender='%s'", gender);
		}
		if(choice1.equals("y")|| choice2.equals("y") || choice3.equals("y")) {
			query = String.format(query+" where sid=%d", sid);
		}else {
			System.out.println("No choices made!!");
			System.exit(0);
		}
		sc.close();
		System.out.println(query);
		PreparedStatement statement = con.prepareStatement(query);
		int rowaffected = statement.executeUpdate();
		if(rowaffected == 0) System.out.println("record Insertion Failed!!");
		else System.out.println("record Inserted Successfully , rowaffected :"+rowaffected);
	}
	public static void deleteOperation(Connection con, int sid) throws SQLException {
		PreparedStatement ppt = con.prepareStatement("delete from student where sid=?");
		ppt.setInt(1, sid);
		int affect = ppt.executeUpdate();
		if(affect == 0) System.out.println("record Insertion Failed!!");
		else System.out.println("record Inserted Successfully , rowaffected :"+affect);
	}
	public static void closeOperation() {
		try {			
			DbConnection.cleanUp(null, resultset, statement);
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}
}
