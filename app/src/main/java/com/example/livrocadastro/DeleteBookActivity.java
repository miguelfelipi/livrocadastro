package com.example.livrocadastro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteBookActivity extends AppCompatActivity {

    EditText etId;
    Button btnDelete;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_book);

        etId = findViewById(R.id.etId);
        btnDelete = findViewById(R.id.btnDelete);
        db = new DatabaseHelper(this);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idStr = etId.getText().toString().trim();
                if (idStr.isEmpty()) {
                    Toast.makeText(DeleteBookActivity.this, "Informe o ID do livro", Toast.LENGTH_SHORT).show();
                    return;
                }
                int id = Integer.parseInt(idStr);
                if (db.deleteBook(id)) {
                    Toast.makeText(DeleteBookActivity.this, "Livro excluído com sucesso!", Toast.LENGTH_SHORT).show();
                    etId.setText("");
                } else {
                    Toast.makeText(DeleteBookActivity.this, "Erro ao excluir livro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
