package com.iut63.projet21.phamtom_pilot.frame;

import android.os.Handler;

import com.iut63.projet21.phamtom_pilot.utils.Config;

import dji.sdk.api.DJIDrone;
import dji.sdk.api.DJIError;
import dji.sdk.api.GroundStation.DJIGroundStationFlyingInfo;
import dji.sdk.interfaces.DJIExecuteResultCallback;


/**
 * Created by christophe on 04/01/2016.
 */
public class Decollage {
    private MouvementDrone mouvementDrone;
    private int hauteur;

    private Handler handler;
    public Decollage(int hauteur, Handler handler) {
        this.hauteur = hauteur;
        this.handler = handler;
        mouvementDrone = new MouvementDrone(handler);
    }

    /**
     * fonction qui permet le décollage du drone
     */
    public void decoller() {
        DJIGroundStationFlyingInfo flyingInfo = new DJIGroundStationFlyingInfo();
        //enregistrement de la localisation du drone
        DJIDrone.getDjiMainController().setAircraftHomeGpsLocation(flyingInfo.homeLocationLatitude, flyingInfo.droneLocationLongitude, new DJIExecuteResultCallback() {
            @Override
            public void onResult(DJIError djiError) {
                if (djiError.errorCode == DJIError.RESULT_OK) {
                    //allumage des moteurs
                    DJIDrone.getDjiMainController().turnOnMotor(new DJIExecuteResultCallback() {
                        @Override
                        public void onResult(DJIError djiError) {
                            if (djiError.errorCode == DJIError.RESULT_OK) {
                                //commence décollage
                                DJIDrone.getDjiMainController().startTakeoff(new DJIExecuteResultCallback() {
                                    @Override
                                    public void onResult(DJIError djiError) {
                                        if (djiError.errorCode == DJIError.RESULT_OK) {
                                            //appel de mouvement drone pour monter le drone
                                            mouvementDrone.monter(hauteur);
                                            handler.sendMessage(handler.obtainMessage(Config.SHOWDIALOG, "decollage en cours"));
                                        } else
                                            handler.sendMessage(handler.obtainMessage(Config.SHOWDIALOG, "errorCode =" + djiError.errorCode + "\n" + "errorDescription =" + DJIError.getErrorDescriptionByErrcode(djiError.errorCode)));
                                    }
                                });
                            } else
                                handler.sendMessage(handler.obtainMessage(Config.SHOWDIALOG, "errorCode =" + djiError.errorCode + "\n" + "errorDescription =" + DJIError.getErrorDescriptionByErrcode(djiError.errorCode)));
                        }
                    });
                } else
                    handler.sendMessage(handler.obtainMessage(Config.SHOWDIALOG, "errorCode =" + djiError.errorCode + "\n" + "errorDescription =" + DJIError.getErrorDescriptionByErrcode(djiError.errorCode)));
            }
        });
    }
}
