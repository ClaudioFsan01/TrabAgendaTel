package Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Modelo.Contato;


public class AgendaDao {
	
	public void adicionarContato(Contato contato) {
	
	 try {

         Connection con = Banco.getConexao();

         PreparedStatement ps = con.prepareStatement("insert into contato (nome, telefone) values (?,?)");
         ps.setString(1, contato.getNome());
         ps.setString(2, contato.getTelefone());
         ps.executeUpdate();
         ps.close();

     } catch(Exception exception) {

         throw new RuntimeException( exception);
     }
}
	
 public List<Contato> obterTodos() {	 

     try {    	 
         Connection con = Banco.getConexao();
         Statement statement = con.createStatement();
         ResultSet rs = statement.executeQuery("select * from contato");
         List<Contato> lista = new ArrayList<>();
         while( rs.next()) {
 
             Contato contato = new Contato( rs.getString(1), rs.getString(2));
             lista.add( contato);
         }
         return lista;
     } catch(Exception exception) {

         throw new RuntimeException( exception);
     }
 }

 public void excluir(Contato contato) {

     try {

         Connection con = Banco.getConexao();
         PreparedStatement ps = con.prepareStatement("delete from contato where telefone=?");
         ps.setString(1, contato.getTelefone());
         ps.executeUpdate();
         ps.close();
     } catch(Exception exception) {

         throw new RuntimeException( exception);
     }
 }

 public void alterarContato(String telefoneOriginal, Contato contato) {

     try {

         Connection con = Banco.getConexao();
         PreparedStatement ps = con.prepareStatement("update contato set nome=?,telefone=? where telefone=?");
         ps.setString(1, contato.getNome());
         ps.setString(2, contato.getTelefone());
         ps.setString(3, telefoneOriginal);
         ps.executeUpdate();
         ps.close();
     } catch(Exception exception) {

         throw new RuntimeException( exception);
     }
 }
	
	

 }
