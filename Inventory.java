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

@WebServlet(urlPatterns = "/Store/Inventory",loadOnStartup =1)
public class Inventory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Inventory() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		List<Item> inventory = new ArrayList<Item>();
		//inventory.add(new Item(1,"Water", "good for health",3, 1));
		getServletContext().setAttribute("inventory", inventory);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<Item> inventory = (List<Item>) getServletContext().getAttribute("inventory");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// In case there isn't anything in the inventory
		if (inventory.size() == 0) {
			out.println("<h3>Thre are no items in your inventory</h3>");
			out.println(" <br /><a href=\"AddItem\">Add Item</a><br/>");
		}

		out.println("<html><head><title>Inventory</title></head><body>");

		//show the search bar only if inventory has anything
		if(inventory.size()!=0){
			out.println("<form action='Inventory? "+ request.getQueryString() + "'  \"method=\"get\" >");
			out.println("<input type=\"text\" name=\"query\" placeholder=\"Search Query\" /> ");
			out.println("<input type=\"submit\" value=\"Search\"><br/><br/>");
			out.println("</form>");
		}

		String query = request.getParameter("query");
		if (query != null && query.trim().length()!=0) {
			out.println("<html><body>");
			out.println("<table border='1'>");
			out.println("<tr><th>Id</th><th>Name</th><th>Description</th><th>Price</th><th>Qty</th><th>Action</th></tr>");
			for (Item item : inventory) {
				//our table has what the user is looking for
				if (item.getName().contains(query) || item.getDescription().contains(query)) {

					out.println("<tr><td>" + item.getId() + "</td><td>" + item.getName() + "</td><td>"
					+ item.getDescription() + "</td><td>" + item.getPrice() + "</td><td>" + item.getQuantity()
					+ "</td><td>"+ "<a href='DeleteItem?id=" + item.getId() + "'> Delete</a></td></tr>");

				}
			}
			out.println("</table>");
			out.println(" <br /><a href=\"AddItem\">Add Item</a><br/>");
			out.println("</body> </html>");


		}
		// For an emptry string, show the entire table
		if (inventory.size() != 0 && (request.getQueryString()==null || query.trim().length()==0)) {
			out.println("<html><body>");
			out.println("<table border='1'>");
			out.println("	<tr><th>Id</th><th>Name</th><th>Description</th><th>Price</th><th>Qty</th><th>Action</th></tr>");
			for (Item item : inventory) {
				out.println("<tr><td>" + item.getId() + "</td><td>" + item.getName() + "</td><td>"
				+ item.getDescription() + "</td><td>" + item.getPrice() + "</td><td>" + item.getQuantity()
				+ "</td><td>" + "<a href='DeleteItem?id=" + item.getId() + "'> Delete</a></td></tr>");
			}
			out.println("</table>");
			out.println(" <br /><a href=\"AddItem\">Add Item</a><br/>");
			out.println("</body> </html>");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
