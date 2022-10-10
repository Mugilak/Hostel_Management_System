package hostelManagement;

final class LoginViewPage {
	private HostelSetup setup;
	LoginViewPage (){
		setup = new HostelSetup();
	}

	public static void main(String[] args) {
		LoginViewPage login = new LoginViewPage();
		login.entry();
	}

	private void entry() {
		System.out.println("----------  Welcome to HOSTEL MANAGEMENT SYSTEM  ----------\n");
		setup.start();
	}

}
