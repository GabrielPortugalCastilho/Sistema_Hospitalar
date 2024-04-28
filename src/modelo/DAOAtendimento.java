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
public class DAOAtendimento {
    
    DAOPaciente objDAOPaciente = new DAOPaciente();
    DAOMedico objDAOMedico = new DAOMedico();
    
    public List<Atendimento> getLista(){
        String sql = "select * from atendimento";
        List<Atendimento> listaAtendimento = new ArrayList<>();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Atendimento objAtendimento = new Atendimento();
                objAtendimento.setCodigo(rs.getInt("codAtendimento"));
                objAtendimento.setPlano(rs.getString("plano"));
                
                java.sql.Date dt = rs.getDate("data");
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                objAtendimento.setData(c);
                
                objAtendimento.setObjPacinte(objDAOPaciente.localizar(rs.getInt("codPaciente")));
                objAtendimento.setObjMedico(objDAOMedico.localizar(rs.getInt("codMedico")));
                listaAtendimento.add(objAtendimento);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL no List no DAOAtendimento: "+ex.getMessage());
        }
        return listaAtendimento;
    }
    
    
    public boolean incluir(Atendimento objAtendimento){
        String sql="insert into Atendimento(plano, data, codPaciente, codMedico) values(?,?,?,?)";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objAtendimento.getPlano());
            pst.setDate(2, new java.sql.Date(objAtendimento.getData().getTimeInMillis()));
            pst.setInt(3, objAtendimento.getObjPacinte().getCodigo());
            pst.setInt(4, objAtendimento.getObjMedico().getCodigo());
            if(pst.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Atendimento cadastrado com sucesso");
            return true;
        }else{
                JOptionPane.showMessageDialog(null, "Atendimento não cadastrado");
                return false;
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL no incluir no DAOAtendimeto: "+ ex.getMessage());
            return false;
        }
    }
    
    public boolean alterar(Atendimento objAtendimento){
        String sql="update Atendimento set plano=?,data=?,codPaciente=?,codMedico=? where codAtendimento=?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, objAtendimento.getPlano());
            pst.setDate(2, new java.sql.Date(objAtendimento.getData().getTimeInMillis()));
            pst.setInt(3, objAtendimento.getObjPacinte().getCodigo());
            pst.setInt(4, objAtendimento.getObjMedico().getCodigo());
            pst.setInt(5, objAtendimento.getCodigo());
            if(pst.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Atendmento alterado com sucesso");
            return true;
        }else{
                JOptionPane.showMessageDialog(null, "Atendimento não alterado");
                return false;
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL no Alterar no DAOAtendimento: "+ ex.getMessage());
            return false;
        }
    }
    
    public boolean salvar(Atendimento obj){
        if(obj.getCodigo() == null){
            return incluir(obj);
        }else{
            return alterar(obj);
        }
    }
    
    public boolean remover(Atendimento objAtendimento){
        String sql="delete from Atendimento where codAtendimento=?";
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, objAtendimento.getCodigo());
            if(pst.executeUpdate() > 0){
            JOptionPane.showMessageDialog(null, "Atendimento excluido com sucesso");
            return true;
        }else{
                JOptionPane.showMessageDialog(null, "Atendimento não excluido");
                return false;
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro de SQL no remover no DAOAtendimento: "+ ex.getMessage());
            return false;
        }
    }
    
}
