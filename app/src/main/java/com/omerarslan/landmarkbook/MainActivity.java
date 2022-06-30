package com.omerarslan.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        //Data

        ArrayList<String> landmarkNames = new ArrayList<>();
        landmarkNames.add("Pisa");
        landmarkNames.add("Eiffel");
        landmarkNames.add("Colosseo");
        landmarkNames.add("London Bridge");

        ArrayList<String> countryNames = new ArrayList<>();
        //Eklemeleri yaparken landmarkNames ile aynı indexde ekleme yapmamız gerekiyor. Bilgilerin birbiriyle eşleşmesi için.
        countryNames.add("Italy");
        countryNames.add("France");
        countryNames.add("Italy");
        countryNames.add("United Kingdom");

        Bitmap pisa = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.pisa);
        //Bir Bitmap objesi oluşturduk. Artık bunu ArrayList dahil istediğimiz yerde kullanabiliriz.
        Bitmap eiffel = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.eiffel);
        Bitmap colosseo = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.colosseo);
        Bitmap longonBridge = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.londonbridge);

        ArrayList<Bitmap> landmarkImages = new ArrayList<>();
        landmarkImages.add(pisa);
        landmarkImages.add(eiffel);
        landmarkImages.add(colosseo);
        landmarkImages.add(longonBridge);

        //ListView

        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,landmarkNames);
        //simple_list_item_1 : Liste içerisinde tek satır halinde sadece metin göstereceksek bunu kullanabiliriz.

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override                                               // hangisine tıklarsak onun indexi position a atanır.
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //listView içerisinde herhangi bir item a tıklanınca ne olacağını belirttiğimiz kısım
                //System.out.println(landmarkNames.get(position));
                //System.out.println(countryNames.get(position));

                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                intent.putExtra("name",landmarkNames.get(position));
                intent.putExtra("country",countryNames.get(position));

                //Bitmap büyük bir veri olduğundan dolayı intent.putExtra ile aktarılamıyor.
                //putExtra Bitmap verisi aktarmak için uygun bir metod değildir.

                Singleton singleton = Singleton.getInstance();
                singleton.setChosenImage(landmarkImages.get(position));

                startActivity(intent);
            }
        });
    }
}
//Singleton da tek bir obje oluşturacağız ve böylelikle hangi activite üzerinde oluşturursak oluşturalım bize tek bir obje üstünde işlem yetkisi verilsin istiyoruz.