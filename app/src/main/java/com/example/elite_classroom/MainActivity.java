package com.example.elite_classroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.security.AlgorithmConstraints;

public class MainActivity extends AppCompatActivity {



    Button sign_out_button ;
    GoogleSignInClient mGoogleSignInClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign_out_button= findViewById(R.id.sign_out_button);

        sign_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

                mGoogleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);
                mGoogleSignInClient.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                Toast.makeText(MainActivity.this,"Signed_Out",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });


        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(account!=null)
        {
            Toast.makeText(MainActivity.this,account.getEmail(),Toast.LENGTH_LONG).show();

        }



    }
}