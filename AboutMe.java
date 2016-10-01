package cs320.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Labs/AboutMe")
public class AboutMe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AboutMe() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//bootstrap CDN
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<!-- Latest compiled and minified CSS -->");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");			
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>About | Me</title>");
		out.println("</head>");
		out.println("<body>");
		//bootstrap CDN
		
		out.println("<div class=\"container\">");
		out.println("	<form action=\"AboutMe\" method=\"POST\">");
		out.println("		<div class=\"form-group col-xs-4\">");
		out.println("			<label for=\"cin\">Enter CIN</label> <input type=\"text\" class=\"form-control\" id=\"cinNumber\" name =\"cin\">");
		out.println("			<small class=\"form-text text-muted\">Enter your CIN to view your About page</small></br>");
		out.println("			<button type=\"submit\" class=\"btn btn-primary\">Submit</button>");
		out.println("		</div>");
		out.println("	</form>");
		out.println("</div>");
		
		out.println("</body>");
		out.println("</html>");
	}
	protected void doPost( HttpServletRequest request,
			HttpServletResponse response ) throws ServletException, IOException
	{
		//doGet( request, response );//without this, if we choose "post" as the method in html form tag, it returns a blank page, doesn't "get" anything
		String input = request.getParameter("cin");
		int myCIN = 305334892;
		try{
			if((Integer.parseInt(request.getParameter("cin")) != myCIN || input == null ||  input.trim().length() == 0)){
				//|| (Integer.parseInt(input) >= 97 && Integer.parseInt(input)<=122)
				doGet(request, response);
				return;
			}
			//else if (Integer.parseInt(input) == myCIN ){
			response.setContentType("text/html");

			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<!-- Latest compiled and minified CSS -->");
			out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");			
			out.println("<meta charset=\"UTF-8\">");
			out.println("<title>About | Me</title>");
			out.println("</head>");
			out.println("<body>");
			
			//navbar start
			out.println("<nav class=\"navbar navbar-inverse\">");
			out.println("  <div class=\"container-fluid\">");
			out.println("    <div class=\"navbar-header\">");
			out.println("      <a class=\"navbar-brand\" href=\"#\">CS3220</a>");
			out.println("    </div>");
			out.println("    <ul class=\"nav navbar-nav\">");
			out.println("      <li><a href=\"#\">Home</a></li>");
			out.println("      <li class=\"active\"><a href=\"#\">About</a></li>");
			out.println("    </ul>");
			out.println("  </div>");
			out.println("</nav>");
			// navbar end

			//name div start
			out.println("<div class=\"container\">");
			out.println("  <div class=\"page-header\">");
			out.println("<div id = \"name\">");
			out.println("    <h2>Md Aquib Javed</h2>");
			out.println("  </div>");
			out.println("  </div>");
			out.println("</div>");
			//name div end
			out.println("	<div class=\"container\">");
			//jumbotron
			out.println("<div class=\"jumbotron\">");
			
			out.println("<h2><strong>Hello!</strong></h2>");
			out.println("<p class = \"description\">I have been programming in Java for the past 5 years. Apart from working "
					+ "with Java, I have developed web applications using Laravel,"
					+ " Django and currently working my way through JavaScript. I also worked on the front-end of several web applications. "
					+ "An estimation of total number of lines I've coded would be- Java(6000+), JavaScript(3000+), PHP(1500+), Front-End(5000+)</p>");
			out.println("<p class = \"description\">My core focus is to learn developing web applications, regardless of the language or platform. I have some"
					+ "experience in working with web applications and from the knowledge I gain from this course, I intend to apply and implement it fully towards my aim.</p>");
			out.println("<p class = \"description\"> I love comicbooks, food and browsing weird subreddits.</p>");
			
			out.println("</div>");
			out.println("</div>");
			//jumbotron end

			out.println("</body>");
			out.println("</html>");
		}
		catch(Exception e){
			doGet(request, response);
			return;
		}
	}

}

