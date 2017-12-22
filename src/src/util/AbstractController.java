package util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Classe que implementa m�todos comuns e �teis aos MBeans. Portanto,
 * deve ser estendido por eles.
 *  
 * @author Kerolayne e Edilson
 */
@SuppressWarnings("serial")
public class AbstractController implements Serializable {
	
	/** Adiciona uma mensagem de informa��o a ser exibida para o usu�rio. */
	protected void addMsgInfo(String msg){
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	/** Adiciona uma mensagem de aviso a ser exibida para o usu�rio. */
	protected void addMsgWarning(String msg){
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	/** Adiciona uma mensagem de erro a ser exibida para o usu�rio. */
	protected void addMsgError(String msg){
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	
	/** Transforma uma exce��o em String, para torn�-la leg�vel a humanos. */
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
	 * Possibilita o acesso ao HttpServletRequest, ou seja, � requisi��o de acesso
	 * � p�gina atual.
	 */
	public HttpServletRequest getCurrentRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	/**
	 * Possibilita o acesso ao HttpServletResponse, ou seja, � resposta a ser enviada
	 * � requisi��o de acesso � p�gina atual.
	 */
	public HttpServletResponse getCurrentResponse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}

	/**
	 * Possibilita o acesso ao HttpSession, ou seja, � sess�o do sistema,
	 * local da mem�ria onde ficam armazenadas as informa��es atuais.
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
