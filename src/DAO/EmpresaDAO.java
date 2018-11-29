package DAO;
import ConexaoBanco.Conexao;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class EmpresaDAO {

    public static LinkedList<String> getHorarios(String empresaid) {
        Connection conn = null;
        LinkedList<String> horarios = new LinkedList<>();
        try {
            conn = Conexao.getConnection();
        } catch (Exception e) {

        }
        String sql = "";
        sql += "SELECT horario FROM public.horarios";
        sql += " WHERE codempresa = ";
        sql += "'" + empresaid + "'";

        System.out.println(sql);
        try {
            PreparedStatement comando = conn.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                horarios.add(resultado.getString("horario"));
            }

        } catch (SQLException e) {

        }
        Conexao.fecharConexao();
        return horarios;
    }
}
