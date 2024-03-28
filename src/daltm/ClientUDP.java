/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daltm;

import java.awt.Point;
import java.io.*;
import static java.lang.Integer.parseInt;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author minht
 */
public class ClientUDP extends javax.swing.JFrame {
    public ClientUDP() {
        initComponents();
        
    }
    private int length = 0;
    private String[][] table;
    String PortTemp="";
    DatagramSocket ds = null;
    int Port = 8080;
    byte[] buffer = new byte[255];
    String[] temp=new String[3];
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtInput = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        btnSubmit = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtInput1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtInput2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtInput3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(736, 476));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ClientUDP");
        jLabel1.setToolTipText("");
        jLabel1.setAutoscrolls(true);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setText("Nhập IP Server:");

        jLabel3.setText("Console Terminal");

        txtArea.setColumns(500);
        txtArea.setRows(25);
        jScrollPane1.setViewportView(txtArea);

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        jLabel4.setText("Nhập Port(Cổng):");

        jLabel5.setText("Nhập Khoá (Key):");

        jLabel6.setText("Nhập tin nhắn:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtInput)
                            .addComponent(txtInput1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtInput3)
                            .addComponent(txtInput2)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSubmit)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(322, 322, 322)
                .addComponent(jLabel1)
                .addContainerGap(323, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtInput1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtInput2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtInput3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(btnSubmit))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        try{
            //---------------------------------Getting Port---------------------------------//
            String ip = txtInput.getText();
            if(ip.equals("")){ip = "127.0.0.1";txtArea.append("\nThiết lập IP mặc định thành công");}
            PortTemp=txtInput1.getText();
            if(PortTemp.equals("")){Port=8080;txtArea.append("\nThiết lập cổng mặc định thành công");}else Port= parseInt(PortTemp);
            //Mở tường lửa
            OpenFirewallPort(Port);
            //---------------------------------Get Key and Message from Input---------------------------------//
            ds = new DatagramSocket();
            InetAddress server = InetAddress.getByName(ip);
            // Input and send key to server
            String orgKey = removeAccent(txtInput2.getText().toUpperCase());
            String key =parseString(orgKey);
            if (key.equals(""))
            {
                orgKey ="PLAYFAIRKEY";
                key =parseString(orgKey);
            }
            table = this.cipherTable(key);
            String message =txtInput3.getText();
            if (message.equals("")){
                message="Xin chào đây là tin nhắn được gửi từ Client test xin chào Playfair";}
            List<Integer> spacePositions = saveSpacePositions(message);
            message=removeAccent(message);
            String spacePositionsString = spacePositions.toString();
            byte[] spacePositionsData = spacePositionsString.getBytes("UTF-8");
            DatagramPacket spacePositionsPacket = new DatagramPacket(spacePositionsData, spacePositionsData.length, server, Port);
            ds.send(spacePositionsPacket);
            message=parseString(message);
            //---------------------------------Processing With Server---------------------------------//
            //Send key to server
            byte[] keyData = orgKey.getBytes("UTF-8");
            DatagramPacket keyPacket = new DatagramPacket(keyData, keyData.length, server, Port);
            ds.send(keyPacket);
            // Encrypting message before send to server
            String output = cipher(message);
            this.keyTable(table);
            String encryptedMessage=output;
            this.printResults(output);
            // Send encrypted message to server
            byte[] encryptedData = encryptedMessage.getBytes("UTF-8");
            DatagramPacket encryptedPacket = new DatagramPacket(encryptedData, encryptedData.length, server, Port);
            ds.send(encryptedPacket);

            // Receive positions from server
            DatagramPacket positionsPacket = new DatagramPacket(buffer, buffer.length);
            ds.receive(positionsPacket);
            String positions = new String(positionsPacket.getData(), 0, positionsPacket.getLength());
            txtArea.append("\nCác vị trí của 'Xin chào' xuất hiện trong chuỗi mã hoá là: " + positions+"\n");
            //Đóng tường lửa
            CloseFirewallPort(Port);
        } catch (IOException e) {
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }//GEN-LAST:event_btnSubmitActionPerformed
    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(txtArea.getText().isEmpty()){txtArea.append("Vui lòng nhập địa chỉ IP(Mặc định: 127.0.0.1 khi để trống): ");
        txtArea.append("\n\nVui lòng nhập Port(Cổng) của IP(Mặc định: 8080 khi để trống): ");
        txtArea.append("\n\nNhập khoá cho PlayFair: ");
        txtArea.append("\n\nNhập tin nhắn cần gửi: ");}
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(ClientUDP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientUDP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientUDP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientUDP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientUDP().setVisible(true);
            }
        });
    }
    //---------------------------------Coding Section---------------------------------//
    //---------------------------------Decryption Processing and Others Function---------------------------------//
    //Open and Close Port in the Firewall
    private void OpenFirewallPort(int Port){
        try{
            String command="netsh advfirewall firewall add rule name=\"OpenPort" + Port +"\" dir=in action=allow protocol=UDP localport=" + Port;
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            Process process = processBuilder.start();
            process.waitFor();
            txtArea.append("\nCổng " + Port + " đã được mở qua tường lửa.");
        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void CloseFirewallPort(int port) {
        try {
            String command = "netsh advfirewall firewall delete rule name=\"OpenPort" + port + "\"";
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            Process process = processBuilder.start();
            process.waitFor();
            txtArea.append("\nCổng " + port + " đã được đóng lại tại tường lửa.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void printResults(String encipher) {
        txtArea.append("\nTin nhắn mã hoá(Bản mã): ");
        txtArea.append(encipher+"\n");
        txtArea.append("\n");
    }
    public static String removeAccent(String s) { 
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replace('đ','d').replace('Đ','D');
    }
    
    private String parseString(String parse) {
        parse = removeAccent(parse);
        parse = parse.toUpperCase();
        parse = parse.replaceAll("[^A-Z]", "");
        parse = parse.replace("J", "I");
        return parse;
    }

    private String[][] cipherTable(String key) {
        String[][] playfairTable = new String[5][5];
        String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                playfairTable[i][j] = "";

        for (int k = 0; k < keyString.length(); k++) {
            boolean repeat = false;
            boolean used = false;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (playfairTable[i][j].equals("" + keyString.charAt(k))) {
                        repeat = true;
                    } else if (playfairTable[i][j].equals("") && !repeat && !used) {
                        playfairTable[i][j] = "" + keyString.charAt(k);
                        used = true;
                    }
                }
            }
        }
        return playfairTable;
    }

    private String cipher(String in) {
        length = (int) in.length() / 2 + in.length() % 2;

        for (int i = 0; i < (length -1); i++) {
            if (in.charAt(2 * i) == in.charAt(2 * i + 1)) {
                in = new StringBuffer(in).insert(2 * i + 1, 'X').toString();
                length = (int) in.length() / 2 + in.length() % 2;
            }
        }

        String[] digraph = new String[length];
        for (int j = 0; j < length; j++) {
            if (j == (length - 1) && in.length() / 2 == (length - 1))
                in = in + "X";
            digraph[j] = in.charAt(2 * j) + "" + in.charAt(2 * j + 1);
        }

        String out = "";
        String[] encDigraphs = new String[length];
        encDigraphs = encodeDigraph(digraph);
        for (int k = 0; k < length; k++)
            out = out + encDigraphs[k];
        return out;
    }
    private String[] encodeDigraph(String di[]) {
        String[] encipher = new String[length];
        for (int i = 0; i < length; i++) {
            char a = di[i].charAt(0);
            char b = di[i].charAt(1);
            int r1 = (int) getPoint(a).getX();
            int r2 = (int) getPoint(b).getX();
            int c1 = (int) getPoint(a).getY();
            int c2 = (int) getPoint(b).getY();

            if (r1 == r2) {
                c1 = (c1 + 1) % 5;
                c2 = (c2 + 1) % 5;
            } else if (c1 == c2) {
                r1 = (r1 + 1) % 5;
                r2 = (r2 + 1) % 5;
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }

            encipher[i] = table[r1][c1] + "" + table[r2][c2];
        }
        return encipher;
    }
    private Point getPoint(char c) {
        Point pt = new Point(0, 0);
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (c == table[i][j].charAt(0))
                    pt = new Point(i, j);
        return pt;
    }

    private void keyTable(String[][] printTable) {
        txtArea.append("\nPlayfair Cipher Key Matrix: \n");
        txtArea.append("\n");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                txtArea.append(printTable[i][j] + " ");
            }
            txtArea.append("\n");
        }
        txtArea.append("\n");
    }
    private List<Integer> saveSpacePositions(String message) {
        List<Integer> spacePositions = new ArrayList<>();
        for (int i = 0; i < message.length(); i++) {
            if (Character.isWhitespace(message.charAt(i))) {
                spacePositions.add(i);
            }
        }
        return spacePositions;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtInput;
    private javax.swing.JTextField txtInput1;
    private javax.swing.JTextField txtInput2;
    private javax.swing.JTextField txtInput3;
    // End of variables declaration//GEN-END:variables
}
