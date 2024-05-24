package id.ac.binus.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
    Button btnsign;
    EditText username;
    EditText email;
    EditText password;
    EditText Repassword;
    DatabaseHelpher db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Repassword = findViewById(R.id.Repassword);
        btnsign = findViewById(R.id.btnsign);

        db = new DatabaseHelpher(this);

        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = Repassword.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(RegisterPage.this, "Please enter all the fields",
                            Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkUser = db.checkUsername(user);
                        if (checkUser == false) {
                            Boolean insert = db.isDataInserted(user, pass);
                            if (insert == true) {
                                Toast.makeText(RegisterPage.this,
                                        "Registered successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(),
                                        HomeMain.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterPage.this, "Registered failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterPage.this, "User already exists! Please sign in",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterPage.this, "Password not matching",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}