package xyz.cirno.wcgtest;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.android_ui_test_title);
        this.setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }
}
