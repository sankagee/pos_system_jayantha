/*
committ
 */
package INF;

import CODE.DBConnect;
import java.awt.print.*;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class BillPrinter {

    Connection conn = null;
    PreparedStatement pst = null;
    PreparedStatement pst1 = null;
    ResultSet rs = null;
    ResultSet rs1 = null;

    public BillPrinter() throws SQLException {

        conn = DBConnect.connect();
    }

    public void printBill(String inv, String bilamount, String arius, String totalArius, String da, String cname, String user) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                // Set font and other styles
                int fontSize = 10;
                Font font = new Font("Iskoola Pota", Font.PLAIN, fontSize);
                g2d.setFont(font);
                g2d.setColor(Color.BLACK);

                // Simulated data
                String storeName = "Jayantha Store";
                String storeAddress = "Jayantha stores,Tissa Road,Ambalantota.";
                String storePhone = "Tel: 077 006 7800";
                String invoiceNumber = "Invoice Number : ";
                String invoiceDate = "Date:";
                String cusname = "Customer name :";

                String itemNameHeader = "Item Name";
                String unitHeader = "Unit";
                String quantityHeader = "Quantity";
                String luuse = "Luuse";
                String totalqty = "Total Quantity";
                String price = "Price per";
                String tp = "Total Price";

                String billamounttitlee = "Bill Amount.............:";
                String ariustitleeeeeee = "Arius Amount..........:";
                String tsriusssssssssss = "Total Arius Amount.:";
                String user1 = "User :";
                String thankYou = "Thank you come again";
                String companyname = "Designed By Cybercom Technologies";
                String outtel = "TEL : 071 154 8797/076 810 7345/071 656 2135";

                String dashedLine = "\u2013"; // Em dash character used to create a dashed line
                int lineWidth = (int) (pageFormat.getImageableWidth() - 2 * 40); // Subtracting margins

                int y = 40; // Starting Y coordinate for printing

                g2d.drawString(user1 + user, 40, y);
                g2d.drawString(storeName, 260, y);
                y += 10;
                g2d.drawString(storeAddress, 215, y);
                y += 10;
                g2d.drawString(storePhone, 260, y);
                y += 8;

                g2d.drawString(dashedLine.repeat(lineWidth), 40, y);
                y += 8;
                g2d.drawString(invoiceNumber + inv, 40, y);
                g2d.drawString(cusname + cname, 220, y);
                g2d.drawString("Date: " + da, 500, y);
                y += 8;
                g2d.drawString(dashedLine.repeat(lineWidth), 40, y);
                y += 8;

                g2d.drawString(itemNameHeader, 40, y);
                g2d.drawString(unitHeader, 200, y);
                g2d.drawString(quantityHeader, 240, y);
                g2d.drawString(luuse, 300, y);
                g2d.drawString(totalqty, 340, y);
                g2d.drawString(price, 420, y);
                g2d.drawString(tp, 480, y);
                y += 8;
                g2d.drawString(dashedLine.repeat(lineWidth), 40, y);
                y += 8;

                try {
                    String sql = "SELECT * FROM bill WHERE inv='" + inv + "'";
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();

                    while (rs.next()) {
                        String inv = rs.getString("inv");
                        String it = rs.getString("item");
                        String unit = rs.getString("unit");
                        String qty = rs.getString("qty");
                        String lus = rs.getString("luse");
                        String tk = rs.getString("tkg");
                        String p = rs.getString("price");
                        String tpp = rs.getString("tprice");

                        g2d.drawString(it, 40, y);
//                                        y += 10;
                        g2d.drawString(unit, 200, y);
                        g2d.drawString(qty, 240, y);
                        g2d.drawString(lus, 300, y);
                        g2d.drawString(tk, 340, y);
                        g2d.drawString("රු." + p, 420, y);
                        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
                        double tppDouble = Double.parseDouble(tpp);
                        String formattedtpp = decimalFormat.format(tppDouble);
                        g2d.drawString("රු." + formattedtpp, 480, y);

                        y += 10;

                    }
                } catch (Exception e) {
                }
                DecimalFormat decimalFormat = new DecimalFormat("#,###.00");

                g2d.drawString(dashedLine.repeat(lineWidth), 40, y);
                y += 10;
                double bilamountDouble = Double.parseDouble(bilamount);
                String formattedbilamount = decimalFormat.format(bilamountDouble);
                g2d.drawString(billamounttitlee + " රු." + formattedbilamount, 400, y);
                y += 10;
                double ariusDouble = Double.parseDouble(arius);
                String formattedArius = decimalFormat.format(ariusDouble);
                g2d.drawString(ariustitleeeeeee + " රු." + formattedArius, 400, y);
                y += 10;

                double totalAriusDouble = Double.parseDouble(totalArius);
                String formattedTotalArius = decimalFormat.format(totalAriusDouble);
                g2d.drawString(tsriusssssssssss + " රු." + formattedTotalArius, 400, y);
                y += 10;

                g2d.drawString(dashedLine.repeat(lineWidth), 40, y);
                y += 14;

                g2d.drawString(thankYou, 250, y);
                y += 14;
                g2d.drawString(companyname, 220, y);
                y += 14;
                g2d.drawString(outtel, 200, y);

//                g2d.drawString("Total: " + (quantity * price), 20, y);
                return Printable.PAGE_EXISTS;
            }
        });

        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

}
