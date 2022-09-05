/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author guilhermewestrup
 */
public class ContaModel {
    private int id;
    private String numeroConta;
    private float saldo;
    private ClienteModel cliente;
    private AgenciaModel agencia;

    public ContaModel() {
    }
    
    public ContaModel(String numeroConta, float saldo, ClienteModel cliente, AgenciaModel agencia) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.cliente = cliente;
        this.agencia = agencia;
    }

    public ContaModel(int id, String numeroConta, float saldo, ClienteModel cliente, AgenciaModel agencia) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.cliente = cliente;
        this.agencia = agencia;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public AgenciaModel getAgencia() {
        return agencia;
    }

    public void setAgencia(AgenciaModel agencia) {
        this.agencia = agencia;
    }
    
    
}
