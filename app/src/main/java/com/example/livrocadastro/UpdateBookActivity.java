package com.example.livrocadastro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.livrocadastro.model.Book;

public class UpdateBookActivity extends AppCompatActivity {

    // O id é usado internamente, não será exibido
    long bookId;
    EditText etTitle, etAuthor, etPages, etPublisher, etReleaseDate;
    Button btnUpdate, btnDelete;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        // Busque os componentes do layout
        etTitle = findViewById(R.id.etTitle);
        etAuthor = findViewById(R.id.etAuthor);
        etPages = findViewById(R.id.etPages);
        etPublisher = findViewById(R.id.etPublisher);
        etReleaseDate = findViewById(R.id.etReleaseDate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        db = new DatabaseHelper(this);

        // Recebe os dados enviados pela ListBooksActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("id")) {
            bookId = intent.getLongExtra("id", -1);
            String title = intent.getStringExtra("title");
            String author = intent.getStringExtra("author");
            int pages = intent.getIntExtra("pages", 0);
            String publisher = intent.getStringExtra("publisher");
            String releaseDate = intent.getStringExtra("releaseDate");

            etTitle.setText(title);
            etAuthor.setText(author);
            etPages.setText(pages != 0 ? String.valueOf(pages) : "");
            etPublisher.setText(publisher != null ? publisher : "");
            etReleaseDate.setText(releaseDate != null ? releaseDate : "");
        }

        // Se a ação for "delete", ocultamos o botão de atualizar
        String action = intent.getStringExtra("action");
        if ("delete".equals(action)) {
            btnUpdate.setVisibility(View.GONE);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString().trim();
                String author = etAuthor.getText().toString().trim();
                String pagesStr = etPages.getText().toString().trim();
                String publisher = etPublisher.getText().toString().trim();
                String releaseDate = etReleaseDate.getText().toString().trim();

                if (title.isEmpty() || author.isEmpty() || pagesStr.isEmpty() || publisher.isEmpty() || releaseDate.isEmpty()) {
                    Toast.makeText(UpdateBookActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                int pages;
                try {
                    pages = Integer.parseInt(pagesStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(UpdateBookActivity.this, "Número de páginas inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Cria o objeto Book com os dados atualizados
                Book book = new Book(bookId, title, author, pages, publisher, releaseDate);

                if (db.updateBook(book)) {
                    Toast.makeText(UpdateBookActivity.this, "Livro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UpdateBookActivity.this, "Erro ao atualizar livro", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (db.deleteBook(bookId)) {
                    Toast.makeText(UpdateBookActivity.this, "Livro excluído com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UpdateBookActivity.this, "Erro ao excluir livro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
