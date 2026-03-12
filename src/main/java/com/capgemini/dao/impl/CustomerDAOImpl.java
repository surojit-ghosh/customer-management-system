package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.capgemini.dao.CustomerDAO;
import com.capgemini.entity.Customer;

public class CustomerDAOImpl implements CustomerDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ems");

    @Override
    public String saveCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return "Customer saved successfully!";
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return "Failed to save customer: " + e.getMessage();
        } finally {
            em.close();
        }
    }

    @Override
    public String updateCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(customer);
            em.getTransaction().commit();
            return "Customer updated successfully!";
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return "Failed to update customer: " + e.getMessage();
        } finally {
            em.close();
        }
    }

    @Override
    public String deleteCustomerById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, id);
            if (customer != null) {
                em.remove(customer);
                em.getTransaction().commit();
                return "Customer deleted successfully!";
            } else {
                em.getTransaction().rollback();
                return "Customer not found with id: " + id;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return "Failed to delete customer: " + e.getMessage();
        } finally {
            em.close();
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("FROM Customer", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery(
                    "FROM Customer c WHERE c.email = :email", Customer.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
