package dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.google.gson.Gson;

import bean.ProductBean;
import uitl.ConnectionFactory;

public class DAO {

	private Connection connection;

	public DAO() {
		this.connection = ConnectionFactory.getConnection();
	}

	public Set<String> XXXXcheckRepeatName() {
		Set<String> set = new HashSet<String>();
		String sqlStr = "SELECT name FROM product ORDER BY Id";
		try {
			PreparedStatement statement = connection.prepareStatement(sqlStr);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				set.add(resultSet.getString("Name").toLowerCase());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set;
	}

	public List<ProductBean> showInOrder(String column) {
		List<ProductBean> list = new ArrayList<>();
		String sqlStr = "SELECT * FROM product ORDER BY id";
		try {
			PreparedStatement statement = connection.prepareStatement(sqlStr);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				list.add(new ProductBean(resultSet.getString("Type"), resultSet.getString("Supplier"),
						resultSet.getString("ID"), resultSet.getString("Name"), resultSet.getInt("Stock"),
						resultSet.getDouble("Cost"), resultSet.getDouble("Price"), resultSet.getString("Image")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void insert(ProductBean product) {
		String sqlStr = "INSERT INTO product (Type, Supplier, ID, Name, Stock, Cost, Price, Image) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sqlStr);) {
			statement.setString(1, product.getType());
			statement.setString(2, product.getSupplier());
			statement.setString(3, product.getId());
			statement.setString(4, product.getName().trim());
			statement.setInt(5, product.getStock());
			statement.setDouble(6, product.getCost());
			statement.setDouble(7, product.getPrice());
			statement.setString(8, product.getImage());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String id) {
		String sqlStr = "DELETE FROM product WHERE id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sqlStr);
			statement.setString(1, id);
			statement.executeUpdate();
			File image = new File("E:/Project/projectWorkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/iSpanSecond/image/product/" + id + ".jpg" );
			image.delete();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void edit(ProductBean product) {
		String sqlStr = "UPDATE product SET Type = ?, Supplier = ?, Name = ?, Stock = ?, Cost = ?, Price = ?, Image = ? WHERE ID = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(sqlStr);
			statement.setString(1, product.getType());
			statement.setString(2, product.getSupplier());
			statement.setString(3, product.getName().trim());
			statement.setInt(4, product.getStock());
			statement.setDouble(5, product.getCost());
			statement.setDouble(6, product.getPrice());
			statement.setString(7, product.getImage());
			statement.setString(8, product.getId());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String exportJson(List<ProductBean> list) {
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
}
