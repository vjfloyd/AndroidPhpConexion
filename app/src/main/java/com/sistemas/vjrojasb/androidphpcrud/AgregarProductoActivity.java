package com.sistemas.vjrojasb.androidphpcrud;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vjrojasb on 3/16/15.
 */
public class AgregarProductoActivity extends Activity{

    private ProgressDialog progressDialog;

    JSONParser jsonParser = new  JSONParser();
    EditText inputNombre;
    EditText inputPrecio;
    EditText inputDesc;
    // url to create new product
    private static String url_create_product = "http://192.168.1.14:8888/android_connect/create_product.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_producto);

        // Edit Text
        inputNombre = (EditText) findViewById(R.id.inputName);

        inputPrecio = (EditText) findViewById(R.id.inputPrice);
        inputDesc = (EditText) findViewById(R.id.inputDesc);

        // Create button
        Button btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);

        // button click event
        btnCreateProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                new CreateNewProduct().execute();
            }
        });
    }

    /**
     * Background Async Task to Create new product
     * */
    class CreateNewProduct extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(AgregarProductoActivity.this);
            progressDialog.setMessage("Creating Product..baby");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String name = inputNombre.getText().toString();
            String price = inputPrecio.getText().toString();
            String description = inputDesc.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("price", price));
            params.add(new BasicNameValuePair("description", description));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_product, "POST",params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product
                    Intent i = new Intent(getApplicationContext(), ListarProductosActivity.class);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            progressDialog.dismiss();
        }

    }
}
