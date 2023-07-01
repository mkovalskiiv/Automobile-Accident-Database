//
// Michael Kovalski
// 01711761
// CIS452
// 
// Final Project
// 

import java.sql.*;
import java.util.Scanner;

public class finalProj2 {
	final static String toMain[] = {"go", "to", "main"};	//Random gibberish to pass as arguments to main
	final static String driverClass = "org.sqlite.JDBC";	//Initialize JDBC driver class
	final static String url = "jdbc:sqlite:autosDB.sqlite";	//--Now functionally works with relative file path! 
															//--(I used to need the entire system file path here)
	
	public static void main(String[] argsv)
	{
		
		// Print main text blurb
		System.out.println("\n\n\nWelcome to the Autos Database Management program.");
		System.out.println("Please select a mode of operation (case sensitive):");
		System.out.println("  - Type 'a' to add an accident to the database");
		System.out.println("  - Type 'e' to add a vehicle to an existing accident's database entry");
		System.out.println("  - Type 'f' to find an accident record with an accident ID#");
		System.out.println("  - Type 'r' to search for an accident within a range of dates");
		System.out.println("  - Type 'v' to search for an accident within a range of average damages");
		System.out.println("  - Type 'd' to search for an accident within a range of total damages");
		System.out.println("  - Type 'q' to exit");
		System.out.println("\nPlease enter your choice now: ");
		
		// Take and check user input
		Scanner scanner_main = new Scanner(System.in);  			// Create a Scanner object
	    String userChoice = scanner_main.nextLine();  				// Read user input
	    System.out.println("User chose: " + userChoice);  	// DEBUG
		
	    // Go to correct mode of operation per user choice
	    if (userChoice.equals("a")) {			//Works!
	    	input_a();
	    } else if (userChoice.equals("e")) {	//Works!
	    	input_e();
	    } else if (userChoice.equals("f")) {	//Works!
	    	input_f();
	    } else if (userChoice.equals("r")) {	//Works!
	    	input_r();
	    } else if (userChoice.equals("v")) {	//Works, but unable to list vehicles involved due to SQL query constraints...
	    	input_v();
	    } else if (userChoice.equals("d")) {	//Works, but unable to list vehicles involved due to SQL query constraints...
	    	input_d();
	    } else if (userChoice.equals("q")) {	//Works!
	    	input_q();
	    } else {								//Works!
	    	input_error();
	    }//if-else

	}//main
	
	public static void input_a()
	{
		//input_a: Let user add new accident to database, with at least one vehicle being added as well
		
		
		Connection connection_a = null;
		Scanner scanner_a = new Scanner(System.in);
		
		try {
			// Load the JDBC drivers
			Class.forName(driverClass);

			// Open a DB Connection
			connection_a = DriverManager.getConnection(url);

			// Create a Statement
			Statement stmnt_a = connection_a.createStatement();		// ONLY USE ONE
																	// OF THESE!!!!!!!
			
			
			System.out.println("\n\n\n\nYou may now begin entering a new accident record. Input 'q' at any time to return to the main menu.");
			
			
			System.out.println("\nEnter the accident's ID#:");
		    String insert_aid = scanner_a.nextLine();  				// Read user input
		    //System.out.println("insert_aid set to: " + insert_aid);  	// DEBUG
		    if (insert_aid.equals("q")) {
		    	main(toMain);
		    }

			System.out.println("Enter the new accident's date (format YYYY-MM-DD):");
			String insert_date = scanner_a.nextLine();  			// Read user input
			//System.out.println("insert_date set to: " + insert_date);  	// DEBUG
			if (insert_date.equals("q")) {
				main(toMain);
			}
			    
			System.out.println("Enter the city the new accident happened in:");
			String insert_city = scanner_a.nextLine();  			// Read user input
			//System.out.println("insert_city set to: " + insert_city);  	// DEBUG
			if (insert_city.equals("q")) {
			    main(toMain);
			}
			    
			System.out.println("Enter the state the new accident happened in (2-letter abbreviations):");
			String insert_state = scanner_a.nextLine();  			// Read user input
			//System.out.println("insert_state set to: " + insert_state); // DEBUG
			if (insert_city.equals("q")) {
			   	main(toMain);
			}

		    System.out.println("(Note: only input one vehicle at a time. To input another vehicle for the same accident, please use the 'e' process from the main menu.)");
		    System.out.println("Enter the VIN number of the vehicle involved:");
		    String insert_vin = scanner_a.nextLine();  			// Read user input
		    //System.out.println("insert_vin set to: " + insert_vin); 	// DEBUG
		    if (insert_vin.equals("q")) {
		    	main(toMain);
		    }
		    
		    System.out.println("Enter the cost of damages done to the vehicle (withhold the $, decimals NOT allowed):");
		    String insert_damages = scanner_a.nextLine();  			// Read user input
		    //System.out.println("insert_damages set to: " + insert_damages); // DEBUG
		    if (insert_damages.equals("q")) {
		    	main(toMain);
		    }
		    
		    System.out.println("Enter the SSN of the driver of the vehicle (format ###-##-####, or leave blank if no SSN):");
		    String insert_ssn = scanner_a.nextLine();  			// Read user input
		    //System.out.println("insert_ssn set to: " + insert_ssn); 	// DEBUG
		    if (insert_ssn.equals("q")) {
		    	main(toMain);
		    }
		    
		    //
		    //TODO: Catch user input error, and block it if not correct input type
		    //
		    
			
		    //"INSERT INTO accidents VALUES (insert_aid, 'insert_date', 'insert_city', 'insert_state')"
			stmnt_a.executeUpdate("INSERT INTO accidents VALUES (" + insert_aid + ", '" + insert_date + "', '" + insert_city + "', '" + insert_state + "')");
			//"INSERT INTO involvements VALUES (insert_aid, 'insert_vin', insert_damages, 'insert_ssn')"
			stmnt_a.executeUpdate("INSERT INTO involvements VALUES (" + insert_aid + ", '" + insert_vin + "', " + insert_damages + ", '" + insert_ssn + "')");
			
		} catch (Exception e) {
			System.out.println("An error has occurred.");
		    System.out.println("See full details below.");
			e.printStackTrace();
		} finally {
			try {
				connection_a.close();
			} catch (Exception e) {
			}
		}
		
		try {
			System.out.println("\nEntry successfully added to database. Returning to main menu.\n");
			Thread.sleep(1000);
			System.out.println("...");
			Thread.sleep(1000);
			System.out.println("...");
			Thread.sleep(1000);
			System.out.println("...");
			System.out.println("");
			main(toMain);
		} catch (Exception e) {
		}//try-catch
		
	}//input_a
	
	public static void input_e()
	{
		//input_e: Let user add a vehicle involved in existing accident. (pretty much just input_a but stripped down a bit)
		
		
		Connection connection_a = null;
		Scanner scanner_e = new Scanner(System.in);				// Create a Scanner object
		
		try {
			// Load the JDBC drivers
			Class.forName(driverClass);

			// Open a DB Connection
			connection_a = DriverManager.getConnection(url);

			// Create a Statement
			Statement stmnt_a = connection_a.createStatement();		// ONLY USE ONE
																	// OF THESE!!!!!!!
			
			
			System.out.println("\n\n\n\nYou may now begin entering vehicle involvement details for an existing accident.\n Input 'q' at any time to return to the main menu.");
			
			System.out.println("\nEnter the accident's ID#:");
		    String insert_aid = scanner_e.nextLine();  				// Read user input
		    //System.out.println("insert_aid set to: " + insert_aid);  	// DEBUG
		    if (insert_aid.equals("q")) {
		    	main(toMain);
		    }
			
		    System.out.println("(Note: only input one vehicle at a time. To input another vehicle for the same accident, please do this process again from the main menu.)");
		    System.out.println("Enter the VIN number of the vehicle involved:");
		    String insert_vin = scanner_e.nextLine();  					// Read user input
		    //System.out.println("insert_vin set to: " + insert_vin); 	// DEBUG
		    if (insert_vin.equals("q")) {
		    	main(toMain);
		    }
		    
		    System.out.println("Enter the cost of damages done to the vehicle (withhold the $, decimals NOT allowed):");
		    String insert_damages = scanner_e.nextLine();  						// Read user input
		    //System.out.println("insert_damages set to: " + insert_damages);	// DEBUG
		    if (insert_damages.equals("q")) {
		    	main(toMain);
		    }
		    
		    System.out.println("Enter the SSN of the driver of the vehicle (format ###-##-####, or leave blank if no SSN):");
		    String insert_ssn = scanner_e.nextLine();  						// Read user input
		    //System.out.println("insert_ssn set to: " + insert_ssn); 		// DEBUG
		    if (insert_ssn.equals("q")) {
		    	main(toMain);
		    }
		    
		    //
		    //TODO: Catch user input error, and block it if not correct input type
		    //
			
		    //if-else to check if user input a SSN or not, and insert a null value into table if they did
		    if (insert_ssn.isEmpty() == true) {
		    	//"INSERT INTO involvements VALUES (insert_aid, 'insert_vin', insert_damages, NULL)"
		    	stmnt_a.executeUpdate("INSERT INTO involvements VALUES (" + insert_aid + ", '" + insert_vin + "', " + insert_damages + ", NULL)");
		    } else {
		    	//"INSERT INTO involvements VALUES (insert_aid, 'insert_vin', insert_damages, 'insert_ssn')"
		    	stmnt_a.executeUpdate("INSERT INTO involvements VALUES (" + insert_aid + ", '" + insert_vin + "', " + insert_damages + ", '" + insert_ssn + "')");
		    }
			
			
		} catch (Exception e) {
			System.out.println("An error has occurred.");
		    System.out.println("See full details below.");
			e.printStackTrace();
		} finally {
			try {
				connection_a.close();
			} catch (Exception e) {
			}
		}
		
		try {
			System.out.println("\nEntry successfully added to database. Returning to main menu.\n");
			Thread.sleep(1000);
			System.out.println("...");
			Thread.sleep(1000);
			System.out.println("...");
			Thread.sleep(1000);
			System.out.println("...");
			System.out.println("");
			main(toMain);
		} catch (Exception e) {
		}//try-catch
		
	}//input_e
	
	public static void input_f()
	{
		Connection connection = null;
		String query_f = null;
		String query_f2 = null;
		Scanner scanner_f = new Scanner(System.in);

		System.out.println("\n\n\n\nYou may now search for an accident's details. Input 'q' at any time to return to the main menu.");
		
		//Take in user input of desired accident ID
		System.out.println("\nEnter the accident's ID#:");
	    String aid_f = scanner_f.nextLine();  						// Read user input
	    if (aid_f.equals("q")) {
	    	main(toMain);
	    }
		
		//first try-catch gets the accident info (and displays it)
		try {
			
			Class.forName(driverClass);							// Load JDBC
			connection = DriverManager.getConnection(url);		// Connect to database
			Statement stmnt = connection.createStatement();		// Create a Statement

			
			// Execute the query
			query_f = "select aid, accident_date, city, state from accidents where aid = '" + aid_f + "'";
			ResultSet results_f = stmnt.executeQuery(query_f);

			// Print out the results
			while (results_f.next()) {
				
				String found_aid = results_f.getString(1);
				String found_date = results_f.getString(2);
				String found_city = results_f.getString(3);
				String found_state = results_f.getString(4);

				System.out.println("\n----------------------------------------------------------------------------------------");
				System.out.println("Accident ID: " + found_aid);
				System.out.println("Date: " + found_date);
				System.out.println("City: " + found_city + ", " + found_state);
				System.out.println();
			}

		} catch (Exception e) {
			System.out.println(query_f);
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
			}
		}//try-catch1
		
		//second try-catch gets the info of vehicles involved in the accident (and displays it)
		try {
			
			Class.forName(driverClass);							// Load JDBC
			connection = DriverManager.getConnection(url);		// Connect to database
			Statement stmnt = connection.createStatement();		// Create a Statement

			System.out.println("Vehicles involved in this accident:");
			
			// Execute the query
			query_f2 = "select aid, vin, damages, driver_ssn from involvements where aid = '" + aid_f + "'";
			ResultSet results_f2 = stmnt.executeQuery(query_f2);

			// Print out the results
			while (results_f2.next()) {
				
				String found_vin = results_f2.getString(2);
				String found_damages = results_f2.getString(3);
				String found_driver_ssn = results_f2.getString(4);
				if (found_driver_ssn == null) {
					found_driver_ssn = "   N/A";
				}

				System.out.println("VIN: " + found_vin + " ... Total Damages: $" + found_damages + " ... Driver's SSN:" + found_driver_ssn);
			}

		} catch (Exception e) {
			System.out.println(query_f);
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
			}
		}//try-catch2
		
		//Ask user to search again, or go back to main menu
		System.out.println("\nData retrieved. Input 'f' to search again, otherwise any other input will return you to the main menu.");
		System.out.println("Input a choice now:");
	    String choice_f = scanner_f.nextLine();  						// Read user input
	    if (choice_f.equals("f")) {
	    	input_f();
	    } else {
	    	main(toMain);
	    }//if-else
	}//input_f
	
	public static void input_r()
	{
		Connection connection = null;
		String query_r = null;
		String dataFound = null;
		Scanner scanner_r = new Scanner(System.in);

		System.out.println("\n\n\n\nYou may now search for accidents using a range of dates. Input 'q' at any time to return to the main menu.");
		
		//Take in user input for start of date range
		System.out.println("\nEnter the starting point of the date range (Formatted as 'YYYY-MM-DD'):");
	    String startDate = scanner_r.nextLine();  						// Read user input
	    if (startDate.equals("q")) {
	    	main(toMain);
	    }
	    //Take in user input for end of date range
	    System.out.println("Enter the ending point of the date range (Formatted as 'YYYY-MM-DD'):");
	  	String endDate = scanner_r.nextLine();  						// Read user input
	  	if (endDate.equals("q")) {
	  	   	main(toMain);
	  	}
	  	
	  	System.out.println("");	//spacing fix
		
		//first try-catch gets the accident info (and displays it)
		try {
			
			Class.forName(driverClass);							// Load JDBC
			connection = DriverManager.getConnection(url);		// Connect to database
			Statement stmnt = connection.createStatement();		// Create a Statement

			
			// Execute the query
			//"SELECT * from accidents left join involvements on (involvements.aid = accidents.aid) WHERE accident_date BETWEEN '1111-11-11' AND '9999-11-11' ORDER BY accident_date"
			//query_r = "select * from accidents where accident_date between '" + startDate + "' and '" + endDate + "' order by accident_date";
			query_r = "SELECT * from accidents left join involvements on (involvements.aid = accidents.aid) WHERE accident_date BETWEEN '" + startDate + "' and '" + endDate + "' order by accident_date";
			ResultSet results_r = stmnt.executeQuery(query_r);

			String found_aid_old = "nice";
			// Print out the results
			while (results_r.next()) {
				
				String found_aid = results_r.getString(1);
				String found_date = results_r.getString(2);
				String found_city = results_r.getString(3);
				String found_state = results_r.getString(4);
				
				String found_vin = results_r.getString(6);
				String found_damages = results_r.getString(7);
				String found_driver_ssn = results_r.getString(8);
				
				dataFound = "Data Found!";	//For use in text output later

				//only print out new accident info if accident hasn't been printed yet
				if (found_aid.equals(found_aid_old) == false) {
					System.out.println("----------------------------------------------------------------------------------------");
					System.out.println("Accident ID: " + found_aid);
					System.out.println("Date: " + found_date);
					System.out.println("City: " + found_city + ", " + found_state);
					System.out.println();
					System.out.println("Vehicles involved in this accident:");
				}
				found_aid_old = found_aid;
				
				//proper formatting for a null Driver SSN field
				if (found_driver_ssn == null) {
					found_driver_ssn = "   N/A";
				}

				//print out vehicle info
				System.out.println("VIN: " + found_vin + " ... Total Damages: $" + found_damages + " ... Driver's SSN:" + found_driver_ssn);
				
			}//while

		} catch (Exception e) {
			System.out.println(query_r);
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
			}
		}//try-catch1
		
		//Ask user to search again, or go back to main menu
		if (dataFound == null) {
			System.out.println("\nNo accidents found within specified range. Input 'r' to search again, otherwise any other input will return you to the main menu.");
		} else {
			System.out.println("\nData retrieved. Input 'r' to search again, otherwise any other input will return you to the main menu.");
		}//if
		System.out.println("Input a choice now:");
	    String choice_r = scanner_r.nextLine();  						// Read user input
	    if (choice_r.equals("r")) {
	    	input_r();
	    } else {
	    	main(toMain);
	    }//if-else
	}//input_r
	
	public static void input_v()
	{
		Connection connection = null;
		String query_v = null;
		String dataFound = null;
		Scanner scanner_v = new Scanner(System.in);

		System.out.println("\n\n\n\nYou may now search for accidents using a range of average damages. Input 'q' at any time to return to the main menu.");
		
		//Take in user input for start of date range
		System.out.println("\nEnter the starting point of the average damage range (withhold the $, decimals allowed):");
	    String startAvgDamage = scanner_v.nextLine();  						// Read user input
	    if (startAvgDamage.equals("q")) {
	    	main(toMain);
	    }
	    //Take in user input for end of date range
	    System.out.println("Enter the ending point of the average damage range (withhold the $, decimals allowed):");
	    String endAvgDamage = scanner_v.nextLine();  						// Read user input
	  	if (endAvgDamage.equals("q")) {
	  	   	main(toMain);
	  	}
	  	
	  	System.out.println("");	//spacing fix
		
		//first try-catch gets the accident info (and displays it)
		try {
			
			Class.forName(driverClass);							// Load JDBC
			connection = DriverManager.getConnection(url);		// Connect to database
			Statement stmnt = connection.createStatement();		// Create a Statement
			//Statement stmnt2 = connection.createStatement();		// Create a Statement

			
			// Execute the query
			query_v = "SELECT accidents.aid, accident_date, city, state, AVG(damages) from involvements left join accidents on (accidents.aid = involvements.aid) group by accidents.aid";
			//query_v2 = "SELECT aid, AVG(damages) from involvements group by aid";
			ResultSet results_v = stmnt.executeQuery(query_v);
			//ResultSet results_v2 = stmnt2.executeQuery(query_v2);
			
			// Print out the results
			while (results_v.next()) {
				
				String found_aid = results_v.getString(1);
				String found_date = results_v.getString(2);
				String found_city = results_v.getString(3);
				String found_state = results_v.getString(4);
				String found_avgDamages = results_v.getString(5);
				
				dataFound = "Data Found!";	//For use in text output later

				//only print out accident info if the accident's average damages are <= end range and >= start range
				if (Double.parseDouble(found_avgDamages) <= Double.parseDouble(endAvgDamage) && Double.parseDouble(found_avgDamages) >= Double.parseDouble(startAvgDamage)) {
					System.out.println("----------------------------------------------------------------------------------------");
					System.out.println("Accident ID: " + found_aid);
					System.out.println("Date: " + found_date);
					System.out.println("City: " + found_city + ", " + found_state);
					System.out.println();
					System.out.println("Average damages done to vehicles involved in this accident: $" + found_avgDamages);
				} else {
					
				}//if-else
				
			}//while

		} catch (Exception e) {
			System.out.println(query_v);
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
			}
		}//try-catch1
		
		//Ask user to search again, or go back to main menu
		if (dataFound == null) {
			System.out.println("\nNo accidents found within specified range. Input 'v' to search again, otherwise any other input will return you to the main menu.");
		} else {
			System.out.println("\nData retrieved. Input 'v' to search again, otherwise any other input will return you to the main menu.");
		}//if
		System.out.println("Input a choice now:");
	    String choice_v = scanner_v.nextLine();  						// Read user input
	    if (choice_v.equals("v")) {
	    	input_v();
	    } else {
	    	main(toMain);
	    }//if-else
	}//input_v
	
	public static void input_d()
	{
		Connection connection = null;
		String query_d = null;
		String dataFound = null;
		Scanner scanner_d = new Scanner(System.in);

		System.out.println("\n\n\n\nYou may now search for accidents using a range of total damages. Input 'q' at any time to return to the main menu.");
		
		//Take in user input for start of date range
		System.out.println("\nEnter the starting point of the total damage range (withhold the $, decimals allowed):");
	    String startTotalDamage = scanner_d.nextLine();  						// Read user input
	    if (startTotalDamage.equals("q")) {
	    	main(toMain);
	    }
	    //Take in user input for end of date range
	    System.out.println("Enter the ending point of the total damage range (withhold the $, decimals allowed):");
	    String endTotalDamage = scanner_d.nextLine();  						// Read user input
	  	if (endTotalDamage.equals("q")) {
	  	   	main(toMain);
	  	}
	  	
	  	System.out.println("");	//spacing fix
		
		//first try-catch gets the accident info (and displays it)
		try {
			
			Class.forName(driverClass);							// Load JDBC
			connection = DriverManager.getConnection(url);		// Connect to database
			Statement stmnt = connection.createStatement();		// Create a Statement
			//Statement stmnt2 = connection.createStatement();		// Create a Statement

			
			// Execute the query
			query_d = "SELECT accidents.aid, accident_date, city, state, SUM(damages) from involvements left join accidents on (accidents.aid = involvements.aid) group by accidents.aid";
			ResultSet results_d = stmnt.executeQuery(query_d);
			
			// Print out the results
			while (results_d.next()) {
				
				String found_aid = results_d.getString(1);
				String found_date = results_d.getString(2);
				String found_city = results_d.getString(3);
				String found_state = results_d.getString(4);
				String found_totalDamages = results_d.getString(5);
				
				dataFound = "Data Found!";	//For use in text output later

				//only print out accident info if the accident's average damages are <= end range and >= start range
				if (Double.parseDouble(found_totalDamages) <= Double.parseDouble(endTotalDamage) && Double.parseDouble(found_totalDamages) >= Double.parseDouble(startTotalDamage)) {
					System.out.println("----------------------------------------------------------------------------------------");
					System.out.println("Accident ID: " + found_aid);
					System.out.println("Date: " + found_date);
					System.out.println("City: " + found_city + ", " + found_state);
					System.out.println();
					System.out.println("Total damages done to vehicles involved in this accident: $" + found_totalDamages);
				} else {
					
				}//if-else
				
			}//while

		} catch (Exception e) {
			System.out.println(query_d);
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
			}
		}//try-catch1
		
		//Ask user to search again, or go back to main menu
		if (dataFound == null) {
			System.out.println("\nNo accidents found within specified range. Input 'd' to search again, otherwise any other input will return you to the main menu.");
		} else {
			System.out.println("\nData retrieved. Input 'd' to search again, otherwise any other input will return you to the main menu.");
		}//if
		System.out.println("Input a choice now:");
	    String choice_d = scanner_d.nextLine();  						// Read user input
	    if (choice_d.equals("d")) {
	    	input_d();
	    } else {
	    	main(toMain);
	    }//if-else
	}//input_d
	
	public static void input_q()
	{
		//input_q: Print quit message
		try {
			System.out.println("\nCommand accepted. Quitting now...\n");
			Thread.sleep(2000);
			System.exit(0);
		} catch (Exception e) 	{
		}//try-catch
	}//input_q
	
	public static void input_error()
	{
		//input_error: Tell user that input was wrong, then go back to main
		
		try {																	
			System.out.println("\nError! User input incorrect. Please input only the choices listed.\n");
			Thread.sleep(1000);																			
			System.out.println("...");																	
			Thread.sleep(1000);																			
			System.out.println("...");																	
			Thread.sleep(1000);																			
			System.out.println("...");																	
			System.out.println("");	
			main(toMain);
		} catch (Exception e) 	{
		}//try-catch
		
	}//input_error
	
}//finalProj2