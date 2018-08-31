package ConexaoBanco;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;

public class Conexao {
    private Connection conn = null;
    private String url = "jdbc:postgresql://localhost:5432/UauMarte";
    private String user = "postgres";
    private String password = "uaumarte";
    
    public Connection f_GetConnection(String user, String password, Label l) throws IOException, ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        
        try {
            
            conn = DriverManager.getConnection(url, this.user, this.password);
            /*Statement stmt = conn.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT \"Usuario\", \"Senha\", \"idCliente\" FROM public.\"Cliente\";");
            while(rs.next()) {
                if(rs.getString("\"Usuario\"").equals(user) && rs.getString("\"Senha\" ").equals(password)) {
                    l.setText("Login feito com sucesso!");
                    break;
                }
            }*/
            l.setText("Login feito com sucesso!");
            return conn;
        }catch(SQLException ex){
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null,ex);
            l.setText("Não foi possível fazer o login.");
        }
        
        return conn;
    }
    
    public void f_CloseConnection(Connection con) throws SQLException {
        con.close();
    }
    
}
