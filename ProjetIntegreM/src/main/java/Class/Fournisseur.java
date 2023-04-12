/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import Utility.SingletonConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class Fournisseur {
    public int id ; 
    public String nom , prenom , num , email , adresse ; 
    
    
    public Fournisseur()
    {
        
    }

    public Fournisseur(int id, String nom, String prenom, String num, String email, String adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.num = num;
        this.email = email;
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Fournisseur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", num=" + num + ", email=" + email + ", adresse=" + adresse + '}';
    }
    
    
    public static boolean creatFournisseur(Fournisseur F) throws SQLException{
        boolean res=false;
        int a =0;
     try{   
        //Connection
        Connection conn = SingletonConnection.getConnection();
        
         String sql ="INSERT INTO `projetintegre`.`tbl_fournisseur`(`nom`,`prenom`,`num`,`email`,`adresse`)VALUES(?,?,?,?,?)";
        //PreparedStatement
                PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1,F.nom);
        pstmt.setString(2, F.prenom);
          pstmt.setString(3,F.num);
          pstmt.setString(4,F.email);
         pstmt.setString(5,F.adresse);
       

            
            //  Execute Update :
             a =pstmt.executeUpdate();
         if (a==1)   {
              JOptionPane.showMessageDialog(null, "Done !!");
              res=true;
         }
     }
     catch(SQLException ex){
         
            JOptionPane.showMessageDialog(null, "Attention !!! Probleme d'ajout ! ");
     }
        
        
        return res;
        
    }
    public static boolean Supprimer_Fournisseur(int numR){
        boolean res=false;
        int a=0;
        try{
            Connection conn= SingletonConnection.getConnection();
            String sql = "DELETE FROM `projetintegre`.`tbl_fournisseur` WHERE (`idtbl_fournisseur` = '"+numR+"')";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

              a =pstmt.executeUpdate();
         if (a==1)   {
              JOptionPane.showMessageDialog(null, "Done !!");
              res=true;
         }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Probleme de suppression  !!");
        }
        
        return res;
        }
        
         public static Fournisseur get_Fournisseur(int numR){
        boolean res=false;
        Fournisseur f1 = new Fournisseur();
       int a=0;
       //"SELECT `tbl_produit`.`nom`,`tbl_produit`.`categorie`,`tbl_produit`.`prix`,`tbl_produit`.`quantite` FROM `projetintegre`.`tbl_produit` WHERE (`idtbl_produit` = '"+numR+"')"
       Connection cnx = SingletonConnection.getConnection();
           String requete = "SELECT `tbl_fournisseur`.`nom`,`tbl_fournisseur`.`prenom`,`tbl_fournisseur`.`num`,`tbl_fournisseur`.`email`,`tbl_fournisseur`.`adresse` FROM `projetintegre`.`tbl_fournisseur` WHERE (`idtbl_fournisseur` = '"+numR+"')";
           PreparedStatement ps;
            try {
                ps = cnx.prepareStatement(requete);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                     f1.nom = rs.getString(1);
                     f1.prenom = rs.getString(2);
                     f1.num = rs.getString(3);
                     f1.email = rs.getString(4);
                      f1.adresse = rs.getString(4);
                }
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
        
     
        
        
        
        return f1;
    
    }
          public static boolean ModifyFournisseur(Fournisseur f,int numR){
        boolean res=false;
        int a =0;
     try{   
        //Connection
        Connection conn = SingletonConnection.getConnection();
        
         String sql ="UPDATE `projetintegre`.`tbl_fournisseur`\n" +
"SET `nom` ='"+f.nom+"' ,\n" +
"`prenom` ='"+f.prenom+"',\n" +
"`num` = '"+f.num+"',\n" +
"`email` = '"+f.email+"',\n" +
"`adresse` = '"+f.adresse+"'\n" +
"WHERE `idtbl_fournisseur` ='"+numR+"'";
        //PreparedStatement
                PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
          
            
            //  Execute Update :
             a =pstmt.executeUpdate();
         if (a==1)   {
              JOptionPane.showMessageDialog(null, "Done !!");
              res=true;
         }
     }
     catch(SQLException ex){
         
            JOptionPane.showMessageDialog(null, "Attention !!! Probleme de modification ! ");
     }
        return res;
    }
    
}
