/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Code_Window.java
 *
 * Created on 20 Jan, 2012, 3:01:28 PM
 */
package com.my.cryptC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;

/**
 *
 * @author rajiv
 */
public class Round1 extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;
    protected int delay = 1000;
    protected int max_min= 0;
    protected int max_sec= 0;
    protected String name=UsrDetail.usrdetl[0];
    protected String id=UsrDetail.usrdetl[1];
    static int flag =0;
    protected Timer coutdown;
    protected int Ccounter=0;
    protected Process procC,procR;
    protected Runtime rt;
    protected File tosave;
    private int carretPos;
    protected String line;
    protected FileWriter fstream;
    protected BufferedWriter out;
    protected BufferedReader br;
    FileInputStream qs;
    DataInputStream qin;
    BufferedReader qbr;
    protected String qno;
    String pcno;
    protected boolean result=false;
    protected boolean msgsent;
    BufferedReader inFromUser;
     Socket clientSocket;
       DataOutputStream outToServer;
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
         if(min==20 && sec==0){
             
             coutdown.stop();
             compr.setEnabled(false);
           coder.setEnabled(false);
            System.gc();
             compile();
             
              
             outputArea.append("sorry..."+"\n" );
             
             //compile();
             if(!result){
                    try {
                  outToServer.writeBytes("----------------------------------------------------------------------\n");
                 
                  outToServer.writeBytes("Laqshya Id:- "+id+"\nSystem_no:- "+pcno+"\nQ no:- "+qno+"\nTime Count:- "+time.getText()+"\nResult:- compilation failed\nOutput:-\nNA\n");
                  outToServer.writeBytes("----------------------------------------------------------------------\n");
                  msgsent=true;
                    String rslt="Name:- "+name+"\nId:- "+id+"\nResult:- Failed";
                    Rslt.setText(rslt);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
             }
             else if(result){
                    try {
                        outToServer.writeBytes("Laqshya Id:- "+id+"\nSystem_no:- "+pcno+"\nQ no:- "+qno+"\nTime Count:- "+time.getText()+"\nResult:- compilation successfull\nOutput:-");
                        outToServer.writeBytes(outputArea.getText());
                         msgsent=true;
                        outToServer.writeBytes("\n----------------------------------------------------------------------\n");
                       String rslt="Name:- "+name+"\nId:- "+id+"\nResult:- Passed";
                        Rslt.setText(rslt);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
             }
           JOptionPane.showMessageDialog(null,"Sorry "+name+" your time is over ","Cryptic-C", JOptionPane.INFORMATION_MESSAGE);
           
             
         }
         
         
         
         sec++;
         if(sec>59){
             //time.setText(min+":0"+sec);
             sec = 0;
              min++;
          }
      
    }
  };
 
  
    /** Creates new form Code_Window */
    public Round1() throws IOException {
        FileReader fr = null;
        try {
            initComponents();
            name=UsrDetail.usrname.getText();
            id=UsrDetail.usrid.getText();
            pcno=UsrDetail.pcNo.getText();
            chancesLeftLabel.setText("Chances Left : 2");
            tosave=new File("round1.c");
            Runtime r1= Runtime.getRuntime();
            clientSocket = new Socket("192.168.4.1", 6789);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
           // outToServer.writeBytes("************************************************\nLOGs of Round-1\n************************************************\n");
                
            coutdown= new Timer(delay,timer);
            coutdown.start();
            compr.setEnabled(false);
            Process p1;
            try{
             p1=r1.exec("rm round1.c");
             p1=r1.exec("rm round1");
            }catch(Exception e){}
             Random randomGenerator = new Random();
    
            int randomInt = randomGenerator.nextInt(27);
            String file="./Round1qb/"+String.valueOf(randomInt)+".txt";
           /*fr = new FileReader(file);
           BufferedReader reader = new BufferedReader(fr);
           */ //problem.read(reader, "jTextArea1");
            qs= new FileInputStream(file);
            qin= new DataInputStream(qs);
            qbr = new BufferedReader(new InputStreamReader(qin));
            
           while((line=qbr.readLine())!=null){
               problem.append(line+"\n");
           }
            qs= new FileInputStream(file);
            qin= new DataInputStream(qs);
            qbr = new BufferedReader(new InputStreamReader(qin));
                qno=qbr.readLine();
            
            System.out.println(qno);
        } catch (FileNotFoundException ex) {
        } /*finally {
            try {
//                fr.close();
            } catch (IOException ex) {
            }
        }*/
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        coder = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputArea = new javax.swing.JTextArea();
        time = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        compr = new javax.swing.JButton();
        chancesLeftLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        Rslt = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        problem = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        linenum = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(java.awt.Color.gray);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setForeground(new java.awt.Color(1, 1, 1));
        setName("mainF"); // NOI18N
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setFont(new java.awt.Font("Arial", 1, 15));

        coder.setColumns(20);
        coder.setFont(new java.awt.Font("Arial", 0, 14));
        coder.setForeground(new java.awt.Color(61, 99, 253));
        coder.setRows(5);
        coder.setTabSize(4);
        coder.setToolTipText("Do Code Here");
        coder.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                coderInputMethodTextChanged(evt);
            }
        });
        coder.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                coderPropertyChange(evt);
            }
        });
        coder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                coderKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                coderKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(coder);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 15));
        jLabel2.setForeground(new java.awt.Color(1, 1, 1));
        jLabel2.setText("OUTPUT WINDOW");

        outputArea.setColumns(20);
        outputArea.setEditable(false);
        outputArea.setFont(new java.awt.Font("Ubuntu", 1, 15));
        outputArea.setRows(5);
        outputArea.setTabSize(4);
        outputArea.setToolTipText("Output Window");
        jScrollPane2.setViewportView(outputArea);

        time.setBackground(java.awt.Color.darkGray);
        time.setFont(new java.awt.Font("Arial", 1, 48));
        time.setForeground(new java.awt.Color(1, 1, 1));
        time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        time.setText("00:00");
        time.setToolTipText("Time Ellasped");
        time.setAlignmentX(0.5F);
        time.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        time.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                timePropertyChange(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 15));
        jLabel1.setForeground(new java.awt.Color(1, 1, 1));
        jLabel1.setText("Code Your Program Here:-");

        compr.setBackground(java.awt.Color.darkGray);
        compr.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        compr.setForeground(new java.awt.Color(255, 254, 250));
        compr.setText("Compile And Run");
        compr.setToolTipText("Compile and Test");
        compr.setName("comr"); // NOI18N
        compr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comprActionPerformed(evt);
            }
        });

        chancesLeftLabel.setFont(new java.awt.Font("Ubuntu", 1, 15));
        chancesLeftLabel.setForeground(new java.awt.Color(1, 1, 1));
        chancesLeftLabel.setText("Chances Left:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 15));
        jLabel3.setForeground(new java.awt.Color(1, 1, 1));
        jLabel3.setText("Result:-");

        Rslt.setColumns(20);
        Rslt.setEditable(false);
        Rslt.setFont(new java.awt.Font("Ubuntu", 1, 16));
        Rslt.setLineWrap(true);
        Rslt.setRows(5);
        Rslt.setTabSize(4);
        Rslt.setToolTipText("The Verdict");
        jScrollPane3.setViewportView(Rslt);

        problem.setColumns(20);
        problem.setEditable(false);
        problem.setFont(new java.awt.Font("Arial", 1, 12));
        problem.setForeground(new java.awt.Color(61, 99, 253));
        problem.setRows(5);
        problem.setTabSize(4);
        problem.setToolTipText("Do Code Here");
        problem.setFocusable(false);
        problem.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                problemInputMethodTextChanged(evt);
            }
        });
        problem.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                problemPropertyChange(evt);
            }
        });
        problem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                problemKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                problemKeyTyped(evt);
            }
        });
        jScrollPane4.setViewportView(problem);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 15));
        jLabel4.setForeground(new java.awt.Color(1, 1, 1));
        jLabel4.setText("Problem Statement :-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(224, 224, 224))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                                    .addComponent(jLabel4))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(chancesLeftLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(compr, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel3)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(compr, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chancesLeftLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        exit.setBackground(java.awt.Color.darkGray);
        exit.setForeground(new java.awt.Color(254, 254, 254));
        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        linenum.setBackground(java.awt.Color.gray);
        linenum.setEditable(false);
        linenum.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        linenum.setForeground(new java.awt.Color(255, 250, 250));
        linenum.setToolTipText("Current Line Number");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(linenum, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(exit)
                    .addComponent(linenum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void compile(){
    try {
                rt = Runtime.getRuntime();
                
                fstream = new FileWriter(tosave);
                out = new BufferedWriter(fstream);
                out.write(coder.getText());
                out.close();
                
        	
                 System.gc();
		procC = rt.exec("gcc -o round1 round1.c -lm");
              
              
		if(procC.waitFor()==0){
                    result = true;
                    procR = rt.exec("chmod +x round1");
                    System.gc();
                  procR = rt.exec("./round1");
              
		br = new BufferedReader(new InputStreamReader(procR.getInputStream()));
                while ((line = br.readLine()) != null) 
		{
                      outputArea.append(line.toString()+"\n");
                        
		}
               
                }
                else{
                    result = false;
                    outputArea.append("Following Error occured in code :-\n");
                   br = new BufferedReader(new InputStreamReader(procC.getErrorStream()));
                while ((line = br.readLine()) != null) 
		{
                      
                        outputArea.append(line.toString()+"\n");
                        
		}           
                }
                    
            } catch (IOException | InterruptedException ex) {
                outputArea.append(ex.getMessage().toString());
            }
              
}
private void comprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comprActionPerformed
//op.setText(coder.getText());// TODO add your handling code here:
try{    outputArea.setText(null);
        
        Ccounter++;
        chancesLeftLabel.setText("Chances Left: " + (2-Ccounter));
 
            System.gc();
            compile();

            
    if(Ccounter >=2){
                        compr.setEnabled(false);
                        coder.setEnabled(false);
                         coutdown.stop();
                        if(!result){
                outToServer.writeBytes("----------------------------------------------------------------------\n");         
                outToServer.writeBytes("Laqshya Id:- "+id+"\nSystem_no:- "+pcno+"\nQ no:- "+qno+"\nTime Count:- "+time.getText()+"\nResult:- compilation failed\nOutput:-\nNA\n");
                outToServer.writeBytes("----------------------------------------------------------------------\n");
                 msgsent=true;
                            
                  String rslt="Name:- "+name+"\nId:- "+id+"\nResult:- Sorry you Failed to clear Round-1";
                  Rslt.setText(rslt);
                  compr.setEnabled(false);
             }
             else if(result){
             
                 outToServer.writeBytes("----------------------------------------------------------------------\n");
                 outToServer.writeBytes("Laqshya Id:- "+id+"\nSystem_no:- "+pcno+"\nQ no:- "+qno+"\nTime Count:- "+time.getText()+"\nResult:- compilation successfull\nOutput:-\n");
                 outToServer.writeBytes(outputArea.getText());
                 outToServer.writeBytes("----------------------------------------------------------------------\n");
                  msgsent=true;
                 String rslt="Name:- "+name+"\nId:- "+id+"\nResult:-  Congrats !! Wait for the Result";
                  Rslt.setText(rslt);
                  compr.setEnabled(false);
             }
         } 
             
    //compr.setEnabled(false);
}catch(Exception exe){
     JOptionPane.showMessageDialog(this,exe.getMessage(),"Cryptic-C",JOptionPane.INFORMATION_MESSAGE);
}

     finally{
    
        if(result && outputArea.getText().equals("") && Ccounter<2){
            outputArea.setText("Logic Error in Code :P");
        }
        if(Ccounter >=2){
            coutdown.stop();
                        compr.setEnabled(false);
        if(result && outputArea.getText().equals("")){
                    try {
                        outputArea.setText("Logic Error in Code :P");
                        outToServer.writeBytes("----------------------------------------------------------------------\n");
                        outToServer.writeBytes("Laqshya Id:- "+id+"\nSystem_no:- "+pcno+"\nQ no:- "+qno+"\nTime Count:- "+time.getText()+"\nResult:- compilation failed\nOutput:-\nNA");
                         outToServer.writeBytes("\n----------------------------------------------------------------------\n");
                             msgsent=true;
                        String rslt="Name:- "+name+"\nId:- "+id+"\nResult:- Sorry you Failed to clear Round-1";
                              Rslt.setText(rslt);
                    } catch (IOException ex) {
                    }
        }
        } 
    }
}//GEN-LAST:event_comprActionPerformed

    @SuppressWarnings("deprecation")
private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
    int x =JOptionPane.showConfirmDialog(this,"Are you sure","Cryptic-C",JOptionPane.YES_NO_OPTION);
    if(x==JOptionPane.YES_OPTION){  
        if(!msgsent){
        if(result){
                try {
                    //  coutdown.stop();
                      outToServer.writeBytes("----------------------------------------------------------------------\n");
                      outToServer.writeBytes("Laqshya Id:- "+id+"\nSystem_no:- "+pcno+"\nQ no:- "+qno+"\nTime Count:- "+time.getText()+"\nResult:- compilation successfull\nOutput:-\n");
                      outToServer.writeBytes(outputArea.getText());
                      outToServer.writeBytes("----------------------------------------------------------------------\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        }
        else if(!result){
                try {
                    outToServer.writeBytes("----------------------------------------------------------------------\n");
                    outToServer.writeBytes("Laqshya Id:- "+id+"\nSystem_no:- "+pcno+"\nQ no:- "+qno+"\nTime Count:- "+time.getText()+"\nResult:- compilation failed\nOutput:-\nNA");
                    outToServer.writeBytes("\n----------------------------------------------------------------------\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                            
        }
    
    }
        System.exit(0);
    }
}//GEN-LAST:event_exitActionPerformed

private void coderPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_coderPropertyChange
    // TODO add your handling code here:
}//GEN-LAST:event_coderPropertyChange

private void coderInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_coderInputMethodTextChanged
    
     
    // TODO add your handling code here:
}//GEN-LAST:event_coderInputMethodTextChanged

private void coderKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_coderKeyTyped
    
}//GEN-LAST:event_coderKeyTyped

private void timePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_timePropertyChange

}//GEN-LAST:event_timePropertyChange

private void coderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_coderKeyReleased

            
    try{
        compr.setEnabled(true);
        
        
        
    carretPos = coder.getCaretPosition();
    
    linenum.setText("Line #: " + String.valueOf(coder.getLineOfOffset(carretPos)+1));  
    }
    catch(Exception exec){
    System.out.println("error in calculating column and Row. :(" + exec.getMessage());
    }// TODO add your handling code here:
}//GEN-LAST:event_coderKeyReleased

private void coderMouseClicked(java.awt.event.MouseEvent evt) {
        try {
            carretPos = coder.getCaretPosition();
    linenum.setText("Line #: " + String.valueOf(coder.getLineOfOffset(carretPos)+1));  
}
        catch (BadLocationException ex) {
            ex.printStackTrace();
        }
}
private void coderMouseEntered(java.awt.event.MouseEvent evt) {
        try {
            carretPos = coder.getCaretPosition();
    linenum.setText("Line #: " + String.valueOf(coder.getLineOfOffset(carretPos)+1));  
}
        catch (BadLocationException ex) {
            ex.printStackTrace();
 
        }
}
private void coderMouseExited(java.awt.event.MouseEvent evt) {
        try {
            carretPos = coder.getCaretPosition();
    linenum.setText("Line #: " + String.valueOf(coder.getLineOfOffset(carretPos)+1));  
}
        catch (BadLocationException ex) {
            ex.printStackTrace();
        }}
private void coderMousePressed(java.awt.event.MouseEvent evt) {
        try {
            carretPos = coder.getCaretPosition();
    linenum.setText("Line #: " + String.valueOf(coder.getLineOfOffset(carretPos)+1));  
}
        catch (BadLocationException ex) {
            ex.printStackTrace();
        }}
private void coderMouseReleased(java.awt.event.MouseEvent evt) {
        try {
            carretPos = coder.getCaretPosition();
    linenum.setText("Line #: " + String.valueOf(coder.getLineOfOffset(carretPos)+1));  
}
        catch (BadLocationException ex) {
            ex.printStackTrace();
        }
}
private void problemInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_problemInputMethodTextChanged
// TODO add your handling code here:
}//GEN-LAST:event_problemInputMethodTextChanged

private void problemPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_problemPropertyChange
// TODO add your handling code here:
}//GEN-LAST:event_problemPropertyChange

private void problemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_problemKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_problemKeyReleased

private void problemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_problemKeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_problemKeyTyped

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
            java.util.logging.Logger.getLogger(Round1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Round1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Round1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Round1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new Round1().setVisible(true);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Rslt;
    private javax.swing.JLabel chancesLeftLabel;
    private javax.swing.JTextArea coder;
    private javax.swing.JButton compr;
    private javax.swing.JButton exit;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField linenum;
    private javax.swing.JTextArea outputArea;
    private javax.swing.JTextArea problem;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
