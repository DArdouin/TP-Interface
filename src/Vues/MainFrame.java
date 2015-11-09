package Vues;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controlleurs.ControlleurConnexion;

import Controlleurs.ControlleurModification;

import Modeles.Etudiant;
import Modeles.EtudiantManager;


public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//un panneau qui utilise le gestionnaire cardLayout
	JPanel cardsPanel ;
	PanelTabbed cardPanelTabbed;
	EtudiantManager monModele;
	PanelConnexion vueConnexion;
	ControlleurConnexion controlleurConnexion;
	ControlleurModification controlleurModification;
	PanelConsultationEtudiant vueEtudiants;
	PanelModificationEtudiant vueModificationEtudiants;
	
	public MainFrame(String nomLogiciel) {
		super(nomLogiciel);
		cardsPanel = new JPanel(new CardLayout());
		this.add(cardsPanel);
				
		cardPanelTabbed = new PanelTabbed();	
		cardPanelTabbed.setMainFrame(this);
		
		//Mod�le MVC :
			//On d�finit le mod�le
		monModele = new EtudiantManager();
		

			//On d�finit les controlleurs
		controlleurConnexion =  new ControlleurConnexion(this);
		controlleurModification = new ControlleurModification(this);
		
			//On d�finit les vues
			//On abonne les vues aux mod�les et aux controlleurs	
		vueConnexion = new PanelConnexion(monModele,controlleurConnexion);
		vueEtudiants = new PanelConsultationEtudiant(monModele);
		vueModificationEtudiants = new PanelModificationEtudiant(monModele,controlleurModification);

		
			//On indique les vues & les mod�les au controlleur
		controlleurConnexion.setModele(monModele);
		controlleurConnexion.setVueConnexion(vueConnexion);

		controlleurModification.setModele(monModele);
		controlleurModification.setVueModification(vueModificationEtudiants);

		
		
		
			//On ajoute les observer au mod�le		
		monModele.addObserver(vueConnexion);
		monModele.addObserver(vueEtudiants);
		monModele.addObserver(vueModificationEtudiants);
		
		//On ajoute les vues � la mainFrame
		cardPanelTabbed.addConsultation(vueEtudiants);
		cardPanelTabbed.addModificationEtudiants(vueModificationEtudiants);
		cardsPanel.add(vueConnexion,"PanelConnexion");
		cardsPanel.add(cardPanelTabbed, "PanelTabbed");
	}
	
	//M�thode permettant d'afficher le panel souhait�
	public void showCurrentPanel(String panelToShow) {
		CardLayout currentLayout = (CardLayout) (cardsPanel.getLayout());
		currentLayout.show(cardsPanel, panelToShow);
	}
	
	public void setEtudiant(Etudiant etudiant){
		cardPanelTabbed.setEtudiant(etudiant);		
	}
}
