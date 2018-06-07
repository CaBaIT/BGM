package com.example.azubi.bgm;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AusgangFragment extends Fragment {


    public AusgangFragment() {
        // Required empty public constructor
    }

    EditText et_Bauleiter;
    EditText et_Baustelle;
    ImageView iv_gerät;
    TextView tv_gerät;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ausgang, container, false);
        // Inflate the layout for this fragment
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        et_Bauleiter =  ((EditText) view.findViewById(R.id.et_Bauleiter));
        et_Baustelle =  ((EditText) view.findViewById(R.id.et_Baustelle));
        iv_gerät =  ((ImageView) view.findViewById(R.id.iv_gerät));
        tv_gerät =  ((TextView) view.findViewById(R.id.tv_gerätename));
        Button bt_ausgang = (Button) view.findViewById(R.id.bt_ausgang);
        Button bt_scan = (Button) view.findViewById(R.id.bt_scan);


        bt_ausgang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Wollen Sie dieses Gerät wirklich herausgeben?").setPositiveButton("Ja", dialogClickListener)
                        .setNegativeButton("Nein", dialogClickListener).show();
            }

        });
        bt_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_gerät.setVisibility(View.VISIBLE);
                tv_gerät.setVisibility(View.VISIBLE);
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
                    et_Bauleiter.setText("");
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
}