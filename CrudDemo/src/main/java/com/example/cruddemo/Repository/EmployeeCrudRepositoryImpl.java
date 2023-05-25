package com.example.cruddemo.Repository;

import com.example.cruddemo.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class EmployeeCrudRepositoryImpl {

    @Autowired
    private EntityManager entityManager;

    public List<Contact> getContactsBasedOnParameters(String firstName , String lastName , String email){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> query = cb.createQuery(Contact.class);
        Root<Contact> root = query.from(Contact.class);

        Predicate predicate = cb.conjunction();

        if (firstName != null) {
            predicate = cb.and(predicate, cb.equal(root.get("firstName"), firstName));
        }

        if (lastName != null) {
            predicate = cb.and(predicate, cb.equal(root.get("lastName"), lastName));
        }

        if (email != null) {
            predicate = cb.and(predicate, cb.equal(root.get("email"), email));
        }

        query.where(predicate);

        List<Contact> contacts = entityManager.createQuery(query).getResultList();

        if (contacts.isEmpty()) {
            return null;
        } else {
            return contacts;
        }
    }
}
