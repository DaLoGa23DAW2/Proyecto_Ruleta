package com.example.ruleta;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
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
        // Crear un ObjectAnimator para rotar la ruleta
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 3600f);

        // Duración de la animación (en milisegundos)
        animator.setDuration(2000); // 2 segundos para girar

        // Interpolador para desacelerar gradualmente
        TimeInterpolator interpolator = new DecelerateInterpolator();
        animator.setInterpolator(interpolator);

        // Iniciar la animación
        animator.start();
    }




}