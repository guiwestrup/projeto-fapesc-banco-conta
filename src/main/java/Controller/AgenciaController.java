/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.AgenciaDao;
import Model.AgenciaModel;

/**
 *
 * @author guilhermewestrup
 */
public class AgenciaController {
    
    public static String salvarAgencia(String codigo, String nome, String cidade) {
        var agencia = new AgenciaModel(codigo, nome, cidade);
        var agenciaDao = new AgenciaDao();
        
        agenciaDao.insert(agencia);
        
        return "Criado.";
    }
}
