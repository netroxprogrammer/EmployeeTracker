package netroxtech.com.employeetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

     Button registerasManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerasManger = (Button)findViewById(R.id.regiserasManager_main_activity);

    }
    public void registerASManager(View v){
        Intent intent = new Intent(MainActivity.this,RegisterAsManager.class);
        startActivity(intent);
    }

}
