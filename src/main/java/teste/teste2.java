package teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.snf.library.Encripta;
import com.snf.model.Usuario;

public class teste2 {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("restauranteCDI");
	static EntityManager entityManager = emf.createEntityManager();
	
	public static void main(String[] args) {
		
		Encripta e = new Encripta();
		
		//Usuario u = getUsuarioByLoginSenha("a", e.encripta("a"));
		
		System.out.println( e.encripta("a"));
	}
	
	public static Usuario getUsuarioByLoginSenha(String login,String senha){
		Usuario user=null;
		
		try {
			
			Query q = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.login=:log AND u.senha=:sen");
			q.setParameter("log", login);
			q.setParameter("sen", senha);
			user = (Usuario) q.getSingleResult();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return user;
			
	}

}
