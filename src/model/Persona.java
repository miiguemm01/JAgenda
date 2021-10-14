package model;

public class Persona {
	public String nombrePersona;
	public String telefonoPersona;
	
	public Persona() {
		
	}
	
	public Persona(String nombre, String telefono) {
		this.nombrePersona = nombre;
		this.telefonoPersona = telefono;
	}
	
	public String toString() {
		return "Nombre: " + this.nombrePersona + "\nTeléfono: " + this.telefonoPersona;
	}
	
	
}
