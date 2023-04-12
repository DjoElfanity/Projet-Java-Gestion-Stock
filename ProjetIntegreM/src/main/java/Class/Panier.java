/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import static Frames.initC.jID;
import Utility.SingletonConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class Panier {
    
    public int idP  ,quantite;
    public double prix ; 
    public String nom , categorie ;  
    
    
    
    public Panier()
    {
        
    }

    public Panier(int idP, int quantite, double prix, String nom, String categorie) {
        this.idP = idP;
        this.quantite = quantite;
        this.prix = prix;
        this.nom = nom;
        this.categorie = categorie;
    }
    
    public static void suppall_product(){
          int a =0;
     try{   
        //Connection
        Connection conn = SingletonConnection.getConnection();
         int id = Integer.parseInt(jID.getText());
         String sql ="DELETE FROM `projetintegre`.`tbl_panier`where `idtbl_user` = '"+id+"'";
        //PreparedStatement
                PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
           

            
            //  Execute Update :
             a =pstmt.executeUpdate();
         if (a==1)   {
              JOptionPane.showMessageDialog(null, "Done !!");
            
         }
     }
     catch(SQLException ex){
         
            JOptionPane.showMessageDialog(null, "Attention !!! Probleme de supression ! ");
     }
    }
    
    public static void suppUn_product(int numR){
          int a =0;
     try{   
        //Connection
        Connection conn = SingletonConnection.getConnection();
        
         String sql ="DELETE  FROM `projetintegre`.`tbl_panier`WHERE (`idtbl_produit` = '"+numR+"')";
        //PreparedStatement
                PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
           

            
            //  Execute Update :
             a =pstmt.executeUpdate();
         if (a==1)   {
              JOptionPane.showMessageDialog(null, "Done !!");
            
         }
     }
     catch(SQLException ex){
         
            JOptionPane.showMessageDialog(null, "Attention !!! Probleme de supression ! ");
     }
    }
    
}
