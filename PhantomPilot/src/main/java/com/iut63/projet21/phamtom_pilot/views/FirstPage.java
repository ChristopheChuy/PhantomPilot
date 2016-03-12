package com.iut63.projet21.phamtom_pilot.views;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.iut63.projet21.phamtom_pilot.R;
import com.iut63.projet21.phamtom_pilot.frame.Atterissage;
import com.iut63.projet21.phamtom_pilot.frame.Decollage;
import com.iut63.projet21.phamtom_pilot.utils.Config;

import dji.sdk.api.DJIDrone;
import dji.sdk.interfaces.DJIReceivedVideoDataCallBack;
import dji.sdk.widget.DjiGLSurfaceView;

public class FirstPage extends Fragment {
    private Decollage decollage;
    private Atterissage atterissage;
    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
                Toast.makeText(getActivity(),(String)message.obj,Toast.LENGTH_SHORT).show();

        };
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final View myFragmentView = inflater.inflate(R.layout.firstpage,
                container, false);
        Button buDec = (Button) myFragmentView.findViewById(R.id.buttonDecollage);
        Button buAtt = (Button) myFragmentView.findViewById(R.id.buttonAtterissage);
        Button buAttUrg = (Button) myFragmentView.findViewById(R.id.buttonUrgence);

        decollage=new Decollage(Config.HAUTEURDECOLLAGE,handler);
        atterissage=new Atterissage(handler);
        buDec.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    decollage.decoller();
                    return false;

                }
            });
        buAtt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                atterissage.atterissage();
                return false;

            }
        });
        buAttUrg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               atterissage.atterissageUrgence();
                return false;

            }
        });
        return myFragmentView;
    }
}