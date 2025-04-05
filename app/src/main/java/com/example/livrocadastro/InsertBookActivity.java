package com.example.livrocadastro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.livrocadastro.model.Book;

public class InsertBookActivity extends AppCompatActivity {

    EditText etTitle, etAuthor, etPages, etPublisher, etReleaseDate;
    Button btnInsert;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_book);

        // Obtém referências dos componentes do layout
        etTitle = findViewById(R.id.etTitle);
        etAuthor = findViewById(R.id.etAuthor);
        etPages = findViewById(R.id.etPages);
        etPublisher = findViewById(R.id.etPublisher);
        etReleaseDate = findViewById(R.id.etReleaseDate);
        btnInsert = findViewById(R.id.btnInsert);

        db = new DatabaseHelper(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString().trim();
                String author = etAuthor.getText().toString().trim();
                String pagesStr = etPages.getText().toString().trim();
                String publisher = etPublisher.getText().toString().trim();
                String releaseDate = etReleaseDate.getText().toString().trim();

                // Validação dos campos
                if (title.isEmpty() || author.isEmpty() || pagesStr.isEmpty() || publisher.isEmpty() || releaseDate.isEmpty()) {
                    Toast.makeText(InsertBookActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                int pages;
                try {
                    pages = Integer.parseInt(pagesStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(InsertBookActivity.this, "Número de páginas inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Cria o objeto Book (usando o construtor sem id)
                Book book = new Book(title, author, pages, publisher, releaseDate);

                // Insere o livro; se ocorrer erro, o método retorna -1
                long id = db.insertBook(book);
                if (id != -1) {
                    Toast.makeText(InsertBookActivity.this, "Livro inserido com sucesso!", Toast.LENGTH_SHORT).show();
                    etTitle.setText("");
                    etAuthor.setText("");
                    etPages.setText("");
                    etPublisher.setText("");
                    etReleaseDate.setText("");
                } else {
                    Toast.makeText(InsertBookActivity.this, "Erro ao inserir livro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
