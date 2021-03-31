package com.example.upgradst2.repository;

import com.example.upgradst2.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.*;

@Repository
public class UserRepository {

    @PersistenceUnit(unitName = "techblog")
    private EntityManagerFactory entityManagerFactory;


    public void registerUser(User newUser) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            entityManager.persist(newUser);
            transaction.commit();
        }
        catch(Exception e) {
            System.out.println(e);
            transaction.rollback();
        }
    }
    public List<User> getAllUser(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery(
                "SELECT c FROM User c WHERE c.fullName LIKE :custName")
                .setParameter("custName", name)
                .getResultList();

    }

}