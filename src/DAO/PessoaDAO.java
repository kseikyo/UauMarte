package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ControllerClass.ControllerStart;
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
            ControllerStart controllerStart = new ControllerStart();
            controllerStart.initScreen("/FXMLFILES/TelaInicial.fxml","Tela inicial", controllerStart.getStage());
        }catch (SQLException e) {
            loginResult.setText("Usuário não encontrado!");
        }
        Conexao.fecharConexao();
    }
    
    public static void adicionarPessoa() {
        
    }
    
}
