/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author guilhermewestrup
 */
public class AgenciaModel {
    private int id;
    private String codigo;
    private String nome;
    private String cidade;

    public AgenciaModel() {
    }

    public AgenciaModel(int id, String codigo, String nome, String cidade) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.cidade = cidade;
    }
    
    public AgenciaModel(String codigo, String nome, String cidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.cidade = cidade;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

}
