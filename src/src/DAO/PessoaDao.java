package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import dominio.Pessoa;
import util.CriptografiaUtils;

public class PessoaDao extends Database {
	/** Criando usuário partir do id. */
	public void criar(Pessoa p){
		try {
			getEntityManager().getTransaction().begin();// preparando para gravar
			p.setSenha(CriptografiaUtils.criptografarMD5(p.getSenha()));
			getEntityManager().persist(p);//inserindo localmente o usuario
			getEntityManager().getTransaction().commit();//gravando no banco de dados
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
		}
	}
	/** Atualizando os dados da pessoa partir do id. */
	public void atualizar(Pessoa p){
		try {
			getEntityManager().getTransaction().begin();
			Pessoa pessoaBanco = getEntityManager().find(Pessoa.class, p.getId());
			
			pessoaBanco.setNome(p.getNome());
			pessoaBanco.setEmail(p.getEmail());
		
			getEntityManager().merge(pessoaBanco);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
		}
	}
	/** Remover pessoa partir do id. */
	public void remove(int id){
		try {
			getEntityManager().getTransaction().begin();
			Pessoa pessoaBanco = getEntityManager().find(Pessoa.class, id);
			
			getEntityManager().remove(pessoaBanco);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
		}
	}
	/** Buscar pessoa partir do id. */
	public Pessoa buscar(int id){
		try {
			Pessoa pessoaBanco = getEntityManager().find(Pessoa.class, id);
			return pessoaBanco;
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
		}
		return null;
	}
	
	/** Encontra um usuário a partir de seu login e senha. */
	public Pessoa findPessoaByEmailSenha(String login, String senha){
		EntityManager em = getEntityManager();
		
		String hql = "SELECT p ";
		hql += " FROM Pessoa p WHERE p.email = :email and p.senha = :senha ";
		
		Query q = em.createQuery(hql);
		q.setParameter("email", login);
		q.setParameter("senha", senha);
		
		try {
			Pessoa usuario = (Pessoa) q.getSingleResult();
			return usuario;
		} catch (NoResultException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/** Listar as pessoas cadastradas.*/
	public List<Pessoa> listaPessoa(){
		EntityManager em = getEntityManager();
		
		String hql = "SELECT p  FROM Pessoa p ";
		Query q = em.createQuery(hql);
		
		try {
			List<Pessoa> lista = q.getResultList();
			return lista;
		} catch (NoResultException e){
			e.printStackTrace();
			return null;
		}
	}
}
