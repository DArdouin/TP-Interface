package Vues;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controlleurs.ControlleurConnexion;
import Modèles.Etudiant;
import Modèles.EtudiantManager;

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
	PanelConsultationEtudiant vueEtudiants;
	PanelModificationEtudiant vueModificationEtudiants;
	
	public MainFrame(String nomLogiciel) {
		super(nomLogiciel);
		cardsPanel = new JPanel(new CardLayout());
		this.add(cardsPanel);
				
		cardPanelTabbed = new PanelTabbed();	
		cardPanelTabbed.setMainFrame(this);
		
		//Modèle MVC :
			//On définit le modèle
		monModele = new EtudiantManager();
		
			//On définit les vues
		vueConnexion = new PanelConnexion();
		vueEtudiants = new PanelConsultationEtudiant();
		vueModificationEtudiants = new PanelModificationEtudiant();
		
			//On définit les controlleurs
		controlleurConnexion =  new ControlleurConnexion();
		
			//On indique les vues & les modèles au controlleur
		controlleurConnexion.setModele(monModele);
		controlleurConnexion.setVueConnexion(vueConnexion);
		
			//On abonne les vues aux modèles et aux controlleurs
		vueConnexion.setControlleur(controlleurConnexion);
		vueConnexion.setModele(monModele);
		
			//On ajoute les observer au modèle		
		monModele.addObserver(vueConnexion);
		
		//On ajoute les vues à la mainFrame
		cardPanelTabbed.addConsultation(vueEtudiants);
		cardPanelTabbed.addModificationEtudiants(vueModificationEtudiants);
		cardsPanel.add(vueConnexion,"PanelConnexion");
		cardsPanel.add(cardPanelTabbed, "PanelTabbed");
	}
	
	//Méthode permettant d'afficher le panel souhaité
	public void showCurrentPanel(String panelToShow) {
		CardLayout currentLayout = (CardLayout) (cardsPanel.getLayout());
		currentLayout.show(cardsPanel, panelToShow);
	}
	
	public void setEtudiant(Etudiant etudiant){
		cardPanelTabbed.setEtudiant(etudiant);		
	}
}
