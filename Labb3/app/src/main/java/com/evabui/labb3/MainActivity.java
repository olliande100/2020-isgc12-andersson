package com.evabui.labb3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.logging.XMLFormatter;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText textBox = (EditText) findViewById(R.id.artistName);
                new ApiCall(getApplicationContext()).execute(textBox.getText().toString());
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if (getIntent().getStringExtra("result") != null) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(getIntent().getStringExtra("result").getBytes());
            List<String> liknandeArtister = ArtistParser.parse(inputStream);
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < liknandeArtister.size(); i++) {
                sb.append(liknandeArtister.get(i));
                sb.append("\n");
            }

            RecyclerView view = (RecyclerView)findViewById(R.id.recycler);
            view.setHasFixedSize(true);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            view.setLayoutManager(manager);
            view.setAdapter(new ArtistAdapter(liknandeArtister));
        }
    }
}