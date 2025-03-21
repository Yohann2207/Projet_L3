package Ressource;

public class Ordinateur extends Ressource{
	
	private boolean port_USB;
	private boolean port_USB_C;
	private boolean port_HDMI;
	
	public Ordinateur(int id, String nom, String marque, int autonomie, int nb_coeurs, int memoire, int resolution_ecran, boolean libre, double prix, int duree_max, boolean port_USB, boolean port_USB_C, boolean port_HDMI) {
		super(id, nom, marque, autonomie, nb_coeurs, memoire, resolution_ecran, libre, prix, duree_max);
		this.port_USB = port_USB;
		this.port_USB_C = port_USB_C;
		this.port_HDMI = port_HDMI;
	}

}
