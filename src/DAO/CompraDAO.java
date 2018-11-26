package DAO;

import ConexaoBanco.Conexao;
import Objetos.Carrinho;
import Objetos.Compra;
import Objetos.ProdutoCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    public static List<Compra> HistoricoCompras() throws SQLException{
        Connection conn = Conexao.getConnection();
        List<Compra> lista = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Compra ORDER BY codcompra");
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            Compra c = new Compra();
            c.setCodCompra( rs.getInt("codCompra") );
            c.setHorarioEntrega( rs.getTimestamp("horarioEntrega"));
            c.setIdUsuario( rs.getInt("idUsuario"));
            c.setObservacao( rs.getString("Observacao"));
            lista.add(c);        
        }
        
        return lista;
    }
    
    public static List<ProdutoCompra> ProdutoCompra(int codCompra) throws SQLException{
        Connection conn = Conexao.getConnection();
        List<ProdutoCompra> lista = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(""
                + "SELECT p.descricao,p.marca,p.preco , cp.quantidade "
                + "FROM Produto p INNER JOIN CompraProduto cp ON p.codProduto = cp.CodProduto "
                + "WHERE cp.CodCompra = ?");
        
        ps.setInt(1, codCompra);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            ProdutoCompra pc = new ProdutoCompra();
            pc.setDescricao(rs.getString(1));
            pc.setMarca(rs.getString(2));
            pc.setPreco(rs.getDouble(3));
            pc.setQuantidade(rs.getInt(4));
            lista.add(pc);
        }
            return lista;
    }
}
