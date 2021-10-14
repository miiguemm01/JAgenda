package controller;

import java.io.*;

import javax.swing.JFileChooser;
import view.FrmPrincipal;
import model.Persona;
import java.util.ArrayList;

public class Controller {
	
	public File getFile() {
		File selectedFile = null;
		JFileChooser jChooser = new JFileChooser();
		jChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int fileSelectButton = jChooser.showOpenDialog(null);
		if(fileSelectButton != JFileChooser.CANCEL_OPTION) {
			selectedFile = jChooser.getSelectedFile();
		}
		return selectedFile;
	}
	
	public ArrayList<Persona> loadFile(File configFile) {
		ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
		try {
			Persona loadedPersona;
			BufferedReader fileReader = new BufferedReader(new FileReader(configFile));
			String linea = fileReader.readLine();
			String[] dataPersona;
			while(linea != null) {
				dataPersona = linea.split("#");
				loadedPersona = new Persona(dataPersona[0], dataPersona[1]);
				listaPersonas.add(loadedPersona);
				FrmPrincipal.listaNombres.add(loadedPersona.nombrePersona);
				linea = fileReader.readLine();
			}
			fileReader.close();
		}catch(Exception e) {
			FrmPrincipal.listaNombres.add("Error");
			listaPersonas = null;
		}
		
		return listaPersonas;
	}
	
	public void loadPersona(String nombrePersona, ArrayList<Persona> listaPersonas){
		try {
			for(int i = 0; i < listaPersonas.size(); i++) {
				if(listaPersonas.get(i).nombrePersona.equals(nombrePersona)) {
					FrmPrincipal.lblNumero.setText(listaPersonas.get(i).telefonoPersona);
				}
			}
		}catch(Exception e) {
			FrmPrincipal.lblNumero.setText("ERROR");
		}
		
	}
	
	public ArrayList<Persona> savePersona(File configFile, ArrayList<Persona> listaPersonas) {
		try {
			boolean existe = false;
			String nombre, numero;
			nombre = FrmPrincipal.txtName.getText().toString();
			numero = FrmPrincipal.txtNumber.getText().toString();
			for(int i = 0; i < listaPersonas.size() && !existe; i++) {
				if(!listaPersonas.get(i).nombrePersona.equals(nombre)) {
					existe = true;
				}
			}
			if(!existe) {
				FrmPrincipal.listaNombres.add(nombre);
				listaPersonas.add(new Persona(nombre, numero));
			}else {
				FrmPrincipal.lblNumero.setText("Ya existe");
			}
		}catch(Exception e) {
			FrmPrincipal.lblNumero.setText("Error guardando");
		}
		return listaPersonas;
	}
	
	public File guardarConfig(File configFile, ArrayList<Persona> listaPersonas) {
		try {
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(configFile));
			for(int i = 0; i < listaPersonas.size(); i++) {
				if(i != listaPersonas.size() - 1) {
					fileWriter.write(listaPersonas.get(i).nombrePersona + "#" + listaPersonas.get(i).telefonoPersona + "\n");
				}else {
					fileWriter.write(listaPersonas.get(i).nombrePersona + "#" + listaPersonas.get(i).telefonoPersona);
				}
				
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			FrmPrincipal.lblNumero.setText("Error");
		}
		return configFile;
	}
	
	
	
	public ArrayList<Persona> editPerson(ArrayList<Persona> listaPersonas) {
		try {
			String personName = FrmPrincipal.listaNombres.getSelectedItem();
			for(int i = 0; i < listaPersonas.size(); i++) {
				if(personName.equals(listaPersonas.get(i).nombrePersona)) {
					if(FrmPrincipal.txtName.getText().toString().equals("") || FrmPrincipal.txtNumber.getText().toString().equals("")) {
						FrmPrincipal.lblNumero.setText("ERROR");
					}else {
						FrmPrincipal.listaNombres.remove(listaPersonas.get(i).nombrePersona);
						FrmPrincipal.lblNumero.setText("");
						listaPersonas.get(i).nombrePersona = FrmPrincipal.txtName.getText().toString();
						listaPersonas.get(i).telefonoPersona = FrmPrincipal.txtNumber.getText().toString();
						FrmPrincipal.listaNombres.add(listaPersonas.get(i).nombrePersona);
						
					}
				}
			}
		}catch(Exception e) {
			FrmPrincipal.lblNumero.setText("ERROR");
		}
		return listaPersonas;
	}
}
