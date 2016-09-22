package netroxtech.com.employeetracker;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import netroxtech.com.employeetracker.applications.VolleyInitilizer;
import netroxtech.com.employeetracker.utils.Constants;

public class ManagerRegister extends AppCompatActivity {
    EditText name, userName, password, cformPassword, mobile, email;
    JSONObject reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_register);
        setXML();
    }

    public void setXML() {
        name = (EditText) findViewById(R.id.name_activity_registermanager);
        userName = (EditText) findViewById(R.id.userName_activity_registermanager);
        cformPassword = (EditText) findViewById(R.id.comfrm_pass_activity_registermanager);
        password = (EditText) findViewById(R.id.password_activity_registermanager);
        mobile = (EditText) findViewById(R.id.mobile_activity_registermanager);
        email = (EditText) findViewById(R.id.email_activity_registermanager);
    }

    public void addManager(View v) {
        checkValidation();
    }

    public void registerManager() {
        //  these three  line use for  dialog  box
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("please  Wait");
        pDialog.show();

        // this    funciton take  three argumnents for send  request to  MYSQL and  it send post Request.
        //  see second argument
        StringRequest sendResqurst = new StringRequest(Request.Method.POST, Constants.ADD_EMPLOYEE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    reader = new JSONObject(response);
                    // pasre json object to readable text
                    JSONObject data = reader.getJSONObject("employee");
                    String status = data.getString("success");
                    if (status.equalsIgnoreCase("1")) {

                        //  String data = response;
                        Log.v("message", response);
                        Toast.makeText(getApplicationContext(), "Manager Register", Toast.LENGTH_SHORT).show();
                        clearData();
                    } else {
                        Toast.makeText(getApplicationContext(), "Sorry Manager No Register", Toast.LENGTH_SHORT).show();
                    }
                    pDialog.hide();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error ", error.toString());
                pDialog.hide();
            }
        }) {
            // this is build in function for google volley that take  parameters for sending to MYSQL
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Constants.NAME, name.getText().toString());
                params.put(Constants.USERNAME, userName.getText().toString());
                params.put(Constants.PASSWORD, password.getText().toString());
                params.put(Constants.EMAIL, email.getText().toString());
                params.put(Constants.MOBILE, mobile.getText().toString());
                params.put(Constants.EMPLOYEENAME, "");
                params.put(Constants.EMPLOYEEMobile, "");
                params.put(Constants.ROLE, "manager");
                return params;
            }
        };

        //  this is class that generate request.
        VolleyInitilizer.getmInstance(this).addToRequestQueue(sendResqurst);
    }

    public void clearData() {
        name.setText("");
        userName.setText("");
        password.setText("");
        cformPassword.setText("");
        email.setText("");
        mobile.setText("");

    }

    // this function   validation checking for textbox
    public void checkValidation() {


        //  this is TextWatcher  Class that  check password and comfirm passward are same or not
        cformPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!password.getText().toString().equalsIgnoreCase(cformPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Password not  match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // bleow all if  conditions check Textboxs are empty or not
        if (name.getText().toString().equalsIgnoreCase("") || name.getText().toString() == null) {
            name.setHintTextColor(Color.RED);
            name.setHint("Please Enter Name");
        } else if (userName.getText().toString().equalsIgnoreCase("") || userName.getText().toString() == null) {
            userName.setHintTextColor(Color.RED);
            userName.setHint("Please Enter UserName");

        } else if (password.getText().toString().equalsIgnoreCase("") || password.getText().toString() == null) {
            password.setHintTextColor(Color.RED);
            password.setHint("Please Enter password");

        } else if (cformPassword.getText().toString().equalsIgnoreCase("") || cformPassword.getText().toString() == null) {
            cformPassword.setHintTextColor(Color.RED);
            cformPassword.setHint("Please Enter Comform password");

        } else if (email.getText().toString().equalsIgnoreCase("") || email.getText().toString() == null) {
            email.setHintTextColor(Color.RED);
            email.setHint("Please Enter email");

        } else if (mobile.getText().toString().equalsIgnoreCase("") || mobile.getText().toString() == null) {
            mobile.setHintTextColor(Color.RED);
            mobile.setHint("Please Enter mobile");

        } else {
            registerManager();
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
