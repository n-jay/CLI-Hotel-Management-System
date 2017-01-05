
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainController implements Serializable {

	static Customer[] roomList = new Customer[10];

	static Customer[] cusQueue = new Customer[7];

	static String[] args = {};

	public static String fileName = "Customers.txt";

	public static void main(String[] args) {

		roomList[0] = new Customer(1, "", "");
		roomList[1] = new Customer(2, "", "");
		roomList[2] = new Customer(3, "", "");
		roomList[3] = new Customer(4, "", "");
		roomList[4] = new Customer(5, "", "");
		roomList[5] = new Customer(6, "", "");
		roomList[6] = new Customer(7, "", "");
		roomList[7] = new Customer(8, "", "");
		roomList[8] = new Customer(9, "", "");
		roomList[9] = new Customer(10, "", "");

		cusQueue[0] = new Customer(1, "", "");
		cusQueue[1] = new Customer(2, "", "");
		cusQueue[2] = new Customer(3, "", "");
		cusQueue[3] = new Customer(4, "", "");
		cusQueue[4] = new Customer(5, "", "");
		cusQueue[5] = new Customer(6, "", "");
		cusQueue[6] = new Customer(7, "", "");

		userInput(roomList);

	}

	public static void userInput(Customer[] roomArray) {

		System.out.println("_________________________________");
		System.out.println("_________________________________");

		// the menu for the usage
		System.out.println("-----------------Menu----------------");
		System.out.println("L - Load saved Array from file: ");
		System.out.println("A - Add new Customer to new room array: ");
		System.out.println("D - Display Empty rooms in new array: ");
		System.out.println("P - Display Status of all rooms in new array: ");
		System.out.println("F - Find Rooms from cutomer name in new array: ");
		System.out.println("S - Sort new array into alphabetical order: ");
		System.out.println("X - Delete Customer from new array");
		System.out.println("R - Refresh array");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.print("Give Your input: ");

		Scanner sc = new Scanner(System.in);
		String inp = sc.nextLine();

		// various input options and the methods they call
		switch (inp) {

		case "a":
			System.out.println("ADDING NEW CUSTOMER: ");
			newCustomer(roomArray);
			break;
		case "A":
			System.out.println("ADDING NEW CUSTOMER: ");
			newCustomer(roomArray);
			break;

		case "d":
			System.out.println("DISPLAYING EMPTY ROOMS: ");
			emptyRooms(roomArray);
			break;
		case "D":
			System.out.println("DISPLAYING EMPTY ROOMS: ");
			emptyRooms(roomArray);
			break;

		case "L":
			System.out.println("LOADING SAVED FILE: ");
			try {
				load();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "l":
			System.out.println("LOADING SAVED FILE: ");
			try {
				load();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "F":
			System.out.println("FINDING CUSTOMER NAME: ");
			findRoomsFromName(roomArray);
			break;
		case "f":
			System.out.println("FINDING CUSTOMER NAME: ");
			findRoomsFromName(roomArray);
			break;

		case "X":
			System.out.println("DELETING CUSTOMER: ");
			delete(roomArray);
			break;
		case "x":
			System.out.println("DELETING CUSTOMER: ");
			delete(roomArray);
			break;

		case "P":
			System.out.println("DISPLAYING ALL CUSTOMERS AND ROOM STATUS: ");
			printArray(roomArray);
			break;
		case "p":
			System.out.println("DISPLAYING ALL CUSTOMERS AND ROOM STATUS: ");
			printArray(roomArray);
			break;

		case "S":
			System.out.println("SORTING NAMES TO ALPHABETICAL ORDER: ");
			sort(roomArray);
			break;
		case "s":
			System.out.println("SORTING NAMES TO ALPHABETICAL ORDER: ");
			sort(roomArray);
			break;

		case "R":
			System.out.println("REFRESHING ARRAY");
			arrayNew(roomList);
			break;
		case "r":
			System.out.println("REFRESHING ARRAY ");
			arrayNew(roomList);
			break;

		// takes invalid inputs and gives error while restarting the program
		default:
			System.out.println("ERROR");
			userInput(roomArray);

		}

	}

	// method that clears all values in the temporary array
	public static void arrayRefresh(Customer[] roomArray) {
		for (int i = 0; i <= roomArray.length - 1; i++) {
			roomArray[i].setName("");
			roomArray[i].setAddress("");
		}
	}

	// method that gives a fresh array at beginning of program execution
	public static void arrayNew(Customer[] roomArray) {
		roomList[0] = new Customer(1, "", "");
		roomList[1] = new Customer(2, "", "");
		roomList[2] = new Customer(3, "", "");
		roomList[3] = new Customer(4, "", "");
		roomList[4] = new Customer(5, "", "");
		roomList[5] = new Customer(6, "", "");
		roomList[6] = new Customer(7, "", "");
		roomList[7] = new Customer(8, "", "");
		roomList[8] = new Customer(9, "", "");
		roomList[9] = new Customer(10, "", "");

		userInput(roomArray);
	}

	// method to add new customer
	public static void newCustomer(Customer[] roomArray) {

		// initializing a temporary array to store free rooms
		int[] emptyRoom = new int[10];

		// loop to validate which rooms are empty
		int index = 0;
		for (int i = 0; i <= roomArray.length - 1; i++) {

			if (roomArray[i].getName().equals("") && roomArray[i].getAddress().equals("")) {
				System.out.println("Can add customer to room no: " + roomArray[i].getId());
				emptyRoom[index] = roomArray[i].getId();
				index++;

			}
		}

		System.out.println("To which room do you want to add customer to?: ");

		// catches the exceptional inputs as when a character is entered instead
		// of a number
		try {
			// gets the user input for which room the customer should be added
			// to
			Scanner sc = new Scanner(System.in);
			int inp = sc.nextInt();

			if (inp == emptyRoom[0] || inp == emptyRoom[1] || inp == emptyRoom[2] || inp == emptyRoom[3]
					|| inp == emptyRoom[4] || inp == emptyRoom[5] || inp == emptyRoom[6] || inp == emptyRoom[7]
					|| inp == emptyRoom[8] || inp == emptyRoom[9]) {

				System.out.println("Add Customer details... ");

				System.out.println("Insert Customer name:  ");
				Scanner sx = new Scanner(System.in);
				String inpName = sx.nextLine();

				for (int i1 = 0; i1 <= roomArray.length - 1; i1++) {
					int editRoomIndex1 = roomArray[i1].getId();
					if (editRoomIndex1 == inp) {
						roomArray[i1].setName(inpName);
						break;
					}
				}

				System.out.println("Insert Customer address:  ");
				Scanner sv = new Scanner(System.in);
				String inpAddress = sv.nextLine();

				for (int i2 = 0; i2 <= roomArray.length - 1; i2++) {
					int editRoomIndex2 = roomArray[i2].getId();
					if (editRoomIndex2 == inp) {
						roomArray[i2].setAddress(inpAddress);
						break;
					}
				}

				System.out.println(" ");
				System.out.println("Do you prefer to save this array permanently? ((Y)es/(N)o)");

				Scanner sin = new Scanner(System.in);
				String saveConf = sin.nextLine();

				switch (saveConf) {
				case "Y":

					// saves the newly added customer details into text file
					try {
						save(roomArray);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					// loads saved data from text file and prints them for
					// convenience
					try {
						loadPrint();
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						save(roomArray);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						save(roomArray);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case "N":
					System.out.println("SAVE ABORTED: DATA NOT SAVED");
					// MainController.main(args);
					userInput(roomArray);
					break;

				default:
					System.out.println("INVALID ENTRY: TRY AGAIN");
					newCustomer(roomArray);
					break;
				}

			} else {

				System.out.println("CANNOT USE ROOM! ROOM OCCUPIED");
				newCustomer(roomArray);
			}

		} catch (Exception e1) {
			System.out.println("ERROR, INVLALID INPUT");
			newCustomer(roomArray);
		}

		// MainController.main(args);
		userInput(roomArray);

	}

	// prints the contents of main array as it is in the current state
	public static void printArray(Customer[] roomArray) {

		for (Customer data : roomArray) {
			if (data.getName().equals("") || data.getAddress().equals("") || data.getName().equals(null)
					|| data.getAddress().equals(null)) {
				System.out.println("Room no." + data.getId() + " is AVAILABLE");
			} else {
				System.out.println("Room number " + data.getId() + " is currently used by " + data.getName() + " from "
						+ data.getAddress());
			}
		}
		// MainController.main(args);
		userInput(roomArray);
	}

	// prints contents of the saved array when called
	public static void printSavedArray(Customer[] roomArray) {

		for (Customer data : roomArray) {
			if (data.getName().equals("") || data.getAddress().equals("") || data.getName().equals(null)
					|| data.getAddress().equals(null)) {
				System.out.println("Room no." + data.getId() + " is AVAILABLE");
			} else {
				System.out.println("Room number " + data.getId() + " is currently used by " + data.getName() + " from "
						+ data.getAddress());
			}
		}
	}

	public static void printQue(Customer[] queArray) {

		for (Customer data : queArray) {
			if (data.getName().equals("") || data.getAddress().equals("") || data.getName().equals(null)
					|| data.getAddress().equals(null)) {
				System.out.println("Queue space no." + data.getId() + " is AVAILABLE");
			} else {
				System.out.println("Queue space no. " + data.getId() + " is currently booked by " + data.getName()
						+ " from " + data.getAddress());
			}
		}

	}

	// the method to get the number of empty rooms
	public static void emptyRooms(Customer[] roomArray) {
		for (int i = 0; i <= roomArray.length - 1; i++) {
			if (!roomArray[i].getName().equals("") || !roomArray[i].getAddress().equals("")) {
				System.out.println("Room " + roomArray[i].getId() + " is unavailable");
			} else {
				System.out.println("Room " + roomArray[i].getId() + ": FREE");
			}
		}
		// MainController.main(args);
		userInput(roomArray);

	}

	// the method to get the rooms based on names of the customers
	public static void findRoomsFromName(Customer[] roomArray) {
		// System.out.println("Find names TEST");

		Scanner s = new Scanner(System.in);
		System.out.println("Enter a name for seach: ");
		String inpName = s.nextLine();

		int i = 0;
		int n = 0;
		while (i < 10) {
			if (inpName.equals(roomArray[i].getName())) {
				System.out.println("Customer with name " + inpName + " was located in room no: " + (i + 1));
				n++;
			}
			i++;

		}

		if (n == 0) {
			System.out.println("No Customers with name " + inpName + " has been found");
		}
		// MainController.main(args);
		userInput(roomArray);

	}

	public static void findRoomsFromNameSaved(Customer[] roomArray) {
		// System.out.println("Find names TEST");

		Scanner s = new Scanner(System.in);
		System.out.println("Enter a name for seach: ");
		String inpName = s.nextLine();

		int i = 0;
		int n = 0;
		while (i < 10) {
			if (inpName.equals(roomArray[i].getName())) {
				System.out.println("Customer with name " + inpName + " was located in room no: " + (i + 1));
				n++;
			}
			i++;

		}

		if (n == 0) {
			System.out.println("No Customers with name " + inpName + " has been found");
		}
		try {
			load();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			load();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			load();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// method to save the array to a text file
	public static void save(Customer[] roomArray) throws IOException {

		try {
			// creates a new file with object ObjectOutputStream
			FileOutputStream out = new FileOutputStream(fileName);
			ObjectOutputStream oout = new ObjectOutputStream(out);

			// writes the array into the file
			oout.writeObject(roomArray);

			// closes the stream
			oout.flush();
			oout.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void loadPrint() throws IOException, ClassNotFoundException {

		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);

		Customer[] loadArray = (Customer[]) ois.readObject();

		printSavedArray(loadArray);
		// MainController.main(args);

	}

	// method to load an already saved array file
	public static void load() throws ClassNotFoundException, IOException {

		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);

		Customer[] loadArray = (Customer[]) ois.readObject();

		Customer[] loadList = new Customer[10];

		for (int n = 0; n <= 9; n++) {
			loadList[n] = new Customer(loadArray[n].getId(), loadArray[n].getName(), loadArray[n].getAddress());
		}

		printSavedArray(loadArray);

		System.out.print("  ");

		System.out.println("-----------------Menu for LOADED CUSTOMER LIST----------------");
		System.out.println("A - Add new Customer to loaded room list: ");
		System.out.println("D - Display Empty rooms in loaded room list: ");
		System.out.println("P - Display Status of all rooms in loaded room list: ");
		System.out.println("F - Find Rooms from customer name in loaded room list: ");
		System.out.println("S - Sort Into alphabetical order names in loaded room list: ");
		System.out.println("X - Delete Customer from loaded room list");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.print("Give Your input: ");

		Scanner sc = new Scanner(System.in);
		String inp = sc.nextLine();

		switch (inp) {
		case "a":
			System.out.println("ADDING NEW CUSTOMER: ");
			chkArray(loadList, cusQueue);
			newCustomer(loadList);
			break;
		case "A":
			System.out.println("ADDING NEW CUSTOMER: ");
			chkArray(loadList, cusQueue);
			newCustomer(loadList);
			break;

		case "S":
			System.out.println("SORTING NAMES TO ALPHABETICAL ORDER: ");
			sort(loadList);
			break;
		case "s":
			System.out.println("SORTING NAMES TO ALPHABETICAL ORDER: ");
			sort(loadList);
			break;

		case "F":
			System.out.println("FINDING CUSTOMER NAME: ");
			findRoomsFromNameSaved(loadList);
			break;
		case "f":
			System.out.println("FINDING CUSTOMER NAME: ");
			findRoomsFromNameSaved(loadList);
			break;

		case "d":
			System.out.println("DISPLAYING EMPTY ROOMS: ");
			emptyRooms(loadList);
			break;
		case "D":
			System.out.println("DISPLAYING EMPTY ROOMS: ");
			emptyRooms(loadList);
			break;

		case "P":
			System.out.println("DISPLAYING ALL CUSTOMERS AND ROOM STATUS: ");
			printArray(loadArray);
			break;
		case "p":
			System.out.println("DISPLAYING ALL CUSTOMERS AND ROOM STATUS: ");
			printArray(loadArray);
			break;

		case "X":
			System.out.println("DELETING CUSTOMER: ");
			delete(loadArray);
			chkQue(loadArray, cusQueue);
			break;
		case "x":
			System.out.println("DELETING CUSTOMER: ");
			delete(loadArray);
			chkQue(loadArray, cusQueue);
			break;

		default:
			System.out.println("ERROR");
			MainController.main(args);

		}

		ois.close();

	}

	// sort names according to alphabetical order
	public static void sort(Customer[] roomArray) {

		Customer temp;

		for (int i = 0; i < roomArray.length; i++) {
			for (int j = i + 1; j < 10; j++) {
				// the names of the objects at that particular instance assigned
				// to variables for comparison
				String s1 = roomArray[i].getName();
				String s2 = roomArray[j].getName();

				if ((s1.compareTo(s2)) > 0) {
					temp = roomArray[i];
					roomArray[i] = roomArray[j];
					roomArray[j] = temp;
				}
			}
		}
		try {
			save(roomArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			loadPrint();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void delete(Customer[] roomArray) {

		try {
			// asks question about customer in which room number should be
			// deleted
			Scanner sr = new Scanner(System.in);
			System.out.println("Enter room number to delete customer: ");
			int inp = sr.nextInt();

			int i = 0;
			// int n=0;
			while (i < 10) {
				if (inp == roomArray[i].getId()) {

					Scanner so = new Scanner(System.in);
					System.out.println("Are you sure you want to delete customer?(Y/N):  ");
					String inpConf = so.nextLine();

					switch (inpConf) {
					case "Y":
						roomArray[i].setName("");
						roomArray[i].setAddress("");
						// saves updated array to text file
						try {
							save(roomArray);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// loads and prints updated array for convenience
						try {
							load();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						// MainController.main(args);
//						chkQue(roomArray, cusQueue);
						break;
					case "y":
						roomArray[i].setName("");
						roomArray[i].setAddress("");

						// saves updated array to text file
						try {
							save(roomArray);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// loads and prints updated array for convenience
						try {
							load();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// userInput(roomArray);
						chkQue(roomArray, cusQueue);
						break;
					case "N":
						System.out.println("ACTION ABORTED");
						userInput(roomArray);
						break;
					case "n":
						System.out.println("ACTION ABORTED");
						userInput(roomArray);
						break;
					}
					// int conf = sr.nextInt();
					// n++;
				}
				i++;
			}
			try {
				save(roomArray);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e2) {
//			chkQue(roomArray, cusQueue);
			userInput(roomArray);
		}
	}

	// QUEUE METHODS
	// checks if the array is full for the queue functionality
	public static void chkArray(Customer[] roomArray, Customer[] queArray) {
		int count = 0;
		for (Customer value : roomArray) {
			if (!value.getName().equals("") || !value.getAddress().equals("")) {
				count++;
			}
		}

		if (count == 10) {
			System.out.println("All the rooms are currently FULL");
			System.out.println("THE CUSTOMER WILL BE ADDED TO THE QUEUE");
			System.out.println(" ");

			System.out.println("Add Customer details for queue... ");

			System.out.println("Insert Customer name:  ");
			Scanner sx = new Scanner(System.in);
			String inpName = sx.nextLine();

			System.out.println("Insert Customer address:  ");
			Scanner sv = new Scanner(System.in);
			String inpAddress = sv.nextLine();

			queManage(roomArray, queArray, inpName, inpAddress);
		}
	}

	// does the extra object additions to the queue
	public static void queManage(Customer[] roomArray, Customer[] queArray, String name, String address) {

		for (Customer val : queArray) {
			if (val.getName().equals("") || val.getAddress().equals("")) {
				val.setName(name);
				val.setAddress(address);
				break;
			}
		}
		printQue(queArray);
		userInput(roomArray);
	}

	// checks the queue and assigns a booked space when a room becomes available
	// assigning happens by priority order
	public static void chkQue(Customer[] roomArray, Customer[] queArray) {
		Scanner sco = new Scanner(System.in);
		String conf = sco.nextLine();
		
		System.out.println("Do you want to add customers from the queue? (Y)es/(N)o");
		
		switch (conf) {
			case "Y" :
				System.out.println("CHECKING AND ASSIGNING CUSTOMER NAMES FROM QUEUE");
				// checks the queue array
				for (Customer val : queArray) {
					if (!val.getName().equals("") || !val.getAddress().equals("")) {
						String bookName = val.getName();
						val.setName("");
						String bookAddress = val.getAddress();
						val.setAddress("");
						System.out.println("Priority given to customer " + bookName + " from the queue");

						// checks the room list
						for (Customer roomVal : roomArray) {
							if (roomVal.getName().equals("") || roomVal.getAddress().equals("")) {
								roomVal.setName(bookName);
								roomVal.setAddress(bookAddress);
								System.out.println(
										"Customer " + bookName + " assigned to room no." + roomVal.getId() + " from queue");

								break;
							}
						}
					}
					break;
				}
				userInput(roomArray);
				
			case "N" :
				System.out.println("ADDING CUSTOMERS FROM QUEUE ABORTED");
				userInput(roomArray);
				
		}
	} 

}
