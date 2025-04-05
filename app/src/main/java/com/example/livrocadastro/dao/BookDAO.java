package com.example.livrocadastro.dao;

import android.content.Context;
import android.database.Cursor;
import com.example.livrocadastro.DatabaseHelper;
import com.example.livrocadastro.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private DatabaseHelper dbHelper;

    public BookDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public long insert(Book book) {
        return dbHelper.insertBook(book);
    }

    public boolean update(Book book) {
        return dbHelper.updateBook(book);
    }

    public boolean delete(long id) {
        return dbHelper.deleteBook(id);
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        // Usa o método getAllBooks() da DatabaseHelper, que retorna todas as colunas
        Cursor cursor = dbHelper.getAllBooks();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndex("id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String author = cursor.getString(cursor.getColumnIndex("author"));

                // Tratamento para o campo 'pages'
                int pages = 0;
                int pagesIndex = cursor.getColumnIndex("pages");
                if (pagesIndex != -1 && !cursor.isNull(pagesIndex)) {
                    pages = cursor.getInt(pagesIndex);
                }

                // Tratamento para o campo 'publisher'
                String publisher = "";
                int publisherIndex = cursor.getColumnIndex("publisher");
                if (publisherIndex != -1 && !cursor.isNull(publisherIndex)) {
                    publisher = cursor.getString(publisherIndex);
                }

                // Tratamento para o campo 'releaseDate'
                String releaseDate = "";
                int releaseDateIndex = cursor.getColumnIndex("releaseDate");
                if (releaseDateIndex != -1 && !cursor.isNull(releaseDateIndex)) {
                    releaseDate = cursor.getString(releaseDateIndex);
                }

                books.add(new Book(id, title, author, pages, publisher, releaseDate));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return books;
    }
}
