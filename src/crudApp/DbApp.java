package crudApp;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import dbConnection.DbConnection;
import dbConnection.DbOperations;
public class DbApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Enter Choice :");
			System.out.println("Press 1 for Insert operation");
			System.out.println("Press 2 for select operation");
			System.out.println("Press 3 for Update operation");
			System.out.println("Press 4 for Delete operation");
			System.out.println("Press 5 for Exit");
			int choice = sc.nextInt();
			try {
				Connection con = DbConnection.getConnection();
				if(choice==1) DbOperations.insertOperation(con);
				if(choice==2) {
					System.out.println("Enter SID :");
					int sid = sc.nextInt();
					DbOperations.selectOperation(con, sid);
				}
				if(choice==3) {
					System.out.println("Enter SID :");
					int sid = sc.nextInt();
					DbOperations.updateOpertation(con, sid);
					System.out.println("is it here");
				}
				if(choice==4) {
					System.out.println("Enter SID :");
					int sid = sc.nextInt();
					DbOperations.deleteOperation(con, sid);
				}
				if(choice==5) {
					sc.close();
					System.exit(0);
				}else {
					continue;
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}catch(IOException ie) {
				ie.printStackTrace();
			}finally {
				DbOperations.closeOperation();
			}
		}
	}

}
