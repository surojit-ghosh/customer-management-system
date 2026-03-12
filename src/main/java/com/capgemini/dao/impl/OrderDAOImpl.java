package com.capgemini.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.capgemini.dao.OrderDAO;
import com.capgemini.entity.Order;

public class OrderDAOImpl implements OrderDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ems");

    @Override
    public String saveOrder(Order order) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
            return "Order saved successfully!";
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return "Failed to save order: " + e.getMessage();
        } finally {
            em.close();
        }
    }

    @Override
    public String updateOrder(Order order) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(order);
            em.getTransaction().commit();
            return "Order updated successfully!";
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return "Failed to update order: " + e.getMessage();
        } finally {
            em.close();
        }
    }

    @Override
    public String deleteOrderById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Order order = em.find(Order.class, id);
            if (order != null) {
                em.remove(order);
                em.getTransaction().commit();
                return "Order deleted successfully!";
            } else {
                em.getTransaction().rollback();
                return "Order not found with id: " + id;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return "Failed to delete order: " + e.getMessage();
        } finally {
            em.close();
        }
    }

    @Override
    public Order getOrderById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Order.class, id);
        } finally {
            em.close();
        }
    }
}
