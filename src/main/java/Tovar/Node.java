package Tovar;

public class Node {
    private Tovar Data;
    private Node Next, Prev;
    public Tovar getData(){
        return Data;
    }
    public void setData(Tovar Data) {
        this.Data = Data;
    }
    public Node getNext(){
        return Next;
    }
    public void setNext(Node Next) {
        this.Next = Next;
    }
    public Node getPrev(){
        return Prev;
    }
    public void setPrev(Node Prev) {
        this.Prev = Prev;
    }
}
