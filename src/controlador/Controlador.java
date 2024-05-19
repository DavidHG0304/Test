package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Modelo;
import vista.Vista;
import vista.VistaPanelInicio;
import vista.VistaRegistro;
import vista.AccionesListeners.MetodosLog_Reg;


public class Controlador implements ActionListener{
	
	public Vista nuevaVista;
	public VistaRegistro vistaRegistro;
	public VistaPanelInicio panelInicio;
	
	public MetodosLog_Reg metodos;
	
	public Modelo nuevoModelo;
	public ControladorTXT controladorTXT;
	public ControladorRegistrarse controladorRegistro;
	public boolean sesionIniciada;
	public boolean usuarioRegistrado;
	
	// Constructor del controlador donde se inicializan lo que se va a necesitar
	public Controlador()  {
		// Instanciar vistas
		this.nuevaVista = new Vista();
		this.nuevoModelo = new Modelo();
		this.controladorTXT = new ControladorTXT(nuevaVista);
		
		this.metodos = new MetodosLog_Reg();
		
		nuevaVista.asignarActListner(this);
	}
	
	// Metodos para llamar las vistas
	public void login() {
		nuevaVista.login();
	}
	
	public void registro() {
		this.vistaRegistro = new VistaRegistro();
		this.controladorRegistro = new ControladorRegistrarse(vistaRegistro);
		vistaRegistro.asignarActListner(this);
		vistaRegistro.registro();
	}
	
	public void panelPrincipal() {
		this.panelInicio = new VistaPanelInicio();
		panelInicio.asignarActListner(this);
		panelInicio.panelPrincipal();
	}
	
	// Metodo validacion Login
	public void accionLogin() {
		if(metodos.loginValidado(new String (nuevaVista.getTxtContrasenia().getPassword()), nuevaVista.getTxtCorreo(), nuevaVista.getTxtContrasenia())) {
			sesionIniciada = nuevoModelo.accionLogin(nuevaVista.getTxtCorreo().getText(),new String (nuevaVista.getTxtContrasenia().getPassword()));
		}
		metodos.loginNoValido(nuevaVista.getTxtCorreo(), nuevaVista.getTxtContrasenia());
	}	
	
	// Metodo validacion Registro
	public void accionRegistro() {
		if(metodos.registroValido(new String(vistaRegistro.getTxtContrasenia().getPassword()), new String(vistaRegistro.getConfirmarContrasenia().getPassword()),  vistaRegistro.getNombre(), vistaRegistro.getApellidos(), vistaRegistro.getTxtCorreo(), vistaRegistro.getTxtContrasenia(), vistaRegistro.getConfirmarContrasenia())){
			usuarioRegistrado = nuevoModelo.accionRegistro(vistaRegistro.getNombre().getText(), vistaRegistro.getApellidos().getText(), vistaRegistro.getTxtCorreo().getText(), new String(vistaRegistro.getTxtContrasenia().getPassword()), new String(vistaRegistro.getConfirmarContrasenia().getPassword()));
		}
//		metodos.registroNoValido(vistaRegistro.getNombre(), vistaRegistro.getApellidos(), vistaRegistro.getTxtCorreo(), vistaRegistro.getTxtContrasenia(), vistaRegistro.getConfirmarContrasenia());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method s
		switch (e.getActionCommand()) {
		case "Iniciar":
			// System.out.println("Hola");
			accionLogin();
			if (sesionIniciada) {
				nuevaVista.getFrame().dispose();
				panelPrincipal();
			}
			break;
		
		case "Crear Cuenta":
			accionRegistro();
			if(usuarioRegistrado) {
				vistaRegistro.getFrame().dispose();
			}
			
			break;
		case "Registrarse":
			// System.out.println("Registro");
			nuevaVista.getFrame().dispose();
			registro();
			break;
			
		case "Iniciar Sesion":
			// System.out.println("Hola");
			vistaRegistro.getFrame().dispose();
			nuevaVista.getFrame().setVisible(true);
			break;
		
		case "Cerrar Sesión":
			panelInicio.getFrame().dispose();
			nuevaVista.getFrame().setVisible(true);
			break;
		}
	}
}
