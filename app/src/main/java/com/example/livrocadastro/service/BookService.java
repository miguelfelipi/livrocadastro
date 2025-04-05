package com.example.livrocadastro.service;

import android.content.Context;
import com.example.livrocadastro.dao.BookDAO;
import com.example.livrocadastro.model.Book;
import java.util.List;

public class BookService {
    private BookDAO bookDAO;

    public BookService(Context context) {
        bookDAO = new BookDAO(context);
    }

    public long addBook(Book book) {
        // Aqui você pode adicionar validações, por exemplo:
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Título inválido");
        }
        return bookDAO.insert(book);
    }

    public boolean updateBook(Book book) {
        return bookDAO.update(book);
    }

    public boolean deleteBook(long id) {
        return bookDAO.delete(id);
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }
}
