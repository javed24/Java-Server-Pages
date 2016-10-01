package cs320.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GradeCalculator")
public class GradeCalculator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GradeCalculator() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		//prepoulating the input fields
		String attendance = request.getParameter("my_attendance") == null ? "" : request.getParameter("my_attendance");
        String hwlabs = request.getParameter("my_hwlabs") == null ? "" : request.getParameter("my_hwlabs");
        String quizes = request.getParameter("my_quizes") == null ? "" : request.getParameter("my_quizes");
        String midterm = request.getParameter("my_midterm") == null ? "" : request.getParameter("my_midterm");
        String myfinal = request.getParameter("my_final") == null ? "" : request.getParameter("my_final");
        
        
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Grade Calculator</title>");
		out.println("</head>");
		out.println("<body><form action=\"GradeCalculator\" method=\"post\">");
		out.println("Attendance: <input type=\"text\" name=\"possible_attendance\" value=\"100\" />  <input type=\"text\" name=\"my_attendance\" value =\""+attendance+"\" /> - "+request.getAttribute("attendance")+" / 5% <br />");
		out.println("HW & Labs: <input type=\"text\" name=\"possible_hwlabs\" value=\"210\" />  <input type=\"text\" name=\"my_hwlabs\" value =\""+hwlabs+"\"/> - "+request.getAttribute("hw&labs")+" / 20%<br />");
		out.println("Quizes: <input type=\"text\" name=\"possible_quizes\" value = \"100\" />  <input type=\"text\" name=\"my_quizes\" value =\""+quizes+"\"/>"+request.getAttribute("quizes")+" / 25% <br />");
		out.println("Midterm: <input type=\"text\" name=\"possible_midterm\" value = \"100\" />  <input type=\"text\" name=\"my_midterm\" value =\""+midterm+"\"/>"+request.getAttribute("midterm")+" / 25% <br />");
		out.println("Final: <input type=\"text\" name=\"possible_final\" value = \"100\" />  <input type=\"text\" name=\"my_final\" value =\""+myfinal+"\"/> "+request.getAttribute("final")+"/ 25% <br />");
		out.println("<input type=\"submit\" value=\"Calculate\" />");
		out.println("<h2>Total: "+request.getAttribute("total")+" / 100</h2>");
		out.println("</form></body>");
		out.println("</html>");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Double my_attendance = Double.parseDouble(request.getParameter("my_attendance"));
		Double possible_attendance = Double.parseDouble(request.getParameter("possible_attendance"));
		Double my_hwlabs = Double.parseDouble(request.getParameter("my_hwlabs"));
		Double possible_hwlabs = Double.parseDouble(request.getParameter("possible_hwlabs"));
		Double my_quizes = Double.parseDouble(request.getParameter("my_quizes"));
		Double possible_quizes = Double.parseDouble(request.getParameter("possible_quizes"));
		Double my_midterm = Double.parseDouble(request.getParameter("my_midterm"));
		Double possible_midterm = Double.parseDouble(request.getParameter("possible_midterm"));
		Double my_final = Double.parseDouble(request.getParameter("my_final"));
		Double possible_final = Double.parseDouble(request.getParameter("possible_final"));
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		//test print
//		out.println("<html>");
//		out.println("<head>");
//		out.println("<meta charset=\"UTF-8\">");
//		out.println("<title>Grade Calculator</title>");
//		out.println("</head>");
//		out.println("<body>");
//		//test print
		//out.println("<h2>" + attendance+"</h2>"); //checked if input works
		
		//for attendance
		double final_attendance = (my_attendance*100)/possible_attendance;
		double final_attendance_in_percent = final_attendance*.05;
	//	out.println("<h2> attendance: " + final_attendance_in_percent+"</h2>");
		
		//for hw&labs
		double final_hw = (my_hwlabs*100)/possible_hwlabs;
		double final_hw_in_percent = final_hw*.2;
	//	out.println("<h2>hw&labs: " + final_hw+"</h2>");
		
		//for quizes
		double final_quizes = (my_quizes*100)/possible_quizes;
		double final_quizes_in_percent = final_quizes*.25;
	//	out.println("<h2>quizes: " + final_quizes+"</h2>");
		
		//for midterm
		double final_midterm = (my_midterm*100)/possible_midterm;
		double final_midterm_in_percent = final_midterm*.25;
	//	out.println("<h2>midterm: " + final_midterm+"</h2>");
		
		//for final
		double final_final = (my_final*100)/possible_final;
		double final_final_in_percent = final_final*.25;
	//	out.println("<h2>hw&labs: " + final_final+"</h2>");
			
	//	out.println("</body>");
	//	out.println("</html>");
		
		//calculating total
		double total = final_attendance_in_percent + final_hw_in_percent+ final_quizes_in_percent + final_midterm_in_percent
				+ final_final_in_percent;
		
		
		request.setAttribute("attendance", final_attendance_in_percent);
		request.setAttribute("hw&labs", final_hw_in_percent);
		request.setAttribute("quizes", final_quizes_in_percent);
		request.setAttribute("midterm", final_midterm_in_percent);
		request.setAttribute("final", final_final_in_percent);
		request.setAttribute("total", df2.format(total));
		//out.println("<h2>total: " + total+"</h2>");
		doGet(request, response);
	}
	private static DecimalFormat df2 = new DecimalFormat(".##"); // rounding to two digits after decimal

}
