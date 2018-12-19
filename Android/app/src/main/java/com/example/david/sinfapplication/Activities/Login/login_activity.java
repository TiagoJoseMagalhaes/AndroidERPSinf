package com.example.david.sinfapplication.Activities.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.david.sinfapplication.Activities.Main_Menu.main_menu_activity;
import com.example.david.sinfapplication.Activities.create_customer.create_customer_activity;
import com.example.david.sinfapplication.Activities.create_sales_oportunity.create_sales_oportunity_activity;
import com.example.david.sinfapplication.Activities.edit_customer.edit_customer_activity;
import com.example.david.sinfapplication.Activities.view_customer.view_customer_activity;
import com.example.david.sinfapplication.CommonDataClasses.CustomerBasic;
import com.example.david.sinfapplication.R;
import com.example.david.sinfapplication.WebAPI.WebAPI;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class login_activity extends AppCompatActivity {

    SharedPreferences preferences = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        preferences = getSharedPreferences("Primavera",0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        ((Button)this.findViewById(R.id.login_button)).performClick();
    }

    public void execute_login(View view)
    {
        String username = "";
        String password = "";
        if(this.preferences.contains("username") && this.preferences.contains("password"))
        {
            username = preferences.getString("username","");
            password = preferences.getString("password","");
        }
        else {
            username = ((EditText) this.findViewById(R.id.username)).getText().toString();
            password = ((EditText) this.findViewById(R.id.password)).getText().toString();
        }

        String username_field_text = ((EditText) this.findViewById(R.id.username)).getText().toString();
        String password_field_text = ((EditText) this.findViewById(R.id.password)).getText().toString();

        if(!username_field_text.equals("") && !password_field_text.equals(""))
        {
            username = username_field_text;
            password = password_field_text;
        }

        if(username.equals("")||password.equals(""))
        {
            Log.d("Primavera Login","No Username or password suplied");
            return;
        }

        WebAPI.loginResult result = null;
        try {
            result = WebAPI.login(username,password);
            WebAPI.getAttribOfSalesOportunity("20", "Valor");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        Log.d("Primavera Login","Finished Login Proccess");

        if(result!=null)
        {
            if(result.equals(WebAPI.loginResult.loginSucessfull))
            {
                //go to main menu
                Log.d("Primavera Login","Login Successfull");

                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username",username);
                editor.putString("password",password);
                editor.apply();

                Intent intent = new Intent(this, main_menu_activity.class);
                startActivity(intent);
            }
            else if(result.equals(WebAPI.loginResult.loginFailedWrongUsernameOrPassword))
            {
                // wrong username password combo error msg
                Log.d("Primavera Login","Wrong Username/password combo");

                ((TextView)this.findViewById(R.id.error_pane)).setText("Wrong Username/Password Combo");
            }
            else
            {
                // server error msg
                Log.d("Primavera Login","Login Server Error");
                ((TextView)this.findViewById(R.id.error_pane)).setText("Internal Server Error Please Try Again Later");
            }
        }
    }

}
