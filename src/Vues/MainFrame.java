package Vues;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controlleurs.ControlleurConnexion;
import Mod�les.Etudiant;
import Mod�les.EtudiantManager;

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
		
		//Mod�le MVC :
			//On d�finit le mod�le
		monModele = new EtudiantManager();
		
			//On d�finit les vues
		vueConnexion = new PanelConnexion();
		vueEtudiants = new PanelConsultationEtudiant();
		vueModificationEtudiants = new PanelModificationEtudiant();
		
			//On d�finit les controlleurs
		controlleurConnexion =  new ControlleurConnexion();
		
			//On indique les vues & les mod�les au controlleur
		controlleurConnexion.setModele(monModele);
		controlleurConnexion.setVueConnexion(vueConnexion);
		
			//On abonne les vues aux mod�les et aux controlleurs
		vueConnexion.setControlleur(controlleurConnexion);
		vueConnexion.setModele(monModele);
		
			//On ajoute les observer au mod�le		
		monModele.addObserver(vueConnexion);
		
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
