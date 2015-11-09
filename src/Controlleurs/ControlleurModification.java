package Controlleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Modèles.EtudiantManager;
import Vues.MainFrame;
import Vues.PanelModificationEtudiant;

public class ControlleurModification implements ActionListener{
	private MainFrame mainFrame;
	private EtudiantManager monModele;
	private PanelModificationEtudiant vueModificationEtudiants;
	
	public ControlleurModification(MainFrame m){
		this.mainFrame = m;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancel")) cancel();
		if(e.getActionCommand().equals("Sauvegarder")) save();		
	}
	public void cancel() {
		// Si l'utilisateur ne souhaite pas sauvegarder, recharge les infos de base
		if(JOptionPane.showConfirmDialog(null,"Voulez vous quitter sans sauvegarder vos modifications") == 0)
			vueModificationEtudiants.miseAJour();
	}
	
	public void save(){
		// Sauvegarde des informations de l'étudiant
		JOptionPane.showMessageDialog(null,
			    "La sauvegarde des données n'a pas été implémentée.",
			    "Erreur de sauvegarde",
			    JOptionPane.ERROR_MESSAGE);
	}

	public void setModele(EtudiantManager m) {
		this.monModele = m;
	}
	
	public void setVueModification(PanelModificationEtudiant v){
		this.vueModificationEtudiants = v;
	}
}
