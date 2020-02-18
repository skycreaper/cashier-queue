package edu.co.cashierqueue.logic;

import UI.Ventana;
import edu.co.cashierqueue.models.Node;
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.Random;
import javax.swing.BorderFactory;

/**
 *
 * @author juancsr, davidssantos
 */
public class Queue {
    private Node cashierNode;
    private int clients;
    public Ventana v;
    
    public Queue() {
        System.out.println("clients: " + clients);
        v = new Ventana();
        v.btnStart.addActionListener(new java.awt.event.ActionListener() {      //Metodo implementado provisional para el actionperformed 
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
    }
    
    public void initValues() {
        
        int ranClientReceipts;
        
        int maxClientReceipts = 14;
        //int maxCashierReceipts = 2;
        Random rand = new Random();
        //int ranCashierReceitps = rand.nextInt(maxCashierReceipts)+1;
        
        cashierNode = new Node(3, Node.CASHIER_TYPE, "Cajero"); // this is de cashier
        ranClientReceipts = rand.nextInt(maxClientReceipts)+1;
        Node clientsStruct = new Node(ranClientReceipts, Node.CLIENT_TYPE+"1", "Cliente 1");
        cashierNode.setNext(clientsStruct);
        clientsStruct.setNext(cashierNode);
        Node newClient;
        
        for (int i = 1; i <= clients; i++) {
            ranClientReceipts = rand.nextInt(maxClientReceipts)+1;
            newClient = new Node(ranClientReceipts, Node.CLIENT_TYPE+(i), "Cliente "+i);
            newClient.setNext(cashierNode);
            clientsStruct = (clientsStruct.getNext() == null) ? clientsStruct : clientsStruct.getNext();
            clientsStruct.setNext(newClient);
        }
    }
    
    public void btnStartActionPerformed(java.awt.event.ActionEvent evt) {
        generateClients();
        cashierNode = null;
        v.lblCashier.setText(cashierNode.CASHIER_TYPE);
        v.lblClientCounter.setText("CLIENTES: " + clients );
        
        initValues();
        v.drawsNodes(cashierNode);
        v.btnStart.setEnabled(false);
        dispatchProcess();
        v.btnStart.setEnabled(false);
    }
    
    public void executeProcess() {
        System.err.println("-- Starting process --");
        dispatchProcess();
        System.out.println("-- Finished process --");
    }
    
    private void dispatchProcess() {
        int cashierReceipts = this.cashierNode.getReceipts();  // available cashier receipts 
        Thread waitThread = new Thread() {
            @Override
            public void run() {
                Node lastCustomer, currentCustomer;
                Object currentNodeLabel;
                int customerRecetips;
                
                try {

                    while (cashierNode.getNext() != cashierNode) {
                        
                        currentCustomer = cashierNode.getNext();
                        customerRecetips = currentCustomer.getReceipts();
                        
                        v.nodeLabels.get(currentCustomer.getType()).setBorder(BorderFactory.createLineBorder(Color.red));
                        this.sleep(1000);
                        
                        currentCustomer.setReceipts(customerRecetips - cashierReceipts);
                        cashierNode.setNext(currentCustomer.getNext());

                        if (currentCustomer.getReceipts() > 0) {
                            lastCustomer = getLastNode(cashierNode);
                            lastCustomer.setNext(currentCustomer);
                            currentCustomer.setNext(cashierNode);
                            
                            v.nodeLabels.get(currentCustomer.getType()).setText(
                                "<html>"+currentCustomer.getName()+"<br>"+
                                "Recibos: "+currentCustomer.getReceipts()+"</html>"
                            );
                            v.nodeLabels.get(currentCustomer.getType()).setBorder(BorderFactory.createLineBorder(Color.black));
                        } else {
                            cashierNode.setNext(currentCustomer.getNext());
                            v.drawsNodes(cashierNode);
                            v.nodeLabels.get(currentCustomer.getType()).setText(
                                "<html>"+currentCustomer.getName()+"<br>"+
                                "Recibos: 0</html>"
                            );
                            v.nodeLabels.get(currentCustomer.getType()).setOpaque(true);
                            v.nodeLabels.get(currentCustomer.getType()).setBackground(Color.red);
                        }
                        
                    }
                } catch(Exception e) {
                } finally {
                    //this.interrupt();
                    System.out.println("thread finished");
                }
                v.btnStart.setEnabled(true);
            }
        };
        waitThread.start();   
    }
    
    /*
    Get the last node of the queue
    @param node: It the node to starts the search
    */
    private Node getLastNode(Node node) {
        if (node.getNext() == this.cashierNode) {
            return node;
        }
        return getLastNode(node.getNext());
    }
    
    private void generateClients() {
        Random rand = new Random();
        this.clients = rand.nextInt(5)+1;
    }
    
    public void printAllStructure() {
        Node node = this.cashierNode;
        System.out.print("[");
        while (node.getNext() != this.cashierNode) {
            System.out.print("(Node type: " + node.getType() + 
                    " Node receipts: " + node.getReceipts()+"),");
            node = node.getNext();
            if (node.getNext() == this.cashierNode) {
                System.out.print("(Node type: " + node.getType() + 
                    " Node receipts: " + node.getReceipts()+"),");
            }
        }
        System.out.println("]");
    }
}
