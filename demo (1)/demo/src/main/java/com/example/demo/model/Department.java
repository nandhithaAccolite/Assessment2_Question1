package com.example.demo.model;

import com.example.demo.ClassDocumentation;

/**
 * This is Department class annotated with @ClassDocumentation
 */
@ClassDocumentation
public class Department {
    /**
     * This is the department function from Department class
     * It doesnot have any annotation
     */
    public void department(){
        System.out.println("There are 7 branches");
    }
}
