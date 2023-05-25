package com.example.cruddemo.Service;

import com.example.cruddemo.CustomException.BusinessException;
import com.example.cruddemo.CustomException.EmptyInputException;
import com.example.cruddemo.Repository.EmployeeCrudRepository;
import com.example.cruddemo.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeServiceInterface{

    @Autowired
    private EmployeeCrudRepository employeeCrudRepository;

    @Override
    public Contact addContact(Contact employee) {
            try {
                Contact saveEmployee = employeeCrudRepository.save(employee);
                return saveEmployee;

            } catch (IllegalArgumentException e) {
                throw new BusinessException("602", "Given employee is null" + e.getMessage());
            } catch (Exception e) {
                throw new BusinessException("603", "Something went wrong on service layer while saving" + e.getMessage());
            }
        }

    @Override
    public List<Contact> getAllContactDetails() {
        List<Contact> savedEmployee = null;
        try {
            savedEmployee = employeeCrudRepository.findAll();

        }catch(Exception e){
            throw new BusinessException("605","Something went wrong while fetching employees"+e.getMessage());
        }
        if(savedEmployee.isEmpty()) {
            throw new BusinessException("604", "Hey!list is completely empty,nothing to return");
        }
        return savedEmployee;
    }

    @Override
    public Optional<Contact> getContactbyId(int empid) {
        try {
            Optional<Contact> employee = employeeCrudRepository.findById(empid);
            return employee;
        }catch(IllegalArgumentException e){
            throw new BusinessException("606","Given employee id is null"+e.getMessage());
        }catch(NoSuchElementException e){
            throw new BusinessException("607","given id is not present in DB"+e.getMessage());
        }catch(Exception e){
            throw new BusinessException("608","something went wrong while fetching the employee by id"+e.getMessage());
        }

    }

    @Override
    public void deleteContactById(int empid) {
        try {
            employeeCrudRepository.deleteById(empid);
        }catch(IllegalArgumentException e){
            throw new BusinessException("609","id sent to be deleted is not present in DB!"+e.getMessage());
        }catch(Exception e){
            throw new BusinessException("610","Something wnet wrong while deleting"+e.getMessage());
        }
    }
}
