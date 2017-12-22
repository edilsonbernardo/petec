package mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DAO.PessoaDao;
import dominio.Pessoa;

@ManagedBean
@ViewScoped
public class PessoaMBean extends PessoaDao{
	/**
	 * @author Kerolayne e Edilson
	 */
	private static final long serialVersionUID = 1L;
	Pessoa p = new Pessoa();
	/** Chamando o método criar pessoa. */
	public String criar() {
		criar(p);
		return null;
	}
	/** Chamando o método pesquisar pessoa. */
	public String pesquisar() {
		p = buscar(p.getId());
		return null;
	}
	/** Chamando o método atualizar pessoa. */
	public String atualizar() {
		atualizar(p);
		return null;
	}
	/** Chamando o método excluir pessoa. */
	public String excluir() {
		remove(p.getId());
		return null;
		
	}
	
	public Pessoa getP() {
		return p;
	}

	public void setP(Pessoa p) {
		this.p = p;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
