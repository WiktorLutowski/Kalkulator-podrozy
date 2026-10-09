package com.example.kalkulatorpodrozy;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText dystansEdit;
    private EditText spalanieEdit;
    private RadioButton benzynaRadio;
    private RadioButton dieselRadio;
    private RadioButton lpgRadio;
    private EditText pasazerowieEdit;
    private Button obliczButton;
    private TextView wynikText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dystansEdit = findViewById(R.id.dystansEdit);
        spalanieEdit = findViewById(R.id.spalanieEdit);

        benzynaRadio = findViewById(R.id.benzynaRadio);
        dieselRadio = findViewById(R.id.dieselRadio);
        lpgRadio = findViewById(R.id.lpgRadio);

        pasazerowieEdit = findViewById(R.id.pasazerowieEdit);
        obliczButton = findViewById(R.id.obliczButton);
        wynikText = findViewById(R.id.wynikText);

        pasazerowieEdit.setText("1");

        obliczButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    float dystans = Integer.parseInt(dystansEdit.getText().toString());
                    if(dystans <= 0)
                        throw new IllegalArgumentException();

                    int spalanie = Integer.parseInt(spalanieEdit.getText().toString());
                    if(spalanie <= 0)
                        throw new IllegalArgumentException();

                    int pasazerowie = Integer.parseInt(pasazerowieEdit.getText().toString());
                    if(pasazerowie <= 0)
                        throw new IllegalArgumentException();

                    float przelicznik = 0;
                    if(benzynaRadio.isChecked())
                        przelicznik = 6.50f;
                    else if(dieselRadio.isChecked())
                        przelicznik = 6.70f;
                    else if(lpgRadio.isChecked())
                        przelicznik = 3.10f;
                    else
                        throw new IllegalArgumentException();

                    float ileSpali = Math.round(spalanie * (dystans / 100) * przelicznik * 100.0) / 100.0f;

                    float naOsobe = Math.round(ileSpali / pasazerowie * 100.0) / 100.0f;

                    wynikText.setTextColor(0xFF00BB00);
                    wynikText.setText("Całkowity koszt: " + ileSpali + " zł \n Na osobę: " + naOsobe + " zł");

                }
                catch (Exception e)
                {
                    wynikText.setTextColor(0xFFBB0000);
                    wynikText.setText("Niepoprawne dane wejściowe!");
                }
            }
        });
    }
}