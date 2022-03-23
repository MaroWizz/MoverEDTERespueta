import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MoverXMLSegunTAG {
	public static void main(String[] args) throws ParserConfigurationException, SAXException {
		File diradquirirArchivos = new File("C:\\extract-attachment-mail\\XML\\attachment\\");


		MoverXMLSegunTAG mvXML = new MoverXMLSegunTAG();
		mvXML.ValidarXML(diradquirirArchivos);

	}

	public void ValidarXML(File dir) throws ParserConfigurationException, SAXException {
		try {
			File PathEnvioDTE = new File("C:\\a\\EnvioDTE");
			File PathOtros = new File("C:\\a\\Otros");
			File PathRespuesta = new File("C:\\a\\Respuesta");
			File[] ArregloXML = dir.listFiles();

			for (File file : ArregloXML) {
				if ((file.isDirectory() == false)
						&& (file.getAbsolutePath().endsWith(".xml") || file.getAbsolutePath().endsWith(".XML"))) {
					System.out.println("Documento a consultar: " + file.getName());
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();
					Document document = db.parse(file);
					document.getDocumentElement().normalize();
					System.out.println("El elemento raiz es: " + document.getDocumentElement().getNodeName());

					if (document.getDocumentElement().getNodeName().equals("RespuestaDTE")) {
						System.out.println("Se mueve a ruta " + PathRespuesta);
						System.out.println(file.getCanonicalPath());
						if (!PathRespuesta.exists())
							PathRespuesta.mkdirs();
						Files.move(Paths.get(file.getCanonicalPath()), Paths.get(PathRespuesta +"\\"+ file.getName()),
								StandardCopyOption.REPLACE_EXISTING);
						System.out.println("*****");
						System.out.println("*****");
					}

					if (document.getDocumentElement().getNodeName().equals("EnvioDTE")) {
						System.out.println("Se mueve a ruta " + PathEnvioDTE);
						System.out.println(dir + "\\EnvioDTE\\" + file.getName());
						if (!PathEnvioDTE.exists())
							PathEnvioDTE.mkdirs();
						Files.move(Paths.get(file.getCanonicalPath()), Paths.get(PathEnvioDTE +"\\"+ file.getName()),
								StandardCopyOption.REPLACE_EXISTING);
						System.out.println("*****");
						System.out.println("*****");
					}

					if (!document.getDocumentElement().getNodeName().equals("RespuestaDTE")
							&& !document.getDocumentElement().getNodeName().equals("EnvioDTE")) {
						System.out.println("Se mueve a ruta " + PathOtros);
						if (!PathOtros.exists())
							PathOtros.mkdirs();
						Files.move(Paths.get(file.getCanonicalPath()), Paths.get(PathOtros +"\\"+ file.getName()),
								StandardCopyOption.REPLACE_EXISTING);
						System.out.println("*****");
						System.out.println("*****");
					}

				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
	
