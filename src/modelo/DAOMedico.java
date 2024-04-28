/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class DAOMedico {
      public List<Medico> getLista(){
        String sql = "select * from medico";
        List<Medico> listaMedico = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Medico objMedico = new Medico();
                objMedico.setCodigo(rs.getInt("codMedico"));
                objMedico.setNome(rs.getString("nome"));
                objMedico.setCpf(rs.getString("cpf"));
                objMedico.setSalario(rs.getDouble("salario"));
                listaMedico.add(objMedico);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL: "+ex.getMessage());
        }
        return listaMedico;
    }
    
    public boolean salvar(Medico obj){
        if(obj.getCodigo() == null){
            return incluir(obj);
        }else{
            return alterar(obj);
        }
    }
    
    public boolean incluir(Medico objMedico){
        String sql="insert into medico(nome, cpf, salario) values(?,?,?)";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objMedico.getNome());
            pst.setString(2, objMedico.getCpf());
            pst.setDouble(3, objMedico.getSalario());
            if(pst.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Médico cadastrado com sucesso");
            return true;
        }else{
                JOptionPane.showMessageDialog(null, "Medico não cadastrada");
                return false;
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL: "+ ex.getMessage());
            return false;
        }
    }
    
    public boolean alterar(Medico objMedico){
        String sql="update medico set nome=?,cpf=?,salario=?where codMedico=?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objMedico.getNome());
            pst.setString(2, objMedico.getCpf());
            pst.setDouble(3, objMedico.getSalario());
            pst.setInt(4, objMedico.getCodigo());
            if(pst.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Médico alterado com sucesso");
            return true;
        }else{
                JOptionPane.showMessageDialog(null, "Médico não alterada");
                return false;
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL: "+ ex.getMessage());
            return false;
        }
    }
    
    public boolean remover(Medico objMedico){
        String sql="delete from medico where codMedico=?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, objMedico.getCodigo());
            if(pst.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Médico excluido com sucesso");
            return true;
        }else{
                JOptionPane.showMessageDialog(null, "Médico não excluida");
                return false;
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL: "+ ex.getMessage());
            return false;
        }
    }
    
     public Medico localizar(Integer id){
        
        String  sql = "select * from medico where codMedico=?";
        Medico objMedico = new Medico();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                objMedico.setCodigo(rs.getInt("codMedico"));
                objMedico.setNome(rs.getString("nome"));
                return objMedico;    
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de sql em localizar no DAOMedico: "+ex.getMessage());
            
        }
        return null;
    }
}