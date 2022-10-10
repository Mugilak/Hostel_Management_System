package hostel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Transactions {
	private int roomId;
	private int allotteId;
	private int bedId;
	private Stack<Monthly> MonthTransaction;
	private String entry;
	private String exit;

	public Transactions(int roomId, int bedId, int allotteId) {
		this.roomId = roomId;
		this.bedId = bedId;
		this.allotteId = allotteId;
		this.MonthTransaction = new Stack<Monthly>();
	}

	public void setMonthTransaction(Monthly month) {
		this.MonthTransaction.push(month);
	}

	public int monthStayed() {
		return MonthTransaction.size();
	}

	public List<Monthly> viewMonthTransact() {
		List<Monthly> list = new ArrayList<>();
		Iterator<Monthly> iterator = MonthTransaction.iterator();
		while (iterator.hasNext()) {
			list.add((Monthly) iterator.next());
		}
		return list;
	}

	public static class Monthly {
		private double paid;
		private double due;
		private String month;

		public Monthly(double paid, double due, String month) {
			this.month = month;
			this.paid = paid;
			this.due = due;
		}

		public void setCurrentMonth(String month) {
			this.month = month;
		}

		public String getCurrentMonth() {
			return month;
		}

		public void setPaid(double paid) {
			this.paid = paid;
		}

		public double getPaid() {
			return paid;
		}

		public void setDue(double due) {
			this.due = due;
		}

		public double getDue() {
			return due;
		}
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setAllotteId(int allotteId) {
		this.allotteId = allotteId;
	}

	public int getAllotteId() {
		return allotteId;
	}

	public void setBedId(int bedId) {
		this.bedId = bedId;
	}

	public int getBedId() {
		return bedId;
	}

	public void setEntryTime(String entry) {
		this.entry = entry;
	}

	public String getEntryTime() {
		return entry;
	}

	public void setExitTime(String exit) {
		this.exit = exit;
	}

	public String getExitTime() {
		return exit;
	}

}
