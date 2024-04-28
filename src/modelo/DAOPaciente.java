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
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class DAOPaciente {
    public List<Paciente> getLista(){
        String sql = "select * from paciente";
        List<Paciente> listaPaciente = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Paciente objPaciente = new Paciente();
                objPaciente.setCodigo(rs.getInt("codPaciente"));
                objPaciente.setNome(rs.getString("nome"));
                objPaciente.setCpf(rs.getString("cpf"));
                objPaciente.setSexo(rs.getString("sexo"));
                java.sql.Date dt = rs.getDate("nascimento");
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                objPaciente.setNascimento(c);
                listaPaciente.add(objPaciente);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL: "+ex.getMessage());
        }
        return listaPaciente;
    }
    
    public boolean salvar(Paciente obj){
        if(obj.getCodigo() == null){
            return incluir(obj);
        }else{
            return alterar(obj);
        }
    }
    
    public boolean incluir(Paciente objPaciente){
        String sql="insert into paciente(nome, cpf, sexo, nascimento) values(?,?,?,?)";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objPaciente.getNome());
            pst.setString(2, objPaciente.getCpf());
            pst.setString(3, objPaciente.getSexo());
              System.out.println("Data:"+new java.sql.Date(objPaciente.getNascimento().getTimeInMillis()));
            pst.setDate(4, new java.sql.Date(objPaciente.getNascimento().getTimeInMillis()));
            if(pst.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso");
            return true;
        }else{
                JOptionPane.showMessageDialog(null, "Paciente não cadastrada");
                return false;
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL: "+ ex.getMessage());
            return false;
        }
    }
    
    public boolean alterar(Paciente objPaciente){
        String sql="update paciente set nome=?,cpf=?,sexo=?, nascimento=? where codPaciente=?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objPaciente.getNome());
            pst.setString(2, objPaciente.getCpf());
            pst.setString(3, objPaciente.getSexo());
            pst.setDate(4, new java.sql.Date(objPaciente.getNascimento().getTimeInMillis()));
            pst.setInt(5, objPaciente.getCodigo());
            if(pst.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Paciente alterado com sucesso");
            return true;
        }else{
                JOptionPane.showMessageDialog(null, "Paciente não alterada");
                return false;
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL: "+ ex.getMessage());
            return false;
        }
    }
    
    public boolean remover(Paciente objPaciente){
        String sql="delete from paciente where codPaciente=?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, objPaciente.getCodigo());
            if(pst.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Paciente excluido com sucesso");
            return true;
        }else{
                JOptionPane.showMessageDialog(null, "Pacente não excluida");
                return false;
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL: "+ ex.getMessage());
            return false;
        }
    }
    public Paciente localizar(Integer id){
        
        String  sql = "select * from paciente where codPaciente=?";
        Paciente objPaciente = new Paciente();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                objPaciente.setCodigo(rs.getInt("codPaciente"));
                objPaciente.setNome(rs.getString("nome"));
                return objPaciente;    
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de sql em localizar no DAOPaciente: "+ex.getMessage());
            
        }
        return null;
    }

}
