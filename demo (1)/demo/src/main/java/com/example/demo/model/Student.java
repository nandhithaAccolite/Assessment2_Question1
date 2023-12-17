package com.example.demo.model;

import com.example.demo.ClassDocumentation;
import com.example.demo.MethodDocumentation;

/**
 * This is Student class annotated with @ClassDocumentation
 */
@ClassDocumentation
public class Student {
    /**
     * This is the study method from Student class annotated with @MethodDocumentation
     */
    @MethodDocumentation
    public void study(){
        System.out.println("Students will study");
    }
}
