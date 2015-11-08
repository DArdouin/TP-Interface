package Vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Modèles.Cours;
import Modèles.Etudiant;
import Modèles.EtudiantManager;
import Modèles.Programme;

public class PanelModificationEtudiant extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;

	private Etudiant etudiant;
	private JTextField prenomTextField;
	private JTextField nomTextField;
	private JTextField pseudoTextField;
	private JPasswordField passwordField;
	private JRadioButton manRadioButton;
	private JRadioButton womanRadioButton;
	private JComboBox programmeComboBox;
	private HashMap<Cours, JCheckBox> coursChckbx;
	private JButton cancelButton;
	private JButton saveButton;
	private PanelTabbed panelTabbed;
	private JPanel panelIdentification;
	private JLabel pseudoLabel;
	private JLabel passwordLabel;
	private JPanel informationPanel;
	private JPanel panelProgramme;
	private JLabel lblProgramme;
	private JPanel panelCours;
	private JPanel panelConnexion;
	private ButtonGroup groupSexe;
	private JPanel panelEtude;
	private JPanel panelSexe;
	private JLabel prenomLabel;
	private JLabel nomLabel;
	private JPanel nomPrenomPanel;
	private EtudiantManager monModele;
	
	public PanelModificationEtudiant() {
		// TODO ajouter du code pour construire la vue
		
		//Gestion de la présentation du panel concernant la modification des données relatives à l'étudiant connecté
		setLayout(new GridBagLayout());

		panelIdentification = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelIdentification.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelIdentification.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Identification", TitledBorder.LEADING, TitledBorder.TOP, null, null));		
		
		//Ajout des composants graphiques concernant le pseudonyme et le mot de passe de l'étudiant connecté
		pseudoLabel = new JLabel("Pseudo:");
		panelIdentification.add(pseudoLabel);

		pseudoTextField = new JTextField();
		pseudoTextField.setEditable(false);
		panelIdentification.add(pseudoTextField);
		pseudoTextField.setColumns(10);

		passwordLabel = new JLabel("Mot de passe:");
		panelIdentification.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		panelIdentification.add(passwordField);
		
		//Gestion de la présentation du panel concernant  le pseudonyme et le mot de passe de l'étudiant connecté
		GridBagConstraints gbc_panelIdentification = new GridBagConstraints();
		gbc_panelIdentification.fill = GridBagConstraints.BOTH;
		gbc_panelIdentification.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc_panelIdentification.gridx = 0;
		gbc_panelIdentification.gridy = 0;
		gbc_panelIdentification.weightx = 1;
		gbc_panelIdentification.weighty = 1;
		this.add(panelIdentification, gbc_panelIdentification);
		
		
		//Implémentation d'une bordure du panel englobant les composants graphiques concernant le nom, prénom et le sexe de l'étudiant connecté
		informationPanel = new JPanel();
		informationPanel.setLayout(new GridBagLayout());
		
		informationPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Information", TitledBorder.LEADING,TitledBorder.TOP, null, null));
		
		//Ajout des composants graphiques concernant le nom, prénom de l'étudiant connecté
		nomPrenomPanel = new JPanel();
		
		prenomLabel = new JLabel("Prénom: ");
		nomPrenomPanel.add(prenomLabel);
		prenomTextField = new JTextField(10);
		nomPrenomPanel.add(prenomTextField);
		
		nomLabel = new JLabel("Nom: ");
		nomPrenomPanel.add(nomLabel);
		nomTextField = new JTextField(10);
		nomPrenomPanel.add(nomTextField);
		
		//Gestion de la présentation des composants graphiques concernant le nom, prénom de l'étudiant connecté
		GridBagConstraints gbc_panelNom = new GridBagConstraints();
		gbc_panelNom.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc_panelNom.gridx = 0;
		gbc_panelNom.gridy = 0;
		gbc_panelNom.weightx = 1;
		gbc_panelNom.weighty = 1;
		informationPanel.add(nomPrenomPanel, gbc_panelNom);
	
		//Ajout des composants graphiques concernant le sexe de l'étudiant connecté
		panelSexe = new JPanel();
		FlowLayout flowLayout_panelSexe = (FlowLayout) panelSexe.getLayout();
		flowLayout_panelSexe.setAlignment(FlowLayout.LEFT);
		manRadioButton = new JRadioButton("Homme");
		panelSexe.add(manRadioButton);

		womanRadioButton = new JRadioButton("Femme");
		panelSexe.add(womanRadioButton);

		groupSexe = new ButtonGroup();
		groupSexe.add(manRadioButton);
		groupSexe.add(womanRadioButton);
		
		//Gestion de la présentation des composants graphiques concernant le sexe de l'étudiant connecté
		GridBagConstraints gbc_panelSexe = new GridBagConstraints();
		gbc_panelSexe.fill = GridBagConstraints.BOTH;
		gbc_panelSexe.gridx = 0;
		gbc_panelSexe.gridy = 1;
		gbc_panelSexe.weightx = 1;
		gbc_panelSexe.weighty = 1;
		informationPanel.add(panelSexe, gbc_panelSexe);

		
		//Gestion de la présentation des panels concernant le nom,prénom et le sexe de l'étudiant connecté
		GridBagConstraints gbc_nomPrenomSexePanel = new GridBagConstraints();
		gbc_nomPrenomSexePanel.fill = GridBagConstraints.BOTH;
		gbc_nomPrenomSexePanel.gridx = 0;
		gbc_nomPrenomSexePanel.gridy = 1;
		gbc_nomPrenomSexePanel.weightx = 1;
		gbc_nomPrenomSexePanel.weighty = 1;
		this.add(informationPanel, gbc_nomPrenomSexePanel);
		
		//Implémentation d'une bordure du panel englobant les panels concernant le programme d'étude et les cours de l'étudiant connecté
		panelEtude = new JPanel();		
		panelEtude.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Étude", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEtude.setLayout(new GridBagLayout());
		GridBagConstraints gbc_panelEtude = new GridBagConstraints();
		gbc_panelEtude.fill = GridBagConstraints.BOTH;		
		gbc_panelEtude.gridx = 0;
		gbc_panelEtude.gridy = 2;
		this.add(panelEtude, gbc_panelEtude);	
		
		//Ajout des composants graphiques concernant le programme de l'étudiant connecté
		panelProgramme = new JPanel();
		FlowLayout fl_panelProgramme = (FlowLayout) panelProgramme.getLayout();
		fl_panelProgramme.setAlignment(FlowLayout.LEFT);
		lblProgramme = new JLabel("Programme:");
		panelProgramme.add(lblProgramme);
		programmeComboBox = new JComboBox();
		
		// Inscription des programmes existants en informatique
		for (Programme prog : monModele.getProgrammes()) {
			programmeComboBox.addItem(prog.getName());
		}

		panelProgramme.add(programmeComboBox);
		GridBagConstraints gbc_panelProgramme = new GridBagConstraints();
		gbc_panelProgramme.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelProgramme.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc_panelProgramme.gridx = 0;
		gbc_panelProgramme.gridy = 0;
		gbc_panelProgramme.weightx = 1;
		gbc_panelProgramme.weighty = 1;
		panelEtude.add(panelProgramme, gbc_panelProgramme);
		
		//Ajout des composants graphiques concernant les cours de l'étudiant connecté
		panelCours = new JPanel(new BorderLayout());
		panelCours.setBorder(new TitledBorder(null, "Cours", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panelCours1 = new JPanel();
		panelCours.add(panelCours1, BorderLayout.NORTH);

		JPanel panelCours2 = new JPanel();
		panelCours.add(panelCours2, BorderLayout.SOUTH);

		coursChckbx = new HashMap<Cours, JCheckBox>();

		ArrayList<Cours> allCours = monModele.getCours();
		int demi = (allCours.size() / 2) + 1;
		
		
		// Construction de l'encart affichant les checkboxes contenant les identifiant des cours (ext: IFT209, IFT215..) 
		for (int i = 0; i < demi; ++i) {
			Cours current = allCours.get(i);

			JCheckBox chckbxCour = new JCheckBox(current.getIdentifiant());
			coursChckbx.put(current, chckbxCour);
			panelCours1.add(chckbxCour);
		}

		for (int i = demi; i < allCours.size(); ++i) {
			Cours current = allCours.get(i);

			JCheckBox chckbxCour = new JCheckBox(current.getIdentifiant());
			coursChckbx.put(current, chckbxCour);
			panelCours2.add(chckbxCour);
		}
		
		//Gestion de la présentation du panel concernant les cours de l'étudiant connecté à l'intérieur du panel parent
		GridBagConstraints gbc_panelCours = new GridBagConstraints();
		gbc_panelCours.fill = GridBagConstraints.BOTH;
		gbc_panelCours.gridx = 0;
		gbc_panelCours.gridy = 1;
		panelEtude.add(panelCours, gbc_panelCours);

		//Implémentation du panel concernant l'annulation ou la sauvegarde de la modification des données de l'étudiant connecté
		panelConnexion = new JPanel();
		panelConnexion.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		cancelButton = new JButton("Annuler");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}	
		});
		
		saveButton = new JButton("Sauvegarder");	
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		panelConnexion.add(cancelButton);
		panelConnexion.add(saveButton);
		
		GridBagConstraints gbc_panelCommand = new GridBagConstraints();
		gbc_panelCommand.fill = GridBagConstraints.BOTH;
		gbc_panelCommand.gridx = 0;
		gbc_panelCommand.gridy = 3;
		gbc_panelCommand.weightx = 1;
		gbc_panelCommand.weighty = 1;
		
		this.add(panelConnexion,gbc_panelCommand);
	}

	public void setEtudiant(Etudiant connectedEtudiant) {
		if(connectedEtudiant != null ){
			pseudoTextField.setText(connectedEtudiant.getPseudo());
			prenomTextField.setText(connectedEtudiant.getPrenom());
			nomTextField.setText(connectedEtudiant.getNom());
			
			womanRadioButton.setSelected(!connectedEtudiant.getEstHomme());
			manRadioButton.setSelected(connectedEtudiant.getEstHomme());
			
			programmeComboBox.setSelectedItem(connectedEtudiant.getProgramme());

			// Inscription des cours existants de l'étudiant
			for (Entry<Cours, JCheckBox> chck : coursChckbx.entrySet()) {				
				chck.getValue().setSelected(connectedEtudiant.getCours().contains(chck.getKey()));
			}
		}
		
	}
	public void cancel() {
		// Do something
	}
	
	public void save(){
		// Sauvegarde des informations de l'étudiant
		JOptionPane.showMessageDialog(null,
			    "La sauvegarde des données n'a pas été implémentée.",
			    "Erreur de sauvegarde",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public void setPanelTabbed(PanelTabbed panelTabbed) {
		this.panelTabbed = panelTabbed;		
	}
	
	public void setModele(EtudiantManager monModele){
		this.monModele = monModele;
	}

	@Override
	public void update(Observable o, Object arg) {
		//Pas d'affichage de données, on update donc rien		
	}	

}
