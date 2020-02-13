package com.habitissimo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private WebView webView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        this.setContentView(R.layout.activity_main);

        this.listView = (ListView) findViewById(R.id.listView);

        List<Budget> budgets = new ArrayList<>();
        budgets.add(new Budget("Reparación baño", "Baños", "Antonio", "antonio@mail.com", 623145874, new Location("Madrid")));

        budgets.add(new Budget("Instalación cocina", "Cocina", "Maria", "maria@mail.com", 214589654, null));

        budgets.add(new Budget("Montar armario", "Chapuzas", "Carmen", null, null, null));

        budgets.add(new Budget("Armario empotrado", "Carpintería", null, null, null, null));

        budgets.add(new Budget("Reparación vidé", "Baños", null, null, null, null));

        this.listView.setAdapter(new BudgetAdapter(this, budgets));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setProgressBarIndeterminateVisibility(true);

                progressDialog = ProgressDialog.show(MainActivity.this, "ProgressDialog", "Loading");
            }
        });
    }
}
