package com.example.azubi.bgm;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AusgangFragment extends Fragment {


    public AusgangFragment() {
        // Required empty public constructor
    }

    public static final String EmployeeNamearray = "polier";
    public static final String JSON_ARRAY = "result";
    private JSONArray result;
    private ArrayList<String> arrayList;
    EditText et_Baustelle;
    ImageView iv_gerät;
    TextView tv_gerät;
    Spinner spn_polier;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ausgang, container, false);
        // Inflate the layout for this fragment
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        et_Baustelle =  ((EditText) view.findViewById(R.id.et_Baustelle));
        iv_gerät =  ((ImageView) view.findViewById(R.id.iv_gerät));
        tv_gerät =  ((TextView) view.findViewById(R.id.tv_gerätename));
        Button bt_ausgang = (Button) view.findViewById(R.id.bt_ausgang);
        Button bt_scan = (Button) view.findViewById(R.id.bt_scan);
        spn_polier =view.findViewById(R.id.spn_polier);

        arrayList = new ArrayList<String>();
        getdata();
        spn_polier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        bt_ausgang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spn_polier.getSelectedItemPosition()==0){
                    Toast.makeText(getActivity(),"Polier auswählen",Toast.LENGTH_LONG).show();

                }else {
                    if(et_Baustelle.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(),"Baustelle auswählen",Toast.LENGTH_LONG).show();
                    }
                    else {
                        builder.setMessage("Wollen Sie dieses Gerät wirklich herausgeben?").setPositiveButton("Ja", dialogClickListener)
                                .setNegativeButton("Nein", dialogClickListener).show();
                    }

                }


            }

        });
        bt_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                iv_gerät.setVisibility(View.VISIBLE);
//                tv_gerät.setVisibility(View.VISIBLE);
                IntentIntegrator.forSupportFragment(AusgangFragment.this)
                .initiateScan();
            }

        });
        return view;
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    et_Baustelle.setText("");
                    iv_gerät.setVisibility(View.INVISIBLE);
                    tv_gerät.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(),"Gerät erfolgreich herausgegeben",Toast.LENGTH_LONG).show();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(getActivity(), "Abgebrochen", Toast.LENGTH_LONG).show();
            } else {
                    iv_gerät.setVisibility(View.VISIBLE);
                    tv_gerät.setVisibility(View.VISIBLE);
                    tv_gerät.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void getdata() {
        StringRequest stringRequest = new StringRequest("https://siegerfrequenz.de/bgm_api/get_polier.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            result = j.getJSONArray(JSON_ARRAY);
                            empdetails(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }
    private void empdetails(JSONArray j) {
        for (int i = 0; i < j.length(); i++) {
            try {
                JSONObject json = j.getJSONObject(i);
                arrayList.add(json.getString(EmployeeNamearray));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // arrayList.add(0,"Select Employee");
        spn_polier.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList));
    }
    private String getemployeeName(int position){
        String name="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);
            //Fetching name from that object
            name = json.getString("polier");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return name;
    }

}