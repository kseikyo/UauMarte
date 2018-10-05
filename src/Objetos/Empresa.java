
package Objetos;

/**
 *
 * @author soart
 */
public class Empresa {
    private int CodEmpresa;
    private String Cnpj;
    private String Nome;
    private String CEP ;
    private String Endereco;
    private String Numero;
    private String Email;
    private String Senha;

    public Empresa() {
    }

    public int getCodEmpresa() {
        return CodEmpresa;
    }

    public void setCodEmpresa(int CodEmpresa) {
        this.CodEmpresa = CodEmpresa;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String Cnpj) {
        this.Cnpj = Cnpj;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }
    
}
