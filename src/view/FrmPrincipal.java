package view;

import controller.Controller;
import model.Persona;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.List;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FrmPrincipal extends JFrame {

	private JPanel contentPane;
	public static JTextField txtName;
	public static JTextField txtNumber;
	private JButton btnOpenAgenda;
	private Controller appCtrl;
	public static List listaNombres;
	private JButton btnGuardar;
	private JButton btnEditar;
	private JButton btnSaveAgenda;
	public static JLabel lblNumero;
	private ArrayList<Persona> listaPersonas;
	private File agendaFile;


	/**
	 * Create the frame.
	 */
	public FrmPrincipal() {
		appCtrl = new Controller();
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("assets/telefono.png"));
		setTitle("JAgenda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 271, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listaNombres = new List();
		listaNombres.setBounds(10, 10, 110, 212);
		contentPane.add(listaNombres);
		listaNombres.addItemListener(l -> {
			appCtrl.loadPersona(listaNombres.getSelectedItem(), listaPersonas);
		});
		
		btnOpenAgenda = new JButton("Abrir Agenda");
		btnOpenAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agendaFile = appCtrl.getFile();
				listaPersonas = appCtrl.loadFile(agendaFile);
			}
		});
		btnOpenAgenda.setBounds(10, 228, 110, 23);
		contentPane.add(btnOpenAgenda);
		
		JPanel panel = new JPanel();
		panel.setBounds(126, 10, 119, 114);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblNumero = new JLabel("");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(lblNumero);
		
		txtName = new JTextField();
		panel.add(txtName);
		txtName.setColumns(1);
		
		txtNumber = new JTextField();
		panel.add(txtNumber);
		txtNumber.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(126, 135, 119, 81);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaPersonas = appCtrl.savePersona(agendaFile, listaPersonas);
			}
		});
		panel_1.add(btnGuardar);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaPersonas = appCtrl.editPerson(listaPersonas);
			}
		});
		panel_1.add(btnEditar);
		
		btnSaveAgenda = new JButton("Guardar Agenda");
		btnSaveAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agendaFile = appCtrl.guardarConfig(agendaFile, listaPersonas);
			}
		});
		btnSaveAgenda.setBounds(130, 227, 115, 23);
		contentPane.add(btnSaveAgenda);
		setVisible(true);
	}
}
