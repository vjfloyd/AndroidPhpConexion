package com.sistemas.vjrojasb.androidphpcrud;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;


public class MainScreenActivity extends ActionBarActivity {

    Button btnVerProductos;
    Button btnNuevoProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //Botones:
        btnVerProductos = (Button)findViewById(R.id.btnViewProducts);
        btnNuevoProducto = (Button)findViewById(R.id.btnCreateProduct);

        //Ver productos , click event
        btnVerProductos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Launching all products activity
                Intent i = new Intent(getApplicationContext(), ListarProductosActivity.class);
                startActivity(i);
            }

        });

        //Agregar productos clic event
            btnNuevoProducto.setOnClickListener(new View.OnClickListener(){
                @Override
            public void onClick(View view){
                    //Lauching create new product activity
                    Intent i = new Intent(getApplicationContext(), AgregarProductoActivity.class);
                    startActivity(i);
                }

            });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_screen, container, false);
            return rootView;
        }
    }
}
