package com.example.alor.bazadanych;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View;
import android.database.Cursor;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener ,AdapterView.OnItemSelectedListener
{
    public CheckBox check;

    public Button btnInsert;
    public Button btnDelete;
    public Button btnSelect;
    public Button btnSearch;
    public EditText editName;
    public EditText editAge;
    public EditText editDelete;
    public EditText editSearch;
    public EditText editPesel;
    public EditText editNazwisko;
    public EditText editPlec;
    public String spinnerchoose="";
    Spinner list;
    ZarzadzajDanymi dm;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dm = new ZarzadzajDanymi(this);

        check = findViewById(R.id.check);

        btnInsert = findViewById(R.id.insert);
        btnDelete = findViewById(R.id.deletebutton);
        btnSelect = findViewById(R.id.selectall);
        btnSearch = findViewById(R.id.search);

        editName = findViewById(R.id.imietext);
        editAge = findViewById(R.id.wiektext);
        editDelete = findViewById(R.id.deleteimietext);
        editPesel = findViewById(R.id.peseltext);
        editNazwisko = findViewById(R.id.nazwiskotext);
        editSearch = findViewById(R.id.searchimie);
        editPlec =  findViewById(R.id.plectext);

        list = findViewById(R.id.spinner);

        String[] items = new String[]{"Imie", "Nazwisko", "Wiek","Płeć"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        list.setAdapter(adapter);

        list.setOnItemSelectedListener(this);
        btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        showData();
        search();
    }

    public void showData()
    {
        btnSelect.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Cursor c = dm.selectAll();
                                             if (c.getCount() == 0) {
                                                 show("Error", "Nic nie znaleziono");
                                                 return;
                                             }

                                             StringBuffer buffer = new StringBuffer();
                                             while (c.moveToNext()) {
                                                 buffer.append("Id :" + c.getString(0) + "\n");
                                                 buffer.append("Imie :" + c.getString(1) + "\n");
                                                 buffer.append("Nazwisko :" + c.getString(2) + "\n");
                                                 buffer.append("Wiek :" + c.getString(3) + "\n");
                                                 buffer.append("Pesel :" + c.getString(4) + "\n");
                                                 buffer.append("Płeć :" + c.getString(5) + "\n\n");
                                             }
                                             show("Data", buffer.toString());
                                         }
                                     }
            );
    }

    public void search()
    {
        btnSearch.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view)
                                         {
                                             Cursor  c = null;
                                            if(spinnerchoose=="Imie")
                                            {
                                                c = dm.searchName(editSearch.getText().toString());
                                            }
                                           else if(spinnerchoose=="Nazwisko")
                                             {
                                                 c = dm.searchNazwisko(editSearch.getText().toString());
                                             }
                                            else if(spinnerchoose=="Wiek")
                                             {
                                                 c = dm.searchAge(editSearch.getText().toString());
                                             }
                                            else if(spinnerchoose=="Płeć")
                                             {
                                                 c = dm.serchPlec(editSearch.getText().toString());
                                             }


                                             if (c.getCount() == 0)
                                             {
                                                 show("Error", "Nic nie znaleziono");
                                                 return;
                                             }

                                             StringBuffer buffer = new StringBuffer();
                                             while(c.moveToNext())
                                             {
                                                 buffer.append("Id:"+c.getString(0)+"\n");
                                                 buffer.append("Imie:"+c.getString(1)+"\n");
                                                 buffer.append("Nazwisko:"+c.getString(2)+"\n");
                                                 buffer.append("Wiek:"+c.getString(3)+"\n");
                                                 buffer.append("Pesel:"+c.getString(4)+"\n");
                                                 buffer.append("Płeć:"+c.getString(5)+"\n\n");
                                                 if(!check.isChecked())
                                                 {
                                                     break;
                                                 }
                                             }

                                             show("Data", buffer.toString());
                                         }
                                     }
        );
    }

    public void show(String title, String mess)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(mess);
        builder.show();
    }

    @Override
    public void onClick(View v)
    {
        String imie = editName.getText().toString();
        String nazwisko = editNazwisko.getText().toString();
        String age = editAge.getText().toString();
        String pesel = editPesel.getText().toString();
        String plec = editPlec.getText().toString();
        if(imie.equals(""))
        {
            imie="Null";
        }
        if(nazwisko.equals(""))
        {
            nazwisko="Null";
        }
        if(age.equals(""))
        {
            age="Null";
        }
        if(pesel.equals(""))
        {
            pesel="Null";
        }
        if(plec.equals(""))
        {
            plec="Null";
        }

        switch (v.getId())
        {
            case R.id.insert:
                    dm.insert(imie,
                            nazwisko,
                            age,
                            pesel,
                            plec);
                break;
            case R.id.deletebutton:
                dm.delete(editDelete.getText().toString());
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        TextView text = (TextView) view;
        spinnerchoose = (String) text.getText();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }
}
