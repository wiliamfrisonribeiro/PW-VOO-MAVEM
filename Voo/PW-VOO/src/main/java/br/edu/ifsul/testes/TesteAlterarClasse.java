/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Classe;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteAlterarClasse {
      public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PW-VOO-PU");
        EntityManager em = emf.createEntityManager();
        Classe c = em.find(Classe.class, 1);
        c.setNome("Primeira classe");

        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
        em.close();
        emf.close();
       
    }
}
