/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Voo;

import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class VooDAO<TIPO> extends DAOGenerico<Voo> implements Serializable {
    public VooDAO(){
        super();
        classePersistente = Voo.class;
        // definir as ordens possíveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("descricao", "Descricao", "like"));
        // difinir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);   
    }
    
    public Voo getObjectById(Object id) throws Exception{

        
      Voo obj = em.find(Voo.class, id);
      //uso ara evitar  o erro de lazy inicialization exception
      obj.getVooagendado().size();
      obj.getEscalas().size();
      return obj;
    }

}
