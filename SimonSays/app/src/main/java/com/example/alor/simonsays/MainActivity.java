package com.example.alor.simonsays;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import  android.widget.Button;
import java.util.Random;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {
    public Button startButton;
    public Button redButton;
    public Button blueButton;
    public Button greenButton;
    public Button yellowButton;
    public Button orangebutton;
    public Button purplebutton;
    Random losowa = new Random();
    public int[] zapamietane = new int[100];
    int zgadnij = 2;
    int licznik = 0;
    int levelset=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.startbutton);
        redButton=(Button) findViewById(R.id.redbutton);
        blueButton=(Button) findViewById(R.id.bluebutton);
        greenButton=(Button) findViewById(R.id.greenbutton);
        yellowButton=(Button) findViewById(R.id.yellowbutton);
        orangebutton= (Button) findViewById(R.id.orangebutton);
        purplebutton= (Button) findViewById(R.id.purplebutton);
        orangebutton.setVisibility(View.GONE);
        purplebutton.setVisibility(View.GONE);
        setColorButtonEnabled(false);
    }
    public void starGame(View v)
    {
        licznik=0;
        Toast.makeText(this,"Start za:",Toast.LENGTH_SHORT).show();

        for(int i=3;i>=1;--i)
        {
            Toast.makeText(this,String.valueOf(i),Toast.LENGTH_SHORT).show();
        }
        for(int i=0; i<zgadnij; ++i)
        {
            if(orangebutton.getVisibility()==View.VISIBLE && purplebutton.getVisibility()==View.VISIBLE)
            {
             levelset=6;
            }
            else if(orangebutton.getVisibility()==View.VISIBLE)
            {
                levelset=5;
            }
            else
            {
                levelset = 4;
            }
                int pomocnicza=losowa.nextInt(levelset)+1;
            Toast toast = Toast.makeText(this,"NUMER "+String.valueOf(i+1)+setTextVal(pomocnicza),Toast.LENGTH_SHORT);
            View view = toast.getView();
            view.setBackgroundResource(android.R.drawable.toast_frame);
            TextView toastMessege =(TextView) view.findViewById(android.R.id.message);
            toastMessege.setBackgroundColor((Color.parseColor(setColor(pomocnicza))));
            toast.show();
            zapamietane[i] = pomocnicza;
        }
        setColorButtonEnabled(true);
        startButton.setEnabled(false);
    }
    public void CheckButton(View v)
    {
        for(int i=0;i<zapamietane.length;++i)
        {
            System.out.println(zapamietane[i]);
        }
        if( (redButton.getId() == v.getId() && zapamietane[licznik]==1) ||
                (greenButton.getId() == v.getId() && zapamietane[licznik]==2) ||
                (blueButton.getId() == v.getId() && zapamietane[licznik]==3) ||
                (yellowButton.getId() == v.getId() && zapamietane[licznik]==4)||
                (orangebutton.getId() == v.getId() && zapamietane[licznik]==5)||
                (purplebutton.getId() == v.getId() && zapamietane[licznik]==6))
        {
            licznik++;
        }
        else
        {
            Toast.makeText(this,"ŹLE JESZCZE RAZ", Toast.LENGTH_SHORT).show();
            setColorButtonEnabled(false);
            startButton.setEnabled(true);
            zgadnij=2;
        }
        if(licznik == 100 || (zapamietane[licznik] ==0))
        {
            Toast.makeText(this, "BRAWO ODGADŁEŚ WSZYSTKO", Toast.LENGTH_SHORT).show();
            setColorButtonEnabled(false);
            startButton.setEnabled(true);
            zgadnij++;
        }
    }
    public String setColor(int nr)
    {
        String col = "#00ff00";
        switch(nr)
        {
            case 1:
            {
                col="#ff0000";
                break;
            }
            case 2:
            {
                col="#00ff00";
                break;
            }
            case 3:
            {
                col="#0000ff";
                break;
            }
            case 4:
            {
                col="#ffff00";
                break;
            }
            case  5:
            {
                col="#FFA500";
                break;
            }
            case 6:
            {
                col="#A020F0";
                break;
            }

        }
        return col;
    }
    public String setTextVal(int nr)
    {
        String text="";
        switch (nr)
        {
            case 1:
            {
                text="  CZERWONY    ";
                break;
            }
            case 2:
            {
                text="  ZIELONY ";
                break;
            }
            case 3:
            {
                text="  NIEBIESKI   ";
                break;
            }
            case 4:
            {
                text="  ŹÓŁTY   ";
                break;
            }
            case 5:
            {
                text="  POMARAŃCZOWY";
                break;
            }
            case  6:
            {
                text= " FIOLETOWY";
                break;
            }
        }
        return  text;
    }
    public  void level1(View v)
    {
        orangebutton.setVisibility(View.GONE);
        purplebutton.setVisibility(View.GONE);
    }
    public void level2(View v)
    {
        orangebutton.setVisibility((View.VISIBLE));
        purplebutton.setVisibility(View.GONE);
    }
    public  void  level3(View v)
    {
        orangebutton.setVisibility(View.VISIBLE);
        purplebutton.setVisibility(View.VISIBLE);
    }
    private void setColorButtonEnabled(boolean enabled)
    {
        redButton.setEnabled(enabled);
        blueButton.setEnabled(enabled);
        greenButton.setEnabled(enabled);
        yellowButton.setEnabled(enabled);
        orangebutton.setEnabled(enabled);
        purplebutton.setEnabled(enabled);
    }
    }