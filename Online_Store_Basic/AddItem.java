package homework;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import homework.Item;
@WebServlet(urlPatterns="/Store/AddItem", loadOnStartup = 1)
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int idSeed = 100;

	public AddItem() {
		super();
	}
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		ArrayList<String> warnings = new ArrayList<String>();
		getServletContext().setAttribute("warnings", warnings);

	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<String> warnings = (ArrayList<String>) getServletContext().getAttribute("warnings");

		//sticky forms
		String stickyName = request.getParameter("name") == null ? "" : request.getParameter("name");
		String stickyDesc = request.getParameter("description") == null ? "" : request.getParameter("description");
		String stickyPrice = request.getParameter("price") == null ? "" : request.getParameter("price");
		String stickyQuant = request.getParameter("quantity") == null ? "" : request.getParameter("quantity");

		response.setContentType( "text/html" );
		PrintWriter out = response.getWriter();
		out.println("<!doctype html><html><head><title>Add Item</title></head>");
		out.println("<body>");
		out.println("	<form action=\"AddItem\" method=\"post\">");
		out.println("	Name: <input type=\"text\" name=\"name\" value='"+stickyName+"'  /> <br /> ");
		if(warnings.contains("wrongName")){
			out.println("<p style='color: #f00;'>Invalid Name</p></p>");
		}
		out.println("	Description: <input type=\"textarea\" name=\"description\" value='"+stickyDesc+"'  /> <br /> ");
		if(warnings.contains("wrongDescription")){
			out.println("<p style='color: #f00;'>Invalid Description</p></p>");
		}
		out.println("	Quantity: <input type=\"text\" name=\"quantity\" value='"+stickyQuant+"'  />  <br />");
		if(warnings.contains("wrongQuantity")){
			out.println("<p style='color: #f00;'>Invalid Quantity</p>");
		}
		out.println("	Price: <input type=\"text\" name=\"price\" value='"+stickyPrice+"'   />  <br />");
		if(warnings.contains("wrongPrice")){
			out.println("<p style='color: #f00;'>Invalid Price</p>");
		}
		out.println("	<input type=\"submit\" value=\"Add\"><br/><br/>");
		out.println("	</form></body></html>");

	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> warnings = (List<String>) getServletContext().getAttribute("warnings");

		boolean checker=false; //gets toggled when an invalid input has been found

		if(request.getParameter("name")==null || request.getParameter("name").trim().length()==0){
			warnings.add("wrongName");
			checker=true;
		}
		if(request.getParameter("description")==null || request.getParameter("description").trim().length()==0){
			warnings.add("wrongDescription");
			checker=true;
		}

		if((request.getParameter("quantity").matches("[0-9]+")!=true) ||request.getParameter("quantity").contains(".")  || request.getParameter("quantity")==null
		|| request.getParameter("quantity").trim().length()==0){

			warnings.add("wrongQuantity");
			checker=true;
		}
		if(!request.getParameter("price").matches("[0-9]+(.[0-9]*{0,1})") || request.getParameter("price")==null || request.getParameter("price").trim().length()==0){
			System.out.println("I shouldn't be here");
			warnings.add("wrongPrice");
			checker=true;
		}

		if(checker==false){
			String name= request.getParameter("name");
			String description= request.getParameter("description");
			int quantity= Integer.parseInt(request.getParameter("quantity"));
			Double price= Double.parseDouble(request.getParameter("price"));
			List<Item> inventory = (List<Item>) getServletContext().getAttribute("inventory");
			inventory.add(new Item(idSeed++,name, description, quantity, price));
			response.sendRedirect( "Inventory" );
		}
		else if(checker==true){
			doGet(request, response);
			warnings.clear();
			return;
		}
	}

}
