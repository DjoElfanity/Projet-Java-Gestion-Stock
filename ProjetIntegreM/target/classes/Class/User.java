/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import Frames.*;

/**
 *
 * @author Dell
 */
public class User {
    
    public int id ; 
    public String  nom , prenom ,num, adresse , email , login , password , role; 
    
    
    public User()
    {
        
    }

    public User(int id, String nom, String prenom,String num, String adresse, String email, String login, String password, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.num=num;
        this.adresse = adresse;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", num=" + num + ", adresse=" + adresse + ", email=" + email + ", login=" + login + ", password=" + password + ", role=" + role + '}';
    }

   
    
    
    public static boolean authentification ( String login , String password)
    {
        boolean res = false; 
        Connection cnx = SingletonConnection.getConnection();
        String requete = "SELECT * FROM `tbl_user` WHERE `login` = '"+login+"' AND `password` = '"
                +password+"'";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
          if (rs.next())
          {
              res =true; 
              
          }
          
          else {
              res=false;
                Authentification.jRefus.setText("Nom de compte ou mot de passe incorect");
          }
              
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        
        
        return res; 
        
        
    }
    
    public static User choix_page (String login , String password)
    {
        
        String choix = "",id="";
       User u = new User();
        boolean x = authentification(login, password); 
        if (x == true)
        {
           Connection cnx = SingletonConnection.getConnection();
           String requete = "SELECT Role, idtbl_user FROM `tbl_user` WHERE `login` = '"+login+"' AND `password` = '"
                +password+"'";
           PreparedStatement ps;
            try {
                ps = cnx.prepareStatement(requete);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                     u.role = rs.getString(1);
                     u.id=rs.getInt(2);
                }
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
              
            }
            
           
        }
     
        
        
        
        return u;
    }
    
    public static User profil_set(User u,int id){
        User C1= new User();
           Connection cnx = SingletonConnection.getConnection();
        String requete = "SELECT `tbl_user`.`idtbl_user`,`tbl_user`.`nom`,`tbl_user`.`prenom`,`tbl_user`.`num`,`tbl_user`.`adresse`,`tbl_user`.`Email`,`tbl_user`.`login`,`tbl_user`.`password`\n" +
"FROM `projetintegre`.`tbl_user` WHERE `idtbl_user` = '"+id+"'";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
          if (rs.next())
          {  C1.id=rs.getInt(1);
              C1.nom = rs.getString(2);
                     C1.prenom = rs.getString(3);
                     C1.num = rs.getString(4);
                     C1.adresse = rs.getString(5);
                     C1.email = rs.getString(6);
                     C1.login=rs.getString(7);
                     C1.password=rs.getString(8);
                     
                  //jEmailP.setText(C1.email);
        
              
          }
          
         
              
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return C1 ;
    }
    
    
    
    
    public static boolean creatClient(User C) throws SQLException{
        boolean res=false;
        int a =0;
     try{   
        //Connection
        Connection conn = SingletonConnection.getConnection();
        
         String sql ="INSERT INTO `projetintegre`.`tbl_user`(`nom`,`prenom`,`num`,`adresse`,`Email`,`login`,`password`,`Role`)VALUES(?,?,?,?,?,?,?,'C')";
        //PreparedStatement
                PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setString(1,C.nom);
         pstmt.setString(2, C.prenom);
         pstmt.setString(3, C.num);
         pstmt.setString(4,C.adresse);
         pstmt.setString(5,C.email);
          pstmt.setString(6,C.login);
           pstmt.setString(7,C.password);
            
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
     public static boolean Supprimer_Client(int numR){
        boolean res=false;
        int a=0;
        try{
            Connection conn= SingletonConnection.getConnection();
            String sql = "DELETE FROM `projetintegre`.`tbl_user`\n" +"WHERE (`idtbl_user` = '"+numR+"')";
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
    
     public static User get_User(int numR){
        boolean res=false;
        User C1 = new User();
       int a=0;
       //"SELECT `tbl_produit`.`nom`,`tbl_produit`.`categorie`,`tbl_produit`.`prix`,`tbl_produit`.`quantite` FROM `projetintegre`.`tbl_produit` WHERE (`idtbl_produit` = '"+numR+"')"
       Connection cnx = SingletonConnection.getConnection();
           String requete = "SELECT `tbl_user`.`idtbl_user`,`tbl_user`.`nom`,`tbl_user`.`prenom`,`tbl_user`.`num`,`tbl_user`.`adresse`,`tbl_user`.`Email`,`tbl_user`.`login`,`tbl_user`.`password`,`tbl_user`.`Role`FROM `projetintegre`.`tbl_user` WHERE (`idtbl_user` = '"+numR+"')";
           PreparedStatement ps;
            try {
                ps = cnx.prepareStatement(requete);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    C1.id=rs.getInt(1);
              C1.nom = rs.getString(2);
                     C1.prenom = rs.getString(3);
                     C1.num = rs.getString(4);
                     C1.adresse = rs.getString(5);
                     C1.email = rs.getString(6);
                     C1.login=rs.getString(7);
                     C1.password=rs.getString(8);
                }
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
        
     
        
        
        
        return C1;
    }
     
     public static boolean ModifyUser(User C,int numR){
        boolean res=false;
        int a =0;
     try{   
        //Connection
        Connection conn = SingletonConnection.getConnection();
        
         String sql ="UPDATE `projetintegre`.`tbl_user`\n" +
"SET `nom` ='"+C.nom+"' ,\n" +
"`prenom` ='"+C.prenom+"',\n" +
"`num` = '"+C.num+"',\n" +
"`adresse` = '"+C.adresse+"',\n" +
"`Email` = '"+C.email+"',\n" +
"`login` = '"+C.login+"',\n" +
"`password` = '"+C.password+"'\n" +
"WHERE `idtbl_user` ='"+numR+"'";
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


public static void Update_Profil(User u,int id){
    int a=0;
      try{  
    Connection conn = SingletonConnection.getConnection();
        
         String sql ="UPDATE `projetintegre`.`tbl_user`\n" +

" SET `num` = '"+u.num+"',\n" +
"`adresse` = '"+u.adresse+"',\n" +
"`Email` = '"+u.email+"',\n" +
"`login` = '"+u.login+"',\n" +
"`password` = '"+u.password+"'\n" +
"WHERE `idtbl_user` ='"+id+"'";
        //PreparedStatement
                PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
          
            
            //  Execute Update :
             a =pstmt.executeUpdate();
         if (a==1)   {
              JOptionPane.showMessageDialog(null, "Done !!");
             
         }
     }
     catch(SQLException ex){
         
            JOptionPane.showMessageDialog(null, "Attention !!! Probleme de modification ! ");
     }
    
}







}

     
    

