package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TestePersistirUsuario {

    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PW-VOO-PU");
        EntityManager em = emf.createEntityManager();
        Usuario u1 = new Usuario();
        u1.setNome("Wiliam");
        u1.setNomeUsuario("caruso");
        u1.setSenha("123");
        u1.setAtivo(true);
        u1.setEmail("wiliam@gmail.com");        
        em.getTransaction().begin();
        em.persist(u1);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

}