package netroxtech.com.employeetracker;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import netroxtech.com.employeetracker.Models.PlayCalls;
import netroxtech.com.employeetracker.adapters.PlayCallAdapters;
import netroxtech.com.employeetracker.utils.Constants;

public class PlayCallLogs extends AppCompatActivity {
    ListView listView;
    List<PlayCalls> playCallsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_call_logs);
        setXML();
           }
    public  void setXML(){
        listView = (ListView)findViewById(R.id.activity_play_call_listView);
        playCallsList = new ArrayList<>();
        getListOfFiles();
    }

    public void getListOfFiles() {
        File f = new File(Environment.getExternalStorageDirectory(), "/");
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            {
                PlayCalls calls = new PlayCalls();
                File file = files[i];
	     	      /*It's assumed that all file in the path are in supported type*/

                String filePath = file.getPath();
                if (filePath.endsWith(".3gp")) // Condition to check .txt file extension
                {
                    File getdate = new File(filePath);
                    Log.v(Constants.MYLOGS, "Last Access Date " + getdate.lastModified());

                    calls.setName(getdate.getName());
                    calls.setAdddres(getdate.getPath());
                    calls.setTime(String.valueOf(new Date(getdate.lastModified())));
                    playCallsList.add(calls);
                }
            }

        }
        ListAdapter adapter = new PlayCallAdapters(PlayCallLogs.this,playCallsList);
        listView.setAdapter(adapter);
    }
}
