package by.it.mazniou.tasks.task_of_xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.StringWriter;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException, XMLStreamException {
        StringWriter result = new StringWriter();
        XMLStreamWriter xsw = XMLOutputFactory.newFactory().createXMLStreamWriter(result);
        MyContetnHandler myContetnHandler=new MyContetnHandler(tagName,comment,xsw);
        JAXBContext context=JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller=context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj,myContetnHandler);
        return result.toString();
    }

    public static void main(String[] args) throws JAXBException, XMLStreamException {
        System.out.println(Solution.toXmlWithComment(new First(), "second", "it's a comment"));
    }
    public static class MyContetnHandler extends DefaultHandler{
        private String tagName;
        private String comment;
        private XMLStreamWriter xsw;

        public MyContetnHandler(String tagName, String comment, XMLStreamWriter xsw) {
            this.tagName = tagName;
            this.comment = comment;
            this.xsw = xsw;
        }

        @Override
        public void startDocument() throws SAXException {
            try {
                xsw.writeDTD("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if(localName.equals(tagName)) {
                try {
                    xsw.writeComment(comment);
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
            try {
                xsw.writeStartElement("",localName,uri);
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            try {
                xsw.writeEndElement();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String data = new String(ch,start,length);
            if(data.matches(".*[<>&'\"].*")) {
                try {
                    xsw.writeCData(data);
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    xsw.writeCharacters(data);
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
