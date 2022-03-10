package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.ProductBean;
import dao.DAO;

@WebServlet(name = "EditServlet", urlPatterns = { "/EditServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		String oldImageName = id + ".jpg";
		
		Part image = request.getPart("image");
		String newImageName = image.getSubmittedFileName();
		if (newImageName != "") {
			for (Part part : request.getParts()) {
				part.write("E:/Project/projectWorkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/iSpanSecond/images/product/" + id + ".jpg");
			}
		}

		DAO dao = new DAO();
		ProductBean product = new ProductBean(type, supplier, id, name, stock, cost, price, oldImageName);
		dao.edit(product);
		response.sendRedirect("index.jsp");
	}
}

