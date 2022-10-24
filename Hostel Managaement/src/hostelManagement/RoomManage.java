package hostelManagement;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import controller.ManageController;
import hostel.Bed;
import hostel.HostelDatabase;
import hostel.Room;
import hostel.Transactions;
import hostel.Transactions.Monthly;

public class RoomManage {

	private String roomId, bedId, blockName, roomType, bedSize;
	private Scanner input = new Scanner(System.in);
	private Room room;
	private ManageController manage;
	private AlotteManage alotteManage;
	private int choice = 1;
	private String choose;
	boolean found = false;

	RoomManage() {
		manage = new ManageController();
		alotteManage = new AlotteManage();
	}

	public void create() {
		System.out.println("ADD ROOM");
		createRoomList();
		System.out.println("ADD BED");
		createBed();
	}

	public void getUserChoice() {
		try {
			do {
				System.out.println("""
						\n1. Add ROOMS
						2. Add BEDS
						3. Add both ROOMS and BEDS
						4. Add Allotte
						5. See each Allotte details
						6. view All allottes details
						7. See each Allotte Transaction details
						8. Remove Allotte
						press corresponding number to do operation----
						""");
				input.nextLine();
				try {
					choose = input.nextLine();
					switch (choose) {
					case "1":
						createRoomList();
						break;
					case "2":
						createBed();
						break;
					case "3":
						createRoomList();
						createBed();
						break;
					case "4":
						alotteManage.Allot();
						break;
					case "5":
						alotteManage.viewAllotteDetails(5);
						break;
					case "6":
						alotteManage.viewAllotteDetails(6);
						break;
					case "7":
						alotteManage.viewAllotteDetails(7);
						break;
					case "8":
						alotteManage.deAllot();
						break;
					default:
						System.out.println("Enter valid number to  do operations");
					}
				} catch (NumberFormatException e) {
					System.out.println("please..Press any number to continue operation");
				}

				System.out.println("\npress  1--> to continue the operations.   others -- to log out");
				choice = input.nextInt();
			} while (choice == 1);
			System.out.println("Thank You");
		} catch (NumberFormatException e) {
			System.out.println("Invalid Input.. you are logged out");
		}
	}

	private void createRoomList() {
		try {
			System.out.println("Enter no.of Rooms you want to add");
			choice = input.nextInt();
			input.nextLine();
			int i = 1;
			loop: while (i <= choice) {
				System.out.println("Enter Room ID");
				roomId = input.nextLine();
				if (manage.isValid("^[1-9][0-9][0-9]$", roomId)) {
					if ((manage.roomIdCheck(Integer.valueOf(roomId))) == null) {
						System.out.println("Enter Room Block");
						blockName = input.nextLine();
						if ((manage.isValid("^[a-zA-Z]+$", blockName)) == false) {

							while ((manage.isValid("^[a-zA-Z]+$", blockName)) == false) {
								System.out.println(
										"Invalid Block Name !  ----  Enter Block Name again (No numbers Included)");
								blockName = input.nextLine();
							}
						}
						System.out.println("Enter Room Type (Single / Shared)");
						roomType = input.nextLine();
						if (!(roomType.equalsIgnoreCase("single") || roomType.equalsIgnoreCase("shared"))) {
							while (!(roomType.equalsIgnoreCase("single") || roomType.equalsIgnoreCase("shared"))) {
								System.out
										.println("Invalid room Type !  ----  Enter Room Type again (Single / Shared)");
								roomType = input.nextLine();
							}
						}
						manage.addRoom(roomId, blockName, roomType);
						System.out.println(i + "st Room added\n");

						i++;
					} else {
						System.out.println("RoomId already exists ! give correct new room Id again");
						continue loop;
					}
				} else {
					System.out.println("Invalid roomId ! give Valid room Id again (e.g. 123) ");
					continue loop;
				}
			}
			if (i == choice + 1)
				System.out.println(choice + " ROOMS added\n");

		} catch (NumberFormatException e) {
			System.out.println("Press valid input");
		}
	}

	private void createBed() {
		try {
			System.out.println("Enter Room ID where you want to add Bed");
			roomId = input.next();
			room = manage.roomIdCheck(Integer.valueOf(roomId));
			if (room != null) {
				System.out.println("Enter no.of Beds you want to add");
				choice = input.nextInt();
				int i = 1;
				while (i <= choice) {
					input.nextLine();
					System.out.println("Enter Bed ID");
					bedId = input.nextLine();
					if ((manage.isValid("^[1-9][0-9][0-9][1-9]$", bedId)) == false) {

						while ((manage.isValid("^[1-9][0-9][0-9][1-9]$", bedId)) == false) {
							System.out.println("Invalid Bed ID !  ----  Enter Bed ID again (e.g.  1001)");
							bedId = input.nextLine();
						}
					}
					if (manage.isBedAvailable(room, Integer.valueOf(bedId)) == null) {
						System.out.println("Enter Bed Size (Large / small)");
						bedSize = input.next();
						if (bedSize.equalsIgnoreCase("large") || bedSize.equalsIgnoreCase("small")) {
							manage.addBed(room, bedId, bedSize);
							System.out.println(i + "st Bed added\n");
						} else {
							System.out.println("\n--- Invalid Bed size !  ----  continue from start ---\n");
							continue;
						}
					} else {
						System.out.println(
								"\n--- Bed Id already exists in the room " + roomId + " ..... give again ---\n");
						continue;
					}
					i++;
				}
				if (i == choice + 1)
					System.out.println("Totally " + choice + " BEDS added\n");
			}
		} catch (NumberFormatException e) {
			System.out.println("Enter valid input");
		} catch (InputMismatchException e) {
			System.out.println("Enter valid input");
		}
	}

}
