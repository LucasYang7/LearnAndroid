// IBookManager.aidl
package com.example.yangzhe.learnipc;

// Declare any non-default types here with import statements
import com.example.yangzhe.learnipc.Book;
interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
