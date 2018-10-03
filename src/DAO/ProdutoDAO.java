package DAO;

import ConexaoBanco.Conexao;
import Objetos.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ProdutoDAO {

    public static LinkedList<Produto> retornarTodosProdutos() {
        Connection con = null;
        try {
            con = Conexao.getConnection();
        }catch (Exception e) {
            e.getMessage();
        }
        String sql = "";
        sql += "SELECT * FROM produto";

        int count = 1;

        LinkedList<Produto> list = new LinkedList<>();


        try {
            PreparedStatement comando = con.prepareStatement(sql);
            ResultSet rs = comando.executeQuery();


            while (rs.next()) {
                Produto p = new Produto();
                p.setCodigo(rs.getInt("codproduto"));
                p.setDescricao(rs.getString("descricao"));
                p.setPreco(rs.getDouble("preco"));
                p.setVendaPor(rs.getString("vendapor"));
                p.setMarca(rs.getString("marca"));
                p.setUrlImagem("/IMG_produtos/"+count+".JPG");
                count++;
                p.setObservacao(rs.getString("observacao"));

                list.add(p);

            }
            con.close();
            }catch(SQLException e){
                e.getMessage();
                e.getCause();
                e.printStackTrace();
            }
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getUrlImagem());
        }
        return list;
    }
}

