package by.tce.jonline.archive;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// работа с xml-файлом
// запускается из Server (или Archive если снять комментарии)

public class FileIO {
	
	// загружаем Архив
	public static List <Student> load() {
		List<Student> archive = new ArrayList<>();
        File xmlFile = new File("archive.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            //System.out.println("Корневой элемент: " + doc.getDocumentElement().getNodeName());
            // получаем узлы с именем Student
            // теперь XML полностью загружен в память в виде объекта Document
            NodeList nodeList = doc.getElementsByTagName("Student");
 
            // создадим из него список объектов Student
            for (int i = 0; i < nodeList.getLength(); i++) {
            	archive.add(getElement(nodeList.item(i)));
            }
 
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return archive;
    }
 
    // создаем из узла документа объект Student
    private static Student getElement(Node node) {
    	int number=0;
    	String name="";
    	int course=0;
    	String faculty="";
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            number = Integer.parseInt(getTagAttr("id", element));
            name = getTagValue("name", element);
            course = Integer.parseInt(getTagValue("course", element));
            faculty = getTagValue("faculty", element);
        }
        Student student = new Student(number, name, course, faculty);
        return student;
    }
    
    // получаем значение атрибута (может, надо было без атрибутов обойтись :)
    private static String getTagAttr(String tag, Element element) {
    	String number = element.getAttribute(tag);
        return number;
    }
 
    // получаем значение элемента по указанному тегу
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
	
	// сохраняем архив
    public static void create(List <Student> archive) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            
            // создаем новый Document, в котором будем создавать наш xml-файл
            Document doc = builder.newDocument();
            // создаем корневой элемент
            Element rootElement = doc.createElement("Archive");
            // добавляем корневой элемент в объект Document
            doc.appendChild(rootElement);
 
            //добавляем дочерние элементы к корневому
            for(Student student : archive) {
            	rootElement.appendChild(getCase(doc, student));
            }
 
            //создаем TransformerFactory для печати в файл 
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
 
            //печатаем в файл
            StreamResult file = new StreamResult(new File("archive.xml"));
 
            //записываем данные
            transformer.transform(source, file);
            System.out.println("Создание XML файла завершено");
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    // метод для создания нового узла XML-файла
    private static Node getCase(Document doc, Student student) {
        Element el = doc.createElement("Student");
 
        // устанавливаем атрибут id
        el.setAttribute("id", ""+student.getNumber());
 
        // создаем элемент name
        el.appendChild(getCaseElements(doc, "name", student.getName()));
        
     // создаем элемент course
        el.appendChild(getCaseElements(doc, "course", ""+student.getCourse()));
 
        // создаем элемент faculty
        el.appendChild(getCaseElements(doc, "faculty", student.getFaculty())); 
        return el;
    }
 
 
    // создание элементов нового узла
    private static Node getCaseElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

}
