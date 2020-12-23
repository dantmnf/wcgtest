package xyz.cirno.wcgtest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class EntryActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        findViewById(R.id.launch_ui_test_button).setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
        findViewById(R.id.launch_gl_test_button).setOnClickListener(v -> startActivity(new Intent(this, GLActivity.class)));
        findViewById(R.id.launch_browser_test_button).setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://dantmnf.github.io/wcgtest/"))));
    }
}