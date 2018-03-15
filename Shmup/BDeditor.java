package Jeu;

import javax.xml.parsers.*; 
import org.w3c.dom.*; 
import org.xml.sax.*; 
import javax.xml.transform.*; 
import javax.xml.transform.dom.*; 
import javax.xml.transform.stream.*; 
import java.io.*;
import java.util.ArrayList; 
import javax.xml.transform.TransformerException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import MG2D.geometrie.*;
import Jeu.*;

public class BDeditor{
		
	Element racine, ennemi,textureE,pointsRecompense,
		orientationTir,typeArmeE,nomE,nombreMunitionE,
		ecartMunitionE,puissanceTirE,cadenceTirE,vitesseTirE,
		evolutionSpatioTemporelle,	
		etape,temps,pointXspa,pointYspa;

	DocumentBuilderFactory fabrique;
	DocumentBuilder constructeur;
	Document document;

	ArrayList<Ennemi> listeEnnemi = new ArrayList<>();
	ArrayList<Point> posEnnemi = new ArrayList<>();
	ArrayList<Long> tpsPosEnnemi = new ArrayList<>();

	public BDeditor(){
		
		try{
		fabrique = DocumentBuilderFactory.newInstance();
		constructeur=fabrique.newDocumentBuilder();
		document=constructeur.newDocument();
		
		racine = document.createElement("scenario");

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
		
		document.appendChild(racine);			

		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
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
	// Méthode qui ajoute un ennemi
	public void ajouterEnnemi(String str, int ppointDeVie, int vvitesseTir, long ccadenceTir, int ppuissanceTir, int oorientationTir, int ttypeArme, int ttypeArmeNbMunition, int ttypeArmeEcartMunition, ArrayList<Point> ppositions, ArrayList<Long> ttempsIntermediaires, int ppointsRecompense, int ttailleX, int ttailleY){
		listeEnnemi.add(new Ennemi(str,ppointDeVie,vvitesseTir,ccadenceTir,ppuissanceTir,oorientationTir,ttypeArme,ttypeArmeNbMunition,
		ttypeArmeEcartMunition,ppositions,ttempsIntermediaires,ppointsRecompense,ttailleX,ttailleY));
	}

	public void modifierEnnemi(){
		
	}

	public void supprimerEnnemi(int i){
		listeEnnemi.remove(i);
	}

	public void ajoutTempo(Point p,Long l){
		posEnnemi.add(p);
		tpsPosEnnemi.add(l);
	}

	public void modifierTempo(){
	
	}
	
	public void supprimerTempo(int i){
		posEnnemi.remove(i);
		tpsPosEnnemi.remove(i);
	}


	public void EnregistrerFichier(){
		
	    		System.out.println("Sauvegarde..");
			transformerXml(document, "./NouveauNiveau.xml");
	}
}
