package com.iut63.projet21.phamtom_pilot.frame;


import android.os.Handler;

import com.iut63.projet21.phamtom_pilot.utils.Config;

import dji.sdk.api.DJIDrone;
import dji.sdk.api.DJIError;
import dji.sdk.interfaces.DJIExecuteResultCallback;

/**
 * Created by christophe on 25/01/2016.
 */
public class MouvementDrone {
    private Handler handler;
    private ControlModeDeplacement controlModeDeplacement;

    public MouvementDrone(Handler handler) {
        this.handler = handler;
        controlModeDeplacement = new ControlModeDeplacement();
    }

    /**
     * cette fonction met en mouvement le drone grâce au vitesse des axes passé en paramètre
     * @param vitesseX vitesse sur l'axe des X en m/s
     * @param vitesseY vitesse sur l'axe des Y en m/s
     * @param vitesseZ vitesse sur l'axe des Z en m/s
     */
    public void bouger(float vitesseX,float vitesseY,float vitesseZ){
        //mis en place control déplacement
        controlModeDeplacement.mettreXYZVelocityMode();
        DJIDrone.getDjiGroundStation().sendFlightControlData(0,vitesseY,vitesseX,vitesseZ, new DJIExecuteResultCallback() {
            @Override
            public void onResult(DJIError djiError) {
                if(djiError.errorCode != DJIError.RESULT_OK){
                    handler.sendMessage(handler.obtainMessage(Config.SHOWDIALOG, "errorCode =" + djiError.errorCode + "\n" + "errorDescription =" + DJIError.getErrorDescriptionByErrcode(djiError.errorCode)));
                }
            }
        });
    }
    /**
     * cette fonction met en mouvement le drone grâce au vitesse des axes passé en paramètre
     * @param vitesseX vitesse sur l'axe des X en m/s
     * @param vitesseY vitesse sur l'axe des Y en m/s
     * @param vitesseZ vitesse sur l'axe des Z en m/s
     * @param vitesseRH vitesse de rotation horizontal du drone degree/s
     */

    public void bouger(float vitesseX,float vitesseY,float vitesseZ,float vitesseRH){
        //mis en place control déplacement
        controlModeDeplacement.mettreXYZVelocityMode();
        controlModeDeplacement.mettreRHPalstanceMode();
        DJIDrone.getDjiGroundStation().sendFlightControlData(vitesseRH,vitesseY,vitesseX,vitesseZ, new DJIExecuteResultCallback() {
            @Override
            public void onResult(DJIError djiError) {
                if(djiError.errorCode != DJIError.RESULT_OK){
                    handler.sendMessage(handler.obtainMessage(Config.SHOWDIALOG, "errorCode =" + djiError.errorCode + "\n" + "errorDescription =" + DJIError.getErrorDescriptionByErrcode(djiError.errorCode)));
                }
            }
        });
    }

    /**
     * cette méthode va faire monter le drone a l'hauteur passé en paramètre
     * @param hauteur hauteur en m/s
     */
    public void monter(int hauteur){
        //mis en place control déplacement
        controlModeDeplacement.mettreZPositionMode();
        final int HAUTEUR=hauteur;
        DJIDrone.getDjiGroundStation().sendFlightControlData(0, 0, 0, hauteur, new DJIExecuteResultCallback() {
            @Override
            public void onResult(DJIError djiError) {
                handler.sendMessage(handler.obtainMessage(Config.SHOWDIALOG, "drone a "+Integer.toString(HAUTEUR)+"de hauteur"));
            }
        });
    }
}
