package util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Classe que implementa métodos comuns e úteis aos MBeans. Portanto,
 * deve ser estendido por eles.
 *  
 * @author Kerolayne e Edilson
 */
@SuppressWarnings("serial")
public class AbstractController implements Serializable {
	
	/** Adiciona uma mensagem de informação a ser exibida para o usuário. */
	protected void addMsgInfo(String msg){
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	/** Adiciona uma mensagem de aviso a ser exibida para o usuário. */
	protected void addMsgWarning(String msg){
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	/** Adiciona uma mensagem de erro a ser exibida para o usuário. */
	protected void addMsgError(String msg){
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	
	/** Transforma uma exceção em String, para torná-la legível a humanos. */
	private static String getStackTrace(Throwable t){
		String msg = t.toString() + "<br/>";
		
		if (t.getStackTrace() != null){
			for (StackTraceElement s : t.getStackTrace()){
				msg += s.toString() + "<br/>";
			}
		}
		
		if (t.getCause() != null){
			msg += "Caused by: " + t.getCause().toString() + "<br/>";
			
			if (t.getCause().getStackTrace() != null){
				for (StackTraceElement s : t.getCause().getStackTrace()){
					msg += s.toString() + "<br/>";
				}
			}
		}
		
		
		return msg;
	}
	
	
	/**
	 * Possibilita o acesso ao HttpServletRequest, ou seja, à requisição de acesso
	 * à página atual.
	 */
	public HttpServletRequest getCurrentRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	/**
	 * Possibilita o acesso ao HttpServletResponse, ou seja, à resposta a ser enviada
	 * à requisição de acesso à página atual.
	 */
	public HttpServletResponse getCurrentResponse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}

	/**
	 * Possibilita o acesso ao HttpSession, ou seja, à sessão do sistema,
	 * local da memória onde ficam armazenadas as informações atuais.
	 */
	public HttpSession getCurrentSession() {
		return getCurrentRequest().getSession(true);
	}
	
	/**
	 * Acessa o external context do JavaServer Faces
	 **/
	private ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	
}
