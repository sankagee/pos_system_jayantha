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
import java.awt.print.Printable;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.print.*;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import java.text.DecimalFormat;

/**
 *
 * @author SASITHA HASHAN
 */
public class MainPage extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    PreparedStatement pst1 = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    String creditorId = null;
    String pid = null;
    String ctime = "";
    String cdate = "";
    String Usertype = "";
    double toCreditorArius = 0.0;
    DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
    
    public MainPage() throws SQLException {
        initComponents();
        conn = DBConnect.connect();
        txtQuantity.setEnabled(false);
        getCurrentDateTime();
        //getCreditorData();

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

        jRadioButton5.setSelected(true);
        jRadioButton9.setSelected(true);
        jTextField48.setEditable(false);
        jTextField47.setEditable(false);
        jButton3.setBackground(Color.yellow);
        jButton3.setBackground(Color.yellow);
        jButton1.setBackground(Color.white);
        jButton13.setBackground(Color.white);
        jButton15.setBackground(Color.white);
        jButton22.setBackground(Color.white);
        jButton14.setBackground(Color.white);
        jButton23.setBackground(Color.white);
        jButton24.setBackground(Color.white);
        jButton25.setBackground(Color.white);
        jButton32.setBackground(Color.white);

        invID();

    }

    public void getUserD(String name, String uType) {
        jLabel20.setText(name);
        this.Usertype = uType;
        if (uType.equals("User")) {
            jButton14.setVisible(false);//add or update arius table
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
            jTextField48.setVisible(false);
            jTextField47.setVisible(false);
            jTextField49.setVisible(false);
            jButton42.setVisible(false);

            jTable1.getColumnModel().getColumn(8).setMinWidth(0);
            jTable1.getColumnModel().getColumn(8).setMaxWidth(0);

            jTable1.getColumnModel().getColumn(9).setMinWidth(0);
            jTable1.getColumnModel().getColumn(9).setMaxWidth(0);

            jTable1.getColumnModel().getColumn(10).setMinWidth(0);
            jTable1.getColumnModel().getColumn(10).setMaxWidth(0);

            jTable1.getColumnModel().getColumn(11).setMinWidth(0);
            jTable1.getColumnModel().getColumn(11).setMaxWidth(0);
        }
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
    public void fetchEmpty() {
        try {
            String s = "SELECT * FROM creditor_table WHERE creditor_name='" + 0000 + "'";
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

    public void fetchProductData(String c, String d) {
        try {
            String s = "SELECT * FROM creditor_table WHERE creditor_name='" + c + "' AND date='" + d + "'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            jTable1.getColumnModel().getColumn(1).setMinWidth(170);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(170);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
// commt 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    //auto generate inv id
    public void invID() {

        try {
            Statement c = conn.createStatement();
            rs = c.executeQuery("SELECT Max(inv) FROM bill");
            rs.next();

            rs.getString("Max(inv)");
            if (rs.getString("Max(inv)") == null) {

                jLabel85.setText("000000001");
            } else {

                Long id = Long.parseLong(rs.getString("Max(inv)").substring(2, rs.getString("Max(inv)").length()));
                id++;
                jLabel85.setText(String.format("%09d", id));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

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
    public void setDataToSaleList() throws SQLException {
        String sq = "SELECT * FROM set_sales ORDER BY status desc";
        pst = conn.prepareStatement(sq);
        rs = pst.executeQuery();
        jTable11.setModel(DbUtils.resultSetToTableModel(rs));

    }
//fetch data to creditor table after checking

    public void fetchCreditordat() throws SQLException {

        String sq = "SELECT id,name,lorry_number,date,status  FROM set_status ORDER BY date desc";
        pst = conn.prepareStatement(sq);
        rs = pst.executeQuery();
        jTable2.setModel(DbUtils.resultSetToTableModel(rs));

    }

    //insert data to luuse table in database
    public void insertLuuse(String cat, String unit, String qty, String lusekg, String tl, String date, String move) {

        try {
            String sql = "INSERT INTO luuse(cat_name,type_of_kg,quantity,luuse_kg,total_luuse,date,move)"
                    + "VALUES('" + cat + "','" + unit + "','" + qty + "','" + lusekg + "','" + tl + "','" + date + "','" + move + "')";
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
        }

    }

    //code end here
    //insert data to sales table
    public void insertDataToSales(String dealer, String Ktype, String qty, String totalQty, String p, Double Tp, String cat) {
        String name = jTextField7.getText();
        String mn = jTextField8.getText();
        String ln = jTextField9.getText();
        SimpleDateFormat g1 = new SimpleDateFormat("yyyy-MM-dd");
        String dat = g1.format(jDateChooser2.getDate());

        String status = "pending";
        String route = jComboBox2.getSelectedItem().toString();
        String collectr = jTextField45.getText();
        String bill = jLabel85.getText();
        try {
            String insert = "INSERT INTO sales_list(bill,dealer,dealer_name,mobile,lorry_number,date,category,type_kg,quantity,total_kg,price_per_1kg,total_price,route,collector,status)"
                    + "VALUES('" + bill + "','" + dealer + "','" + name + "','" + mn + "','" + ln + "','" + dat + "','" + cat + "','" + Ktype + "','" + qty + "','" + totalQty + "','" + p + "','" + Tp + "','" + route + "','" + collectr + "','" + status + "')";
            pst = conn.prepareStatement(insert);
            pst.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

        //getSalesData(dealer);
    }

    //cleare all the fileds after done sales
    public void clearFields() {

        jRadioButton1.setSelected(true);
        try {
            String sql = "SELECT DISTINCT name as Names_of_customers FROM detor";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable24.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

        jTextField17.setText("");
        jTextField21.setText("");
        jTextField18.setText("");
        jTextField19.setText("");
        jTextField11.setText("");
        jTextField7.setText("");
        jTextField22.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField46.setText("");
        jTextField15.setText("");
        jTextField16.setText("");
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField14.setText("");
        jButton46.setEnabled(true);
        jButton43.setEnabled(false);
        jComboBox2.setSelectedIndex(0);
        jTextField45.setText("");
        jLabel86.setText("0.0");
        jLabel88.setText("0.0");
        jLabel90.setText("0.0");

    }

    //get sales data and set it in set sales table
    public void getSalesData(String dealer) {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String addDate = d.format(jDateChooser2.getDate());
        String n = jTextField7.getText();
        try {

            String bill_no = jLabel85.getText();
            String st = "pending";
            String sum_of_arius = "";
            String j = "SELECT bill,dealer,dealer_name,mobile,lorry_number,date,category,type_kg,quantity,total_kg,price_per_1kg,sum(total_price),route,collector,status FROM sales_list WHERE dealer='" + dealer + "' AND status='" + st + "' AND dealer_name='" + n + "' AND date='" + addDate + "' AND bill='" + bill_no + "'";
            pst = conn.prepareStatement(j);
            rs = pst.executeQuery();

            if (rs.next()) {
                String cname = rs.getString("dealer_name");
                String dt = rs.getString("date");
                String dealer1 = rs.getString("dealer");
                String sta = rs.getString("status");
                String bill = rs.getString("bill");
                sum_of_arius = rs.getString("sum(total_price)");

                String sq = "SELECT * FROM set_sales WHERE dealer_name='" + cname + "' AND date='" + dt + "' AND dealer='" + dealer1 + "'";
                pst = conn.prepareStatement(sq);
                rs = pst.executeQuery();

                if (rs.next()) {
                    String cn = rs.getString("dealer_name");
                    String dtt = rs.getString("date");
                    String bill2 = rs.getString("bill_number");
                    String update = "UPDATE set_sales SET status='" + "pending" + "' WHERE dealer_name='" + cn + "' AND date='" + dtt + "' AND bill_number='" + bill2 + "'";
                    pst = conn.prepareStatement(update);
                    pst.execute();

                } else {

                    String insert = "INSERT INTO set_sales(bill_number,dealer,dealer_name,date,status)VALUES('" + bill + "','" + dealer + "','" + n + "','" + dt + "','" + sta + "')";
                    pst = conn.prepareStatement(insert);
                    pst.execute();

                }
            } else {
                String n1 = jTextField7.getText();
                String st1 = "completed";
                String j1 = "SELECT SELECT bill,dealer,dealer_name,mobile,lorry_number,date,category,type_kg,quantity,total_kg,price_per_1kg,sum(total_price),route,collector,status FROM sales_list WHERE dealer='" + dealer + "' AND status='" + st1 + "' AND dealer_name='" + n1 + "' AND date='" + addDate + "'";
                pst = conn.prepareStatement(j1);
                rs = pst.executeQuery();

                if (rs.next()) {
                    String cname = rs.getString("dealer_name");
                    String dt = rs.getString("date");
                    String dealer1 = rs.getString("dealer");
                    String sta = rs.getString("status");
                    String bill = rs.getString("bill");
                    sum_of_arius = rs.getString("sum(total_price)");
                    String sq = "SELECT * FROM set_sales WHERE dealer_name='" + cname + "' AND date='" + dt + "' AND dealer='" + dealer1 + "'";
                    pst = conn.prepareStatement(sq);
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        String cn = rs.getString("dealer_name");
                        String dtt = rs.getString("date");
                        String update = "UPDATE set_sales SET status='" + "complted" + "' WHERE dealer_name='" + cn + "' AND date='" + dtt + "' AND bill_number='" + bill + "'";
                        pst = conn.prepareStatement(update);
                        pst.execute();
                    } else {

                        String insert = "INSERT INTO set_sales(bill_number,dealer,dealer_name,date,status)VALUES('" + bill + "','" + dealer + "','" + n + "','" + dt + "','" + sta + "')";
                        pst = conn.prepareStatement(insert);
                        pst.execute();

                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

    }

    public void insertDataToSales_Bill_list(String bill, String dealer, String name, String amount) {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String addDate = d.format(jDateChooser2.getDate());
        String n = jTextField7.getText();

        try {
            String sq = "SELECT * FROM sales_bill_list WHERE dealer_name='" + name + "' AND dealer='" + dealer + "' AND bil_num='" + bill + "'";
            pst = conn.prepareStatement(sq);
            rs = pst.executeQuery();

            if (rs.next()) {

            } else {
                String sd = "INSERT INTO sales_bill_list(bil_num,date,dealer,dealer_name,bil_amount)"
                        + "VALUES('" + bill + "','" + cdate + "','" + dealer + "','" + name + "','" + amount + "')";
                pst = conn.prepareStatement(sd);
                pst.execute();

            }
        } catch (Exception e) {
        }
    }

    //add or update arius table
    public void addOrUpdateariusTable(String dealer, String n, String date, String sum_of_arius) {

        try {

            String j = "SELECT dealer,dealer_name,date,total_price FROM sales_list WHERE dealer='" + dealer + "' AND dealer_name='" + n + "'";
            pst = conn.prepareStatement(j);
            rs = pst.executeQuery();
            if (rs.next()) {
                String dealer1 = rs.getString("dealer");
                String name = rs.getString("dealer_name");

                String sq = "SELECT dealer,name,date,arius_amount,complete_amount FROM arius_amount WHERE dealer='" + dealer1 + "' AND name='" + name + "'";
                pst = conn.prepareStatement(sq);
                rs = pst.executeQuery();

                if (rs.next()) {
                    UpdateAriusTable(name, cdate, sum_of_arius, dealer1);
                } else {

                    insertDataToArius(dealer1, name, cdate, sum_of_arius);
                }

            }
        } catch (Exception e) {
        }

    }

    //insert data to arius amout table
    public void insertDataToArius(String dealer, String name, String date, String arius_amount) {

        try {
            String sql = "INSERT INTO arius_amount(dealer,name,date,arius_amount)VALUES('" + dealer + "','" + name + "','" + date + "','" + arius_amount + "')";
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        //code end here   
    }
    //update arius table

    public void UpdateAriusTable(String name, String date, String arius_amoun, String dealer) {

        try {
            String sql = "UPDATE arius_amount SET arius_amount='" + arius_amoun + "' WHERE name='" + name + "' AND dealer='" + dealer + "'";
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

//code end here
    //check if there is any pending order under perticular creditor
    public void getCreditorData(String n, String addDate) {

        try {

            String st = "pending";
            String j = "SELECT creditor_name,lorry_number,date,status FROM creditor_details WHERE status='" + st + "' AND creditor_name='" + n + "' AND date='" + addDate + "' ";
            pst = conn.prepareStatement(j);
            rs = pst.executeQuery();

            if (rs.next()) {
                String cname = rs.getString("creditor_name");
                String dt = rs.getString("date");
                String ln = rs.getString("lorry_number");
                String sta = rs.getString("status");

                String sq = "SELECT * FROM set_status WHERE name='" + cname + "' AND date='" + dt + "'";
                pst = conn.prepareStatement(sq);
                rs = pst.executeQuery();

                if (rs.next()) {
                    String cn = rs.getString("name");
                    String dtt = rs.getString("date");
                    String update = "UPDATE set_status SET status='" + "pending" + "' WHERE name='" + cn + "' AND date='" + dtt + "'";
                    pst = conn.prepareStatement(update);
                    pst.execute();
                    //UpdateAriusCreditorTable(String name,String date,String arius_amoun);
                } else {
                    String insert = "INSERT INTO set_status(name,lorry_number,date,status)VALUES('" + cname + "','" + ln + "','" + dt + "','" + sta + "')";
                    pst = conn.prepareStatement(insert);
                    pst.execute();

                }
            } else {

                String st1 = "completed";
                String j1 = "SELECT creditor_name,lorry_number,date,status FROM creditor_details WHERE status='" + st1 + "' AND creditor_name='" + n + "' AND date='" + addDate + "' ";
                pst = conn.prepareStatement(j1);
                rs = pst.executeQuery();

                if (rs.next()) {
                    String cname = rs.getString("creditor_name");
                    String dt = rs.getString("date");
                    String ln = rs.getString("lorry_number");
                    String sta = rs.getString("status");

                    String sq = "SELECT * FROM set_status WHERE name='" + cname + "' AND date='" + dt + "'";
                    pst = conn.prepareStatement(sq);
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        String cn = rs.getString("name");
                        String dtt = rs.getString("date");
                        String update = "UPDATE set_status SET status='" + "completed" + "' WHERE name='" + cn + "' AND date='" + dtt + "'";
                        pst = conn.prepareStatement(update);
                        pst.execute();
                    } else {
                        String insert = "INSERT INTO set_status(name,lorry_number,date,status)VALUES('" + cname + "','" + ln + "','" + dt + "','" + sta + "')";
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

//add and update creditor arius amount when update price in creditor table
    public void setCreditorAriusAmountUpdate(String creditor, String date) {

        double arius_total = 0.0;
        double complete = 0.0;
        double currentArius = 0.0;
        try {

            try {
                String sql = "SELECT creditor_name,sum(total_price) FROM creditor_table WHERE creditor_name='" + creditor + "'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("creditor_name");
                    String arius_amoun = rs.getString("sum(total_price)");
                    currentArius = Double.parseDouble(arius_amoun);

                    String sq = "SELECT * FROM arius_creditor_amount WHERE name='" + creditor + "'";
                    pst = conn.prepareStatement(sq);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        String compl = rs.getString("complete");
                        complete = Double.parseDouble(compl);
                        arius_total = currentArius - complete;
                        UpdateAriusCreditorTable2(name, arius_total);

                    }

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }

        } catch (Exception e) {
        }

    }
//add and update creditor arius table

    public void setCreditorAriusAmount(String creditor, String date, String arius_amoun) {

        try {
            String sql = "SELECT creditor_name,total_price FROM creditor_table WHERE creditor_name='" + creditor + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String name = rs.getString("creditor_name");

                String sq = "SELECT * FROM arius_creditor_amount WHERE name='" + creditor + "'";
                pst = conn.prepareStatement(sq);
                rs = pst.executeQuery();

                if (rs.next()) {

                    UpdateAriusCreditorTable(creditor, arius_amoun);

                } else {

                    insertDataToAriusCreditor(creditor, date, arius_amoun);

                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

    }
    //insert data to arius amout creditor table

    public void insertDataToAriusCreditor(String name, String date, String arius_amount) {

        try {
            String sql = "INSERT INTO arius_creditor_amount(name,date,arius_amount)VALUES('" + name + "','" + date + "','" + arius_amount + "')";
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        //code end here   
    }
    //update arius crediot table

    public void UpdateAriusCreditorTable(String name, String arius_amoun) {
        try {
            String sql = "UPDATE arius_creditor_amount SET arius_amount='" + arius_amoun + "' WHERE name='" + name + "'";
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    // update creditor arius amount in update creditor step
    public void UpdateAriusCreditorTable2(String name, Double arius_amoun) {
        try {
            String sql = "UPDATE arius_creditor_amount SET arius_amount='" + arius_amoun + "' WHERE name='" + name + "'";
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    //code end here   
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
        jDateChooser6 = new com.toedter.calendar.JDateChooser();
        jButton44 = new javax.swing.JButton();
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
        jTextField47 = new javax.swing.JTextField();
        jTextField48 = new javax.swing.JTextField();
        jButton42 = new javax.swing.JButton();
        jTextField49 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
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
        jButton39 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jLabel84 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton43 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jScrollPane25 = new javax.swing.JScrollPane();
        jTable24 = new javax.swing.JTable();
        jButton45 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
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
        jTextField50 = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jButton56 = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
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
        jLabel76 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        p6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jDateChooser11 = new com.toedter.calendar.JDateChooser();
        jButton52 = new javax.swing.JButton();
        p7 = new javax.swing.JPanel();
        topbar = new javax.swing.JPanel();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        topbar_menu = new javax.swing.JPanel();
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
        jDateChooser9 = new com.toedter.calendar.JDateChooser();
        jTextField53 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jDateChooser10 = new com.toedter.calendar.JDateChooser();
        jButton51 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jButton49 = new javax.swing.JButton();
        tbp3 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTable16 = new javax.swing.JTable();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTable17 = new javax.swing.JTable();
        jLabel56 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jTextField54 = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jDateChooser13 = new com.toedter.calendar.JDateChooser();
        jButton53 = new javax.swing.JButton();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField51 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jButton50 = new javax.swing.JButton();
        jDateChooser8 = new com.toedter.calendar.JDateChooser();
        jLabel78 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jButton54 = new javax.swing.JButton();
        jButton55 = new javax.swing.JButton();
        jLabel99 = new javax.swing.JLabel();
        jDateChooser12 = new com.toedter.calendar.JDateChooser();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField52 = new javax.swing.JTextField();
        jTextField55 = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
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
        jDateChooser7 = new com.toedter.calendar.JDateChooser();
        jButton48 = new javax.swing.JButton();
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
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jButton18)
                .addGap(65, 65, 65))
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

        jButton44.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton44.setText("SET");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jDateChooser6, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                    .addComponent(jDateChooser6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
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
        p2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCreditorName.setFont(new java.awt.Font("Iskoola Pota", 0, 12)); // NOI18N
        txtCreditorName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCreditorNameActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Creditor Name");

        jcategory.setFont(new java.awt.Font("Iskoola Pota", 0, 12)); // NOI18N
        jcategory.setToolTipText("භාණ්ඩ");
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
        jkg.setToolTipText("මලු වර්ගය(උදා :- 5kg,10kg)");
        jkg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jkgActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Date");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Add manual kg ");
        jLabel5.setToolTipText("ලූස් තිබෙන ප්‍රමාණය");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Per kg/unit (Price)");

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
        jLabel16.setText("Total Amount(kg/units)");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("00");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Total Price(Rs)");

        txtQuantity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtQuantity.setToolTipText("ලූස් තිබෙන ප්‍රමාණය");
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
        jr1.setToolTipText("ලූස් තීබේ නම් පමණක් Y දෙන්න");
        jr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jr1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jr2);
        jr2.setText("Y");
        jr2.setToolTipText("ලූස් තීබේ නම් පමණක් Y දෙන්න");
        jr2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jr2ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Activate Manual Kg");
        jLabel19.setToolTipText("ලූස් තීබේ නම් පමණක් Y දෙන්න");

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel53.setText("Category");

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel54.setText("Type of KG");
        jLabel54.setToolTipText("මලු වර්ගය(උදා :- 5kg,10kg)");

        jButton21.setBackground(new java.awt.Color(255, 51, 51));
        jButton21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setText("RESET");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel59.setText("Bill Number");

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jTextField47.setText("0");
        jTextField47.setBorder(javax.swing.BorderFactory.createTitledBorder("Arius Amount"));

        jTextField48.setBorder(javax.swing.BorderFactory.createTitledBorder("Bill Amount"));

        jButton42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton42.setText("Send To Arius");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jTextField49.setText("0");
        jTextField49.setBorder(javax.swing.BorderFactory.createTitledBorder("Total Arius Amount"));

        jLabel108.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel108.setText("රු.");

        jLabel109.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel109.setText("රු.");

        jLabel110.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel110.setText("රු.");

        jLabel111.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel111.setText("රු.");

        jLabel112.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel112.setText("රු.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel109)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(28, 28, 28))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jr1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jr2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17))
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(txtCreditorName, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jcategory, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton11))
                            .addComponent(jLabel53))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jkg, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(302, 302, 302)
                                .addComponent(jLabel18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel108)))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel59)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField33)
                                .addGap(40, 40, 40))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel110)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton21)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField48)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel111)
                                .addGap(0, 0, 0)
                                .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel112)
                                .addGap(0, 0, 0)
                                .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton42)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel53))
                                    .addComponent(jLabel54))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCreditorName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jcategory, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jkg, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton11)))
                        .addGap(22, 22, 22)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(jLabel17))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel14)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jr1)
                            .addComponent(jr2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jLabel59)
                        .addComponent(jLabel6)))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel108)
                        .addComponent(jLabel109))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel110)
                    .addComponent(jLabel111)
                    .addComponent(jLabel112))
                .addGap(17, 17, 17))
        );

        p2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 21, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Category");

        txtCat.setFont(new java.awt.Font("Iskoola Pota", 0, 12)); // NOI18N
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
                .addContainerGap(13, Short.MAX_VALUE))
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

        p2.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(802, 21, -1, 334));

        jTable1.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
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

        p2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 373, 1049, 311));

        rightPanel.add(p2, "card3");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable3.setFont(new java.awt.Font("Iskoola Pota", 0, 12)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable3);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 170, 190));

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
        jPanel4.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 177, 37));

        jLabel21.setText(" Name");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, -1, -1));

        jLabel22.setText("Mobile Number");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 348, 177, -1));

        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });
        jPanel4.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 368, 177, 37));
        jPanel4.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 168, 37));

        jLabel23.setText("Lorry Number");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, -1, -1));

        jLabel24.setText("Date");
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, -1, -1));

        jTextField11.setFont(new java.awt.Font("Iskoola Pota", 0, 12)); // NOI18N
        jPanel4.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 300, 177, 37));

        jLabel25.setText("Category");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 280, -1, -1));
        jPanel4.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, 42, 37));

        jLabel26.setText("KG");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 210, -1, -1));

        jLabel27.setText("Quantity");
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 210, -1, -1));

        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField13KeyReleased(evt);
            }
        });
        jPanel4.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(857, 230, 80, 37));

        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField14KeyReleased(evt);
            }
        });
        jPanel4.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 230, 100, 37));

        jLabel28.setText("Total KG");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 210, -1, -1));

        jTable7.setFont(new java.awt.Font("Iskoola Pota", 0, 12)); // NOI18N
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

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, 11, 410, 190));

        jLabel30.setText("Price per 1kg/1 Unit");
        jPanel4.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, -1, -1));

        jTextField15.setBackground(new java.awt.Color(0, 0, 255));
        jTextField15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField15.setForeground(new java.awt.Color(255, 255, 255));
        jTextField15.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField15KeyReleased(evt);
            }
        });
        jPanel4.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 100, 37));

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
        jPanel4.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 350, 100, 37));

        jDateChooser2.setDateFormatString("yyyy-MM-dd");
        jPanel4.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 130, 37));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select route", "Tissa,Kataragama", "Hambantota", "Weeraketiya", " " }));
        jPanel4.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 368, 150, 30));
        jPanel4.add(jTextField45, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 368, 126, 37));

        jLabel82.setText("Name of the collector");
        jPanel4.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 348, 120, -1));

        jLabel83.setText("Arius Amount");
        jPanel4.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, -1, -1));

        jTextField46.setEditable(false);
        jPanel4.add(jTextField46, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 96, 37));

        jLabel85.setEditable(false);
        jLabel85.setBorder(javax.swing.BorderFactory.createTitledBorder("Invoice NUmber"));
        jPanel4.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 177, 42));

        jButton39.setText("Add");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton39, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 350, 74, 37));

        jButton41.setText("<html>Remove<br>Selected Row</html>");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton41, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 300, -1, 30));

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel84.setText("Bill Amount :");
        jPanel4.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 310, -1, -1));

        jLabel86.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel86.setText("0.0");
        jPanel4.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 310, -1, -1));

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel87.setText("Arius Amount :");
        jPanel4.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 340, -1, -1));

        jLabel88.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel88.setText("0.0");
        jPanel4.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 340, -1, -1));

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel89.setText("Total Amount :");
        jPanel4.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 370, -1, -1));

        jLabel90.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel90.setText("0.0");
        jPanel4.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 370, -1, -1));

        jCheckBox1.setText("Get Luuse");
        jCheckBox1.setToolTipText("දැනට කඩා ඇති මලු/පෙට්ටි (loose එවයින්) වලින් ගන්නවා නම්");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel4.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 210, -1, -1));

        jCheckBox2.setText("AddLuuse");
        jCheckBox2.setToolTipText("මල්ලක්/පෙට්ටියක් කඩා ප්‍රමාණයක් ගන්නවා නම්");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jPanel4.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 240, -1, -1));

        jButton43.setText("Close Bill View");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton43, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 151, 110, 40));

        jButton46.setText("Get Bill View");
        jButton46.setToolTipText("අලුත් බිලක් සඳහා \"Get Bill View\" ඔබන්න");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton46, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 151, 110, 40));

        jTable24.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Names of customers"
            }
        ));
        jTable24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable24MouseClicked(evt);
            }
        });
        jScrollPane25.setViewportView(jTable24);

        jPanel4.add(jScrollPane25, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 11, 160, 190));

        jButton45.setBackground(new java.awt.Color(255, 51, 51));
        jButton45.setText("Clear All");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton45, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 301, 73, 30));

        jButton47.setText("Add");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton47, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 350, 74, 37));

        jLabel113.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel113.setText("රු.");
        jPanel4.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 370, -1, -1));

        jLabel114.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel114.setText("රු.");
        jPanel4.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 310, -1, -1));

        jLabel115.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel115.setText("රු.");
        jPanel4.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 340, -1, -1));

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
        jRadioButton5.setText("Salt");
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
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
        });
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

        jTextField50.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField50KeyReleased(evt);
            }
        });

        jLabel91.setText("Luuse");

        jButton56.setText("Clear");
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });

        jLabel55.setFont(new java.awt.Font("Iskoola Pota", 1, 12)); // NOI18N
        jLabel55.setText("රු.");

        jLabel116.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel116.setText("0.0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel91)
                                                    .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel39)
                                                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel37)))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(52, 52, 52)
                                                .addComponent(jRadioButton9)
                                                .addGap(18, 18, 18)
                                                .addComponent(jRadioButton10)
                                                .addGap(28, 28, 28)
                                                .addComponent(jLabel55)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel116)))))
                                .addGap(141, 141, 141)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jRadioButton5)
                                .addGap(10, 10, 10)
                                .addComponent(jRadioButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jRadioButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jRadioButton8)
                                .addGap(50, 50, 50)
                                .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton20)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(34, 34, 34))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton5)
                        .addComponent(jRadioButton6)
                        .addComponent(jRadioButton7)
                        .addComponent(jRadioButton8))
                    .addComponent(jDateChooser5, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton56)
                                .addGap(27, 27, 27))))
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
                                    .addComponent(jRadioButton10)
                                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel116))))
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
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel91))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel39)
                                    .addComponent(jLabel37))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField22, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                    .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                .addGap(34, 34, 34))
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
        jTable8.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
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

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel76.setText("......");

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel79.setText("......");

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
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel81)
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel42)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel43))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel76)
                                            .addComponent(jLabel79))
                                        .addGap(22, 22, 22)))
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
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel81)))
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel76)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel79))
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
                .addContainerGap(39, Short.MAX_VALUE))
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

        jTable11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable11.setToolTipText("");
        jTable11.setOpaque(false);
        jTable11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable11MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(jTable11);

        jDateChooser11.setDateFormatString("yyyy-MM-dd");

        jButton52.setText("SET");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jDateChooser11, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 1103, Short.MAX_VALUE)
                        .addGap(27, 27, 27))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton52, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jDateChooser11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                .addGap(17, 17, 17))
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
        jTable14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable14MouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(jTable14);

        jTable15.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane16.setViewportView(jTable15);

        jLabel47.setText("Amount");

        jLabel48.setText("Cheque No");

        jLabel49.setText("Cheque Date");

        jLabel50.setText("Available Balance");

        jLabel51.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel51.setText("Total");

        jLabel52.setText("Arrias");

        jTextField26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField26ActionPerformed(evt);
            }
        });

        jTextField27.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField27KeyReleased(evt);
            }
        });

        jTextField28.setEditable(false);
        jTextField28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField28ActionPerformed(evt);
            }
        });

        jTextField29.setEditable(false);
        jTextField29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField29ActionPerformed(evt);
            }
        });

        jButton30.setText("Add");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jTextField53.setEditable(false);

        jLabel77.setText("Name");

        jDateChooser10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser10MouseClicked(evt);
            }
        });

        jButton51.setText("SET");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("<html>Total Cheque<br>Balance :</html>");

        jLabel93.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel93.setText("රු.");

        jLabel94.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel94.setText("රු.");

        jLabel95.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel95.setText("රු.");

        jButton49.setText("Clear");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tbp2Layout = new javax.swing.GroupLayout(tbp2);
        tbp2.setLayout(tbp2Layout);
        tbp2Layout.setHorizontalGroup(
            tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbp2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbp2Layout.createSequentialGroup()
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tbp2Layout.createSequentialGroup()
                        .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tbp2Layout.createSequentialGroup()
                                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                                .addGap(88, 88, 88))
                            .addGroup(tbp2Layout.createSequentialGroup()
                                .addComponent(jDateChooser10, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(tbp2Layout.createSequentialGroup()
                                .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48)
                                    .addComponent(jLabel49)
                                    .addComponent(jLabel77))
                                .addGap(68, 68, 68))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbp2Layout.createSequentialGroup()
                                .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(tbp2Layout.createSequentialGroup()
                                        .addComponent(jLabel52)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel95))
                                    .addGroup(tbp2Layout.createSequentialGroup()
                                        .addComponent(jLabel50)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                        .addComponent(jLabel94))
                                    .addGroup(tbp2Layout.createSequentialGroup()
                                        .addComponent(jLabel47)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel93)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField28)
                            .addComponent(jTextField26)
                            .addComponent(jTextField27)
                            .addComponent(jTextField29)
                            .addComponent(jDateChooser9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField53, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(152, 152, 152))))
        );
        tbp2Layout.setVerticalGroup(
            tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbp2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbp2Layout.createSequentialGroup()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton51, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)))
                    .addGroup(tbp2Layout.createSequentialGroup()
                        .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField53, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel77))
                        .addGap(18, 18, 18)
                        .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47)
                            .addComponent(jLabel93))
                        .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tbp2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbp2Layout.createSequentialGroup()
                                        .addComponent(jLabel49)
                                        .addGap(8, 8, 8))))
                            .addGroup(tbp2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tbp2Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel50)
                                    .addComponent(jLabel94))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel52)
                                    .addComponent(jLabel95)))
                            .addGroup(tbp2Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(33, 33, 33)
                .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(tbp2Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(tbp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jTable16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable16MouseClicked(evt);
            }
        });
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

        jLabel56.setText("Avalble Balance");

        jTextField30.setEditable(false);

        jLabel57.setText("Day Credit Balance");

        jTextField31.setEditable(false);
        jTextField31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31ActionPerformed(evt);
            }
        });

        jLabel58.setText("Total Balance");

        jTextField32.setEditable(false);

        jTextField54.setEditable(false);

        jLabel96.setText("Name");

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel102.setText("<html>Total Credit<br>Balance :</html>");

        jLabel103.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel103.setText("Total");

        jDateChooser13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser13MouseClicked(evt);
            }
        });

        jButton53.setText("SET");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });

        jLabel104.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel104.setText("රු.");

        jLabel105.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel105.setText("රු.");

        jLabel106.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel106.setText("රු.");

        javax.swing.GroupLayout tbp3Layout = new javax.swing.GroupLayout(tbp3);
        tbp3.setLayout(tbp3Layout);
        tbp3Layout.setHorizontalGroup(
            tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbp3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tbp3Layout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tbp3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tbp3Layout.createSequentialGroup()
                                        .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(tbp3Layout.createSequentialGroup()
                                                .addComponent(jLabel56)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel104)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(tbp3Layout.createSequentialGroup()
                                                .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(tbp3Layout.createSequentialGroup()
                                                        .addComponent(jLabel58)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel106))
                                                    .addGroup(tbp3Layout.createSequentialGroup()
                                                        .addComponent(jLabel57)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jLabel105)))
                                                .addGap(4, 4, 4)))
                                        .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField30)
                                            .addComponent(jTextField31)
                                            .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tbp3Layout.createSequentialGroup()
                                        .addComponent(jLabel96)
                                        .addGap(90, 90, 90)
                                        .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(tbp3Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jDateChooser13, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        tbp3Layout.setVerticalGroup(
            tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbp3Layout.createSequentialGroup()
                .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbp3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE))
                    .addGroup(tbp3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField54, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel96))
                        .addGap(26, 26, 26)
                        .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel104))
                        .addGap(35, 35, 35)
                        .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel105))
                        .addGap(47, 47, 47)
                        .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel58)
                            .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel106))))
                .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbp3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbp3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tbp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(294, 294, 294))))
        );

        topbar_menu.add(tbp3, "card4");

        tbp1.setBackground(new java.awt.Color(255, 255, 255));

        jTable12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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
        jTable12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable12MouseClicked(evt);
            }
        });
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

        jTextField23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField23MouseClicked(evt);
            }
        });
        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });
        jTextField23.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField23KeyReleased(evt);
            }
        });

        jTextField24.setEditable(false);

        jTextField25.setEditable(false);
        jTextField25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField25ActionPerformed(evt);
            }
        });

        jButton29.setText("Add");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("<html>Total Cash<br>Balance :</html>");

        jLabel3.setFont(new java.awt.Font("Iskoola Pota", 1, 14)); // NOI18N
        jLabel3.setText("jLabel3");

        jTextField51.setEditable(false);
        jTextField51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField51ActionPerformed(evt);
            }
        });

        jLabel75.setText("Name");

        jButton50.setText("SET");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        jDateChooser8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser8MouseClicked(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel78.setText("රු.");

        jLabel80.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel80.setText("රු.");

        jLabel92.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel92.setText("රු.");

        jLabel97.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel97.setText("Add Customer");

        jLabel98.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel98.setText("Dealer");

        jButton54.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton54.setText("Reset");

        jButton55.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton55.setText("Add");
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });

        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel99.setText("Name");

        jLabel100.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel100.setText("Date");

        jLabel101.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel101.setText("Arrears Amount");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Deliver/Detor", "Salt", "Customer" }));

        jTextField52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField52ActionPerformed(evt);
            }
        });

        jLabel107.setFont(new java.awt.Font("Iskoola Pota", 0, 14)); // NOI18N
        jLabel107.setText("රු.");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel100)
                            .addComponent(jLabel101))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField52, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, 234, Short.MAX_VALUE)
                            .addComponent(jDateChooser12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel98)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel97))
                            .addComponent(jLabel99, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel107)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel97)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel98)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel99)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel100)
                .addGap(3, 3, 3)
                .addComponent(jDateChooser12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel101)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel107))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton31.setText("Clear");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tbp1Layout = new javax.swing.GroupLayout(tbp1);
        tbp1.setLayout(tbp1Layout);
        tbp1Layout.setHorizontalGroup(
            tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbp1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbp1Layout.createSequentialGroup()
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 754, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tbp1Layout.createSequentialGroup()
                        .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(tbp1Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jDateChooser8, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(tbp1Layout.createSequentialGroup()
                                    .addComponent(jLabel46)
                                    .addGap(56, 56, 56)
                                    .addComponent(jLabel92)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField24))
                                .addGroup(tbp1Layout.createSequentialGroup()
                                    .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(tbp1Layout.createSequentialGroup()
                                            .addComponent(jLabel45)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel80))
                                        .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel75)
                                            .addGroup(tbp1Layout.createSequentialGroup()
                                                .addComponent(jLabel44)
                                                .addGap(13, 13, 13)
                                                .addComponent(jLabel78))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jTextField51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(tbp1Layout.createSequentialGroup()
                                .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tbp1Layout.setVerticalGroup(
            tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tbp1Layout.createSequentialGroup()
                .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tbp1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tbp1Layout.createSequentialGroup()
                                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton50, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                    .addComponent(jDateChooser8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(tbp1Layout.createSequentialGroup()
                                .addGap(204, 204, 204)
                                .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(tbp1Layout.createSequentialGroup()
                                .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel75))
                                .addGap(18, 18, 18)
                                .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel44)
                                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel78))
                                .addGap(18, 18, 18)
                                .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel45)
                                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel80))
                                .addGap(28, 28, 28)
                                .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel46)
                                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel92))))
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tbp1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(tbp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tbp1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)))))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        topbar_menu.add(tbp1, "card2");

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
                .addContainerGap(300, Short.MAX_VALUE))
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
                .addContainerGap(326, Short.MAX_VALUE))
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
                .addContainerGap(325, Short.MAX_VALUE))
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
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

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
                .addContainerGap(235, Short.MAX_VALUE))
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
                .addContainerGap(19, Short.MAX_VALUE))
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

        jDateChooser7.setDateFormatString("yyyy-MM-dd");

        jButton48.setText("OK");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tp1Layout = new javax.swing.GroupLayout(tp1);
        tp1.setLayout(tp1Layout);
        tp1Layout.setHorizontalGroup(
            tp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tp1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(tp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tp1Layout.createSequentialGroup()
                        .addComponent(jDateChooser7, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton48)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tp1Layout.createSequentialGroup()
                        .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
                        .addGap(262, 262, 262))))
        );
        tp1Layout.setVerticalGroup(
            tp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tp1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(tp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton48))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                .addGap(240, 240, 240))
        );

        tmenu.add(tp1, "card2");

        javax.swing.GroupLayout tp2Layout = new javax.swing.GroupLayout(tp2);
        tp2.setLayout(tp2Layout);
        tp2Layout.setHorizontalGroup(
            tp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1149, Short.MAX_VALUE)
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
            .addComponent(topmenu, javax.swing.GroupLayout.DEFAULT_SIZE, 1149, Short.MAX_VALUE)
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
                .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1149, Short.MAX_VALUE))
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
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1383, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        String h = jTextField1.getText();

        try {
            String s = "SELECT * FROM  set_status WHERE name LIKE '%" + h + "%' OR lorry_number LIKE '%" + h + "%' ORDER BY date desc";
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
        String h = jTextField1.getText();

        try {
            String s = "SELECT * FROM  set_status WHERE name LIKE '%" + h + "%' OR lorry_number LIKE '%" + h + "%' ORDER BY date desc";
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
        String name1 = jTable2.getValueAt(row, 1).toString();
        try {

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

            if (Usertype.equals("User")) {
                jTable1.getColumnModel().getColumn(8).setMinWidth(0);
                jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
                jTable1.getColumnModel().getColumn(9).setMinWidth(0);
                jTable1.getColumnModel().getColumn(9).setMaxWidth(0);
                jTable1.getColumnModel().getColumn(10).setMinWidth(0);
                jTable1.getColumnModel().getColumn(10).setMaxWidth(0);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        //get crditor arius amountand set it       CASE WHEN movement = 'IN' THEN quantity ELSE 0 END
        String arius_amountnew = "0";
        try {
            String sd = "SELECT sum(arius_amount) FROM arius_creditor_amount WHERE name='" + name1 + "'";
            pst = conn.prepareStatement(sd);
            rs = pst.executeQuery();
            if (rs.next()) {

                String arius_amount = rs.getString("sum(arius_amount)");
                arius_amountnew = arius_amount;

                if (arius_amountnew == null) {

                    //JOptionPane.showMessageDialog(this, "0");
                    jTextField47.setText("0");
                } else {
                    // JOptionPane.showMessageDialog(this, arius_amountnew);
                    jTextField47.setText(arius_amountnew);
                }

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jTable2MouseClicked
    public void getProductdata() {
        try {
            String s = "SELECT * FROM  product_sales";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
        }

    }
//select data from product properties

    public void getKiloPackets() {

        int row = jTable3.getSelectedRow();
        String id = jTable3.getValueAt(row, 0).toString();

        try {
            String s = "SELECT type_of_kg,quantity,rest_of_quantity as luuse,total_quantity as total_kg,price_per_1kg,p_id FROM   product_properties WHERE p_id='" + id + "'";
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

        jComboBox2.setVisible(false);
        jLabel82.setVisible(false);
        jRadioButton1.setSelected(true);
        jButton47.setVisible(true);
        jButton39.setVisible(false);
        jTable3.setEnabled(false);
        jButton41.setEnabled(false);

        getProductdata();
        try {

            String s = "SELECT type_of_kg,quantity,rest_of_quantity as luuse,total_quantity as total_kg FROM   product_properties WHERE p_id='" + "ww" + "'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable4.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

        try {
            String select = "SELECT * FROM detor ORDER BY id DESC";
            pst = conn.prepareStatement(select);
            rs = pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

        try {
            String sql = "SELECT DISTINCT name as Names_of_customers FROM detor";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable24.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

        jButton43.setEnabled(false);
        jButton46.setEnabled(true);

        jTable3.getColumnModel().getColumn(0).setMinWidth(0);
        jTable3.getColumnModel().getColumn(0).setMaxWidth(0);

        jButton3.setBackground(Color.white);
        jButton1.setBackground(Color.white);
        jButton13.setBackground(Color.white);
        jButton15.setBackground(Color.yellow);
        jButton22.setBackground(Color.white);
        jButton14.setBackground(Color.white);
        jButton23.setBackground(Color.white);
        jButton24.setBackground(Color.white);
        jButton25.setBackground(Color.white);
        jButton32.setBackground(Color.white);
    }//GEN-LAST:event_jButton15ActionPerformed
    public void getProductdata2() {
        try {
            String s = "SELECT * FROM  product_sales";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable9.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
        }

    }
//fetch data to table in stock 

    public void fetchDataStock() {
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
        jLabel76.setVisible(false);
        jLabel79.setVisible(false);
        fetchDataStock();
        getProductdata2();
        try {

            String s = "SELECT type_of_kg,quantity,rest_of_quantity as luuse,total_quantity as total_kg,price_per_1kg FROM   product_properties WHERE p_id='" + "ww" + "'";
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

        jButton3.setBackground(Color.white);
        jButton1.setBackground(Color.white);
        jButton13.setBackground(Color.white);
        jButton15.setBackground(Color.white);
        jButton22.setBackground(Color.white);
        jButton14.setBackground(Color.yellow);
        jButton23.setBackground(Color.white);
        jButton24.setBackground(Color.white);
        jButton25.setBackground(Color.white);
        jButton32.setBackground(Color.white);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        rightPanel.removeAll();
        rightPanel.add(p4);
        rightPanel.repaint();
        rightPanel.revalidate();

        try {
            String sq = "SELECT bill_number,dealer,dealer_name,date FROM set_sales ORDER BY status desc";
            pst = conn.prepareStatement(sq);
            rs = pst.executeQuery();
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
        getDataFromReturnTable();

        jButton3.setBackground(Color.white);
        jButton1.setBackground(Color.white);
        jButton13.setBackground(Color.yellow);
        jButton15.setBackground(Color.white);
        jButton22.setBackground(Color.white);
        jButton14.setBackground(Color.white);
        jButton23.setBackground(Color.white);
        jButton24.setBackground(Color.white);
        jButton25.setBackground(Color.white);
        jButton32.setBackground(Color.white);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            rightPanel.removeAll();
            rightPanel.add(p1);
            rightPanel.repaint();
            rightPanel.revalidate();
            fetchCreditordat();
//        String creditorName=txtCreditorName.getText();
//        getCreditorData(creditorName,date);
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        jButton3.setBackground(Color.yellow);
        jButton1.setBackground(Color.white);
        jButton13.setBackground(Color.white);
        jButton15.setBackground(Color.white);
        jButton22.setBackground(Color.white);
        jButton14.setBackground(Color.white);
        jButton23.setBackground(Color.white);
        jButton24.setBackground(Color.white);
        jButton25.setBackground(Color.white);
        jButton32.setBackground(Color.white);


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

        jButton3.setBackground(Color.white);
        jButton1.setBackground(Color.yellow);
        jButton13.setBackground(Color.white);
        jButton15.setBackground(Color.white);
        jButton22.setBackground(Color.white);
        jButton14.setBackground(Color.white);
        jButton23.setBackground(Color.white);
        jButton24.setBackground(Color.white);
        jButton25.setBackground(Color.white);
        jButton32.setBackground(Color.white);
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
            int h = Integer.parseInt(txtQuantity.getText());

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
            int h = Integer.parseInt(jTextField2.getText());

        } catch (NumberFormatException e) {

            jTextField2.setText("");
        }
        String a1 = jkg.getSelectedItem().toString();
        boolean check = jr2.isSelected();
        if (check == true) {

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
        jTextField47.setText("0");
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
        Date dat = jDateChooser1.getDate();
        String toprice = null;
        String nquantiy = jTextField2.getText();
        String bil_num = jTextField33.getText();
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = d.format(jDateChooser1.getDate());
        if (creditorname.equals("") || lorryNum.equals("") || nquantiy.equals("") || price.equals("") || dat == null || bil_num.equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill the empty fields");

        } else {
            int msg = JOptionPane.showConfirmDialog(this, "Do You Want To Update Price");

            if (msg == 0) {

                try {

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
                        String s = "UPDATE creditor_table SET bill_number='" + bil_num + "',price='" + price + "',total_price='" + toprice + "',status='" + status + "' WHERE creditor_id='" + id + "'";
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

                        String getCat = "SELECT * FROM product_sales WHERE category_name='" + cat + "'";
                        pst = conn.prepareStatement(getCat);
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            String cn = rs.getString("category_name");
                            int newTid = rs.getInt("id");
                            String getKg = "SELECT type_of_kg FROM  product_properties WHERE type_of_kg='" + kg + "' AND p_id='" + newTid + "'";
                            pst = conn.prepareStatement(getKg);
                            rs = pst.executeQuery();

                            if (rs.next()) {

                                String quaUpdate = "UPDATE product_properties SET rest_of_quantity='" + "0" + "',price_per_1kg='" + price + "' WHERE p_id='" + newTid + "' AND type_of_kg='" + kg + "'";

                                pst = conn.prepareStatement(quaUpdate);
                                pst.execute();

                            }

                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

//                    try {
//                        String sw="";
//                        pst=conn.prepareStatement(sw);
//                        rs=pst.executeQuery();
//                    } catch (Exception e) {
//                    }
                    fetchData();
                    fetchProductData(creditorname, fdate);
                    getCreditorData(creditorname, fdate);
                    String getUser = jLabel20.getText();
                    String move = " Price Updated";
                    String cred = "creditor";
                    double tpp = Double.parseDouble(toprice);
                    insertDataStock(cred, cat, kg, nquantiy, tokg, price, tpp, move, fdate, getUser);
                    //setCreditorAriusAmountUpdate(creditorname,fdate);
                    JOptionPane.showMessageDialog(null, "Updated...");
                    txtCreditorName.requestFocus();
                    jTextField2.setText("");
                    jTextField5.setText("");
                    jTextField4.setText("");
                    jkg.removeAllItems();
                    //jcategory.setSelectedIndex(0);
                    txtQuantity.setText("");

                    txtPrice.setText("");
                    jr1.setSelected(true);
                    txtQuantity.setEnabled(false);
                    txtQuantity.setEditable(false);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);
                }
                //count total bill amount

                int rowcount = jTable1.getRowCount();
                DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
                double tot = 0.00;
                String val = "";
                for (int i = 0; i < rowcount; i++) {
                    val = (String) df.getValueAt(i, 10);
                    tot = tot + Double.parseDouble(val);

                }
                jTextField48.setText(String.valueOf(tot));
                Double tarius = tot + Double.parseDouble(jTextField47.getText());
                toCreditorArius = tarius;
                jTextField49.setText(String.valueOf(tarius));
            }

        }

    }//GEN-LAST:event_jButton5ActionPerformed
    public void deleteSetStatus(String creditorname, String dat) {

        try {
            String g = "SELECT * FROM creditor_details WHERE creditor_name='" + creditorname + "' AND date='" + dat + "'";
            pst = conn.prepareStatement(g);
            rs = pst.executeQuery();
            if (rs.next()) {

            } else {
                String del = "DELETE FROM set_status WHERE name='" + creditorname + "' AND date='" + dat + "'";
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
        String kg2 = jkg.getSelectedItem().toString();
        String price = txtPrice.getText();
        String status = null;
        String lorryNum = jTextField3.getText();
        String tokg = jTextField5.getText();
        String toprice = null;
        String nquantiy = jTextField2.getText();
        SimpleDateFormat g1 = new SimpleDateFormat("yyyy-MM-dd");
        String dat = g1.format(jDateChooser1.getDate());
        if (creditorname.equals("") || lorryNum.equals("") || nquantiy.equals("") || price.equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill the empty fields");

        } else {

            int msg = JOptionPane.showConfirmDialog(this, "Do You Want To Delete ?");
            if (msg == 0) {
                try {
                    int id = Integer.parseInt(jLabel11.getText());
                    String sq = "DELETE FROM creditor_table WHERE creditor_id='" + id + "'";
                    pst = conn.prepareStatement(sq);
                    pst.execute();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);
                }
                try {

                    String getCat = "SELECT * FROM product_sales WHERE category_name='" + cat + "'";
                    pst = conn.prepareStatement(getCat);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        String cn = rs.getString("category_name");
                        int newTid = rs.getInt("id");
                        String getKg = "SELECT type_of_kg FROM product_properties WHERE type_of_kg='" + kg2 + "' AND p_id='" + newTid + "'";
                        pst = conn.prepareStatement(getKg);
                        rs = pst.executeQuery();

                        if (rs.next()) {

                            String quaUpdate = "UPDATE product_properties SET quantity=quantity-'" + jTextField2.getText() + "',total_quantity=total_quantity-'" + jTextField5.getText() + "' WHERE p_id='" + newTid + "' AND type_of_kg='" + kg2 + "'";

                            pst = conn.prepareStatement(quaUpdate);
                            pst.execute();

                        }

                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);
                }
                deleteSetStatus(creditorname, dat);
                fetchData();
                fetchProductData(creditorname, dat);
                String getUser = jLabel20.getText();
                String move = " Deleted";
                String cred = "creditorname";
                double tpp = Double.parseDouble(jTextField4.getText());
                insertDataStock(cred, cat, kg2, nquantiy, tokg, price, tpp, move, cdate, getUser);
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
        Date dat2 = jDateChooser1.getDate();
        String p = txtPrice.getText();
        String b = jLabel11.getText();
        boolean check = jr2.isSelected();
        String testQuq = txtQuantity.getText();
        String bil_num = "";

        String category = jcategory.getSelectedItem().toString();

        String price = null;
        String kg = null;
        String status = null;

        //get price
        if (p.equals("")) {
            price = "0.00";
            toprice = "0.00";
            status = "pending";
            bil_num = "null";
        } else {
            price = txtPrice.getText();
            toprice = jTextField4.getText();
            bil_num = jTextField33.getText();
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
        SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd");
        String date = g.format(jDateChooser1.getDate());
        String getBIll = jTextField33.getText();
        if (dat2 == null || creditorName.equals("") || lorryNum.equals("") || nquantiy.equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill the empty fields");

        } else if (!b.equals("")) {
            JOptionPane.showMessageDialog(this, "Can not add same data again and again. If you want to add a new data then press" + "'CANCEL'" + "button to RESET fields.");
        } else if (check == true) {
            if (testQuq.equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill the empty fields");
            } else {
                int msg = JOptionPane.showConfirmDialog(this, "Do you want to add this product ?");

                if (msg == 0) {

                    try {

                        String s = "INSERT INTO creditor_table(creditor_name,lorry_number,category,kg,quantiy,total_kg,date,bill_number,price,total_price,status)VALUES('" + creditorName + "','" + lorryNum + "','" + category + "','" + kg + "','" + nquantiy + "','" + tokg + "','" + date + "','" + bil_num + "'," + price + "','" + toprice + "','" + status + "')";

                        pst = conn.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
                        pst.execute();

                        int id1;

                        try ( ResultSet generatedKeys = pst.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                id1 = generatedKeys.getInt(1);
                                String sq = "INSERT INTO creditor_details(creditor_name,lorry_number,date,status,id)VALUES('" + creditorName + "','" + lorryNum + "','" + date + "','" + status + "','" + id1 + "')";

                                pst = conn.prepareStatement(sq);
                                pst.execute();
                            }
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);

                    }

                    try {

                        String getCat = "SELECT * FROM product_sales WHERE category_name='" + category + "'";
                        pst = conn.prepareStatement(getCat);
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            String cn = rs.getString("category_name");
                            int newTid = rs.getInt("id");
                            String getKg = "SELECT type_of_kg FROM  product_properties WHERE type_of_kg='" + kg + "' AND p_id='" + newTid + "'";
                            pst = conn.prepareStatement(getKg);
                            rs = pst.executeQuery();

                            if (rs.next()) {

                                String quaUpdate = "UPDATE product_properties SET quantity=quantity+'" + nquantiy + "',rest_of_quantity='" + "0" + "',total_quantity=total_quantity+'" + tokg + "',price_per_1kg='" + price + "' WHERE p_id='" + newTid + "' AND type_of_kg='" + kg + "'";

                                pst = conn.prepareStatement(quaUpdate);
                                pst.execute();

                            } else {

                                String sr = "INSERT INTO product_properties(type_of_kg,quantity,rest_of_quantity,total_quantity,price_per_1kg,p_id)VALUES('" + kg + "','" + nquantiy + "','" + "0" + "','" + tokg + "','" + price + "','" + newTid + "')";
                                pst = conn.prepareStatement(sr);
                                pst.execute();
                            }

                        } else {

                            String sq = "INSERT INTO product_sales(category_name)VALUES('" + category + "')";
                            pst = conn.prepareStatement(sq, Statement.RETURN_GENERATED_KEYS);
                            pst.execute();

                            int id2;
                            try ( ResultSet generatedKeys = pst.getGeneratedKeys()) {
                                if (generatedKeys.next()) {
                                    id2 = generatedKeys.getInt(1);
                                    int norest = 0;
                                    String sr = "INSERT INTO product_properties(type_of_kg,quantity,rest_of_quantity,total_quantity,price_per_1kg,p_id)VALUES('" + kg + "','" + nquantiy + "','" + norest + "','" + tokg + "','" + price + "','" + id2 + "')";

                                    pst = conn.prepareStatement(sr);
                                    pst.execute();
                                }
                            }

                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }
                    getCreditorData(creditorName, date);

                    fetchProductData(creditorName, date);

                    String getUser = jLabel20.getText();
                    String move = "IN";
                    String cred = "creditor";
                    double tpp = Double.parseDouble(toprice);
                    insertDataStock(cred, category, kg, nquantiy, tokg, price, tpp, move, cdate, getUser);
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
        } else {

            int msg = JOptionPane.showConfirmDialog(this, "Do you want to add this product ?");

            if (msg == 0) {

                try {

                    String s = "INSERT INTO creditor_table(creditor_name,lorry_number,category,kg,quantiy,total_kg,date,price,total_price,status)VALUES('" + creditorName + "','" + lorryNum + "','" + category + "','" + kg + "','" + nquantiy + "','" + tokg + "','" + date + "','" + price + "','" + toprice + "','" + status + "')";

                    pst = conn.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
                    pst.execute();

                    int id1;

                    try ( ResultSet generatedKeys = pst.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            id1 = generatedKeys.getInt(1);
                            String sq = "INSERT INTO creditor_details(creditor_name,lorry_number,date,status,id)VALUES('" + creditorName + "','" + lorryNum + "','" + date + "','" + status + "','" + id1 + "')";

                            pst = conn.prepareStatement(sq);
                            pst.execute();
                        }
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);

                }

                try {

                    String getCat = "SELECT * FROM product_sales WHERE category_name='" + category + "'";
                    pst = conn.prepareStatement(getCat);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        String cn = rs.getString("category_name");
                        int newTid = rs.getInt("id");
                        String getKg = "SELECT type_of_kg FROM  product_properties WHERE type_of_kg='" + kg + "' AND p_id='" + newTid + "'";
                        pst = conn.prepareStatement(getKg);
                        rs = pst.executeQuery();

                        if (rs.next()) {

                            String quaUpdate = "UPDATE product_properties SET quantity=quantity+'" + nquantiy + "',rest_of_quantity='" + "0" + "',total_quantity=total_quantity+'" + tokg + "',price_per_1kg='" + price + "' WHERE p_id='" + newTid + "' AND type_of_kg='" + kg + "'";

                            pst = conn.prepareStatement(quaUpdate);
                            pst.execute();

                        } else {

                            String sr = "INSERT INTO product_properties(type_of_kg,quantity,rest_of_quantity,total_quantity,price_per_1kg,p_id)VALUES('" + kg + "','" + nquantiy + "','" + "0" + "','" + tokg + "','" + price + "','" + newTid + "')";
                            pst = conn.prepareStatement(sr);
                            pst.execute();
                        }

                    } else {

                        String sq = "INSERT INTO product_sales(category_name)VALUES('" + category + "')";
                        pst = conn.prepareStatement(sq, Statement.RETURN_GENERATED_KEYS);
                        pst.execute();

                        int id2;
                        try ( ResultSet generatedKeys = pst.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                id2 = generatedKeys.getInt(1);
                                int norest = 0;
                                String sr = "INSERT INTO product_properties(type_of_kg,quantity,rest_of_quantity,total_quantity,price_per_1kg,p_id)VALUES('" + kg + "','" + nquantiy + "','" + norest + "','" + tokg + "','" + price + "','" + id2 + "')";

                                pst = conn.prepareStatement(sr);
                                pst.execute();
                            }
                        }

                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);
                }
                getCreditorData(creditorName, date);
                fetchProductData(creditorName, date);

                String getUser = jLabel20.getText();
                String move = "IN";
                String cred = "creditor";
                double tpp = Double.parseDouble(toprice);
                insertDataStock(cred, category, kg, nquantiy, tokg, price, tpp, move, cdate, getUser);
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
            int h = Integer.parseInt(txtPrice.getText());

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
    public void setRaadiobutEnable() {

        boolean jr = jr1.isSelected();
        boolean j2 = jr2.isSelected();

        if (jr == true) {
            txtQuantity.setEnabled(false);
            txtQuantity.setEditable(false);
            txtQuantity.setText("");
            jTextField2.setText("");
            jTextField5.setText("");
        } else {
            txtQuantity.setEnabled(true);
            txtQuantity.setEditable(true);
            jTextField2.setText("");
            jTextField5.setText("");
        }
    }
    private void jr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jr1ActionPerformed
        setRaadiobutEnable();
    }//GEN-LAST:event_jr1ActionPerformed
    public void getProductDataTosalesTAble() {

        int row = jTable3.getSelectedRow();
        String category = jTable3.getValueAt(row, 1).toString();

        jTextField11.setText(category);

    }
/// get total quantity

    public void getTotalQuantity2() {
        DefaultTableModel df = (DefaultTableModel) jTable10.getModel();
        int row = jTable10.getRowCount();
        int gettotal = 0;
        for (int i = 0; i < row; i++) {

            gettotal = gettotal + (int) df.getValueAt(i, 3);
        }

        jLabel42.setText(String.valueOf(gettotal));

    }

    public void getTotalQuantity() {
        DefaultTableModel df = (DefaultTableModel) jTable4.getModel();
        int row = jTable4.getRowCount();
        int gettotal = 0;
        for (int i = 0; i < row; i++) {

            gettotal = gettotal + (int) df.getValueAt(i, 3);
        }

        //jLabel3.setText(String.valueOf(gettotal));
    }
    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked

        getKiloPackets();
        getTotalQuantity();
        getProductDataTosalesTAble();
    }//GEN-LAST:event_jTable3MouseClicked
    public void getProductPropeties() {

        int row = jTable4.getSelectedRow();

        String kg = jTable4.getValueAt(row, 0).toString();
        String p = jTable4.getValueAt(row, 4).toString();
        jTextField12.setText(kg);
        // jTextField15.setText(p);

    }
    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        getProductPropeties();

//            
//            jTable7.getColumnModel().getColumn(7).setMinWidth(0);
//        jTable7.getColumnModel().getColumn(7).setMaxWidth(0);
    }//GEN-LAST:event_jTable4MouseClicked
//changing sellers

    public void selectSellers() {

        boolean detorq = jRadioButton1.isSelected();
        boolean deliver = jRadioButton2.isSelected();
        boolean customer = jRadioButton3.isSelected();
        boolean shop = jRadioButton4.isSelected();

        if (detorq == true) {
            jTextField9.setEnabled(true);
            jTextField9.setEditable(true);
            jTextField9.setText("");
            jTextField11.setText("Salt");
            jTextField7.setEnabled(true);
            jTextField7.setEditable(true);
            jTextField8.setEnabled(true);
            jTextField8.setEditable(true);
            jTextField45.setVisible(false);
            jComboBox2.setVisible(false);
            jLabel82.setVisible(false);

            try {
                String select = "SELECT * FROM detor ORDER BY id DESC";
                pst = conn.prepareStatement(select);
                rs = pst.executeQuery();
                jTable7.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }

        } else if (deliver) {
            jTextField7.setEnabled(true);
            jTextField7.setEditable(true);
            jTextField8.setEnabled(true);
            jTextField8.setEditable(true);
            jTextField11.setText("");
            jTextField9.setEnabled(true);
            jTextField9.setEditable(true);

            jTextField45.setVisible(true);
            jComboBox2.setVisible(true);
            jLabel82.setVisible(true);
            try {
                String select = "SELECT * FROM deliver ORDER BY id DESC";
                pst = conn.prepareStatement(select);
                rs = pst.executeQuery();
                jTable7.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        } else if (customer) {
            jTextField9.setEnabled(false);
            jTextField9.setEditable(false);
            jTextField9.setText("");
            jTextField11.setText("");
            jTextField7.setEnabled(true);
            jTextField7.setEditable(true);
            jTextField8.setEnabled(true);
            jTextField8.setEditable(true);
            jTextField45.setVisible(false);
            jComboBox2.setVisible(false);
            jLabel82.setVisible(false);
            try {
                String select = "SELECT * FROM customer ORDER BY id DESC";
                pst = conn.prepareStatement(select);
                rs = pst.executeQuery();
                jTable7.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        } else if (shop == true) {

            jTextField9.setEnabled(false);
            jTextField9.setEditable(false);
            jTextField7.setEnabled(false);
            jTextField7.setEditable(false);
            jTextField8.setEnabled(false);
            jTextField8.setEditable(false);
            jTextField9.setText("");
            jTextField8.setText("");
            jTextField7.setText("");
            jTextField11.setText("");
            jTextField45.setVisible(false);
            jComboBox2.setVisible(false);
            jLabel82.setVisible(false);

            try {
                String sele = "SELECT * FROM shop ORDER BY id DESC";
                pst = conn.prepareStatement(sele);
                rs = pst.executeQuery();
                jTable7.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }

        }

    }
    private void jTextField13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            jTextField14.setText("");
            jTextField16.setText("");
        }

        try {
            int h = Integer.parseInt(jTextField13.getText());

        } catch (NumberFormatException e) {

            jTextField13.setText("");
        }
        boolean addUse = jCheckBox1.isSelected();
        boolean getUse = jCheckBox2.isSelected();
        if (addUse == false && getUse == false) {
            int kg = Integer.parseInt(jTextField12.getText());
            int qua = Integer.parseInt(jTextField13.getText());

            int total = 0;
            total = (kg * qua);

            jTextField14.setText(String.valueOf(total));

            int tkg = Integer.parseInt(jTextField14.getText());
            int p = Integer.parseInt(jTextField15.getText());

            int top = (tkg * p);
            jTextField16.setText(String.valueOf(top));

        } else {
            int qua = Integer.parseInt(jTextField13.getText());
            jTextField14.setText(String.valueOf(qua));

            int tkg = Integer.parseInt(jTextField14.getText());
            int p = Integer.parseInt(jTextField15.getText());

            int top = (tkg * p);
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

        boolean n = jButton46.isEnabled();
        if (n == false) {
            JOptionPane.showMessageDialog(this, "cannot performe this task while in bill view");
            jButton46.setEnabled(true);
            jButton43.setEnabled(false);
            jRadioButton1.setSelected(true);
            jButton47.setVisible(true);
            jButton39.setVisible(false);
            jTextField45.setVisible(false);
            jTextField11.setText("Salt");
            jTable3.setEnabled(false);
            jComboBox2.setVisible(false);
            jLabel82.setVisible(false);
            try {
                String sql = "SELECT DISTINCT name as Names_of_customers FROM detor";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable24.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
            try {
                String select = "SELECT * FROM detor ORDER BY id DESC";
                pst = conn.prepareStatement(select);
                rs = pst.executeQuery();
                jTable7.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        } else {
            jButton47.setVisible(true);
            jButton39.setVisible(false);
            selectSellers();
            jTable3.setEnabled(false);
            try {
                String sql = "SELECT DISTINCT name as Names_of_customers FROM detor";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable24.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
            }
        }


    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        boolean n = jButton46.isEnabled();
        if (n == false) {
            JOptionPane.showMessageDialog(this, "cannot performe this task while in bill view");
            jRadioButton1.setSelected(true);
            jTextField11.setText("Salt");

            jButton46.setEnabled(true);
            jTextField45.setVisible(false);
            jComboBox2.setVisible(false);
            jLabel82.setVisible(false);
            jButton43.setEnabled(false);
            jButton47.setVisible(true);
            jButton39.setVisible(false);
            try {
                String sql = "SELECT DISTINCT name as Names_of_customers FROM  arius_amount WHERE dealer='Deliver/Detor'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable24.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
            try {
                String select = "SELECT * FROM detor ORDER BY id DESC";
                pst = conn.prepareStatement(select);
                rs = pst.executeQuery();
                jTable7.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        } else {
            selectSellers();
            jTable3.setEnabled(true);
            jButton47.setVisible(false);
            jButton39.setVisible(true);
            try {
                String sql = "SELECT DISTINCT name as Names_of_customers FROM  arius_amount WHERE dealer='Deliver/Detor'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable24.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }

    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        boolean n = jButton46.isEnabled();
        if (n == false) {
            JOptionPane.showMessageDialog(this, "cannot performe this task while in bill view");
            jRadioButton1.setSelected(true);
            jTextField11.setText("Salt");
            jButton46.setEnabled(true);
            jTextField45.setVisible(false);
            jComboBox2.setVisible(false);
            jLabel82.setVisible(false);
            jButton43.setEnabled(false);
            jButton47.setVisible(true);
            jButton39.setVisible(false);
            try {
                String sql = "SELECT DISTINCT name as Names_of_customers FROM  arius_amount WHERE dealer='Customer'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable24.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
            try {
                String select = "SELECT * FROM detor ORDER BY id DESC";
                pst = conn.prepareStatement(select);
                rs = pst.executeQuery();
                jTable7.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        } else {
            selectSellers();
            jButton47.setVisible(false);
            jButton39.setVisible(true);
            jTable3.setEnabled(true);
            try {
                String sql = "SELECT DISTINCT name as Names_of_customers FROM  arius_amount WHERE dealer='Customer'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable24.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
            }
        }

    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        boolean n = jButton46.isEnabled();
        if (n == false) {
            JOptionPane.showMessageDialog(this, "cannot performe this task while in bill view");
            jRadioButton1.setSelected(true);
            jTextField45.setVisible(false);
            jTextField11.setText("Salt");
            jComboBox2.setVisible(false);
            jLabel82.setVisible(false);
            jButton46.setEnabled(true);
            jButton43.setEnabled(false);
            jButton47.setVisible(true);
            jButton39.setVisible(false);
            try {
                String sql = "SELECT DISTINCT name as Names_of_customers FROM deliver";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable24.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
            try {
                String select = "SELECT * FROM detor ORDER BY id DESC";
                pst = conn.prepareStatement(select);
                rs = pst.executeQuery();
                jTable7.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        } else {
            selectSellers();
            jButton47.setVisible(false);
            jButton39.setVisible(true);
            jTable3.setEnabled(true);
            DefaultTableModel df = (DefaultTableModel) jTable24.getModel();
            int r = jTable24.getRowCount();
            for (int i = r; r > 0; i--) {
                {

                    df.removeRow(i - 1);
                }
            }
        }

    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jTextField14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyReleased


    }//GEN-LAST:event_jTextField14KeyReleased
//add data to detor table

    public void insertToDetor(String cat, String kg, String qua, String totalKg, String price, Double totalP) {
        String name = jTextField7.getText();
        String mn = jTextField8.getText();
        String ln = jTextField9.getText();
        SimpleDateFormat g1 = new SimpleDateFormat("yyyy-MM-dd");
        String dat = g1.format(jDateChooser2.getDate());

        try {
            String insert = "INSERT INTO detor(name,mobile_number,date,category,type_of_kg,quantity,total_kg,price_of_1kg,total_price)"
                    + "VALUES('" + name + "','" + mn + "','" + dat + "','" + cat + "','" + kg + "','" + qua + "','" + totalKg + "','" + price + "','" + totalP + "')";
            pst = conn.prepareStatement(insert);
            pst.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        try {
            String select = "SELECT * FROM detor ORDER BY id DESC";
            pst = conn.prepareStatement(select);
            rs = pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

    }

    //add data to deliver table
    public void insertToDeliver(String cat, String kg, String qua, String totalKg, String price, Double totalP) {

        String name = jTextField7.getText();
        String mn = jTextField8.getText();
        String ln = jTextField9.getText();
        SimpleDateFormat g1 = new SimpleDateFormat("yyyy-MM-dd");
        String dat = g1.format(jDateChooser2.getDate());
        String route = jComboBox2.getSelectedItem().toString();
        String collector = jTextField45.getText();
//        String cat=jTextField11.getText();
//        String kg=jTextField12.getText();
//        String qua=jTextField13.getText();
//        String totalKg=jTextField14.getText();
//        String price=jTextField15.getText();
//        String totalP=jTextField16.getText();
        try {
            String insert = "INSERT INTO deliver(name,mobile_number,date,lorry_number,category,type_of_kg,quantity,total_kg,price_of_1kg,total_price,route,collector)"
                    + "VALUES('" + name + "','" + mn + "','" + dat + "','" + ln + "','" + cat + "','" + kg + "','" + qua + "','" + totalKg + "','" + price + "','" + totalP + "','" + route + "','" + collector + "')";
            pst = conn.prepareStatement(insert);
            pst.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
//    try {
//            String select="SELECT * FROM deliver ORDER BY id DESC";
//            pst =conn.prepareStatement(select);
//            rs=pst.executeQuery();
//            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, e);
//        }

//     jTextField7.setText("");
//        jTextField8.setText("");
//        jTextField9.setText("");
//        jDateChooser2.setDate(null);
//        jTextField11.setText("");
//        jTextField12.setText("");
//        jTextField13.setText("");
//         jTextField14.setText("");     
//          jTextField15.setText("");     
//          jTextField16.setText("");   
    }
//add data to cutomer table

    public void insertToCustomer(String cat, String kg, String qua, String totalKg, String price, Double totalP) {
        String name = jTextField7.getText();
        String mn = jTextField8.getText();
        String ln = jTextField9.getText();
        SimpleDateFormat g1 = new SimpleDateFormat("yyyy-MM-dd");
        String dat = g1.format(jDateChooser2.getDate());

        try {
            String insert = "INSERT INTO customer(name,mobile_number,date,category,type_of_kg,quantity,total_kg,price_of_1kg,total_price)"
                    + "VALUES('" + name + "','" + mn + "','" + dat + "','" + cat + "','" + kg + "','" + qua + "','" + totalKg + "','" + price + "','" + totalP + "')";
            pst = conn.prepareStatement(insert);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Data added to table");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        try {
            String select = "SELECT * FROM customer ORDER BY id DESC";
            pst = conn.prepareStatement(select);
            rs = pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

    }

    //add data to shop table
    public void insertToShop(String cat, String kg, String qua, String totalKg, String price, Double totalP) {

        String name = jTextField7.getText();
        String mn = jTextField8.getText();
        String ln = jTextField9.getText();
        SimpleDateFormat g1 = new SimpleDateFormat("yyyy-MM-dd");
        String dat = g1.format(jDateChooser2.getDate());

        try {
            String insert = "INSERT INTO shop(date,category,type_of_kg,quantity,total_kg,price_of_1kg,total_price)"
                    + "VALUES('" + dat + "','" + cat + "','" + kg + "','" + qua + "','" + totalKg + "','" + price + "','" + totalP + "')";
            pst = conn.prepareStatement(insert);
            pst.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        try {
            String select = "SELECT * FROM shop ORDER BY id DESC";
            pst = conn.prepareStatement(select);
            rs = pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

//        jTextField7.setText("");
//        jTextField8.setText("");
//        jTextField9.setText("");
//        jDateChooser2.setDate(null);
//        jTextField11.setText("");
//        jTextField12.setText("");
//        jTextField13.setText("");
//         jTextField14.setText("");     
//          jTextField15.setText("");     
//          jTextField16.setText("");     
    }

    //update product propeties with new data
    public void reduceCategoryAmount(String switchName, String ktype, String qty, String luuseqty, String tkg, String pid) {

        if (switchName.equals("AL")) {
            try {
                String sql = "UPDATE product_properties SET rest_of_quantity=rest_of_quantity-'" + luuseqty + "',total_quantity=total_quantity-'" + tkg + "' WHERE p_id='" + pid + "' AND type_of_kg='" + ktype + "'";
                pst = conn.prepareStatement(sql);
                pst.execute();
            } catch (Exception e) {
//             JOptionPane.showMessageDialog(this, e);
            }

        } else if (switchName.equals("GL")) {
            int ty = Integer.parseInt(ktype);
            int tt = Integer.parseInt(tkg);
            int sum = (ty - tt);
            try {
                String sql = "UPDATE product_properties SET quantity=quantity-'" + 1 + "',rest_of_quantity=rest_of_quantity+'" + sum + "',total_quantity=total_quantity-'" + tt + "' WHERE p_id='" + pid + "' AND type_of_kg='" + ktype + "'";
                pst = conn.prepareStatement(sql);
                pst.execute();
            } catch (Exception e) {
//               JOptionPane.showMessageDialog(this, e);
            }
        } else {

            try {
                String sql = "UPDATE product_properties SET quantity=quantity-'" + qty + "',total_quantity=total_quantity-'" + tkg + "' WHERE p_id='" + pid + "' AND type_of_kg='" + ktype + "'";
                pst = conn.prepareStatement(sql);
                pst.execute();
            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, e);
            }
        }

        getKiloPackets();
    }//code end here

    //generate bill 
    private void printBill() {
        try {
            BillPrinter billPrinter = new BillPrinter();
            String jLabel86Text = jLabel86.getText().replace(",", ""); // Remove commas
            jLabel86Text = jLabel86Text.replace(".00", ".0"); // Remove .00 if present (optional)
            billPrinter.printBill(jLabel85.getText(), jLabel86Text, jLabel88.getText(), jLabel90.getText(), cdate, jTextField7.getText(), jLabel20.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void generateBill(String inv, String item, String unit, String qty, String luse, String tkg, String price, Double tprice, String bil_amount, String arius, String tarius, String cname) {

        try {
            String sql = "INSERT INTO bill(inv,item,unit,qty,luse,tkg,price,tprice,bill_amount,arius,total_arius,cname)"
                    + "VALUES('" + inv + "','" + item + "','" + unit + "','" + qty + "','" + luse + "','" + tkg + "','" + price + "','" + tprice + "','" + bil_amount + "','" + arius + "','" + tarius + "','" + cname + "')";
            pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

    }
    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        int r = jTable7.getRowCount();
        boolean bt = jButton46.isEnabled();
        if (r == 0 || bt == true) {
            JOptionPane.showMessageDialog(this, "Bill is empty");
        } else {
            String name = jTextField7.getText();
            String mn = jTextField8.getText();
            String ln = jTextField9.getText();
            SimpleDateFormat g1 = new SimpleDateFormat("yyyy-MM-dd");
            Date dat = jDateChooser2.getDate();
            String cat = jTextField11.getText();
            String kg = jTextField12.getText();
            String qua = jTextField13.getText();
            String totalKg = jTextField14.getText();
            String price = jTextField15.getText();
            String totalP = jTextField16.getText();

            String collector = jTextField45.getText();
            int route = jComboBox2.getSelectedIndex();

            boolean detor2 = jRadioButton1.isSelected();
            boolean deliver = jRadioButton2.isSelected();
            boolean customer = jRadioButton3.isSelected();
            boolean shop = jRadioButton4.isSelected();

            if (detor2 == true) {
                if (dat == null || name.equals("") || mn.equals("") || cat.equals("") || kg.equals("") || qua.equals("") || totalKg.equals("") || price.equals("") || totalP.equals("") || ln.equals("")) {

                    JOptionPane.showMessageDialog(this, "Fil the empty fields");
                } else {
                    //ask the user that, he needs to print the bill
                    int msg = JOptionPane.showConfirmDialog(this, "Do you want to print the bill");
                    if (msg == 0) {
                        DefaultTableModel df2 = (DefaultTableModel) jTable7.getModel();
                        int newRC = jTable7.getRowCount();
                        String nqty = "";
                        String newluse = "";
                        String switchName = "null";

                        for (int i = 0; i < newRC; i++) {

                            //get dealer name,mobile number,lorry number...etc
                            String bil = jLabel85.getText();
                            String ktype = (String) df2.getValueAt(i, 1);
                            String item = (String) df2.getValueAt(i, 0);
                            String qty = (String) df2.getValueAt(i, 2);
                            String luuseqty = (String) df2.getValueAt(i, 3);
                            if (qty.equals("0")) {
                                nqty = "L" + luuseqty;
                                newluse = luuseqty;
                            } else {
                                nqty = qty;
                                newluse = qty;
                            }
                            String lVaue = (String) df2.getValueAt(i, 8);
                            String tkg = (String) df2.getValueAt(i, 4);
                            String pp = (String) df2.getValueAt(i, 5);
                            double tpp = (Double) df2.getValueAt(i, 6);
                            String pid7 = (String) df2.getValueAt(i, 7);
                            String getUser = jLabel20.getText();
                            String move = "OUT";
                            String cred = "Salt";
                            switch (lVaue) {
                                case "AL":
                                    switchName = "AL";
                                    insertLuuse(item, ktype, "0", luuseqty, luuseqty, cdate, move);
                                    insertDataToSales(cred, ktype, nqty, tkg, pp, tpp, item);
                                    insertDataStock(cred, item + "/luuse", ktype, newluse, tkg, pp, tpp, move, cdate, getUser);

                                    break;
                                case "GL":
                                    switchName = "GL";

                                    insertLuuse(item, ktype, "1", luuseqty, luuseqty, cdate, move);

                                    insertDataToSales(cred, ktype, nqty, tkg, pp, tpp, item);
                                    insertDataStock(cred, item + "/luuse", ktype, newluse, tkg, pp, tpp, move, cdate, getUser);
                                    break;
                                default:
                                    switchName = "null";

                                    insertDataStock(cred, item, ktype, qty, tkg, pp, tpp, move, cdate, getUser);
                                    insertDataToSales(cred, ktype, qty, tkg, pp, tpp, item);
                                    break;
                            }

                            insertToDetor(item, ktype, nqty, tkg, pp, tpp);
                            //sen to bill
                            String jLabel86Text = jLabel86.getText().replace(",", "");
                            jLabel86Text = jLabel86Text.replace(".00", ".0");
                            generateBill(jLabel85.getText(), item, ktype, qty, luuseqty, tkg, pp, tpp, jLabel86Text, jLabel88.getText(), jLabel90.getText(), jTextField7.getText());
                        }
                        String cred = "Salt";
                        getSalesData(cred);
                        String jLabel86Text2 = jLabel86.getText().replace(",", "");
                        jLabel86Text2 = jLabel86Text2.replace(".00", ".0");
                        insertDataToSales_Bill_list(jLabel85.getText(), cred, jTextField7.getText(), jLabel86Text2);
                        addOrUpdateariusTable(cred, jTextField7.getText(), cdate, jLabel90.getText());
                        printBill();
                        invID();
                        DefaultTableModel df1 = (DefaultTableModel) jTable7.getModel();
                        int rowCount = jTable7.getRowCount();
                        //df1.setRowCount(0);
                        for (int i = rowCount; i > 0; i--) {
                            df1.removeRow(i - 1);

                        }

                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(this, "please use 'YES' option ");
                    }

                }

            } else if (deliver == true) {
                if (dat == null || name.equals("") || ln.equals("") || collector.equals("") || route == 0) {

                    JOptionPane.showMessageDialog(this, "Fil the empty fields");
                } else {

                    //ask the user that, he needs to print the bill
                    int msg = JOptionPane.showConfirmDialog(this, "Do you want to print the bill");
                    if (msg == 0) {
                        DefaultTableModel df2 = (DefaultTableModel) jTable7.getModel();
                        int newRC = jTable7.getRowCount();
                        String nqty = "";
                        String newluse = "";
                        String switchName = "null";
                        for (int i = 0; i < newRC; i++) {

                            //get dealer name,mobile number,lorry number...etc
                            String ktype = (String) df2.getValueAt(i, 1);
                            String item = (String) df2.getValueAt(i, 0);
                            String qty = (String) df2.getValueAt(i, 2);
                            String luuseqty = (String) df2.getValueAt(i, 3);
                            if (qty.equals("0")) {
                                nqty = "L" + luuseqty;
                                newluse = luuseqty;
                            } else {
                                nqty = qty;
                                newluse = qty;
                            }
                            String lVaue = (String) df2.getValueAt(i, 8);
                            String tkg = (String) df2.getValueAt(i, 4);
                            String pp = (String) df2.getValueAt(i, 5);
                            double tpp = (Double) df2.getValueAt(i, 6);
                            String pid = (String) df2.getValueAt(i, 7);
                            String getUser = jLabel20.getText();
                            String move = "OUT";

                            String cred = "Deliver/Detor";
                            switch (lVaue) {
                                case "AL":
                                    switchName = "AL";
                                    insertLuuse(item, ktype, "0", luuseqty, luuseqty, cdate, move);
                                    insertDataToSales(cred, ktype, nqty, tkg, pp, tpp, item);
                                    insertDataStock(cred, item + "/luuse", ktype, newluse, tkg, pp, tpp, move, cdate, getUser);

                                    break;
                                case "GL":
                                    switchName = "GL";

                                    insertLuuse(item, ktype, "1", luuseqty, luuseqty, cdate, move);
                                    insertDataToSales(cred, ktype, nqty, tkg, pp, tpp, item);
                                    insertDataStock(cred, item + "/luuse", ktype, newluse, tkg, pp, tpp, move, cdate, getUser);
                                    break;
                                default:
                                    switchName = "null";

                                    insertDataStock(cred, item, ktype, qty, tkg, pp, tpp, move, cdate, getUser);
                                    insertDataToSales(cred, ktype, qty, tkg, pp, tpp, item);
                                    break;
                            }

                            insertToDeliver(item, ktype, nqty, tkg, pp, tpp);
                            //update product properties table(addition/subtraction)

                            reduceCategoryAmount(switchName, ktype, qty, luuseqty, tkg, pid);
                            String jLabel86Text = jLabel86.getText().replace(",", "");
                            jLabel86Text = jLabel86Text.replace(".00", ".0");
                            generateBill(jLabel85.getText(), item, ktype, qty, luuseqty, tkg, pp, tpp, jLabel86Text, jLabel88.getText(), jLabel90.getText(), jTextField7.getText());
                        }//loop end here
                        String cred = "Deliver/Detor";
                        getSalesData(cred);
                        String jLabel86Text2 = jLabel86.getText().replace(",", "");
                        jLabel86Text2 = jLabel86Text2.replace(".00", ".0");
                        insertDataToSales_Bill_list(jLabel85.getText(), cred, jTextField7.getText(), jLabel86.getText());
                        addOrUpdateariusTable(cred, jTextField7.getText(), cdate, jLabel90.getText());
                        printBill();
                        invID();
                        DefaultTableModel df1 = (DefaultTableModel) jTable7.getModel();
                        int rowCount = jTable7.getRowCount();
                        //df1.setRowCount(0);
                        for (int i = rowCount; i > 0; i--) {
                            df1.removeRow(i - 1);

                        }
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(this, "please use 'YES' option");
                    }

                }
            } else if (customer == true) {
                if (dat == null || name.equals("")) {

                    JOptionPane.showMessageDialog(this, "Fil the empty fields");
                } else {

                    //ask the user that, he needs to print the bill
                    int msg = JOptionPane.showConfirmDialog(this, "Do you want to print the bill");
                    if (msg == 0) {
                        DefaultTableModel df2 = (DefaultTableModel) jTable7.getModel();
                        int newRC = jTable7.getRowCount();
                        String nqty = "";
                        String newluse = "";
                        String switchName = "null";
                        for (int i = 0; i < newRC; i++) {

                            //get dealer name,mobile number,lorry number...etc
                            String ktype = (String) df2.getValueAt(i, 1);
                            String item = (String) df2.getValueAt(i, 0);
                            String qty = (String) df2.getValueAt(i, 2);
                            String luuseqty = (String) df2.getValueAt(i, 3);
                            if (qty.equals("0")) {
                                nqty = "L" + luuseqty;
                                newluse = luuseqty;
                            } else {
                                nqty = qty;
                                newluse = qty;
                            }
                            String lVaue = (String) df2.getValueAt(i, 8);
                            String tkg = (String) df2.getValueAt(i, 4);
                            String pp = (String) df2.getValueAt(i, 5);
                            double tpp = (Double) df2.getValueAt(i, 6);
                            String pid6 = (String) df2.getValueAt(i, 7);
                            String getUser = jLabel20.getText();
                            String move = "OUT";

                            String cred = "Customer";
                            switch (lVaue) {
                                case "AL":
                                    switchName = "AL";
                                    insertLuuse(item, ktype, "0", luuseqty, luuseqty, cdate, move);
                                    insertDataToSales(cred, ktype, nqty, tkg, pp, tpp, item);
                                    insertDataStock(cred, item + "/luuse", ktype, newluse, tkg, pp, tpp, move, cdate, getUser);
                                    break;
                                case "GL":
                                    switchName = "GL";

                                    insertLuuse(item, ktype, "1", luuseqty, luuseqty, cdate, move);
                                    insertDataToSales(cred, ktype, nqty, tkg, pp, tpp, item);
                                    insertDataStock(cred, item + "/luuse", ktype, newluse, tkg, pp, tpp, move, cdate, getUser);
                                    break;
                                default:
                                    switchName = "null";

                                    insertDataStock(cred, item, ktype, qty, tkg, pp, tpp, move, cdate, getUser);
                                    insertDataToSales(cred, ktype, qty, tkg, pp, tpp, item);
                                    break;
                            }

                            insertToCustomer(item, ktype, nqty, tkg, pp, tpp);
                            //update product properties table(addition/subtraction)
                            reduceCategoryAmount(switchName, ktype, qty, luuseqty, tkg, pid6);
                            String jLabel86Text = jLabel86.getText().replace(",", "");
                            jLabel86Text = jLabel86Text.replace(".00", ".0");
                            generateBill(jLabel85.getText(), item, ktype, qty, luuseqty, tkg, pp, tpp, jLabel86Text, jLabel88.getText(), jLabel90.getText(), jTextField7.getText());
                        }//loop end here
                        String cred = "Customer";
                        getSalesData(cred);
                        String jLabel86Text2 = jLabel86.getText().replace(",", "");
                        jLabel86Text2 = jLabel86Text2.replace(".00", ".0");
                        insertDataToSales_Bill_list(jLabel85.getText(), cred, jTextField7.getText(), jLabel86Text2);
                        addOrUpdateariusTable(cred, jTextField7.getText(), cdate, jLabel90.getText());
                        printBill();
                        invID();
                        DefaultTableModel df1 = (DefaultTableModel) jTable7.getModel();
                        int rowCount = jTable7.getRowCount();
                        //df1.setRowCount(0);
                        for (int i = rowCount; i > 0; i--) {
                            df1.removeRow(i - 1);

                        }
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(this, "please use 'YES' option");
                    }

                }
            } else if (shop == true) {
                if (dat == null) {

                    JOptionPane.showMessageDialog(this, "Fil the empty fields");
                } else {

                    //ask the user that, he needs to print the bill
                    int msg = JOptionPane.showConfirmDialog(this, "Do you want to print the bill");
                    if (msg == 0) {
                        DefaultTableModel df2 = (DefaultTableModel) jTable7.getModel();
                        int newRC = jTable7.getRowCount();
                        String nqty = "";
                        String newluse = "";
                        String switchName = "null";
                        for (int i = 0; i < newRC; i++) {

                            //get dealer name,mobile number,lorry number...etc
                            String ktype = (String) df2.getValueAt(i, 1);
                            String item = (String) df2.getValueAt(i, 0);
                            String qty = (String) df2.getValueAt(i, 2);
                            String luuseqty = (String) df2.getValueAt(i, 3);
                            if (qty.equals("0")) {
                                nqty = "L" + luuseqty;
                                newluse = luuseqty;
                            } else {
                                nqty = qty;
                                newluse = qty;
                            }
                            String lVaue = (String) df2.getValueAt(i, 8);
                            String tkg = (String) df2.getValueAt(i, 4);
                            String pp = (String) df2.getValueAt(i, 5);
                            double tpp = (Double) df2.getValueAt(i, 6);
                            String pid = (String) df2.getValueAt(i, 7);
                            String getUser = jLabel20.getText();
                            String move = "OUT";

                            String cred = "Shop";
                            switch (lVaue) {
                                case "AL":
                                    switchName = "AL";
                                    insertLuuse(item, ktype, "0", luuseqty, luuseqty, cdate, move);
                                    insertDataToSales(cred, ktype, nqty, tkg, pp, tpp, item);
                                    insertDataStock(cred, item + "/luuse", ktype, newluse, tkg, pp, tpp, move, cdate, getUser);
                                    break;
                                case "GL":
                                    switchName = "GL";

                                    insertLuuse(item, ktype, "1", luuseqty, luuseqty, cdate, move);
                                    insertDataToSales(cred, ktype, nqty, tkg, pp, tpp, item);
                                    insertDataStock(cred, item + "/luuse", ktype, newluse, tkg, pp, tpp, move, cdate, getUser);
                                    break;
                                default:
                                    switchName = "null";

                                    insertDataStock(cred, item, ktype, qty, tkg, pp, tpp, move, cdate, getUser);
                                    insertDataToSales(cred, ktype, qty, tkg, pp, tpp, item);
                                    break;
                            }

                            insertToShop(item, ktype, nqty, tkg, pp, tpp);
                            //update product properties table(addition/subtraction)
                            reduceCategoryAmount(switchName, ktype, qty, luuseqty, tkg, pid);
                            String jLabel86Text = jLabel86.getText().replace(",", "");
                            jLabel86Text = jLabel86Text.replace(".00", ".0");
                            generateBill(jLabel85.getText(), item, ktype, qty, luuseqty, tkg, pp, tpp, jLabel86Text, jLabel88.getText(), jLabel90.getText(), jTextField7.getText());
                        }//loop end here
                        String cred = "Shop";
                        getSalesData(cred);
                        String jLabel86Text2 = jLabel86.getText().replace(",", "");
                        jLabel86Text2 = jLabel86Text2.replace(".00", ".0");
                        insertDataToSales_Bill_list(jLabel85.getText(), cred, jTextField7.getText(), jLabel86Text2);
                        addOrUpdateariusTable(cred, jTextField7.getText(), cdate, jLabel90.getText());
                        printBill();
                        invID();
                        DefaultTableModel df1 = (DefaultTableModel) jTable7.getModel();
                        int rowCount = jTable7.getRowCount();
                        //df1.setRowCount(0);
                        for (int i = rowCount; i > 0; i--) {
                            df1.removeRow(i - 1);

                        }
                        clearFields();
                    } else {
                        JOptionPane.showMessageDialog(this, "please use 'YES' option");
                    }

                }
            }

        }
        //getTotalQuantity();
    }//GEN-LAST:event_jButton16ActionPerformed
    public void getDataByClickRadioBtn() {
        boolean detorq = jRadioButton5.isSelected();
        boolean deliver = jRadioButton6.isSelected();
        boolean customer = jRadioButton7.isSelected();
        boolean shop = jRadioButton8.isSelected();

        if (detorq == true) {

            try {
                String sq = "SELECT bill_number,dealer,dealer_name,date FROM set_sales WHERE dealer='" + "Salt" + "' ORDER BY status desc";
                pst = conn.prepareStatement(sq);
                rs = pst.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
            }

        } else if (deliver == true) {

            try {
                String sq = "SELECT bill_number,dealer,dealer_name,date FROM set_sales WHERE dealer='" + "Deliver/Detor" + "' ORDER BY status desc";
                pst = conn.prepareStatement(sq);
                rs = pst.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
            }

        } else if (customer == true) {

            try {
                String sq = "SELECT bill_number,dealer,dealer_name,date FROM set_sales WHERE dealer='" + "Customer" + "' ORDER BY status desc";
                pst = conn.prepareStatement(sq);
                rs = pst.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
            }

        } else if (shop == true) {

            try {
                String sq = "SELECT bill_number,dealer,dealer_name,date FROM set_sales WHERE dealer='" + "Shop" + "' ORDER BY status desc";
                pst = conn.prepareStatement(sq);
                rs = pst.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
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
        int m = JOptionPane.showConfirmDialog(this, "Do you want to log out the system");
        if (m == 0) {
            Login l;
            try {

                String getUser = jLabel20.getText();
                String move = "LOU OUT";
                String descr = "logout user";
                MainPage p = new MainPage();
                p.insertIntoMovement(getUser, move, descr, cdate, ctime);
                l = new Login();
                l.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.dispose();

        }

    }//GEN-LAST:event_jButton18ActionPerformed
//fetch data to return table

    public void getDataFromReturnTable() {
        try {
            String a = "SELECT * FROM returntable ORDER BY id";
            pst = conn.prepareStatement(a);
            rs = pst.executeQuery();
            jTable6.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
        }

    }
    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        String name = jTextField17.getText();
        String mn = jTextField18.getText();
        String kg = jTextField19.getText();
        String qua = jTextField20.getText();
        String des = jTextArea1.getText();
        String cat = jTextField21.getText();

        boolean detorq = jRadioButton5.isSelected();
        boolean deliver = jRadioButton6.isSelected();
        boolean customer = jRadioButton7.isSelected();
        boolean shop = jRadioButton8.isSelected();

        SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd");
        String ys = g.format(jDateChooser3.getDate());
        String cred = "";
        if (detorq == true) {
            cred = "Detor";
        } else if (deliver == true) {
            cred = "Deliver";
        } else if (customer == true) {
            cred = "Customer";
        } else if (shop == true) {
            cred = "Shop";
        }

        String s = "";
        boolean b = jRadioButton9.isSelected();

        Date v = jDateChooser3.getDate();

        if (name.equals("") || mn.equals("") || kg.equals("") || qua.equals("") || v == null) {

            JOptionPane.showMessageDialog(this, "Fill the fields");
        } else {

            try {
                if (b == true) {
                    s = "damage";
                } else {
                    s = "reusable";
                }
                if (s.equals("reusable")) {

                    try {

                        String q = "SELECT id FROM product_sales WHERE category_name='" + jTextField21.getText() + "'";
                        pst = conn.prepareStatement(q);
                        rs = pst.executeQuery();
                        if (rs.next()) {
                            int id = rs.getInt("id");

                            String quaUpdate = "UPDATE product_properties SET quantity=quantity+'" + qua + "',rest_of_quantity=rest_of_quantity+'" + jTextField50.getText() + "',total_quantity=total_quantity+'" + jTextField22.getText() + "' WHERE p_id='" + id + "' AND type_of_kg='" + kg + "'";
                            pst = conn.prepareStatement(quaUpdate);
                            pst.execute();

                            try {

                                String luuseUpdate = "INSERT INTO luuse(cat_name,type_of_kg,quantity,luuse_kg,total_luuse,date,move)"
                                        + "VALUES('" + cat + "','" + kg + "','" + "0" + "','" + jTextField50.getText() + "','" + jTextField50.getText() + "','" + ys + "','" + "IN" + "')";
                                pst1 = conn.prepareStatement(luuseUpdate);
                                pst1.execute();

                            } catch (Exception e) {
                            }
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }
                    String inser = "INSERT INTO returntable(name,mobile,category,kg,quantity,total_kg,status,date,descr)"
                            + "VALUES('" + name + "','" + mn + "','" + cat + "','" + kg + "','" + qua + "','" + jTextField22.getText() + "','" + s + "','" + ys + "','" + des + "')";
                    pst = conn.prepareStatement(inser);
                    pst.execute();

                    String getUser = jLabel20.getText();
                    String move = "IN";
                    String price = "0";
                    String totalP = "0";
                    double tpp = Double.parseDouble(totalP);
                    insertDataStock(cred, cat, kg, qua, jTextField22.getText(), price, tpp, move, cdate, getUser);

                } else {
                    String inser = "INSERT INTO returntable(name,mobile,category,kg,quantity,total_kg,status,date,descr)"
                            + "VALUES('" + name + "','" + mn + "','" + cat + "','" + kg + "','" + qua + "','" + jTextField22.getText() + "','" + s + "','" + ys + "','" + des + "')";
                    pst = conn.prepareStatement(inser);
                    pst.execute();

                }

                JOptionPane.showMessageDialog(this, "added data to return");
                getDataFromReturnTable();

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
        String sq = jTextField6.getText();

        try {
            String s = "SELECT * FROM  product_sales WHERE category_name LIKE '%" + sq + "%'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
        }
        jTable3.getColumnModel().getColumn(0).setMinWidth(0);
        jTable3.getColumnModel().getColumn(0).setMaxWidth(0);
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTextField20KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField20KeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            jTextField22.setText("");
        }
        try {
            int h = Integer.parseInt(jTextField20.getText());

        } catch (NumberFormatException e) {

            jTextField20.setText("");
        }
        int kg = Integer.parseInt(jTextField19.getText());
        int qua = Integer.parseInt(jTextField20.getText());

        int total = 0;
        total = (kg * qua);

        jTextField22.setText(String.valueOf(total));


    }//GEN-LAST:event_jTextField20KeyReleased

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        try {
            int r = jTable5.getSelectedRow();
            String bil = jTable5.getValueAt(r, 0).toString();
            String dealer = jTable5.getValueAt(r, 1).toString();
            String name = jTable5.getValueAt(r, 2).toString();

            String sa = "SELECT * FROM sales_list WHERE bill='" + bil + "' AND dealer='" + dealer + "' AND dealer_name='" + name + "'";
            pst = conn.prepareStatement(sa);
            rs = pst.executeQuery();
            jTable6.setModel(DbUtils.resultSetToTableModel(rs));

            jTable6.getColumnModel().getColumn(0).setMinWidth(0);
            jTable6.getColumnModel().getColumn(0).setMaxWidth(0);

        } catch (Exception e) {
        }


    }//GEN-LAST:event_jTable5MouseClicked
    public void getKiloPackets2() {

        int row = jTable9.getSelectedRow();
        String id = jTable9.getValueAt(row, 0).toString();

        try {
            String s = "SELECT type_of_kg,quantity,rest_of_quantity as luuse,total_quantity as total_kg,price_per_1kg FROM   product_properties WHERE p_id='" + id + "'";
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
            int h = Integer.parseInt(jTextField18.getText());

        } catch (NumberFormatException e) {

            jTextField18.setText("");
        }
    }//GEN-LAST:event_jTextField18KeyReleased

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        try {
            int h = Integer.parseInt(jTextField8.getText());

        } catch (NumberFormatException e) {

            jTextField8.setText("");
        }
    }//GEN-LAST:event_jTextField8KeyReleased

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
        String sq = jTextField10.getText();

        try {
            String s = "SELECT * FROM  product_sales WHERE category_name LIKE '%" + sq + "%'";
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
            String s = "SELECT * FROM stock WHERE date LIKE '%" + date + "%'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable8.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd");
        String date = g.format(jDateChooser5.getDate());

        boolean detorq = jRadioButton5.isSelected();
        boolean deliver = jRadioButton6.isSelected();
        boolean customer = jRadioButton7.isSelected();
        boolean shop = jRadioButton8.isSelected();

        if (detorq == true) {

            try {
                String sq = "SELECT bill_number,dealer,dealer_name,date FROM set_sales WHERE dealer='" + "Salt" + "' AND date='" + date + "' ORDER BY status desc";
                pst = conn.prepareStatement(sq);
                rs = pst.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
            }

        } else if (deliver == true) {

            try {
                String sq = "SELECT bill_number,dealer,dealer_name,date FROM set_sales WHERE dealer='" + "Deliver/Detor" + "' AND date='" + date + "' ORDER BY status desc";
                pst = conn.prepareStatement(sq);
                rs = pst.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
            }

        } else if (customer == true) {

            try {
                String sq = "SELECT bill_number,dealer,dealer_name,date FROM set_sales WHERE dealer='" + "Customer" + "' AND date='" + date + "' ORDER BY status desc";
                pst = conn.prepareStatement(sq);
                rs = pst.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
            }

        } else if (shop == true) {

            try {
                String sq = "SELECT bill_number,dealer,dealer_name,date FROM set_sales WHERE dealer='" + "Shop" + "' AND date='" + date + "' ORDER BY status desc";
                pst = conn.prepareStatement(sq);
                rs = pst.executeQuery();
                jTable5.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
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

        jButton3.setBackground(Color.white);
        jButton1.setBackground(Color.white);
        jButton13.setBackground(Color.white);
        jButton15.setBackground(Color.white);
        jButton22.setBackground(Color.yellow);
        jButton14.setBackground(Color.white);
        jButton23.setBackground(Color.white);
        jButton24.setBackground(Color.white);
        jButton25.setBackground(Color.white);
        jButton32.setBackground(Color.white);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        rightPanel.removeAll();
        rightPanel.add(p7);
        rightPanel.repaint();
        rightPanel.revalidate();
        topbar_menu.removeAll();
        topbar_menu.add(tbp1);
        topbar_menu.repaint();
        topbar_menu.revalidate();

        try {
            String sq = "SELECT name FROM arius_amount";
            pst = conn.prepareStatement(sq);
            rs = pst.executeQuery();
            jTable12.setModel(DbUtils.resultSetToTableModel(rs));
            displayCashInHand();
            calculateAndDisplaySum();
        } catch (Exception e) {
        }

        jButton3.setBackground(Color.white);
        jButton1.setBackground(Color.white);
        jButton13.setBackground(Color.white);
        jButton15.setBackground(Color.white);
        jButton22.setBackground(Color.white);
        jButton14.setBackground(Color.white);
        jButton23.setBackground(Color.yellow);
        jButton24.setBackground(Color.white);
        jButton25.setBackground(Color.white);
        jButton32.setBackground(Color.white);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        rightPanel.removeAll();
        rightPanel.add(p8);
        rightPanel.repaint();
        rightPanel.revalidate();

        jButton3.setBackground(Color.white);
        jButton1.setBackground(Color.white);
        jButton13.setBackground(Color.white);
        jButton15.setBackground(Color.white);
        jButton22.setBackground(Color.white);
        jButton14.setBackground(Color.white);
        jButton23.setBackground(Color.white);
        jButton24.setBackground(Color.yellow);
        jButton25.setBackground(Color.white);
        jButton32.setBackground(Color.white);
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        rightPanel.removeAll();
        rightPanel.add(p9);
        rightPanel.repaint();
        rightPanel.revalidate();

        jButton3.setBackground(Color.white);
        jButton1.setBackground(Color.white);
        jButton13.setBackground(Color.white);
        jButton15.setBackground(Color.white);
        jButton22.setBackground(Color.white);
        jButton14.setBackground(Color.white);
        jButton23.setBackground(Color.white);
        jButton24.setBackground(Color.white);
        jButton25.setBackground(Color.yellow);
        jButton32.setBackground(Color.white);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        //        Cash/Banking Section - cash in hand button
        topbar_menu.removeAll();
        topbar_menu.add(tbp1);
        topbar_menu.repaint();
        topbar_menu.revalidate();

        try {
            String sq = "SELECT name FROM arius_amount";
            pst = conn.prepareStatement(sq);
            rs = pst.executeQuery();
            jTable12.setModel(DbUtils.resultSetToTableModel(rs));
            displayCashInHand();
            calculateAndDisplaySum();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void displayCashInHand() {
        //         Cash/Banking Section
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = dateFormat.format(new Date());
            String sq = "SELECT * FROM cash_in_hand WHERE Date = ?";
            pst = conn.prepareStatement(sq);
            pst.setString(1, currentDate);
            rs = pst.executeQuery();
            jTable13.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
    }

    private void displayChequeInHand() {
        //         Cash/Banking Section
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = dateFormat.format(new Date());
            String sq = "SELECT * FROM cheque_in_hand WHERE Date = ?";
            pst = conn.prepareStatement(sq);
            pst.setString(1, currentDate);
            rs = pst.executeQuery();
            jTable15.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
    }

    private void displayCreditToday() {
        //         Cash/Banking Section
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = dateFormat.format(new Date());
            String sq = "SELECT * FROM credit_today WHERE Date = ?";
            pst = conn.prepareStatement(sq);
            pst.setString(1, currentDate);
            rs = pst.executeQuery();
            jTable17.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
    }

    private void displayAriusAmount(String selectedName) {
        //        Cash/Banking Section
        try {
            String sql = "SELECT arius_amount FROM arius_amount WHERE name=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, selectedName);
            rs = pst.executeQuery();

            if (rs.next()) {
                double ariusAmount = rs.getDouble("arius_amount");
                jTextField51.setText(String.valueOf(selectedName));
                jTextField25.setText(String.valueOf(ariusAmount));
            } else {
                jTextField25.setText("Arius Amount not found");
            }

            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculateAndDisplaySum() {
        //         Cash/Banking Section
        double totalSum = 0;

        for (int row = 0; row < jTable13.getRowCount(); row++) {
            double cashInValue = Double.parseDouble(jTable13.getValueAt(row, 3).toString());
            totalSum += cashInValue;
        }

        jLabel3.setText("රු. " + totalSum);
    }

    private void calculateAndDisplayCreditSum() {
        //         Cash/Banking Section
        double totalSum = 0;

        for (int row = 0; row < jTable17.getRowCount(); row++) {
            double cashInValue = Double.parseDouble(jTable17.getValueAt(row, 3).toString());
            totalSum += cashInValue;
        }

        jLabel103.setText("රු. " + totalSum);
    }

    private void calculateAndDisplayChequeSum() {
        //         Cash/Banking Section
        double totalSum = 0;

        for (int row = 0; row < jTable15.getRowCount(); row++) {
            double cashInValue = Double.parseDouble(jTable15.getValueAt(row, 3).toString());
            totalSum += cashInValue;
        }

        jLabel51.setText("රු. " + totalSum);
    }

    private void displayAriusAmountcheque(String selectedName) {
        //        Cash/Banking Section
        try {
            String sql = "SELECT arius_amount FROM arius_amount WHERE name=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, selectedName);
            rs = pst.executeQuery();

            if (rs.next()) {
                double ariusAmount = rs.getDouble("arius_amount");
                jTextField53.setText(String.valueOf(selectedName));
                jTextField28.setText(String.valueOf(ariusAmount));
            } else {
                jTextField28.setText("Arius Amount not found");
            }

            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayAriusDetaers(String selectedName) {
        //        Cash/Banking Section
        try {
            String sql = "SELECT arius_amount FROM arius_amount WHERE name=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, selectedName);
            rs = pst.executeQuery();

            if (rs.next()) {
                double ariusAmount = rs.getDouble("arius_amount");
//                 jTextField54.setText(String.valueOf(selectedName));
                jTextField30.setText(String.valueOf(ariusAmount));
            } else {
                jTextField25.setText("Arius Amount not found");
            }

            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        topbar_menu.removeAll();
        topbar_menu.add(tbp2);
        topbar_menu.repaint();
        topbar_menu.revalidate();
        try {
            String sq = "SELECT name FROM arius_amount";
            pst = conn.prepareStatement(sq);
            rs = pst.executeQuery();
            jTable14.setModel(DbUtils.resultSetToTableModel(rs));
            displayChequeInHand();
            calculateAndDisplayCreditSum();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        topbar_menu.removeAll();
        topbar_menu.add(tbp3);
        topbar_menu.repaint();
        topbar_menu.revalidate();
        try {
            String sq = "SELECT name FROM arius_amount";
            pst = conn.prepareStatement(sq);
            rs = pst.executeQuery();
            jTable16.setModel(DbUtils.resultSetToTableModel(rs));
            displayCreditToday();
            calculateAndDisplayCreditSum();
        } catch (Exception e) {
        }
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

        try {
            String sql = "SELECT name,mobile,category,kg,total_kg,status,date,descr FROM returntable WHERE status='" + "damage" + "' ORDER BY date desc";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable20.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }

        jButton3.setBackground(Color.white);
        jButton1.setBackground(Color.white);
        jButton13.setBackground(Color.white);
        jButton15.setBackground(Color.white);
        jButton22.setBackground(Color.white);
        jButton14.setBackground(Color.white);
        jButton23.setBackground(Color.white);
        jButton24.setBackground(Color.white);
        jButton25.setBackground(Color.white);
        jButton32.setBackground(Color.yellow);
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        tmenu.removeAll();
        tmenu.add(tp1);
        tmenu.repaint();
        tmenu.revalidate();
        try {
            String sql = "SELECT name,mobile,category,kg,total_kg,status,date,descr FROM returntable WHERE status='" + "damage" + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable20.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
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
        TableColumnModel cm = jTable8.getTableHeader().getColumnModel();
        while (cm.getColumnCount() > 0) {
            cm.removeColumn(cm.getColumn(0));
        }
        cm.addColumn(new TableColumn(0, 100));
        cm.getColumn(0).setHeaderValue("Item_Name");

        cm.addColumn(new TableColumn(1, 100));
        cm.getColumn(1).setHeaderValue("Type of unit");

        cm.addColumn(new TableColumn(2, 100));
        cm.getColumn(2).setHeaderValue("IN");

        cm.addColumn(new TableColumn(3, 100));
        cm.getColumn(3).setHeaderValue("Luuse IN");

        cm.addColumn(new TableColumn(4, 100));
        cm.getColumn(4).setHeaderValue("Total Units");

        cm.addColumn(new TableColumn(5, 100));
        cm.getColumn(5).setHeaderValue("OUT");

        cm.addColumn(new TableColumn(6, 100));
        cm.getColumn(6).setHeaderValue("Luuse OUT");
        cm.addColumn(new TableColumn(7, 100));
        cm.getColumn(7).setHeaderValue("Total Units");

        int row = jTable9.getSelectedRow();
        String item = jTable9.getValueAt(row, 1).toString();

        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = d.format(jDateChooser4.getDate());

        int qIN = 0;
        String iname = "";
        String type = "";
        String qOUT = "";
        String totIN = "";
        String totOUT = "";
        String luseOUT = "";
        int total_l_OUT_pluse_q = 0;
        int total_l_IN_pluse_q = 0;

//         DefaultTableModel df =(DefaultTableModel)jTable8.getModel();
//            df.setRowCount(0);
        DefaultTableModel df = (DefaultTableModel) jTable8.getModel();
        int r = jTable8.getRowCount();

        df.setRowCount(0);
        int t = 0;
        try {
            String sql = "SELECT item_name,type_of_kg,"
                    + "sum(CASE WHEN movement = 'IN' THEN quantity ELSE 0 END)AS qin,"
                    + "sum(CASE WHEN movement = 'IN' THEN total_kg ELSE 0 END)AS tqtyi,"
                    + "sum(CASE WHEN movement = 'OUT' THEN quantity ELSE 0 END)AS qout,"
                    + "sum(CASE WHEN movement = 'OUT' THEN total_kg ELSE 0 END)AS tqty FROM stock WHERE item_name='" + item + "' AND date='" + fdate + "' GROUP BY type_of_kg ";

            String sql4 = "SELECT type_of_kg,sum(CASE WHEN move = 'OUT' THEN total_luuse ELSE 0 END) as luse,sum(CASE WHEN move = 'IN' THEN total_luuse ELSE 0 END) as luseIN FROM luuse WHERE date='" + fdate + "' AND cat_name='" + item + "' GROUP BY type_of_kg";
            pst = conn.prepareStatement(sql);
            pst1 = conn.prepareStatement(sql4);
            rs = pst.executeQuery();
            rs1 = pst1.executeQuery();

            // Create a map to store luse values for each type_of_kg
            Map<String, Integer> luseMap = new HashMap<>();
            Map<String, Integer> luseINMap = new HashMap<>();
            // Process the rs1 result set and populate the luseMap
            while (rs1.next()) {
                String type1 = rs1.getString("type_of_kg");
                int luseValue = rs1.getInt("luse");
                int luseINValue = rs1.getInt("luseIN");
                luseMap.put(type1, luseValue);
                luseINMap.put(type1, luseINValue);
            }

            while (rs.next()) {
                iname = rs.getString("item_name");
                type = rs.getString("type_of_kg");
                qIN = rs.getInt("qin");
                totIN = rs.getString("tqtyi");

                qOUT = rs.getString("qout");
                totOUT = rs.getString("tqty");

                // Get the corresponding luse value from the map
                int luseOUT1 = luseMap.getOrDefault(type, 0);
                int luseIN = luseINMap.getOrDefault(type, 0);
                total_l_IN_pluse_q = Integer.parseInt(totIN) + luseIN;
                total_l_OUT_pluse_q = Integer.parseInt(totOUT) + luseOUT1;

                df.addRow(new Object[]{
                    iname,
                    type,
                    qIN,
                    luseIN,
                    total_l_IN_pluse_q,
                    qOUT,
                    luseOUT1,
                    total_l_OUT_pluse_q

                });

            }

        } catch (Exception e) {
        }


    }//GEN-LAST:event_jButton40ActionPerformed

    private void jTable10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable10MouseClicked
        //add quantitiy (IN or OUT) to the table when user bress the jtable10 row
        TableColumnModel cm = jTable8.getTableHeader().getColumnModel();
        while (cm.getColumnCount() > 0) {
            cm.removeColumn(cm.getColumn(0));
        }
        cm.addColumn(new TableColumn(0, 100));
        cm.getColumn(0).setHeaderValue("Item_Name");

        cm.addColumn(new TableColumn(1, 100));
        cm.getColumn(1).setHeaderValue("Type of unit");

        cm.addColumn(new TableColumn(2, 100));
        cm.getColumn(2).setHeaderValue("IN");

        cm.addColumn(new TableColumn(3, 100));
        cm.getColumn(3).setHeaderValue("Luuse IN");

        cm.addColumn(new TableColumn(4, 100));
        cm.getColumn(4).setHeaderValue("Total Units");

        cm.addColumn(new TableColumn(5, 100));
        cm.getColumn(5).setHeaderValue("OUT");

        cm.addColumn(new TableColumn(6, 100));
        cm.getColumn(6).setHeaderValue("Luuse OUT");
        cm.addColumn(new TableColumn(7, 100));
        cm.getColumn(7).setHeaderValue("Total Units");

        int row = jTable9.getSelectedRow();
        String item = jTable9.getValueAt(row, 1).toString();

        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = d.format(jDateChooser4.getDate());
        int r2 = jTable10.getSelectedRow();

        String ty = jTable10.getValueAt(r2, 0).toString();

        String qIN = "";
        String iname = "";
        String type = "";
        String qOUT = "";
        int totIN = 0;
        int totOUT = 0;
        int luseIN = 0;
        int luseOUT = 0;
        int total_l_pluse_q_IN = 0;
        int total_l_pluse_q_OUT = 0;
        try {
            String sql = "SELECT item_name,type_of_kg,sum(quantity),sum(total_kg) FROM stock WHERE date='" + fdate + "' AND item_name='" + item + "' AND type_of_kg='" + ty + "' AND movement='" + "IN" + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                iname = rs.getString("item_name");
                type = rs.getString("type_of_kg");
                qIN = rs.getString("sum(quantity)");
                totIN = rs.getInt("sum(total_kg)");
            }

        } catch (Exception e) {
        }
        try {
            String sql = "SELECT sum(quantity),sum(total_kg) FROM stock WHERE date='" + fdate + "' AND item_name='" + item + "' AND type_of_kg='" + ty + "' AND movement='" + "OUT" + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                qOUT = rs.getString("sum(quantity)");
                totOUT = rs.getInt("sum(total_kg)");

            }

        } catch (Exception e) {
        }
        try {
            String sqlq = "SELECT sum(CASE WHEN move = 'IN' THEN total_luuse ELSE 0 END) as luseIN,sum(CASE WHEN move = 'OUT' THEN total_luuse ELSE 0 END) as luseOUT FROM luuse WHERE date='" + fdate + "' AND cat_name='" + item + "' AND type_of_kg='" + ty + "' ";
            pst = conn.prepareStatement(sqlq);
            rs = pst.executeQuery();
            if (rs.next()) {
                luseIN = rs.getInt("luseIN");
                luseOUT = rs.getInt("luseOUT");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        total_l_pluse_q_IN = (totIN + luseIN);
        total_l_pluse_q_OUT = (totOUT + luseOUT);
        DefaultTableModel df = (DefaultTableModel) jTable8.getModel();
        df.setRowCount(0);
        df.addRow(new Object[]{
            iname,
            type,
            qIN,
            luseIN,
            total_l_pluse_q_IN,
            qOUT,
            luseOUT,
            total_l_pluse_q_OUT

        });

        try {
            String sw = "SELECT sum(total_kg) FROM stock WHERE date='" + fdate + "' AND item_name='" + item + "' AND movement='" + "IN" + "'";
            pst = conn.prepareStatement(sw);
            rs = pst.executeQuery();

            if (rs.next()) {
                String t = rs.getString("sum(total_kg)");
                jLabel76.setText(String.valueOf(t));
            }
        } catch (Exception e) {
        }
        try {
            String sw = "SELECT sum(total_kg) FROM stock WHERE date='" + fdate + "' AND item_name='" + item + "' AND movement='" + "OUT" + "'";
            pst = conn.prepareStatement(sw);
            rs = pst.executeQuery();

            if (rs.next()) {
                String t1 = rs.getString("sum(total_kg)");
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
        boolean cv = jRadioButton2.isSelected();
        jTextField9.setEditable(true);
        jTextField9.setEnabled(true);
        jTextField11.setText("");
        int row = jTable11.getSelectedRow();
        String name2 = "";
        String name1 = jTable11.getValueAt(row, 2).toString();

        if (name1.equals("Salt")) {

            jRadioButton1.setSelected(true);
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
        } else if (name1.equals("Deliver/Detor")) {
            jRadioButton1.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
            jRadioButton2.setSelected(true);
        } else if (name1.equals("Customer")) {
            jRadioButton1.setSelected(false);
            jRadioButton2.setSelected(false);
            jRadioButton4.setSelected(false);
            jRadioButton3.setSelected(true);
        } else if (name1.equals("Shop")) {
            jRadioButton1.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton2.setSelected(false);
            jRadioButton4.setSelected(true);
        }
        jTable3.getColumnModel().getColumn(0).setMinWidth(0);
        jTable3.getColumnModel().getColumn(0).setMaxWidth(0);
        try {

            name2 = jTable11.getValueAt(row, 3).toString();
            Date fDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) jTable11.getValueAt(row, 4).toString());
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            String fda = d.format(fDate);
            String s = "SELECT * FROM sales_list WHERE date ='" + fda + "' AND dealer_name='" + name2 + "' AND dealer='" + name1 + "'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();

            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
            //getArius amount

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

        try {
            name2 = jTable11.getValueAt(row, 3).toString();
            Date fDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) jTable11.getValueAt(row, 4).toString());
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            String fda = d.format(fDate);
            String s = "SELECT * FROM sales_list WHERE date ='" + fda + "' AND dealer_name='" + name2 + "' AND dealer='" + name1 + "'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();

            if (rs.next()) {

                String n = rs.getString("dealer_name");
                String mn = rs.getString("mobile");
                String ln = rs.getString("lorry_number");

                String route = rs.getString("route");
                String collector = rs.getString("collector");
                jTextField7.setText(n);
                jTextField8.setText(mn);
                jTextField9.setText(ln);
                jTextField45.setText(collector);
                jComboBox2.setSelectedItem(route);

            }
        } catch (Exception e) {
        }
        jTable7.getColumnModel().getColumn(0).setMinWidth(0);
        jTable7.getColumnModel().getColumn(0).setMaxWidth(0);

        getProductdata();
        try {

            String s = "SELECT type_of_kg,quantity,rest_of_quantity as luuse,total_quantity as total_kg FROM   product_properties WHERE p_id='" + "ww" + "'";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable4.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        String newAriusamount = "0";
//get arius amount        
        try {
            String sql = "SELECT sum(arius_amount) FROM arius_amount WHERE name='" + name2 + "' AND dealer='" + name1 + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String amount = rs.getString("sum(arius_amount)");
                newAriusamount = amount;

                if (newAriusamount == null) {

                    jTextField46.setText("0.0");
                    jLabel88.setText("0.0");
                } else {
                    jTextField46.setText(String.valueOf(amount));
                    jLabel88.setText(String.valueOf(amount));
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

        jButton43.setEnabled(false);
        jButton46.setEnabled(true);
    }//GEN-LAST:event_jTable11MouseClicked

    private void jTextField15KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyReleased
        int tkg = Integer.parseInt(jTextField14.getText());
        int p = Integer.parseInt(jTextField15.getText());

        int top = (tkg * p);
        jTextField16.setText(String.valueOf(top));

        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            jTextField16.setText("");

        }


    }//GEN-LAST:event_jTextField15KeyReleased
//add data to bill view

    public void addDataToBillView() {

        boolean bt = jButton46.isEnabled();
        String cname = jTextField7.getText();
        String ll = jTextField9.getText();
        String k = jTextField12.getText();
        String q = jTextField13.getText();
        String tkk = jTextField14.getText();
        String c = jTextField11.getText();
        String p = jTextField15.getText();
        String p2 = jTextField16.getText();

        if (k.equals("") || q.equals("") || tkk.equals("") || c.equals("") || p.equals("") || p2.equals("")) {
            JOptionPane.showMessageDialog(this, "please fill the text fields");
        } else if (bt == true) {
            JOptionPane.showMessageDialog(this, "please select the Bill view");
        } else {

            //if check add/get luuse button is true
            boolean addUse = jCheckBox1.isSelected();
            boolean getUse = jCheckBox2.isSelected();
            String quantity = "0";
            String luuse = "0";
            String luuseName = "null";
            if (addUse == true || getUse == true) {
                if (addUse == true) {
                    luuseName = "AL";
                } else {
                    luuseName = "GL";
                }
                luuse = jTextField13.getText();
                quantity = "0";
            } else {

                quantity = jTextField13.getText();
                luuse = "0";
            }

            int rr = jTable4.getSelectedRow();

            String type = jTable4.getValueAt(rr, 0).toString();
            String pid3 = jTable4.getValueAt(rr, 5).toString();

            try {
                String sql = "SELECT sum(quantity),sum(rest_of_quantity) FROM  product_properties WHERE type_of_kg='" + type + "' AND p_id='" + pid3 + "'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs.next()) {

                    int quat = rs.getInt("sum(quantity)");
                    int res = rs.getInt("sum(rest_of_quantity)");
                    int wqty = Integer.parseInt(quantity);
                    int wluuse = Integer.parseInt(luuse);
                    if (quat < wqty) {
                        JOptionPane.showMessageDialog(this, "cannot performe the task, quantitiy is higher than the stock");
                    } else if (res == 0) {

                        //add data to bill table(bill preview)  
                        int r1 = jTable3.getSelectedRow();
                        int idr = jTable4.getSelectedRow();
                        String item1 = jTextField11.getText();
                        String kyType = jTextField12.getText();
                        String totalQuantity = jTextField14.getText();
                        String price = jTextField15.getText();
                        double totalP = Double.parseDouble(jTextField16.getText());
                        String pid1 = jTable4.getValueAt(idr, 5).toString();

                        DefaultTableModel df = (DefaultTableModel) jTable7.getModel();

                        df.addRow(new Object[]{
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
                        double totalq = 0;
                        double sub = 0;
                        int col = jTable7.getRowCount();

                        for (int i = 0; i < col; i++) {

                            sub = (Double) df.getValueAt(i, 6);
                            totalq = totalq + sub;
                        }
                        
                        String formattedTotalq = decimalFormat.format(totalq);
                        jLabel86.setText(formattedTotalq);

                        double ariusTotalwithBillamout = 0.0;

                        double b = Double.parseDouble(jLabel88.getText());
                        ariusTotalwithBillamout = totalq + b;
                        jLabel90.setText(String.valueOf(ariusTotalwithBillamout));

                    } else if (wluuse > res) {
                        JOptionPane.showMessageDialog(this, "cannot performe the task, luuse quantitiy is higher than the stock");
                    } else {

                        //add data to bill table(bill preview)  
                        int r1 = jTable3.getSelectedRow();
                        int idr = jTable4.getSelectedRow();
                        String item1 = jTextField11.getText();
                        String kyType = jTextField12.getText();
                        String totalQuantity = jTextField14.getText();
                        String price = jTextField15.getText();
                        double totalP = Double.parseDouble(jTextField16.getText());
                        String pid1 = jTable4.getValueAt(idr, 5).toString();

                        DefaultTableModel df = (DefaultTableModel) jTable7.getModel();

                        df.addRow(new Object[]{
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
                        double totalq = 0;
                        double sub = 0;
                        int col = jTable7.getRowCount();

                        for (int i = 0; i < col; i++) {

                            sub = (Double) df.getValueAt(i, 6);
                            totalq = totalq + sub;
                        }

                        String formattedTotalq = decimalFormat.format(totalq);
                        jLabel86.setText(formattedTotalq);

                        Double ariusTotalwithBillamout = 0.0;

                        Double b = Double.parseDouble(jLabel88.getText());
                        ariusTotalwithBillamout = totalq + b;
                        jLabel90.setText(String.valueOf(ariusTotalwithBillamout));
                    }

                } else {

                    JOptionPane.showMessageDialog(this, "Error");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }

        }

    }
    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed

        boolean bt = jButton46.isEnabled();
        String cname = jTextField7.getText();
        String ll = jTextField9.getText();
        String k = jTextField12.getText();
        String q = jTextField13.getText();
        String tkk = jTextField14.getText();
        String c = jTextField11.getText();
        String p = jTextField15.getText();
        String p2 = jTextField16.getText();

        if (k.equals("") || q.equals("") || tkk.equals("") || c.equals("") || p.equals("") || p2.equals("")) {
            JOptionPane.showMessageDialog(this, "please fill the text fields");
        } else if (bt == true) {
            JOptionPane.showMessageDialog(this, "please select the Bill view");
        } else {

            //if check add/get luuse button is true
            boolean addUse = jCheckBox1.isSelected();
            boolean getUse = jCheckBox2.isSelected();
            String quantity = "0";
            String luuse = "0";
            String luuseName = "null";
            if (addUse == true || getUse == true) {
                if (addUse == true) {
                    luuseName = "AL";
                } else {
                    luuseName = "GL";
                }
                luuse = jTextField13.getText();
                quantity = "0";
            } else {

                quantity = jTextField13.getText();
                luuse = "0";
            }

            int rr = jTable4.getSelectedRow();

            String type = jTable4.getValueAt(rr, 0).toString();
            String pid3 = jTable4.getValueAt(rr, 5).toString();

            try {
                String sql = "SELECT sum(quantity),sum(rest_of_quantity) FROM  product_properties WHERE type_of_kg='" + type + "' AND p_id='" + pid3 + "'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs.next()) {

                    int quat = rs.getInt("sum(quantity)");
                    int res = rs.getInt("sum(rest_of_quantity)");
                    int wqty = Integer.parseInt(quantity);
                    int wluuse = Integer.parseInt(luuse);
                    if (quat < wqty) {
                        JOptionPane.showMessageDialog(this, "cannot performe the task, quantitiy is higher than the stock");
                    } else if (res == 0) {

                        //add data to bill table(bill preview)  
                        int r1 = jTable3.getSelectedRow();
                        int idr = jTable4.getSelectedRow();
                        String item1 = jTextField11.getText();
                        String kyType = jTextField12.getText();
                        String totalQuantity = jTextField14.getText();
                        String price = jTextField15.getText();
                        double totalP = Double.parseDouble(jTextField16.getText());
                        String pid1 = jTable4.getValueAt(idr, 5).toString();

                        DefaultTableModel df = (DefaultTableModel) jTable7.getModel();

                        df.addRow(new Object[]{
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
                        double totalq = 0;
                        double sub = 0;
                        int col = jTable7.getRowCount();

                        for (int i = 0; i < col; i++) {

                            sub = (Double) df.getValueAt(i, 6);
                            totalq = totalq + sub;
                        }

                        String formattedTotalq = decimalFormat.format(totalq);
                        jLabel86.setText(formattedTotalq);

                        double ariusTotalwithBillamout = 0.0;

                        double b = Double.parseDouble(jLabel88.getText());
                        ariusTotalwithBillamout = totalq + b;
                        jLabel90.setText(String.valueOf(ariusTotalwithBillamout));

                        try {

                            String s = "SELECT type_of_kg,quantity,rest_of_quantity as luuse,total_quantity as total_kg FROM   product_properties WHERE p_id='" + "ww" + "'";
                            pst = conn.prepareStatement(s);
                            rs = pst.executeQuery();
                            jTable4.setModel(DbUtils.resultSetToTableModel(rs));

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, e);
                        }

                        jTextField11.setText("");
                        jTextField15.setText("");
                        jTextField16.setText("");
                        jTextField12.setText("");
                        jTextField13.setText("");
                        jTextField14.setText("");
                        jCheckBox1.setSelected(false);
                        jCheckBox2.setSelected(false);
                    } else if (wluuse > res) {
                        JOptionPane.showMessageDialog(this, "cannot performe the task, luuse quantitiy is higher than the stock");
                    } else {

                        //add data to bill table(bill preview)  
                        int r1 = jTable3.getSelectedRow();
                        int idr = jTable4.getSelectedRow();
                        String item1 = jTextField11.getText();
                        String kyType = jTextField12.getText();
                        String totalQuantity = jTextField14.getText();
                        String price = jTextField15.getText();
                        double totalP = Double.parseDouble(jTextField16.getText());
                        String pid1 = jTable4.getValueAt(idr, 5).toString();

                        DefaultTableModel df = (DefaultTableModel) jTable7.getModel();

                        df.addRow(new Object[]{
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
                        double totalq = 0;
                        double sub = 0;
                        int col = jTable7.getRowCount();

                        for (int i = 0; i < col; i++) {

                            sub = (Double) df.getValueAt(i, 6);
                            totalq = totalq + sub;
                        }

                        String formattedTotalq = decimalFormat.format(totalq);
                        jLabel86.setText(formattedTotalq);

                        Double ariusTotalwithBillamout = 0.0;

                        Double b = Double.parseDouble(jLabel88.getText());
                        ariusTotalwithBillamout = totalq + b;
                        jLabel90.setText(String.valueOf(ariusTotalwithBillamout));
                    }

                    try {

                        String s = "SELECT type_of_kg,quantity,rest_of_quantity as luuse,total_quantity as total_kg FROM   product_properties WHERE p_id='" + "ww" + "'";
                        pst = conn.prepareStatement(s);
                        rs = pst.executeQuery();
                        jTable4.setModel(DbUtils.resultSetToTableModel(rs));

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e);
                    }

                    jTextField11.setText("");
                    jTextField15.setText("");
                    jTextField16.setText("");
                    jTextField12.setText("");
                    jTextField13.setText("");
                    jTextField14.setText("");
                    jCheckBox1.setSelected(false);
                    jCheckBox2.setSelected(false);
                } else {

                    JOptionPane.showMessageDialog(this, "Error");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }

        }
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed

        int row = jTable7.getSelectedRow();
        int rowCount = jTable7.getRowCount();

        DefaultTableModel df = (DefaultTableModel) jTable7.getModel();

        if (row >= 0) {
            df.removeRow(row);
            double total = 0.00;
            double totalq = 0;
            double ariusTotalwithBillamout = 0.00;
            double b = Double.parseDouble(jLabel88.getText());
            double sub = 0.00;
            int col = jTable7.getRowCount();

            for (int i = 0; i < col; i++) {

                sub = (Double) df.getValueAt(i, 6);
                total = total + sub;

                ariusTotalwithBillamout = total + b;
            }
            DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
            String formattedtotal = decimalFormat.format(total);
            jLabel86.setText(formattedtotal);
            jLabel90.setText(String.valueOf(ariusTotalwithBillamout));
        }


    }//GEN-LAST:event_jButton41ActionPerformed

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased


    }//GEN-LAST:event_jTextField7KeyReleased

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        jButton43.setEnabled(false);
        jButton41.setEnabled(false);
        jButton46.setEnabled(true);
        jButton43.setEnabled(false);
        jButton47.setVisible(true);
        jButton39.setVisible(false);
        jTextField11.setText("Salt");
        boolean shop = jRadioButton4.isSelected();
        try {
            String select = "SELECT * FROM detor ORDER BY id DESC";
            pst = conn.prepareStatement(select);
            rs = pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
        try {
            String sql = "SELECT DISTINCT name as Names_of_customers FROM detor";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable24.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        boolean shop = jRadioButton4.isSelected();
        if (shop == true) {
            try {
                String select = "SELECT * FROM detor ORDER BY id DESC";
                pst = conn.prepareStatement(select);
                rs = pst.executeQuery();
                jTable7.setModel(DbUtils.resultSetToTableModel(rs));

                //change the table header names 
                DefaultTableModel df1 = (DefaultTableModel) jTable7.getModel();
                int rowCount = jTable7.getRowCount();
                //df1.setRowCount(0);
                for (int i = rowCount; i > 0; i--) {
                    df1.removeRow(i - 1);

                }

                TableColumnModel cm = jTable7.getTableHeader().getColumnModel();
                while (cm.getColumnCount() > 0) {
                    cm.removeColumn(cm.getColumn(0));
                }
                cm.addColumn(new TableColumn(0, 100));
                cm.getColumn(0).setHeaderValue("Item_Name");

                cm.addColumn(new TableColumn(1, 100));
                cm.getColumn(1).setHeaderValue("Type of unit");

                cm.addColumn(new TableColumn(2, 100));
                cm.getColumn(2).setHeaderValue("Quantity");

                cm.addColumn(new TableColumn(3, 100));
                cm.getColumn(3).setHeaderValue("qty(luuse)");

                cm.addColumn(new TableColumn(4, 100));
                cm.getColumn(4).setHeaderValue("Total Kg");

                cm.addColumn(new TableColumn(5, 100));
                cm.getColumn(5).setHeaderValue("Price");

                cm.addColumn(new TableColumn(6, 100));
                cm.getColumn(6).setHeaderValue("Total Price");

                cm.addColumn(new TableColumn(7, 100));
                cm.getColumn(7).setHeaderValue("pid");

                cm.addColumn(new TableColumn(8, 100));
                cm.getColumn(8).setHeaderValue("luuse type");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
            jButton43.setEnabled(true);
            jButton46.setEnabled(false);
            jButton41.setEnabled(true);
        } else {
            jButton43.setEnabled(true);
            jButton46.setEnabled(false);
            jButton41.setEnabled(true);
            //change the table header names 
            DefaultTableModel df1 = (DefaultTableModel) jTable7.getModel();
            int rowCount = jTable7.getRowCount();
            //df1.setRowCount(0);
            for (int i = rowCount; i > 0; i--) {
                df1.removeRow(i - 1);

            }

            TableColumnModel cm = jTable7.getTableHeader().getColumnModel();
            while (cm.getColumnCount() > 0) {
                cm.removeColumn(cm.getColumn(0));
            }
            cm.addColumn(new TableColumn(0, 100));
            cm.getColumn(0).setHeaderValue("Item_Name");

            cm.addColumn(new TableColumn(1, 100));
            cm.getColumn(1).setHeaderValue("Type of unit");

            cm.addColumn(new TableColumn(2, 100));
            cm.getColumn(2).setHeaderValue("Quantity");

            cm.addColumn(new TableColumn(3, 100));
            cm.getColumn(3).setHeaderValue("qty(luuse)");

            cm.addColumn(new TableColumn(4, 100));
            cm.getColumn(4).setHeaderValue("Total Kg");

            cm.addColumn(new TableColumn(5, 100));
            cm.getColumn(5).setHeaderValue("Price");

            cm.addColumn(new TableColumn(6, 100));
            cm.getColumn(6).setHeaderValue("Total Price");

            cm.addColumn(new TableColumn(7, 100));
            cm.getColumn(7).setHeaderValue("pid");

            cm.addColumn(new TableColumn(8, 100));
            cm.getColumn(8).setHeaderValue("luuse type");

        }
        jTable7.getColumnModel().getColumn(7).setMinWidth(0);
        jTable7.getColumnModel().getColumn(7).setMaxWidth(0);
        jTable7.getColumnModel().getColumn(8).setMinWidth(0);
        jTable7.getColumnModel().getColumn(8).setMaxWidth(0);
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jTable24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable24MouseClicked
        int r = jTable24.getSelectedRow();
        boolean detorq = jRadioButton1.isSelected();
        boolean deliver = jRadioButton2.isSelected();
        boolean customer = jRadioButton3.isSelected();
        boolean shop = jRadioButton4.isSelected();
        String n = jTable24.getValueAt(r, 0).toString();
        jTextField7.setText(n);
        String newAriusamount = "0";
        String dealer = "";
        if (detorq == true) {
            dealer = "Salt";
        } else if (deliver == true) {
            dealer = "Deliver/Detor";

        } else if (customer == true) {
            dealer = "Customer";

        } else if (shop == true) {
            dealer = "Shop";

        }

        //get arius amount        
        try {
            String sql = "SELECT sum(arius_amount) FROM arius_amount WHERE name='" + n + "' AND dealer='" + dealer + "'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                String amount = rs.getString("sum(arius_amount)");
                newAriusamount = amount;
                if (newAriusamount == null) {

                    jTextField46.setText("0.0");
                    jLabel88.setText("0.0");
                } else {
                    jTextField46.setText(String.valueOf(amount));
                    jLabel88.setText(String.valueOf(amount));

                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_jTable24MouseClicked

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        String creditorname = txtCreditorName.getText();
        SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = g.format(jDateChooser1.getDate());
        try {
            setCreditorAriusAmount(creditorname, fdate, jTextField49.getText());
            JOptionPane.showMessageDialog(this, "send data to arius table");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        try {
            String se = "SELECT * FROM creditor_bill_list WHERE bill_num='" + jTextField33.getText() + "'";
            pst = conn.prepareStatement(se);
            rs = pst.executeQuery();

            if (rs.next()) {

            } else {
                String sw = "INSERT INTO creditor_bill_list(bill_num,creditor_name,date,bill_amount)"
                        + "VALUES('" + jTextField33.getText() + "','" + txtCreditorName.getText() + "','" + fdate + "','" + jTextField48.getText() + "')";
                pst1 = conn.prepareStatement(sw);
                pst1.execute();

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

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
        jTextField33.setText("");
        jTextField48.setText("");
        jTextField47.setText("");
        jTextField49.setText("");
        jkg.removeAllItems();
        jr1.setSelected(true);
        txtQuantity.setEnabled(false);
        txtQuantity.setEditable(false);
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = d.format(jDateChooser6.getDate());

        try {
            String s = "SELECT * FROM  set_status WHERE date LIKE '%" + fdate + "%' ORDER BY date desc";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jTextField50KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField50KeyReleased
        int luuse = Integer.parseInt(jTextField50.getText());

        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            int tkg = Integer.parseInt(jTextField22.getText());

            int tr = tkg - luuse;
            jTextField22.setText("");
        } else {

            int tkg = Integer.parseInt(jTextField22.getText());

            int t = luuse + tkg;
            jTextField22.setText(String.valueOf(t));

        }


    }//GEN-LAST:event_jTextField50KeyReleased

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        int r = jTable6.getSelectedRow();

        String name = jTable6.getValueAt(r, 3).toString();
        String cat = jTable6.getValueAt(r, 7).toString();
        String mn = jTable6.getValueAt(r, 4).toString();
        String kg = jTable6.getValueAt(r, 8).toString();
        String qua = jTable6.getValueAt(r, 9).toString();
        String tkg = jTable6.getValueAt(r, 10).toString();
        if (qua.equals(tkg)) {
            jTextField50.setText(qua);
            jTextField20.setText("0");
        } else {
            jTextField20.setText(qua);
            jTextField50.setText("0");
        }

        jTextField17.setText(name);
        jTextField21.setText(cat);
        jTextField18.setText(mn);
        jTextField19.setText(kg);

        jTextField22.setText(tkg);
    }//GEN-LAST:event_jTable6MouseClicked

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            jCheckBox2.setEnabled(false);
        } else {
            jCheckBox2.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        jRadioButton1.setSelected(true);
        jButton47.setVisible(true);
        jButton39.setVisible(false);
        jTextField11.setText("Salt");
        try {
            String select = "SELECT * FROM detor ORDER BY id DESC";
            pst = conn.prepareStatement(select);
            rs = pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        try {
            String sql = "SELECT DISTINCT name as Names_of_customers FROM detor";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable24.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        jTextField17.setText("");
        jTextField21.setText("");
        jTextField18.setText("");
        jTextField19.setText("");
        jTextField11.setText("");
        jTextField7.setText("");
        jTextField22.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField46.setText("");
        jTextField15.setText("");
        jTextField16.setText("");
        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField14.setText("");
        jButton46.setEnabled(true);
        jButton43.setEnabled(false);
        jComboBox2.setSelectedIndex(0);
        jTextField45.setText("");

        jLabel86.setText("0.0");
        jLabel88.setText("0.0");
        jLabel90.setText("0.0");
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        boolean bt = jButton46.isEnabled();
        String cname = jTextField7.getText();
        String ll = jTextField9.getText();
        String k = jTextField12.getText();
        String q = jTextField13.getText();
        String tkk = jTextField14.getText();
        String c = jTextField11.getText();
        String p = jTextField15.getText();
        String p2 = jTextField16.getText();

        if (cname.equals("") || ll.equals("") || k.equals("") || q.equals("") || tkk.equals("") || c.equals("") || p.equals("") || p2.equals("")) {
            JOptionPane.showMessageDialog(this, "please fill the text fields");
        } else if (bt == true) {
            JOptionPane.showMessageDialog(this, "please select the Bill view");
        } else {
            //if check add/get luuse button is true
            boolean addUse = jCheckBox1.isSelected();
            boolean getUse = jCheckBox2.isSelected();
            String quantity = "0";
            String luuse = "0";
            String luuseName = "null";
            if (addUse == true || getUse == true) {
                if (addUse == true) {
                    luuseName = "AL";
                } else {
                    luuseName = "GL";
                }
                luuse = jTextField13.getText();
                quantity = "0";
            } else {

                quantity = jTextField13.getText();
                luuse = "0";
            }

            //add data to bill table(bill preview)  
            int r1 = jTable3.getSelectedRow();
            int idr = jTable4.getSelectedRow();
            String item1 = "Salt";
            String kyType = jTextField12.getText();
            String totalQuantity = jTextField14.getText();
            String price = jTextField15.getText();
            double totalP = Double.parseDouble(jTextField16.getText());
            String pid1 = "wwe";

            DefaultTableModel df = (DefaultTableModel) jTable7.getModel();

            df.addRow(new Object[]{
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
            double totalq = 0;
            double sub = 0;
            int col = jTable7.getRowCount();

            for (int i = 0; i < col; i++) {

                sub = (Double) df.getValueAt(i, 6);
                totalq = totalq + sub;
            }
            DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
            String formattedTotalq = decimalFormat.format(totalq);
            jLabel86.setText(formattedTotalq);

            double ariusTotalwithBillamout = 0.0;

            double b = Double.parseDouble(jLabel88.getText());
            ariusTotalwithBillamout = totalq + b;
            jLabel90.setText(String.valueOf(ariusTotalwithBillamout));
        }
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = d.format(jDateChooser7.getDate());

        try {
            String sql = "SELECT name,mobile,category,kg,total_kg,status,date,descr FROM returntable WHERE status='" + "damage" + "' AND date='" + fdate + "' ORDER BY date desc";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable20.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if (jCheckBox2.isSelected()) {
            jCheckBox1.setEnabled(false);
        } else {
            jCheckBox1.setEnabled(true);
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jTextField51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField51ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField51ActionPerformed

    private void jTable12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable12MouseClicked
        //         Cash/Banking Section
        int selectedRow = jTable12.getSelectedRow();
        if (selectedRow != -1) {
            String selectedName = jTable12.getValueAt(selectedRow, 0).toString();
            displayAriusAmount(selectedName);
        }
    }//GEN-LAST:event_jTable12MouseClicked

    private void jTextField23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField23MouseClicked

    }//GEN-LAST:event_jTextField23MouseClicked

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        //         Cash/Banking Section
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = d.format(jDateChooser8.getDate());

        try {
            String s = "SELECT * FROM  cash_in_hand WHERE date LIKE '%" + fdate + "%' ORDER BY date desc";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable13.setModel(DbUtils.resultSetToTableModel(rs));
            calculateAndDisplaySum();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jDateChooser8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser8MouseClicked

    }//GEN-LAST:event_jDateChooser8MouseClicked

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        //        Cash/Banking Section
        try {
            String amount23 = jTextField23.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = dateFormat.format(new Date());
            String amount51 = jTextField51.getText();
            String amount24 = jTextField24.getText();

            String insertQuery = "INSERT INTO cash_in_hand (name, Date, cash_in, Arius_amont) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
            preparedStatement.setString(1, amount51);
            preparedStatement.setString(2, currentDate);
            preparedStatement.setString(3, amount23);
            preparedStatement.setString(4, amount24);

            String updateQuery = "UPDATE arius_amount SET arius_amount = ? WHERE name = ?";
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setString(1, amount24);
            updateStatement.setString(2, amount51);
            updateStatement.executeUpdate();
            preparedStatement.executeUpdate();

            // Clear the text fields after insertion
            jTextField23.setText("");
            jTextField51.setText("");
            jTextField24.setText("");
            jTextField25.setText("");
            // Optionally, show a success message to the user
            JOptionPane.showMessageDialog(this, "Cash Added successfully");
            displayCashInHand();
            calculateAndDisplaySum();

        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
            // Handle exceptions here
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jTextField23KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField23KeyReleased
        //         Cash/Banking Section
        try {
            double value23 = Double.parseDouble(jTextField23.getText());
            double value25 = Double.parseDouble(jTextField25.getText());
            double sum = value25 - value23;
            jTextField24.setText(String.valueOf(sum));
        } catch (NumberFormatException e) {
            jTextField24.setText("Invalid input");
        }
    }//GEN-LAST:event_jTextField23KeyReleased

    private void jTable14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable14MouseClicked
        //         Cash/Banking Section
        int selectedRow = jTable14.getSelectedRow();
        if (selectedRow != -1) {
            String selectedName = jTable14.getValueAt(selectedRow, 0).toString();
            displayAriusAmountcheque(selectedName);
        }
    }//GEN-LAST:event_jTable14MouseClicked

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        //         Cash/Banking Section
        try {
            String chname = jTextField53.getText();
            String chenumber = jTextField26.getText();
            String cheamount = jTextField27.getText();
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            String chedate = d.format(jDateChooser9.getDate());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = dateFormat.format(new Date());
            String cheavailableamount = jTextField28.getText();
            String newavailble = jTextField29.getText();

            String insertQuery = "INSERT INTO cheque_in_hand (name, cheque_no,cheque_amount, cheque_date,Date,old_amount,new_amount) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
            preparedStatement.setString(1, chname);
            preparedStatement.setString(2, chenumber);
            preparedStatement.setString(3, cheamount);
            preparedStatement.setString(4, chedate);
            preparedStatement.setString(5, currentDate);
            preparedStatement.setString(6, cheavailableamount);
            preparedStatement.setString(7, newavailble);

            try {
                String sq = "UPDATE arius_amount SET arius_amount ='" + newavailble + "' WHERE name ='" + chname + "'";
                pst = conn.prepareStatement(sq);
                pst.execute();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }

            // Clear the text fields after insertion
            jTextField53.setText("");
            jTextField26.setText("");
            jTextField27.setText("");
            jTextField28.setText("");
            jTextField28.setText("");
            // Optionally, show a success message to the user
            JOptionPane.showMessageDialog(this, "Cheque Added successfully");
            displayChequeInHand();
            calculateAndDisplayChequeSum();

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            // Handle exceptions here
            JOptionPane.showMessageDialog(this, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jTextField27KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField27KeyReleased
        //         Cash/Banking Section
        try {
            double value23 = Double.parseDouble(jTextField28.getText());
            double value25 = Double.parseDouble(jTextField27.getText());
            double sum = value23 - value25;
            jTextField29.setText(String.valueOf(sum));
        } catch (NumberFormatException e) {
            jTextField29.setText("Invalid input");
        }
    }//GEN-LAST:event_jTextField27KeyReleased

    private void jDateChooser10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser10MouseClicked

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        //         Cash/Banking Section - cheque
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = d.format(jDateChooser10.getDate());

        try {
            String s = "SELECT * FROM  cheque_in_hand WHERE date LIKE '%" + fdate + "%' ORDER BY date desc";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable15.setModel(DbUtils.resultSetToTableModel(rs));
            calculateAndDisplayChequeSum();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jTable16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable16MouseClicked
        //         Cash/Banking Section
        int selectedRow = jTable16.getSelectedRow();
        if (selectedRow != -1) {
            String selectedName = jTable16.getValueAt(selectedRow, 0).toString();
            displayAriusAmountdebit(selectedName);
        }
    }//GEN-LAST:event_jTable16MouseClicked

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = d.format(jDateChooser11.getDate());
        try {

            String sq = "SELECT * FROM set_sales WHERE date='" + fdate + "' ORDER BY date desc";
            pst = conn.prepareStatement(sq);
            rs = pst.executeQuery();
            jTable11.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
        }

        jTable11.getColumnModel().getColumn(0).setMinWidth(0);
        jTable11.getColumnModel().getColumn(0).setMaxWidth(0);
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        // TODO add your handling code here
        try {
            String selectedOption = (String) jComboBox3.getSelectedItem();
            String cusname = jTextField52.getText();
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            String fdate = d.format(jDateChooser12.getDate());
            String arreaseamount = jTextField55.getText();

            String insertQuery = "INSERT INTO arius_amount (dealer, name, date, arius_amount) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
            preparedStatement.setString(1, selectedOption);
            preparedStatement.setString(2, cusname);
            preparedStatement.setString(3, fdate);
            preparedStatement.setString(4, arreaseamount);

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Successfully Add Customer");
            jTextField55.setText("");
            jTextField52.setText("");

            try {
                String sq = "SELECT DISTINCT name FROM arius_amount";
                pst = conn.prepareStatement(sq);
                rs = pst.executeQuery();
                jTable12.setModel(DbUtils.resultSetToTableModel(rs));
                displayCashInHand();
                calculateAndDisplaySum();
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton55ActionPerformed

    private void jTextField52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField52ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField52ActionPerformed

    private void jDateChooser13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser13MouseClicked

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        //         Cash/Banking Section - cheque
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = d.format(jDateChooser13.getDate());

        try {
            String s = "SELECT * FROM  credit_today WHERE Date LIKE '%" + fdate + "%' ORDER BY Date desc";
            pst = conn.prepareStatement(s);
            rs = pst.executeQuery();
            jTable17.setModel(DbUtils.resultSetToTableModel(rs));
            calculateAndDisplayCreditSum();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        jTextField51.setText("");
        jTextField25.setText("");
        jTextField23.setText("");
        jTextField24.setText("");
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        jTextField53.setText("");
        jTextField26.setText("");
        jTextField27.setText("");
        jDateChooser9.setDate(null);
        jTextField28.setText("");
        jTextField29.setText("");
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        // TODO add your handling code here:
        jTextField17.setText("");
        jTextField18.setText("");
        jTextField19.setText("");
        jTextField20.setText("");
        jTextArea1.setText("");
        jTextField21.setText("");
        jTextArea1.setText("Write Here");
        jTextField22.setText("");
        jLabel116.setText("0.0");
    }//GEN-LAST:event_jButton56ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton35ActionPerformed
    private void displayAriusAmountdebit(String selectedName) {
        //        Cash/Banking Section
        try {
            String sql = "SELECT arius_amount FROM arius_amount WHERE name=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, selectedName);
            rs = pst.executeQuery();

            if (rs.next()) {
                double ariusAmount = rs.getDouble("arius_amount");
                jTextField54.setText(String.valueOf(selectedName));
                jTextField30.setText(String.valueOf(ariusAmount));
            } else {
                jTextField30.setText("Arius Amount not found");
            }

            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSubcat(String c) {

        try {
            jkg.removeAllItems();
            String s = "SELECT * FROM catqua WHERE cat='" + c + "'";
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

    public void getProductToSales() {

//    try {
//            String s = "SELECT * FROM catqua WHERE cat='"+c+"'";
//            pst = conn.prepareStatement(s);
//            rs = pst.executeQuery();
//    } catch (Exception e) {
//    }
    }

    public void getCurrentDateTime() {

        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                java.text.SimpleDateFormat SimpleDateFormat = new java.text.SimpleDateFormat("HH:mm:ss");
                String displayTime = SimpleDateFormat.format(new Date());
                String displayDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(new Date());

                jLabel38.setText(displayDate);
                //d=displayDate;ctime
                ctime = displayTime;
                cdate = displayDate;
            }
        }).start();

    }
//set movement

    public void insertIntoMovement(String user, String movement, String descr, String date, String time) {

        try {
            String insert = "INSERT INTO movement_table(user,movement,description,date,time)"
                    + "VALUES('" + user + "','" + movement + "','" + descr + "','" + date + "','" + time + "')";
            pst = conn.prepareStatement(insert);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

    }
//insert data into stock

    public void insertDataStock(String dname, String Iname, String kg, String qua, String totalKg, String price, double toP, String move, String date, String user) {

        try {
            String insert = "INSERT INTO stock(dealer_name,item_name,type_of_kg,quantity,total_kg,price_per_1kg,total_price,movement,date,user)VALUES('" + dname + "','" + Iname + "','" + kg + "','" + qua + "','" + totalKg + "','" + price + "','" + toP + "','" + move + "','" + date + "','" + user + "')";
            pst = conn.prepareStatement(insert);
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
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser10;
    private com.toedter.calendar.JDateChooser jDateChooser11;
    private com.toedter.calendar.JDateChooser jDateChooser12;
    private com.toedter.calendar.JDateChooser jDateChooser13;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private com.toedter.calendar.JDateChooser jDateChooser6;
    private com.toedter.calendar.JDateChooser jDateChooser7;
    private com.toedter.calendar.JDateChooser jDateChooser8;
    private com.toedter.calendar.JDateChooser jDateChooser9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
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
    private javax.swing.JScrollPane jScrollPane25;
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
    private javax.swing.JTable jTable24;
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
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JLabel jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
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
