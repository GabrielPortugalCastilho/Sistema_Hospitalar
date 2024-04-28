/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author gabri
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usuario = new javax.swing.JLabel();
        Senha = new javax.swing.JLabel();
        usuario2 = new javax.swing.JTextField();
        senha2 = new javax.swing.JPasswordField();
        entrar = new javax.swing.JButton();
        exit = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 300));
        getContentPane().setLayout(null);

        usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usuario.setForeground(new java.awt.Color(255, 255, 255));
        usuario.setText("Usuario:");
        getContentPane().add(usuario);
        usuario.setBounds(100, 130, 57, 20);

        Senha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Senha.setForeground(new java.awt.Color(255, 255, 255));
        Senha.setText("Senha:");
        getContentPane().add(Senha);
        Senha.setBounds(100, 170, 48, 17);
        getContentPane().add(usuario2);
        usuario2.setBounds(170, 130, 490, 20);
        getContentPane().add(senha2);
        senha2.setBounds(170, 170, 490, 20);

        entrar.setText("Entrar");
        entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entrarActionPerformed(evt);
            }
        });
        getContentPane().add(entrar);
        entrar.setBounds(240, 220, 83, 32);

        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        getContentPane().add(exit);
        exit.setBounds(410, 220, 51, 32);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/loadin.png"))); // NOI18N
        jLabel2.setMinimumSize(new java.awt.Dimension(500, 300));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(-590, -210, 1450, 570);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
         System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entrarActionPerformed
        // TODO add your handling code here:
        if(usuario2.getText().equals("Teste") && senha2.getText().equals("12345")){
            FormPrincipal principal = new FormPrincipal();
            principal.setVisible(true);
            principal.setLocationRelativeTo(null);
            
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Acesso Negado!");
        }
    }//GEN-LAST:event_entrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
                
                Login login = new Login();
                login.setLocationRelativeTo(null);//centraliza a tela
                login.setVisible(true);//mostra a janela
                login.setResizable(false);//retira o botão maximizar
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Senha;
    private javax.swing.JButton entrar;
    private javax.swing.JToggleButton exit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField senha2;
    private javax.swing.JLabel usuario;
    private javax.swing.JTextField usuario2;
    // End of variables declaration//GEN-END:variables
}