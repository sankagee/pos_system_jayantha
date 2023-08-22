/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INF;

import CODE.DBConnect;
import CODE.FontUtils;
import com.sun.tools.javac.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author SASITHA HASHAN
 */
public class MainPage extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    PreparedStatement pst1 = null;
    ResultSet rs = null;
    String creditorId = null;
    String pid = null;
    String ctime="";
    String cdate="";
    String Usertype="";
    
    public MainPage() throws SQLException {
        initComponents();
        conn = DBConnect.connect();
        txtQuantity.setEnabled(false);
        getCurrentDateTime();
        getCreditorData();
        createCreId();
        fetchCreditordat();
        //fetchProductData();
   
        fetchEmpty();
        jTextField11.setText("Salt");
        jTextField5.setEditable(false);
        jTextField4.setEditable(false);
        jr1.setSelected(true);
        jkg.addItem("-select-");
        jkg.setSelectedIndex(0);
        jRadioButton1.setSelected(true);
        jTextField9.setEnabled(false);
        jTextField9.setEditable(false);
        jRadioButton5.setSelected(true);
        jRadioButton9.setSelected(true);
          
       
    }
    public void getUserD(String name,String uType) {
        jLabel20.setText(name);
        this.Usertype=uType;
        if(uType.equals("User")){
                        jButton14.setVisible(false);
                        jButton23.setVisible(false);
                        jButton24.setVisible(false);
                        jButton25.setVisible(false);
                        jLabel6.setVisible(false);
                        jLabel18.setVisible(false);
                        txtPrice.setVisible(false);
                        jLabel59.setVisible(false);
                        jTextField33.setVisible(false);
                        jTextField4.setVisible(false);
                        jButton5.setVisible(false);
                        jButton32.setVisible(false);
        jTable1.getColumnModel().getColumn(8).setMinWidth(0);
        jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(9).setMinWidth(0);
        jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
        }
    }

    public void createCreId() {

//        try {
//            java.sql.Statement p = conn.createStatement();
//            rs = p.executeQuery("SELECT Max(creditor_id)FROM creditor_table");
//            rs.next();
//
//            rs.getString("Max(creditor_id)");
//            if (rs.getString("Max(creditor_id)") == null) {
//
//                creditorId = "000000001";
//
//            } else {
//                long id = Long.parseLong(rs.getString("Max(creditor_id)").substring(2, rs.getString("Max(creditor_id)").length()));
//                id++;
//                creditorId = String.format("%09d", id);
//
//            }
//
//        } catch (NumberFormatException | SQLException e) {
//        }
    }
//fetch creditor table
    public void fetchData() {
        String name = txtCreditorName.getText();
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = d.format(jDateChooser1.getDate());
        try {
            String s = "SELECT * FROM creditor_table WHERE creditor_name ='" + name + "' AND date='" + fdate + "'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    //fetch empty product data
    public void fetchEmpty(){
     try {
            String s = "SELECT * FROM creditor_table WHERE creditor_name='"+0000+"'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            jTable1.getColumnModel().getColumn(1).setMinWidth(170);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(170);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    
    }
//fetch added product data
 public void fetchProductData(String c){
 try {
            String s = "SELECT * FROM creditor_table WHERE creditor_name='"+c+"'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            jTable1.getColumnModel().getColumn(1).setMinWidth(170);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(170);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
 
 }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void setDataCre() throws ParseException {
        
        
        int row = jTable1.getSelectedRow();
        String id = jTable1.getValueAt(row, 0).toString();
        String creditor_name = jTable1.getValueAt(row, 1).toString();
        String lorrynum = jTable1.getValueAt(row, 2).toString();
        String cat = jTable1.getValueAt(row, 3).toString();
        String kg = jTable1.getValueAt(row, 4).toString();
        String qun = jTable1.getValueAt(row, 5).toString();
        String totalkg = jTable1.getValueAt(row, 6).toString();
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse((String) jTable1.getValueAt(row, 7).toString());
        String price = jTable1.getValueAt(row, 9).toString();
        String totalprice = jTable1.getValueAt(row, 10).toString();
//        String status=jTable1.getValueAt(row, 10).toString();

        jLabel11.setText(id);
        txtCreditorName.setText(creditor_name);
        jTextField3.setText(lorrynum);

        
        jcategory.setSelectedItem(cat);
       
         
        jkg.addItem(kg);
        
        jkg.setSelectedItem(kg);
      
        jTextField2.setText(qun);
        jTextField5.setText(totalkg);

        jDateChooser1.setDate(date1);
        txtPrice.setText(price);
        jTextField4.setText(totalprice);
        
    }

    //fetch category
    public void fetchCatInProduct() {

        

        try {
            String f1 = "SELECT category_name FROM category GROUP BY category_name";
            pst = conn.prepareStatement(f1);
            rs = pst.executeQuery();
            //jTable8.setModel(DbUtils.resultSetToTableModel(rs));
            while (rs.next()) {

                String c = rs.getString("category_name");
                jcategory.addItem(c);

            }
            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(this , e);
        }

       
  
    }
    // fetch data to sale list tab
    public void setDataToSaleList()throws SQLException{
                    String sq ="SELECT * FROM set_sales ORDER BY status desc";
                    pst = conn.prepareStatement(sq);
                    rs = pst.executeQuery();
                    jTable11.setModel(DbUtils.resultSetToTableModel(rs));
    
    }
//fetch data to creditor table after checking
    public void fetchCreditordat() throws SQLException{
    
                    String sq ="SELECT id,name,lorry_number,date,status  FROM set_status ORDER BY status desc";
                    pst = conn.prepareStatement(sq);
                    rs = pst.executeQuery();
                    jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    
    }
    //insert data to sales table
    public void insertDataToSales(String dealer,String Ktype,String qty,String totalQty,String p,Double Tp){
        String name=jTextField7.getText();
        String mn=jTextField8.getText();
        String ln=jTextField9.getText();
        SimpleDateFormat g1 = new SimpleDateFormat("yyyy-MM-dd");
        String dat = g1.format(jDateChooser2.getDate());
        String cat=jTextField11.getText();
        String kg=jTextField12.getText();
        String qua=jTextField13.getText();
        String totalKg=jTextField14.getText();
        String price=jTextField15.getText();
        String totalP=jTextField16.getText();  
        String status="pending";
        String route=jComboBox2.getSelectedItem().toString();
        String collectr=jTextField45.getText();
        String bill=jLabel85.getText();
        try {
             String insert="INSERT INTO sales_list(bill,dealer,dealer_name,mobile,lorry_number,date,category,type_kg,quantity,total_kg,price_per_1kg,total_price,route,collector,status)"
                     + "VALUES('"+bill+"','"+dealer+"','"+name+"','"+mn+"','"+ln+"','"+dat+"','"+cat+"','"+Ktype+"','"+qty+"','"+totalQty+"','"+p+"','"+Tp+"','"+route+"','"+collectr+"','"+status+"')";
            pst = conn.prepareStatement(insert);
            pst.execute();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
         
               
                   
    //getSalesData(dealer);
    }
    //get sales data and set it in set sales table
    public void getSalesData(String dealer){
                SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = d.format(jDateChooser2.getDate());
                
               try {
                String n = jTextField7.getText();
                String bill_no=jLabel85.getText();
                String st ="pending";
                String sum_of_arius="";
                String j = "SELECT bill,dealer,dealer_name,mobile,lorry_number,date,category,type_kg,quantity,total_kg,price_per_1kg,sum(total_price),route,collector,status FROM sales_list WHERE dealer='"+dealer+"' AND status='"+st+"' AND dealer_name='"+n+"' AND date='"+addDate+"' AND bill='"+bill_no+"'";
                pst = conn.prepareStatement(j);
                rs = pst.executeQuery();
                
                 if(rs.next()){
                    String cname = rs.getString("dealer_name");
                    String dt = rs.getString("date");
                    String dealer1 = rs.getString("dealer");
                    String sta = rs.getString("status");
                    String bill=rs.getString("bill");
                    sum_of_arius=rs.getString("sum(total_price)");
                    
                    String sq ="SELECT * FROM set_sales WHERE dealer_name='"+cname+"' AND date='"+dt+"' AND dealer='"+dealer1+"'";
                    pst = conn.prepareStatement(sq);
                    rs = pst.executeQuery();
                            
                            if(rs.next()){
                             String cn = rs.getString("dealer_name");
                             String dtt = rs.getString("date");
                             String bill2=rs.getString("bill_number");
                             String update="UPDATE set_sales SET status='"+"pending"+"' WHERE dealer_name='"+cn+"' AND date='"+dtt+"' AND bill_number='"+bill2+"'";
                             pst = conn.prepareStatement(update);
                             pst.execute();
                             UpdateAriusTable(cname,dt,sum_of_arius);
                            }else{
                            
                             String insert="INSERT INTO set_sales(bill_number,dealer,dealer_name,date,status)VALUES('"+bill+"','"+dealer+"','"+n+"','"+dt+"','"+sta+"')";
                             pst = conn.prepareStatement(insert);
                             pst.execute();
                            
                           insertDataToArius(cname,dt,sum_of_arius,"0"); 
                           //UpdateAriusTable(cname,dt,sum_of_arius);
                            }
                 }else{
                String n1 = jTextField7.getText();
                String st1 ="completed";
                String j1 = "SELECT SELECT bill,dealer,dealer_name,mobile,lorry_number,date,category,type_kg,quantity,total_kg,price_per_1kg,sum(total_price),route,collector,status FROM sales_list WHERE dealer='"+dealer+"' AND status='"+st1+"' AND dealer_name='"+n1+"' AND date='"+addDate+"'";
                pst = conn.prepareStatement(j1);
                rs = pst.executeQuery();
                 
                  if(rs.next()){
                    String cname = rs.getString("dealer_name");
                    String dt = rs.getString("date");
                    String dealer1 = rs.getString("dealer");
                    String sta = rs.getString("status");
                    String bill=rs.getString("bill");
                   sum_of_arius=rs.getString("sum(total_price)");
                    String sq ="SELECT * FROM set_sales WHERE dealer_name='"+cname+"' AND date='"+dt+"' AND dealer='"+dealer1+"'AND bill_number='"+bill+"'";
                    pst = conn.prepareStatement(sq);
                    rs = pst.executeQuery();
                    
                      if(rs.next()){
                            String cn = rs.getString("dealer_name");
                            String dtt = rs.getString("date");
                            String update="UPDATE set_sales SET status='"+"complted"+"' WHERE dealer_name='"+cn+"' AND date='"+dtt+"' AND bill_number='"+bill+"'";
                            pst = conn.prepareStatement(update);
                            pst.execute();
                            }else{
                            
                             String insert="INSERT INTO set_sales(bill_number,dealer,dealer_name,date,status)VALUES('"+bill+"','"+dealer+"','"+n+"','"+dt+"','"+sta+"')";
                             pst = conn.prepareStatement(insert);
                             pst.execute();
                            
                            
                            }
                  }
                 }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    
    
    }
    //insert data to arius amout table
    public void insertDataToArius(String name,String date,String arius_amount,String complete_amount ){
    
           try {
            String sql="INSERT INTO arius_amount(name,date,arius_amount,complete_amount	)VALUES('"+name+"','"+date+"','"+arius_amount+"','"+complete_amount+"')";
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
        }
 //code end here   
    }
  //update arius table
    public void UpdateAriusTable(String name,String date,String arius_amoun){
        try {
            String sql="UPDATE arius_amount SET arius_amount=arius_amount+'"+arius_amoun+"' WHERE name='"+name+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
        }
    }
  
//code end here
    
    //check if there is any pending order under perticular creditor
    public void getCreditorData() {

        try {
            
                SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = d.format(jDateChooser1.getDate());
                
                String n = txtCreditorName.getText();
                String st ="pending";
                String j = "SELECT creditor_name,lorry_number,date,status FROM creditor_details WHERE status='"+st+"' AND creditor_name='"+n+"' AND date='"+addDate+"' ";
                pst = conn.prepareStatement(j);
                rs = pst.executeQuery();
            
                    if(rs.next()){
                    String cname = rs.getString("creditor_name");
                    String dt = rs.getString("date");
                    String ln = rs.getString("lorry_number");
                    String sta = rs.getString("status");
                    
                    String sq ="SELECT * FROM set_status WHERE name='"+cname+"' AND date='"+dt+"'";
                    pst = conn.prepareStatement(sq);
                    rs = pst.executeQuery();
                    
                        if(rs.next()){
                            String cn = rs.getString("name");
                            String dtt = rs.getString("date");
                            String update="UPDATE set_status SET status='"+"pending"+"' WHERE name='"+cn+"' AND date='"+dtt+"'";
                            pst = conn.prepareStatement(update);
                            pst.execute();
                        }else{
                             String insert="INSERT INTO set_status(name,lorry_number,date,status)VALUES('"+cname+"','"+ln+"','"+dt+"','"+sta+"')";
                             pst = conn.prepareStatement(insert);
                             pst.execute();
                        
                        }
                }else{
                
                
                String st1 ="completed";
                String j1 = "SELECT creditor_name,lorry_number,date,status FROM creditor_details WHERE status='"+st1+"' AND creditor_name='"+n+"' AND date='"+addDate+"' ";
                pst = conn.prepareStatement(j1);
                rs = pst.executeQuery(); 
                
                    if(rs.next()){
                        String cname = rs.getString("creditor_name");
                        String dt = rs.getString("date");
                        String ln = rs.getString("lorry_number");
                        String sta = rs.getString("status");
                        
                         String sq ="SELECT * FROM set_status WHERE name='"+cname+"' AND date='"+dt+"'";
                         pst = conn.prepareStatement(sq);
                         rs = pst.executeQuery();
                         
                          if(rs.next()){
                                String cn = rs.getString("name");
                                String dtt = rs.getString("date");
                                String update="UPDATE set_status SET status='"+"completed"+"' WHERE name='"+cn+"' AND date='"+dtt+"'";
                                pst = conn.prepareStatement(update);
                                pst.execute();
                        }else{
                             String insert="INSERT INTO set_status(name,lorry_number,date,status)VALUES('"+cname+"','"+ln+"','"+dt+"','"+sta+"')";
                             pst = conn.prepareStatement(insert);
                             pst.execute();
                        
                        }
                    }
                }
            
            fetchCreditordat();
   
        } catch (Exception e) {
            
            System.out.println(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        detor = new javax.swing.ButtonGroup();
        returnRadio = new javax.swing.ButtonGroup();
        returnD = new javax.swing.ButtonGroup();
        credit_or_cash = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        rightPanel = new javax.swing.JPanel();
        p1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        p2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtCreditorName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jcategory = new javax.swing.JComboBox<>();
        jkg = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        jr1 = new javax.swing.JRadioButton();
        jr2 = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        jTextField33 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtCat = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtQuan = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        p3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField45 = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jTextField46 = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JTextField();
        jRadioButton20 = new javax.swing.JRadioButton();
        jRadioButton21 = new javax.swing.JRadioButton();
        jButton39 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jLabel84 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        p4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jLabel32 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel37 = new javax.swing.JLabel();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jButton17 = new javax.swing.JButton();
        Category = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        p5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jTextField10 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jButton40 = new javax.swing.JButton();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        p6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        p7 = new javax.swing.JPanel();
        topbar = new javax.swing.JPanel();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        topbar_menu = new javax.swing.JPanel();
        tbp1 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jButton29 = new javax.swing.JButton();
        tbp2 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable14 = new javax.swing.JTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable15 = new javax.swing.JTable();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jButton30 = new javax.swing.JButton();
        tbp3 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTable16 = new javax.swing.JTable();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTable17 = new javax.swing.JTable();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jButton31 = new javax.swing.JButton();
        p8 = new javax.swing.JPanel();
        menba = new javax.swing.JPanel();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        menp = new javax.swing.JPanel();
        mep1 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jRadioButton11 = new javax.swing.JRadioButton();
        jRadioButton12 = new javax.swing.JRadioButton();
        jRadioButton13 = new javax.swing.JRadioButton();
        jRadioButton14 = new javax.swing.JRadioButton();
        jLabel69 = new javax.swing.JLabel();
        jTextField38 = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jTextField39 = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jTextField40 = new javax.swing.JTextField();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTable21 = new javax.swing.JTable();
        mep2 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
        jTextField41 = new javax.swing.JTextField();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTable22 = new javax.swing.JTable();
        mep3 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jRadioButton15 = new javax.swing.JRadioButton();
        jRadioButton16 = new javax.swing.JRadioButton();
        jRadioButton17 = new javax.swing.JRadioButton();
        jTextField42 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jTextField43 = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jTextField44 = new javax.swing.JTextField();
        jRadioButton18 = new javax.swing.JRadioButton();
        jRadioButton19 = new javax.swing.JRadioButton();
        jLabel74 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        jTable23 = new javax.swing.JTable();
        p9 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTable18 = new javax.swing.JTable();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTable19 = new javax.swing.JTable();
        jLabel61 = new javax.swing.JLabel();
        jTextField34 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jTextField35 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jTextField36 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jTextField37 = new javax.swing.JTextField();
        jButton35 = new javax.swing.JButton();
        p10 = new javax.swing.JPanel();
        topmenu = new javax.swing.JPanel();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        tmenu = new javax.swing.JPanel();
        tp1 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTable20 = new javax.swing.JTable();
        tp2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setSize(new java.awt.Dimension(1280, 720));

        mainPanel.setBackground(new java.awt.Color(255, 51, 51));
        mainPanel.setPreferredSize(new java.awt.Dimension(1280, 720));

        leftPanel.setBackground(java.awt.SystemColor.textHighlight);
        leftPanel.setPreferredSize(new java.awt.Dimension(214, 720));

        jButton1.setBackground(new java.awt.Color(51, 51, 255));
        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setText("Product");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 51, 255));
        jButton3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton3.setText("Creditor");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("JAYANTHA");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText(" STORE");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("User :");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Date :");

        jButton13.setBackground(new java.awt.Color(51, 51, 255));
        jButton13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton13.setText("Return");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(51, 51, 255));
        jButton15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton15.setText("Sales");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("jLabel20");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("jLabel20");

        jButton18.setText("LOGOUT");
        jButton18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton22.setBackground(new java.awt.Color(51, 51, 255));
        jButton22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton22.setText("Sales list");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setBackground(new java.awt.Color(51, 51, 255));
        jButton23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton23.setText("Cash/Banking");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setBackground(new java.awt.Color(51, 51, 255));
        jButton24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton24.setText("Bank/Cheque In");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setBackground(new java.awt.Color(51, 51, 255));
        jButton25.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton25.setText("Creditor Payment");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(51, 51, 255));
        jButton14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton14.setText("Stock");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton32.setBackground(new java.awt.Color(51, 51, 255));
        jButton32.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton32.setText("Report");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leftPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel10))
                            .addComponent(jLabel9)))
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leftPanelLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leftPanelLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addComponent(jButton32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        rightPanel.setBackground(new java.awt.Color(0, 0, 255));
        rightPanel.setPreferredSize(new java.awt.Dimension(1071, 720));
        rightPanel.setLayout(new java.awt.CardLayout());

        p1.setBackground(new java.awt.Color(204, 204, 255));

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.setFocusable(false);
        jTable2.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable2.setRowHeight(25);
        jTable2.setSelectionBackground(new java.awt.Color(25, 110, 51));
        jTable2.setShowVerticalLines(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton12.setText("Search");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p1Layout = new javax.swing.GroupLayout(p1);
        p1.setLayout(p1Layout);
        p1Layout.setHorizontalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p1Layout.setVerticalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        rightPanel.add(p1, "card2");

        p2.setBackground(new java.awt.Color(204, 204, 255));

        txtCreditorName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCreditorName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreditorNameActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Creditor Name");

        jcategory.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jcategory.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcategoryItemStateChanged(evt);
            }
        });
        jcategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jcategoryMouseEntered(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jcategoryMouseReleased(evt);
            }
        });
        jcategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcategoryActionPerformed(evt);
            }
        });

        jkg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jkg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jkg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkgActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Date");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Add manual kg ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Per kg (Price)");

        txtPrice.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPriceMouseClicked(evt);
            }
        });
        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });
        txtPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPriceKeyReleased(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("ADD CREDITOR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("DELETE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("UPDATE PRICE");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("CANCEL");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton11.setText("SET");
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton11MouseEntered(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Quantity");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Lorry number");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Total Amount(kg)");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("00");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Total Price(Rs)");

        txtQuantity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityKeyReleased(evt);
            }
        });

        buttonGroup1.add(jr1);
        jr1.setText("N");
        jr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jr1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jr2);
        jr2.setText("Y");
        jr2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jr2ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Activate Manual Kg");

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel53.setText("Category");

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel54.setText("Type of KG");

        jButton21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton21.setText("RESET");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel59.setText("Bill Number");

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(jr1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jr2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(44, 44, 44))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel18)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel17))
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtCreditorName, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jcategory, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel11))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(59, 59, 59)
                                                .addComponent(jLabel15)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel53)
                                        .addGap(130, 130, 130)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel54)
                                    .addComponent(jkg, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(100, 100, 100))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(151, 151, 151)
                                .addComponent(jLabel6))
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel59))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton21)
                        .addGap(21, 21, 21))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel11))
                                .addGap(11, 11, 11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCreditorName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jcategory, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton11)
                                        .addComponent(jkg, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(jLabel17))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel14)
                                .addComponent(jLabel19)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jr1)
                            .addComponent(jr2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(jLabel59)))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 46, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Category");

        txtCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCatActionPerformed(evt);
            }
        });
        txtCat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCatKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCatKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Type of KG");

        txtQuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuanActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton7.setText("Reset");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton8.setText("Add");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton9.setText("Reset");

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton10.setText("Add");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCat, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setShowGrid(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            jTable1.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            jTable1.getColumnModel().getColumn(2).setHeaderValue("Title 3");
            jTable1.getColumnModel().getColumn(3).setHeaderValue("Title 4");
        }

        javax.swing.GroupLayout p2Layout = new javax.swing.GroupLayout(p2);
        p2.setLayout(p2Layout);
        p2Layout.setHorizontalGroup(
            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(91, 91, 91)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addGap(27, 27, 27))
        );
        p2Layout.setVerticalGroup(
            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addGap(36, 36, 36))
        );

        rightPanel.add(p2, "card3");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable3);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 11, 310, 190));

        jTextField6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Items", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });
        jPanel4.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 11, 212, -1));

        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });
        jPanel4.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 231, 177, 37));

        jLabel21.setText(" Name");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 211, -1, -1));

        jLabel22.setText("Mobile Number");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 348, 177, -1));

        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });
        jPanel4.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 368, 177, 37));
        jPanel4.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 231, 168, 37));

        jLabel23.setText("Lorry Number");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 211, -1, -1));

        jLabel24.setText("Date");
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 211, -1, -1));
        jPanel4.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 300, 177, 37));

        jLabel25.setText("Category");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 280, -1, -1));
        jPanel4.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 42, 37));

        jLabel26.setText("KG");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, -1, -1));

        jLabel27.setText("Quantity");
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 210, -1, -1));

        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField13KeyReleased(evt);
            }
        });
        jPanel4.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 230, 57, 37));

        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField14KeyReleased(evt);
            }
        });
        jPanel4.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 230, 80, 37));

        jLabel28.setText("Total KG");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 210, -1, -1));

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTable7);

        jPanel4.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 423, 1015, 251));

        detor.add(jRadioButton1);
        jRadioButton1.setText("Salt");
        jRadioButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 62, 103, -1));

        detor.add(jRadioButton2);
        jRadioButton2.setText("Deliver/Detor");
        jRadioButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 62, 99, -1));

        detor.add(jRadioButton3);
        jRadioButton3.setText("Direct Customer");
        jRadioButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 103, -1, -1));

        detor.add(jRadioButton4);
        jRadioButton4.setText("Shop");
        jRadioButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 103, 99, -1));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(614, 11, 434, 190));

        jLabel30.setText("Price per 1kg");
        jPanel4.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(237, 279, -1, -1));

        jTextField15.setBackground(new java.awt.Color(0, 0, 255));
        jTextField15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField15.setForeground(new java.awt.Color(255, 255, 255));
        jTextField15.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField15KeyReleased(evt);
            }
        });
        jPanel4.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 90, 37));

        jLabel31.setText("Total Price");
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(333, 280, -1, -1));

        jTextField16.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField16.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jPanel4.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(333, 300, 77, 37));

        jButton16.setText("Sale");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 370, 90, 37));

        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        jPanel4.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 231, 148, 37));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select route", "Ambalantota To Kataragama  ", "Ambalantota To Matara", "Ambalantota To Abilipitiya" }));
        jPanel4.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 368, 150, -1));
        jPanel4.add(jTextField45, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 368, 126, 37));

        jLabel82.setText("Name of the collector");
        jPanel4.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 348, 120, -1));

        jLabel83.setText("Arius Amount");
        jPanel4.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, -1, -1));
        jPanel4.add(jTextField46, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 96, 37));

        jLabel85.setBorder(javax.swing.BorderFactory.createTitledBorder("Invoice NUmber"));
        jPanel4.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 144, 177, 42));

        credit_or_cash.add(jRadioButton20);
        jRadioButton20.setText("Credit");
        jPanel4.add(jRadioButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 286, 69, -1));

        credit_or_cash.add(jRadioButton21);
        jRadioButton21.setText("On Cash");
        jPanel4.add(jRadioButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(625, 286, 81, -1));

        jButton39.setText("Add");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton39, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 370, 74, 37));

        jButton41.setText("Remove");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton41, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 73, -1));

        jButton42.setText("Remove all");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton42, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 330, 86, -1));

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel84.setText("Bill Amount");
        jPanel4.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(832, 332, -1, -1));

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel86.setText("0.0");
        jPanel4.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 332, -1, -1));

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel87.setText("Arius Amount");
        jPanel4.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(832, 360, -1, -1));

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel88.setText("0.0");
        jPanel4.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 360, -1, -1));

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel89.setText("Total Amount");
        jPanel4.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(832, 388, -1, -1));

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel90.setText("0.0");
        jPanel4.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(945, 388, -1, -1));

        jCheckBox1.setText("Add Luuse");
        jPanel4.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 210, -1, -1));

        jCheckBox2.setText("Get Luuse");
        jPanel4.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 240, -1, -1));

        jButton43.setText("jButton43");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton43, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 280, -1, -1));

        jButton44.setText("jButton44");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton44, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 320, -1, -1));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p3Layout = new javax.swing.GroupLayout(p3);
        p3.setLayout(p3Layout);
        p3Layout.setHorizontalGroup(
            p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p3Layout.setVerticalGroup(
            p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        rightPanel.add(p3, "card4");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        returnRadio.add(jRadioButton5);
        jRadioButton5.setText("Detor");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        returnRadio.add(jRadioButton6);
        jRadioButton6.setText("Deliver");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        returnRadio.add(jRadioButton7);
        jRadioButton7.setText("Direct Customer");
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });

        returnRadio.add(jRadioButton8);
        jRadioButton8.setText("shop");
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });

        jLabel32.setText("Name");

        jLabel33.setText("Mobile Number");

        jTextField18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField18KeyReleased(evt);
            }
        });

        jLabel34.setText("Type Of Kg");

        jLabel35.setText("Quantity");

        jTextField20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField20KeyReleased(evt);
            }
        });

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable6);

        jLabel36.setText("Description");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Write Here\n");
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea1MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTextArea1);

        jLabel37.setText("Return Date");

        returnD.add(jRadioButton9);
        jRadioButton9.setText("Damage");

        returnD.add(jRadioButton10);
        jRadioButton10.setText("Reusable");

        jButton17.setText("ADD");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        Category.setText("Category");

        jLabel39.setText("Total Kg");

        jButton20.setText("OK");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jDateChooser3.setDateFormatString("yyyy-MM-dd");

        jDateChooser5.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jRadioButton5)
                                .addGap(12, 12, 12)
                                .addComponent(jRadioButton6)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jRadioButton8)
                                .addGap(40, 40, 40)
                                .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton20)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(34, 34, 34))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField18, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel33))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Category)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField21, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel34)
                                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel35)
                                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel39)
                                            .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addComponent(jRadioButton9)))))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel37)
                                .addGap(110, 110, 110))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jRadioButton10))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton5)
                        .addComponent(jRadioButton6)
                        .addComponent(jRadioButton7)
                        .addComponent(jRadioButton8))
                    .addComponent(jButton20)
                    .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(Category)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jRadioButton9)
                                    .addComponent(jRadioButton10))))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel39)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel37)
                                        .addGap(11, 11, 11)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField22, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout p4Layout = new javax.swing.GroupLayout(p4);
        p4.setLayout(p4Layout);
        p4Layout.setHorizontalGroup(
            p4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p4Layout.setVerticalGroup(
            p4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        rightPanel.add(p4, "card5");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jTable8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(jTable8);

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jTable9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable9MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTable9);

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable10MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTable10);

        jTextField10.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField10KeyReleased(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("Product of Stock");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Movements");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("......");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("KG");

        jButton19.setText("OK");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jDateChooser4.setDateFormatString("yyyy-MM-dd");

        jButton40.setText("SET");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel75.setText("KG");

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel76.setText("......");

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel77.setText("Total KG(IN)");

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel78.setText("Total KG(OUT)");

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel79.setText("......");

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel80.setText("KG");

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel81.setText("Rest Of Kg");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(96, 96, 96)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(88, 88, 88)
                                        .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton40)))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel77)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel76)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel75))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel78)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel79)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel80))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel81)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel42)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel43)))
                                .addGap(125, 125, 125)))
                        .addGap(29, 29, 29))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel42)
                                .addComponent(jLabel43)
                                .addComponent(jLabel81)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel76)
                                    .addComponent(jLabel75)
                                    .addComponent(jLabel77))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel79)
                                    .addComponent(jLabel80)
                                    .addComponent(jLabel78)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jDateChooser4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton19)
                            .addComponent(jButton40))))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout p5Layout = new javax.swing.GroupLayout(p5);
        p5.setLayout(p5Layout);
        p5Layout.setHorizontalGroup(
            p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p5Layout.setVerticalGroup(
            p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        rightPanel.add(p5, "card6");

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable11MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(jTable11);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout p6Layout = new javax.swing.GroupLayout(p6);
        p6.setLayout(p6Layout);
        p6Layout.setHorizontalGroup(
            p6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p6Layout.setVerticalGroup(
            p6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        rightPanel.add(p6, "card7");

        topbar.setBackground(java.awt.SystemColor.textHighlight);
        topbar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jButton26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton26.setText("Cash");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton27.setText("Cheques");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton28.setText("Credit");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topbarLayout = new javax.swing.GroupLayout(topbar);
        topbar.setLayout(topbarLayout);
        topbarLayout.setHorizontalGroup(
            topbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topbarLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jButton26)
                .addGap(18, 18, 18)
                .addComponent(jButton27)
                .addGap(18, 18, 18)
                .addComponent(jButton28)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topbarLayout.setVerticalGroup(
            topbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topbarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton26)
                    .addComponent(jButton27)
                    .addComponent(jButton28))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        topbar_menu.setLayout(new java.awt.CardLayout());

        tbp1.setBackground(new java.awt.Color(255, 255, 255));

        jTable12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane13.setViewportView(jTable12);

        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane14.setViewportView(jTable13);

        jLabel44.setText("Available Balace  ");

        jLabel45.setText("Cash                   ");

        jLabel46.setText("Arrias    ");

        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });

        jTextField25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField25ActionPerformed(evt);
            }
        });

        jButton29.setText("Add");

        javax.swing.GroupLayout tbp1Layout = new javax.swing.GroupLayout(tbp1);
        tbp1.setLayout(tbp1Layout);
        tbp1Layout.setHorizontalGroup(
            tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbp1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbp1Layout.createSequentialGroup()
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(322, Short.MAX_VALUE))
                    .addGroup(tbp1Layout.createSequentialGroup()
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(53, 53, 53)
                        .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(tbp1Layout.createSequentialGroup()
                                    .addComponent(jLabel46)
                                    .addGap(76, 76, 76)
                                    .addComponent(jTextField24, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                                .addGroup(tbp1Layout.createSequentialGroup()
                                    .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbp1Layout.createSequentialGroup()
                                            .addComponent(jLabel44)
                                            .addGap(33, 33, 33))
                                        .addGroup(tbp1Layout.createSequentialGroup()
                                            .addComponent(jLabel45)
                                            .addGap(35, 35, 35)))
                                    .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField23, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                                        .addComponent(jTextField25))))
                            .addComponent(jButton29))
                        .addGap(277, 277, 277))))
        );
        tbp1Layout.setVerticalGroup(
            tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbp1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tbp1Layout.createSequentialGroup()
                        .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton29)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        topbar_menu.add(tbp1, "card2");

        tbp2.setBackground(new java.awt.Color(255, 255, 255));

        jTable14.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane15.setViewportView(jTable14);

        jTable15.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane16.setViewportView(jTable15);

        jLabel47.setText("Amount");

        jLabel48.setText("Cheque No");

        jLabel49.setText("Cheque Date");

        jLabel50.setText("Available Balance");

        jLabel51.setText("Total");

        jLabel52.setText("Arrias");

        jTextField26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField26ActionPerformed(evt);
            }
        });

        jTextField28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField28ActionPerformed(evt);
            }
        });

        jTextField29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField29ActionPerformed(evt);
            }
        });

        jButton30.setText("Add");

        javax.swing.GroupLayout tbp2Layout = new javax.swing.GroupLayout(tbp2);
        tbp2.setLayout(tbp2Layout);
        tbp2Layout.setHorizontalGroup(
            tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbp2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbp2Layout.createSequentialGroup()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(91, 91, 91)
                        .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48)
                            .addComponent(jLabel47)
                            .addComponent(jLabel49)
                            .addComponent(jLabel50)
                            .addComponent(jLabel52))
                        .addGap(68, 68, 68)
                        .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField28, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addComponent(jTextField26)
                            .addComponent(jTextField27)
                            .addComponent(jTextField29))
                        .addGap(67, 67, 67)
                        .addComponent(jButton30)
                        .addGap(140, 140, 140))
                    .addGroup(tbp2Layout.createSequentialGroup()
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel51)
                        .addContainerGap(287, Short.MAX_VALUE))))
        );
        tbp2Layout.setVerticalGroup(
            tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbp2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbp2Layout.createSequentialGroup()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(tbp2Layout.createSequentialGroup()
                        .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tbp2Layout.createSequentialGroup()
                                .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel48)
                                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton30))
                                .addGap(26, 26, 26)
                                .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel47))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel49)
                                .addGap(57, 57, 57))
                            .addGroup(tbp2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel50))))
                        .addGap(28, 28, 28)
                        .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52))))
                .addGap(40, 40, 40)
                .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbp2Layout.createSequentialGroup()
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tbp2Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(344, 344, 344))))
        );

        topbar_menu.add(tbp2, "card3");

        jTable16.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane17.setViewportView(jTable16);

        jTable17.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane18.setViewportView(jTable17);

        jLabel55.setText("Total");

        jLabel56.setText("Avalble Balance");

        jLabel57.setText("Day Credit Balance");

        jTextField31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31ActionPerformed(evt);
            }
        });

        jLabel58.setText("Total Balance");

        jButton31.setText("Add");

        javax.swing.GroupLayout tbp3Layout = new javax.swing.GroupLayout(tbp3);
        tbp3.setLayout(tbp3Layout);
        tbp3Layout.setHorizontalGroup(
            tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbp3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbp3Layout.createSequentialGroup()
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel55)
                        .addGap(191, 191, 191))
                    .addGroup(tbp3Layout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(91, 91, 91)
                        .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(tbp3Layout.createSequentialGroup()
                                .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel56)
                                    .addComponent(jLabel57)
                                    .addComponent(jLabel58))
                                .addGap(30, 30, 30)
                                .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField30)
                                    .addComponent(jTextField31)
                                    .addComponent(jTextField32, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)))
                            .addComponent(jButton31))
                        .addGap(342, 342, 342))))
        );
        tbp3Layout.setVerticalGroup(
            tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbp3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(tbp3Layout.createSequentialGroup()
                        .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel58)
                            .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(jButton31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)))
                .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbp3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(32, 32, 32))
                    .addGroup(tbp3Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel55)
                        .addContainerGap(332, Short.MAX_VALUE))))
        );

        topbar_menu.add(tbp3, "card4");

        javax.swing.GroupLayout p7Layout = new javax.swing.GroupLayout(p7);
        p7.setLayout(p7Layout);
        p7Layout.setHorizontalGroup(
            p7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(topbar_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p7Layout.setVerticalGroup(
            p7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p7Layout.createSequentialGroup()
                .addComponent(topbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(topbar_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rightPanel.add(p7, "card8");

        menba.setBackground(java.awt.SystemColor.textHighlight);

        jButton36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton36.setText("Expenses");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jButton37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton37.setText("Bank");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jButton38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton38.setText("Cheque In");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menbaLayout = new javax.swing.GroupLayout(menba);
        menba.setLayout(menbaLayout);
        menbaLayout.setHorizontalGroup(
            menbaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menbaLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jButton36)
                .addGap(47, 47, 47)
                .addComponent(jButton37)
                .addGap(48, 48, 48)
                .addComponent(jButton38)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menbaLayout.setVerticalGroup(
            menbaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menbaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menbaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton36)
                    .addComponent(jButton37)
                    .addComponent(jButton38))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menp.setLayout(new java.awt.CardLayout());

        jLabel68.setText("Expenses Type");

        jRadioButton11.setText("office");
        jRadioButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton11ActionPerformed(evt);
            }
        });

        jRadioButton12.setText("Transport");

        jRadioButton13.setText("jRadioButton11");

        jRadioButton14.setText("jRadioButton11");

        jLabel69.setText("Amount");

        jLabel70.setText("Today Total Cash");

        jLabel71.setText("Balance");

        jTable21.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane22.setViewportView(jTable21);

        javax.swing.GroupLayout mep1Layout = new javax.swing.GroupLayout(mep1);
        mep1.setLayout(mep1Layout);
        mep1Layout.setHorizontalGroup(
            mep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mep1Layout.createSequentialGroup()
                .addGroup(mep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mep1Layout.createSequentialGroup()
                        .addGroup(mep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mep1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel69)
                                .addGap(36, 36, 36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mep1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel68)))
                        .addGroup(mep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mep1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(mep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton11)
                                    .addComponent(jRadioButton12)
                                    .addComponent(jRadioButton14)
                                    .addComponent(jRadioButton13))
                                .addGap(100, 100, 100)
                                .addGroup(mep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel71)
                                    .addComponent(jLabel70))
                                .addGap(18, 18, 18)
                                .addGroup(mep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(mep1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(mep1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(237, Short.MAX_VALUE))
        );
        mep1Layout.setVerticalGroup(
            mep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mep1Layout.createSequentialGroup()
                .addGroup(mep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mep1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(mep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70)
                            .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(mep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel71)
                            .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mep1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(mep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton11)
                            .addComponent(jLabel68))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton14)))
                .addGap(18, 18, 18)
                .addGroup(mep1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        menp.add(mep1, "card2");

        jLabel66.setText("Bank List");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel60.setText("Cash Deposit");

        jTable22.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane23.setViewportView(jTable22);

        javax.swing.GroupLayout mep2Layout = new javax.swing.GroupLayout(mep2);
        mep2.setLayout(mep2Layout);
        mep2Layout.setHorizontalGroup(
            mep2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mep2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(mep2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mep2Layout.createSequentialGroup()
                        .addGroup(mep2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel60)
                            .addComponent(jLabel66))
                        .addGap(18, 18, 18)
                        .addGroup(mep2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField41)
                            .addComponent(jComboBox1, 0, 175, Short.MAX_VALUE))))
                .addContainerGap(263, Short.MAX_VALUE))
        );
        mep2Layout.setVerticalGroup(
            mep2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mep2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(mep2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mep2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(243, Short.MAX_VALUE))
        );

        menp.add(mep2, "card3");

        jLabel67.setText("Bank Account");

        jRadioButton15.setText("Bank");

        jRadioButton16.setText("PD");

        jRadioButton17.setText("Daily");

        jLabel72.setText("Chequ No");

        jLabel73.setText("Amount");

        jRadioButton18.setText("Union Bank");

        jRadioButton19.setText("Sampath Bank");

        jLabel74.setText("Date");

        jTable23.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane24.setViewportView(jTable23);

        javax.swing.GroupLayout mep3Layout = new javax.swing.GroupLayout(mep3);
        mep3.setLayout(mep3Layout);
        mep3Layout.setHorizontalGroup(
            mep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mep3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(mep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 793, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mep3Layout.createSequentialGroup()
                        .addGroup(mep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton15)
                            .addComponent(jLabel67)
                            .addComponent(jLabel72)
                            .addComponent(jLabel73))
                        .addGap(18, 18, 18)
                        .addGroup(mep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mep3Layout.createSequentialGroup()
                                .addComponent(jRadioButton16)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton17))
                            .addGroup(mep3Layout.createSequentialGroup()
                                .addGroup(mep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(mep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel74)
                                    .addComponent(jRadioButton19)
                                    .addComponent(jRadioButton18))))))
                .addContainerGap(262, Short.MAX_VALUE))
        );
        mep3Layout.setVerticalGroup(
            mep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mep3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(mep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton15)
                    .addComponent(jRadioButton16)
                    .addComponent(jRadioButton17))
                .addGap(18, 18, 18)
                .addGroup(mep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton19)
                .addGroup(mep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mep3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel72)
                            .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mep3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel74)))
                .addGap(22, 22, 22)
                .addGroup(mep3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(207, Short.MAX_VALUE))
        );

        menp.add(mep3, "card4");

        javax.swing.GroupLayout p8Layout = new javax.swing.GroupLayout(p8);
        p8.setLayout(p8Layout);
        p8Layout.setHorizontalGroup(
            p8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p8Layout.setVerticalGroup(
            p8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p8Layout.createSequentialGroup()
                .addComponent(menba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        rightPanel.add(p8, "card9");

        jTable18.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane19.setViewportView(jTable18);

        jTable19.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane20.setViewportView(jTable19);

        jLabel61.setText("Total Balance");

        jLabel62.setText("Amount");

        jLabel63.setText("Cheque Number");

        jLabel64.setText("Date");

        jLabel65.setText("Arrias");

        jButton35.setText("Add");

        javax.swing.GroupLayout p9Layout = new javax.swing.GroupLayout(p9);
        p9.setLayout(p9Layout);
        p9Layout.setHorizontalGroup(
            p9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(p9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p9Layout.createSequentialGroup()
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118)
                        .addGroup(p9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel61)
                            .addComponent(jLabel62)
                            .addComponent(jLabel63)
                            .addComponent(jLabel64)
                            .addComponent(jLabel65))
                        .addGap(48, 48, 48)
                        .addGroup(p9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField34, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                            .addComponent(jTextField35)
                            .addComponent(jTextField36)
                            .addComponent(jTextField37))
                        .addGap(82, 82, 82)
                        .addComponent(jButton35))
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(169, Short.MAX_VALUE))
        );
        p9Layout.setVerticalGroup(
            p9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p9Layout.createSequentialGroup()
                .addGroup(p9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(p9Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(p9Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(p9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel61)
                            .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(p9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62))
                        .addGap(37, 37, 37)
                        .addGroup(p9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63)
                            .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addComponent(jLabel64)
                        .addGap(34, 34, 34)
                        .addGroup(p9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel65)
                            .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton35))))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        rightPanel.add(p9, "card10");

        topmenu.setBackground(java.awt.SystemColor.textHighlight);
        topmenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        topmenu.setPreferredSize(new java.awt.Dimension(1070, 54));

        jButton33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton33.setText("Return Report");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton34.setText("Report 2");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topmenuLayout = new javax.swing.GroupLayout(topmenu);
        topmenu.setLayout(topmenuLayout);
        topmenuLayout.setHorizontalGroup(
            topmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topmenuLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jButton33)
                .addGap(39, 39, 39)
                .addComponent(jButton34)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topmenuLayout.setVerticalGroup(
            topmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topmenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton33)
                    .addComponent(jButton34))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        tmenu.setLayout(new java.awt.CardLayout());

        jTable20.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane21.setViewportView(jTable20);

        javax.swing.GroupLayout tp1Layout = new javax.swing.GroupLayout(tp1);
        tp1.setLayout(tp1Layout);
        tp1Layout.setHorizontalGroup(
            tp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tp1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
                .addGap(272, 272, 272))
        );
        tp1Layout.setVerticalGroup(
            tp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tp1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addGap(294, 294, 294))
        );

        tmenu.add(tp1, "card2");

        javax.swing.GroupLayout tp2Layout = new javax.swing.GroupLayout(tp2);
        tp2.setLayout(tp2Layout);
        tp2Layout.setHorizontalGroup(
            tp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1086, Short.MAX_VALUE)
        );
        tp2Layout.setVerticalGroup(
            tp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );

        tmenu.add(tp2, "card3");

        javax.swing.GroupLayout p10Layout = new javax.swing.GroupLayout(p10);
        p10.setLayout(p10Layout);
        p10Layout.setHorizontalGroup(
            p10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topmenu, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE)
            .addComponent(tmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        p10Layout.setVerticalGroup(
            p10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p10Layout.createSequentialGroup()
                .addComponent(topmenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tmenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rightPanel.add(p10, "card11");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1318, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        String h=jTextField1.getText();
        
        try {
            String s = "SELECT * FROM  set_status WHERE name LIKE '%"+h+"%' OR lorry_number LIKE '%"+h+"%'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
        //             String search = jTextField1.getText();
        //            String g= "pending";
        //            String c="completed";
        //            String s ="SELECT DISTINCT creditor_name,date,status FROM creditor_details WHERE creditor_name LIKE '%"+search+"%' AND status='"+g+"' OR status='"+c+"'ORDER BY date desc";
        //            pst = conn.prepareStatement(s);
        //            rs = pst.executeQuery();
        //            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
       String h=jTextField1.getText();
        
        try {
            String s = "SELECT * FROM  set_status WHERE name LIKE '%"+h+"%' OR lorry_number LIKE '%"+h+"%'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        rightPanel.removeAll();
        rightPanel.add(p2);
        rightPanel.repaint();
        rightPanel.revalidate();

        int row = jTable2.getSelectedRow();

        try {
            String name1 = jTable2.getValueAt(row, 1).toString();
            Date fDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) jTable2.getValueAt(row, 3).toString());
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            String fda = d.format(fDate);
            String s = "SELECT * FROM creditor_table WHERE date ='" + fda + "' AND creditor_name='" + name1 + "'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            fetchCatInProduct();
            jTable1.getColumnModel().getColumn(1).setMinWidth(170);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(170);
        
        if(Usertype.equals("User")){
        jTable1.getColumnModel().getColumn(8).setMinWidth(0);
        jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(9).setMinWidth(0);
        jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
        
        }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_jTable2MouseClicked
public void getProductdata(){
            try {
            String s = "SELECT * FROM  product_sales";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
           
    
    } catch (Exception e) {
    }


}
//select data from product properties
public void getKiloPackets(){

    int row=jTable3.getSelectedRow();
    String id =jTable3.getValueAt(row, 0).toString();
    
     try {
            String s = "SELECT type_of_kg,quantity,rest_of_quantity,total_quantity as total_kg,price_per_1kg,p_id FROM   product_properties WHERE p_id='"+id+"'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable4.setModel(DbUtils.resultSetToTableModel(rs));
            
            jTable4.getColumnModel().getColumn(4).setMinWidth(0);
            jTable4.getColumnModel().getColumn(4).setMaxWidth(0);
                 jTable4.getColumnModel().getColumn(5).setMinWidth(0);
        jTable4.getColumnModel().getColumn(5).setMaxWidth(0);
    } catch (Exception e) {
    }
    


}
    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        rightPanel.removeAll();
        rightPanel.add(p3);
        rightPanel.repaint();
        rightPanel.revalidate();
        jTextField45.setVisible(false);
        jComboBox2.setVisible(false); jLabel82.setVisible(false);
        jRadioButton20.setSelected(true);
        getProductdata();
        try {
         
            String s = "SELECT type_of_kg,quantity,rest_of_quantity,total_quantity as total_kg FROM   product_properties WHERE p_id='"+"ww"+"'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable4.setModel(DbUtils.resultSetToTableModel(rs));
           

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
    }
       
       try {
            String select="SELECT * FROM detor ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
       
      
    }//GEN-LAST:event_jButton15ActionPerformed
public void getProductdata2(){
            try {
            String s = "SELECT * FROM  product_sales";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable9.setModel(DbUtils.resultSetToTableModel(rs));
           
    
    } catch (Exception e) {
    }


}
//fetch data to table in stock 
public void fetchDataStock(){
     try {
         String s = "SELECT * FROM stock ORDER BY id DESC";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable8.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (Exception e) {
    }

}

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
          rightPanel.removeAll();
        rightPanel.add(p5);
        rightPanel.repaint();
        rightPanel.revalidate();
        fetchDataStock();
        getProductdata2();
        try {
         
            String s = "SELECT type_of_kg,quantity,rest_of_quantity,total_quantity as total_kg,price_per_1kg FROM   product_properties WHERE p_id='"+"ww"+"'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable10.setModel(DbUtils.resultSetToTableModel(rs));
           
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
    }
         jTable9.getColumnModel().getColumn(0).setMinWidth(50);
        jTable9.getColumnModel().getColumn(0).setMaxWidth(50);
        jTable8.getColumnModel().getColumn(0).setMinWidth(50);
        jTable8.getColumnModel().getColumn(0).setMaxWidth(50);
         jTable10.getColumnModel().getColumn(4).setMinWidth(0);
        jTable10.getColumnModel().getColumn(4).setMaxWidth(0);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        rightPanel.removeAll();
        rightPanel.add(p4);
        rightPanel.repaint();
        rightPanel.revalidate();
         try {
            String select="SELECT * FROM detor ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
         
         getDataToReturnTable();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        rightPanel.removeAll();
        rightPanel.add(p1);
        rightPanel.repaint();
        rightPanel.revalidate();
        getCreditorData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        rightPanel.removeAll();
        rightPanel.add(p2);
        rightPanel.repaint();
        rightPanel.revalidate();
        jcategory.removeAllItems();
        txtCreditorName.requestFocus();
        fetchCatInProduct();
        jTable1.getColumnModel().getColumn(1).setMinWidth(170);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(170);
        

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:

            setDataCre();

        } catch (ParseException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            // TODO add your handling code here:

            jkg.removeAllItems();
            setDataCre();
        } catch (ParseException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String t = txtQuan.getText();
        if (t.equals("")) {

            JOptionPane.showMessageDialog(this, "Please fill the field");

        } else {

            Op op;
            try {
                op = new Op();
                op.setVisible(true);
                String b = txtQuan.getText();
                String c = txtCat.getText();
                op.getSub(b, c);

                txtQuan.setText("");
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        String t = txtCat.getText();
        if (t.equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill the field");
        } else {
            try {
                String cab = txtCat.getText();
                String h = "SELECT category_name FROM category WHERE category_name='" + cab + "'";
                pst = conn.prepareStatement(h);
                rs = pst.executeQuery();

                if (rs.next()) {
                    String c = rs.getString("category_name");
                    if (c.equals(cab)) {
                        JOptionPane.showMessageDialog(this, "This Category Is Already Added");

                    }

                } else {

                    try {
                        String cat = txtCat.getText();
                        String s = "INSERT INTO category(category_name)VALUES('" + cat + "')";

                        pst = conn.prepareStatement(s);
                        pst.execute();
                        JOptionPane.showMessageDialog(this, "Category added");
                        txtCat.setText("");

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }
                            jcategory.removeAllItems();
                    fetchCatInProduct();
                }

            } catch (Exception e) {
            }

        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtQuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuanActionPerformed

    private void txtCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCatActionPerformed

    private void txtQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {

            jTextField5.setText("");

        }
try {
             int h=Integer.parseInt(txtQuantity.getText());
            
        } catch (NumberFormatException e) {
             
             txtQuantity.setText("");
        }
    }//GEN-LAST:event_txtQuantityKeyReleased

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {

            jTextField5.setText("");

        }
 try {
             int h=Integer.parseInt(jTextField2.getText());
            
        } catch (NumberFormatException e) {
             
             jTextField2.setText("");
        }
        String a1 = jkg.getSelectedItem().toString();
boolean check=jr2.isSelected();
        if (check==true) {

            int e = Integer.parseInt(txtQuantity.getText());
            int y = Integer.parseInt(jTextField2.getText());

            jTextField5.setText(String.valueOf(y * e));
        } else {
            int w = Integer.parseInt(a1);
            int y = Integer.parseInt(jTextField2.getText());

            jTextField5.setText(String.valueOf(w * y));
        }

    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        String catT = jcategory.getSelectedItem().toString();
        setSubcat(catT);
        
         String t = jkg.getSelectedItem().toString();

       
txtQuantity.setText("");
jr1.setSelected(true);
jTextField2.setText("");
jTextField5.setText("");
txtQuantity.setEditable(false);
txtQuantity.setEnabled(false);
        //        jkg.removeAllItems();
        //        try {
            //            String catT = jcategory.getSelectedItem().toString();
            //            String s = "SELECT * FROM catqua WHERE cat='" + catT + "'";
            //            pst = conn.prepareStatement(s);
            //            rs = pst.executeQuery();
            //
            //            while (rs.next()) {
                //                String cat = rs.getString("qua");
                //                jkg.addItem(cat);
                //
                //            }
            //            jkg.addItem("other");
            //        } catch (Exception e) {
            //        }

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseEntered
        // TODO add your handling code here:
//        jkg.removeAllItems();
    }//GEN-LAST:event_jButton11MouseEntered

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        txtCreditorName.requestFocus();
        txtCreditorName.setText("");
        jTextField2.setText("");
        jTextField5.setText("");
        jTextField4.setText("");
        
        jLabel11.setText("");
        txtQuantity.setText("");
        jTextField3.setText("");
        jDateChooser1.setDate(null);
        txtPrice.setText("");

        fetchEmpty();
        jkg.removeAllItems();
                 jr1.setSelected(true);
            txtQuantity.setEnabled(false);
            txtQuantity.setEditable(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // update price:
        String creditorname = txtCreditorName.getText();
        String cat = jcategory.getSelectedItem().toString();
        String id = jLabel11.getText();
        String kg = null;
        String price = txtPrice.getText();
        String status = null;
        String lorryNum = jTextField3.getText();
        String tokg = jTextField5.getText();
        Date dat=jDateChooser1.getDate();
        String toprice = null;
        String nquantiy = jTextField2.getText();
        String bil_num=jTextField33.getText();
        
        if (creditorname.equals("")||lorryNum.equals("")||nquantiy.equals("")||price.equals("")||dat==null) {
            JOptionPane.showMessageDialog(this, "Please fill the empty fields");

        }else {
            int msg = JOptionPane.showConfirmDialog(this, "Do You Want To Update Price");

            if (msg == 0) {

                try {

                    SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
                    String fdate = d.format(jDateChooser1.getDate());
                    if (price.equals("")) {
                        price = "0.00";
                        toprice = "0.00";
                        status = "pending";
                    } else {
                        price = txtPrice.getText();
                        toprice = jTextField4.getText();
                        status = "completed";
                    }

                    //get quantity
                    boolean yes = txtQuantity.isEnabled();
                    if (yes == true) {
                        kg = txtQuantity.getText();
                    } else {
                        kg = jkg.getSelectedItem().toString();
                    }

                    try {
                        String s = "UPDATE creditor_table SET bill_number='"+bil_num+"',price='" + price + "',total_price='" + toprice + "',status='" + status + "' WHERE creditor_id='" + id + "'";
                        pst = conn.prepareStatement(s);
                        pst.execute();

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                    try {
                        String s = "UPDATE creditor_details SET status='" + status + "' WHERE id='" + id + "'";
                        pst = conn.prepareStatement(s);
                        pst.execute();

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }
                  
                    try {
                   
                   String getCat="SELECT * FROM product_sales WHERE category_name='"+cat+"'";
                   pst = conn.prepareStatement(getCat);
                   rs = pst.executeQuery();
                   if(rs.next()){
                   String cn=rs.getString("category_name");
                   int newTid= rs.getInt("id");
                        String getKg="SELECT type_of_kg FROM  product_properties WHERE type_of_kg='"+kg+"' AND p_id='"+newTid+"'";
                        pst = conn.prepareStatement(getKg);
                        rs = pst.executeQuery();
                        
                     
                            if(rs.next()){
                                
                                
                                        String quaUpdate="UPDATE product_properties SET rest_of_quantity='"+"0"+"',price_per_1kg='"+price+"' WHERE p_id='"+newTid+"' AND type_of_kg='"+kg+"'";
                                       
                                        pst =conn.prepareStatement(quaUpdate);
                                        pst.execute();
                                        
                                    }
                           
                   }


                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);
                }
                    
                    
                    
                    
                 
                    
                    fetchData();
                    
                    fetchProductData(creditorname);
                     getCreditorData();
                String getUser=jLabel20.getText();
                String move=" Price Updated";
                String cred="creditor";
               double tpp=Double.parseDouble(toprice);
                insertDataStock(cred,cat,kg,nquantiy,tokg,price,tpp,move,cdate,getUser);
                JOptionPane.showMessageDialog(null, "Updated...");
                    txtCreditorName.requestFocus();
                    jTextField2.setText("");
                    jTextField5.setText("");
                    jTextField4.setText("");
                    jkg.removeAllItems();
                    //jcategory.setSelectedIndex(0);
                    txtQuantity.setText("");
                    jDateChooser1.setDate(null);
                    txtPrice.setText("");
                    jr1.setSelected(true);
            txtQuantity.setEnabled(false);
            txtQuantity.setEditable(false);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);
                }

            }

        }

    }//GEN-LAST:event_jButton5ActionPerformed
public void deleteSetStatus(String creditorname, String dat){

    try {
        String g="SELECT * FROM creditor_details WHERE creditor_name='"+creditorname+"' AND date='"+dat+"'";
        pst =conn.prepareStatement(g);
        rs=pst.executeQuery();
        if(rs.next()){
       
        }else{
            String del="DELETE FROM set_status WHERE name='"+creditorname+"' AND date='"+dat+"'";
            pst = conn.prepareStatement(del);
            pst.execute();
            fetchCreditordat();
        }
    } catch (Exception e) {
    }

}
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String creditorname = txtCreditorName.getText();
        String cat = jcategory.getSelectedItem().toString();

        String kg = null;
        String kg2=jkg.getSelectedItem().toString();
        String price = txtPrice.getText();
        String status = null;
        String lorryNum = jTextField3.getText();
        String tokg = jTextField5.getText();
        String toprice = null;
        String nquantiy = jTextField2.getText();
      SimpleDateFormat g1 = new SimpleDateFormat("yyyy-MM-dd");
                String dat = g1.format(jDateChooser1.getDate());
        if (creditorname.equals("")||lorryNum.equals("")||nquantiy.equals("")||price.equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill the empty fields");

        }else{

            int msg =JOptionPane.showConfirmDialog(this, "Do You Want To Delete ?");
            if(msg ==0){
                try {
                    int id = Integer.parseInt(jLabel11.getText());
                    String sq = "DELETE FROM creditor_table WHERE creditor_id='"+id+"'";
                    pst = conn.prepareStatement(sq);
                    pst.execute();
                    
                     
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);
                }
                 try {
                   
                   String getCat="SELECT * FROM product_sales WHERE category_name='"+cat+"'";
                   pst = conn.prepareStatement(getCat);
                   rs = pst.executeQuery();
                   if(rs.next()){
                   String cn=rs.getString("category_name");
                   int newTid= rs.getInt("id");
                        String getKg="SELECT type_of_kg FROM product_properties WHERE type_of_kg='"+kg2+"' AND p_id='"+newTid+"'";
                        pst = conn.prepareStatement(getKg);
                        rs = pst.executeQuery();
                        
                     
                            if(rs.next()){
                              
                                
                                        String quaUpdate="UPDATE product_properties SET quantity=quantity-'"+jTextField2.getText()+"',total_quantity=total_quantity-'"+jTextField5.getText()+"' WHERE p_id='"+newTid+"' AND type_of_kg='"+kg2+"'";
                                       
                                        pst =conn.prepareStatement(quaUpdate);
                                        pst.execute();
                                        
                                    }
                           
                   }


                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);
                }
                 deleteSetStatus(creditorname,dat);
                    fetchData();
                    fetchProductData(creditorname);
                    String getUser=jLabel20.getText();
                String move=" Deleted";
                String cred="creditorname";
               double tpp=Double.parseDouble(jTextField4.getText());
                insertDataStock(cred,cat,kg2,nquantiy,tokg,price,tpp,move,cdate,getUser);
                    JOptionPane.showMessageDialog(this, "Deleted..");

                    txtCreditorName.requestFocus();
                    txtCreditorName.setText("");
                   jTextField2.setText("");
                    jTextField5.setText("");
                    jTextField4.setText("");
                    jkg.removeAllItems();
                    jcategory.setSelectedIndex(0);
                    txtQuantity.setText("");
                    jTextField3.setText("");
                    jDateChooser1.setDate(null);
                    txtPrice.setText("");
                    jLabel11.setText("");
            }

        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String creditorName = txtCreditorName.getText();
        String lorryNum = jTextField3.getText();
        String tokg = jTextField5.getText();
        String toprice = null;
        String nquantiy = jTextField2.getText();
        Date dat2=jDateChooser1.getDate();
        String p = txtPrice.getText();
        String b =jLabel11.getText();
        boolean check=jr2.isSelected();
        String testQuq=txtQuantity.getText();
        String bil_num="";
       
         String category = jcategory.getSelectedItem().toString();

                String price = null;
                String kg = null;
                String status = null;

                
                //get price
                if (p.equals("")) {
                    price = "0.00";
                    toprice = "0.00";
                    status = "pending";
                    bil_num="null";
                } else {
                    price = txtPrice.getText();
                    toprice = jTextField4.getText();
                    bil_num=jTextField33.getText();
                    status = "completed";
                }

                //get quantity
                boolean yes = txtQuantity.isEnabled();
                if (yes == true) {
                    kg = txtQuantity.getText();
                } else {
                    kg = jkg.getSelectedItem().toString();
                }

                int categoryindex = jcategory.getSelectedIndex();
                int qtyindex = jcategory.getSelectedIndex();
                
                
        if (dat2==null||creditorName.equals("")||lorryNum.equals("")||nquantiy.equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill the empty fields");

        }else if(!b.equals("")){
            JOptionPane.showMessageDialog(this, "Can not add same data again and again. If you want to add a new data then press"+"'CANCEL'"+"button to RESET fields.");
        }else if (check==true) {
            if(testQuq.equals("")){
                        JOptionPane.showMessageDialog(this, "Please fill the empty fields");
                    }else{
            int msg = JOptionPane.showConfirmDialog(this, "Do you want to add this product ?");

            if (msg == 0) {

               
               try {
                    SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd");
                    String date = g.format(jDateChooser1.getDate());

                    String s = "INSERT INTO creditor_table(creditor_name,lorry_number,category,kg,quantiy,total_kg,date,bill_number,price,total_price,status)VALUES('" + creditorName + "','" + lorryNum + "','" + category + "','" + kg + "','" + nquantiy + "','" + tokg + "','" + date + "','"+bil_num+"'," + price + "','" + toprice + "','" + status + "')";

                    pst = conn.prepareStatement(s,Statement.RETURN_GENERATED_KEYS);
                    pst.execute();

                    int id1;

                    try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            id1 = generatedKeys.getInt(1);
                            String sq = "INSERT INTO creditor_details(creditor_name,lorry_number,date,status,id)VALUES('" + creditorName + "','" + lorryNum + "','" + date + "','" + status + "','" +id1+ "')";

                            pst = conn.prepareStatement(sq);
                            pst.execute();
                        }
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);

                }
                
               try {
                   
                   String getCat="SELECT * FROM product_sales WHERE category_name='"+category+"'";
                   pst = conn.prepareStatement(getCat);
                   rs = pst.executeQuery();
                   if(rs.next()){
                   String cn=rs.getString("category_name");
                   int newTid= rs.getInt("id");
                        String getKg="SELECT type_of_kg FROM  product_properties WHERE type_of_kg='"+kg+"' AND p_id='"+newTid+"'";
                        pst = conn.prepareStatement(getKg);
                        rs = pst.executeQuery();
                        
                     
                            if(rs.next()){
                                
                                
                                        String quaUpdate="UPDATE product_properties SET quantity=quantity+'"+nquantiy+"',rest_of_quantity='"+"0"+"',total_quantity=total_quantity+'"+tokg+"',price_per_1kg='"+price+"' WHERE p_id='"+newTid+"' AND type_of_kg='"+kg+"'";
                                       
                                        pst =conn.prepareStatement(quaUpdate);
                                        pst.execute();
                                        
                                    }else{
                                    
                                        String sr = "INSERT INTO product_properties(type_of_kg,quantity,rest_of_quantity,total_quantity,price_per_1kg,p_id)VALUES('" + kg + "','" + nquantiy + "','" + "0"+ "','" + tokg + "','"+price+"','" +newTid+ "')";
                                        pst = conn.prepareStatement(sr);
                                        pst.execute();
                                    }
                            
                            
                  
                   }else{
                       
                             String sq = "INSERT INTO product_sales(category_name)VALUES('" + category + "')";
                             pst = conn.prepareStatement(sq,Statement.RETURN_GENERATED_KEYS);
                             pst.execute();
                            
                            int id2;
                              try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                                 if (generatedKeys.next()) {
                                id2 = generatedKeys.getInt(1);
                                int norest=0;
                                String sr = "INSERT INTO product_properties(type_of_kg,quantity,rest_of_quantity,total_quantity,price_per_1kg,p_id)VALUES('" + kg + "','" + nquantiy + "','" + norest + "','" + tokg + "','"+price+"','" +id2+ "')";

                                pst = conn.prepareStatement(sr);
                                pst.execute();
                        }
                    }
                   
                  }


                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);
                }
                getCreditorData();
                fetchProductData(creditorName);
                
                
                String getUser=jLabel20.getText();
                String move="IN";
                String cred="creditor";
                double tpp=Double.parseDouble(toprice);
                insertDataStock(cred,category,kg,nquantiy,tokg,price,tpp,move,cdate,getUser);
                JOptionPane.showMessageDialog(this, "Product added");
                txtCreditorName.requestFocus();
                jTextField2.setText("");
                jTextField5.setText("");
                jTextField4.setText("");
                jkg.removeAllItems();
                //jcategory.setSelectedIndex(0);
                txtQuantity.setText("");
                txtPrice.setText("");

            }
            }
        }else {

            int msg = JOptionPane.showConfirmDialog(this, "Do you want to add this product ?");

            if (msg == 0) {

               
               try {
                    SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd");
                    String date = g.format(jDateChooser1.getDate());

                    String s = "INSERT INTO creditor_table(creditor_name,lorry_number,category,kg,quantiy,total_kg,date,price,total_price,status)VALUES('" + creditorName + "','" + lorryNum + "','" + category + "','" + kg + "','" + nquantiy + "','" + tokg + "','" + date + "','" + price + "','" + toprice + "','" + status + "')";

                    pst = conn.prepareStatement(s,Statement.RETURN_GENERATED_KEYS);
                    pst.execute();

                    int id1;

                    try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            id1 = generatedKeys.getInt(1);
                            String sq = "INSERT INTO creditor_details(creditor_name,lorry_number,date,status,id)VALUES('" + creditorName + "','" + lorryNum + "','" + date + "','" + status + "','" +id1+ "')";

                            pst = conn.prepareStatement(sq);
                            pst.execute();
                        }
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);

                }
                
               try {
                   
                   String getCat="SELECT * FROM product_sales WHERE category_name='"+category+"'";
                   pst = conn.prepareStatement(getCat);
                   rs = pst.executeQuery();
                   if(rs.next()){
                   String cn=rs.getString("category_name");
                   int newTid= rs.getInt("id");
                        String getKg="SELECT type_of_kg FROM  product_properties WHERE type_of_kg='"+kg+"' AND p_id='"+newTid+"'";
                        pst = conn.prepareStatement(getKg);
                        rs = pst.executeQuery();
                        
                     
                            if(rs.next()){
                                
                                
                                        String quaUpdate="UPDATE product_properties SET quantity=quantity+'"+nquantiy+"',rest_of_quantity='"+"0"+"',total_quantity=total_quantity+'"+tokg+"',price_per_1kg='"+price+"' WHERE p_id='"+newTid+"' AND type_of_kg='"+kg+"'";
                                       
                                        pst =conn.prepareStatement(quaUpdate);
                                        pst.execute();
                                        
                                    }else{
                                    
                                        String sr = "INSERT INTO product_properties(type_of_kg,quantity,rest_of_quantity,total_quantity,price_per_1kg,p_id)VALUES('" + kg + "','" + nquantiy + "','" + "0"+ "','" + tokg + "','"+price+"','" +newTid+ "')";
                                        pst = conn.prepareStatement(sr);
                                        pst.execute();
                                    }
                            
                            
                  
                   }else{
                       
                             String sq = "INSERT INTO product_sales(category_name)VALUES('" + category + "')";
                             pst = conn.prepareStatement(sq,Statement.RETURN_GENERATED_KEYS);
                             pst.execute();
                            
                            int id2;
                              try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                                 if (generatedKeys.next()) {
                                id2 = generatedKeys.getInt(1);
                                int norest=0;
                                String sr = "INSERT INTO product_properties(type_of_kg,quantity,rest_of_quantity,total_quantity,price_per_1kg,p_id)VALUES('" + kg + "','" + nquantiy + "','" + norest + "','" + tokg + "','"+price+"','" +id2+ "')";

                                pst = conn.prepareStatement(sr);
                                pst.execute();
                        }
                    }
                   
                  }


                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);
                }
                getCreditorData();
                fetchProductData(creditorName);
                String getUser=jLabel20.getText();
                String move="IN";
                String cred="creditor";
               double tpp=Double.parseDouble(toprice);
                insertDataStock(cred,category,kg,nquantiy,tokg,price,tpp,move,cdate,getUser);
                JOptionPane.showMessageDialog(this, "Product added");
                txtCreditorName.requestFocus();
                jTextField2.setText("");
                jTextField5.setText("");
                jTextField4.setText("");
                jkg.removeAllItems();
                //jcategory.setSelectedIndex(0);
                txtQuantity.setText("");
                txtPrice.setText("");

            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtPriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            jTextField4.setText("");
        }
try {
             int h=Integer.parseInt(txtPrice.getText());
            
        } catch (NumberFormatException e) {
             
             txtPrice.setText("");
        }
        int ta = Integer.parseInt(jTextField5.getText());
        int per = Integer.parseInt(txtPrice.getText());

        int total = (ta * per);
        jTextField4.setText(String.valueOf(total));
    }//GEN-LAST:event_txtPriceKeyReleased

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

    private void jkgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jkgActionPerformed
       
    }//GEN-LAST:event_jkgActionPerformed

    private void jcategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcategoryActionPerformed
 
    }//GEN-LAST:event_jcategoryActionPerformed

    private void jcategoryMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcategoryMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_jcategoryMouseReleased

    private void jcategoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcategoryMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_jcategoryMouseEntered

    private void jcategoryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcategoryItemStateChanged

    }//GEN-LAST:event_jcategoryItemStateChanged

    private void txtCreditorNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCreditorNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCreditorNameActionPerformed

    private void jr2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jr2ActionPerformed
        // TODO add your handling code here:
      setRaadiobutEnable();
    }//GEN-LAST:event_jr2ActionPerformed
public void setRaadiobutEnable(){

    boolean jr =jr1.isSelected();
    boolean j2=jr2.isSelected();
   
    if(jr ==true){
     txtQuantity.setEnabled(false);
        txtQuantity.setEditable(false);
        txtQuantity.setText("");
        jTextField2.setText("");
                jTextField5.setText("");
    }else{
    txtQuantity.setEnabled(true);
        txtQuantity.setEditable(true);
        jTextField2.setText("");
                jTextField5.setText("");
    }
}
    private void jr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jr1ActionPerformed
        setRaadiobutEnable();
    }//GEN-LAST:event_jr1ActionPerformed
public void getProductDataTosalesTAble(){

    int row=jTable3.getSelectedRow();
    String category =jTable3.getValueAt(row, 1).toString();
    
    jTextField11.setText(category);



}
/// get total quantity
public void getTotalQuantity2(){
DefaultTableModel df =(DefaultTableModel)jTable10.getModel();
int row=jTable10.getRowCount();
int gettotal=0;
for(int i=0;i<row;i++){

    gettotal=gettotal+(int)df.getValueAt(i, 3);
}
 
  jLabel42.setText(String.valueOf(gettotal));
    

}
public void getTotalQuantity(){
DefaultTableModel df =(DefaultTableModel)jTable4.getModel();
int row=jTable4.getRowCount();
int gettotal=0;
for(int i=0;i<row;i++){

    gettotal=gettotal+(int)df.getValueAt(i, 3);
}
 
  //jLabel3.setText(String.valueOf(gettotal));
    

}
    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
     
        getKiloPackets();
       getTotalQuantity();
       getProductDataTosalesTAble();
    }//GEN-LAST:event_jTable3MouseClicked
public void getProductPropeties(){

    int row=jTable4.getSelectedRow();
    
    String kg=jTable4.getValueAt(row, 0).toString();
     String p=jTable4.getValueAt(row, 4).toString();
    jTextField12.setText(kg);
   // jTextField15.setText(p);

}
    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
       getProductPropeties();
   //change the table header names 
   DefaultTableModel df1 =(DefaultTableModel)jTable7.getModel();
              df1.setRowCount(0);
        
        TableColumnModel cm=jTable7.getTableHeader().getColumnModel();
            while(cm.getColumnCount()>0){
                cm.removeColumn(cm.getColumn(0));
            }
            cm.addColumn(new TableColumn(0,100));
            cm.getColumn(0).setHeaderValue("Item_Name");
            
             cm.addColumn(new TableColumn(1,100));
            cm.getColumn(1).setHeaderValue("Type of unit");
            
             cm.addColumn(new TableColumn(2,100));
            cm.getColumn(2).setHeaderValue("Quantity");
            
             cm.addColumn(new TableColumn(3,100));
            cm.getColumn(3).setHeaderValue("qty(luuse)");
            
            cm.addColumn(new TableColumn(4,100));
            cm.getColumn(4).setHeaderValue("Total Kg");
            
             cm.addColumn(new TableColumn(5,100));
            cm.getColumn(5).setHeaderValue("Price");
            
             cm.addColumn(new TableColumn(6,100));
            cm.getColumn(6).setHeaderValue("Total Price");
            
             cm.addColumn(new TableColumn(7,100));
            cm.getColumn(7).setHeaderValue("pid");
            
               cm.addColumn(new TableColumn(8,100));
            cm.getColumn(8).setHeaderValue("luuse type");
//            
//            jTable7.getColumnModel().getColumn(7).setMinWidth(0);
//        jTable7.getColumnModel().getColumn(7).setMaxWidth(0);
    }//GEN-LAST:event_jTable4MouseClicked
//changing sellers
    
    public void selectSellers(){
    
        boolean detorq =jRadioButton1.isSelected();
        boolean deliver =jRadioButton2.isSelected();
         boolean customer =jRadioButton3.isSelected();
         boolean shop =jRadioButton4.isSelected();
    
    if(detorq == true){
        jTextField9.setEnabled(true);
        jTextField9.setEditable(true);
        jTextField9.setText("");
        jTextField11.setText("Salt");
        jTextField7.setEnabled(true);
        jTextField7.setEditable(true);
        jTextField8.setEnabled(true);
        jTextField8.setEditable(true);jTextField45.setVisible(false);
       jComboBox2.setVisible(false); jLabel82.setVisible(false);
        
        try {
            String select="SELECT * FROM detor ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    
    }else if(deliver){
     jTextField7.setEnabled(true);
        jTextField7.setEditable(true);
        jTextField8.setEnabled(true);
        jTextField8.setEditable(true);
        jTextField11.setText("");
     jTextField9.setEnabled(true);
        jTextField9.setEditable(true);
        
        jTextField45.setVisible(true);
       jComboBox2.setVisible(true); jLabel82.setVisible(true);
        try {
            String select="SELECT * FROM deliver ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }else if(customer){
        jTextField9.setEnabled(false);
        jTextField9.setEditable(false);
        jTextField9.setText("");
        jTextField11.setText("");
        jTextField7.setEnabled(true);
        jTextField7.setEditable(true);
        jTextField8.setEnabled(true);
        jTextField8.setEditable(true);jTextField45.setVisible(false);
       jComboBox2.setVisible(false); jLabel82.setVisible(false);
        try {
            String select="SELECT * FROM customer ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }else if(shop==true){
    
        jTextField9.setEnabled(false);
        jTextField9.setEditable(false);
        jTextField7.setEnabled(false);
        jTextField7.setEditable(false);
        jTextField8.setEnabled(false);
        jTextField8.setEditable(false);
        jTextField9.setText("");
        jTextField8.setText("");
        jTextField7.setText("");jTextField11.setText("");jTextField45.setVisible(false);
       jComboBox2.setVisible(false);
       jLabel82.setVisible(false);
      
       try {
            String select="SELECT * FROM shop ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

    }
    
    }
    private void jTextField13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyReleased
        
        if(evt.getKeyCode()==KeyEvent.VK_BACK_SPACE){
        jTextField14.setText("");
        jTextField16.setText("");
        }

 try {
             int h=Integer.parseInt(jTextField13.getText());
            
        } catch (NumberFormatException e) {
             
             jTextField13.setText("");
        }
            boolean addUse=jCheckBox1.isSelected();
            boolean getUse=jCheckBox2.isSelected();
             if(addUse==false && getUse==false){
                  int kg=Integer.parseInt(jTextField12.getText());
                        int qua=Integer.parseInt(jTextField13.getText());

                     int total=0;
                     total=(kg*qua);

                     jTextField14.setText(String.valueOf(total));

                     int tkg=Integer.parseInt(jTextField14.getText());
                     int p=Integer.parseInt(jTextField15.getText());

                     int top=(tkg * p);
                     jTextField16.setText(String.valueOf(top));
             
             }else{
                int qua=Integer.parseInt(jTextField13.getText());
                jTextField14.setText(String.valueOf(qua));
                
                     int tkg=Integer.parseInt(jTextField14.getText());
                     int p=Integer.parseInt(jTextField15.getText());

                     int top=(tkg * p);
                     jTextField16.setText(String.valueOf(top));
                
             }
//       if(getUse==false){
//                  int kg=Integer.parseInt(jTextField12.getText());
//                        int qua=Integer.parseInt(jTextField13.getText());
//
//                     int total=0;
//                     total=(kg*qua);
//
//                     jTextField14.setText(String.valueOf(total));
//
//                     int tkg=Integer.parseInt(jTextField14.getText());
//                     int p=Integer.parseInt(jTextField15.getText());
//
//                     int top=(tkg * p);
//                     jTextField16.setText(String.valueOf(top));
//             
//             }else{
//                int qua=Integer.parseInt(jTextField13.getText());
//                jTextField14.setText(String.valueOf(qua));
//                
//                     int tkg=Integer.parseInt(jTextField14.getText());
//                     int p=Integer.parseInt(jTextField15.getText());
//
//                     int top=(tkg * p);
//                     jTextField16.setText(String.valueOf(top));
//                
//             }
    }//GEN-LAST:event_jTextField13KeyReleased

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
       selectSellers();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        selectSellers();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        selectSellers();
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        selectSellers();
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jTextField14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyReleased
       
        
    }//GEN-LAST:event_jTextField14KeyReleased
//add data to detor table
    public void insertToDetor(){
         String name=jTextField7.getText();
       String mn=jTextField8.getText();
       String ln=jTextField9.getText();
       SimpleDateFormat g1 = new SimpleDateFormat("yyyy-MM-dd");
    String dat = g1.format(jDateChooser2.getDate());
        String cat=jTextField11.getText();
        String kg=jTextField12.getText();
        String qua=jTextField13.getText();
        String totalKg=jTextField14.getText();
        String price=jTextField15.getText();
        String totalP=jTextField16.getText();
        try {
            String insert="INSERT INTO detor(name,mobile_number,date,category,type_of_kg,quantity,total_kg,price_of_1kg,total_price)"
                    + "VALUES('"+name+"','"+mn+"','"+dat+"','"+cat+"','"+kg+"','"+qua+"','"+totalKg+"','"+price+"','"+totalP+"')";
            pst=conn.prepareStatement(insert);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Data added to table");
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this, e);
        }
        try {
            String select="SELECT * FROM detor ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        try {
    
            int row=jTable3.getSelectedRow();
                 String id=jTable3.getValueAt(row, 0).toString();
                String getId="UPDATE product_properties SET quantity=quantity-'"+jTextField13.getText()+"',total_quantity=total_quantity-'"+jTextField14.getText()+"'  WHERE p_id='"+id+"' AND type_of_kg='"+jTextField12.getText()+"'";
                 pst =conn.prepareStatement(getId);
                 pst.execute();
                 
                 getKiloPackets();
             } catch (Exception e) {
                 JOptionPane.showMessageDialog(this, e);
             }
     jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jDateChooser2.setDate(null);
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
         jTextField14.setText("");     
          jTextField15.setText("");     
          jTextField16.setText("");   
    }
    //add data to deliver table
    public void insertToDeliver(){
    
         String name=jTextField7.getText();
       String mn=jTextField8.getText();
       String ln=jTextField9.getText();
       SimpleDateFormat g1 = new SimpleDateFormat("yyyy-MM-dd");
    String dat = g1.format(jDateChooser2.getDate());
        String cat=jTextField11.getText();
        String kg=jTextField12.getText();
        String qua=jTextField13.getText();
        String totalKg=jTextField14.getText();
        String price=jTextField15.getText();
        String totalP=jTextField16.getText();
        try {
            String insert="INSERT INTO deliver(name,mobile_number,date,lorry_number,category,type_of_kg,quantity,total_kg,price_of_1kg,total_price)"
                    + "VALUES('"+name+"','"+mn+"','"+dat+"','"+ln+"','"+cat+"','"+kg+"','"+qua+"','"+totalKg+"','"+price+"','"+totalP+"')";
            pst=conn.prepareStatement(insert);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Data added to table");
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this, e);
        }
    try {
            String select="SELECT * FROM deliver ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
      try {
    
            int row=jTable3.getSelectedRow();
                 String id=jTable3.getValueAt(row, 0).toString();
                String getId="UPDATE product_properties SET quantity=quantity-'"+jTextField13.getText()+"',total_quantity=total_quantity-'"+jTextField14.getText()+"'  WHERE p_id='"+id+"' AND type_of_kg='"+jTextField12.getText()+"'";
                 pst =conn.prepareStatement(getId);
                 pst.execute();
                 
                 getKiloPackets();
             } catch (Exception e) {
             }
     jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jDateChooser2.setDate(null);
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
         jTextField14.setText("");     
          jTextField15.setText("");     
          jTextField16.setText("");   
    
    }
//add data to cutomer table
    public void insertToCustomer(){
       String name=jTextField7.getText();
       String mn=jTextField8.getText();
       String ln=jTextField9.getText();
       SimpleDateFormat g1 = new SimpleDateFormat("yyyy-MM-dd");
    String dat = g1.format(jDateChooser2.getDate());
        String cat=jTextField11.getText();
        String kg=jTextField12.getText();
        String qua=jTextField13.getText();
        String totalKg=jTextField14.getText();
        String price=jTextField15.getText();
        String totalP=jTextField16.getText();
        try {
            String insert="INSERT INTO customer(name,mobile_number,date,category,type_of_kg,quantity,total_kg,price_of_1kg,total_price)"
                    + "VALUES('"+name+"','"+mn+"','"+dat+"','"+cat+"','"+kg+"','"+qua+"','"+totalKg+"','"+price+"','"+totalP+"')";
            pst=conn.prepareStatement(insert);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Data added to table");
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this, e);
        }try {
            String select="SELECT * FROM customer ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
          try {
    
            int row=jTable3.getSelectedRow();
                 String id=jTable3.getValueAt(row, 0).toString();
                String getId="UPDATE product_properties SET quantity=quantity-'"+jTextField13.getText()+"',total_quantity=total_quantity-'"+jTextField14.getText()+"'  WHERE p_id='"+id+"' AND type_of_kg='"+jTextField12.getText()+"'";
                 pst =conn.prepareStatement(getId);
               pst.execute();
                 
                 getKiloPackets();
             } catch (Exception e) {
             }
     jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jDateChooser2.setDate(null);
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
         jTextField14.setText("");     
          jTextField15.setText("");     
          jTextField16.setText("");   
    }
    //add data to shop table
    public void insertToShop(){
    
       String name=jTextField7.getText();
       String mn=jTextField8.getText();
       String ln=jTextField9.getText();
       SimpleDateFormat g1 = new SimpleDateFormat("yyyy-MM-dd");
    String dat = g1.format(jDateChooser2.getDate());
        String cat=jTextField11.getText();
        String kg=jTextField12.getText();
        String qua=jTextField13.getText();
        String totalKg=jTextField14.getText();
        String price=jTextField15.getText();
        String totalP=jTextField16.getText();
        try {
            String insert="INSERT INTO shop(date,category,type_of_kg,quantity,total_kg,price_of_1kg,total_price)"
                    + "VALUES('"+dat+"','"+cat+"','"+kg+"','"+qua+"','"+totalKg+"','"+price+"','"+totalP+"')";
            pst=conn.prepareStatement(insert);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Data added to table");
        } catch (Exception e) {
             JOptionPane.showMessageDialog(this, e);
        }try {
            String select="SELECT * FROM shop ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
          try {
    
            int row=jTable3.getSelectedRow();
                 String id=jTable3.getValueAt(row, 0).toString();
                String getId="UPDATE product_properties SET quantity=quantity-'"+jTextField13.getText()+"',total_quantity=total_quantity-'"+jTextField14.getText()+"'  WHERE p_id='"+id+"' AND type_of_kg='"+jTextField12.getText()+"'";
                 pst =conn.prepareStatement(getId);
                pst.execute();
                 
                 getKiloPackets();
             } catch (Exception e) {
             }
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jDateChooser2.setDate(null);
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
         jTextField14.setText("");     
          jTextField15.setText("");     
          jTextField16.setText("");     
                
                
    }
    //update product propeties with new data
    public void reduceCategoryAmount(String switchName,String ktype,String qty,String luuseqty,String tkg,String pid){
    
    if(switchName.equals("AL")){
        try {
            String sql="UPDATE product_properties SET rest_of_quantity=rest_of_quantity-'"+luuseqty+"',total_quantity=total_quantity-'"+tkg+"' WHERE p_id='"+pid+"' AND type_of_kg='"+ktype+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
        }
   
    }else if(switchName.equals("GL")){
                    int ty=Integer.parseInt(ktype);
                    int tt=Integer.parseInt(tkg);
                    int sum=(ty-tt);
        try {
               String sql="UPDATE product_properties SET quantity=quantity-'"+1+"',rest_of_quantity=rest_of_quantity+'"+sum+"',total_quantity=total_quantity-'"+sum+"' WHERE p_id='"+pid+"' AND type_of_kg='"+ktype+"'";
               pst=conn.prepareStatement(sql);
               pst.execute();
           } catch (Exception e) {
           }
    }else{
    
        try {
               String sql="UPDATE product_properties SET quantity=quantity-'"+qty+"',total_quantity=total_quantity-'"+tkg+"' WHERE p_id='"+pid+"' AND type_of_kg='"+ktype+"'";
               pst=conn.prepareStatement(sql);
               pst.execute();
           } catch (Exception e) {
           }
    }
    
    getKiloPackets();
    }//code end here
    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
       String name=jTextField7.getText();
       String mn=jTextField8.getText();
       String ln=jTextField9.getText();
       SimpleDateFormat g1 = new SimpleDateFormat("yyyy-MM-dd");
       Date dat = jDateChooser2.getDate();
        String cat=jTextField11.getText();
        String kg=jTextField12.getText();
        String qua=jTextField13.getText();
        String totalKg=jTextField14.getText();
        String price=jTextField15.getText();
        String totalP=jTextField16.getText();
        
        String collector=jTextField45.getText();
        int route=jComboBox2.getSelectedIndex();
        
        boolean detor2 =jRadioButton1.isSelected();
        boolean deliver =jRadioButton2.isSelected();
         boolean customer =jRadioButton3.isSelected();
         boolean shop =jRadioButton4.isSelected();
        
         if(detor2==true){
             if(dat==null||name.equals("")||mn.equals("")||cat.equals("")||kg.equals("")||qua.equals("")||totalKg.equals("")||price.equals("")||totalP.equals("")||ln.equals("")){
             
             JOptionPane.showMessageDialog(this, "Fil the empty fields");
             }else{
             
                   
                String getUser=jLabel20.getText();
                String move="OUT";
                String cred="Salt";
               double tpp=Double.parseDouble(totalP);
                insertDataStock(cred,cat,kg,qua,totalKg,price,tpp,move,cdate,getUser);
                insertToDetor();
             
             }

         }else if(deliver==true){
             if(dat==null||name.equals("")||mn.equals("")||ln.equals("")||cat.equals("")||kg.equals("")||qua.equals("")||totalKg.equals("")||price.equals("")||totalP.equals("")||collector.equals("")||route==0){
             
             JOptionPane.showMessageDialog(this, "Fil the empty fields");
             }else{
                 
                 //ask the user that, he needs to print the bill
                 int msg =JOptionPane.showConfirmDialog(this, "Do you want to print the bill");
                if(msg==0){
                            DefaultTableModel df2 =(DefaultTableModel)jTable7.getModel();
                                   int newRC=jTable7.getRowCount();
                                   String nqty="";
                                   String newluse="";
                                   String switchName="null";
                                   for(int i=0;i<newRC;i++){
                                       String ktype=(String)df2.getValueAt(i, 1);
                                        String item=(String)df2.getValueAt(i, 0);
                                       String qty=(String)df2.getValueAt(i, 2);
                                       String luuseqty=(String)df2.getValueAt(i, 3);
                                                if(qty.equals("0")){
                                                    nqty="L"+luuseqty;
                                                    newluse=luuseqty;
                                                }else{
                                                     nqty=qty;
                                                }
                                        String lVaue=(String)df2.getValueAt(i, 8);
                                switch (lVaue) {
                                    case "AL":
                                        switchName="AL";
                                        break;
                                    case "GL":
                                        switchName="GL";
                                        break;
                                    default:
                                        switchName="null";
                                        break;
                                }
                                
                                       String tkg=(String)df2.getValueAt(i, 4);
                                       String pp=(String)df2.getValueAt(i, 5);
                                       double tpp=(Double)df2.getValueAt(i, 6);
                                       String pid=(String)df2.getValueAt(i, 7);
                                //update product properties table(addition/subtraction)
                                reduceCategoryAmount(switchName,ktype,qty,luuseqty,tkg,pid);

                                      String cred="Deliver/Detor"; 
                                       insertDataToSales(cred,ktype,nqty,tkg,pp,tpp);
                                                String getUser=jLabel20.getText();
                                                String move="OUT";
     
                                                      
                                      insertDataStock(cred,item,ktype,nqty,tkg,pp,tpp,move,cdate,getUser);
                                      insertToDeliver();
                                   }//loop end here
                                   String cred="Deliver/Detor"; 
                                   getSalesData(cred);
                 }else{
                            JOptionPane.showMessageDialog(this, "adding dat to database");
                }
                 
         
             }
         }else if(customer==true){
             if(dat==null||name.equals("")||mn.equals("")||cat.equals("")||kg.equals("")||qua.equals("")||totalKg.equals("")||price.equals("")||totalP.equals("")){
             
             JOptionPane.showMessageDialog(this, "Fil the empty fields");
             }else{
                String getUser=jLabel20.getText();
                String move="OUT";
                String cred="Customer";
               double tpp=Double.parseDouble(totalP);
                insertDataStock(cred,cat,kg,qua,totalKg,price,tpp,move,cdate,getUser);
                insertToCustomer();
             }
         }else if(shop==true){
             if(dat==null||cat.equals("")||kg.equals("")||qua.equals("")||totalKg.equals("")||price.equals("")||totalP.equals("")){
             
             JOptionPane.showMessageDialog(this, "Fil the empty fields");
             }else{
                String getUser=jLabel20.getText();
                String move="OUT";
                String cred="Shop";
                double tpp=Double.parseDouble(totalP);
                insertDataStock(cred,cat,kg,qua,totalKg,price,tpp,move,cdate,getUser);
                insertToShop();
             }
         }
        getTotalQuantity();
    }//GEN-LAST:event_jButton16ActionPerformed
public void getDataByClickRadioBtn(){
 boolean detorq =jRadioButton5.isSelected();
        boolean deliver =jRadioButton6.isSelected();
         boolean customer =jRadioButton7.isSelected();
         boolean shop =jRadioButton8.isSelected();
    
    if(detorq == true){
     
        
        try {
            String select="SELECT * FROM detor ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    
    

}else if(deliver ==true){
     
        
        try {
            String select="SELECT * FROM deliver ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    
    }else if(customer ==true){
     
        
        try {
            String select="SELECT * FROM customer ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    
    }
    else if(shop ==true){
     
        
        try {
            String select="SELECT * FROM shop ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    
    }

}
    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        getDataByClickRadioBtn();
        
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        getDataByClickRadioBtn();
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        // TODO add your handling code here:
         getDataByClickRadioBtn();
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        // TODO add your handling code here:
         getDataByClickRadioBtn();
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        int m =JOptionPane.showConfirmDialog(this, "Do you want to log out the system");
        if(m==0){
            Login l;
            try {
                
                  String getUser=jLabel20.getText();
                String move="LOU OUT";
                String descr="logout user";
                MainPage p = new MainPage();
                p.insertIntoMovement(getUser,move,descr,cdate,ctime);
                l = new Login();
                 l.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            this.dispose();
            
        }
        
    }//GEN-LAST:event_jButton18ActionPerformed
//fetch data to return table
    public void getDataToReturnTable(){
        try {
            String a="SELECT * FROM returntable ORDER BY id";
            pst =conn.prepareStatement(a);
            rs=pst.executeQuery();
            jTable6.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
        }
    
    
    }
    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
       String name=jTextField17.getText();
       String mn=jTextField18.getText();
       String kg=jTextField19.getText();
       String qua=jTextField20.getText();
       String des=jTextArea1.getText();
       String cat=jTextField21.getText();
       
       boolean detorq =jRadioButton5.isSelected();
        boolean deliver =jRadioButton6.isSelected();
         boolean customer =jRadioButton7.isSelected();
         boolean shop =jRadioButton8.isSelected();
    
    String cred="";
       
       String s="";
       boolean b=jRadioButton9.isSelected();

       Date v=jDateChooser3.getDate();

      
       if(name.equals("")||mn.equals("")||kg.equals("")||qua.equals("")||v==null){
       
       JOptionPane.showMessageDialog(this, "Fill the fields");
       }else{
            
           try {
         if(b==true){
        s="damage";
        }else{
        s="reusable";
        }
         if(s.equals("reusable")){
             
               try {
                   
                   String q="SELECT id FROM product_sales WHERE category_name='"+jTextField21.getText()+"'";
                   pst =conn.prepareStatement(q);
                   rs =pst.executeQuery();
                   if(rs.next()){
                       int id=rs.getInt("id");
                      
                  String quaUpdate="UPDATE product_properties SET quantity=quantity+'"+qua+"',total_quantity=total_quantity+'"+jTextField22.getText()+"' WHERE p_id='"+id+"' AND type_of_kg='"+kg+"'";
                 pst =conn.prepareStatement(quaUpdate);
                 pst.execute();
                   }
                   
 
             } catch (Exception e) {
                 JOptionPane.showMessageDialog(this, e);
             }
                String inser="INSERT INTO returntable(name,mobile,category,kg,quantity,total_kg,status,date,descr)"
                       + "VALUES('"+name+"','"+mn+"','"+cat+"','"+kg+"','"+qua+"','"+jTextField22.getText()+"','"+s+"','"+cdate+"','"+des+"')";
               pst=conn.prepareStatement(inser);
               pst.execute();
               
         }else{
            String inser="INSERT INTO returntable(name,mobile,category,kg,quantity,total_kg,status,date,descr)"
                       + "VALUES('"+name+"','"+mn+"','"+cat+"','"+kg+"','"+qua+"','"+jTextField22.getText()+"','"+s+"','"+cdate+"','"+des+"')";
               pst=conn.prepareStatement(inser);
               pst.execute();
        
         }
              if(detorq == true){
              cred="Detor";
              }else if(deliver==true){
              cred="Deliver";
              }else if(customer==true){
                  cred="Customer";
           }else if(shop==true){
           cred="Shop";
           }
               
               
               
               String getUser=jLabel20.getText();
                String move="IN";
                
               String price="0";
               String totalP="0";
               double tpp=Double.parseDouble(totalP);
                insertDataStock(cred,cat,kg,qua,jTextField22.getText(),price,tpp,move,cdate,getUser);
                 JOptionPane.showMessageDialog(this, "added data to return");
                  getDataToReturnTable();
                  
                    jTextField17.setText("");
      jTextField18.setText("");
       jTextField19.setText("");
       jTextField20.setText("");
       jTextArea1.setText("");
       jTextField21.setText("");
       jTextArea1.setText("Write Here");
       jTextField22.setText("");
           } catch (Exception e) {
           }
           
           
       }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jTextArea1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseClicked
        jTextArea1.setText("");
    }//GEN-LAST:event_jTextArea1MouseClicked

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        String sq=jTextField6.getText();
        
        try {
            String s = "SELECT * FROM  product_sales WHERE category_name LIKE '%"+sq+"%'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
    
    } catch (Exception e) {
    }
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTextField20KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField20KeyReleased
         
        if(evt.getKeyCode()==KeyEvent.VK_BACK_SPACE){
        jTextField22.setText("");
        }
        try {
             int h=Integer.parseInt(jTextField20.getText());
            
        } catch (NumberFormatException e) {
             
             jTextField20.setText("");
        }
        int kg=Integer.parseInt(jTextField19.getText());
                        int qua=Integer.parseInt(jTextField20.getText());

                     int total=0;
                     total=(kg*qua);

                     jTextField22.setText(String.valueOf(total));

                     
    }//GEN-LAST:event_jTextField20KeyReleased

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        try {
            int r=jTable5.getSelectedRow();
String name=jTable5.getValueAt(r, 1).toString();
String cat=jTable5.getValueAt(r, 4).toString();
String mn=jTable5.getValueAt(r, 2).toString();
String kg=jTable5.getValueAt(r, 5).toString();
Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse((String) jTable5.getValueAt(r, 3).toString());

jTextField17.setText(name);
jTextField21.setText(cat);
jTextField18.setText(mn);
jTextField19.setText(kg);

jDateChooser3.setDate(date1);

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTable5MouseClicked
public void getKiloPackets2(){

    int row=jTable9.getSelectedRow();
    String id =jTable9.getValueAt(row, 0).toString();
    
     try {
            String s = "SELECT type_of_kg,quantity,rest_of_quantity,total_quantity as total_kg,price_per_1kg FROM   product_properties WHERE p_id='"+id+"'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            
            jTable10.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (Exception e) {
    }
    
 jTable10.getColumnModel().getColumn(4).setMinWidth(0);
        jTable10.getColumnModel().getColumn(4).setMaxWidth(0);

}
    private void jTable9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable9MouseClicked
        getKiloPackets2();
        getTotalQuantity2();
        
    }//GEN-LAST:event_jTable9MouseClicked

    private void jTextField18KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField18KeyReleased
        try {
             int h=Integer.parseInt(jTextField18.getText());
            
        } catch (NumberFormatException e) {
             
             jTextField18.setText("");
        }
    }//GEN-LAST:event_jTextField18KeyReleased

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        try {
             int h=Integer.parseInt(jTextField8.getText());
            
        } catch (NumberFormatException e) {
            
             jTextField8.setText("");
        }
    }//GEN-LAST:event_jTextField8KeyReleased

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
       String sq=jTextField10.getText();
        
        try {
            String s = "SELECT * FROM  product_sales WHERE category_name LIKE '%"+sq+"%'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable9.setModel(DbUtils.resultSetToTableModel(rs));
    
    } catch (Exception e) {
    }
    }//GEN-LAST:event_jTextField10KeyReleased

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
         SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd");
                    String date = g.format(jDateChooser4.getDate());
                    
                     try {
            String s = "SELECT * FROM stock WHERE date LIKE '%"+date+"%'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable8.setModel(DbUtils.resultSetToTableModel(rs));
    
    } catch (Exception e) {
    }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd");
                    String date = g.format(jDateChooser5.getDate());
        
        
        boolean detorq =jRadioButton5.isSelected();
        boolean deliver =jRadioButton6.isSelected();
         boolean customer =jRadioButton7.isSelected();
         boolean shop =jRadioButton8.isSelected();
    
    if(detorq == true){
     
        
        try {
            String select="SELECT * FROM detor WHERE date='"+date+"' ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    
    

}else if(deliver ==true){
     
        
        try {
            String select="SELECT * FROM deliver WHERE date='"+date+"' ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    
    }else if(customer ==true){
     
        
        try {
            String select="SELECT * FROM customer WHERE date='"+date+"' ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    
    }
    else if(shop ==true){
     
        
        try {
            String select="SELECT * FROM shop WHERE date='"+date+"' ORDER BY id DESC";
            pst =conn.prepareStatement(select);
            rs=pst.executeQuery();
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    
    }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void txtPriceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPriceMouseClicked
        txtPrice.setText("");
    }//GEN-LAST:event_txtPriceMouseClicked

    private void txtCatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCatKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCatKeyReleased

    private void txtCatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCatKeyPressed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
         txtCreditorName.requestFocus();
        txtCreditorName.setText("");
        jTextField2.setText("");
        jTextField5.setText("");
        jTextField4.setText("");
        
        jLabel11.setText("");
        txtQuantity.setText("");
        jTextField3.setText("");
        jDateChooser1.setDate(null);
        txtPrice.setText("");
         jkg.removeAllItems();
                 jr1.setSelected(true);
            txtQuantity.setEnabled(false);
            txtQuantity.setEditable(false);
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
          rightPanel.removeAll();
        rightPanel.add(p6);
        rightPanel.repaint();
        rightPanel.revalidate();
        try {
            setDataToSaleList();
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
           jTable11.getColumnModel().getColumn(0).setMinWidth(0);
        jTable11.getColumnModel().getColumn(0).setMaxWidth(0);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
           rightPanel.removeAll();
        rightPanel.add(p7);
        rightPanel.repaint();
        rightPanel.revalidate();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
          rightPanel.removeAll();
        rightPanel.add(p8);
        rightPanel.repaint();
        rightPanel.revalidate();
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
         rightPanel.removeAll();
        rightPanel.add(p9);
        rightPanel.repaint();
        rightPanel.revalidate();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        topbar_menu.removeAll();
        topbar_menu.add(tbp1);
        topbar_menu.repaint();
        topbar_menu.revalidate();
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
         topbar_menu.removeAll();
        topbar_menu.add(tbp2);
        topbar_menu.repaint();
        topbar_menu.revalidate();
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
         topbar_menu.removeAll();
        topbar_menu.add(tbp3);
        topbar_menu.repaint();
        topbar_menu.revalidate();
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jTextField25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField25ActionPerformed

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void jTextField26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField26ActionPerformed

    private void jTextField28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField28ActionPerformed

    private void jTextField29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField29ActionPerformed

    private void jTextField31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
          rightPanel.removeAll();
        rightPanel.add(p10);
        rightPanel.repaint();
        rightPanel.revalidate();
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
          tmenu.removeAll();
        tmenu.add(tp1);
        tmenu.repaint();
        tmenu.revalidate();
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        tmenu.removeAll();
        tmenu.add(tp2);
        tmenu.repaint();
        tmenu.revalidate();
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
           menp.removeAll();
        menp.add(mep1);
        menp.repaint();
        menp.revalidate();
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
           menp.removeAll();
        menp.add(mep2);
        menp.repaint();
        menp.revalidate();
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
           menp.removeAll();
        menp.add(mep3);
        menp.repaint();
        menp.revalidate();
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jRadioButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton11ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
           TableColumnModel cm=jTable8.getTableHeader().getColumnModel();
            while(cm.getColumnCount()>0){
                cm.removeColumn(cm.getColumn(0));
            }
            cm.addColumn(new TableColumn(0,100));
            cm.getColumn(0).setHeaderValue("Item_Name");
            
             cm.addColumn(new TableColumn(1,100));
            cm.getColumn(1).setHeaderValue("Type of unit");
            
             cm.addColumn(new TableColumn(2,100));
            cm.getColumn(2).setHeaderValue("IN");
            
            cm.addColumn(new TableColumn(3,100));
            cm.getColumn(3).setHeaderValue("Total Units");
            
             cm.addColumn(new TableColumn(4,100));
            cm.getColumn(4).setHeaderValue("OUT");
         cm.addColumn(new TableColumn(5,100));
            cm.getColumn(5).setHeaderValue("Total Units");
            
              int row=jTable9.getSelectedRow();
        String item=jTable9.getValueAt(row, 1).toString();
        
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = d.format(jDateChooser4.getDate());
       
       int qIN=0;
String iname="";
String type="";
 String qOUT="";  
 String totIN="";
 String totOUT=""; 
        
        
//         DefaultTableModel df =(DefaultTableModel)jTable8.getModel();
//            df.setRowCount(0);
            DefaultTableModel df =(DefaultTableModel)jTable8.getModel();
            df.setRowCount(0);
           int t=0;
        try {
            String sql="SELECT item_name,type_of_kg,"
                    + "sum(CASE WHEN movement = 'IN' THEN quantity ELSE 0 END)AS qin,"
                    + "sum(CASE WHEN movement = 'IN' THEN total_kg ELSE 0 END)AS tqtyi,"
                    + "sum(CASE WHEN movement = 'OUT' THEN quantity ELSE 0 END)AS qout,"
                    + "sum(CASE WHEN movement = 'OUT' THEN total_kg ELSE 0 END)AS tqty FROM stock WHERE item_name='"+item+"' AND date='"+fdate+"' GROUP BY type_of_kg";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
            iname=rs.getString("item_name");
            type=rs.getString("type_of_kg");
            qIN=rs.getInt("qin");
             totIN=rs.getString("tqtyi"); 
             
             qOUT=rs.getString("qout");
            totOUT=rs.getString("tqty");            
             
                        df.addRow(new Object []{
        
                        iname,
                        type,
                        qIN,
                        totIN,
                        qOUT,
                        totOUT

                    });
                    
       
            }
            
            
                        
        } catch (Exception e) {
        }
           
 
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jTable10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable10MouseClicked
         TableColumnModel cm=jTable8.getTableHeader().getColumnModel();
            while(cm.getColumnCount()>0){
                cm.removeColumn(cm.getColumn(0));
            }
            cm.addColumn(new TableColumn(0,100));
            cm.getColumn(0).setHeaderValue("Item_Name");
            
             cm.addColumn(new TableColumn(1,100));
            cm.getColumn(1).setHeaderValue("Type of unit");
            
             cm.addColumn(new TableColumn(2,100));
            cm.getColumn(2).setHeaderValue("IN");
            
            cm.addColumn(new TableColumn(3,100));
            cm.getColumn(3).setHeaderValue("Total Units");
            
             cm.addColumn(new TableColumn(4,100));
            cm.getColumn(4).setHeaderValue("OUT");
         cm.addColumn(new TableColumn(5,100));
            cm.getColumn(5).setHeaderValue("Total Units");
        
        
        int row=jTable9.getSelectedRow();
        String item=jTable9.getValueAt(row, 1).toString();
        
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = d.format(jDateChooser4.getDate());
        int r2=jTable10.getSelectedRow();
        
        String ty=jTable10.getValueAt(r2, 0).toString();
        
String qIN="";
String iname="";
String type="";
 String qOUT="";  
 String totIN="";
 String totOUT="";
        try {
            String sql="SELECT item_name,type_of_kg,sum(quantity),sum(total_kg) FROM stock WHERE date='"+fdate+"' AND item_name='"+item+"' AND type_of_kg='"+ty+"' AND movement='"+"IN"+"'";
            pst=conn.prepareStatement(sql);
            rs =pst.executeQuery();
           if(rs.next()){
           iname=rs.getString("item_name");
           type=rs.getString("type_of_kg");
           qIN=rs.getString("sum(quantity)");
           totIN=rs.getString("sum(total_kg)");
           }
            
            
        } catch (Exception e) {
        }
          try {
            String sql="SELECT sum(quantity),sum(total_kg) FROM stock WHERE date='"+fdate+"' AND item_name='"+item+"' AND type_of_kg='"+ty+"' AND movement='"+"OUT"+"'";
            pst=conn.prepareStatement(sql);
            rs =pst.executeQuery();
           
            if(rs.next()){
            qOUT=rs.getString("sum(quantity)");
            totOUT=rs.getString("sum(total_kg)");
          
           
            }
            
        } catch (Exception e) {
        }
 
           
            DefaultTableModel df =(DefaultTableModel)jTable8.getModel();
            df.setRowCount(0);
        df.addRow(new Object []{
        
            iname,
            type,
            qIN,
            totIN,
            qOUT,
            totOUT
        
        });
        
        
        try {
            String sw="SELECT sum(total_kg) FROM stock WHERE date='"+fdate+"' AND item_name='"+item+"' AND movement='"+"IN"+"'";
              pst=conn.prepareStatement(sw);
            rs =pst.executeQuery();
            
            if(rs.next()){
                String t=rs.getString("sum(total_kg)");
                jLabel76.setText(String.valueOf(t));
            }
        } catch (Exception e) {
        }
        try {
            String sw="SELECT sum(total_kg) FROM stock WHERE date='"+fdate+"' AND item_name='"+item+"' AND movement='"+"OUT"+"'";
              pst=conn.prepareStatement(sw);
            rs =pst.executeQuery();
            
            if(rs.next()){
                String t1=rs.getString("sum(total_kg)");
                jLabel79.setText(String.valueOf(t1));
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTable10MouseClicked

    private void jTable11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable11MouseClicked
           rightPanel.removeAll();
        rightPanel.add(p3);
        rightPanel.repaint();
        rightPanel.revalidate();
jRadioButton2.setSelected(true);
jTextField9.setEditable(true);
jTextField9.setEnabled(true);
jTextField11.setText("");
        int row = jTable11.getSelectedRow();
String name2="";
        try {
            String name1 = jTable11.getValueAt(row, 2).toString();
            name2 = jTable11.getValueAt(row, 3).toString();
            Date fDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) jTable11.getValueAt(row, 4).toString());
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            String fda = d.format(fDate);
            String s = "SELECT * FROM sales_list WHERE date ='" + fda + "' AND dealer_name='" + name2 + "' AND dealer='"+name1+"'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            
            if(rs.next()){
            
            String n=rs.getString("dealer_name");
            String mn=rs.getString("mobile");
            String ln=rs.getString("lorry_number");
           
            String route=rs.getString("route");
            String collector=rs.getString("collector");
            jTextField7.setText(n);
            jTextField8.setText(mn);
            jTextField9.setText(ln);
            jTextField45.setText(collector);
            jComboBox2.setSelectedItem(route);
            
            }
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
                   //getArius amount
        
        
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
            jTable7.getColumnModel().getColumn(0).setMinWidth(0);
        jTable7.getColumnModel().getColumn(0).setMaxWidth(0);
        
        getProductdata();
        try {
         
            String s = "SELECT type_of_kg,quantity,rest_of_quantity,total_quantity as total_kg,price_per_1kg FROM   product_properties WHERE p_id='"+"ww"+"'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable4.setModel(DbUtils.resultSetToTableModel(rs));
           
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
    }
        
try {
            String sql="SELECT arius_amount FROM arius_amount WHERE name='"+name2+"'";
               pst = conn.prepareStatement(sql);
                 rs = pst.executeQuery();
                 if(rs.next()){
                 String amount=rs.getString("arius_amount");
                 jTextField46.setText(String.valueOf(amount));
                 }
                 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    jRadioButton20.setSelected(true);
    }//GEN-LAST:event_jTable11MouseClicked

    private void jTextField15KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyReleased
        int tkg=Integer.parseInt(jTextField14.getText());
         int p=Integer.parseInt(jTextField15.getText());
         
         int top=(tkg * p);
         jTextField16.setText(String.valueOf(top));
         
         if(evt.getKeyCode()==KeyEvent.VK_BACK_SPACE){
         jTextField16.setText("");
         
         }
         
         
    }//GEN-LAST:event_jTextField15KeyReleased

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
             //if check add/get luuse button is true
             boolean addUse=jCheckBox1.isSelected();
             boolean getUse=jCheckBox2.isSelected();
             String quantity="0";
             String luuse="0";
             String luuseName="null";
             if(addUse==true ||getUse==true){
                 if(addUse==true){luuseName="AL";}else{luuseName="GL";}
             luuse=jTextField13.getText();
             quantity="0";
             }else{
             
             quantity=jTextField13.getText();
             luuse="0";
             }
        
     
               int rr=jTable4.getSelectedRow();
        
                
                String type=jTable4.getValueAt(rr, 0).toString();
                String pid3=jTable4.getValueAt(rr, 5).toString();
        
    
       
            try {
                String sql="SELECT sum(quantity),sum(rest_of_quantity) FROM  product_properties WHERE type_of_kg='"+type+"' AND p_id='"+pid3+"'";
                pst =conn.prepareStatement(sql);
                rs=pst.executeQuery();
                
                if(rs.next()){
                
                    int quat=rs.getInt("sum(quantity)");
                    int res=rs.getInt("sum(rest_of_quantity)");
                    int wqty=Integer.parseInt(quantity);
                    int wluuse=Integer.parseInt(luuse);
                    if(quat<wqty){
                        JOptionPane.showMessageDialog(this, "cannot performe the task, quantitiy is higher than the stock");
                    }else if(res==0){
                    
                         //add data to bill table(bill preview)  
              int r1=jTable3.getSelectedRow();
              int idr=jTable4.getSelectedRow();
             String item1=jTable3.getValueAt(r1, 1).toString();
             String kyType=jTextField12.getText();
             String totalQuantity=jTextField14.getText();
             String price=jTextField15.getText();
             double totalP=Double.parseDouble(jTextField16.getText());
             String pid1=jTable4.getValueAt(idr, 5).toString();
            
         DefaultTableModel df =(DefaultTableModel)jTable7.getModel();
        
              df.addRow(new Object []{
                  item1,
                  kyType,
                  quantity,
                  luuse,
                  totalQuantity,
                  price,
                  totalP,
                   pid1,
                   luuseName
        
        
            });
              
       // DefaultTableModel df =(DefaultTableModel)jTable7.getModel();       
              //set and get total
        double totalq=0;
        double sub=0;
        int col =jTable7.getRowCount();
        
       
        for(int i=0;i<col;i++){
        
        sub=(Double)df.getValueAt(i, 6);
         totalq=totalq+sub;
        }
       jLabel86.setText(String.valueOf(totalq));
         
                    }else if(wluuse>res){
                        JOptionPane.showMessageDialog(this, "cannot performe the task, luuse quantitiy is higher than the stock");
                    }else{
                                     
                       //add data to bill table(bill preview)  
              int r1=jTable3.getSelectedRow();
              int idr=jTable4.getSelectedRow();
             String item1=jTable3.getValueAt(r1, 1).toString();
             String kyType=jTextField12.getText();
             String totalQuantity=jTextField14.getText();
             String price=jTextField15.getText();
             double totalP=Double.parseDouble(jTextField16.getText());
             String pid1=jTable4.getValueAt(idr, 5).toString();
            
         DefaultTableModel df =(DefaultTableModel)jTable7.getModel();
        
              df.addRow(new Object []{
                  item1,
                  kyType,
                  quantity,
                  luuse,
                  totalQuantity,
                  price,
                  totalP,
                   pid1,
                   luuseName
        
        
            });
              
       // DefaultTableModel df =(DefaultTableModel)jTable7.getModel();       
              //set and get total
        double totalq=0;
        double sub=0;
        int col =jTable7.getRowCount();
        
       
        for(int i=0;i<col;i++){
        
        sub=(Double)df.getValueAt(i, 6);
         totalq=totalq+sub;
        }
       jLabel86.setText(String.valueOf(totalq));
         
              
                    }
                
                
                }else{
                
                JOptionPane.showMessageDialog(this,"Error");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,e);
            }
        //System.out.println("item name :-"+item+"type :-"+type+"qty :-"+qut+"luuse :-"+luse+"totalKG :-"+totalkg+"price :-"+p+"total Price :-"+tp);
        
        //code end here

         
//         if(getUse==true){
//                int row=jTable4.getSelectedRow();
//
//                String type=jTable4.getValueAt(row, 0).toString();
//               
//                String pid1=jTable4.getValueAt(row, 5).toString();
//                
//                int utype=Integer.parseInt(jTextField12.getText());
//                int qty=Integer.parseInt(jTextField13.getText());
//                int newQun=(utype-qty);
//                int tKg=Integer.parseInt(jTextField14.getText());
//                
//                try {
//                    String sql="UPDATE product_properties SET quantity=quantity-'"+1+"',rest_of_quantity=rest_of_quantity+'"+newQun+"',total_quantity=total_quantity-'"+tKg+"' WHERE p_id='"+pid1+"' AND type_of_kg='"+type+"'";
//                    pst=conn.prepareStatement(sql);
//                    pst.execute();
//                    getKiloPackets();
//                } catch (Exception e) {
//                }
//            }
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
         int row=jTable7.getSelectedRow();
          DefaultTableModel df = (DefaultTableModel)jTable7.getModel();
          boolean getUse=jCheckBox2.isSelected();
        if(row >=0){
        
        
         if(getUse==true){
               
               String pid1=jTable7.getValueAt(row, 7).toString();
                String utype=jTextField12.getText();
                String qty=jTextField13.getText();
                
                int re=Integer.parseInt(qty);
                
                int newQun=(re);
                String tKg=jTextField14.getText();
                
//                try {
//                    String sql="UPDATE product_properties SET quantity=quantity+'"+1+"',rest_of_quantity=rest_of_quantity-'"+newQun+"',total_quantity=total_quantity+'"+tKg+"' WHERE p_id='"+pid1+"' AND type_of_kg='"+utype+"'";
//                    pst=conn.prepareStatement(sql);
//                    pst.execute();
//                    getKiloPackets();
//                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(this, e);
//                }
                
          double total=0.00;
           double sub=0.00;
           int col =jTable7.getRowCount();
        
       
        for(int i=0;i<col;i++){
        
            sub=(Double)df.getValueAt(i, 6);
         total=total+sub;
        }
         jLabel86.setText(String.valueOf(total));
            }
        
        
        df.removeRow(row);
        
        
        
        
        
        }
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
       
            
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
         DefaultTableModel df = (DefaultTableModel)jTable7.getModel();
         int rowCount = jTable7.getRowCount();
         double total=0.00;
           double sub=0.00;
          for(int i = rowCount;i>0;i--){
            df.removeRow(i-1);
           jLabel86.setText("0.0");
          }
           jLabel86.setText("0.0");
        
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
      //check wheather the quantity/luuse qty is higher than the stock  
        DefaultTableModel df1 =(DefaultTableModel)jTable7.getModel();
                int r=jTable7.getRowCount();
                int rr=jTable7.getSelectedRow();
                int total=0;
                int lusTotal=0;
        for(int i =0;i<r;i++){
        
                String item=(String)df1.getValueAt(i,0);
                String type=(String)df1.getValueAt(i, 1);

                String qut=(String)df1.getValueAt(i, 2);
                total=total+Integer.parseInt(qut);

                String luse=(String)df1.getValueAt(i, 3);
                lusTotal=lusTotal+Integer.parseInt(luse);

                String totalkg=(String)df1.getValueAt(i, 4);
                String p=(String)df1.getValueAt(i, 5);
                double tp=(Double)df1.getValueAt(i, 6);
                String pid=(String)df1.getValueAt(i, 7);
       
            try {
                String sql="SELECT sum(quantity),sum(rest_of_quantity) FROM  product_properties WHERE type_of_kg='"+type+"' AND p_id='"+pid+"'";
                pst =conn.prepareStatement(sql);
                rs=pst.executeQuery();
                
                if(rs.next()){
                
                    int quat=rs.getInt("sum(quantity)");
                    int res=rs.getInt("sum(rest_of_quantity)");
                    if(quat<total){
                        JOptionPane.showMessageDialog(this, "cannot performe the task, quantitiy is higher than the stock");
                    }else if(lusTotal>res){
                        JOptionPane.showMessageDialog(this, "cannot performe the task, luuse quantitiy is higher than the stock");
                    }else{
                        //ask the user that, he needs to print the bill
                        int msg =JOptionPane.showConfirmDialog(this, "Do you want to print the bill");
                        if(msg==0){
                            
                            JOptionPane.showMessageDialog(this, "printing bill");
                        
                        }else{
                            JOptionPane.showMessageDialog(this, "adding dat to database");
                        }
                    }
                
                
                }else{
                
                JOptionPane.showMessageDialog(this,"Error");
                }
            } catch (Exception e) {
            }
        //System.out.println("item name :-"+item+"type :-"+type+"qty :-"+qut+"luuse :-"+luse+"totalKG :-"+totalkg+"price :-"+p+"total Price :-"+tp);
        }
        //code end here
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        int r=jTable3.getSelectedRow();
        int rr=jTable4.getSelectedRow();
        
        String name=jTable3.getValueAt(r, 1).toString();
        String type=jTable4.getValueAt(rr, 0).toString();
        JOptionPane.showMessageDialog(this, name+"........ "+type);
    }//GEN-LAST:event_jButton44ActionPerformed

public void setSubcat(String c){
 
        try {
           jkg.removeAllItems();
            String s = "SELECT * FROM catqua WHERE cat='"+c+"'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                String cat = rs.getString("qua");
                jkg.addItem(cat);

            }
            
            //jkg.addItem("other");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
}
//retrive product to table in sales tab
public void getProductToSales(){

//    try {
//            String s = "SELECT * FROM catqua WHERE cat='"+c+"'";
//            pst = conn.prepareStatement(s);
//            rs = pst.executeQuery();
//    } catch (Exception e) {
//    }


}
public void getCurrentDateTime(){
    
    new Timer(0, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
             
            java.text.SimpleDateFormat SimpleDateFormat = new java.text.SimpleDateFormat("HH:mm:ss");
            String displayTime = SimpleDateFormat.format(new Date());
            String displayDate = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH).format(new Date());
     
            
            jLabel38.setText(displayDate);
            //d=displayDate;ctime
            ctime=displayTime;
            cdate=displayDate;
        }
    }).start();
  
   
    }
//set movement
public void insertIntoMovement(String user,String movement,String descr,String date,String time){

    try {
        String insert="INSERT INTO movement_table(user,movement,description,date,time)"
                + "VALUES('"+user+"','"+movement+"','"+descr+"','"+date+"','"+time+"')";
        pst=conn.prepareStatement(insert);
        pst.execute();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
    }


}
//insert data into stock
public void insertDataStock(String dname,String Iname,String kg,String qua,String totalKg,String price,double toP,String move,String date,String user){

    try {
        String insert="INSERT INTO stock(dealer_name,item_name,type_of_kg,quantity,total_kg,price_per_1kg,total_price,movement,date,user)VALUES('"+dname+"','"+Iname+"','"+kg+"','"+qua+"','"+totalKg+"','"+price+"','"+toP+"','"+move+"','"+date+"','"+user+"')";
        pst=conn.prepareStatement(insert);
        pst.execute();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
    }

}
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainPage().setVisible(true);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Category;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup credit_or_cash;
    private javax.swing.ButtonGroup detor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JTextField jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton13;
    private javax.swing.JRadioButton jRadioButton14;
    private javax.swing.JRadioButton jRadioButton15;
    private javax.swing.JRadioButton jRadioButton16;
    private javax.swing.JRadioButton jRadioButton17;
    private javax.swing.JRadioButton jRadioButton18;
    private javax.swing.JRadioButton jRadioButton19;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton20;
    private javax.swing.JRadioButton jRadioButton21;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable14;
    private javax.swing.JTable jTable15;
    private javax.swing.JTable jTable16;
    private javax.swing.JTable jTable17;
    private javax.swing.JTable jTable18;
    private javax.swing.JTable jTable19;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable20;
    private javax.swing.JTable jTable21;
    private javax.swing.JTable jTable22;
    private javax.swing.JTable jTable23;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JComboBox<String> jcategory;
    private javax.swing.JComboBox<String> jkg;
    private javax.swing.JRadioButton jr1;
    private javax.swing.JRadioButton jr2;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel menba;
    private javax.swing.JPanel menp;
    private javax.swing.JPanel mep1;
    private javax.swing.JPanel mep2;
    private javax.swing.JPanel mep3;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel p10;
    private javax.swing.JPanel p2;
    private javax.swing.JPanel p3;
    private javax.swing.JPanel p4;
    private javax.swing.JPanel p5;
    private javax.swing.JPanel p6;
    private javax.swing.JPanel p7;
    private javax.swing.JPanel p8;
    private javax.swing.JPanel p9;
    private javax.swing.ButtonGroup returnD;
    private javax.swing.ButtonGroup returnRadio;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JPanel tbp1;
    private javax.swing.JPanel tbp2;
    private javax.swing.JPanel tbp3;
    private javax.swing.JPanel tmenu;
    private javax.swing.JPanel topbar;
    private javax.swing.JPanel topbar_menu;
    private javax.swing.JPanel topmenu;
    private javax.swing.JPanel tp1;
    private javax.swing.JPanel tp2;
    private javax.swing.JTextField txtCat;
    private javax.swing.JTextField txtCreditorName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuan;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables
}
