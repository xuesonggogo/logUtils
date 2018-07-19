package app.log.com.logapp;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.log_subject.platform.Manager.LogUtils;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_write = findViewById(R.id.log_write);
        btn_write.setOnClickListener(this);
        LogUtils.verifyStoragePermissions(this);
        LogUtils.getInstance().setLogSaveRank(false).setLogDebug(false);
    }

    @Override
    public void onClick(View v) {
        LogUtils.getInstance().i("hhhhhhhhhhhhhhhhhhhhh==" + "lixuesong 222");
    }
}

