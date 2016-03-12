package com.iut63.projet21.phamtom_pilot.frame;

import android.graphics.Bitmap;
import android.os.Handler;


import dji.sdk.api.DJIDrone;
import dji.sdk.api.DJIError;
import dji.sdk.interfaces.DJIExecuteBooleanResultCallback;
import dji.sdk.interfaces.DJIExecuteResultCallback;
import com.iut63.projet21.phamtom_pilot.utils.Config;

/**
 * Created by christophe on 04/01/2016.
 */public class Atterissage {
    private Handler handler;
    private final int ALTITUDEGOHOME=20;
    public Atterissage(Handler handler) {
        this.handler = handler;
    }

    /**
     *cette fonction permet au drone d'atterrir à la position  du décollage
     */
    public void atterissageUrgence(){
        // parametrage de l'altitude du GoHome
        DJIDrone.getDjiMainController().setGohomeAltitude(ALTITUDEGOHOME, new DJIExecuteBooleanResultCallback() {
            @Override
            public void onResult(boolean b) {
                //commencer GoHome
                DJIDrone.getDjiMainController().startGoHome(new DJIExecuteResultCallback() {
                    @Override
                    public void onResult(DJIError djiError) {
                        if(djiError.errorCode!=DJIError.RESULT_OK) {
                            handler.sendMessage(handler.obtainMessage(Config.SHOWDIALOG, "errorCode =" + djiError.errorCode + "\n" + "errorDescription =" + DJIError.getErrorDescriptionByErrcode(djiError.errorCode)));
                        }
                        atterissage();

                    }
                });
            }
        });
    }

    /**
     * cette fonction permet au drone d'atterir à la position actuelle du drone
     */
    public void atterissage (){
        // commencer atterisage
        DJIDrone.getDjiMainController().startLanding(new DJIExecuteResultCallback() {
            @Override
            public void onResult(DJIError djiError) {
                if(djiError.errorCode==DJIError.RESULT_OK){
                    handler.sendMessage(handler.obtainMessage(Config.SHOWDIALOG,"atterissage en cours"));
                }
                else
                    handler.sendMessage(handler.obtainMessage(Config.SHOWDIALOG,"errorCode ="+djiError.errorCode + "\n"+"errorDescription =" + DJIError.getErrorDescriptionByErrcode(djiError.errorCode)));

            }
        });
    }

}
