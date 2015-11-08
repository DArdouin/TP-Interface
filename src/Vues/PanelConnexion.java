package Vues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controlleurs.ControlleurConnexion;
import Modèles.EtudiantManager;

public class PanelConnexion extends JPanel implements Observer{
	private static final long serialVersionUID = 1L;
	private static final int TAILLE_DES_TEXTFIELD = 10;
	private JLabel titleLabel;
	private JLabel pseudonymLabel;
	private JLabel passwordLabel;
	private JTextField pseudonymTextField;
	private JPasswordField passwordField;
	private JButton cancelButton;
	private JButton connectionButton;
	private ControlleurConnexion monControlleur;
	private EtudiantManager monModele;

	/**
	 * Create the panel.
	 */
	public PanelConnexion() {
		super(new BorderLayout());
		
		//Titre de l'interface
		titleLabel= new JLabel("Connexion");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 22.0f));
		this.add(titleLabel, BorderLayout.NORTH);
		
		//Centre de l'interface
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout( new GridBagLayout());

		pseudonymLabel = new JLabel("Pseudo:");
		pseudonymLabel.setHorizontalAlignment(JLabel.RIGHT);
		GridBagConstraints gbc_PseudonymLabel = new GridBagConstraints();
		gbc_PseudonymLabel.fill = GridBagConstraints.BOTH;
		gbc_PseudonymLabel.gridx = 0;
		gbc_PseudonymLabel.gridy = 0;
		panelCenter.add(pseudonymLabel, gbc_PseudonymLabel);

		pseudonymTextField = new JTextField("", TAILLE_DES_TEXTFIELD);
		pseudonymTextField.setText("truj1234");
		GridBagConstraints gbc_PseudonymTextField = new GridBagConstraints();
		gbc_PseudonymTextField.gridx = 1;
		gbc_PseudonymTextField.gridy = 0;
		panelCenter.add(pseudonymTextField, gbc_PseudonymTextField);
		
		passwordLabel = new JLabel("Mot de passe:");
		passwordLabel.setHorizontalAlignment(JLabel.RIGHT);
		GridBagConstraints gbc_PasswordLabel = new GridBagConstraints();
		gbc_PasswordLabel.anchor = GridBagConstraints.EAST;
		gbc_PasswordLabel.gridx = 0;
		gbc_PasswordLabel.gridy = 1;
		panelCenter.add(passwordLabel, gbc_PasswordLabel);
		
		passwordField = new JPasswordField(TAILLE_DES_TEXTFIELD);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		panelCenter.add(passwordField, gbc_passwordField);
		passwordField.setText("");		
		panelCenter.add(passwordField, gbc_passwordField);
		
		this.add(panelCenter, BorderLayout.CENTER);
		
		//Sud de l'interface
		JPanel panelActions = new JPanel();
		panelActions.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		cancelButton = new JButton("Annuler");	
		cancelButton.setFont(cancelButton.getFont().deriveFont(Font.PLAIN));		
		cancelButton.addActionListener(monControlleur);
		cancelButton.setActionCommand("Cancel");
		
		connectionButton = new JButton("Connexion");		
		connectionButton.addActionListener(monControlleur);
		connectionButton.setActionCommand("Connexion");
		
		panelActions.add(cancelButton);
		panelActions.add(connectionButton);
			
		this.add(panelActions, BorderLayout.SOUTH);
	}
	
	public String getPseudo(){
		return pseudonymTextField.getText();
	}
	
	public String getPassword(){
		return new String(passwordField.getPassword());
	}
	
	public void setControlleur(ControlleurConnexion c){
		this.monControlleur = c;
	}
	
	public void setModele(EtudiantManager m){
		this.monModele = m;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//Pas d'affichage de données, on update donc rien
	}
}
