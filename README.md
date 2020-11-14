# Mitchell-Coding-Challenge

Matthew Choi 11/13/2020

## Project Overview

This project consists of performing CRUD (Create, Read, Update, Delete) operations to add Vehicle objects into a MySQL database. This features a client command-line interface where users are able to create and add new vehicles to the database, view, update and delete them. 


### CRUD Operation Explanations

User commands given in the console. 
To view all of the vehicles in the database use the GET ALL command that retrieves all of the vehicles with its stats.
To view a specific vehicle use the GET keyword with the specific vehicle id or a specified year, make, or model. For example, GET Year 2008.
To add a collection of vehicles use the CREATE keyword and specify the quantity of vehicles  along with the id, year, make and model. 
To update a group of vehicles, use the UPDATE keyword with the specification along with the value such as 'UPDATE Make Audi.'
To delete a vehicle from the database, start with the DELETE keyword and specify the vehicle id to delete the vehicle such as 'DELETE 34322'


### Explanation of Code

I structured the code where the main program keeps the program running with a while loop that takes in user input until they end the program. The user input is broken up into specific words and put into the methods from the vehicle object. The Vehicle entity has 5 different methods that perform the CRUD operations.

		- createVehicles()
		- getVehicleSpec()
		- getVehicles()
		- updateVehicles()
		- deleteVehicle()
		
createVehicles() - The parameters takes in a statement object to send SQL to the database, and the rest of the attributes for the new vehicle. The function sends out the statement and if there is no error, the console prints out success message. 
getVehicleSpec() - The parameters takes in a statement obj along with the specific id or the specification along with its specified value. Retrieves the result from the query and uses a while loop to print out the resulting set.
getVehicles() - This method takes in no parameters and retrieves all of the items in the database. I used a while loop to print out all of the vehicles along with its attributes.
updateVehicles() - The parameters takes in the statement object along with the vehicle attribute and its value. The query updates the database and a success message is printed out to the console. 
deleteVehicles() - The parameter takes in the statement object and the specified vehicle id. The deleted query is sent and a success message is printed out if the vehicle is deleted. 


### List of Files

README.md
Vehicle.java

