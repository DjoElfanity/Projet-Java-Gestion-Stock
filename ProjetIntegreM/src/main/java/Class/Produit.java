package Class;

import Utility.SingletonConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Frames.intA;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import Frames.*;
import static Frames.initC.jID;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class Produit {
    
    public int id ,quantite; 
    public double prix ;
    public String nom , categorie;

    public Produit(int id, String nom, String categorie, double prix, int quantite) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.prix = prix;
        this.quantite = quantite;
    }
    
    public Produit()
    {
        
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", categorie=" + categorie + ", prix=" + prix + ", quantite=" + quantite + '}';
    }
    
    public static boolean creatProduit(Produit P) throws SQLException{
        boolean res=false;
        int a =0;
     try{   
        //Connection
        Connection conn = SingletonConnection.getConnection();
        
         String sql ="INSERT INTO `projetintegre`.`tbl_produit`(`nom`,`categorie`,`prix`,`quantite`)VALUES(?,?,?,?)";
        //PreparedStatement
                PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1,P.nom);
         pstmt.setString(2,P.categorie);
         pstmt.setDouble(3,P.prix);
         pstmt.setInt(4,P.quantite);

            
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
    
    
    public static boolean Supprimer_Produit(int numR){
        boolean res=false;
        int a=0;
        try{
            Connection conn= SingletonConnection.getConnection();
            String sql = "DELETE FROM `projetintegre`.`tbl_produit` WHERE (`idtbl_produit` = '"+numR+"')";
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
    public static Produit get_produit(int numR){
        boolean res=false;
        Produit p1 = new Produit();
       int a=0;
       //"SELECT `tbl_produit`.`nom`,`tbl_produit`.`categorie`,`tbl_produit`.`prix`,`tbl_produit`.`quantite` FROM `projetintegre`.`tbl_produit` WHERE (`idtbl_produit` = '"+numR+"')"
       Connection cnx = SingletonConnection.getConnection();
           String requete = "SELECT * FROM `projetintegre`.`tbl_produit` WHERE (`idtbl_produit` = '"+numR+"')";
           PreparedStatement ps;
            try {
                ps = cnx.prepareStatement(requete);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {    p1.id=rs.getInt(1);
                     p1.nom = rs.getString(2);
                     p1.categorie = rs.getString(3);
                     p1.prix = rs.getDouble(4);
                     p1.quantite = rs.getInt(5);
                }
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
        
     
        
        
        
        return p1;
    }
    public static boolean ModifyProduit(Produit P,int numR){
        boolean res=false;
        int a =0;
     try{   
        //Connection
        Connection conn = SingletonConnection.getConnection();
        
         String sql ="UPDATE `projetintegre`.`tbl_produit`\n" +
"SET`nom` = '"+P.nom+"',\n" +
"`categorie` ='"+P.categorie+"',\n" +
"`prix` = '"+P.prix+"',\n" +
"`quantite` = '"+P.quantite+"'\n" +
"WHERE `idtbl_produit` ='"+numR+"'";
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




public static boolean Ajout_produit_panier(int numR ,Produit P,int idU){
  
       boolean res=false;
        int a =0;
 
    
        //Connection
        Connection conn = SingletonConnection.getConnection();
        String rq ="SELECT idtbl_produit  FROM projetintegre.tbl_panier\n" +
"WHERE (`idtbl_produit` = '"+numR+"')";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(rq);
              ResultSet rs = ps.executeQuery();
              if(rs.next()){
                  //////////////////Update////////////
                  int b =0,c=0;
                            try{   
       
                Connection con = SingletonConnection.getConnection(); 
                String req="UPDATE `projetintegre`.`tbl_panier` SET`quantite` = `quantite`+1 WHERE `idtbl_produit` = '"+numR+"'and `idtbl_user` = '"+idU+"'";
                String r =" Update `projetintegre`.`tbl_produit` Set quantite=quantite-1 WHERE `idtbl_produit` = '"+numR+"' and `idtbl_user` = '"+idU+"'";
                 PreparedStatement pstm = (PreparedStatement) con.prepareStatement(req);
                  PreparedStatement pre = (PreparedStatement) con.prepareStatement(r);
      c=pre.executeUpdate();            
    b =pstm.executeUpdate();
    if (b==1)   {
        
              JOptionPane.showMessageDialog(null, "ajout done !!");
              res=true;
         } 
}catch(SQLException ex){
         
           JOptionPane.showMessageDialog(null, "Attention !!! Probleme de update! ");
     }
              }else{
                  ////////////Ajooooout///
                    Connection conx = SingletonConnection.getConnection();
                    try{ 
                    String sql ="INSERT INTO `projetintegre`.`tbl_panier`(`idtbl_produit`,`nom`,`categorie`,`prix`,`quantite`,`idtbl_user`)VALUES(?,?,?,?,?,?)";
        //PreparedStatement
                PreparedStatement pst = (PreparedStatement) conx.prepareStatement(sql);
             pst.setInt(1,P.id);   
            pst.setString(2,P.nom);
         pst.setString(3,P.categorie);
         pst.setDouble(4,P.prix);
         
         pst.setInt(5,1);
        pst.setInt(6, Integer.parseInt(initC.jID.getText()));
            
            //  Execute Update :
             a =pst.executeUpdate();
         if (a==1)   {
              JOptionPane.showMessageDialog(null, "Done !!");
              res=true;
              
                 String r =" Update `projetintegre`.`tbl_produit` Set quantite=quantite-1 WHERE `idtbl_produit` = '"+numR+"' and `idtbl_user` = '"+idU+"'";
                 
                  PreparedStatement pre = (PreparedStatement) conx.prepareStatement(r);
     int y =0;           
    y =pst.executeUpdate();
              
              
         } else{
              JOptionPane.showMessageDialog(null, " probleme d ajout!!");
                        }  }
                 catch (SQLException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        } 
                  
                  
                  
                  
              }
              
        } catch (SQLException ex) {
            Logger.getLogger(Produit.class.getName()).log(Level.SEVERE, null, ex);
        }
              
   
  
        
      


return res;

}}

