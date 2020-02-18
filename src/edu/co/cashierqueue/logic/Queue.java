package edu.co.cashierqueue.logic;

import UI.Ventana;
import edu.co.cashierqueue.models.Node;
import java.lang.reflect.Array;
import java.util.Random;

/**
 *
 * @author juancsr, davidssantos
 */
public class Queue {
    private Node cashierNode;
    private int clients;
    Ventana v;
    Node lastCustomer, currentCustomer;
    int customerRecetips;
    
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
        
        cashierNode = new Node(3, Node.CASHIER_TYPE); // this is de cashier
        System.out.println("cashier's dispatch capacity: " + cashierNode.getReceipts());
        ranClientReceipts = rand.nextInt(maxClientReceipts)+1;
        Node clientsStruct = new Node(ranClientReceipts, Node.CLIENT_TYPE+"1");
        cashierNode.setNext(clientsStruct);
        clientsStruct.setNext(cashierNode);
        Node newClient;
        
        for (int i = 1; i <= clients; i++) {
            ranClientReceipts = rand.nextInt(maxClientReceipts)+1;
            newClient = new Node(ranClientReceipts, Node.CLIENT_TYPE+(i));
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
        System.out.println("Click!");
        
        initValues();
        v.drawsNodes(cashierNode);
        v.btnStart.setEnabled(false);
        dispatchProcess();
        v.btnStart.setEnabled(true);
    }
    
    public void executeProcess() {
        System.err.println("-- Starting process --");
        dispatchProcess();
        System.out.println("-- Finished process --");
    }
    
    private void dispatchProcess() {
        //printAllStructure();
        
        
        
        int cashierReceipts = this.cashierNode.getReceipts();  // available cashier receipts        
        Thread waitThread = new Thread() {
            @Override
            public void run() {
                try {

                    while (cashierNode.getNext() != cashierNode) {

                        currentCustomer = cashierNode.getNext();
                        customerRecetips = currentCustomer.getReceipts();
                        //System.out.println("currentCustomer recepits: "+ currentCustomer.getReceipts());

                        currentCustomer.setReceipts(customerRecetips - cashierReceipts);
                        cashierNode.setNext(currentCustomer.getNext());
                        //System.out.println("currentCustomer after recepits: "+ currentCustomer.getReceipts());

                        if (currentCustomer.getReceipts() > 0) {
                            lastCustomer = getLastNode(cashierNode);
                            lastCustomer.setNext(currentCustomer);
                            currentCustomer.setNext(cashierNode);
                        }
                        this.sleep(1000);
                        System.out.println("label: "+ v.nodeLabels.get(currentCustomer.getType()).getText());
                        v.nodeLabels.get(currentCustomer.getType()).setText(currentCustomer.getType()+"|Recibos: "+currentCustomer.getReceipts());
                        
                        System.out.println("label: "+ v.nodeLabels.get(currentCustomer.getType()).getText());

                        System.out.print("structure: ");
                        //printAllStructure();
                        //v.content.getCom
                    }
                } catch(Exception e) {
                } finally {
                    this.interrupt();
                }
            }
        };
        waitThread.start();   
        
        //System.out.println("(Node type: " + this.cashierNode.getType() + " Node receipts: " + this.cashierNode.getReceipts()+"),");
    }
    
    /*
    Get the last node of the queue
    @param node: It the node to starts the search
    */
    private Node getLastNode(Node node) {
        if (node.getNext() == this.cashierNode) {
//            System.out.println("Found empty: " + node.getType());
            return node;
        }
        return getLastNode(node.getNext());
    }
    
    private void generateClients() {
        Random rand = new Random();
        this.clients = rand.nextInt(5)+1;
        //this.clients = 1;
    }
    
    public void printAllStructure() {
        Node node = this.cashierNode;
        System.out.print("[");
        while (node.getNext() != this.cashierNode) {
            System.out.print("(Node type: " + node.getType() + 
                    " Node receipts: " + node.getReceipts()+"),");
            node = node.getNext();
//            System.out.println("\nnode?"+node);
            if (node.getNext() == this.cashierNode) {
                System.out.print("(Node type: " + node.getType() + 
                    " Node receipts: " + node.getReceipts()+"),");
            }
        }
        System.out.println("]");
    }
}
