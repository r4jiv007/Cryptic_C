/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Round3.java
 *
 * Created on Feb 13, 2012, 5:48:26 PM
 */
package com.my.cryptC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

/**
 *
 * @author rajiv
 */
public class Round3 extends javax.swing.JFrame {
    
    private static final long serialVersionUID = 1L;
    protected int delay = 1000;
    protected int max_min= 0;
    protected int max_sec= 0;
     protected String name=UsrDetail.usrname.getName();
     protected String id=UsrDetail.usrid.getText();
    static int flag =0;
    protected Timer countdown;
    protected Process procC,procR;
    protected boolean msgsent;
    protected Runtime rt;
    protected File tosave;
    protected String line;
    protected FileWriter fstream;
    protected BufferedWriter out;
    protected BufferedReader br;
    protected boolean result=false;
    protected String pcno=UsrDetail.pcNo.getText();
    private int carretPos;
    protected FileReader fr;
    Socket clientSocket;
       DataOutputStream outToServer;
      //  DataOutputStream outToServer;
       BufferedReader inFromServer;
     String sentence;
  String modifiedSentence;

     ActionListener timer = new ActionListener() {
     int min= max_min;
     int sec = max_sec;
      
    public void actionPerformed(ActionEvent evt) {
        
        
        if(sec<10){
         time.setText(min+":0"+sec);   
        }
         if( min <10){
         time.setText("0"+min+":"+sec);   
        }
          if(sec<10 && min <10){
         time.setText("0"+min+":0"+sec);   
        }
        if(sec>=10 && min>=10){
         time.setText(min+":"+sec);   
        } 
         if(min==90 && sec==0){
                try {
                    time.setText("00:00");
                   // ((Timer)evt.getSource()).stop();
                    countdown.stop();
                    compl.setEnabled(false);
                    run.setEnabled(false);
                    jQuitButton.setEnabled(false);
                     System.gc();
                    compile();
                     if(result){
                        outToServer.writeBytes("--------------------------------------------------------\nLaqshya Id:- "+id+" on System_no:-"+pcno+" has completed program in "+time.getText()+" mins\n--------------------------------------------------------\n\n");
                        msgsent=true;
                     }
                       else if(!result){
                           outToServer.writeBytes("--------------------------------------------------------\nLaqshya Id:- "+id+" on System_no:-"+pcno+" has failed to complete the program in  "+time.getText()+" mins\n--------------------------------------------------------\n\n");
                           msgsent=true;
                     }
                    JOptionPane.showMessageDialog(null,"Sorry "+name+" your TIME has expired. ","Cryptic-C",JOptionPane.INFORMATION_MESSAGE);
                    
                  compl.setEnabled(false);
                  coder.setEnabled(false);
                  jQuitButton.setEnabled(false);
                  outputArea.append("\n\nResults of this Round will be out soon. Best of Luck!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
             
         }
         
         //if(time.getText()=="00:00"){
      
         //}
         
         sec++;
         if(sec>59){
             //time.setText(min+":0"+sec);
             sec = 0;;
              min++;
          }
      
    }
  };
 
    /** Creates new form Round3 */
    public Round3() {
        initComponents();
        run.setEnabled(false);
        compl.setEnabled(false);
         countdown= new Timer(delay,timer);
         
        carretPos = 0;
        tosave=new File("round3.c");
        Runtime r1= Runtime.getRuntime();
        Process p1;
       try{
        //p1=r1.exec("rm round1.c");
           clientSocket = new Socket("192.168.4.1", 6789);
         outToServer = new DataOutputStream(clientSocket.getOutputStream());
           if(tosave.exists()){
               FileReader fr = new FileReader(tosave);
                BufferedReader reader = new BufferedReader(fr);
                coder.read(reader, "jTextArea1");
           }
        p1=r1.exec("rm round3");
       }catch(Exception e){}
        countdown.start();
    }
    
    private void compile(){
    try {
                rt = Runtime.getRuntime();
                
                fstream = new FileWriter(tosave);
                out = new BufferedWriter(fstream);
                
                //while(jTextArea1.read)
                out.write(coder.getText());
                out.close();
                
        	//fstream = new FileWriter(rslt);
		//out = new BufferedWriter(fstream);
                
		procC = rt.exec("gcc -o round3 round3.c -lm");
               // wait(5000);
               int cval=procC.waitFor();
              if(cval==0){
                    result = true;
                 outputArea.append("Compilation successfull.");
		   
               // op.append(proc.toString());
		//br = new BufferedReader(new InputStreamReader(procR.getInputStream()));
               /* while ((line = br.readLine()) != null) 
		{
                       // outputArea.invalidate();
			//outputs +=   ( line.toString()+"\n");
                       // outputArea.invalidate();
                        outputArea.append(line.toString()+"\n");
                        
		}*/
                ///out.close();
                //outputArea.setText(outputs);
                }
                else if(cval!=0){
                    result = false;
                    outputArea.append("Following Error occured in code :-\n");
                   br = new BufferedReader(new InputStreamReader(procC.getErrorStream()));
                while ((line = br.readLine()) != null) 
		{
                       // outputArea.invalidate();
			//outputs +=   ( line.toString()+"\n");
                       // outputArea.invalidate();
                        outputArea.append(line.toString()+"\n");
                        //outputArea.append("\n\nResults of this Round will be out soon. Best of Luck!");
		} 
                   
                }
                    
            } catch (Exception ex) {
//                outputArea.append(ex.getMessage().toString());
            }
    
}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        coder = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        compl = new javax.swing.JButton();
        run = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        linenum = new javax.swing.JTextArea();
        time = new javax.swing.JLabel();
        jQuitButton = new javax.swing.JButton();
        save = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        coder.setBackground(java.awt.Color.white);
        coder.setColumns(20);
        coder.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        coder.setForeground(new java.awt.Color(61, 99, 253));
        coder.setRows(5);
        coder.setTabSize(4);
        coder.setToolTipText("Your codegoes here");
        coder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                coderMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                coderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                coderMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                coderMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                coderMouseReleased(evt);
            }
        });
        coder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                coderKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                coderKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                coderKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(coder);

        outputArea.setBackground(new java.awt.Color(241, 241, 188));
        outputArea.setColumns(20);
        outputArea.setFont(new java.awt.Font("Ubuntu", 1, 15));
        outputArea.setLineWrap(true);
        outputArea.setRows(5);
        outputArea.setToolTipText("Error Console Window");
        outputArea.setEnabled(false);
        jScrollPane2.setViewportView(outputArea);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15));
        jLabel1.setText("Error Console");

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15));
        jLabel2.setText("Your code goes here");

        compl.setBackground(java.awt.Color.darkGray);
        compl.setFont(new java.awt.Font("Ubuntu", 1, 15));
        compl.setForeground(new java.awt.Color(254, 254, 254));
        compl.setText("Compile");
        compl.setToolTipText("Compile");
        compl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                complActionPerformed(evt);
            }
        });

        run.setBackground(java.awt.Color.darkGray);
        run.setFont(new java.awt.Font("Ubuntu", 1, 15));
        run.setForeground(new java.awt.Color(254, 254, 254));
        run.setText("Run");
        run.setToolTipText("Run");
        run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runActionPerformed(evt);
            }
        });

        exit.setBackground(java.awt.Color.darkGray);
        exit.setFont(new java.awt.Font("Ubuntu", 1, 15));
        exit.setForeground(new java.awt.Color(254, 254, 254));
        exit.setText("Exit");
        exit.setFocusable(false);
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        linenum.setBackground(java.awt.Color.darkGray);
        linenum.setColumns(20);
        linenum.setEditable(false);
        linenum.setFont(new java.awt.Font("Ubuntu", 1, 15));
        linenum.setForeground(new java.awt.Color(254, 254, 254));
        linenum.setRows(5);
        linenum.setToolTipText("Current Line Number");
        jScrollPane3.setViewportView(linenum);

        time.setBackground(java.awt.Color.darkGray);
        time.setFont(new java.awt.Font("DejaVu Sans", 1, 18));
        time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        time.setText("00:00");
        time.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jQuitButton.setBackground(java.awt.Color.darkGray);
        jQuitButton.setFont(new java.awt.Font("Ubuntu", 1, 15));
        jQuitButton.setForeground(new java.awt.Color(254, 254, 254));
        jQuitButton.setText("Quit / Done");
        jQuitButton.setToolTipText("Quit if Done");
        jQuitButton.setFocusable(false);
        jQuitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jQuitButtonActionPerformed(evt);
            }
        });

        save.setBackground(java.awt.Color.darkGray);
        save.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        save.setForeground(new java.awt.Color(1, 1, 1));
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        jLabel3.setText("Developed by: Rajiv Singh & Annim Banerjee");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 523, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(compl, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(run, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(114, 114, 114)
                                .addComponent(jQuitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jQuitButton)
                    .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(save))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(compl)
                        .addComponent(run))
                    .addComponent(exit)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void complActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_complActionPerformed
        
        outputArea.setText(null); 
         System.gc();
        compile();
        if(result){
            run.setEnabled(true);
        }else
            compl.setEnabled(false);
         
                // TODO add your handling code here:
}//GEN-LAST:event_complActionPerformed

private void runActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runActionPerformed
        try {
            procR = rt.exec("gnome-terminal --title Cryptic-C -x ./hold.sh");
        }catch(Exception e){}      // TODO add your handling code here:
}//GEN-LAST:event_runActionPerformed

private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
int x =JOptionPane.showConfirmDialog(this,"Are you sure","Cryptic-C",JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
    if(x==JOptionPane.YES_OPTION){        
            try {
                jQuitButton.setEnabled(false);
                 System.gc();
                compile();
                if(!msgsent){
                if(result) {
                        outToServer.writeBytes("--------------------------------------------------------\n(Exit button pressed!)-Laqshya Id:- "+id+"\n on System_no:-"+pcno+"\n has completed program in "+time.getText()+" mins\n--------------------------------------------------------\n\n");
                    }
                else if(!result) {
                        outToServer.writeBytes("--------------------------------------------------------\n(Exit button pressed!)-Laqshya Id:- "+id+"\n on System_no:-"+pcno+"\n has failed to complete the program in  "+time.getText()+" mins\n--------------------------------------------------------\n\n");
                    }
                }    
            System.exit(0);
            } // TODO add your handling code here:
            catch (IOException ex) {
                ex.printStackTrace();
            }
    }// TODO add your handling code here:
}//GEN-LAST:event_exitActionPerformed

private void coderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_coderKeyReleased
     
                          // TODO add your handling code here:
        
    try{
        compl.setEnabled(true);
        
        
        
    carretPos = coder.getCaretPosition();
    linenum.setText("Line #: " + String.valueOf(coder.getLineOfOffset(carretPos)+1));  
    }
    catch(Exception exec){
    System.out.println("error in calculating column and Row. :(" + exec.getMessage());
    }
}//GEN-LAST:event_coderKeyReleased

private void coderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_coderKeyPressed
// TODO add your handling code here:
   
   
}//GEN-LAST:event_coderKeyPressed

private void coderKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_coderKeyTyped
         //compl.setEnabled(true);       // TODO add your handling code here:
         
         
         
}//GEN-LAST:event_coderKeyTyped

private void jQuitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jQuitButtonActionPerformed
        try {
            
            int x =JOptionPane.showConfirmDialog(this,"Are you sure to Submit your Program?\n\nOnces Submitted will be considered \nas the Final Submission of your code.\n\nTo go back to EDITOR, Click NO!\n\nTo Submit/Done Click YES!","Cryptic-C",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(x == JOptionPane.YES_OPTION){
                
                outputArea.setText(null);
                 System.gc();
                compile();
                
             //   if(result)
                  if(result){
                        outToServer.writeBytes("--------------------------------------------------------\n*) Laqshya Id:- "+id+"\n   on System_no:-"+pcno+" \n   has completed program in "+time.getText()+" mins\n--------------------------------------------------------\n\n");
                        msgsent=true;
                     }
                       else if(!result){
                           outToServer.writeBytes("--------------------------------------------------------\n*) Laqshya Id:- "+id+"\n   on System_no:-"+pcno+"\n   has failed to complete the program in  "+time.getText()+" mins\n--------------------------------------------------------\n\n");
                           msgsent=true;
                     }
                
                      countdown.stop();
                       coder.setEditable(false);
                       outputArea.append("\n\nResults of this Round will be out soon. Best of Luck!");
                        try {
                            outToServer.writeBytes("");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                  
                        jQuitButton.setEnabled(false);
                
            // TODO add your handling code here:
            }
            else{
               
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    
}//GEN-LAST:event_jQuitButtonActionPerformed

private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        try {
            fstream = new FileWriter(tosave);
                    out = new BufferedWriter(fstream);
                    
                    //while(jTextArea1.read)
                    out.write(coder.getText());
                    out.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
                // TODO add your handling code here:
}//GEN-LAST:event_saveActionPerformed

private void coderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coderMouseClicked
        try {
            carretPos = coder.getCaretPosition();
    linenum.setText("Line #: " + String.valueOf(coder.getLineOfOffset(carretPos)+1));  
}//GEN-LAST:event_coderMouseClicked
        catch (BadLocationException ex) {
            ex.printStackTrace();
        }
}
private void coderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coderMouseEntered
        try {
            carretPos = coder.getCaretPosition();
    linenum.setText("Line #: " + String.valueOf(coder.getLineOfOffset(carretPos)+1));  
}//GEN-LAST:event_coderMouseEntered
        catch (BadLocationException ex) {
            ex.printStackTrace();
 
        }
}
private void coderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coderMouseExited
        try {
            carretPos = coder.getCaretPosition();
    linenum.setText("Line #: " + String.valueOf(coder.getLineOfOffset(carretPos)+1));  
}//GEN-LAST:event_coderMouseExited
        catch (BadLocationException ex) {
            ex.printStackTrace();
        }}
private void coderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coderMousePressed
        try {
            carretPos = coder.getCaretPosition();
    linenum.setText("Line #: " + String.valueOf(coder.getLineOfOffset(carretPos)+1));  
}//GEN-LAST:event_coderMousePressed
        catch (BadLocationException ex) {
            ex.printStackTrace();
        }}
private void coderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coderMouseReleased
        try {
            carretPos = coder.getCaretPosition();
    linenum.setText("Line #: " + String.valueOf(coder.getLineOfOffset(carretPos)+1));  
}//GEN-LAST:event_coderMouseReleased
        catch (BadLocationException ex) {
            ex.printStackTrace();
        }
}  /**
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
            java.util.logging.Logger.getLogger(Round3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Round3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Round3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Round3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Round3().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea coder;
    private javax.swing.JButton compl;
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jQuitButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea linenum;
    private javax.swing.JTextArea outputArea;
    private javax.swing.JButton run;
    private javax.swing.JButton save;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}










