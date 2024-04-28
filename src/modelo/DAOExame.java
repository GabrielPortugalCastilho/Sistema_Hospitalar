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
public class DAOExame {
    
    DAOPaciente objDAOPaciente = new DAOPaciente();
    DAOMedico objDAOMedico = new DAOMedico();
    
    public List<Exame> getLista(){
        String sql = "select * from exame";
        List<Exame> listaExame = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Exame objExame = new Exame();
                objExame.setCodigo(rs.getInt("codExame"));
                objExame.setNome(rs.getString("nome"));
                objExame.setDescricao(rs.getString("descricao"));
                objExame.setHora(rs.getString("hora"));
                
                java.sql.Date dt = rs.getDate("data");
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                objExame.setData(c);
                objExame.setObjPacinte(objDAOPaciente.localizar(rs.getInt("codPaciente")));
                objExame.setObjMedico(objDAOMedico.localizar(rs.getInt("codMedico")));
                listaExame.add(objExame);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL no List no DAOExame: "+ex.getMessage());
        }
        return listaExame;
    }
    
    public boolean incluir(Exame objExame){
        String sql="insert into Exame(nome, descricao, data, hora, codPaciente, codMedico) values(?,?,?,?,?,?)";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objExame.getNome());
            pst.setString(2, objExame.getDescricao());
            pst.setDate(3, new java.sql.Date(objExame.getData().getTimeInMillis()));
            pst.setString(4, objExame.getHora());
            pst.setInt(5, objExame.getObjPacinte().getCodigo());
            pst.setInt(6, objExame.getObjMedico().getCodigo());
            
            if(pst.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Exame cadastrado com sucesso");
            return true;
        }else{
                JOptionPane.showMessageDialog(null, "Exame não cadastrado");
                return false;
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL no incluir no DAOExame: "+ ex.getMessage());
            return false;
        }
    }
    
    public boolean alterar(Exame objExame){
        String sql="update Exame set nome=?,descricao=?,data=?,hora=?,codPaciente=?,codMedico=? where codExame=?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objExame.getNome());
            pst.setString(2, objExame.getDescricao());
            pst.setDate(3, new java.sql.Date(objExame.getData().getTimeInMillis()));
            pst.setString(4, objExame.getHora());
            pst.setInt(5, objExame.getObjPacinte().getCodigo());
            pst.setInt(6, objExame.getObjMedico().getCodigo());
            pst.setInt(7, objExame.getCodigo());
            if(pst.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Exame alterado com sucesso");
            return true;
        }else{
                JOptionPane.showMessageDialog(null, "Exame não alterado");
                return false;
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL no Alterar no DAOExame: "+ ex.getMessage());
            return false;
        }
    }
    
    public boolean salvar(Exame obj){
        if(obj.getCodigo() == null){
            return incluir(obj);
        }else{
            return alterar(obj);
        }
    }
    
    public boolean remover(Exame objExame){
        String sql="delete from Exame where codExame=?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, objExame.getCodigo());
            if(pst.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Exame excluido com sucesso");
            return true;
        }else{
                JOptionPane.showMessageDialog(null, "Exame não excluido");
                return false;
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL no remover no DAOExame: "+ ex.getMessage());
            return false;
        }
    }
    
}
