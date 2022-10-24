package hostelManagement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import controller.ManageController;
import controller.SetupController;
import hostel.Allotte;
import hostel.Bed;
import hostel.HostelDatabase;
import hostel.Room;
import hostel.Transactions;
import hostel.Transactions.Monthly;

public class AlotteManage {
	private Allotte allotte;
	private ManageController manage;
	private SetupController setup;
	private Transactions transact;
	private Transactions.Monthly month;
	private HostelDatabase hostelDB = HostelDatabase.getInstance();
	private Scanner input = new Scanner(System.in);
	private String roomId, bedId, allotteId;
	private String allotteName, address, entry, currentMonth, exit;
	private String phoneNo, paid, due;

	AlotteManage() {
		manage = new ManageController();
		setup = new SetupController();
	}

	public void Allot() {
		System.out.println("You can allot allottes in the available room now");
		registerAllotte();
	}

	public void deAllot() {
		System.out.println("You can remove allottes from the available room now");
		removeAllotte();

	}

	public void viewAllotteDetails(int num) {
		if (setup.isAllotteSetUped()) {
			if (num == 5) {
				System.out.println("Enter Allotte Id");
				allotteId = input.nextLine();
				allotte = manage.isAllotteAvailable(Integer.valueOf(allotteId));
				if (allotte != null)
					viewAllotte(allotte);
				else
					System.out.println("No allottes in this allotte id");
			} else if (num == 6) {
				viewAllotteList();
			} else if (num == 7) {
				System.out.println("Enter Allotte Id");
				allotteId = input.next();
				allotte = manage.isAllotteAvailable(Integer.valueOf(allotteId));
				if (allotte != null)
					viewTransaction(Integer.valueOf(allotteId));
				else
					System.out.println("No allottes in this allotte id");
			}
		} else {
			System.out.println("Allottes not yet added");
		}
	}

	private void viewAllotte(Allotte eachAllotte) {
		System.out.println("\nSTAYING ROOM ID \tSTAYING BED ID  \tALLOTTE ID\tALLOTTE NAME\tPHONE NO \t\tADDRESS");
		System.out.print(
				"----------------------------------------------------------------------------------------------------------------\n");
		System.out.printf("|%17d|", eachAllotte.getStayRoomId());
		System.out.printf("%19d|", eachAllotte.getStayBedId());
		System.out.printf("%19d|", eachAllotte.getAlotteId());
		System.out.printf("%25s|", eachAllotte.getAlotteName());
		System.out.printf("%12d|", eachAllotte.getPhoneNo());
		System.out.printf("%14s|", eachAllotte.getAddress());
		System.out.println();
	}

	private void viewAllotteList() {
		List<Allotte> allotteList = hostelDB.getAllotteList();
		System.out.println("\nSTAYING ROOM ID \tSTAYING BED ID  \tALLOTTE ID\tALLOTTE NAME\tPHONE NO \t\tADDRESS");
		System.out.print(
				"----------------------------------------------------------------------------------------------------------------\n");
		for (int index = 0; index < allotteList.size(); index++) {
			Allotte eachAllotte = allotteList.get(index);
			System.out.printf("|%17d|", eachAllotte.getStayRoomId());
			System.out.printf("%19d|", eachAllotte.getStayBedId());
			System.out.printf("%19d|", eachAllotte.getAlotteId());
			System.out.printf("%25s|", eachAllotte.getAlotteName());
			System.out.printf("%12d|", eachAllotte.getPhoneNo());
			System.out.printf("%14s|", eachAllotte.getAddress());
			System.out.println();
		}
	}

	private void registerAllotte() {
		System.out.println("----- Availability status of Room are below  -----");
		manage.viewRoomList();
		try {
			System.out.print("Enter Room id : ");
			roomId = input.nextLine();
			Room eachRoom = manage.isRoomAvailable(Integer.valueOf(roomId));
			if (eachRoom != null) {
				System.out.print("Room Available\n you can check the available bed List now...");
				manage.viewBedList(eachRoom);
				System.out.print("Choose Bed id : ");
				bedId = input.nextLine();
				Bed eachBed = manage.isBedAvailable(eachRoom, Integer.valueOf(bedId));
				if (eachBed != null) {
					System.out.print("\nEnter Alotte id : ");
					allotteId = input.nextLine();
					if ((manage.isValid("^[1-9][0-9][0-9][0-9][1-9]$", allotteId)) == false) {
						while ((manage.isValid("^[1-9][0-9][0-9][0-9][1-9]$", allotteId)) == false) {
							System.out.println("Invalid Allotte Id !  ----  Enter Allotte ID again (e.g. 10001)");
							allotteId = input.nextLine();
						}
					}
					if (manage.isAllotteAvailable(Integer.valueOf(allotteId)) == null) {
						System.out.print("\nEnter Alotte Name : ");
						allotteName = input.nextLine();
						if ((manage.isValid("^[a-zA-Z ]+$", allotteName)) == false) {
							while ((manage.isValid("^[a-zA-Z ]+$", allotteName)) == false) {
								System.out.println(
										"Invalid Allotte Name !  ----  Enter Allotte Name again (e.g.  Mugi k)    (No numbers Included)");
								allotteName = input.nextLine();
							}
						}
						System.out.print("\nEnter Alotte PhoneNo. : ");
						phoneNo = input.nextLine();
						if ((manage.isValid("^[6-9][0-9]{9}", phoneNo)) == false) {
							while ((manage.isValid("^[6-9][0-9]{9}", phoneNo)) == false) {
								System.out.println(
										"Invalid Phone No. !  ----  Enter Phone Number again (indian phone number(10 digits))");
								phoneNo = input.nextLine();
							}
						}
						System.out.print("\nEnter Alotte Address :  ");
						address = input.nextLine();
						entry = new SimpleDateFormat("EEE / dd-MMM-YYYY / hh:mm:ss aa")
								.format(Calendar.getInstance().getTime());
						transact = manage.register(roomId, bedId, allotteId, allotteName, phoneNo, address, entry,
								eachRoom, eachBed);
						System.out
								.println("\n" + allotteName + " with Id " + allotteId + " is Allotted at the Bed ID : "
										+ bedId + " in Room no : " + roomId + " on  " + entry);
						System.out.println("\nAmount for 1 month is --- 3000");
						paid = getInput();
						while (Double.valueOf(paid) < 1500 || Double.valueOf(paid) > 3000) {
							if (Double.valueOf(paid) < 1500) {
								System.out.println("\nYou must pay atleast 1500 as advance.....");
								paid = getInput();
							} else if (Double.valueOf(paid) > 3000) {
								System.out.println("*** Please don't give amount exceed 3000 ***");
								paid = getInput();
							}
						}
						due = ((Double) (3000 - Double.valueOf(paid))).toString();
						if (Double.valueOf(paid) == 3000) {
							System.out.println("You paid full amount Rs." + paid);
							manage.addTransaction(paid, due, transact);
						} else {
							System.out.println("You paid Rs." + paid + "  with DUE amount of Rs." + due);
							manage.addTransaction(paid, due, transact);
						}
					} else {
						System.out.println("Allotte Id already Exists");
						registerAllotte();
					}
				} else {
					System.out.println(
							"\nSorry ....  You have given WRONG BED ID or Bed you chose is currently NOT Available\nPlease Select other room with available option ");
					registerAllotte();
				}
			} else {
				System.out.println(
						"\nSorry .... Room you chose is currently NOT Available or You have given wrong RoomID\nPlease Select other room with available option ");
				registerAllotte();
			}
		} catch (NumberFormatException e) {
			System.out.println("Exited");
		} catch (InputMismatchException e) {
			System.out.println("Enter valid input");
		}
	}

	private String getInput() {
		System.out.print("\nEnter the amount you want to pay as of now ------ ");
		paid = input.nextLine();
		if ((manage.isValid("^[1-9][0-9]+$", paid)) == false) {
			while ((manage.isValid("^[1-9][0-9]+$", paid)) == false) {
				System.out.println("Invalid paid Amount!  ----  Enter paying amount again (No alphabets included)");
				paid = input.nextLine();
			}
		}
		return paid;
	}

	private void removeAllotte() {
		double due;
		System.out.println("\nEnter Alotte id : ");
		allotteId = input.next();
		allotte = manage.isAllotteAvailable(Integer.valueOf(allotteId));
		if (allotte != null) {
			viewAllotte(allotte);
			System.out.println("\n*********************\n");
			Transactions transact = manage.getTransaction(Integer.valueOf(allotteId));
			viewTransaction(Integer.valueOf(allotteId));
			due = manage.getDue(Integer.valueOf(allotteId));
			roomId = ((Integer) allotte.getStayRoomId()).toString();
			bedId = ((Integer) allotte.getStayBedId()).toString();
			Room room = manage.isRoomAvailable(Integer.valueOf(roomId));
			Bed bed = manage.isBedAvailable(room, Integer.valueOf(bedId));
			if (due == 0) {
				manage.remove(bed, allotte, transact, room);
				System.out.println("succesfully removed...\n THANK YOU ");
			} else {
				System.out.println("\n***************************\nALLOTTE ID " + allotteId + " have Due of Rs. " + due
						+ "\n please clear it...");
				check(due, room, bed, allotte, transact);
			}
		} else {
			System.out.println("You have entered wrong allotte Id");
			removeAllotte();
		}
	}

	private void viewTransaction(int allotteId) {
		List<Transactions> TransactList = hostelDB.getTransactionList();
		System.out.println("\nROOM ID \tBED ID  \tALLOTTE ID\tENTRY\tEXIT");
		System.out.print("---------------------------------------------------------------------\n");
		for (int index = 0; index < TransactList.size(); index++) {
			Transactions eachTransact = TransactList.get(index);
			if (eachTransact.getAllotteId() == allotteId) {
				System.out.printf("|%10d|", eachTransact.getRoomId());
				System.out.printf("%10d|", eachTransact.getBedId());
				System.out.printf("%10d|", eachTransact.getAllotteId());
				System.out.printf("%25s|", eachTransact.getEntryTime());
				System.out.printf("%25s|", eachTransact.getExitTime());
				System.out.println();
				List<Monthly> month = eachTransact.viewMonthTransact();
				System.out.println("\n*********************\n");
				System.out.println("\nPaid amount \tDue  \tMonth");
				System.out.print("---------------------------------------\n");
				for (int i = 0; i < month.size(); i++) {
					Transactions.Monthly eachMonth = month.get(i);
					System.out.printf("|%15.2f|", eachMonth.getPaid());
					System.out.printf("%15.2f|", eachMonth.getDue());
					System.out.printf("%19s|", eachMonth.getCurrentMonth());
					System.out.println();
				}
			}
		}
	}

	private void check(double due, Room room, Bed bed, Allotte allotte, Transactions transact) {
		double paid;
		System.out.print("enter the amount to pay--->");
		paid = input.nextDouble();
		if (paid == due) {
			manage.remove(bed, allotte, transact, room);
			System.out.println("\nSuccesfully removed...\n THANK YOU ");
		} else {
			System.out.println("\nEnter the full due amount to vacate from the hostel--->");
			check(due, room, bed, allotte, transact);
		}
	}

}
