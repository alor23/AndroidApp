package com.example.alor.mixer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import  java.io.OutputStreamWriter;
import  java.io.InputStreamReader;
public class MainActivity extends AppCompatActivity {

    public static final int sound1=R.raw.song1;
    public static final int sound2=R.raw.song2;
    public static final int sound3=R.raw.song3;
    public static final int sound4=R.raw.song4;
    public static final int sound5=R.raw.song5;
    public static final int sound6=R.raw.song6;

    static final int Read_Block_Size = 100;

    Button dzwiek1;
    Button dzwiek2;
    Button dzwiek3;
    Button dzwiek4;
    Button dzwiek5;
    Button dzwiek6;
    Button save;
    Button load;
    TextView wczytaj;
    public int sound;

    public List<String> lista = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP)
        {
        }
        else
        {
            Toast.makeText(this,"Minimalna wersja systemu to 5.0",Toast.LENGTH_SHORT).show();
        }

        wczytaj = findViewById(R.id.text1);
        save =  findViewById(R.id.Save);
        load =  findViewById(R.id.Load);
        dzwiek1 = findViewById(R.id.Sound1);
        dzwiek2 = findViewById(R.id.Sound2);
        dzwiek3 = findViewById(R.id.Sound3);
        dzwiek4 = findViewById(R.id.Sound4);
        dzwiek5 = findViewById(R.id.Sound5);
        dzwiek6 = findViewById(R.id.Sound6);
    }

    public void playSound(Context context,int soundID)
    {
        MediaPlayer mp = MediaPlayer.create(context,soundID);
        mp.start();
    }
    public void GrajSound(View v)
    {
        sound =0;
        if(dzwiek1.getId() == v.getId())
        {
            lista.add("A");
            sound=sound1;
            dzwiek1.setBackgroundColor(0xff00a000);
            dzwiek2.setBackgroundColor(0xffff0000);
            dzwiek3.setBackgroundColor(0xffff0000);
            dzwiek4.setBackgroundColor(0xffff0000);
            dzwiek5.setBackgroundColor(0xffff0000);
            dzwiek6.setBackgroundColor(0xffff0000);

        }
        else if(dzwiek2.getId() == v.getId())
        {
            lista.add("B");
            sound=sound2;
            dzwiek2.setBackgroundColor(0xff00a000);
            dzwiek1.setBackgroundColor(0xffff0000);
            dzwiek3.setBackgroundColor(0xffff0000);
            dzwiek4.setBackgroundColor(0xffff0000);
            dzwiek5.setBackgroundColor(0xffff0000);
            dzwiek6.setBackgroundColor(0xffff0000);

        }
        else if(dzwiek3.getId() == v.getId())
        {
            lista.add("C");
            sound=sound3;
            dzwiek3.setBackgroundColor(0xff00a000);
            dzwiek2.setBackgroundColor(0xffff0000);
            dzwiek1.setBackgroundColor(0xffff0000);
            dzwiek4.setBackgroundColor(0xffff0000);
            dzwiek5.setBackgroundColor(0xffff0000);
            dzwiek6.setBackgroundColor(0xffff0000);

        }
        else if(dzwiek4.getId() == v.getId())
        {
            lista.add("D");
            sound=sound4;
            dzwiek4.setBackgroundColor(0xff00a000);
            dzwiek2.setBackgroundColor(0xffff0000);
            dzwiek3.setBackgroundColor(0xffff0000);
            dzwiek1.setBackgroundColor(0xffff0000);
            dzwiek5.setBackgroundColor(0xffff0000);
            dzwiek6.setBackgroundColor(0xffff0000);

        }
        else if(dzwiek5.getId() == v.getId())
        {
            lista.add("E");
            sound=sound5;
            dzwiek5.setBackgroundColor(0xff00a000);
            dzwiek2.setBackgroundColor(0xffff0000);
            dzwiek3.setBackgroundColor(0xffff0000);
            dzwiek4.setBackgroundColor(0xffff0000);
            dzwiek1.setBackgroundColor(0xffff0000);
            dzwiek6.setBackgroundColor(0xffff0000);

        }
        else if(dzwiek6.getId() == v.getId())
        {
            lista.add("F");
            sound=sound6;
            dzwiek6.setBackgroundColor(0xff00a000);
            dzwiek2.setBackgroundColor(0xffff0000);
            dzwiek3.setBackgroundColor(0xffff0000);
            dzwiek4.setBackgroundColor(0xffff0000);
            dzwiek5.setBackgroundColor(0xffff0000);
            dzwiek1.setBackgroundColor(0xffff0000);

        }

       playSound(this,sound);
    }
    public void Write(View v)
    {
        try
        {
            FileOutputStream fileout = openFileOutput("datafile.txt",MODE_PRIVATE);
            OutputStreamWriter outwrite = new OutputStreamWriter(fileout);
            for(int i=0;i<lista.size();i++) {
                String x = (String) lista.get(i);
                outwrite.write(x);
                x="";
            }
            outwrite.close();
            lista.clear();
            Toast.makeText(getBaseContext(), "Zapisano",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void Read(View v)
    {
        try
        {
            FileInputStream filein = openFileInput("datafile.txt");
            InputStreamReader InputRead = new InputStreamReader(filein);

            char[] InputBuffer = new char[Read_Block_Size];
            String s="";
            int charRead;
            while ((charRead=InputRead.read(InputBuffer))>0)
            {
                String readstring = String.copyValueOf(InputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            for (int i=0;i<s.length();i++)
            {
                sound=0;
                char a=s.charAt(i);
                if(a=='A')
                {
                    sound=sound1;
                }
                else if(a=='B')
                {
                    sound=sound2;
                }
                else if(a=='C')
                {
                    sound=sound3;
                }
                else if(a=='D')
                {
                    sound=sound4;
                }
                else if(a=='E')
                {
                    sound=sound5;
                }
                else if(a=='F')
                {
                    sound=sound6;
                }

                playSound(this,sound);
            }

            wczytaj.setText(s);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
