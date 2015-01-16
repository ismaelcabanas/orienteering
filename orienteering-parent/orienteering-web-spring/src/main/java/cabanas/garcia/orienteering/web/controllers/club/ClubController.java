package cabanas.garcia.orienteering.web.controllers.club;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cabanas.garcia.orienteering.dtos.club.ClubBusquedaForm;
import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.dtos.club.ClubForm;
import cabanas.garcia.orienteering.servicios.club.api.ClubServicio;
import cabanas.garcia.orienteering.web.util.mensaje.MensajeUsuario;

/**
 * Clase controladora que mapea las peticiones HTTP (path y método request) con métodos de la clase.
 * 
 * @author f009994r
 *
 */
@Controller
public class ClubController {

	public static final String REQUEST_ATTRIBUTE_CLUB_BUSQUEDA_FORM = "clubBusquedaForm";
	
	public static final String VISTA_ADMIN_CLUBS_LISTADO = "admin/clubs/listado";
	public static final String VISTA_ADMIN_CLUBS_NUEVO = "admin/clubs/nuevo";

	public static final String MODEL_ATTRIBUTE_CLUBS = "clubs";
	
	@Resource
	private ClubServicio clubServicio;
	
	public ClubController() {
				
	}

//	/**
//	 * Constructor creado por motivos de test.
//	 * 
//	 * @param clubServicio
//	 * @param clubBusquedaForm 
//	 */
//	public ClubController(ClubServicio clubServicio, ClubBusquedaForm clubBusquedaForm) {
//		this.clubServicio = clubServicio;
//		this.clubBusquedaForm = clubBusquedaForm;
//	}
	
//	@ModelAttribute("clubForm")
//	public ClubForm getClubForm(){
//		return ClubForm.getBuilder().build();
//	}
	
	/**
	 * @param clubServicio
	 */
	public ClubController(ClubServicio clubServicio) {
		this.clubServicio = clubServicio;
	}

	@RequestMapping(value=ClubControllerPaths.LISTADO, method=RequestMethod.GET)
	public String getClubs(){
		
		return VISTA_ADMIN_CLUBS_LISTADO;
		
	}
	
	@RequestMapping(value=ClubControllerPaths.NUEVO, method=RequestMethod.GET)	
	public String nuevoClub(Model model){
		
		// establezco el objeto clubForm en la request
		model.addAttribute("clubForm", ClubForm.getBuilder().build());
		
		return VISTA_ADMIN_CLUBS_NUEVO;
		
	}
	
	@RequestMapping(value=ClubControllerPaths.ALTA, method=RequestMethod.POST)
	public String crearClub(ClubForm clubForm, Model model){
		
		// el servicio da de alta el club
		clubServicio.alta(clubForm);
		
//		// el servicio recupera el listado de clubs
//		Collection<ClubDto> listadoClubs = clubServicio.buscar(clubBusquedaForm);
		
		// se establece el mensaje de usuario en la request
		model.addAttribute("mensaje", new MensajeUsuario("texto.accion.crear"));
		
//		// establezco el listado de clubs 
//		model.addAttribute("listado", listadoClubs);
		
		// devuelvo la vista de listado de clubs
		return VISTA_ADMIN_CLUBS_LISTADO;
		
	}
		
	@RequestMapping(value=ClubControllerPaths.CANCELAR, method=RequestMethod.GET)
	public String cancelar(Model model){
		
		// se establece el mensaje de usuario en la request
		model.addAttribute("mensaje", new MensajeUsuario("texto.accion.cancelar"));
		
		return VISTA_ADMIN_CLUBS_LISTADO;
		
	}
	
	/**
	 * 
	 * Si anotamos el primer parámetro del método buscar() con @ModelAttribute(REQUEST_ATTRIBUTE_CLUB_BUSQUEDA_FORM) tendremos
	 * el objeto disponible en la request (y tengo que investigar si en algún scope más)
	 * 
	 * @param clubFormBusqueda
	 * @param model
	 * @return
	 */
	@RequestMapping(value=ClubControllerPaths.BUSCAR, method=RequestMethod.POST)
	public String buscar(ClubBusquedaForm clubFormBusqueda, 
			Model model){
		
		// creo la lista que contendrá los clubs resultados de la búsqueda
		Collection<ClubDto> clubs = clubServicio.buscar(clubFormBusqueda);
		
		// añado la lista de clubs como atributo de la request
		model.addAttribute(MODEL_ATTRIBUTE_CLUBS, clubs);
		
		// devuelvo la vista de listado
		return VISTA_ADMIN_CLUBS_LISTADO;
	}
	
}
