package netroxtech.com.employeetracker.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mac on 9/18/2016.
 */
public class InitilizeSharePref {
    SharedPreferences mPref;
    Context mcontext;
    public InitilizeSharePref(Context context){
        mcontext = context;
        mPref = mcontext.getSharedPreferences(Constants.MYPREFNAME,Context.MODE_PRIVATE);
  }

    public void saveUserData(String role){
        SharedPreferences.Editor editor  = mPref.edit();
        editor.putString(Constants.ROLEPREF,role);
        editor.commit();
    }
    public String getUserRole(){
        String role =  mPref.getString(Constants.ROLEPREF,null);
        return  role;
    }
}
