package labo5init;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//un panneau qui utilise le gestionnaire cardLayout
	JPanel cardsPanel ;	
	JPanel cardPanelConnection;
	PanelTabbed cardPanelTabbed;
	
	public MainFrame(String nomLogiciel) {
		
		// TODO ajouter le code pour les elements de la JFrame
		super(nomLogiciel);
		cardsPanel = new JPanel(new CardLayout());
		this.add(cardsPanel);
		
		cardPanelConnection = new PanelConnexion(this);		
		cardPanelTabbed = new PanelTabbed();	
		cardPanelTabbed.setMainFrame(this);
		
		cardsPanel.add(cardPanelConnection,"PanelConnexion");
		cardsPanel.add(cardPanelTabbed, "PanelTabbed");
		
		EtudiantManager.getInstance();
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
