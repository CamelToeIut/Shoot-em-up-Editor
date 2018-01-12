import javax.xml.parsers.*; 
import org.w3c.dom.*; 
import org.xml.sax.*; 
import javax.xml.transform.*; 
import javax.xml.transform.dom.*; 
import javax.xml.transform.stream.*; 
import java.io.*; 
 
public class CreationXML{

	public static void transformerXml(Document document, String fichier) {
	
        try {
            // Création de la source DOM
            Source source = new DOMSource(document);
 
            // Création du fichier de sortie
            File file = new File(fichier);
            Result resultat = new StreamResult(fichier);
 
            // Configuration du transformer
            TransformerFactory fabrique = TransformerFactory.newInstance();
            Transformer transformer = fabrique.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

  transformer.setParameter(OutputKeys.INDENT, "yes");
 
            // Transformation
            transformer.transform(source, resultat);
        }catch(Exception e){
        	e.printStackTrace();	
        	}
    	}
	public static void main(String[] args){
		try{
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			Document document = constructeur.newDocument();

			document.setXmlVersion("1.0");
			document.setXmlStandalone(true);


			Element racine = document.createElement("scenario");

			Element FPS = document.createElement("FPS");
			racine.appendChild(FPS);

			Element background = document.createElement("background");
			//background.setTextContent(" ");
			racine.appendChild(background);


			document.appendChild(racine);
			

			transformerXml(document, "./NouveauNiveau.xml");
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
}
