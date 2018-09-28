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
    
<<<<<<< HEAD

=======
>>>>>>> Lucas
    public static void pesquisarPessoa(String email, String senha, Label loginResult) throws IOException {
        Connection conn;
        conn = Conexao.getConnection();
        String sql = "";
        sql += "SELECT email, senha FROM public.usuario_do_sistema";
        sql += " WHERE email = ";
        sql += "'" +email + "'";
        sql += " AND senha = ";
        sql += "'" +senha+ "'";
        
        System.out.println(sql);
        
        try {
            PreparedStatement comando = conn.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery();
            resultado.next();
<<<<<<< HEAD

=======
>>>>>>> Lucas
            System.out.println(resultado.getString("email"));
            System.out.println(resultado.getString("senha"));
            ControllerTelaPrincipal controllerTelaPrincipal = new ControllerTelaPrincipal();
            ControllerStart controllerStart = new ControllerStart();
            controllerTelaPrincipal.start(controllerStart.getStage());

        }catch (SQLException e) {
            loginResult.setText("Usuário ou senha incorretos!");
        }
        Conexao.fecharConexao();
    }

    public static void cadastrarPessoa(String nome, String cpf, String data, String login, String senha,String cep,
                                       String ender, String num_casa, String comp, String bairro, String cidade,
                                       String uf) {
        Connection conn = Conexao.getConnection();

        String sql = "";
        sql += "INSERT INTO public.usuario_do_sistema ";
        sql += "(NOME, CPF, DATANASC, EMAIL, SENHA, CEP, ENDERECO, NUM_CASA, COMPLEMENTO, BAIRRO, CIDADE, UF)";
        sql += "VALUES ";
        sql += "(?,?,?,?,?,?,?,?,?,?,?,?)";

        //CPF SHALL HAVE NUMBERS ONLY
        String aux = cpf;
        cpf = aux.substring(0,3);
        cpf+= aux.substring(4,7);
        cpf+= aux.substring(8, 11);
        cpf+= aux.substring(12, 14);
        System.out.println(cpf);

        try {
            PreparedStatement comando = conn.prepareStatement(sql);
            comando.setString(1, nome);
            comando.setString(2, cpf);
            comando.setString(3, data);
            comando.setString(4, login);
            comando.setString(5, senha);
            comando.setString(6, cep);
            comando.setString(7, ender);
            comando.setString(8, num_casa);
            comando.setString(9, comp);
            comando.setString(10, bairro);
            comando.setString(11, cidade);
            comando.setString(12, uf);
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

    public static void pesquisarCPF(String cpf, Label lb) throws IOException {
        Connection conn;
        conn = Conexao.getConnection();
        String sql = "";
        sql += "SELECT cpf FROM public.usuario_do_sistema";
        sql += " WHERE cpf = ";
        sql += "'" +cpf + "'";


        System.out.println(sql);

        try {
            PreparedStatement comando = conn.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery();
            resultado.next();
            System.out.println(resultado.getString("cpf"));

            lb.setText("CPF já cadastrado!");

        }catch (SQLException e) {
            lb.setText("");
        }
        Conexao.fecharConexao();
    }
    
}
