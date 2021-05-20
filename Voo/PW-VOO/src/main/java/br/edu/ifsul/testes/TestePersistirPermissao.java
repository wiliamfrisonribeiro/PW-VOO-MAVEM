package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Permissao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestePersistirPermissao {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PW-VOO-PU");
        EntityManager em = emf.createEntityManager();
        Permissao p1 = new Permissao();
        p1.setNome("ADMINISTRADOR");
        p1.setDescricao("adminstradores");
        Permissao p2 = new Permissao();
        p2.setNome("USUARIO");
        p2.setDescricao(" usuários");        
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

}