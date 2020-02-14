package edu.co.cashierqueue.models;

/**
 *
 * @author juancsr
 */
public class Node extends Client{
    public static final char CLIENT_TYPE='c';
    public static final char CASHIER_TYPE = 'C';
    
    private Node next;
    private int receipts;
    private char type;

    public Node(int receipts, char type) {
        this.next = null;
        this.receipts = receipts;
        this.type = type;
    }
    
    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getReceipts() {
        return receipts;
    }

    public void setReceipts(int receipts) {
        this.receipts = receipts;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }
    
    
}
