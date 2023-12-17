package com.example.demo.model;

import com.example.demo.ClassDocumentation;
import com.example.demo.MethodDocumentation;

@ClassDocumentation
public class Teacher {
    /**
     * This is the teach method of Teacher class annotated with @MethodDocumentation
     */
    @MethodDocumentation
    public void teach(){
        System.out.println("Teachers will teach");
    }
}
