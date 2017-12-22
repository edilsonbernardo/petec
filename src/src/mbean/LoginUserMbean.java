package mbean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import DAO.PessoaDao;
import dominio.Pessoa;
import util.AbstractController;
import util.CriptografiaUtils;
import util.ValidatorUtil;

@ManagedBean
@ViewScoped
public class LoginUserMbean extends AbstractController{
	/** Armazena os dados informados na tela de login. 
	 * @author kerolayne e Edilson
	 * */
	private Pessoa pessoa;
	
	@PostConstruct
	private void init(){
		pessoa = new Pessoa();
	}
	
	/** Autentica o usuário e faz login no sistema. */
	public String autenticar(){
		if (!validarLogin()){
			return null;
		}
		
		try {
			PessoaDao dao = new PessoaDao();
			pessoa = dao.findPessoaByEmailSenha(pessoa.getEmail(), CriptografiaUtils.criptografarMD5(pessoa.getSenha()));
			
			if (ValidatorUtil.isEmpty(pessoa)){
				init();
				addMsgError("Usuário/Senha incorretos.");
				return null;
			}
			
			//Salva o usuário permanentemente na memória do sistema 
			getCurrentSession().setAttribute("pessoaLogada", pessoa);
			return "/portal/pagina_principal";
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	
	/** Verifica se os dados para login estão corretos */
	public boolean validarLogin(){
		if (pessoa == null || (ValidatorUtil.isEmpty(pessoa.getEmail()) && 
				ValidatorUtil.isEmpty(pessoa.getSenha()))){
			addMsgError("Usuário/senha não informados.");
			return false;
		}
		
		if (ValidatorUtil.isEmpty(pessoa.getEmail())){
			addMsgError("Usuário: campo obrigatório não informado.");
			return false;
		}
		
		if (ValidatorUtil.isEmpty(pessoa.getSenha())){
			addMsgError("Senha: campo obrigatório não informado.");
			return false;
		}
		
		return true;
	}
	
	/** Realiza logoff do sistema. */
	public String logoff(){
		getCurrentSession().invalidate();
		return "/publico/login.xhtml";
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
}
