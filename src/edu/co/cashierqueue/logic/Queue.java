package edu.co.cashierqueue.logic;

import UI.Ventana;
import edu.co.cashierqueue.models.Node;
import java.util.Random;

/**
 *
 * @author juancsr, davidssantos
 */
public class Queue {
    private Node cashierNode;
    private int clients;
    Ventana v;

    
    public Queue() {
        generateClients();
        System.out.println("clients: " + clients);
    }
    
    public void initValues() {
        v = new Ventana();
        int ranClientReceipts;
        
        int maxClientReceipts = 14;
        int maxCashierReceipts = 2;
        Random rand = new Random();
        int ranCashierReceitps = rand.nextInt(maxCashierReceipts)+1;
        
        cashierNode = new Node(ranCashierReceitps, Node.CASHIER_TYPE); // this is de cashier
        v.btnStart.addActionListener(new java.awt.event.ActionListener() {      //Metodo implementado provisional para el actionperformed 
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        System.out.println("cashier's dispatch capacity: " + cashierNode.getReceipts());
        ranClientReceipts = rand.nextInt(maxClientReceipts)+1;
        Node clientsStruct = new Node(ranClientReceipts, Node.CLIENT_TYPE+"1");
        cashierNode.setNext(clientsStruct);
        clientsStruct.setNext(cashierNode);
        Node newClient;
        
        for (int i = 1; i < clients; i++) {
            ranClientReceipts = rand.nextInt(maxClientReceipts)+1;
            newClient = new Node(ranClientReceipts, Node.CLIENT_TYPE+(i+1));
            newClient.setNext(cashierNode);
            clientsStruct = (clientsStruct.getNext() == null) ? clientsStruct : clientsStruct.getNext();
            clientsStruct.setNext(newClient);
        }
    }
    public void btnStartActionPerformed(java.awt.event.ActionEvent evt) {
        v.lblCashier.setText(cashierNode.CASHIER_TYPE);
        v.lblClientCounter.setText("CLIENTES: " + clients );
    }
    
    public void executeProcess() {
        System.err.println("-- Starting process --");
        dispatchProcess();
        System.out.println("-- Finished process --");
    }
    
    private void dispatchProcess() {
        printAllStructure();
        int customerRecetips;
        Node lastCustomer, currentCustomer;
        
        int cashierReceipts = this.cashierNode.getReceipts();  // available cashier receipts        
        while (this.cashierNode.getNext() != this.cashierNode) {
            
            currentCustomer = this.cashierNode.getNext();
            customerRecetips = currentCustomer.getReceipts();
            System.out.println("currentCustomer recepits: "+ currentCustomer.getReceipts());
            
            currentCustomer.setReceipts(customerRecetips - cashierReceipts);
            this.cashierNode.setNext(currentCustomer.getNext());
            System.out.println("currentCustomer after recepits: "+ currentCustomer.getReceipts());
            
            if (currentCustomer.getReceipts() > 0) {
                lastCustomer = getLastNode(this.cashierNode);
                lastCustomer.setNext(currentCustomer);
                currentCustomer.setNext(this.cashierNode);
            }
            System.out.print("structure: ");
            printAllStructure();
        }
        System.out.println("(Node type: " + this.cashierNode.getType() + 
                    " Node receipts: " + this.cashierNode.getReceipts()+"),");
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
