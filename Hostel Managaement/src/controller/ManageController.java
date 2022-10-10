package controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hostel.Allotte;
import hostel.Bed;
import hostel.HostelDatabase;
import hostel.Room;
import hostel.Transactions;
import hostel.Transactions.Monthly;

public class ManageController {

	private Scanner input = new Scanner(System.in);
	private HostelDatabase hostelDB = HostelDatabase.getInstance();

	public boolean isValid(String regex, String word) {
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(word);
		if (match.find()) {
			return true;
		}
		return false;
	}

	public void viewRoomList() {
		List<Room> roomList = hostelDB.getRoomList();
		System.out.println("\nROOM ID\t\tBLOCK NAME\tROOM TYPE\tBED COUNT\tAVAILABILITY");
		System.out.print("----------------------------------------------------------------------------\n");
		for (int index = 0; index < roomList.size(); index++) {
			Room eachRoom = roomList.get(index);
			System.out.printf("|%10d|", eachRoom.getRoomId());
			System.out.printf("%15s|", eachRoom.getBlockName());
			System.out.printf("%16s|", eachRoom.getRoomType());
			System.out.printf("%14d|", eachRoom.getNonStayingBedCount());
			System.out.printf("%15s|", eachRoom.getAvailability());
			System.out.println();
		}
	}

	public Room isRoomAvailable(int roomId) {
		List<Room> room = hostelDB.getRoomList();
		for (int index = 0; index < room.size(); index++) {
			Room eachRoom = room.get(index);
			if (eachRoom.getRoomId() == roomId && eachRoom.getNonStayingBedCount() > 0) {
				return eachRoom;
			}
		}
		return null;
	}

	public Room roomIdCheck(int roomId) {
		List<Room> room = hostelDB.getRoomList();
		for (int index = 0; index < room.size(); index++) {
			Room eachRoom = room.get(index);
			if (eachRoom.getRoomId() == roomId) {
				return eachRoom;
			}
		}
		return null;
	}

	public void viewBedList(Room room) {
		List<Bed> bedList = room.viewNonStayingBed();
		System.out.println("\nBED ID\t\tBED SIZE");
		System.out.println("-------------------------");
		for (int index = 0; index < bedList.size(); index++) {
			Bed eachBed = bedList.get(index);
			System.out.printf("|%9d|", eachBed.getBedId());
			System.out.printf("%13s|", eachBed.getBedSize());
			System.out.println();
		}
	}

	public Bed isBedAvailable(Room room, int bedId) {
		List<Bed> bedList = room.viewNonStayingBed();
		for (int index = 0; index < bedList.size(); index++) {
			Bed eachBed = bedList.get(index);
			if (eachBed.getBedId() == bedId)
				return eachBed;
		}
		return null;
	}

	public Allotte isAllotteAvailable(int allotteId) {
		List<Allotte> allotteList = hostelDB.getAllotteList();
		for (int index = 0; index < allotteList.size(); index++) {
			Allotte eachAllotte = allotteList.get(index);
			if (eachAllotte.getAlotteId() == allotteId) {
				return eachAllotte;
			}
		}
		return null;
	}

	public double getDue(int allotteId) {
		double sum = 0;
		List<Transactions> TransactList = hostelDB.getTransactionList();
		for (int index = 0; index < TransactList.size(); index++) {
			Transactions eachTransact = TransactList.get(index);
			if (eachTransact.getAllotteId() == allotteId) {
				List<Monthly> month = eachTransact.viewMonthTransact();
				for (int i = 0; i < month.size(); i++) {
					Transactions.Monthly eachMonth = month.get(i);
					if (eachMonth.getDue() >= 0) {
						sum = sum + eachMonth.getDue();
					}
				}
				return sum;
			}
		}
		return 0;
	}

	public Transactions getTransaction(int allotteId) {

		List<Transactions> TransactList = hostelDB.getTransactionList();
		for (int index = 0; index < TransactList.size(); index++) {
			Transactions eachTransact = TransactList.get(index);
			if (eachTransact.getAllotteId() == allotteId) {
				return eachTransact;
			}
		}
		return null;
	}

	public void remove(Bed bed, Allotte allotte, Transactions transact, Room room) {
		room.deleteStayingBed(bed);
		room.addNonStayingBed(bed);
		hostelDB.deleteAllotteList(allotte);
		hostelDB.deleteTransactList(transact);
	}

	public void addRoom(String roomId, String blockName, String roomType) {
		Room room = new Room(Integer.valueOf(roomId), blockName, roomType, Room.Status.YES);
		hostelDB.addRoomList(room);
	}

	public void addBed(Room room, String bedId, String bedSize) {
		Bed bed = new Bed(Integer.valueOf(bedId), bedSize);
		room.setBed(bed);
		room.setNonStayingBed();
	}

	public Transactions register(String roomId, String bedId, String allotteId, String allotteName, String phoneNo,
			String address, Room eachRoom, Bed eachBed) {

		Allotte allotte = new Allotte(Integer.valueOf(roomId), Integer.valueOf(bedId), Integer.valueOf(allotteId),
				allotteName, Long.valueOf(phoneNo), address);
		hostelDB.addAllotteList(allotte);
		eachRoom.addStayingBed(eachBed);
		eachRoom.deleteNonStayingBed(eachBed);
		Transactions transact = new Transactions(Integer.valueOf(roomId), Integer.valueOf(bedId),
				Integer.valueOf(allotteId));
		String entry = new SimpleDateFormat("EEE / dd-MMM-YYYY / hh:mm:ss aa").format(Calendar.getInstance().getTime());
		transact.setEntryTime(entry);
		return transact;
	}

	public void addTransaction(String paid, String due, Transactions transact) {
		String currentMonth = new SimpleDateFormat("MMM").format(Calendar.getInstance().getTime());
		transact.setMonthTransaction(new Transactions.Monthly(Double.valueOf(paid), Double.valueOf(due), currentMonth));
		hostelDB.addTransactionList(transact);
	}

}
