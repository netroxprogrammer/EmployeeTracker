package netroxtech.com.employeetracker.utils;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.provider.CallLog;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import netroxtech.com.employeetracker.Models.CallLogs;

/**
 * Created by mac on 9/25/2016.
 */
public class ApplicationFunctions {
    private Context context;
    public ApplicationFunctions(Context context){
        this.context = context;
    }
    // TODO: Call Log Fucntion (@Link ApplicationFunctions.getCallLogs )
    public ArrayList<CallLogs> getCallLogs(int calltype) {
        ArrayList<CallLogs> calls =  new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        Cursor managedCursor = this.context.getContentResolver().query (CallLog.Calls.CONTENT_URI, null, null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        sb.append("Call Log :");
        while (managedCursor.moveToNext()) {
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            String dir = null;

            int dircode = Integer.parseInt(callType);
            if(dircode== calltype){
                CallLogs logs = new CallLogs();
                logs.setPhoneNumber(phNumber);
                logs.setCallDuration(callDuration);
                logs.setCallDate(callDayTime.toString());
                if(calltype==1) {
                    logs.setCallType("in coming");
                }
                if(calltype==3) {
                    logs.setCallType("Miss Call");
                }
                if(calltype==2) {
                    logs.setCallType("out going");
                }
                calls.add(logs);
            }

        }
        return  calls;
    }

    /**
     * Get SDCARD Address
     * @return
     */
    public String getExternalAddress(){
        String address = Environment.getExternalStorageDirectory().toString();
        return address;
    }

    /**
     * Get Internal Storage Data
     * @return
     */

    public String getInternalAddress(){
        String address= Environment.getDataDirectory().toString();
        return address;
    }
    public void transferFiles(String extension) throws IOException{
        File file  = new File(getExternalAddress()+"/");
        File[]  files = file.listFiles();
        for(File  f : files){
            String filePath = f.getPath();
            if(filePath.endsWith(extension)){
                BufferedInputStream bufferedInputStream = null;
                File  sendFile   = new File(filePath);
                bufferedInputStream = new BufferedInputStream(new FileInputStream(sendFile),8192);
                
            }
        }
    }

}
