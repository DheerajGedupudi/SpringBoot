package com.practice.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Arrays;

public class Driver
{
    public static void main(String[] args)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();

            Student myStudent = mapper.readValue(new File("data/sample-full.json"), Student.class);

            System.out.println("FirstName : "+myStudent.getFirstName());
            System.out.println("LastName : "+myStudent.getLastName());

            Address theAddress = myStudent.getAddress();

            System.out.println("City : "+theAddress.getCity());
            System.out.println("State : "+theAddress.getState());

            System.out.println("Languages known : "+ Arrays.toString(myStudent.getLanguages()));

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
