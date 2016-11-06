package netroxtech.com.employeetracker;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import netroxtech.com.employeetracker.utils.Constants;
import netroxtech.com.employeetracker.utils.InitilizeSharePref;

public class AdvanceSetting extends AppCompatActivity {

    private EditText hostAddress,portAddress,ftpUserName,ftpPassword;
    private InitilizeSharePref sharePref;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_setting);
        context = this;
        sharePref = new InitilizeSharePref(context);
    }
    public void setXML(){
        hostAddress = (EditText)findViewById(R.id.activity_advanceSetting_hostAddress);
        portAddress = (EditText)findViewById(R.id.activity_advanceSetting_portAddress);
        ftpUserName = (EditText)findViewById(R.id.activity_advanceSetting_ftpUserName);
        ftpPassword = (EditText)findViewById(R.id.activity_advanceSetting_ftpPassword);
    }
    public void saveFTPSeting(){
        String host,port,ftpuser,ftppass;
        host= hostAddress.getText().toString();
        port = portAddress.getText().toString();
        ftpuser = ftpUserName.getText().toString();
        ftppass = ftpPassword.getText().toString();
        // Check host is  empty or not.....
        if(host.equalsIgnoreCase("") || host==null){
            hostAddress.setHintTextColor(Color.RED);
          hostAddress.setHint("Required");
          }
        else{
            sharePref.saveRecordSettings(Constants.FTPHOSTADDRESS,host);

        }
        // check  port is empty or  not.....
        if(port.equalsIgnoreCase("") || port==null){
            portAddress.setHintTextColor(Color.RED);
            portAddress.setHint("Required");
        }
        else{
            sharePref.saveRecordSettings(Constants.FTPPORTADDRESS,port);

        }
        // check ftp userName empty or not......
        if(ftpuser.equalsIgnoreCase("") || ftpuser==null){
            ftpUserName.setHintTextColor(Color.RED);
            ftpUserName.setHint("Required");
        }
        else{
            sharePref.saveRecordSettings(Constants.FTPUSERNAME,ftpuser);

        }
        // check passowrd is  empty  or not.....
        if(ftppass.equalsIgnoreCase("") || ftppass==null){
            ftpPassword.setHintTextColor(Color.RED);
            ftpPassword.setHint("Required");
        }
        else{
            sharePref.saveRecordSettings(Constants.FTPPASSWORD,ftppass);

        }
    }
}
