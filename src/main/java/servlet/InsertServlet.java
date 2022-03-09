package servlet;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ProductBean;
import dao.DAO;

@WebServlet(name = "InsertServlet", urlPatterns = { "/InsertServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server

		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		String type = request.getParameter("type");
		String supplier = request.getParameter("supplier");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int stock = Integer.parseInt(request.getParameter("stock"));
		double cost = Math.ceil(Double.parseDouble(request.getParameter("cost")) * 10.0) / 10.0;
		double price = Math.ceil(Double.parseDouble(request.getParameter("price")) * 10.0) / 10.0;
		String image = id + ".jpg";

		for (Part part : request.getParts()) {
			part.write("E:/Project/projectWorkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/iSpanSecond/image/product/" + id + ".jpg");
		}
		
		DAO dao = new DAO();
		ProductBean product = new ProductBean(type, supplier, id, name, stock, cost, price, image);
		dao.insert(product);
		response.sendRedirect("index.jsp");
	}
}