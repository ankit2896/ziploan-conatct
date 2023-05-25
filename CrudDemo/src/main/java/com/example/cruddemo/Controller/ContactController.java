package com.example.cruddemo.Controller;

import com.example.cruddemo.CustomException.BusinessException;
import com.example.cruddemo.CustomException.ControllerException;
import com.example.cruddemo.Repository.EmployeeCrudRepository;
import com.example.cruddemo.Repository.EmployeeCrudRepositoryImpl;
import com.example.cruddemo.Service.EmployeeService;
import com.example.cruddemo.Service.EmployeeServiceInterface;
import com.example.cruddemo.entity.Contact;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/code")
public class ContactController {

    @Autowired
    private EmployeeServiceInterface employeeServiceInterface;

   @Autowired
   private EmployeeCrudRepositoryImpl employeeCrudRepository;

    @Operation(summary = "This is to add employee contact in the db")
    @PostMapping("/save")
    public ResponseEntity<?> addEmployee(@RequestBody Contact employee){
        try {
            Contact savedEmployee = employeeServiceInterface.addContact(employee);
            return new ResponseEntity<Contact>(savedEmployee, HttpStatus.CREATED);
        }catch(BusinessException e){
            ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            ControllerException ce = new ControllerException("611","something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "It is used to fetch all the contacts present in db")
    @GetMapping("/all")
    public ResponseEntity<List<Contact>> fetchContact(){
         List<Contact> employeeList   = employeeServiceInterface.getAllContactDetails();
         return new ResponseEntity<List<Contact>>(employeeList,HttpStatus.OK);
    }

    @Operation(summary = "It is used to fetch the contacts based on id")
    @GetMapping("/emp/{empid}")
    public ResponseEntity<?> getContactById(@PathVariable("empid") int empid){
        try {
            Optional<Contact> empretrieved = employeeServiceInterface.getContactbyId(empid);
            return new ResponseEntity<>(empretrieved, HttpStatus.OK);
        }catch(BusinessException e){
            ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            ControllerException ce = new ControllerException("612","something went wrong in controller");
            return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "This is used to search contact based on firstname,lastname or email")
    @GetMapping("/contacts")
    public List<Contact> searchContact(@RequestParam(required = false) String firstName,
                                       @RequestParam(required = false) String lastName,
                                       @RequestParam(required = false) String email){
        try {
            return employeeCrudRepository.getContactsBasedOnParameters(firstName , lastName , email);
        }catch(Exception e){

        }
      return null;
    }
    @Operation(summary = "This is used to delete the details of particular contact")
    @DeleteMapping("/emp/{empid}")
    public ResponseEntity<Void> deleteContactbyId(@PathVariable("empid") int empid){

        employeeServiceInterface.deleteContactById(empid);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @Operation(summary = "This is used to update the contact for particular user")
    @PutMapping("/update")
    public ResponseEntity<Contact> UpdateContact(@RequestBody Contact employee){

        Contact savedEmployee = employeeServiceInterface.addContact(employee);
        return  new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
}
