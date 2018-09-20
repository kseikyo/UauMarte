package ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Label;

public class Conexao {

    private static String usuario = "postgres";
    private static String senha = "";
    private static String banco = "pessoa";
    private static String host = "localhost:5432";
    private static String driver = "org.postgresql.Driver";
    private static Connection conexao = null;
    
    public Conexao() {
        
    }

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            if (conexao == null || conexao.isClosed()) {
                conexao = DriverManager.getConnection("jdbc:postgresql://" + host + "/" + banco, usuario, senha);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado!");
        } catch (SQLException e) {
            System.out.println("Erro de SQL!");
            System.out.println(e.getMessage());
        }

        return conexao;
    }
    
    public static void fecharConexao() {
        try {
            if(conexao!=null && !conexao.isClosed()) {
                conexao.close();
            }
        }catch(SQLException e) {
            
        }
    }

    public void getConnection(String text, String text0, Label loginResult) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
