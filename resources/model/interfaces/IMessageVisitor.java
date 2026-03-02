package resources.model.interfaces;

//import resources.model.interfaces.IImageMessage;
//import resources.model.interfaces.ITextMessage;

public interface IMessageVisitor{
    void visit(ITextMessage msg);
    void visit(IImageMessage msg);
}