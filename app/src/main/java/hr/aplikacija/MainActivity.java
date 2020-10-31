package hr.aplikacija;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView friednsListView = findViewById(R.id.friendListView);
        final ArrayList<String> myFriends = new ArrayList<String>(asList("Mark","Jane","Sussy","Jan"));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, myFriends);
        friednsListView.setAdapter(arrayAdapter);
        friednsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),
                        "Hello " + myFriends.get(i), Toast.LENGTH_LONG).show();
            }
        });
    }
}