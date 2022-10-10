package controller;

import hostel.HostelDatabase;

public class SetupController {
	public boolean isSetUped() {
		int roomSize = HostelDatabase.getInstance().getRoomList().size();
		if(roomSize > 0) {
			return true;
		}
		return false;
	}
	
	public boolean isAllotteSetUped() {
		int roomSize = HostelDatabase.getInstance().getAllotteList().size();
		if(roomSize > 0) {
			return true;
		}
		return false;
	}
	
}
