package other_package;

import java.sql.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Vehicle {

	
	
	//CRUD operations to insert into the database
	public void createVehicles(Statement stmnt, String quantity, String id, String year, String make, String model) {
		System.out.println();
		
		String create_statement = String.format("INSERT INTO vehicle VALUES (DEFAULT, %s, %s, %s, '%s', '%s')", quantity, id, year, make, model );

		try {
			stmnt.executeUpdate(create_statement);
			
			System.out.println("Vehicle created!");
			System.out.println();
			
		} catch (SQLException e) {
			System.out.println("Error occurred, please try again!");
		}  
	}
	
	
	
	public void getVehicleSpec(Statement stmnt, String spec, String spec_value) {
		//Gets the vehicle from the database with the appropriate specification (Year, Make, Model)
		System.out.println();

		String sql_statement = "";

		if(spec.equals("Year")) {
			sql_statement = "SELECT * FROM vehicle WHERE vehicle_year = '" + spec_value + "'";
			
		} else if(spec.equals("Make")) {
			sql_statement = "SELECT * FROM vehicle WHERE vehicle_make = '" + spec_value + "'";

		} else if(spec.equals("Model")) {
			sql_statement = "SELECT * FROM vehicle WHERE vehicle_model = '" + spec_value + "'";
			
		} else {
			//specification is vehicle_id
			sql_statement = "SELECT * FROM vehicle WHERE vehicle_id = '" + spec + "'";

		}
		
		ResultSet result_set = null;
		try {
			result_set = stmnt.executeQuery(sql_statement);
		} catch (SQLException e) {
			System.out.println("Error occurred, please try again!");
		}  
		
		try {
			while(result_set.next()) {  //iterates through each row of data from the database table and prints
				System.out.println("Vehicle ID - " +result_set.getInt(3) + ", Quantity - "+ result_set.getInt(2) +",  Year - " + result_set.getInt(4) + ",  Make - " + result_set.getString(5) + ",  Model - " + result_set.getString(6));  
			}
		} catch (SQLException e) {
			System.out.println("Error occurred, please try again!");
		}    				
		System.out.println();
		
	}
	
	
	public void getVehicles(Statement stmnt) {
		// Prints all of the vehicles in the database
		System.out.println();

		String sql_statement = "SELECT * FROM vehicle";
		ResultSet result_set = null;
		try {
			result_set = stmnt.executeQuery(sql_statement);
		} catch (SQLException e) {
			System.out.println("Error occurred, please try again!");
		}  
		
		try {
			while(result_set.next()) { //iterates through each row of data from the database table and prints
				System.out.println("Vehicle ID - " +result_set.getInt(3) + ", Quantity - "+ result_set.getInt(2) +",  Year - " + result_set.getInt(4) + ",  Make - " + result_set.getString(5) + ",  Model - " + result_set.getString(6));  
			}
		} catch (SQLException e) {
			System.out.println("Error occurred, please try again!");
		}    				
		System.out.println();
		
	}
	
	
	public void updateVehicles(Statement stmnt, String spec, String val) {
		System.out.println();
		String setter = "";
		
		if(spec.toLowerCase().equals("year")) {
			setter = "vehicle_year";
		} else if(spec.toLowerCase().equals("make")) {
			setter = "vehicle_make";
		} else if(spec.toLowerCase().equals("make")) {
			setter = "vehicle_model";
		}
		
		String update_sql = String.format("UPDATE vehicle SET %s = '%s' WHERE %s <> '%s'", setter, val, setter, val );
		
		System.out.println(update_sql);
		try {
			stmnt.executeUpdate(update_sql);
			
			System.out.println("Vehicles updated!");
			System.out.println();
			
		} catch (SQLException e) {
			System.out.println("Error occurred, please try again!");
		}  
	}
	
	
	public void deleteVehicle( Statement stmnt, String id ) {		
		System.out.println();

		String delete_statement = "DELETE FROM vehicle WHERE vehicle_id = " + id;
		String print_statement = "Vehicle " + id + " deleted!";

		try {
			stmnt.executeUpdate(delete_statement);
			
			
		    // Now you can extract all the records
		    // to see the remaining records
		    String sql = "SELECT vehicle_id FROM vehicle";
		    ResultSet rs = stmnt.executeQuery(sql);
		    boolean checker = true;

		    while(rs.next()){
		    	//Retrieve by column name
		        int vehicle_id  = rs.getInt("vehicle_id");
		        //check if id is still in database or not
		        if(vehicle_id == Integer.parseInt(id)) {
		        	checker = false;
		        }
		    }
		      
		    if(checker) {
				System.out.println(print_statement);
				System.out.println();		    	
		    } else {
		    	System.out.println("Vehicle not deleted, please try again!");
		    	System.out.println();
		    }
		      
		      

			
		} catch (SQLException e) {
			System.out.println("Error occurred, please try again!");
		}  
		
		
	}
	
	
	

	
	//The Client where the user interacts to get, create, update, and delete vehicles
	public static void main (String[] args) { 
		
		
		//Command Line Interface Commands
		System.out.println("GET all vehicles: GET ALL");
		System.out.println("GET specific Id, Make, or Model: GET <vehicle_id> or GET <Make> <Toyota> ");
		System.out.println("CREATE vehicles: CREATE Quantity Id Year Make Model  ex. CREATE 10 938203 2021 Toyota Rav4");
		System.out.println("UPDATE vehicles: UPDATE vehicle_attribute <Value>  (ex. UPDATE Year 2009)");
		System.out.println("DELETE vehicle: DELETE <vehicle_id> ");
		System.out.println("End Program: Q");
		System.out.println();
	
		
		try {
		
			//Connect to the database
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehicle_database?useSSL=false","root","Onepiece9978");  

			 
		    //Gets the user input from console (Client)
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));   
            boolean runner = true;
            String user_input = "";
            
            Vehicle vehicle = new Vehicle();
            
            while (runner) {             	
    			String sql_statement = "";
    			String print_statement = "";
    			
    			Statement statement = connect.createStatement();
            	
            	System.out.println("Enter command: ");
            	user_input = input.readLine();
            	
            	if( user_input.equals("Q") ) {
            		break;
            	}
            	
            	String[] input_arr = user_input.split(" ");
            	
            	
            	if(!user_input.equals("")) {//Checks if the command isn't empty
	            	if( user_input.equals( "GET ALL" ) ) {
	            		vehicle.getVehicles(statement);
	            		
	            	} else if( user_input.contains( "GET" )) {
	            		if(input_arr.length == 3 ) {
	            			String spec = input_arr[1];
	            			String val = input_arr[2];
	            			
		            		vehicle.getVehicleSpec(statement, spec, val);

	            		} else if( input_arr.length == 2 ) {
	            			vehicle.getVehicleSpec(statement, input_arr[1], "");
	            		}
	
	            	} else if( user_input.contains( "CREATE" ) && input_arr.length == 6 ) {
	            		String quantity = input_arr[1];
	            		String id = input_arr[2];
	            		String year = input_arr[3];
	            		String make = input_arr[4];
	            		String model = input_arr[5];	            		
	            		int int_year = Integer.parseInt(year); 
	            		
	            		if( int_year > 1950 && int_year < 2050 ) {
	            			vehicle.createVehicles(statement, quantity, id, year, make, model);
	            		} else {
	            			System.out.println("Please Add a vehicle made between 1950 and 2050");

	            		}
	            		
	            	} else if( user_input.contains( "UPDATE" )  && input_arr.length == 3 ) {
	            		String spec = input_arr[1];
	            		String val = input_arr[2];
	            				
	            		vehicle.updateVehicles(statement, spec, val);
	            		
	            	} else if( user_input.contains( "DELETE" )  && input_arr.length == 2 ) {
	            		vehicle.deleteVehicle(statement, input_arr[1]);
	            		
	            	} else {
	            		System.out.println("Please enter a valid command!");
	            		System.out.println();
	            	}
            	
            	} else {
            		System.out.println("Please enter a command!");
            		System.out.println();
            	}
            	
            	
            }
            
		    
            connect.close();
		    
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		
		
	}
	
}
