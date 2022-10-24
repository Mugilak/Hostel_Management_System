package hostel;

public class Bed {
	private int bedId;
	private String bedSize;
	
	public Bed(int bedId, String bedSize){
		this.bedId = bedId;
		this.bedSize = bedSize;
	}
	
	public void setBedId(int bedId) {
		this.bedId = bedId;
	}
	
	public int getBedId() {
		return bedId;
	}
	
	public void setBedSize(String bedSize) {
		this.bedSize = bedSize;
	}
	
	public String getBedSize() {
		return bedSize;
	}
	
}
