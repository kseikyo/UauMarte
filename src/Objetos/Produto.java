/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author soart
 */
public class Produto {

    private int codigo;
    private String descricao;
    private double preco;
    private String vendaPor;
    private String marca;
    private String observacao;
    private String urlImagem;

    public Produto(int codigo, String descricao, double preco, String vendaPor, String marca, String observacao, String urlImagem) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.vendaPor = vendaPor;
        this.marca = marca;
        this.observacao = observacao;
        this.urlImagem = urlImagem;
    }

    public Produto() {
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getVendaPor() {
        return vendaPor;
    }

    public void setVendaPor(String vendaPor) {
        this.vendaPor = vendaPor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}
