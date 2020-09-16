package ddwucom.mobile.finalreport;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IntroductionActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce_activity);

        btn = findViewById(R.id.btn_intro);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_intro:
                finish();
                break;
        }
    }

}
