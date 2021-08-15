package xyz.cirno.wcgtest;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.android_ui_test_title);
        this.setContentView(R.layout.activity_main);
        Window w = getWindow();
        ((Switch)findViewById(R.id.switch1)).setOnCheckedChangeListener((view, checked)->{
            w.setColorMode(checked ? ActivityInfo.COLOR_MODE_WIDE_COLOR_GAMUT : ActivityInfo.COLOR_MODE_DEFAULT);
        });
        w.setColorMode(ActivityInfo.COLOR_MODE_WIDE_COLOR_GAMUT);
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }
}
