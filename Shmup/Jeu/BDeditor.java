

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

public class BDeditor{
	Element background;
	Element racine, ennemi,textureE,pointDeVieE,pointsRecompense,
		orientationTir,typeArmeE,nomE,nombreMunitionE,
		ecartMunitionE,puissanceTirE,cadenceTirE,vitesseTirE,
		evolutionSpatioTemporelle,	
		etape,temps,pointXspa,pointYspa;
	Element texture, joueur, hitBox, pointDeVie, vitesseDeplacement, cadenceTir, puissanceTir, TIR_UNIQUE, PARALLELE, EVENTAIL;
	DocumentBuilderFactory fabrique;
	DocumentBuilder constructeur;
	Document document;

	ArrayList<Ennemi> listeEnnemi = new ArrayList<>();

	Joueur j;

	public BDeditor(){
		
		try{
		fabrique = DocumentBuilderFactory.newInstance();
		constructeur=fabrique.newDocumentBuilder();
		document=constructeur.newDocument();
		
		racine = document.createElement("scenario");
		
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

	public void parametreJeu(int IPS, String bg, int vb, int tX, int tY){

		Element FPS = document.createElement("FPS");
		FPS.setTextContent(Integer.toString(IPS));
		racine.appendChild(FPS);

		Element backgroundjeu = document.createElement("background");
		backgroundjeu.setTextContent(bg);
		racine.appendChild(backgroundjeu);

		Element vitesse_background = document.createElement("vitesse_background");
		vitesse_background.setTextContent(Integer.toString(vb));
		racine.appendChild(vitesse_background);

		Element tailleX = document.createElement("tailleX");
		tailleX.setTextContent(Integer.toString(tX));
		racine.appendChild(tailleX);

		Element tailleY = document.createElement("tailleY");
		tailleY.setTextContent(Integer.toString(tY));
		racine.appendChild(tailleY);	
	}
	// Méthode qui ajoute un ennemi
	public void ajouterEnnemi(String str, int ppointDeVie, int vvitesseTir, long ccadenceTir, int ppuissanceTir, int oorientationTir, int ttypeArme, int ttypeArmeNbMunition, int ttypeArmeEcartMunition, ArrayList<Point> ppositions, ArrayList<Long> ttempsIntermediaires, int ppointsRecompense, int ttailleX, int ttailleY){
		System.out.println("debut ajout ennemi");
		listeEnnemi.add(new Ennemi(str,ppointDeVie,vvitesseTir,ccadenceTir,ppuissanceTir,oorientationTir,ttypeArme,ttypeArmeNbMunition,
		ttypeArmeEcartMunition,ppositions,ttempsIntermediaires,ppointsRecompense,ttailleX,ttailleY));
		System.out.println("ennemi ajouter");
	}

	public void modifierEnnemi(String str, int ppointDeVie, int vvitesseTir, long ccadenceTir, int ppuissanceTir, int oorientationTir, int ttypeArme, int ttypeArmeNbMunition, int ttypeArmeEcartMunition, ArrayList<Point> ppositions, ArrayList<Long> ttempsIntermediaires, int ppointsRecompense, int ttailleX, int ttailleY,int i){
		
		listeEnnemi.add(new Ennemi(str,ppointDeVie,vvitesseTir,ccadenceTir,ppuissanceTir,oorientationTir,ttypeArme,ttypeArmeNbMunition,
		ttypeArmeEcartMunition,ppositions,ttempsIntermediaires,ppointsRecompense,ttailleX,ttailleY));
		
		listeEnnemi.remove(i);
	}

	public void supprimerEnnemi(int i){
		listeEnnemi.remove(i);
	}

	public void ajoutTempo(Point p,Long l,int i){
		listeEnnemi.get(i).getPositions().add(p);
		listeEnnemi.get(i).getTpsIntervalle().add(l);
	}

	public void modifierTempo(int i,int j,Point p,Long l){
		listeEnnemi.get(i).getPositions().add(p);
		listeEnnemi.get(i).getTpsIntervalle().add(l);
		
		listeEnnemi.get(i).getPositions().remove(j);
		listeEnnemi.get(i).getTpsIntervalle().remove(j);
	}
	
	public void supprimerTempo(int i,int j){
		listeEnnemi.get(i).getPositions().remove(j);
		listeEnnemi.get(i).getTpsIntervalle().remove(j);
	}
	
	public void ajoutJoueurXML(String t, String hb, int pv, int vd, int cd, int tu, int p, int e, int vt){
		
		joueur = document.createElement("joueur");
	
		texture = document.createElement("texture");
		texture.setTextContent(t);
		joueur.appendChild(texture);

		hitBox = document.createElement("hitBox");
		hitBox.setTextContent(hb);
		joueur.appendChild(hitBox);

		pointDeVie = document.createElement("pointDeVie");
		pointDeVie.setTextContent(Integer.toString(pv));
		joueur.appendChild(pointDeVie);

		vitesseDeplacement = document.createElement("vitesseDeplacement");
		vitesseDeplacement.setTextContent(Integer.toString(vd));
		joueur.appendChild(vitesseDeplacement);

		cadenceTir = document.createElement("cadenceTir");
		cadenceTir.setTextContent(Integer.toString(cd));
		joueur.appendChild(cadenceTir);

		puissanceTir = document.createElement("puissanceTir");
			
		TIR_UNIQUE = document.createElement("TIR_UNIQUE");
		TIR_UNIQUE.setTextContent(Integer.toString(tu));
		puissanceTir.appendChild(TIR_UNIQUE);

		PARALLELE = document.createElement("PARALLELE");
		PARALLELE.setTextContent(Integer.toString(p));
		puissanceTir.appendChild(PARALLELE);

		EVENTAIL = document.createElement("EVENTAIL");
		EVENTAIL.setTextContent(Integer.toString(e));
		puissanceTir.appendChild(EVENTAIL);

		joueur.appendChild(puissanceTir);

		racine.appendChild(joueur);
	}
	
	public void modifierJoueurXML(String t, String hb, int pv, int vd, int cd, int tu, int p, int e, int vt){
		
		texture.setTextContent(t);
		hitBox.setTextContent(hb);
		pointDeVie.setTextContent(Integer.toString(pv));
		vitesseDeplacement.setTextContent(Integer.toString(vd));
		cadenceTir.setTextContent(Integer.toString(cd));
		TIR_UNIQUE.setTextContent(Integer.toString(tu));
		PARALLELE.setTextContent(Integer.toString(p));	
		EVENTAIL.setTextContent(Integer.toString(e));

	}

	public void ajoutEnnemiXML(){
System.out.println("debut ajout ennemi XML");
		for(int i=0;i<listeEnnemi.size();i++){
			ennemi = document.createElement("ennemi");
			
			textureE = document.createElement("texture");
			textureE.setTextContent(listeEnnemi.get(i).getCheminTexture());
			ennemi.appendChild(textureE);
			
			pointDeVieE = document.createElement("pointDeVie");
			pointDeVieE.setTextContent(Integer.toString(listeEnnemi.get(i).getPointDeVieMax()));

			pointsRecompense = document.createElement("pointsRecompense");
			pointsRecompense.setTextContent(Integer.toString(listeEnnemi.get(i).getPointsRecompense()));
			ennemi.appendChild(pointsRecompense);
			
			orientationTir = document.createElement("orientationTir");
			if(listeEnnemi.get(i).getOrientationTir()==0){
			orientationTir.setTextContent("VISE");
			}else if(listeEnnemi.get(i).getOrientationTir()==1){
			orientationTir.setTextContent("DROIT");
			}else{
			orientationTir.setTextContent("ALEATOIRE");
			}
			ennemi.appendChild(orientationTir);
			
			typeArmeE = document.createElement("typeArme");

				nomE = document.createElement("nom");
				if(listeEnnemi.get(i).getNomArme()==0){
					nomE.setTextContent("TIR_UNIQUE");
					typeArmeE.appendChild(nomE);
				}else if(listeEnnemi.get(i).getNomArme()==1){
					nomE.setTextContent("EVENTAIL");
					typeArmeE.appendChild(nomE);
					
					nombreMunitionE = document.createElement("nombreMunition");
					nombreMunitionE.setTextContent(Integer.toString(listeEnnemi.get(i).getMunArme()));
					typeArmeE.appendChild(nombreMunitionE);
				
					ecartMunitionE = document.createElement("ecartMuntion");
					ecartMunitionE.setTextContent(Integer.toString(listeEnnemi.get(i).getEcartArme()));
					typeArmeE.appendChild(ecartMunitionE);
				}else{
					nomE.setTextContent("PARALLELE");
					typeArmeE.appendChild(nomE);
					
					nombreMunitionE = document.createElement("nombreMunition");
					nombreMunitionE.setTextContent(Integer.toString(listeEnnemi.get(i).getMunArme()));
					typeArmeE.appendChild(nombreMunitionE);
				
					ecartMunitionE = document.createElement("ecartMuntion");
					ecartMunitionE.setTextContent(Integer.toString(listeEnnemi.get(i).getEcartArme()));
					typeArmeE.appendChild(ecartMunitionE);
				}

			ennemi.appendChild(typeArmeE);
			
			puissanceTirE = document.createElement("puissanceTir");
			puissanceTirE.setTextContent(Integer.toString(listeEnnemi.get(i).getPuissanceTir()));
			ennemi.appendChild(puissanceTirE);
			
			cadenceTirE = document.createElement("cadenceTir");
			cadenceTirE.setTextContent(Long.toString(listeEnnemi.get(i).getCadenceTir()));
			ennemi.appendChild(cadenceTirE);
			
			vitesseTirE = document.createElement("vitesseTir");
			vitesseTirE.setTextContent(Integer.toString(listeEnnemi.get(i).getVitesseTir()));
			ennemi.appendChild(vitesseTirE);
			
			evolutionSpatioTemporelle = document.createElement("evolutionSpatioTemporelle");
			
			for(int j=0; j<listeEnnemi.get(i).getPositions().size();j++){
				etape = document.createElement("etape");
				
					temps = document.createElement("temps");
					temps.setTextContent(Long.toString(listeEnnemi.get(i).getTpsIntervalle().get(j).longValue()));
					etape.appendChild(temps);
					
					pointXspa = document.createElement("pointXspa");
					pointXspa.setTextContent(Integer.toString(listeEnnemi.get(i).getPositions().get(j).getX()));
					etape.appendChild(pointXspa);
					
					pointYspa = document.createElement("pointYspa");
					pointYspa.setTextContent(Integer.toString(listeEnnemi.get(i).getPositions().get(j).getY()));
					etape.appendChild(pointYspa);
					
				evolutionSpatioTemporelle.appendChild(etape);
				
			}
			ennemi.appendChild(evolutionSpatioTemporelle);
			
			racine.appendChild(ennemi);
		}
System.out.println("ennemis ajouter xml");
	}
	
	public void enregistrerFichier(String s){
		document.appendChild(racine);
		System.out.println("Sauvegarde..");
		transformerXml(document, s.concat(".xml"));
    }
}
