package factory;

// Target interface
public interface Document {
    void open();
    void save();
    void close();
}

// --- Concrete document types ---

class WordDocument implements Document {
    public void open()  { System.out.println("Opening Word document."); }
    public void save()  { System.out.println("Saving Word document."); }
    public void close() { System.out.println("Closing Word document."); }
}

class PdfDocument implements Document {
    public void open()  { System.out.println("Opening PDF document."); }
    public void save()  { System.out.println("Saving PDF document."); }
    public void close() { System.out.println("Closing PDF document."); }
}

class ExcelDocument implements Document {
    public void open()  { System.out.println("Opening Excel document."); }
    public void save()  { System.out.println("Saving Excel document."); }
    public void close() { System.out.println("Closing Excel document."); }
}
