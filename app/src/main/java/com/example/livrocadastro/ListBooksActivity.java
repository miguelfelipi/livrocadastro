package com.example.livrocadastro;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.livrocadastro.model.Book;
import java.util.ArrayList;
import java.util.List;

public class ListBooksActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper db;
    List<Book> books = new ArrayList<>();
    ArrayList<String> listItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_books);

        listView = findViewById(R.id.listView);
        db = new DatabaseHelper(this);

        loadBooks();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String action = getIntent().getStringExtra("action");
                if (action != null && (action.equals("update") || action.equals("delete"))) {
                    Book selectedBook = books.get(position);
                    Intent intent = new Intent(ListBooksActivity.this, UpdateBookActivity.class);
                    intent.putExtra("id", selectedBook.getId());
                    intent.putExtra("title", selectedBook.getTitle());
                    intent.putExtra("author", selectedBook.getAuthor());
                    intent.putExtra("pages", selectedBook.getPages());
                    intent.putExtra("publisher", selectedBook.getPublisher());
                    intent.putExtra("releaseDate", selectedBook.getReleaseDate());
                    intent.putExtra("action", action);
                    startActivity(intent);
                }
            }
        });
    }

    private void loadBooks() {
        books.clear();
        listItems.clear();

        Cursor cursor = db.getAllBooks(); // Retorna todas as colunas
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

                // Cria o objeto Book completo (o id é usado internamente)
                Book book = new Book(id, title, author, pages, publisher, releaseDate);
                books.add(book);
                // Exibe na listagem todos os atributos, exceto o id
                listItems.add("Título: " + title +
                        "\nAutor: " + author +
                        "\nPáginas: " + pages +
                        "\nEditora: " + publisher +
                        "\nData de Lançamento: " + releaseDate);
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(this, "Nenhum livro cadastrado", Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadBooks();
    }
}
