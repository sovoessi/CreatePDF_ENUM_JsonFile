package com.dahomeykid;

import com.dahomeykid.entity.Book;
import com.dahomeykid.utils.Extensions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReadDir {

    private static final String[] CHOICES = {Extensions.PDF, Extensions.EPUB};

    private static final List<Book> BOOK_LIST = new ArrayList<>();


    public static void andList(String path) throws IllegalArgumentException{

        File dir = new File(path);

        if (!dir.exists() || !dir.isDirectory())
            throw new IllegalArgumentException("\nThat directory doesn't exist.");
        else
        {
            System.out.println(
                    "\nListing directory tree of:");
            System.out.println(dir.getPath());
            listDirectories(dir);
        }
    }

    private static void listDirectories(File dir)
    {
        File[] dirs = dir.listFiles();

        for (File f : dirs){
            if (f.isDirectory())
            {
                listDirectories(f);
            }else{
                Optional<String> ext = getExtensionByStringHandling(f.getName());
                if(ext.isPresent()){
                    String extension = ext.get();
                    if (hasAdmittedExtensions(extension)){
                        BOOK_LIST.add(new Book(f.getName(), f.getAbsolutePath()));
                    }

                }
            }
        }
    }

    public static List<Book> getBookList(){
        return BOOK_LIST;
    }

    private static boolean hasAdmittedExtensions(String ext){
        for (String s: CHOICES ) {
            if (s.equals(ext))
                return true;
        }
        return false;
    }

    private static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
