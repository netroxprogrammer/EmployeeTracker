package netroxtech.com.employeetracker.recivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import java.io.File;

/**
 * Created by mac on 9/26/2016.
 */
public class CallRecorderReciver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(new PhoneListener(), PhoneStateListener.LISTEN_CALL_STATE);
    }

    private  final class PhoneListener extends PhoneStateListener {
        private MediaRecorder mediaRecorder;
        private File file;
        //private Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        public  void onCallStateChanged(int  state, String incommingNumber){

            try
            {

                switch(state)
                {

                    case TelephonyManager.CALL_STATE_RINGING:
                        //Toast.makeText(getApplicationContext(), "call", Toast.LENGTH_LONG).show();
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:

                        File file=new File(Environment.getExternalStorageDirectory(),incommingNumber+System.currentTimeMillis()+".3gp");
                        mediaRecorder=new MediaRecorder();
                        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        mediaRecorder.setOutputFile(file.getAbsolutePath());
                        mediaRecorder.prepare();
                        mediaRecorder.start();

                        // Toast.makeText(getApplicationContext(), "the  phone  is  Switch on recording", Toast.LENGTH_SHORT).show();
                        //   vibrator.vibrate(200);
                        break;

                    case TelephonyManager.CALL_STATE_IDLE:
                        if( mediaRecorder!=null)
                        {
                            mediaRecorder.stop();
                            mediaRecorder.release();
                            mediaRecorder=null;


                            ///	 Toast.makeText(getApplicationContext(), "phone  hang  up", Toast.LENGTH_LONG).show();

                            //vibrator.vibrate(200);
                        }
                        break;
                }


            }
            catch(Exception e)
            {

                e.printStackTrace();


            }

        }

    }
}
