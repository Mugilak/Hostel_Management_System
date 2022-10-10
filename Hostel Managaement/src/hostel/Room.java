package hostel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Room {
	private int roomId;
	private String blockName;
	private String roomType;
	private Stack<Bed> bedCount;
	private List<Bed> stayingBed;
	private List<Bed> nonStayingBed;

	public enum Status {
		YES, NO;
	}

	private Status availability;

	public Room(int roomId, String blockName, String roomType, Status availability) {
		this.roomId = roomId;
		this.blockName = blockName;
		this.roomType = roomType;
		this.bedCount = new Stack<Bed>();
		this.availability = availability;
		nonStayingBed = new ArrayList<>();
		stayingBed = new ArrayList<>();
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setBed(Bed bed) {
		this.bedCount.push(bed);
	}

	public int getTotalBedCount() {
		return bedCount.size();
	}

	public void setAvailability(Status availability) {
		this.availability = availability;
	}

	public Status getAvailability() {
		return availability;
	}

	public List<Bed> viewTotalBed() {
		List<Bed> list = new ArrayList<>();
		Iterator<Bed> iterator = bedCount.iterator();
		while (iterator.hasNext()) {
			list.add((Bed) iterator.next());
		}
		return list;
	}

	public void setNonStayingBed() {
		this.nonStayingBed = viewTotalBed();
	}
	
	public List<Bed> viewNonStayingBed() {
		return nonStayingBed;
	}

	public void addNonStayingBed(Bed bed) {
		nonStayingBed.add(bed);
	}

	public void deleteNonStayingBed(Bed bed) {
		nonStayingBed.remove(bed);
	}

	public int getNonStayingBedCount() {
		nonStayingBed = viewNonStayingBed();
		return nonStayingBed.size();
	}

	public void addStayingBed(Bed bed) {
		stayingBed.add(bed);
	}

	public void deleteStayingBed(Bed bed) {
		stayingBed.remove(bed);
	}

	public List<Bed> getStayingBed() {
		return stayingBed;
	}

}
