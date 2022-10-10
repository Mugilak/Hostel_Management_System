package hostel;

public class Allotte {
	private int stayingRoomId;
	private int stayingBedId;
	private int alotteId;
	private String alotteName;
	private long phoneNo;
	private String address;

	public Allotte(int stayingRoomId, int stayingBedId, int alotteId, String alotteName, long phoneNo, String address) {
		this.stayingRoomId = stayingRoomId;
		this.stayingBedId = stayingBedId;
		this.alotteId = alotteId;
		this.alotteName = alotteName;
		this.phoneNo = phoneNo;
		this.address = address;
	}

	public int getStayRoomId() {
		return stayingRoomId;
	}

	public void setStayRoomId(int stayingRoomId) {
		this.stayingRoomId = stayingRoomId;
	}

	public int getStayBedId() {
		return stayingBedId;
	}

	public void setStayBedId(int stayingBedId) {
		this.stayingBedId = stayingBedId;
	}

	public int getAlotteId() {
		return alotteId;
	}

	public void setAlotteId(int alotteId) {
		this.alotteId = alotteId;
	}

	public String getAlotteName() {
		return alotteName;
	}

	public void setAlotteName(String alotteName) {
		this.alotteName = alotteName;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
