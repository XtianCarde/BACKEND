package com.riwi.operations.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.operations.entities.Operation;

@RestController
@RequestMapping("/api/v1/operations")
public class OperationController {
    
    @PostMapping("/sum")
    public String sum(@RequestBody Operation objOperation){
        
        int num1 = objOperation.getNum1();
        int num2 = objOperation.getNum2();
        return num1 + " + " + num2 + " = " + (num1+num2);
    }

    @PostMapping("/subtraction")
    public String subtraction(@RequestBody Operation objOperation){
        
        int num1 = objOperation.getNum1();
        int num2 = objOperation.getNum2();
        return num1 + " - " + num2 + " = " + (num1-num2);
    }

    @PostMapping("/div")
    public String div(@RequestBody Operation objOperation){
        
        int num1 = objOperation.getNum1();
        int num2 = objOperation.getNum2();

        if (num2 == 0) return "Error --> The num2 can not be 0";
        return num1 + " / " + num2 + " = " + (num1/num2);
    }

    @PostMapping("/multiplication")
    public String multiplication(@RequestBody Operation objOperation){
        
        int num1 = objOperation.getNum1();
        int num2 = objOperation.getNum2();
        return num1+ " * " + num2 + " = " + (num1*num2);
    }
}
