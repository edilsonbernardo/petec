package mbean;

import DAO.DAOGenerico;
import dominio.Pessoa;
import dominio.Pessoa;

/** 
 * @author Kerolayne e Edilson
*/
public class CadastroPessoaMBean {
	/** Indica se � cadastro (true) ou edi��o (false) */
	private boolean cadastro;
	
	/** Armazena os dados do usu�rio que ser� cadastrado */
	private Pessoa pessoa;
	
	/** Permite o acesso ao banco. */
	private DAOGenerico dao;

}
