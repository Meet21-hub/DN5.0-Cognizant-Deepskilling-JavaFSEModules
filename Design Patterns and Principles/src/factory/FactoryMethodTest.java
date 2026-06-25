package factory;

// Abstract factory
abstract class DocumentFactory {
    public abstract Document createDocument();

    // Template method — create and immediately open
    public Document getDocument() {
        Document doc = createDocument();
        doc.open();
        return doc;
    }
}

class WordDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new WordDocument(); }
}

class PdfDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new PdfDocument(); }
}

class ExcelDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new ExcelDocument(); }
}

// Test
public class FactoryMethodTest {
    public static void main(String[] args) {
        DocumentFactory[] factories = {
            new WordDocumentFactory(),
            new PdfDocumentFactory(),
            new ExcelDocumentFactory()
        };

        for (DocumentFactory factory : factories) {
            Document doc = factory.getDocument();
            doc.save();
            doc.close();
            System.out.println();
        }
    }
}
