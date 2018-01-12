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
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
						transformer.setOutputProperty(OutputKeys.INDENT, "yes");
						transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
 
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

			//document.appendChild(document.createComment(" "));

			Element racine = document.createElement("scenario");

			Element FPS = document.createElement("FPS");
			racine.appendChild(FPS);

			Element background = document.createElement("background");
			//background.setTextContent(" ");
			racine.appendChild(background);

			Element vitesse_background = document.createElement("vitesse_background");
			//background.setTextContent(" ");
			racine.appendChild(vitesse_background);

			Element tailleX = document.createElement("tailleX");
			//background.setTextContent(" ");
			racine.appendChild(tailleX);

			Element tailleY = document.createElement("tailleY");
			//background.setTextContent(" ");
			racine.appendChild(tailleY);

			Element joueur = document.createElement("joueur");
			//background.setTextContent(" ");
	
			Element texture = document.createElement("texture");
			//background.setTextContent(" ");
			joueur.appendChild(texture);

			Element pointDeVie = document.createElement("pointDeVie");
			//background.setTextContent(" ");
			joueur.appendChild(pointDeVie);

			Element pointsRecompense = document.createElement("pointsRecompense");
			//background.setTextContent(" ");
			joueur.appendChild(pointsRecompense);

			Element orientationTir = document.createElement("orientationTir");
			//background.setTextContent(" ");
			joueur.appendChild(orientationTir);

			Element typeArme = document.createElement("typeArme");
			//background.setTextContent(" ");

			Element nom = document.createElement("nom");
			//background.setTextContent(" ");
			typeArme.appendChild(nom);

			Element nombreMunition = document.createElement("nombreMunition");
			//background.setTextContent(" ");
			typeArme.appendChild(nombreMunition);

			Element ecartMunition = document.createElement("ecartMunition");
			//background.setTextContent(" ");
			typeArme.appendChild(ecartMunition);

			joueur.appendChild(typeArme);

			Element puissanceTir = document.createElement("puissanceTir");
			//background.setTextContent(" ");
			typeArme.appendChild(ecartMunition);

		



			racine.appendChild(joueur);

			document.appendChild(racine);
			

			transformerXml(document, "./NouveauNiveau.xml");
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
}
