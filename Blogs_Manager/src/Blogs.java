import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Blogs {
	public static List<Blogs> blist = new ArrayList<>();
	private Connection con;
	private Scanner sc;
	
	public Blogs(Connection con, Scanner sc) {
		this.con = con;
		this.sc = sc;
	}
	private int bid;
	
	private String title;
	private String contents;
	private Date created_time;
	private int user_id;
	private int category_id;

	public Blogs(int bid, String title, String contents, Date created_time, int user_id, int category_id) {
		//super();
		this.bid = bid;
		this.title = title;
		this.contents = contents;
		this.created_time = created_time;
		this.user_id = user_id;
		this.category_id = category_id;
	}
	
	
	
	
	public Blogs(String title, String contents) {
		
		// TODO Auto-generated constructor stub
		this.title=title;
		this.contents=contents;
	}




	public int getBid() {
		return bid;
	}




	public void setBid(int bid) {
		this.bid = bid;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getContents() {
		return contents;
	}




	public void setContents(String contents) {
		this.contents = contents;
	}




	public Date getCreated_time() {
		return created_time;
	}




	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}




	public int getUser_id() {
		return user_id;
	}




	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}




	public int getCategory_id() {
		return category_id;
	}




	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	




	@Override
	public String toString() {
		return "Blogs [bid=" + bid + ", title=" + title + ", contents=" + contents + ", created_time=" + created_time
				+ ", user_id=" + user_id + ", category_id=" + category_id + "]";
	}




	public void createBlogs(int uid)
	{
		System.out.println(uid);
		String str="Insert into blogs(title,contents,created_time,user_id,category_id) values(?,?,?,?,?)";
		try {
			PreparedStatement stmt=con.prepareStatement(str);
			System.out.println("Enter the Title");
			String title=sc.next();
			System.out.println("Enter the Contents");
			sc.nextLine();
			String contents=sc.nextLine();
			System.out.println("Enter the Category_id");
			int cid=sc.nextInt();
			stmt.setString(1, title);
			stmt.setString(2, contents);
			stmt.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			stmt.setInt(4,uid);
			System.out.println(uid);
			stmt.setInt(5, cid);
			int count=stmt.executeUpdate();
			if(count>0)
			{
				System.out.println("Data Written successfully");
			}
		}
		catch(Exception e)
		{
			System.out.println("Enter valid Categories Id");
		}
		
	}
	public void displayAllBlogs()
	{
		blist.clear();
		String display="select * from blogs";
		try {
			PreparedStatement stmt=con.prepareStatement(display);
			ResultSet set=stmt.executeQuery();
			while(set.next())
			{
				Blogs b = new Blogs(bid, title, contents, created_time, user_id, category_id);
				b.setBid(set.getInt("bid"));
				b.setTitle(set.getString("title"));
				b.setContents(set.getString("contents"));
				b.setCreated_time(set.getDate("created_time"));
				b.setUser_id(set.getInt("user_id"));
				b.setCategory_id(set.getInt("category_id"));
				blist.add(b);
						
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		for(Blogs b : blist)
		{
			System.out.println("bid-"+b.bid+" Title-"+b.title+" Contents-"+b.contents+" CreateTime-"+b.created_time+" User Id-"+b.user_id+" Category Id-"+b.category_id);
		}
	}
	public void showMyBlogs(int uid)
	{
		blist.clear();
		String show="Select * from blogs where user_id=?";
		try {
			PreparedStatement stmt=con.prepareStatement(show);
			stmt.setInt(1, uid);
			System.out.println(uid);
			ResultSet set=stmt.executeQuery();
			while(set.next())
			{
				Blogs b = new Blogs(bid, title, contents, created_time, user_id, category_id);
				b.setBid(set.getInt("bid"));
				b.setTitle(set.getString("title"));
				b.setContents(set.getString("contents"));
				b.setCreated_time(set.getDate("created_time"));
				b.setUser_id(set.getInt("user_id"));
				b.setCategory_id(set.getInt("category_id"));
				blist.add(b);
						
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		for(Blogs b : blist)
		{
			System.out.println("Bid-"+b.bid+" Title-"+b.title+" Contents-"+ b.contents+" CreateTime-" +b.created_time+" User Id-" + b.user_id+" Category Id-"+b.category_id);
		}
		
	}
	public void deleteBlogs(int uid)
	{
		String delet="Delete from Blogs where bid=? and user_id=?";
		try {
			PreparedStatement stmt=con.prepareStatement(delet);
			System.out.println("Enter the id of the record to be deleted: ");
			int id=sc.nextInt();
			stmt.setInt(1, id);
			stmt.setInt(2, uid);
			int count=stmt.executeUpdate();
			if(count>0)
			{
				System.out.println("Blogs Deleted successfully");
			}
			else
			{
				System.out.println("Blogs not Deleted ");
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Enter valid Blog Id to Delete the Records");
		}
	}
	public void displayBlogContents()
	{
		blist.clear();
		String display="select title,contents from blogs where bid=?";
		try {
			PreparedStatement stmt=con.prepareStatement(display);
			System.out.println("Enter Blog id to read the contents of blog");
			bid = sc.nextInt();
			stmt.setInt(1, bid);
			ResultSet set=stmt.executeQuery();
			while(set.next())
			{
				Blogs b = new Blogs(title, contents);
				b.setTitle(set.getString("title"));
				b.setContents(set.getString("contents"));
				blist.add(b);
						
			}
		}
		catch(Exception e)
		{
			System.out.println("Enter valid Blogs Id to read the contents");
		}
		for(Blogs b : blist)
		{
			System.out.println(b.getContents());
		}
	}
	public void editBlog(int uid)
	{
		String edit="Update Blogs set contents=? where bid=? and user_id=?";
		try {
			PreparedStatement stmt=con.prepareStatement(edit);
			System.out.println("Enter Blog id to Edit the contents of blog");
			int id = sc.nextInt();
			System.out.println("Enter the New Contents ");
			sc.nextLine();
			String str=sc.nextLine();
			
			stmt.setString(1, str);
			stmt.setInt(2, id);
			stmt.setInt(3, uid);
			int count=stmt.executeUpdate();
			if(count>0)
			{
				System.out.println("Contents Modified successfully");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Enter the valid blog id to edit the contents");

		}
	}
	public void searchBlog(String word)
	{
		String search="select * from blogs";
		try {
			PreparedStatement stmt=con.prepareStatement(search);
			ResultSet set=stmt.executeQuery();
			while(set.next())
			{
			
				this.setContents(set.getString("contents"));
				if(contents.contains(word))
				{
					System.out.println("Blog ID: "+set.getInt("bid")+" ,"+"Title: "+set.getString("title"));
					
				}
				
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
 
	
	

}
