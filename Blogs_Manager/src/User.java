import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class User {
	private Connection con;
	private Scanner sc;

	public User(Connection con, Scanner sc) {
		// TODO Auto-generated constructor stub
		this.con = con;
		this.sc = sc;
	}

	public void registration() {
		System.out.println("Enter the Following Information for Registration ");
		System.out.println("Full Name");
		sc.nextLine();
		String fullname = sc.nextLine();
		System.out.println("City:");
		String city = sc.next();
		System.out.println("Email");
		String email = sc.next();
		System.out.println("Password");
		String pass = sc.next();
		System.out.println("Phone_No");
		String phone = sc.next();
		if (!user_exit(email)) {
			String register = "Insert into User(full_name,address,email,password,phone_no,created_time) values(?,?,?,?,?,?)";
			try {
				PreparedStatement stmt = con.prepareStatement(register);
				stmt.setString(1, fullname);
				stmt.setString(2, city);
				stmt.setString(3, email);
				stmt.setString(4, pass);
				stmt.setString(5, phone);
				stmt.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
				int count = stmt.executeUpdate();
				if (count > 0) {
					System.out.println("Registration Successfully");
				} else {
					System.out.println("Data not Inserted plz try agian");
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} 
		else {
			System.out.println("User already Exists, try another email id");
		}

	}

	public boolean user_exit(String email) {
		String sql = "Select * from User where email=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet set = stmt.executeQuery();
			if (set.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;

	}

	public String login() {
		String login = "Select * from User where email=? and password=?";
		try {
			PreparedStatement stmt = con.prepareStatement(login);
			System.out.println("Email:");
			String email = sc.next();
			System.out.println("password");
			String pass = sc.next();
			stmt.setString(1, email);
			stmt.setString(2, pass);
			ResultSet set = stmt.executeQuery();
			if (set.next()) {
				return email;
			} else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}

	public int getuserId(String email) {
		String getid = "Select uid from User where email=?";
		try {
			PreparedStatement stmt = con.prepareStatement(getid);
			stmt.setString(1, email);
			ResultSet set = stmt.executeQuery();
			if (set.next()) {
				return set.getInt("uid");
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
