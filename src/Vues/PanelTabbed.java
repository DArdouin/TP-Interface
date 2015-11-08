package Vues;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTabbedPane;

import Modèles.Etudiant;

public class PanelTabbed extends JTabbedPane implements MouseListener {
	private static final long serialVersionUID = 1L;
	PanelConsultationEtudiant consult;
	PanelModificationEtudiant edit;
	private MainFrame mainFrame;
	
	/**
	 * Interface generale qui contient les onglets de consultation 
	 * et d'édition des étudiants
	 */
	public PanelTabbed() {
	}
	
	public void addConsultation(PanelConsultationEtudiant p){
		consult = p;
		this.addTab("Consulter", null, consult, null);
	}

	public void addModificationEtudiants(PanelModificationEtudiant p){
		edit = new PanelModificationEtudiant();
		addTab("Editer", null, edit, null);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		for(int i = 0;i<this.getTabCount();i++){
			  if(this.getBoundsAt(i).contains(arg0.getPoint())){ //where e is the mouseEvent
			    System.out.println("Mouse was clicked in tab bounds");
			    return;
			  }
			}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setEtudiant(Etudiant connectedEtudiant) {
		consult.setEtudiant(connectedEtudiant);
		edit.setEtudiant(connectedEtudiant);
	}
	
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
	}
	public MainFrame getMainFrame() {
		return mainFrame;
	}

}
