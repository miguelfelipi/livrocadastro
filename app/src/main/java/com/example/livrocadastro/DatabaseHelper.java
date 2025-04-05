package com.example.livrocadastro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.livrocadastro.model.Book;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "books.db";
    // Atualize a versão para 2 (ou superior) para forçar a recriação da tabela
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_BOOKS = "books";

    // Colunas
    private static final String COL_ID = "id";
    private static final String COL_TITLE = "title";
    private static final String COL_AUTHOR = "author";
    private static final String COL_PAGES = "pages";
    private static final String COL_PUBLISHER = "publisher";
    private static final String COL_RELEASE_DATE = "releaseDate";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_BOOKS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITLE + " TEXT, " +
                COL_AUTHOR + " TEXT, " +
                COL_PAGES + " INTEGER, " +
                COL_PUBLISHER + " TEXT, " +
                COL_RELEASE_DATE + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Para desenvolvimento, podemos descartar os dados antigos
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(db);
    }

    // Inserir livro
    public long insertBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, book.getTitle());
        values.put(COL_AUTHOR, book.getAuthor());
        values.put(COL_PAGES, book.getPages());
        values.put(COL_PUBLISHER, book.getPublisher());
        values.put(COL_RELEASE_DATE, book.getReleaseDate());
        return db.insert(TABLE_BOOKS, null, values);
    }

    // Atualizar livro
    public boolean updateBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, book.getTitle());
        values.put(COL_AUTHOR, book.getAuthor());
        values.put(COL_PAGES, book.getPages());
        values.put(COL_PUBLISHER, book.getPublisher());
        values.put(COL_RELEASE_DATE, book.getReleaseDate());
        int result = db.update(TABLE_BOOKS, values, COL_ID + "=?", new String[]{String.valueOf(book.getId())});
        return result > 0;
    }

    // Excluir livro
    public boolean deleteBook(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_BOOKS, COL_ID + "=?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    // Retorna todas as colunas da tabela
    public Cursor getAllBooks() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_BOOKS, null);
    }
}
