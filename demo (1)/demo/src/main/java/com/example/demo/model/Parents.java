package com.example.demo.model;


import com.example.demo.MethodDocumentation;

/**
 * This is Parents class with no annotation
 */
public class Parents {


    /**
     * This is guide method of Parents class annotated with @MethodDocumentation
     */
    @MethodDocumentation
    public void guide(){
        System.out.println("Parents will guide their children");
    }
    /**
     * This is care method of Parents class with no annotation
     */
    public void care(){
        System.out.println("Parents will take care of their children");
    }
}
