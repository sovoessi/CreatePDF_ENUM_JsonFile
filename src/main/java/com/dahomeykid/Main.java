package com.dahomeykid;

import com.dahomeykid.entity.Book;
import com.google.gson.Gson;

import java.io.*;
import java.util.List;

public class Main {

    // private final static String path = "C://Library";

    public static void main(String[] args){
        System.out.print("Welcome to the PDF ENUM MOBI TXT from Directory Lister");

        if (args.length == 0 || args.length > 1 ) {
                throw new IllegalArgumentException("Please provide a valid single directory path name");
        }

        try {
            ReadDir.andList(args[0]);
        } catch (Exception e) {
            e.getMessage();
        }

        List<Book> books = ReadDir.getBookList();

        String json = new Gson().toJson(books);

        File f = new File("books.json");

        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(f)))) {
            out.println(json);
            System.out.println("File Created !");
        } catch (IOException e) {
            e.printStackTrace();
        }

     }



}
