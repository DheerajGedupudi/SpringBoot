package com.dheeraj.rest;

import com.dheeraj.entity.Customer;
import com.dheeraj.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController
{
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers()
    {
//        System.out.println(customerService.getCustomers());
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getSpecificCustomer(@PathVariable int id)
    {
        Customer myCustomer = customerService.getCustomer(id);

        //not found exception
        if (myCustomer==null)
        {
            throw new CustomerNotFoundException("Customer id is not found : "+id);
        }

        return myCustomer;
    }

    //new customer
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer myCustomer)
    {
        //set id to 0 is for json, it means save or update.
        myCustomer.setId(0);

        customerService.saveCustomer(myCustomer);

        return myCustomer;
    }

    //update customer
    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer myCustomer)
    {
        customerService.saveCustomer(myCustomer);

        return myCustomer;
    }


    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable int id)
    {
        Customer myCustomer = customerService.getCustomer(id);

        //not found exception
        if (myCustomer==null)
        {
            throw new CustomerNotFoundException("Customer id is not found : "+id);
        }

        customerService.deleteCustomer(id);

        return "Deleted customer with id : "+id;
    }
}
