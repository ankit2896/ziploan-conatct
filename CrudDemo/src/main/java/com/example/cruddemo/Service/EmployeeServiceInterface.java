package com.example.cruddemo.Service;

import com.example.cruddemo.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface EmployeeServiceInterface {
     public Contact addContact(Contact employee);

     public List<Contact> getAllContactDetails();

      public Optional<Contact> getContactbyId(int empid);

    void deleteContactById(int empid);
}
