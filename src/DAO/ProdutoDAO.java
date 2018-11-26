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

        //int count = 1;

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
                p.setUrlImagem(rs.getString("imagem"));
                //count++;
                p.setObservacao(rs.getString("observacao"));

                list.add(p);

            }
            con.close();
            }catch(SQLException e){
                e.getMessage();
                e.getCause();
                e.printStackTrace();
            }
        return list;
    }

    /*public static void cadastrarCompra(String codcompra, String idusuario) {
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
        } catch (SQLException e) {
            System.out.println("Erro ao inserir!");
        }
        Conexao.fecharConexao();
    }
    */
}

