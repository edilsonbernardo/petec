package mbean;

import DAO.DAOGenerico;
import dominio.Pessoa;
import dominio.Pessoa;

/** 
 * @author Kerolayne e Edilson
*/
public class CadastroPessoaMBean {
	/** Indica se é cadastro (true) ou edição (false) */
	private boolean cadastro;
	
	/** Armazena os dados do usuário que será cadastrado */
	private Pessoa pessoa;
	
	/** Permite o acesso ao banco. */
	private DAOGenerico dao;

}
