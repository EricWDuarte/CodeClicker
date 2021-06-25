package com.example.codeclicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TelaJogo extends AppCompatActivity {
    ConstraintLayout click;
    Game game = Game.getInstance();
    Sound sound = Sound.getInstance();
    Typer typer = Typer.getInstance();
    LinearLayout layoutUpgrade;
    ImageButton imgAbre,imgFecha,imgUpClick,imgUpProg, imgSoundOn, imgSoundOff;
    TextView valorClick,pontos,upClick,upProg, txtTyper;
    boolean programador = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.telajogo);
        getSupportActionBar().hide();
        click = findViewById(R.id.layoutClickJogo);
        layoutUpgrade = findViewById(R.id.layoutUpgrades);
        layoutUpgrade.setVisibility(View.INVISIBLE);
        imgAbre = findViewById(R.id.imgUpgrades);
        imgFecha = findViewById(R.id.imgFecharUpgrades);
        imgUpClick = findViewById(R.id.imgClick);
        imgUpProg = findViewById(R.id.imgProg);
        imgSoundOn = findViewById(R.id.imgSoundOn);
        imgSoundOff = findViewById(R.id.imgSoundOff);
        valorClick = findViewById(R.id.txtValorClick);
        pontos = findViewById(R.id.txtPonto);
        upClick = findViewById(R.id.txtUpgrade);
        upProg = findViewById(R.id.txtUpgradeAuto);
        txtTyper = findViewById(R.id.txtTyper);

        Thread t = new Thread(){
            @Override
            public void run() {
                while(programador) {
                    try {
                        Thread.sleep(500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                game.codar();
                                typer.addAutomaticText();
                                atualizaTudo();
                                if (game.validaVitoria()) {
                                    programador = false;
                                    proxTela();
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();

        soundSetup();
        typerSetup();

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.contaPonto();
                sound.playTouchSound();
                typer.addText();

                if(game.validaVitoria()){
                    programador = false;
                    proxTela();
                }
                atualizaTudo();
            }
        });
        imgAbre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutUpgrade.setVisibility(View.VISIBLE);
                imgAbre.setVisibility(View.INVISIBLE);
            }
        });
        imgFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutUpgrade.setVisibility(View.INVISIBLE);
                imgAbre.setVisibility(View.VISIBLE);
            }
        });

        imgSoundOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSoundOff.setVisibility(View.VISIBLE);
                imgSoundOn.setVisibility(View.INVISIBLE);
                Sound.getInstance().onOffSound();
            }
        });
        imgSoundOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)    {
                imgSoundOff.setVisibility(View.INVISIBLE);
                imgSoundOn.setVisibility(View.VISIBLE);
                Sound.getInstance().onOffSound();
            }
        });

        imgUpClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.upgradeClick();
                atualizaTudo();
            }
        });
        imgUpProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.upProgramador();
                atualizaTudo();
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(TelaJogo.this,TelaTutorial.class));
    }
    public void proxTela(){
        startActivity(new Intent(TelaJogo.this,TelaVitoria.class));
    }
    public void atualizaTudo(){
        pontos.setText(game.getPonto());
        valorClick.setText("+"+game.getValorClick());
        upClick.setText("" + game.getValorUpgrade());
        upProg.setText(""+game.getValorProg());
        txtTyper.setText(typer.getCurrentText());
    }
    public void soundSetup() {
        sound.addContext(this);
        if(sound.isSoundOn()) {
            imgSoundOff.setVisibility(View.INVISIBLE);
            imgSoundOn.setVisibility(View.VISIBLE);
        }
        else {
            imgSoundOff.setVisibility(View.VISIBLE);
            imgSoundOn.setVisibility(View.INVISIBLE);
        }
    }

    public void typerSetup() {
        typer.getFiles(getResources().openRawResource(R.raw.txttyper));
        typer.getFiles(getResources().openRawResource(R.raw.txttyper2));
        typer.getFiles(getResources().openRawResource(R.raw.txttyper3));
        typer.getFiles(getResources().openRawResource(R.raw.txttyper4));
        typer.getFiles(getResources().openRawResource(R.raw.txttyper5));
        typer.getFiles(getResources().openRawResource(R.raw.txttyper6));
        typer.getFiles(getResources().openRawResource(R.raw.txttyper7));

        typer.getFirstText();
    }
}