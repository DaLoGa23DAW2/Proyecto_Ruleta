package com.example.ruleta;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.animation.ObjectAnimator;
import android.view.animation.DecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.ruleta);
        button = (Button) findViewById(R.id.spin_button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnPulsadoRotar(imageView);
            }
        });
    }




    private void btnPulsadoRotar(View view){
        java.util.Random aleatorio = new java.util.Random();
        int vueltasCompletas = 3 + aleatorio.nextInt(8);
        int anguloExtra = aleatorio.nextInt(360);

        float nVueltas = (vueltasCompletas * 360) + anguloExtra;

        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "rotation", imageView.getRotation(), imageView.getRotation() + nVueltas);
        animator.setDuration(2000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }




}