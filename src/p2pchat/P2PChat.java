/*
 * P2PChat - Peer-to-Peer Chat Application
 *
 * Copyright (c) 2014 Ahmed Samy  <f.fallen45@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package p2pchat;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;

import java.io.IOException;

import java.util.Iterator;
import java.util.List;

import netlib.PeerInfo;

public class P2PChat extends javax.swing.JFrame
{
	private PeerNode peer;
	private DefaultListModel peerListModel;

	public P2PChat()
	{
		try {
			peer = new PeerNode(null, 9119);
			PeerNode p = new PeerNode(null, 9117);
			p.discoverPeers("localhost", 9118);
		} catch (IOException e) {
			e.printStackTrace();
		}

		peerListModel = new DefaultListModel();
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        chatText = new javax.swing.JTextArea();
        chatTextField = new javax.swing.JTextField();
        findPeersButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        chatParticipants = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        peerList = new javax.swing.JList();
        sendButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chatText.setEditable(false);
        chatText.setColumns(20);
        chatText.setRows(5);
        jScrollPane1.setViewportView(chatText);

        chatTextField.setText("Type here...");
        chatTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chatTextFieldKeyPressed(evt);
            }
        });

        findPeersButton.setText("Find Peers");
        findPeersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findPeersButtonActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(chatParticipants);

        peerList.setModel(peerListModel);
        peerList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                peerListMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(peerList);

        sendButton.setText("Send");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(chatTextField)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(findPeersButton, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(findPeersButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chatTextField)
                    .addComponent(sendButton)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void findPeersButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_findPeersButtonActionPerformed
	{
		List peers = peer.discoverPeers("localhost", 9118);
		if (peers == null)
			return;

		Iterator it = peers.iterator();
		while (it.hasNext()) {
			PeerInfo info = (PeerInfo) it.next();
			peerListModel.addElement(info.host + ":" + info.port);
		}
	}//GEN-LAST:event_findPeersButtonActionPerformed

    private void chatTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chatTextFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			String message = chatTextField.getText();
			chatTextField.setText("");

			peer.sendMessage(message);
			chatText.append(message);
		}
    }//GEN-LAST:event_chatTextFieldKeyPressed

    private void peerListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_peerListMouseClicked
		if (evt.getButton() == MouseEvent.BUTTON1) {
			String peerInfo = (String) peerList.getSelectedValue();
			String peerHost = peerInfo.substring(0, peerInfo.indexOf(":"));
			int peerPort = Integer.parseInt(peerInfo.substring(peerInfo.indexOf(":")+1, peerInfo.length()));

			try {
				peer.connect(peerHost, peerPort);
			} catch (IOException e) {
				chatText.append("Unable to connect to: " + peerInfo);
				e.printStackTrace();
			}
		}
    }//GEN-LAST:event_peerListMouseClicked

	public static void main(String args[])
	{
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
			         | javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(P2PChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new P2PChat().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList chatParticipants;
    private javax.swing.JTextArea chatText;
    private javax.swing.JTextField chatTextField;
    private javax.swing.JButton findPeersButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList peerList;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables
}
