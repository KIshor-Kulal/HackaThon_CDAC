import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Categories {
	public static List<Categories> list = new ArrayList<>();
	private Connection con;
	private Scanner sc;
	
	public Categories(Connection con, Scanner sc) {
		super();
		this.con = con;
		this.sc = sc;
	}
	private int cid;
	private String title;
	private String description;
	private Date create_time;
	
	
	
	public Categories(int cid, String title, String description, Date create_time) {
		super();
		this.cid = cid;
		this.title = title;
		this.description = description;
		this.create_time = create_time;
	}
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	

	@Override
	public String toString() {
		return "Categories [cid=" + cid + ", title=" + title + ", description=" + description + ", create_time="
				+ create_time + "]";
	}

	public void addCategories()
	{
		String add="Insert into Categories(title,description,creation_time) values(?,?,?)";
		try {
			PreparedStatement stmt=con.prepareStatement(add);
			System.out.println("Enter the Title");
			String title=sc.next();
			System.out.println("Enter the Description");
			sc.nextLine();
			String description = sc.nextLine();
			stmt.setString(1, title);
			stmt.setString(2, description);
			stmt.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			int count=stmt.executeUpdate();
			if(count>0)
			{
				System.out.println("Data insert successflly");
			}
			else
			{
				System.out.println("Data will not be Inserted");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void displayCategories()
	{
		list.clear();
		String display="Select * from Categories";
		try {
			PreparedStatement stmt=con.prepareStatement(display);
			ResultSet set=stmt.executeQuery();
			while(set.next())
			{
				Categories c = new Categories(cid, title,description, create_time);
				c.setCid(set.getInt("cid"));
				c.setTitle(set.getString("title"));
				c.setDescription(set.getString("description"));
				c.setCreate_time(set.getDate("creation_time"));
				list.add(c);
			
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		for(Categories c: list)
		{
			//System.out.println(c);
			System.out.println("cid-"+c.cid+" "+"title-"+c.title+" "+"Description-"+c.description+" "+"Create Time-"+c.create_time );
		}
	}
	
	
	public boolean validCategories(int id)
	{
		for(Categories c: list)
		{
			if(c.getCid()==id)
			{
				return true;
			}
		}
		return false;
	}
	
	
	

}
