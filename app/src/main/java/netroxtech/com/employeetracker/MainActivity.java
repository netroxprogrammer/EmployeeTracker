package netroxtech.com.employeetracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {
EditText name,  password,employeeName, employeeMobile;
     Button registerasManger;
    JSONObject reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerasManger = (Button)findViewById(R.id.regiserasManager_main_activity);
        boolean isLogin = new InitilizeSharePref(MainActivity.this).isUserLogin();
        if(isLogin){
            Intent  intent = new Intent(MainActivity.this,Options.class);
            startActivity(intent);
            finish();
        }
        else{
            setXml();
        }
 }
    public void setXml(){
        name =  (EditText)findViewById(R.id.name_activity_main);
        password =  (EditText)findViewById(R.id.password_activity_main);
        employeeName =  (EditText)findViewById(R.id.employeename_activity_main);
        employeeMobile =  (EditText)findViewById(R.id.employeemobile_activity_main);
    }
    public void registerASManager(View v){
        Intent intent = new Intent(MainActivity.this,RegisterAsManager.class);
        startActivity(intent);
    }

    public void loginEmployeeUser(View v){
        checkValidation();
    }
    public void loginEmployee(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("please  Wait");
        pDialog.show();

        StringRequest sendResqurst = new StringRequest(Request.Method.POST, Constants.LOGIN_EMPLOYEE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Users users = new Users();
                    reader = new JSONObject(response);
                    JSONObject  data = reader.getJSONObject("employee");
                    String  status = data.getString("success");
                    String  role = data.getString("role");
                    users.setRole(role);
                    users.setUserName(name.getText().toString());


                    new InitilizeSharePref(MainActivity.this).saveUserLogin(users);
                    if(status.equalsIgnoreCase("1") && role.equalsIgnoreCase("employee")){
                        Intent intent = new Intent(MainActivity.this,Options.class);
                        startActivity(intent);
                        //  String data = response;
                        Log.v("message", response);
                        Toast.makeText(getApplicationContext(), "Empoyee Login", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Sorry Employee No Register",Toast.LENGTH_SHORT).show();
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
                params.put(Constants.NAME,name.getText().toString());

                params.put(Constants.PASSWORD,password.getText().toString());
                params.put(Constants.EMPLOYEENAME,employeeName.getText().toString());
                params.put(Constants.EMPLOYEEMobile,employeeMobile.getText().toString());
                return params;
            }
        };

        VolleyInitilizer.getmInstance(this).addToRequestQueue(sendResqurst);
    }
    // this function   validation checking for textbox
    public void checkValidation() {


        // bleow all if  conditions check Textboxs are empty or not
        if (name.getText().toString().equalsIgnoreCase("") || name.getText().toString() == null) {
            name.setHintTextColor(Color.RED);
            name.setHint("Please Enter Name");
        }
        else if(password.getText().toString().equalsIgnoreCase("") || password.getText().toString() == null){
            password.setHintTextColor(Color.RED);
            password.setHint("Please Enter password");

        }

        else if(employeeName.getText().toString().equalsIgnoreCase("") || employeeName.getText().toString() == null){
            employeeName.setHintTextColor(Color.RED);
            employeeName.setHint("Please Enter employee Name");

        }
        else if(employeeMobile.getText().toString().equalsIgnoreCase("") || employeeMobile.getText().toString() == null){
            employeeMobile.setHintTextColor(Color.RED);
            employeeMobile.setHint("Please Enter employee Mobile");

        }else{
            loginEmployee();
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
