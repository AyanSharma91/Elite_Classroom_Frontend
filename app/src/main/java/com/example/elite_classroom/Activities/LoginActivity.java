package com.example.elite_classroom.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elite_classroom.Auth_Response;
import com.example.elite_classroom.Google_Login;
import com.example.elite_classroom.Activities.MainActivity;
import com.example.elite_classroom.R;
import com.example.elite_classroom.Retrofit.DestinationService;
import com.example.elite_classroom.Retrofit.ServiceBuilder;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.security.Provider;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    String sharedPrefFile = "Login_Credentials";
    SignInButton googleBTN ;
    TextView realGoogle  ;
    EditText registration_no;
    Integer RC_SIGN_IN =0;
    Window window;
    SharedPreferences preferences;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        window= LoginActivity.this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        window.setStatusBarColor(ContextCompat.getColor(LoginActivity.this,R.color.black));
        registration_no= findViewById(R.id.registration_no);
        preferences =LoginActivity.this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);


        googleBTN= findViewById(R.id.googleBTN);
        realGoogle = findViewById(R.id.realGoogle);
        realGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(registration_no.getText().toString().trim().length()!=0 )
                {
                    if(registration_no.getText().toString().trim().length()==11)
                    {
                        signIn();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Invalid Registration No",Toast.LENGTH_LONG).show();

                    }

                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Enter your Registration No",Toast.LENGTH_LONG).show();
                }

            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }


    public  void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }



    private void handleSignInResult( Task<GoogleSignInAccount>  completedTask) {


        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if(account!=null) {

                String name =  account.getDisplayName();
                String email=   account.getEmail();


              DestinationService service = ServiceBuilder.INSTANCE.buildService(DestinationService.class);
                Call<Auth_Response> request = service.login_Google_User(new Google_Login(name,email,account.getId(),registration_no.getText().toString()));

                request.enqueue(new Callback<Auth_Response>() {
                    @Override
                    public void onResponse(Call<Auth_Response> call, Response<Auth_Response> response) {
                        SharedPreferences.Editor editor =  preferences.edit();
                        editor.putString("name", name);
                        editor.putString("email",email);
                        editor.putString("token", response.body().getToken());
                        editor.apply();
                        editor.commit();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));


                    }

                    @Override
                    public void onFailure(Call<Auth_Response> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"Something Went wrong",Toast.LENGTH_LONG).show();
                    }
                });



            }

        } catch (ApiException e) {

        }



    }



    @Override
    protected void onStart() {

        if(preferences.getString("token",null)!=null)
        {
            startActivity(new Intent(LoginActivity.this,MainActivity.class));

        }

        super.onStart();
    }
}