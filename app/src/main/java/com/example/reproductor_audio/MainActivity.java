package com.example.reproductor_audio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button play_pause, btn_repetir;
    MediaPlayer mp;
    ImageView iv;
    int repetir = 2, posicion = 0;
    MediaPlayer vectormp[] = new MediaPlayer[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play_pause =(Button) findViewById(R.id.btnreproducir);
        btn_repetir= (Button)findViewById(R.id.btnrepetir);
        iv =  (ImageView)findViewById(R.id.imageView);
        //Lista de canciones a reproducir dentro de la carpeta RAW

         vectormp[0] = MediaPlayer.create(this, R.raw.race);
         vectormp[1] = MediaPlayer.create( this, R.raw.sound);
         vectormp[1] = MediaPlayer.create(  this, R.raw.tea);
    }

    //metodo de reproduccion para el boton de playpause
     public void PlayPause(View View){
        if (vectormp[posicion].isPlaying()){
            vectormp[posicion].pause();
            play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this,"Play", Toast.LENGTH_SHORT).show();

         }
        else{
            vectormp[posicion].start();
            play_pause.setBackgroundResource(R.drawable.reproducir);
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT);

        }
     }
     //metoso para el boton stop
    public void Stop(View View){
        if (vectormp[posicion]!=null){
            vectormp[posicion].stop();

            vectormp[0] = MediaPlayer.create(this, R.raw.race);
            vectormp[1] = MediaPlayer.create( this, R.raw.sound);
            vectormp[1] = MediaPlayer.create(  this, R.raw.tea);
            posicion=0;
            play_pause.setBackgroundResource(R.drawable.reproducir);
            iv.setImageResource(R.drawable.portada1);
            Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
        }
    }
    //Metodo repetir una pista
    public void Repetir(View View){
        if (repetir==1){
            btn_repetir.setBackgroundResource(R.drawable.no_repetir);
            Toast.makeText(this, "No repetir", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(false);
        }else{
            btn_repetir.setBackgroundResource(R.drawable.repetir);
            Toast.makeText(this, "Repetir", Toast.LENGTH_SHORT).show();
            vectormp[posicion].setLooping(true);
            repetir=1;
        }
    }
    //Metod para saltar a la siguiente cancion
    public void Siguiente(View View){
        if (posicion<vectormp.length -1){
            if (vectormp[posicion].isPlaying()){
                vectormp[posicion].stop();
                posicion++;
                vectormp[posicion].start();
                if (posicion==0){
                    iv.setImageResource(R.drawable.portada1);
                }else if (posicion==1){
                    iv.setImageResource(R.drawable.portada2);
                }else if (posicion==2){
                    iv.setImageResource(R.drawable.portada3);
                }
            }
        }else{
            posicion++;
            if (posicion==0){
                iv.setImageResource(R.drawable.portada1);
            } else if (posicion==1){
                iv.setImageResource(R.drawable.portada2);
            } else if (posicion==2){
                iv.setImageResource(R.drawable.portada3);
            }else {
                Toast.makeText(this, "No hay mas canciones",Toast.LENGTH_SHORT).show();
            }
        }

    }
    //Metodo para regresar a la cacion siguiente
    public void Anterior(View View){
    if(posicion>=1){
        if (vectormp[posicion].isPlaying()){
            vectormp[posicion].stop();
            vectormp[0] = MediaPlayer.create(this, R.raw.race);
            vectormp[1] = MediaPlayer.create( this, R.raw.sound);
            vectormp[1] = MediaPlayer.create(  this, R.raw.tea);
            posicion--;

            if (posicion==0){
                iv.setImageResource(R.drawable.portada1);
            }else if (posicion==1){
                iv.setImageResource(R.drawable.portada2);
            }else if (posicion==2){
                iv.setImageResource(R.drawable.portada3);
            }else {
                Toast.makeText(this, "No hay mas canciones",Toast.LENGTH_SHORT).show();
            }
        }
    }
    }

}