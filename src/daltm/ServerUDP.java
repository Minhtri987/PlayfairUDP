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
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author minht
 */
public class ServerUDP extends javax.swing.JFrame {
    public ServerUDP() {
        initComponents();
        
    }
    private int length = 0;
    private String[][] table;
    private List<Integer> xinChaoPositions;
    String PortTemp="";
    DatagramSocket ds = null;
    int Port = 8080;
    byte[] buffer = new byte[255];
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ServerUDP");
        jLabel1.setAutoscrolls(true);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setText("Nhập Port(Cổng):");

        jLabel3.setText("Console Terminal");

        txtArea.setColumns(500);
        txtArea.setRows(30);
        jScrollPane1.setViewportView(txtArea);

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtInput))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 570, Short.MAX_VALUE)
                        .addComponent(btnSubmit)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(318, 318, 318))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addComponent(btnSubmit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        xinChaoPositions = new ArrayList<>();
        try{
            //---------------------------------Getting Port---------------------------------//
            PortTemp=txtInput.getText();
            if(PortTemp.equals("")){Port=8080;txtArea.append("\nThiết lập cổng mặc định thành công");}else Port= parseInt(PortTemp);
            txtArea.append("\nServer khởi chạy tại Port: " + Port);
            txtArea.append("\nĐang chờ Client....");
            //Mở tường lửa
            OpenFirewallPort(Port);
            //---------------------------------Getting Key and Message from Client---------------------------------//
            ds = new DatagramSocket(Port);
            
            btnSubmit.setEnabled(false);
            btnSubmit.setVisible(false);
            JOptionPane.showMessageDialog(this, "Server đang chờ kết nối đến cổng: " + Port, "Waiting", JOptionPane.INFORMATION_MESSAGE);
            
            DatagramPacket spacePositionsPacket = new DatagramPacket(buffer, buffer.length);
            ds.receive(spacePositionsPacket);
            String spacePositionsString = new String(spacePositionsPacket.getData(), 0, spacePositionsPacket.getLength());
            
            DatagramPacket keyPacket = new DatagramPacket(buffer, buffer.length);
            ds.receive(keyPacket);
            String key = new String(keyPacket.getData(),0,keyPacket.getLength());
            
            DatagramPacket messagePacket= new DatagramPacket(buffer, buffer.length);
            ds.receive(messagePacket);
            String encryptedMessage = new String(messagePacket.getData(), 0, messagePacket.getLength());
            //Debugging for receive
            txtArea.append("\nKey: " + key +" Message: "+encryptedMessage+" Space Positions: "+spacePositionsString);
            List<Integer> spacePositions = parseSpacePositions(spacePositionsString);
            //---------------------------------Decryption and Processing---------------------------------//
            // Decrypt the message using Playfair Cipher
            key = parseString(key);
            table = this.cipherTable(key);
            String decryptedMessage = decode(encryptedMessage);
            decryptedMessage= returnDecoded(decryptedMessage);
            decryptedMessage=removeDuplicateX(decryptedMessage);
            decryptedMessage= addSpaces(decryptedMessage,spacePositions);
            this.keyTable(table);
            this.printResults(decryptedMessage);
            //---------------------------------Finding Position Of String---------------------------------//
            // Find positions of "Xin chào" in the decrypted message
            findXinChaoPositions(decryptedMessage);
            String position =xinChaoPositions.toString();
            // Send positions to the client
            String response = String.valueOf(position);
            byte[] data = response.getBytes();
            DatagramPacket outsend = new DatagramPacket(data, data.length, keyPacket.getAddress(), keyPacket.getPort());
            ds.send(outsend);
            txtArea.append("Gửi các vị trí đến Client: " + response);
            //Đóng tường lửa
            CloseFirewallPort(Port);
            btnSubmit.setEnabled(true);
            btnSubmit.setVisible(true);
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ds != null) {
                ds.close();
            }
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if(txtArea.getText().isEmpty()){
        getCurrentIP();
        
        txtArea.append("\nVui lòng nhập Port(Cổng) của IP(Mặc định: 8080 khi để trống): ");}
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
            java.util.logging.Logger.getLogger(ServerUDP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerUDP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerUDP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerUDP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerUDP().setVisible(true);
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
    //Get current Server IP
    public void getCurrentIP(){
        try{
            InetAddress addr = InetAddress.getLocalHost();
            byte[] ipAddr = addr.getAddress();
            String ipAddrStr = "";
            for(int j = 0; j < ipAddr.length; j++){
                if(j > 0 ){
                    ipAddrStr += ".";
                }
                ipAddrStr += ipAddr[j]&0xFF;
            }
            if(txtArea.getText().isEmpty())
            txtArea.append("IP nội bộ(LAN IP) của Server hiện tại là: "+ipAddrStr);
            else
            {
                txtArea.append("\n\nIP nội bộ(LAN IP) của Server hiện tại là: "+ipAddrStr);
            }
        }catch(UnknownHostException e){
        }
    }
    
    private void findXinChaoPositions(String input) {
        int index = 0;
        while (index >= 0) {
            index = input.indexOf("XIN CHAO", index);
            if (index >= 0) {
                xinChaoPositions.add(index + 1);
                index += "XIN CHAO".length();
            }
        }
    }
//    private String addSpaces(String decryptedMessage, List<Integer> spacePositions) {
//        StringBuilder result = new StringBuilder(decryptedMessage);
//
//        for (int i = 0; i < spacePositions.size(); i++) {
//            int spacePosition = spacePositions.get(i) + i; // Adjust position based on previous insertions
//
//            // Check if spacePosition is within bounds before inserting space
//            if (spacePosition >= 0 && spacePosition < result.length()) {
//                result.insert(spacePosition, ' ');
//            } else {
//                // Handle the case where spacePosition is out of bounds
//                // For example, you can append the space to the end of the result
//                result.append(' ');
//            }
//        }
//
//        return result.toString();
//    }
    private String addSpaces(String decryptedMessage, List<Integer> spacePositions) {
        StringBuilder result = new StringBuilder(decryptedMessage);

        for (int i = 0; i < spacePositions.size(); i++) {
            int spacePosition = spacePositions.get(i); // Adjust position based on previous insertions

            // Check if spacePosition is within bounds before inserting space
            if (spacePosition >= 0 && spacePosition < result.length()) {
                result.insert(spacePosition, ' ');
            } else {
                // Handle the case where spacePosition is out of bounds
                // For example, you can append the space to the end of the result
                result.append(' ');
            }
//
//            // Increment all remaining space positions
//            for (int j = i + 1; j < spacePositions.size(); j++) {
//                spacePositions.set(j, spacePositions.get(j) + 1);
//            }
        }

        return result.toString();
    }
    private List<Integer> parseSpacePositions(String spacePositionsString) {
        List<Integer> spacePositions = new ArrayList<>();
        spacePositionsString = spacePositionsString.replaceAll("\\[|\\]", "");
        String[] positionsArray = spacePositionsString.split(", ");

        for (String position : positionsArray) {
            spacePositions.add(Integer.parseInt(position));
        }

        return spacePositions;
    }
    private String returnDecoded(String input){
        if (input != null && input.length() > 0 && input.charAt(input.length() - 1) == 'x') {
            return input.substring(0, input.length() - 1);
        }
        return input;
    }
    private String removeDuplicateX(String decryptedMessage) {
        char[] resultChars = decryptedMessage.toCharArray();

        for (int i = 1; i < resultChars.length - 1; i++) {
            if (resultChars[i] == 'X' && resultChars[i - 1] == resultChars[i + 1]) {
                resultChars[i] = ' ';  // Replace 'X' with a space
            }
        }

        return new String(resultChars).replaceAll(" ", "");
    } 
    private String parseString(String parse) {
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

    private String decode(String out) {
        String decoded = "";
        for (int i = 0; i < out.length() / 2; i++) {
            char a = out.charAt(2 * i);
            char b = out.charAt(2 * i + 1);
            int r1 = (int) getPoint(a).getX();
            int r2 = (int) getPoint(b).getX();
            int c1 = (int) getPoint(a).getY();
            int c2 = (int) getPoint(b).getY();

            if (r1 == r2) {
                c1 = (c1 + 4) % 5;
                c2 = (c2 + 4) % 5;
            } else if (c1 == c2) {
                r1 = (r1 + 4) % 5;
                r2 = (r2 + 4) % 5;
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }

            decoded = decoded + table[r1][c1] + table[r2][c2];
        }
        return decoded;
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
    private void printResults(String dec) {
        txtArea.append("Tin nhắn đã giải mã(Bản rõ): ");
        txtArea.append(dec+"\n");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtInput;
    // End of variables declaration//GEN-END:variables
}
