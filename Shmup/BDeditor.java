import javax.xml.parsers.*; 
import org.w3c.dom.*; 
import org.xml.sax.*; 
import javax.xml.transform.*; 
import javax.xml.transform.dom.*; 
import javax.xml.transform.stream.*; 
import java.io.*;
import java.util.ArrayList; 

public class BDeditor{

		
		Element ennemi,textureE,pointsRecompense,orientationTir,typeArmeE,nomE,nombreMunitionE,	ecartMunitionE,puissanceTirE,cadenceTirE,vitesseTirE,evolutionSpatioTemporelle,etape,temps,pointXspa,pointYspa;

	
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
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
						transformer.setOutputProperty(OutputKeys.INDENT, "yes");
						transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
 
            // Transformation
            transformer.transform(source, resultat);
        }catch(Exception e){
        	e.printStackTrace();	
        	}
    	}

	public BDeditor(){
		try{

		DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
		DocumentBuilder constructeur = fabrique.newDocumentBuilder();
		Document document = constructeur.newDocument();	

		racine = document.createElement("racine");
		document.appendChild(racine);

		transformerXml(document, "./NouveauNiveau.xml");

		}catch(Exception e){
			e.printStackTrace();
		}
	
		
		
	}
}

