package mvc.model.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mvc.model.entity.Customer;

public class CustomerRepository {
	private static final String SELECT_ALL = "SELECT * FROM CUSTOMERS";
	private static final String SELECT_ID = "SELECT * FROM CUSTOMERS WHERE CUSTOMER_ID = ?";
	private static final String INSERT = "INSERT INTO CUSTOMERS (NAME, ADDRESS, WEBSITE, CREDIT_LIMIT) VALUES (?, ?, ?, ?)";
	private static final String DELETE_ID = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = ?";
	private static final String UPDATE_ID = "UPDATE CUSTOMERS SET NAME=?, ADDRESS=?, WEBSITE=?, CREDIT_LIMIT=? WHERE CUSTOMER_ID = ?";

	public static List<Customer> findAll() throws RepositoryException {
		try {
			Connection conn = DBUtils.getConnection();
			List<Customer> list = new ArrayList<>();

			var stm = conn.prepareStatement(SELECT_ALL);
			var rs = stm.executeQuery();

			while (rs != null && rs.next()) {
				Integer id = rs.getInt(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				String website = rs.getString(4);
				Double creditLimit = rs.getDouble(5);
				list.add(new Customer(id, name, address, website, creditLimit));
			}

			return list;
		} catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		}
	}

	public static Optional<Customer> findById(Integer customerId) throws RepositoryException {
		try {
			Connection conn = DBUtils.getConnection();

			var stm = conn.prepareStatement(SELECT_ID);
			stm.setInt(1, customerId);
			var rs = stm.executeQuery();

			if (rs != null && rs.next()) {
				Integer id = rs.getInt(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				String website = rs.getString(4);
				Double creditLimit = rs.getDouble(5);
				return Optional.of(new Customer(id, name, address, website, creditLimit));
			} else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		}
	}

	public static void newCustomer(Customer c) throws RepositoryException {
		try {
			Connection conn = DBUtils.getConnection();

			var stm = conn.prepareStatement(INSERT);
			stm.setString(1, c.getName());
			stm.setString(2, c.getAddress());
			stm.setString(3, c.getWebsite());
			stm.setDouble(4, c.getCreditLimit());
			int num = stm.executeUpdate();

			if (num != 1) {
				throw new RepositoryException("Se produjo un error al insertar el cliente");
			} 
		} catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		}
		
	}

	public static void deleteCustomerById(Integer id) throws RepositoryException {
		try {
			Connection conn = DBUtils.getConnection();

			var stm = conn.prepareStatement(DELETE_ID);
			stm.setInt(1, id);
			int num = stm.executeUpdate();

			if (num != 1) {
				throw new RepositoryException("Se produjo un error al eliminar el cliente");
			} 
		} catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		}
		
	}

	public static void updateCustomerById(int id, String name, String address, String website, Double creditLimit) throws RepositoryException {
		try {
			Connection conn = DBUtils.getConnection();

			var stm = conn.prepareStatement(UPDATE_ID);
			stm.setString(1, name);
			stm.setString(2, address);
			stm.setString(3, website);
			stm.setDouble(4, creditLimit);
			stm.setInt(5, id);
			int num = stm.executeUpdate();

			if (num != 1) {
				throw new RepositoryException("Se produjo un error al actualizar el cliente");
			} 
		} catch (SQLException e) {
			throw new RepositoryException(e.getMessage());
		}
		
	}

}
