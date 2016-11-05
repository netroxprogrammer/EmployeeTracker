package netroxtech.com.employeetracker;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import netroxtech.com.employeetracker.Models.PlayCalls;
import netroxtech.com.employeetracker.adapters.PlayCallAdapters;
import netroxtech.com.employeetracker.utils.Constants;

public class PlayCallLogs extends AppCompatActivity implements  MediaPlayer.OnCompletionListener {
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView data = (TextView) view.findViewById(R.id.play_call_logs_path_text);
                String path = data.getText().toString();
                try {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(path);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
