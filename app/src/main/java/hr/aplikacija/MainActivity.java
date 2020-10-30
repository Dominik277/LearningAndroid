package hr.aplikacija;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void fade(View view){
        ImageView bartimageView = (ImageView) findViewById(R.id.bartImageView);
        bartimageView.animate().scaleX(0.5f).scaleY(0.5f).setDuration(1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView bartimageView = (ImageView) findViewById(R.id.bartImageView);
        bartimageView.animate().translationXBy(-1000);
        bartimageView.animate().translationXBy(1000).rotation(3600).setDuration(2000);
    }
}