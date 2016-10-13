package netroxtech.com.employeetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }
    public void callHistoryButton(View v){
        Intent intent = new Intent(Options.this,CallHistory.class);
        startActivity(intent);
    }
    public  void playCalls(View  v){
        Intent intent = new Intent(Options.this,PlayCallLogs.class);
        startActivity(intent);

    }
    public void getLocation(View  v){
        Intent intent = new Intent(Options.this, CurrentLocation.class);
        startActivity(intent);

    }
}
