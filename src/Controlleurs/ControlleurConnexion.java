package Controlleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modeles.EtudiantManager;
import Vues.MainFrame;
import Vues.PanelConnexion;
import Vues.PanelConsultationEtudiant;
import Vues.PanelModificationEtudiant;

public class ControlleurConnexion implements ActionListener {
	private MainFrame mainFrame;
	private EtudiantManager monModele;
	PanelConnexion vueConnexion;
	
	public ControlleurConnexion(MainFrame m){
		this.mainFrame = m;
	}
	
	//M�thode permettant de quitter l'application
	public void quit() {
		mainFrame.dispose();
	}
	
	//M�thode permettant la connexion avec les informations donn�es par l'utilisateur (pseudonyme et mot de passe)
	public void connect() {
		boolean isConnected = monModele.connexion(vueConnexion.getPseudo(), vueConnexion.getPassword());
		if(isConnected){
			mainFrame.setEtudiant(monModele.getConnectedEtudiant());
			mainFrame.showCurrentPanel("PanelTabbed");										
		}else{
			JOptionPane.showMessageDialog(mainFrame,
				    "Le nom de l'utilisateur ou le mot de passe est invalide.",
				    "Erreur de connexion",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void setModele(EtudiantManager monModele){
		this.monModele = monModele;
	}
	
	public void setVueConnexion(PanelConnexion v){
		this.vueConnexion = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancel")) quit();
		if(e.getActionCommand().equals("Connexion")) connect();
	}

}
