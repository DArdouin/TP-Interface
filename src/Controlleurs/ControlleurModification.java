package Controlleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import Modeles.Cours;
import Modeles.Etudiant;
import Modeles.EtudiantManager;
import Modeles.Programme;
import Vues.MainFrame;
import Vues.PanelModificationEtudiant;

public class ControlleurModification implements ActionListener{
	private MainFrame mainFrame;
	private EtudiantManager monModele;
	private PanelModificationEtudiant vueModificationEtudiants;
	private Etudiant connectedEtudiant;
	
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
			mainFrame.dispose();
		else
			vueModificationEtudiants.miseAJour();
	}
	
	public void save(){
		// Sauvegarde des informations de l'�tudiant
		connectedEtudiant.setPrenom(vueModificationEtudiants.getPrenomTextField().getText());
		connectedEtudiant.setNom(vueModificationEtudiants.getNomTextField().getText());
		connectedEtudiant.setPseudo(vueModificationEtudiants.getPseudoTextField().getText());
		connectedEtudiant.setMotDePasse(vueModificationEtudiants.getPasswordField().getPassword().toString());
		connectedEtudiant.setProgramme(monModele.getProgrammes().get(vueModificationEtudiants.getProgrammeComboBox().getSelectedIndex()));
		
		connectedEtudiant.setEstHomme(vueModificationEtudiants.getManRadioButton().isSelected());
		
		ArrayList<Cours> cours = new ArrayList<Cours>();
		for (Entry<Cours, JCheckBox> chck : vueModificationEtudiants.getCoursChckbx().entrySet()) {				
			if(chck.getValue().isSelected())
				cours.add(chck.getKey());
				
		}
		connectedEtudiant.setCours(cours);
		
		mainFrame.setEtudiant(connectedEtudiant);
		
	}

	public void setModele(EtudiantManager m) {
		this.monModele = m;
	}
	
	public void setVueModification(PanelModificationEtudiant v){
		this.vueModificationEtudiants = v;
	}

	public void setEtudiant(Etudiant connectedEtudiant) {
		this.connectedEtudiant = connectedEtudiant;
		
	}
}
