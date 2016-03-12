package com.iut63.projet21.phamtom_pilot.views;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.iut63.projet21.phamtom_pilot.R;
import com.iut63.projet21.phamtom_pilot.frame.Atterissage;
import com.iut63.projet21.phamtom_pilot.frame.GestionVitesse;
import com.iut63.projet21.phamtom_pilot.utils.Config;

public class SecondPage extends Fragment {

    private Atterissage atterrisage;
    private GestionVitesse gVitesse;

    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            Toast.makeText(getActivity(),(String)message.obj,Toast.LENGTH_SHORT);

        };
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        atterrisage=new Atterissage(handler);
        gVitesse=new GestionVitesse(handler);
        final View myFragmentView = inflater.inflate(R.layout.secondpage,
                container, false);
        ImageButton buGauche = (ImageButton) myFragmentView.findViewById(R.id.dirGauche);
        ImageButton buDroite = (ImageButton) myFragmentView.findViewById(R.id.dirDroite);
        ImageButton buBas = (ImageButton) myFragmentView.findViewById(R.id.dirBas);
        ImageButton buHaut = (ImageButton) myFragmentView.findViewById(R.id.dirHaut);
        ImageButton buAltPlus = (ImageButton) myFragmentView.findViewById(R.id.altPlus);
        ImageButton buAltMoins = (ImageButton) myFragmentView.findViewById(R.id.altMoins);
        Button buArrUrgence=(Button) myFragmentView.findViewById(R.id.buUrgence);
        ImageButton burotGauche=(ImageButton)myFragmentView.findViewById(R.id.rotGauche);
        ImageButton burotDroite=(ImageButton)myFragmentView.findViewById(R.id.rotDroite);

        buGauche.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try{
                gVitesse.setVitesseDroneX((Config.VITESSEX)*(-1));
                }catch(Exception e){
                    Log.e("ErreurDrone",e.getMessage());
                    return false;
                }
                return false;

            }
        });

        buDroite.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try{
                    gVitesse.setVitesseDroneX((Config.VITESSEX));
                }catch(Exception e){
                    Log.e("ErreurDrone",e.getMessage());
                    return false;
                }
                return false;

            }
        });

        buHaut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try{
                    gVitesse.setVitesseDroneY((Config.VITESSEY));
                }catch(Exception e){
                    Log.e("ErreurDrone",e.getMessage());
                    return false;
                }
                return false;

            }
        });

        buBas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try{
                    gVitesse.setVitesseDroneY((Config.VITESSEY)*(-1));
                }catch(Exception e){
                    Log.e("ErreurDrone",e.getMessage());
                    return false;
                }
                return false;

            }
        });

        buAltPlus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try{
                    gVitesse.setVitesseDroneZ((Config.VITESSEZ));
                }catch(Exception e){
                    Log.e("ErreurDrone",e.getMessage());
                    return false;
                }
                return false;

            }
        });

        buAltMoins.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try{
                    gVitesse.setVitesseDroneZ((Config.VITESSEZ)*(-1));
                }catch(Exception e){
                    Log.e("ErreurDrone",e.getMessage());
                    return false;
                }
                return false;

            }
        });

        buArrUrgence.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                atterrisage.atterissageUrgence();
                return false;

            }
        });

        burotGauche.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {
                    gVitesse.setVitesseDroneRH((float)20);
                } catch (Exception e) {
                    Log.e("ErreurDrone",e.getMessage());
                    return false;
                }
                return false;
            }
        });


        return myFragmentView;
    }
}