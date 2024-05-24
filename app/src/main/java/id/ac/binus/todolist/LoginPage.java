package id.ac.binus.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
    Button btnsign;
    EditText username, password;
    DatabaseHelpher db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnsign = findViewById(R.id.btnsign);
        db = new DatabaseHelpher(this);

        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("")||pass.equals(""))
                    Toast.makeText(LoginPage.this, "Please enter all the fields",
                            Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkPassword = db.checkPassword(user, pass);
                    if(checkPassword==true){
                        Toast.makeText(LoginPage.this, "Sign in succesfully",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeMain.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginPage.this, "Invalid",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}