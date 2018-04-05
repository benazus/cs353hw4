import java.sql.*;
import java.util.ArrayList;

/*
    Compilation:
    javac -cp ".;./mysql_connector.jar" Program.java 
    java -cp ".;./mysql_connector.jar" Program
*/

public class Program {
    public static void main(String[] args) {
        String url = "jdbc:mysql://dijkstra.ug.bcc.bilkent.edu.tr/berat_bicer?allowMultiQueries=true";
        String username = "berat.bicer";
        String password = "jfb4ia01";

        System.out.println("Connecting database...");

        java.sql.Connection connection = EstablishConnection(url, username, password);
        if(connection == null) {
          	System.out.println("Cannot connect to database, terminating...");
          	System.exit(1);
        }

        ArrayList<String> tables = new ArrayList<String>();
        ArrayList<String> create_tables = new ArrayList<String>();
        ArrayList<String> insert_customer = new ArrayList<String>();
        ArrayList<String> insert_account = new ArrayList<String>();
        ArrayList<String> insert_owns = new ArrayList<String>();
        ArrayList<String> drop = new ArrayList<String>();
        ArrayList<String> content_tables = new ArrayList<String>();

        tables.add("customer");
        tables.add("account");
        tables.add("owns");

        String customer_definition = "CREATE TABLE customer (\n"
            + "    cid CHAR(12) primary key,\n"
            + "    name VARCHAR(50),\n"
            + "    bdate DATE,\n"
            + "    profession varchar(25),\n"
            + "    address varchar(50),\n"
            + "    city varchar(20),\n"
            + "    nationality varchar(20)\n"
            + ") ENGINE=InnoDB;";

        String account_definition = "CREATE TABLE account (\n"
        + "    aid CHAR(8) primary key,\n"
        + "    branch VARCHAR(20),\n"
        + "    balance float,\n"
        + "    openDate DATE\n"
        + ") ENGINE=InnoDB;";

        String owns_definition = "CREATE TABLE owns (\n"
        + "    cid CHAR(12) not null,\n"
        + "    aid VARCHAR(8) not null,\n"
        + "    primary key(cid, aid),\n"
        + "    foreign key(cid) references customer(cid),\n"
        + "    foreign key(aid) references account(aid)\n"
        + ") ENGINE=InnoDB;";

        create_tables.add(customer_definition);
        create_tables.add(account_definition);
        create_tables.add(owns_definition);

        String insert_customer1 = "INSERT INTO customer VALUES ('20000001', 'cem',"
            + " STR_TO_DATE('10.10.1980', '%d.%m.%Y'), 'Engineer', 'Tunali', 'Ankara', 'TC');";

        String insert_customer2 = "INSERT INTO customer VALUES ('20000002', 'asli',"
            + " STR_TO_DATE('08.09.1985', '%d.%m.%Y'), 'Teacher', 'Nisantasi', 'Istanbul', 'TC');";

        String insert_customer3 = "INSERT INTO customer VALUES ('20000003', 'ahmet',"
            + " STR_TO_DATE('11.02.1995', '%d.%m.%Y'), 'Salesman', 'Karsiyaka', 'Izmir', 'TC');";

        String insert_customer4 = "INSERT INTO customer VALUES ('20000004', 'john',"
            + " STR_TO_DATE('16.04.1990', '%d.%m.%Y'), 'Architect', 'Kizilay', 'Ankara', 'ABD');";

        insert_customer.add(insert_customer1);
        insert_customer.add(insert_customer2);
        insert_customer.add(insert_customer3);
        insert_customer.add(insert_customer4);

        String insert_account1 = "INSERT INTO account VALUES ('A0000001', 'Kizilay',"
            + " 2000, STR_TO_DATE('01.01.2009', '%d.%m.%Y'));";

        String insert_account2 = "INSERT INTO account VALUES ('A0000002', 'Bilkent',"
            + " 8000, STR_TO_DATE('01.01.2011', '%d.%m.%Y'));";

        String insert_account3 = "INSERT INTO account VALUES ('A0000003', 'Cankaya',"
            + " 4000, STR_TO_DATE('01.01.2012', '%d.%m.%Y'));";

        String insert_account4 = "INSERT INTO account VALUES ('A0000004', 'Sincan',"
            + " 1000, STR_TO_DATE('01.01.2012', '%d.%m.%Y'));";

        String insert_account5 = "INSERT INTO account VALUES ('A0000005', 'Tandogan',"
            + " 3000, STR_TO_DATE('01.01.2013', '%d.%m.%Y'));";

        String insert_account6 = "INSERT INTO account VALUES ('A0000006', 'Eryaman',"
            + " 5000, STR_TO_DATE('01.01.2015', '%d.%m.%Y'));";

        String insert_account7 = "INSERT INTO account VALUES ('A0000007', 'Umitkoy',"
            + " 6000, STR_TO_DATE('01.01.2017', '%d.%m.%Y'));";

        insert_account.add(insert_account1);
        insert_account.add(insert_account2);
        insert_account.add(insert_account3);
        insert_account.add(insert_account4);
        insert_account.add(insert_account5);
        insert_account.add(insert_account6);
        insert_account.add(insert_account7);

        String insert_owns1 = "INSERT INTO owns VALUES ('20000001', 'A0000001');";
        String insert_owns2 = "INSERT INTO owns VALUES ('20000001', 'A0000002');";
        String insert_owns3 = "INSERT INTO owns VALUES ('20000001', 'A0000003');";
        String insert_owns4 = "INSERT INTO owns VALUES ('20000001', 'A0000004');";
        String insert_owns5 = "INSERT INTO owns VALUES ('20000002', 'A0000002');";
        String insert_owns6 = "INSERT INTO owns VALUES ('20000002', 'A0000003');";
        String insert_owns7 = "INSERT INTO owns VALUES ('20000002', 'A0000005');";
        String insert_owns8 = "INSERT INTO owns VALUES ('20000003', 'A0000006');";
        String insert_owns9 = "INSERT INTO owns VALUES ('20000003', 'A0000007');";
        String insert_owns10 = "INSERT INTO owns VALUES ('20000004', 'A0000006');";

        insert_owns.add(insert_owns1);
        insert_owns.add(insert_owns2);
        insert_owns.add(insert_owns3);
        insert_owns.add(insert_owns4);
        insert_owns.add(insert_owns5);
        insert_owns.add(insert_owns6);
        insert_owns.add(insert_owns7);
        insert_owns.add(insert_owns8);
        insert_owns.add(insert_owns9);
        insert_owns.add(insert_owns10);

        String drop_customer = "DROP TABLE customer;";
        String drop_account = "DROP TABLE account;";
        String drop_owns = "DROP TABLE owns;";

        
        drop.add("SET FOREIGN_KEY_CHECKS=0;");
        drop.add(drop_customer);
        drop.add(drop_account);
        drop.add(drop_owns);
        drop.add("SET FOREIGN_KEY_CHECKS=1;");

        
        content_tables.add("SELECT * FROM account;");
        content_tables.add("SELECT * FROM owns;");

        try {
  	        Execute(connection, create_tables);
          	ExecuteUpdate(connection, insert_customer);
          	ExecuteUpdate(connection, insert_account);
            ExecuteUpdate(connection, insert_owns);

            System.out.println("CUSTOMER");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer;");
            ResultSetMetaData metadata = rs.getMetaData();
            int column_no = metadata.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= column_no; i++)
                    System.out.print(metadata.getColumnName(i) + ": " + rs.getString(i) + "; ");
                System.out.println();
            }

            System.out.println("\nACCOUNT");
            Statement stmt1 = connection.createStatement();
            ResultSet rs1 = stmt1.executeQuery("SELECT * FROM account;");
            ResultSetMetaData metadata1 = rs1.getMetaData();
            int column_no1 = metadata1.getColumnCount();
            while (rs1.next()) {
                for (int i = 1; i <= column_no1; i++)
                    System.out.print(metadata1.getColumnName(i) + ": " + rs1.getString(i) + "; ");
                System.out.println();
            }

            System.out.println("\nOWNS");
            Statement stmt2 = connection.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM owns;");
            ResultSetMetaData metadata2 = rs2.getMetaData();
            int column_no2 = metadata2.getColumnCount();
            while (rs2.next()) {
                for (int i = 1; i <= column_no2; i++)
                    System.out.print(metadata2.getColumnName(i) + ": " + rs2.getString(i) + "; ");
                System.out.println();
            }
        } catch(Exception e) {}

        System.out.println("Main is terminating...");

        try {
            connection.close();
        } catch(Exception e) {}
    }

    static java.sql.Connection EstablishConnection(String url, String username, String password) throws IllegalStateException {
    	java.sql.Connection connection = null;
    	try {
    		connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

    	if(connection != null)
    		return connection;
    	else {
    		System.out.println("Connection failed.");
    		return null;
    	}
    }

    static void Execute(java.sql.Connection connection, ArrayList<String> query) throws SQLException {
    	for(int i = 0; i < query.size(); i++) {
    		Statement statement = null;
	    	try {
	    		statement = connection.createStatement();
	    		statement.execute(query.get(i));
	    	} catch(Exception e) {
	    		
	        } finally {
	        	if(statement != null)
	        		statement.close();
	        }
    	}
    } 

    static ArrayList<Integer> ExecuteUpdate(java.sql.Connection connection, ArrayList<String> query) throws SQLException {
    	ArrayList<Integer> result = new ArrayList<Integer>();
    	for(int i = 0; i < query.size(); i++) {
    		Statement statement = null;
	    	try {
	    		statement = connection.createStatement();
	    		int rs = statement.executeUpdate(query.get(i));
	    		result.add(rs);
	    	} catch(Exception e) {
	    		
	        } finally {
	        	if(statement != null)
	        		statement.close();
	        }
    	}

    	return result;
    }
}