package netroxtech.com.employeetracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import netroxtech.com.employeetracker.Models.Users;
import netroxtech.com.employeetracker.applications.VolleyInitilizer;
import netroxtech.com.employeetracker.utils.Constants;
import netroxtech.com.employeetracker.utils.InitilizeSharePref;

public class LoginManager extends AppCompatActivity {

    EditText userName, password;
    JSONObject reader;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_manager);
         setXML();
    }
    public void setXML(){
        userName = (EditText)findViewById(R.id.userName_activity_loginmanager);
        password = (EditText)findViewById(R.id.password_activity_loginmanager);
    }
    public  void loginManagerButton(View v){
        checkValidation();
    }

    public void loginManager(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("please  Wait");
        pDialog.show();

        StringRequest sendResqurst = new StringRequest(Request.Method.POST, Constants.LOGIN_MANAGER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Users  users = new Users();
                    reader = new JSONObject(response);
                    JSONObject  data = reader.getJSONObject("employee");
                    String  status = data.getString("success");
                    String  role = data.getString("role");
                    users.setRole(role);
                    users.setUserName(userName.getText().toString());

                    new InitilizeSharePref(LoginManager.this).saveUserLogin(users);
                    if(status.equalsIgnoreCase("1") && role.equalsIgnoreCase("manager")){
                        Intent intent = new Intent(LoginManager.this,Options.class);
                        startActivity(intent);
                        //  String data = response;
                        Log.v("message", response);
                        Toast.makeText(getApplicationContext(), "Manager Login", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Sorry Manager No Register",Toast.LENGTH_SHORT).show();
                    }
                    pDialog.hide();

                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error ",error.toString());
                pDialog.hide();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params =  new HashMap<>();
                params.put(Constants.PASSWORD,password.getText().toString());
                params.put(Constants.USERNAME,userName.getText().toString());

                return params;
            }
        };

        VolleyInitilizer.getmInstance(this).addToRequestQueue(sendResqurst);
    }
    public void checkValidation() {


        // bleow all if  conditions check Textboxs are empty or not
        if (userName.getText().toString().equalsIgnoreCase("") || userName.getText().toString() == null) {
            userName.setHintTextColor(Color.RED);
            userName.setHint("Please Enter Name");
        }
        else if(password.getText().toString().equalsIgnoreCase("") || password.getText().toString() == null){
            password.setHintTextColor(Color.RED);
            password.setHint("Please Enter password");

        }

      else{
            loginManager();
        }
/*
        userName.getText().toString().equalsIgnoreCase("") ||
                password.getText().toString().equalsIgnoreCase("") ||
                cofirmpass.getText().toString().equalsIgnoreCase("") ||
                email.getText().toString().equalsIgnoreCase("") ||
                mobile.getText().toString().equalsIgnoreCase("") ||
                employeeName.getText().toString().equalsIgnoreCase("") ||
                employeeMobile.getText().toString().equalsIgnoreCase(""))
    }*/
    }
}
