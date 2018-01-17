import javax.xml.parsers.*; 
import org.w3c.dom.*; 
import org.xml.sax.*; 
import javax.xml.transform.*; 
import javax.xml.transform.dom.*; 
import javax.xml.transform.stream.*; 
import java.io.*;
import java.util.ArrayList; 
import MG2D.geometrie.*;
 
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
			Element ennemi,textureE,pointsRecompense,orientationTir,typeArmeE,nomE,nombreMunitionE,
			ecartMunitionE,puissanceTirE,cadenceTirE,vitesseTirE,evolutionSpatioTemporelle,etape,temps,pointXspa,pointYspa;
			ArrayList<Ennemi> listeEnnemi = new ArrayList<>();
			listeEnnemi.add(new Ennemi("img/avion1.png",1,1,1,1,1,1,1,1,new ArrayList<Point>(),new ArrayList<Long>(),1,1,1));
			listeEnnemi.add(new Ennemi("img/avion1.png",1,1,1,1,1,1,1,1,new ArrayList<Point>(),new ArrayList<Long>(),1,1,1));
			listeEnnemi.add(new Ennemi("img/avion1.png",1,1,1,1,1,1,1,1,new ArrayList<Point>(),new ArrayList<Long>(),1,1,1));
			listeEnnemi.add(new Ennemi("img/avion1.png",1,1,1,1,1,1,1,1,new ArrayList<Point>(),new ArrayList<Long>(),1,1,1));
			listeEnnemi.add(new Ennemi("img/avion1.png",1,1,1,1,1,1,1,1,new ArrayList<Point>(),new ArrayList<Long>(),1,1,1));
			listeEnnemi.add(new Ennemi("img/avion1.png",1,1,1,1,1,1,1,1,new ArrayList<Point>(),new ArrayList<Long>(),1,1,1));
			document.setXmlVersion("1.0");
			document.setXmlStandalone(true);

			//document.appendChild(document.createComment("ddd"));

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

			Element hitBox = document.createElement("hitBox");
			//background.setTextContent(" ");
			joueur.appendChild(hitBox);

			Element pointDeVie = document.createElement("pointDeVie");
			//background.setTextContent(" ");
			joueur.appendChild(pointDeVie);

			Element vitesseDeplacement = document.createElement("vitesseDeplacement");
			//background.setTextContent(" ");
			joueur.appendChild(vitesseDeplacement);

			Element cadenceTir = document.createElement("cadenceTir");
			//background.setTextContent(" ");
			joueur.appendChild(cadenceTir);

			Element puissanceTir = document.createElement("puissanceTir");
			//background.setTextContent(" ");
			

			Element TIR_UNIQUE = document.createElement("TIR_UNIQUE");
			//background.setTextContent(" ");
			puissanceTir.appendChild(TIR_UNIQUE);

			Element PARALLELE = document.createElement("PARALLELE");
			//background.setTextContent(" ");
			puissanceTir.appendChild(PARALLELE);

			Element EVENTAIL = document.createElement("EVENTAIL");
			//background.setTextContent(" ");
			puissanceTir.appendChild(EVENTAIL);

			joueur.appendChild(puissanceTir);

			racine.appendChild(joueur);
			//////////////////////////////////////////////////////////////////////////////
			for(int i=0;i<listeEnnemi.size();i++){
				ennemi = document.createElement("ennemi");
				
				textureE = document.createElement("texture");
				textureE.setTextContent("test ");
				ennemi.appendChild(textureE);


				pointsRecompense = document.createElement("pointsRecompense");
				pointsRecompense.setTextContent("dude");
				ennemi.appendChild(pointsRecompense);

				orientationTir = document.createElement("orientationTir");
				//background.setTextContent(" ");
				ennemi.appendChild(orientationTir);

				typeArmeE = document.createElement("typeArme");
				//background.setTextContent(" ");

				nomE = document.createElement("nom");
				//background.setTextContent(" ");
				typeArmeE.appendChild(nomE);

				nombreMunitionE = document.createElement("nombreMunition");
				//background.setTextContent(" ");
				typeArmeE.appendChild(nombreMunitionE);

				ecartMunitionE = document.createElement("ecartMunition");
				//background.setTextContent(" ");
				typeArmeE.appendChild(ecartMunitionE);

				ennemi.appendChild(typeArmeE);

				puissanceTirE = document.createElement("puissanceTir");
				//background.setTextContent(" ");
				ennemi.appendChild(puissanceTirE);

				cadenceTirE = document.createElement("cadenceTir");
				//background.setTextContent(" ");
				ennemi.appendChild(cadenceTirE);

				vitesseTirE = document.createElement("vitesseTir");
				//background.setTextContent(" ");
				ennemi.appendChild(vitesseTirE);

				evolutionSpatioTemporelle = document.createElement("evolutionSpatioTemporelle");
				//background.setTextContent(" ");

				etape = document.createElement("vitesseTir");
				//background.setTextContent(" ");

				temps = document.createElement("temps");
				//background.setTextContent(" ");
				evolutionSpatioTemporelle.appendChild(temps);

				pointXspa = document.createElement("pointX");
				//background.setTextContent(" ");
				evolutionSpatioTemporelle.appendChild(pointXspa);

				pointYspa = document.createElement("pointY");
				//background.setTextContent(" ");
				evolutionSpatioTemporelle.appendChild(pointXspa);



				evolutionSpatioTemporelle.appendChild(etape);


				ennemi.appendChild(evolutionSpatioTemporelle);
			

				racine.appendChild(ennemi);
			}
/*///////////////////////////////////////////////////////////////////////////////////////////////////////////
			ennemi = document.createElement("ennemi");
			//background.setTextContent(" ");
			
			textureE = document.createElement("texture");
			//background.setTextContent(" ");
			ennemi.appendChild(textureE);


			pointsRecompense = document.createElement("pointsRecompense");
			//background.setTextContent(" ");
			ennemi.appendChild(pointsRecompense);

			orientationTir = document.createElement("orientationTir");
			//background.setTextContent(" ");
			ennemi.appendChild(orientationTir);

			typeArmeE = document.createElement("typeArme");
			//background.setTextContent(" ");

			nomE = document.createElement("nom");
			//background.setTextContent(" ");
			typeArmeE.appendChild(nomE);

			nombreMunitionE = document.createElement("nombreMunition");
			//background.setTextContent(" ");
			typeArmeE.appendChild(nombreMunitionE);

			ecartMunitionE = document.createElement("ecartMunition");
			//background.setTextContent(" ");
			typeArmeE.appendChild(ecartMunitionE);

			ennemi.appendChild(typeArmeE);

			puissanceTirE = document.createElement("puissanceTir");
			//background.setTextContent(" ");
			ennemi.appendChild(puissanceTirE);

			cadenceTirE = document.createElement("cadenceTir");
			//background.setTextContent(" ");
			ennemi.appendChild(cadenceTirE);

			vitesseTirE = document.createElement("vitesseTir");
			//background.setTextContent(" ");
			ennemi.appendChild(vitesseTirE);

			evolutionSpatioTemporelle = document.createElement("evolutionSpatioTemporelle");
			//background.setTextContent(" ");

			etape = document.createElement("vitesseTir");
			//background.setTextContent(" ");

			temps = document.createElement("temps");
			//background.setTextContent(" ");
			evolutionSpatioTemporelle.appendChild(temps);

			pointXspa = document.createElement("pointX");
			//background.setTextContent(" ");
			evolutionSpatioTemporelle.appendChild(pointXspa);

			pointYspa = document.createElement("pointY");
			//background.setTextContent(" ");
			evolutionSpatioTemporelle.appendChild(pointXspa);



			evolutionSpatioTemporelle.appendChild(etape);


			ennemi.appendChild(evolutionSpatioTemporelle);
			

			racine.appendChild(ennemi);
			////////////////////////////////////////////////////////*/	
			document.appendChild(racine);
			
			transformerXml(document, "./NouveauNiveau.xml");
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
}
