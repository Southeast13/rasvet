package com.example.daynightapp;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isDay = true;
    private TextView treeView;
    private float treePosition = 0f;
    private boolean toRight = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View root = findViewById(R.id.root_layout);
        treeView = findViewById(R.id.tree_view);
        Button btnToggle = findViewById(R.id.btn_toggle);
        Button btnMove = findViewById(R.id.btn_move);

        updateUi(root, btnToggle);

        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDay = !isDay;
                updateUi(root, btnToggle);
            }
        });

        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float delta = getResources().getDisplayMetrics().density * 150f;
                if (toRight) {
                    treePosition += delta;
                } else {
                    treePosition -= delta;
                }
                toRight = !toRight;
                ObjectAnimator anim = ObjectAnimator.ofFloat(treeView, "translationX", treePosition);
                anim.setDuration(600);
                anim.start();
            }
        });
    }

    private void updateUi(View root, Button btnToggle) {
        if (isDay) {
            root.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
            btnToggle.setText("Switch to Night");
        } else {
            root.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
            btnToggle.setText("Switch to Day");
        }
    }
}
