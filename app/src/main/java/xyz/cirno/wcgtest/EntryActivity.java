package xyz.cirno.wcgtest;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

public class EntryActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        findViewById(R.id.launch_ui_test_button).setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
        findViewById(R.id.launch_gl_test_button).setOnClickListener(v -> startActivity(new Intent(this, GLActivity.class)));
        findViewById(R.id.launch_browser_test_button).setOnClickListener(v -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://dantmnf.github.io/wcgtest/"))));
        Display d = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        ((TextView)findViewById(R.id.textView6)).setText("Display.isWideColorGamut() => " + d.isWideColorGamut());
        ((TextView)findViewById(R.id.textView24)).setText("Configuration.isScreenWideColorGamut() => " + Resources.getSystem().getConfiguration().isScreenWideColorGamut());

    }
}