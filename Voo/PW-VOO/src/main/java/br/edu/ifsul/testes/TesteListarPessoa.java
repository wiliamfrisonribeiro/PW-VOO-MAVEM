    /*  
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Pessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteListarPessoa {
    
       public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PW-VOO-PU");
        EntityManager em = emf.createEntityManager();
        List<Pessoa> l = em.createQuery("from Pessoa order by id").getResultList();
        for (Pessoa p : l){
            System.out.println("ID: " + p.getId() + " Nome: " + p.getNome() + " CPF: " + p.getCpf() + " EMAIL:" + p.getEmail());
        }

        em.close();
        emf.close();
        
        
        
    }
   
}
