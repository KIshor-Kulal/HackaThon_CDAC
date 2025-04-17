import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Program {
	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URl = "jdbc:mysql://localhost:3306/Blogs";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "manager";

	static {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection(DB_URl, DB_USER, DB_PASSWORD);
			Scanner sc = new Scanner(System.in);
			User user = new User(con, sc);
			Categories categories = new Categories(con, sc);
			Blogs blogs = new Blogs(con, sc);

			while (true) {
				System.out.println("******************************************");
				System.out.println("Welcome to Blog Management System");
				System.out.println("1.Registration");
				System.out.println("2.Login");
				System.out.println("3.exit");
				System.out.println("Enter the choice");
				int choice1 = sc.nextInt();
				String email;
				int uid;
				// int rid;
				switch (choice1) {
				case 1:
					user.registration();
					break;
				case 2:
					email = user.login();
					uid = user.getuserId(email);
					if (email != null) {
						System.out.println("Login successfully");
						int choice2 = 0;
						while (choice2 != 10) {
							System.out.println("*****************************************");
							System.out.println("1.Show Category");
							System.out.println("2.ADD Category");
							System.out.println("3.Create a Blogs");
							System.out.println("4.Display all Blogs");
							System.out.println("5.Show my Blogs");
							System.out.println("6.Delete Blogs");
							System.out.println("7.Read Blog Contents");
							System.out.println("8.Edit Blogs");
							System.out.println("9.Search Blogs");
							System.out.println("10.Logout");
							System.out.println("*****************************************");
							System.out.println("Enter the Choice:");
							choice2 = sc.nextInt();
							System.out.println();

							switch (choice2) {
							case 1:
								categories.displayCategories();
								break;
							case 2:
								categories.addCategories();
								break;
							case 3:
								categories.displayCategories();
								blogs.createBlogs(uid);
								break;
							case 4:
								blogs.displayAllBlogs();
								break;
							case 5:
								blogs.showMyBlogs(uid);
								break;
							case 6:
								blogs.displayAllBlogs();
								blogs.deleteBlogs(uid);
								break;
							case 7:
								blogs.displayAllBlogs();
								blogs.displayBlogContents();
								break;
							case 8:
								blogs.displayAllBlogs();
								blogs.editBlog(uid);
								break;
							case 9:
								System.out.println("Enter the Word to be searched");
								String word=sc.next();
								blogs.searchBlog(word);
								break;

							case 10:
								System.out.println("Logout Successfully");
								break;
							}
						}
					} else {
						System.out.println("Email and Password are match");
					}
				case 3:
					System.out.println("Thank you Visit again");
					return;
				default:
					System.out.println("Enter correct Choice");

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
