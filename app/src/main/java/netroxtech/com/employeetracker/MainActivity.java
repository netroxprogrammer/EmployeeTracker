package netroxtech.com.employeetracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import netroxtech.com.employeetracker.applications.VolleyInitilizer;
import netroxtech.com.employeetracker.utils.Constants;

public class MainActivity extends AppCompatActivity {
EditText name,  password,employeeName, employeeMobile;
     Button registerasManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerasManger = (Button)findViewById(R.id.regiserasManager_main_activity);

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


    public void RegisterEmployee(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("please  Wait");
        pDialog.show();

        StringRequest sendResqurst = new StringRequest(Request.Method.POST, Constants.ADD_EMPLOYEE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    reader = new JSONObject(response);
                    JSONObject  data = reader.getJSONObject("employee");
                    String  status = data.getString("success");
                    if(status.equalsIgnoreCase("1")){

                        //  String data = response;
                        Log.v("message", response);
                        Toast.makeText(getApplicationContext(), "Employee Register", Toast.LENGTH_SHORT).show();
                        clearData();
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
                params.put(Constants.USERNAME,userName.getText().toString());
                params.put(Constants.PASSWORD,password.getText().toString());
                params.put(Constants.EMAIL,email.getText().toString());
                params.put(Constants.MOBILE,mobile.getText().toString());
                params.put(Constants.EMPLOYEENAME,employeeName.getText().toString());
                params.put(Constants.EMPLOYEEMobile,employeeMobile.getText().toString());
                params.put(Constants.ROLE,role);
                return params;
            }
        };

        VolleyInitilizer.getmInstance(this).addToRequestQueue(sendResqurst);
    }


}
