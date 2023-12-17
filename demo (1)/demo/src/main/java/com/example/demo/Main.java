package com.example.demo;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Main {
    static List<String> javaDocs = new ArrayList<String>();
    public static void main(String[] args) {
        findingClassesAndMethodsWithAnnotation();
    }

    public static void findingClassesAndMethodsWithAnnotation() {
        try (Stream<Path> p = Files.walk(Paths.get("C:\\Users\\linga.nandhitha\\Downloads\\demo (1)\\demo\\src\\main\\java\\com\\example\\demo\\model"))) {
            p
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(Main::processingFile);
        } catch (IOException e) {
            System.out.println("Error occured while reading the files");
        }
    }

    private static void processingFile(Path path) {
        Path outputPath = Paths.get("C:\\Users\\linga.nandhitha\\Downloads\\demo (1)\\demo\\src\\main\\resources\\javadoc");

        try {
            CompilationUnit cu = JavaParser.parse(path.toFile());

            for (TypeDeclaration type : cu.getTypes()) {

                // Check if class is annotated
                if (type.getAnnotations().stream().anyMatch(a -> a.getName().getName().equals(ClassDocumentation.class.getSimpleName()))) {
                    System.out.println("Class "+type.getName()+" annotated with @ClassDocumentation");

                    Comment co = type.getComment();
                    if (co instanceof JavadocComment) {
                        JavadocComment c = (JavadocComment) co;
                        String javadoc = "Class " + type.getName() + " conatins JavaDoc \n" + c.toString() + "\n";
                        javaDocs.add(javadoc);
                    }else{
                        System.out.println("Class " + type.getName() + " doesnot contain JavaDoc");
                    }

                }else{
                    System.out.println("Class "+type.getName()+" not annotated with @ClassDocumentation");

                    Comment co = type.getComment();
                    if (co instanceof JavadocComment) {
                        JavadocComment c = (JavadocComment) co;
                        String javadoc = "Class " + type.getName() + " contains JavaDoc \n" + c.toString() + "\n";
                        javaDocs.add(javadoc);
                    }else{
                        System.out.println("Class " + type.getName() + " doesnot contain JavaDoc");
                    }
                }

                for (BodyDeclaration member : type.getMembers()) {
                    if (member instanceof MethodDeclaration) {
                        MethodDeclaration m = (MethodDeclaration) member;
                        if (m.getAnnotations().stream().anyMatch(a -> a.getName().getName().equals(MethodDocumentation.class.getSimpleName()))) {
                            System.out.println("Method "+m.getName()+ " of class "+type.getName()+"  annotated with @MethodDocumentation");

                            Comment co = m.getComment();
                            if (co instanceof JavadocComment) {
                                JavadocComment c = (JavadocComment) co;
                                String javadoc = "Method " + m.getName() + " of class " + type.getName() + "  contains JavaDoc \n" + c.toString() + "\n";
                                javaDocs.add(javadoc);
                            }else{
                                System.out.println("Method " + m.getName() + " of class " + type.getName() + "does not conatins JavaDoc");
                            }
                        }else{
                            System.out.println("Method "+m.getName()+ " of class "+type.getName()+" not annotated with @MethodDocumentation");

                            Comment co = m.getComment();
                            if (co instanceof JavadocComment) {
                                JavadocComment c = (JavadocComment) co;
                                String javadoc = "Method " + m.getName() + " of class " + type.getName() + " contains JavaDoc \n" + c.toString() + "\n";
                                javaDocs.add(javadoc);
                            }else{
                                System.out.println("Method " + m.getName() + " of class " + type.getName() + " does not contain JavaDoc");
                            }
                        }
                    }
                }
                System.out.println();
            }

            try {
                Files.write(outputPath, javaDocs, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                System.out.println("Error occured while writing to  the output file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error occured while reading the file: " + path);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}