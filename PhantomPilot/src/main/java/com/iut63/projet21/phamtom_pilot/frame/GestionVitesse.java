package com.iut63.projet21.phamtom_pilot.frame;

import android.os.Handler;

/**
 * Created by christophe on 25/01/2016.
 */
public class GestionVitesse {
    private final float VITESSEMAXY=10;
    private final float VITESSEMINY=-10;
    private final float VITESSEMAXX=10;
    private final float VITESSEMINX=-10;
    private final float VITESSEMAXZ=4;
    private final float VITESSEMINZ=-4;
    private final float VITESSEMINRH=-100;
    private final float VITESSEMAXRH=100;
    //vitesse en m/s
    private float vitesseActuelleZ;
    private float vitesseActuelleX;
    private float vitesseActuelleY;
    //vitesse en degree/s
    private float vitesseActuelleRH;
private MouvementDrone mouvementDrone;
    public GestionVitesse(Handler handler) {
        mouvementDrone= new MouvementDrone(handler);
    }
    /**
     *
     * @return vitesse de rotation horizontal du drone  en degree/s
     */
    public float getVitesseActuelleRH() {
        return vitesseActuelleRH;
    }

    /**
     * modification de la vitesse de rotation du drone
     * @param vitesseActuelleRH en degree/s
     */
    private void setVitesseActuelleRH(float vitesseActuelleRH) {
        this.vitesseActuelleRH = vitesseActuelleRH;
    }
    /**
     *
     * @return vitesse du drone sur l'axe des Z en m/s
     */
    public float getVitesseActuelleZ() {
        return vitesseActuelleZ;
    }

    /**
     *
     * @param vitesseActuelleZ
     */
    private void setVitesseActuelleZ(float vitesseActuelleZ) {
        this.vitesseActuelleZ = vitesseActuelleZ;
    }

    /**
     *
     * @return vitesse du drone sur l'axe des X en m/s
     */
    public float getVitesseActuelleX() {
        return vitesseActuelleX;
    }

    private void setVitesseActuelleX(float vitesseActuelleX) {
        this.vitesseActuelleX = vitesseActuelleX;
    }
    /**
     *
     * @return vitesse du drone sur l'axe des Y en m/s
     */
    public float getVitesseActuelleY() {
        return vitesseActuelleY;
    }

    private void setVitesseActuelleY(float vitesseActuelleY) {
        this.vitesseActuelleY = vitesseActuelleY;
    }

    /**
     * change la vitesse du drone
     * @param vitesseX en m/s
     * @throws Exception erreur dans la vitesse
     */
    public void setVitesseDroneX(float vitesseX)throws Exception{
        if(vitesseX>VITESSEMAXX || vitesseX<VITESSEMINX){
            throw new Exception("vitesse incorect");
        }
        mouvementDrone.bouger(vitesseX,vitesseActuelleY,vitesseActuelleZ);
        setVitesseActuelleX(vitesseX);
    }
    /**
     * change la vitesse du drone
     * @param vitesseY en m/s
     * @throws Exception erreur dans la vitesse
     */
    public void setVitesseDroneY(float vitesseY)throws Exception{
        if(vitesseY>VITESSEMAXX || vitesseY<VITESSEMINX){
            throw new Exception("vitesse incorect");
        }
        mouvementDrone.bouger(vitesseActuelleX,vitesseY,vitesseActuelleZ);
        setVitesseActuelleX(vitesseY);
    }
    /**
     * change la vitesse du drone
     * @param vitesseZ en m/s
     * @throws Exception erreur dans la vitesse
     */
    public void setVitesseDroneZ(float vitesseZ)throws Exception{
        if(vitesseZ>VITESSEMAXX || vitesseZ<VITESSEMINX){
            throw new Exception("vitesse incorect");
        }
        mouvementDrone.bouger(vitesseActuelleX,vitesseActuelleY,vitesseZ);
        setVitesseActuelleX(vitesseZ);
    }
    /**
     * change la vitesse du drone
     * @param vitesseRH en m/s
     * @throws Exception erreur dans la vitesse
     */
    public void setVitesseDroneRH(float vitesseRH)throws Exception{
        if(vitesseRH>VITESSEMAXRH || vitesseRH<VITESSEMINRH){
            throw new Exception("vitesse incorect");
        }
        mouvementDrone.bouger(vitesseActuelleX,vitesseActuelleY,vitesseActuelleZ,vitesseRH);
        setVitesseActuelleX(vitesseRH);
    }

    /**
     * immobilise le drone
     */
    public void immobilisation(){
        mouvementDrone.bouger(0,0,0,0);
        setVitesseActuelleZ(0);
        setVitesseActuelleX(0);
        setVitesseActuelleRH(0);
        setVitesseActuelleY(0);
    }
}
