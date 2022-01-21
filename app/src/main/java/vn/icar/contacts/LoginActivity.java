package vn.icar.contacts;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.icar.contacts.R;

import vn.icar.contacts.api.ApiClient;
import vn.icar.contacts.api.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button btnlogin;
    TextView tvforgetpass, tvsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // ánh xạ
        username= findViewById(R.id.edt_username);
        password= findViewById(R.id.edt_password);
        btnlogin= findViewById(R.id.btn_login);
        tvforgetpass= findViewById(R.id.tv_forgetpass);
        tvsignin= findViewById(R.id.tv_signin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(username.getText().toString())||TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Username/password required", Toast.LENGTH_SHORT).show();
                }
                else {
                   login();
                }
            }
        });
        tvforgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent= new Intent(LoginActivity.this, RegisterActivity.class);
               startActivity(intent);

            }
        });


    }
    public void  login(){
        UserService client = ApiClient.getUserService();
       Call<LoginResponse> loginReponseCall= client.userLogin(username.getText().toString(), password.getText().toString());
       loginReponseCall.enqueue(new Callback<LoginResponse>() {
           @Override
           public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
               Toast.makeText(LoginActivity.this, "Login Successful: " , Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity( new Intent(LoginActivity.this,MainActivity.class));

                    }
                }, 100);
           }
           @Override
           public void onFailure(Call<LoginResponse> call, Throwable t) {
               Toast.makeText(LoginActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();

           }
       });


    }

}