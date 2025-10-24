package mvc.model.service;

import java.util.List;
import java.util.Optional;

import mvc.model.repository.RepositoryException;
import mvc.model.entity.Customer;
import mvc.model.repository.CustomerRepository;

public class CustomerService {

	public static List<Customer> findAll() throws RepositoryException {
		return CustomerRepository.findAll();
	}
	
	public static Optional<Customer> findById(Integer customerId) throws RepositoryException {
		return CustomerRepository.findById(customerId);
	}
	
	public static void newCustomer(String name, String address, String website, Double creditLimit) throws RepositoryException {
		Customer c = new Customer(null, name, address, website, creditLimit);
		CustomerRepository.newCustomer(c);
	}

	public static void deleteCustomerById(Integer id) throws RepositoryException {
		CustomerRepository.deleteCustomerById(id);
	}

	public static void updateCustomerById(int id, String name, String address, String website, Double creditLimit) throws RepositoryException {
		CustomerRepository.updateCustomerById(id, name, address, website, creditLimit);
	}
}
