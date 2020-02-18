package edu.co.cashierqueue.models;

/**
 *
 * @author juancsr
 */
public class Node extends Client{
    public static final String CLIENT_TYPE="c";
    public static final String CASHIER_TYPE = "C";
    
    private Node next;
    private int receipts;
    private String type;
    private String name;
    
    public Node(int receipts, String type, String name) {
        this.next = null;
        this.receipts = receipts;
        this.type = type;
        this.name = name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
