package Vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Modèles.Cours;
import Modèles.Etudiant;
import Modèles.EtudiantManager;

public class PanelConsultationEtudiant extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	private Etudiant etudiant;

	private final JLabel lblPseudo;
	private final JLabel lblNomComplet;
	private final JLabel lblSexe;
	private final JLabel lblProgram;
	private final JList cours;
	private JScrollPane jscrollPane; 
	private EtudiantManager monModele;
	
	public PanelConsultationEtudiant() {
		lblPseudo = new JLabel();
		lblNomComplet = new JLabel();
		lblSexe = new JLabel();
		lblProgram = new JLabel();
		cours = new JList();
		jscrollPane = new JScrollPane(cours);
		
		cours.setModel(new DefaultListModel());
		cours.setEnabled(false);
		cours.setFont(cours.getFont().deriveFont(Font.BOLD, 13));

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel panelGrid = new JPanel(new GridLayout(4, 2));
		panelGrid.add(createLabelName("Pseudo: "));
		panelGrid.add(lblPseudo);

		panelGrid.add(createLabelName("Prenom et nom: "));
		panelGrid.add(lblNomComplet);

		panelGrid.add(createLabelName("Sexe: "));
		panelGrid.add(lblSexe);

		panelGrid.add(createLabelName("Programme d'étude: "));
		panelGrid.add(lblProgram);

		this.add(panelGrid);
		this.add(jscrollPane);
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
		revalidateData();	
	}
	
	private JLabel createLabelName(String name) {
		JLabel lbl = new JLabel(name);
		lbl.setHorizontalAlignment(JLabel.RIGHT);

		Font font = lbl.getFont();
		font = font.deriveFont(Font.BOLD, 14);

		lbl.setFont(font);
		return lbl;
	}
	
	// Methode a appeler pour faire afficher les infos de l'etudiant connecte 
	private void revalidateData() {
		if(etudiant != null ){
			lblPseudo.setText(etudiant.getPseudo());
			lblNomComplet.setText(etudiant.getPrenom() + " " + etudiant.getNom());

			if (etudiant.getEstHomme()) {
				lblSexe.setText("Homme");
			} else {
				lblSexe.setText("Femme");
			}

			lblProgram.setText(etudiant.getProgramme().getName());

			DefaultListModel model = (DefaultListModel) cours.getModel();
			model.removeAllElements();
			
			for (Cours cours : etudiant.getCours()) {
				model.addElement(cours.getIdentifiant() + " - " + cours.getNom()+"\n");
			}			
			cours.setModel(model);
		}
	}

	public void setModele(EtudiantManager monModele){
		this.monModele = monModele;
	}
	
	/**
	 * On ré-actualise toutes les données (MVC)
	 */
	@Override
	public void update(Observable o, Object arg) {
		revalidateData();
	}	
}
