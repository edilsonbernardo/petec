package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

import util.AbstractController;

/**
 * Classe que controla a comunica��o com o banco de dados, 
 * inclusive o gerenciamento de entidades (EntityManager).
 * Esta classe � um singleton, ou seja, s� pode existir
 * uma ocorr�ncia (objeto) dela em todo o sistema.
 * 
 * @author Kerolayne e Edilson
 */
public class Database extends AbstractController{
	
	/** */ 
	private static Database singleton = new Database();
	protected static EntityManager em;

	public Database() {
		criarEM();
	}
	
	private void criarEM(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("petec-pu"); //nome que est� no arquivo persistence.xml
		em = emf.createEntityManager();
	}

	public static Database getInstance() {
		return singleton;
	}

	public EntityManager getEntityManager() {
		if (!em.isOpen()){
			criarEM();
		}
		
		return em;
	}
	
	public Session getSession(){
		return (Session) em.getDelegate();
	}

}

