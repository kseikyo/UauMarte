/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ConexaoBanco.Conexao;
import Objetos.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author soart
 */
public class EmpresaDAO {
    /*
        private int CodEmpresa;
    private String Cnpj;
    private String Nome;
    private String CEP ;
    private String Endereco;
    private String Numero;
    private String Email;
    private String Senha;
    */

    //PESQUISAR TODAS AS EMPRESAS (RETORNA UMA LISTA)
    public ArrayList<Empresa> f_PesquisarEmpresa(){
        
        ArrayList<Empresa> lista = new ArrayList(0);
        
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM EMPRESA");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                Empresa empresa = f_GetEmpresa(rs);
                lista.add(empresa);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro de sql! " + ex.getMessage());
        }
        
        return lista;        
    }
    
    //PESQUISAR EMPRESA POR LOGIN
    public Empresa f_PesquisarEmpresa(String email , String senha){
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM EMPRESA WHERE EMAIL = ?");
            ps.setString(1,email);
            ResultSet rs = ps.executeQuery();
            if(rs.first()){
                Empresa empresa = f_GetEmpresa(rs);
                if(senha.equals( empresa.getSenha() )){
                    return empresa;
                }
            }            
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro de sql! " + ex.getMessage());
        }
        return null;
    }
    //PESQUISAR POR COD
    public Empresa f_PesquisarEmpresa(int codEmpresa){
            try {
            Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM EMPRESA WHERE CODEMPRESA = ?");
            ps.setInt(1,codEmpresa);
            ResultSet rs = ps.executeQuery();
            if(rs.first()){
                return f_GetEmpresa(rs);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro de sql! " + ex.getMessage());
        }
        return null;
     
    
    }
    
    //CADASTRAR EMPRESA
    public int f_CadastrarEmpresa(String cnpj, String nome, String cep, String endereco, String numero, String email, String senha){
        //RETORNA O COD DA EMPRESA
        return 0;
    }
    //ATUALIZA EMPRESA PELO CODIGO
    public boolean f_CadastrarEmpresa(int codEmpresa, String cnpj, String nome, String cep, String endereco, String numero, String email, String senha){
        //ACHOU A EMPREASA?
        return false;
    }
    
    //DELETA EMPRESA
    public boolean f_RemoverEmpresa (int codEmpresa){
        //DELETOU A EMPRESA?
        return false;
    } 
    
    private Empresa f_GetEmpresa(ResultSet rs) throws SQLException{
        
        Empresa empresa;
        empresa = new Empresa();
        empresa.setCodEmpresa(rs.getInt("CodEmpresa"));
        empresa.setCnpj(rs.getString("CNPJ"));
        empresa.setNome(rs.getString("nome"));
        empresa.setCEP(rs.getString("CEP"));
        empresa.setEndereco(rs.getString("endereco"));
        empresa.setNumero(rs.getString("numero"));
        empresa.setEmail(rs.getString("email"));
        empresa.setSenha(rs.getString("senha"));
        return empresa;
        
    } 
}
