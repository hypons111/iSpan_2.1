package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.ProductBean;
import dao.DAO;

@WebServlet(name = "AllDataJsonServlet", urlPatterns = { "/AllDataJsonServlet" })
public class AllDataJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AllDataJsonServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		DAO dao = new DAO();
		List<ProductBean> resultList = dao.showInOrder("id");
		String jsonString = dao.exportJson(resultList);
		out.print(jsonString);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
