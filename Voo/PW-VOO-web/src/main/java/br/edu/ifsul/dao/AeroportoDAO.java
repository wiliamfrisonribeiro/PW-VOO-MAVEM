/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Aeroporto;
import java.io.Serializable;
import javax.ejb.Stateful;

@Stateful
public class AeroportoDAO<TIPO> extends DAOGenerico<Aeroporto> implements Serializable {
    public AeroportoDAO(){
        super();
        classePersistente = Aeroporto.class;
        // definir as ordens possíveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        // difinir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);      
    }

}
