package DAO;

import ConexaoBanco.Conexao;
import Objetos.Carrinho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;

import TelaLogin.ControllerTelaLogin;
import javafx.collections.ObservableList;

/**
 *
 * @author soart
 */
public class CompraDAO {

    public static void CriarCompra(ObservableList<Carrinho> lista) throws SQLException {
        Iterator<Carrinho> i = lista.iterator();
        Connection conn = Conexao.getConnection();
        String sql = null;
        Carrinho carrinho = null;

        sql = "INSERT INTO public.compra(" +
                "codcliente)" +
                "VALUES (?);";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, ControllerTelaLogin.idusuario);

        ps.execute();

        int CodCompra = Codigo();
        ps = conn.prepareStatement("INSERT INTO CompraProduto (CodCompra, CodProd, Quantidade) VALUES (?,?,?)");
        while (i.hasNext()) {
            carrinho = i.next();
            ps.setInt(1, CodCompra);
            ps.setInt(2, carrinho.getCod_prod());
            ps.setInt(3, carrinho.getQuantidade());
            ps.execute();
        }

    }

    public static int Codigo() throws SQLException {
        Connection conn = Conexao.getConnection();
        String sql = null;
        Carrinho carrinho = null;

        sql = "SELECT max(CodCompra) FROM Compra WHERE codcliente = "+ ControllerTelaLogin.idusuario;

        PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public static void inserirHorario(String horario) throws SQLException {
        String sql;
        Connection conn = Conexao.getConnection();

        sql = "UPDATE public.compra SET horario = ? where codcompra = (select max(codcompra) from public.compra)" +
                " and " +
                "codcliente = ? " ;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, horario);
            ps.setInt(2, ControllerTelaLogin.idusuario);
            ps.execute();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void inserirFinal(String forma, String ender) {
        String sql;
        Connection conn = Conexao.getConnection();

        sql = "UPDATE public.compra SET formapag = ?, endereco = ? where codcompra = (select max(codcompra) from public.compra) and " +
                "codcliente = ?" ;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, forma);
            ps.setString(2, ender);
            ps.setInt(3, ControllerTelaLogin.idusuario);
            ps.execute();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
