package hostel;

import java.util.ArrayList;
import java.util.List;

public class HostelDatabase {
	private static HostelDatabase hostelDB;
	private List<Room> roomList;
	private List<Allotte> allotteList;
	private List<Transactions> transactionList;
	
	private HostelDatabase() {
		roomList = new ArrayList<Room>();
		allotteList = new ArrayList<Allotte>();
		transactionList = new ArrayList<Transactions>();
	}
	
	public static HostelDatabase getInstance() {
		if(hostelDB == null) {
			hostelDB =  new HostelDatabase();
		}
		return hostelDB;
	}
	
	//Rooms related Operations
	public void addRoomList(Room room) {
		this.roomList.add(room);
	}
	
	public void deleteRoomList(Room room) {
		this.roomList.remove(room);
	}
	
	public List<Room> getRoomList() {
		return roomList;
	}

	//Allottees related operations
	public void addAllotteList(Allotte allotte) {
		this.allotteList.add(allotte);
	}

	public void addtAllotteList(List<Allotte> allotteList) {
		this.allotteList.addAll(allotteList);
	}
	
	public void deleteAllotteList(Allotte allotteList) {
		this.allotteList.remove(allotteList);
	}
	
	public List<Allotte> getAllotteList() {
		return allotteList;
	}

	// transactions related operations

	public void addTransactionList(Transactions transactionList) {
		this.transactionList.add(transactionList);
	}
	
	public List<Transactions> getTransactionList() {
		return transactionList;
	}
	
	public void deleteTransactList(Transactions transactList) {
		this.transactionList.remove(transactList);
	}
	
}
