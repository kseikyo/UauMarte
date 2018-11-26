

package Objetos;

import java.sql.Timestamp;

/**
 *
 * @author soart
 */
public class Compra {
    private int codCompra;
    private Timestamp horarioEntrega;
    private int idUsuario;
    private String observacao;

    public int getCodCompra() {
        return codCompra;
    }

    public void setCodCompra(int codCompra) {
        this.codCompra = codCompra;
    }

    public Timestamp getHorarioEntrega() {
        return horarioEntrega;
    }

    public void setHorarioEntrega(Timestamp horarioEntrega) {
        this.horarioEntrega = horarioEntrega;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    
}
