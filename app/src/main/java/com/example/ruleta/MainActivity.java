package com.example.ruleta;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.DecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button button;
    TextView resultText;
    int[] numerosRuleta = {0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27, 13, 36, 11, 30, 8, 23, 10, 5, 24, 16, 33, 1, 20, 14, 31, 9, 22, 18, 29, 7, 28, 12, 35, 3, 26};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.ruleta);
        button = findViewById(R.id.spin_button);
        resultText = findViewById(R.id.result_text);

        Button toggleTableButton = findViewById(R.id.toggle_table_button);
        FrameLayout bettingTableContainer = findViewById(R.id.betting_table_container);

        toggleTableButton.setOnClickListener(v -> {
            if (bettingTableContainer.getVisibility() == View.GONE) {
                bettingTableContainer.setVisibility(View.VISIBLE);
                bettingTableContainer.animate().alpha(1.0f).setDuration(300);
                toggleTableButton.setText("Ocultar Apuestas");
            } else {
                bettingTableContainer.animate().alpha(0.0f).setDuration(300).withEndAction(() ->
                        bettingTableContainer.setVisibility(View.GONE)
                );
                toggleTableButton.setText("Mostrar Apuestas");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                girarRuleta(imageView);
            }
        });
    }

    private void girarRuleta(View view){
        Random aleatorio = new Random();
        int vueltasCompletas = 3 + aleatorio.nextInt(8);
        int anguloExtra = aleatorio.nextInt(360);

        float nVueltas = (vueltasCompletas * 360) + anguloExtra;

        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "rotation", imageView.getRotation(), imageView.getRotation() + nVueltas);
        animator.setDuration(2000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();

        // Determinar el número ganador
        final int anguloFinal = (int) ((imageView.getRotation() + nVueltas) % 360);
        int indiceGanador = (numerosRuleta.length - (anguloFinal / (360 / numerosRuleta.length))) % numerosRuleta.length;
        final int numeroGanador = numerosRuleta[indiceGanador];

        // Mostrar el resultado tras la animación
        animator.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                resultText.setText("Número ganador: " + numeroGanador);
                Log.v("Rule", String.valueOf(numeroGanador));
            }
        });


    }
}
