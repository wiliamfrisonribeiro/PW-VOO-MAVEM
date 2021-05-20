/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Aeroporto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteAlterarAeroporto {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PW-VOO-PU");
        EntityManager em = emf.createEntityManager();
        Aeroporto a = em.find(Aeroporto.class, 8);
        a.setNome("Congonhas");
        a.setOperacaoNoturna(Boolean.TRUE);
        em.getTransaction().begin();
        em.merge(a);
        em.getTransaction().commit();
        em.close();
        emf.close();

    }

}
