package hostelManagement;

import controller.SetupController;

public class HostelSetup {
	private RoomManage roomManage;
	private AlotteManage allotteManage;
	private SetupController control;

	HostelSetup() {
		control = new SetupController();
		roomManage = new RoomManage();
	}

	public void start() {
		if (control.isSetUped()) {
			roomManage.getUserChoice();
		} else {
			System.out.println("Rooms and Beds are not yet setuped.....\nCreate now-------\n");
			roomManage.create();
			System.out.println("Rooms and Beds are setuped... do any operations");
			roomManage.getUserChoice();
		}
	}
	
}
