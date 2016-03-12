package com.iut63.projet21.phamtom_pilot.frame;

import dji.sdk.api.DJIDrone;
import dji.sdk.api.GroundStation.DJIGroundStationTypeDef;

/**
 * Created by christophe on 25/01/2016.
 */
public class ControlModeDeplacement {
    /**
     * met en place le mode velocity sur tous les axes
     */
    public void mettreXYZVelocityMode(){
        //met en velocity mode sur l'axe des X et Y
        DJIGroundStationTypeDef.DJINavigationFlightControlHorizontalControlMode modeFlightControlHorizontal =DJIDrone.getDjiGroundStation().getHorizontalControlMode();
        if(modeFlightControlHorizontal != DJIGroundStationTypeDef.DJINavigationFlightControlHorizontalControlMode.Navigation_Flight_Control_Horizontal_Control_Velocity){
            DJIDrone.getDjiGroundStation().setHorizontalControlMode(DJIGroundStationTypeDef.DJINavigationFlightControlHorizontalControlMode.Navigation_Flight_Control_Horizontal_Control_Velocity);
        }
        //met en velocity mode sur l'axe des Z
        DJIGroundStationTypeDef.DJINavigationFlightControlVerticalControlMode modeFlightControlVertical =DJIDrone.getDjiGroundStation().getVerticalControlMode();
        if(modeFlightControlVertical != DJIGroundStationTypeDef.DJINavigationFlightControlVerticalControlMode.Navigation_Flight_Control_Vertical_Control_Velocity) {
            DJIDrone.getDjiGroundStation().setVerticalControlMode(DJIGroundStationTypeDef.DJINavigationFlightControlVerticalControlMode.Navigation_Flight_Control_Vertical_Control_Velocity);
        }
    }

    /**
     * met le mode de deplacement de l'axe des z en vitesse mode
     */
    public void mettreZPositionMode(){
        // met en position mode l'axe des z
        DJIGroundStationTypeDef.DJINavigationFlightControlVerticalControlMode modeFlightControlVertical = DJIDrone.getDjiGroundStation().getVerticalControlMode();
        if(modeFlightControlVertical != DJIGroundStationTypeDef.DJINavigationFlightControlVerticalControlMode.Navigation_Flight_Control_Vertical_Control_Position) {
            DJIDrone.getDjiGroundStation().setVerticalControlMode(DJIGroundStationTypeDef.DJINavigationFlightControlVerticalControlMode.Navigation_Flight_Control_Vertical_Control_Position);
        }
    }

    /**
     * cette fonction met en le mode rotation horizontal en vitesse
     */
    public void mettreRHPalstanceMode(){
        DJIGroundStationTypeDef.DJINavigationFlightControlYawControlMode modeFlightControlVertical = DJIDrone.getDjiGroundStation().getYawControlMode();
        if(modeFlightControlVertical != DJIGroundStationTypeDef.DJINavigationFlightControlYawControlMode.Navigation_Flight_Control_Yaw_Control_Palstance) {
            DJIDrone.getDjiGroundStation().setYawControlMode(DJIGroundStationTypeDef.DJINavigationFlightControlYawControlMode.Navigation_Flight_Control_Yaw_Control_Palstance);
        }
    }
}