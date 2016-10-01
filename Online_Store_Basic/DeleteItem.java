package homework;
 
import java.io.IOException;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import homework.Item;
 
@WebServlet("/Store/DeleteItem")
public class DeleteItem extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public DeleteItem() {
        super();
    }
 
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Item> inventory = (List<Item>) getServletContext().getAttribute("inventory");     
        //get the index from url parameter
        int id = Integer.parseInt(request.getParameter("id"));
        for(int i =0;i<inventory.size();i++){
            if(inventory.get(i).getId() == id){
                inventory.remove(i);
                break;
            }
        }      
        response.sendRedirect("Inventory");
    }
}