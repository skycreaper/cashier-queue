package edu.co.cashierqueue.logic;

import edu.co.cashierqueue.models.Node;
import java.util.Random;

/**
 *
 * @author juancsr, davidssantos
 */
public class Queue {
    private Node node;
    private int clients;
    
    public Queue() {
        generateClients();
        System.out.println("clients: " + clients);
    }
    
    public void initValues() {
        int ranClientReceipts;
        
        int maxClientReceipts = 14;
        int maxCashierReceipts = 2;
        Random rand = new Random();
        int ranCashierReceitps = rand.nextInt(maxCashierReceipts)+1;
        
        node = new Node(ranCashierReceitps, Node.CASHIER_TYPE); // this is de cashier
        System.out.println("cashier's dispatch capacity: " + node.getReceipts());
        for (int i = 0; i < clients; i++) {
            ranClientReceipts = rand.nextInt(maxClientReceipts)+1;
            Node clientNode = new Node(ranClientReceipts, Node.CLIENT_TYPE);
            System.out.println("clientNode receipts: " + clientNode.getReceipts());
            Node aviable = aviableNode(this.node);
            aviable.setNext(clientNode);
        }
    }
    
    public void executeProcess() {
        
    }
    
    private void dispatchProcess() {
        printAllStructure();
        int cltRemainingRecp;
        while (this.node.getNext() != this.node) {
            cltRemainingRecp = this.node.getNext().getReceipts();
            this.node.getNext().setReceipts(cltRemainingRecp - this.node.getReceipts());
            if (this.node.getNext().getReceipts() < 0) {
                this.node.setNext(this.node.getNext().getNext());
            } else {
                Node aviable = aviableNode(this.node);
            }
            printAllStructure();
        }
    }
    
    private Node aviableNode(Node node) {
        if (node.getNext() == null) {
            System.out.println("Found empty: " + node.getType() + ", " + node.getReceipts());
            return node;
        }
        return aviableNode(node.getNext());
    }
    
    private void generateClients() {
        Random rand = new Random();
        this.clients = rand.nextInt(5)+1;
    }
    
    public void printAllStructure() {
        Node _node = this.node;
        System.out.print("[");
        while (_node != null) {
            System.out.print("(Node type: " + _node.getType() + 
                    " Node receipts: " + _node.getReceipts()+"),");
            _node = _node.getNext();
        }
        System.out.println("]");
    }
}
