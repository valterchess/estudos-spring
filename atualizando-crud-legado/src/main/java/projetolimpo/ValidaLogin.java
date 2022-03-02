package projetolimpo;

import projetooriginal.BancoDados;

import java.sql.ResultSet;

/***
 * construir uma exception especifica para cada metodo
 * e/ou classe
 */
public class ValidaLogin {
    public static boolean validaLogin(String nome, String senha, BancoDados banco) throws Exception{
        String consulta = String.format("select * from usuarios where nome ='%s' && senha ='%s'", nome, senha);
        ResultSet resultado = banco.processeQuery(consulta);
        if(resultado.next()){
            return nome.equals(resultado.getString(1))
                    && senha.equals(resultado.getString(2));
        }
        return false;
    }
}
