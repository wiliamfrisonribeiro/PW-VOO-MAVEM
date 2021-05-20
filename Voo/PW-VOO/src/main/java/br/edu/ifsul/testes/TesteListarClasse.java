/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Classe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteListarClasse {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PW-VOO-PU");
        EntityManager em = emf.createEntityManager();
        List<Classe> lista = em.createQuery("from Classe order by id").getResultList();
        for (Classe e : lista){
            System.out.println("ID: " + e.getId() + " Nome: " + e.getNome() + " Valor: " + e.getValor());
        }


        em.close();
        emf.close();
        
        
        
    }
}
