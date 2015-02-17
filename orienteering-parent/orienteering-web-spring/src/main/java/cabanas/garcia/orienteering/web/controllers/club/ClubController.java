package cabanas.garcia.orienteering.web.controllers.club;

import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cabanas.garcia.orienteering.dtos.club.ClubBusquedaForm;
import cabanas.garcia.orienteering.dtos.club.ClubDto;
import cabanas.garcia.orienteering.dtos.club.ClubForm;
import cabanas.garcia.orienteering.servicios.club.api.ClubServicio;
import cabanas.garcia.orienteering.servicios.club.exceptions.ClubNoExisteException;
import cabanas.garcia.orienteering.web.controllers.RequestMappings;
import cabanas.garcia.orienteering.web.util.mensaje.MensajeUsuario;

/**
 * Clase controladora que mapea las peticiones HTTP (path y método request) con métodos de la clase.
 * 
 * @author f009994r
 *
 */
@Controller
@RequestMapping(value=RequestMappings.REQUEST_MAPPING_CLUB_ADMIN)
public class ClubController {

	/* Definición de vistas */
	public static final String VISTA_ADMIN_CLUBS_LISTADO = "admin/clubs/listado";
	public static final String VISTA_ADMIN_CLUBS_NUEVO = "admin/clubs/nuevo";
	public static final String VISTA_ADMIN_CLUBS_EDICION = "admin/clubs/edicion";
	public static final String VISTA_ADMIN_CLUBS_ACTUALIZAR = "admin/clubs/actualizar";
	public static final String VISTA_ADMIN_CLUBS_DETALLE = "admin/clubs/detalle";
	
	public static final String REQUEST_ATTRIBUTE_CLUBS = "clubs";

	/* Constantes de atributos de request */
	public static final String REQUEST_ATTRIBUTE_MENSAJE = "mensaje";
	public static final String REQUEST_ATTRIBUTE_CLUB_FORM = "clubForm";
	public static final String REQUEST_ATTRIBUTE_CLUB_BUSQUEDA_FORM = "clubBusquedaForm";
	public static final String REQUEST_ATTRIBUTE_CLUB_DTO = "clubDto";
	
	public static final String URL_PARAM_ID_CLUB = "id";	
	
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

	/**
	 * Método que gestiona la petición de listado
	 * @return
	 * 		La vista a la que se redigirá al usuario
	 */
	@RequestMapping(value=RequestMappings.REQUEST_MAPPING_LIST, method=RequestMethod.GET)
	public String getClubs(){
		
		return VISTA_ADMIN_CLUBS_LISTADO;
		
	}
	
	/**
	 * Método que gestiona la petición de nuevo club.
	 * 
	 * @param model
	 * @return
	 * 		La vista a la que se redigirá al usuario
	 */
	@RequestMapping(value=RequestMappings.REQUEST_MAPPING_NEW, method=RequestMethod.GET)	
	public String nuevo(HttpServletRequest request){
		
		// establezco el formulario en la request
		request.setAttribute(REQUEST_ATTRIBUTE_CLUB_FORM, ClubForm.getBuilder().build());
		
		return VISTA_ADMIN_CLUBS_NUEVO;
		
	}
	
	/**
	 * Método que gestiona la petición de alta de un club.
	 * 
	 * También establece en la request un mensaje informativo.
	 * 
	 * @param clubForm
	 * @param model
	 * @return
	 * 		La vista a la que se redigirá al usuario
	 */
	@RequestMapping(value=RequestMappings.REQUEST_MAPPING_ADD, method=RequestMethod.POST)
	public String alta(ClubForm clubForm, HttpServletRequest request){
		
		// el servicio da de alta el club
		clubServicio.alta(clubForm);
		
//		// el servicio recupera el listado de clubs
//		Collection<ClubDto> listadoClubs = clubServicio.buscar(clubBusquedaForm);
		
		// se establece el mensaje de usuario en la request
		request.setAttribute(REQUEST_ATTRIBUTE_MENSAJE, new MensajeUsuario("texto.accion.alta"));
		
//		// establezco el listado de clubs 
//		model.addAttribute("listado", listadoClubs);
		
		// devuelvo la vista de listado de clubs
		return VISTA_ADMIN_CLUBS_LISTADO;
		
	}
		
	/**
	 * Método que gestiona la cancelación de una acción.
	 * 
	 * Establece en la request un mensaje informativo.
	 * 
	 * @param model
	 * @return
	 * 		La vista a la que se redigirá al usuario
	 */
	@RequestMapping(value=RequestMappings.REQUEST_MAPPING_CANCEL, method=RequestMethod.GET)
	public String cancelar(HttpServletRequest request){
		
		// se establece el mensaje de usuario en la request
		request.setAttribute(REQUEST_ATTRIBUTE_MENSAJE, new MensajeUsuario("texto.accion.cancelar"));
		
		return VISTA_ADMIN_CLUBS_LISTADO;
		
	}
	
	/**
	 * Método que gestiona la petición de búsqueda de clubs.
	 * 
	 * Si anotamos el primer parámetro del método buscar() con @ModelAttribute(REQUEST_ATTRIBUTE_CLUB_BUSQUEDA_FORM) tendremos
	 * el objeto disponible en la request (y tengo que investigar si en algún scope más)
	 * 
	 * @param clubFormBusqueda
	 * @param model
	 * @return
	 */
	@RequestMapping(value=RequestMappings.REQUEST_MAPPING_SEARCH, method=RequestMethod.POST)
	public String buscar(ClubBusquedaForm clubFormBusqueda, 
			HttpServletRequest request){
		
		// creo la lista que contendrá los clubs resultados de la búsqueda
		Collection<ClubDto> clubs = clubServicio.buscar(clubFormBusqueda);
		
		// si la consulta ha devuelto resultados, se establece en la request el listado de clubs resultado de 
		// la consulta; en caso contrario, se establece un mensaje informativo al usuario
		if(clubs == null || clubs.size() == 0){
			request.setAttribute(REQUEST_ATTRIBUTE_MENSAJE, new MensajeUsuario("texto.accion.buscar.sinresultado"));
		}
		else
			request.setAttribute(REQUEST_ATTRIBUTE_CLUBS, clubs);
		
		// devuelvo la vista de listado
		return VISTA_ADMIN_CLUBS_LISTADO;
	}

	/**
	 * Método que gestiona la petición de edición de un club.
	 * 
	 * @param idClubSeleccionado
	 * 		El identificador del club
	 * @param request 
	 * 		El objeto request
	 * @return
	 * 		La vista a la que se redigirá al usuario
	 */
	@RequestMapping(value=RequestMappings.REQUEST_MAPPING_EDIT, method=RequestMethod.GET)
	public String edicion(@RequestParam(value=URL_PARAM_ID_CLUB) Long idClubSeleccionado, HttpServletRequest request) {
		
		// recupero los datos del club seleccionado
		ClubDto clubDtoSeleccionado = null;
		try {
			clubDtoSeleccionado = clubServicio.buscarPorId(idClubSeleccionado);
		} catch (ClubNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// establezco la instancia ClubForm con la información del club seleccionado
		ClubForm clubForm = ClubForm.getBuilder()
				.conId(clubDtoSeleccionado.getId())
				.conNombre(clubDtoSeleccionado.getNombre())
				.build();
		
		// establezco en la request los datos del club seleccionado
		request.setAttribute(REQUEST_ATTRIBUTE_CLUB_FORM, clubForm );
		
		return VISTA_ADMIN_CLUBS_EDICION;
	}

	/**
	 * Método que gestiona la petición de actualizar la información de un club.
	 * 
	 * También establece en la request una mensaje informativo.
	 * 
	 * @param clubForm
	 * 		La información del club a actualizar
	 * @param request 
	 * @return
	 * 		La vista a la que se redigirá al usuario
	 */
	@RequestMapping(value=RequestMappings.REQUEST_MAPPING_MODIFY, method=RequestMethod.POST)
	public String actualizar(ClubForm clubForm, HttpServletRequest request) {

		// actualizo el club con la nueva informació	n proporcionada por la instancia ClubForm
		clubServicio.actualiza(clubForm);
		
		// establezco en la request el mensaje del usuario
		request.setAttribute(REQUEST_ATTRIBUTE_MENSAJE, new MensajeUsuario("texto.accion.actualizar"));
		
		// devuelvo la vista de listado de clubs
		return VISTA_ADMIN_CLUBS_LISTADO;
		
	}

	/**
	 * Método que gestiona la petición de detalle de un club.
	 * 
	 * @param idClubSeleccionado
	 * @param request
	 * @return
	 */
	@RequestMapping(value=RequestMappings.REQUEST_MAPPING_DETAIL, method=RequestMethod.GET)
	public String detalle(@RequestParam(value=URL_PARAM_ID_CLUB) Long idClubSeleccionado,
			HttpServletRequest request) {

		// recupero los datos del club seleccionado
		ClubDto clubDtoSeleccionado = null;
		try {
			clubDtoSeleccionado = clubServicio.buscarPorId(idClubSeleccionado);
		} catch (ClubNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		// establezco en la request la instancia del club recuperado
		request.setAttribute(REQUEST_ATTRIBUTE_CLUB_DTO, clubDtoSeleccionado);
		
		// devuelvo la vista de detalle del club
		return VISTA_ADMIN_CLUBS_DETALLE;
		
	}

	/**	
	 * Método que gestiona la petición al volver de la vista de detalle de club.
	 *
	 * @return
	 */
	@RequestMapping(value=RequestMappings.REQUEST_MAPPING_BACK, method=RequestMethod.GET)
	public String volver() {

		return VISTA_ADMIN_CLUBS_LISTADO;
		
	}
	
	/**
	 * Método que gestiona la petición de baja de club
	 * @param idClubSeleccionado 
	 * @param request 
	 * 
	 * @return
	 */
	@RequestMapping(value=RequestMappings.REQUEST_MAPPING_DELETE, method=RequestMethod.GET)
	public String baja(@RequestParam(value=URL_PARAM_ID_CLUB) Long idClubSeleccionado, HttpServletRequest request) {
		
		// doy de baja al club
		clubServicio.baja(idClubSeleccionado);
		
		// si la baja se efectuó, establezco en la request un mensaje informativo
		request.setAttribute(REQUEST_ATTRIBUTE_MENSAJE, new MensajeUsuario("texto.accion.baja"));
		
		// devuelvo la vista de baja
		return VISTA_ADMIN_CLUBS_LISTADO;
		
	}
	
}
