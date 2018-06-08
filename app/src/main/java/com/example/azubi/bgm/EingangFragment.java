package com.example.azubi.bgm;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EingangFragment extends Fragment {


    public EingangFragment() {
        // Required empty public constructor
    }
    ImageView iv_gerät;
    TextView tv_gerät;
    TextView tv_polier;
    TextView tv_baustelle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_eingang, container, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        iv_gerät =  ((ImageView) view.findViewById(R.id.iv_gerät));
        tv_gerät =  ((TextView) view.findViewById(R.id.tv_gerätename));
        tv_polier =  ((TextView) view.findViewById(R.id.tv_polier));
        tv_baustelle =  ((TextView) view.findViewById(R.id.tv_baustelle));
        Button bt_eingang = (Button) view.findViewById(R.id.bt_eingang);
        Button bt_scan = (Button) view.findViewById(R.id.bt_scan);

        bt_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_gerät.setVisibility(View.VISIBLE);
                tv_gerät.setVisibility(View.VISIBLE);
                tv_polier.setVisibility(View.VISIBLE);
                tv_baustelle.setVisibility(View.VISIBLE);
            }

        });

        bt_eingang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Sind Sie sich bei dieser Rücknahme sicher?").setPositiveButton("Ja", dialogClickListener)
                        .setNegativeButton("Nein", dialogClickListener).show();
            }

        });

        return view;
    }
    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    iv_gerät.setVisibility(View.INVISIBLE);
                    tv_gerät.setVisibility(View.INVISIBLE);
                    tv_polier.setVisibility(View.INVISIBLE);
                    tv_baustelle.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(),"Gerät erfolgreich zurückgenommen",Toast.LENGTH_LONG).show();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    //No button clicked
                    break;
            }
        }
    };

}
