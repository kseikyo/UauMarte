package DAO;

import ConexaoBanco.Conexao;
import Objetos.Carrinho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import javafx.collections.ObservableList;

/**
 *
 * @author soart
 */
public class CompraDAO {

    public static void CriarCompra(ObservableList<Carrinho> lista, Timestamp data) throws SQLException{
        Iterator<Carrinho> i =  lista.iterator();
        Connection conn = Conexao.getConnection();
        String sql = null;
        Carrinho carrinho = null;
        
        sql = "INSERT INTO Compra (HorarioEntrega,IDUsuario,Observacao) VALUES (?,?,?);";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setTimestamp(1, data);
        ps.setInt(2, 1);
        ps.setString(3, "");
        ps.execute();
        
        int CodCompra = Codigo(data);
        ps = conn.prepareStatement("INSERT INTO CompraProduto (CodCompra, CodProduto, Quantidade) VALUES (?,?,?)");
        while(i.hasNext()){
            carrinho = i.next();
            ps.setInt(1, CodCompra);
            ps.setInt(2, carrinho.getCod_prod());
            ps.setInt(3, carrinho.getQuantidade());
            ps.execute();
        }
        
    }
    
    public static int Codigo (Timestamp data) throws SQLException{
        Connection conn = Conexao.getConnection();
        String sql = null;
        Carrinho carrinho = null;
        
        sql = "SELECT CodCompra FROM Compra WHERE HorarioEntrega = ? AND IDUsuario = ?";
        
        PreparedStatement ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ps.setTimestamp(1, data);
        ps.setInt(2, 1);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}
