package ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Clock;
import javafx.scene.control.Label;

/**
 *
 * @author Lucas
 */
public class PessoaDAO {
    
    public static void pesquisarPessoa(String login, String senha, Label loginResult) {

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
            loginResult.setText("Login realizado com sucesso!");
        }catch (SQLException e) {
            loginResult.setText("Usuário não encontrado!");
        }
        Conexao.fecharConexao();
    }
    
    public static void adicionarPessoa() {
        
    }
    
}
