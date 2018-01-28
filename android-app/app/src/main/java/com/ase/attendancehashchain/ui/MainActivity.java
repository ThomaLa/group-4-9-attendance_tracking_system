package com.ase.attendancehashchain.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ase.attendancehashchain.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    Calendar calendar = new GregorianCalendar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button generateCodeButton = findViewById(R.id.generateCode);
        TextView emailContentTextView = findViewById(R.id.email_content);
        TextView weekContentTextView = findViewById(R.id.week_content);
        final EditText tutorialGroupEditText = findViewById(R.id.group);

        final String username = getIntent().getStringExtra("username");
        final String weekOfYear = String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));

        emailContentTextView.setText(username);
        weekContentTextView.setText(weekOfYear);

        generateCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tutorialGroupEditText.getText().toString().equals("")) {
                    Intent qrCodeActivity = new Intent(MainActivity.this, QRCodeActivity.class);
                    qrCodeActivity.putExtra("username", username);
                    qrCodeActivity.putExtra("weekOfYear", weekOfYear);
                    qrCodeActivity.putExtra("tutorialGroup", tutorialGroupEditText.getText().toString());
                    MainActivity.this.startActivity(qrCodeActivity);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
