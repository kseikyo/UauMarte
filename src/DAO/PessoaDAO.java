package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import ControllerClass.ControllerStart;
import TelaPrincipal.ControllerTelaPrincipal;
import javafx.scene.control.Label;
import ConexaoBanco.Conexao;

/**
 *
 * @author Lucas
 */
public class PessoaDAO {
    
    public static void pesquisarPessoa(String login, String senha, Label loginResult) throws IOException {
        Connection conn;
        conn = Conexao.getConnection();
        String sql = "";
        sql += "SELECT login, senha FROM public.usuario_do_sistema";
        sql += " WHERE login = ";
        sql += "'" +login + "'";
        sql += " AND senha = ";
        sql += "'" +senha+ "'";
        
        System.out.println(sql);
        
        try {
            PreparedStatement comando = conn.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery();
            resultado.next();
            System.out.println(resultado.getString("login"));
            System.out.println(resultado.getString("senha"));
            ControllerTelaPrincipal controllerTelaPrincipal = new ControllerTelaPrincipal();
            ControllerStart controllerStart = new ControllerStart();
            controllerTelaPrincipal.start(controllerStart.getStage());

        }catch (SQLException e) {
            loginResult.setText("Usuário não encontrado!");
        }
        Conexao.fecharConexao();
    }

    public static void cadastrarPessoa(String nome, String cpf, String data, String login, String senha, String ender,
                                String bairro, String cidade, String telefone) {
        Connection conn = Conexao.getConnection();

        String sql = "";
        sql += "INSERT INTO public.usuario_do_sistema ";
        sql += "(NOME, CPF, DATANASC, LOGIN, SENHA, ENDERECO, BAIRRO, CIDADE, TELEFONE)";
        sql += "VALUES ";
        sql += "(?,?,?,?,?,?,?,?,?)";


        try {
            PreparedStatement comando = conn.prepareStatement(sql);
            comando.setString(1, nome);
            comando.setString(2, cpf);
            comando.setString(3, data);
            comando.setString(4, login);
            comando.setString(5, senha);
            comando.setString(6, ender);
            comando.setString(7, bairro);
            comando.setString(8, cidade);
            comando.setString(9, telefone);
            System.out.println(sql);
            System.out.println(comando);

            comando.execute();
            comando.close();
            System.out.println("Cadastro realizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir!");
        }
        Conexao.fecharConexao();
    }
    
}
