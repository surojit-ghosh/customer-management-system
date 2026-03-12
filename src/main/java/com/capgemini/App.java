package com.capgemini;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.dao.CustomerDAO;
import com.capgemini.dao.OrderDAO;
import com.capgemini.dao.impl.CustomerDAOImpl;
import com.capgemini.dao.impl.OrderDAOImpl;
import com.capgemini.entity.Customer;
import com.capgemini.entity.Order;

public class App {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();

        System.out.println("Insert Customer with Order");
        List<Customer> customers = new ArrayList<>();

        Order order1 = new Order("ORD-1001", "Football Boots", 1, 12000.00, LocalDate.of(2025, 3, 1));
        Customer customer1 = new Customer("Lamine Yamal", "lamine.yamal@barca.com", "Male", 9876543210L, LocalDate.of(2025, 1, 10));
        customer1.setOrder(order1);
        customers.add(customer1);

        Order order2 = new Order("ORD-1002", "Shin Guards", 2, 3500.00, LocalDate.of(2025, 3, 3));
        Customer customer2 = new Customer("Pedri Gonzalez", "pedri@barca.com", "Male", 9876543211L, LocalDate.of(2025, 1, 12));
        customer2.setOrder(order2);
        customers.add(customer2);

        Order order3 = new Order("ORD-1003", "Jersey Kit", 3, 4500.00, LocalDate.of(2025, 3, 5));
        Customer customer3 = new Customer("Raphinha Dias", "raphinha@barca.com", "Male", 9876543212L, LocalDate.of(2025, 1, 14));
        customer3.setOrder(order3);
        customers.add(customer3);

        Order order4 = new Order("ORD-1004", "Goal Gloves", 1, 8500.00, LocalDate.of(2025, 3, 7));
        Customer customer4 = new Customer("Robert Lewandowski", "lewy@barca.com", "Male", 9876543213L, LocalDate.of(2025, 1, 16));
        customer4.setOrder(order4);
        customers.add(customer4);

        Order order5 = new Order("ORD-1005", "Training Kit", 2, 6000.00, LocalDate.of(2025, 3, 9));
        Customer customer5 = new Customer("Frenkie de Jong", "frenkie@barca.com", "Male", 9876543214L, LocalDate.of(2025, 1, 18));
        customer5.setOrder(order5);
        customers.add(customer5);

        Order order6 = new Order("ORD-1006", "Tracksuit", 1, 5500.00, LocalDate.of(2025, 3, 11));
        Customer customer6 = new Customer("Pau Cubarsi", "cubarsi@barca.com", "Male", 9876543215L, LocalDate.of(2025, 1, 20));
        customer6.setOrder(order6);
        customers.add(customer6);

        Order order7 = new Order("ORD-1007", "Sports Bag", 1, 3000.00, LocalDate.of(2025, 3, 13));
        Customer customer7 = new Customer("Gavi Paez", "gavi@barca.com", "Male", 9876543216L, LocalDate.of(2025, 1, 22));
        customer7.setOrder(order7);
        customers.add(customer7);

        Order order8 = new Order("ORD-1008", "Compression Socks", 4, 1500.00, LocalDate.of(2025, 3, 15));
        Customer customer8 = new Customer("Jules Kounde", "kounde@barca.com", "Male", 9876543217L, LocalDate.of(2025, 1, 24));
        customer8.setOrder(order8);
        customers.add(customer8);

        Order order9 = new Order("ORD-1009", "Ankle Support", 2, 2000.00, LocalDate.of(2025, 3, 17));
        Customer customer9 = new Customer("Dani Olmo", "olmo@barca.com", "Male", 9876543218L, LocalDate.of(2025, 1, 26));
        customer9.setOrder(order9);
        customers.add(customer9);

        Order order10 = new Order("ORD-1010", "Speed Ladder", 1, 4000.00, LocalDate.of(2025, 3, 19));
        Customer customer10 = new Customer("Marcus Rashford", "rashford@barca.com", "Male", 9876543219L, LocalDate.of(2025, 1, 28));
        customer10.setOrder(order10);
        customers.add(customer10);

        for (Customer customer : customers) {
            String saveResult = customerDAO.saveCustomer(customer);
            System.out.println(saveResult);
        }

        System.out.println("\nFetch Customer by ID");
        Customer fetchedCustomer = customerDAO.getCustomerById(customer3.getId());
        System.out.println(fetchedCustomer);

        System.out.println("\nUpdate Customer");
        fetchedCustomer.setCustomerName("John Smith");
        fetchedCustomer.setPhone(9123456780L);
        String updateResult = customerDAO.updateCustomer(fetchedCustomer);
        System.out.println(updateResult);

        System.out.println("\nFetch All Customers");
        List<Customer> allCustomers = customerDAO.getAllCustomers();
        for (Customer c : allCustomers) {
            System.out.println(c);
        }

        System.out.println("\nUpdate Order and Fetch by ID");
        Order fetchedOrder = orderDAO.getOrderById(order3.getId());
        fetchedOrder.setQuantity(2);
        fetchedOrder.setPrice(140000.00);
        String orderUpdateResult = orderDAO.updateOrder(fetchedOrder);
        System.out.println(orderUpdateResult);

        Order updatedOrder = orderDAO.getOrderById(fetchedOrder.getId());
        System.out.println(updatedOrder);

        System.out.println("\nFetch Customer by Email");
        Customer customerByEmail = customerDAO.getCustomerByEmail("john@example.com");
        System.out.println(customerByEmail);

        System.out.println("\nDelete Customer by ID");
        String deleteResult = customerDAO.deleteCustomerById(customer3.getId());
        System.out.println(deleteResult);
    }
}
