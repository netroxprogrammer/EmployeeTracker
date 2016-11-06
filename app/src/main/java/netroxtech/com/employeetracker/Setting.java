package netroxtech.com.employeetracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.net.Inet4Address;

import netroxtech.com.employeetracker.utils.Constants;
import netroxtech.com.employeetracker.utils.InitilizeSharePref;

public class Setting extends AppCompatActivity {

    private InitilizeSharePref sharePref;
    private Context context;
    private CheckBox recordCalls,smsLogs,callLogs;
    private Switch shareViaMail;
    private EditText shareEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        context = this;
        new InitilizeSharePref(context);
        setXML();
        saveCallRecords();
    }

    /**
     * Set Up Function for @Widgets , @Object and @Variable
     */
    public void setXML(){
        recordCalls = (CheckBox)findViewById(R.id.activity_setting_callRecordong);
        smsLogs = (CheckBox)findViewById(R.id.activity_setting_smsLog);
        callLogs = (CheckBox)findViewById(R.id.activity_setting_callLogs);
        shareViaMail = (Switch)findViewById(R.id.activity_setting_autoSwitch);
        shareEmail = (EditText)findViewById(R.id.activity_setting_sharetext);
    }

    /**
     * This Function Save All Recoding
     */
    public void saveCallRecords(){
               // Call Recording Check Listener CheckBox
        recordCalls.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    sharePref.saveRecordSettings(Constants.CALLRECORDPRE, Constants.CALLRECORDPRE);
                    Toast.makeText(getApplicationContext(),"Call Recoding On",Toast.LENGTH_SHORT).show();
                }
                else{
                    sharePref.removeData(Constants.CALLRECORDPRE);
                }
            }
        });
       //   Call Logs Check Listener CheckBox
        callLogs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    sharePref.saveRecordSettings(Constants.CALLLOGSPRE,Constants.CALLLOGSPRE);
                    Toast.makeText(getApplicationContext(),"Save Call Logs On",Toast.LENGTH_SHORT).show();
                }
                else{
                    sharePref.removeData(Constants.CALLLOGSPRE);
                }
            }
        });

        //  SMSLOGS check  Listiner CheckBox
        smsLogs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    sharePref.saveRecordSettings(Constants.SMSLOGSPRE, Constants.SMSLOGSPRE);
                    Toast.makeText(getApplicationContext(),"SMS Logs On",Toast.LENGTH_SHORT).show();
                }else{
                    sharePref.removeData(Constants.SMSLOGSPRE);
                }
            }
        });

        // Switch Button ON/OFF  when
        shareViaMail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    sharePref.saveRecordSettings(Constants.SHAREEMAILPREF,Constants.SHAREEMAILPREF);
                    Toast.makeText(getApplicationContext(),"Auto Send On",Toast.LENGTH_SHORT).show();
                }else{
                    sharePref.removeData(Constants.SHAREEMAILPREF);
                }
            }
        });
    }

    /**
     * This Function Save Email Setting
     * @param v
     */
    public void SaveSettingButtton(View v){
        String email = shareEmail.getText().toString();
        if(!email.equalsIgnoreCase("") || email !=null){
            sharePref.saveRecordSettings(Constants.EMAILSHAREPREF,Constants.EMAILSHAREPREF);
        }
        else{
            sharePref.saveRecordSettings(Constants.EMAILSHAREPREF,"null");
        }
    }

    /**
     * This Function  Go to AdvanceSetting
     * @param v
     */
    public void goToAdvanceSetting(View v){
        Intent intent = new Intent(context,AdvanceSetting.class);
        startActivity(intent);
    }
}
