import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import MG2D.*;
import MG2D.geometrie.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

class ScenarioXML{
    // Configuration du jeu/niveau
    private int fps;
    private String background;
    private int vitesseBackground;
    private int tailleX,tailleY;

    // attributs du joueur
    private String textureJoueur;
    private String hitBoxJoueur;
    private int pointDeVieJoueur;
    private int vitesseDeplacementJoueur;
    private int cadenceTirJoueur;
    private int puissanceTirUniqueJoueur;
    private int puissanceTirParalleleJoueur;
    private int puissanceTirEventailJoueur;
    private int vitesseTirJoueur;

    // ennemis
    private ArrayList<Ennemi> ennemi;    
    
    
    

    public ScenarioXML(String chemin){
	try {

	    File inputFile = new File(chemin);
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(inputFile);
	    doc.getDocumentElement().normalize();
	    
	    ArrayList<String> list = new ArrayList<String>();

	    // Configuration du jeu/niveau
	    list.clear();
	    list.add("FPS");
	    fps=Integer.parseInt(recherche(doc,list));
	    list.clear();
	    list.add("background");
	    background=new String(recherche(doc,list));
	    list.clear();
	    list.add("vitesse_background");
	    vitesseBackground=Integer.parseInt(recherche(doc,list));
	    list.clear();
	    list.add("tailleX");
	    tailleX=Integer.parseInt(recherche(doc,list));
	    list.clear();
	    list.add("tailleY");
	    tailleY=Integer.parseInt(recherche(doc,list));

	    
	    // Attributs du joueur
	    list.clear();
	    list.add("joueur");list.add("texture");
	    textureJoueur=recherche(doc,list);
	    list.clear();
	    list.add("joueur");list.add("hitBox");
	    hitBoxJoueur=recherche(doc,list);
	    list.clear();
	    list.add("joueur");list.add("pointDeVie");
	    pointDeVieJoueur=Integer.parseInt(recherche(doc,list));
	    list.clear();
	    list.add("joueur");list.add("vitesseDeplacement");
	    vitesseDeplacementJoueur=Integer.parseInt(recherche(doc,list));
	    list.clear();
	    list.add("joueur");list.add("cadenceTir");
	    cadenceTirJoueur=Integer.parseInt(recherche(doc,list));
	    list.clear();
	    list.add("joueur");list.add("puissanceTir");list.add("TIR_UNIQUE");
	    puissanceTirUniqueJoueur=Integer.parseInt(recherche(doc,list));
	    list.clear();
	    list.add("joueur");list.add("puissanceTir");list.add("PARALLELE");
	    puissanceTirParalleleJoueur=Integer.parseInt(recherche(doc,list));
	    list.clear();
	    list.add("joueur");list.add("puissanceTir");list.add("EVENTAIL");
	    puissanceTirEventailJoueur=Integer.parseInt(recherche(doc,list));
	    list.clear();
	    list.add("joueur");list.add("vitesseTir");
	    vitesseTirJoueur=Integer.parseInt(recherche(doc,list));

	    
	    // ennemis
	    ennemi = new ArrayList<Ennemi>();
	    for(int i=0;i<rechercheNbEnnemi(doc);i++)
		ennemi.add(getNiemeEnnemi(doc,i));
     
	} catch (Exception e) {
	    System.out.println(e);
	}
	
	// Replacement des positions pour que les coordonnées des fichiers appraissent comme les centres des images
	for(Ennemi e : ennemi){
	    ArrayList<Point> liste = e.getPositions();
	    for(Point p : liste)
		p.translater(-e.getLargeur()/2,-e.getHauteur()/2);
	    //System.out.println(e);
	}
	
	verifCoherence(chemin);
    }

    // Récupére la valeur de l'attribut dans le document à partir de son arborescence
    private String recherche(Document doc, ArrayList<String> list){
	if(list.size()==1)
	    return doc.getElementsByTagName(list.get(0)).item(0).getTextContent();
	NodeList nl=doc.getElementsByTagName(list.get(0));
	Element e=(Element)(nl.item(0));
	for(int i=1;i<list.size()-1;i++)
	    {
		nl=e.getElementsByTagName(list.get(i));
		e=(Element)(nl.item(0));
	    }
	String retour=e.getElementsByTagName(list.get(list.size()-1)).item(0).getTextContent();
	return retour;
    }

    // Compte le nombre d'ennemi dans le document
    private int rechercheNbEnnemi(Document doc){
	return doc.getElementsByTagName("ennemi").getLength();
    }

    // Crée le Nième ennemi
    private Ennemi getNiemeEnnemi(Document doc, int n){
	Element e = (Element)(doc.getElementsByTagName("ennemi").item(n));
	String texture = new String(e.getElementsByTagName("texture").item(0).getTextContent());
	String pointDeVie = new String(e.getElementsByTagName("pointDeVie").item(0).getTextContent());
	String pointsRecompense = new String(e.getElementsByTagName("pointsRecompense").item(0).getTextContent());
	String orientationTir = new String(e.getElementsByTagName("orientationTir").item(0).getTextContent());
	Element eTypeTir=(Element)(e.getElementsByTagName("typeArme").item(0));
	String typeArme = new String(eTypeTir.getElementsByTagName("nom").item(0).getTextContent());
	String nombreMunition=new String("0"), ecartMunition=new String("0");
	if(typeArme.equals("PARALLELE") || typeArme.equals("EVENTAIL")){
	    nombreMunition = new String(eTypeTir.getElementsByTagName("nombreMunition").item(0).getTextContent());
	    ecartMunition = new String(eTypeTir.getElementsByTagName("ecartMunition").item(0).getTextContent());
	}
	String puissanceTir = new String(e.getElementsByTagName("puissanceTir").item(0).getTextContent());
	String cadenceTir = new String(e.getElementsByTagName("cadenceTir").item(0).getTextContent());
	String vitesseTir = new String(e.getElementsByTagName("vitesseTir").item(0).getTextContent());
	
	ArrayList<Long> tps=new ArrayList<Long>();
	ArrayList<Point> pos=new ArrayList<Point>();
	Element evoSpatioTemp = (Element)(e.getElementsByTagName("evolutionSpatioTemporelle").item(0));
	NodeList nl=evoSpatioTemp.getElementsByTagName("etape");
	for(int i=0;i<nl.getLength();i++){
	    tps.add(new Long(((Element)(nl.item(i))).getElementsByTagName("temps").item(0).getTextContent()));
	    int posx=Integer.parseInt(((Element)(nl.item(i))).getElementsByTagName("pointX").item(0).getTextContent());
	    int posy=Integer.parseInt(((Element)(nl.item(i))).getElementsByTagName("pointY").item(0).getTextContent());
	    pos.add(new Point(posx,posy));
	}
	int orientation=Ennemi.DROIT;
	boolean trouve=false;
	if(orientationTir.equals("VISE")){
	    trouve=true;
	    orientation=Ennemi.VISE;
	}
	if(orientationTir.equals("DROIT")){
	    trouve=true;
	    orientation=Ennemi.DROIT;
	}
	if(orientationTir.equals("ALEATOIRE")){
	    trouve=true;
	    orientation=Ennemi.ALEATOIRE;
	}
	if(!trouve)
	    throw new java.lang.RuntimeException("L'orientation du tir de l'ennemi"+ n+" n'est pas correcte : "+orientationTir);
	trouve=false;

	int type=Vaisseau.TIR_UNIQUE;
	if(typeArme.equals("TIR_UNIQUE")){
	    trouve=true;
	    type=Vaisseau.TIR_UNIQUE;
	}
	if(typeArme.equals("EVENTAIL")){
	    trouve=true;
	    type=Vaisseau.EVENTAIL;
	}
	if(typeArme.equals("PARALLELE")){
	    trouve=true;
	    type=Vaisseau.PARALLELE;
	}
	if(!trouve)
	    throw new java.lang.RuntimeException("L'orientation du tir de l'ennemi"+ n+" n'est pas correcte : "+orientationTir);
	
	return new Ennemi(texture, Integer.parseInt(pointDeVie), Integer.parseInt(vitesseTir), Long.parseLong(cadenceTir), Integer.parseInt(puissanceTir), orientation, type, Integer.parseInt(nombreMunition), Integer.parseInt(ecartMunition), pos, tps, Integer.parseInt(pointsRecompense), tailleX, tailleY);
    }

    private void verifCoherence(String chemin){
	// Configuration du jeu/niveau
	if(fps<30)
	    throw new java.lang.RuntimeException("Le nombre de frame par seconde est trop bas pour avoir une bonne jouabilité ! Il en faut au moins 30. Revoyez le fichier "+chemin);
	File img = new File(background);
	if(!img.exists())
	    throw new java.lang.RuntimeException("Le fichier "+background+" n'existe pas. Revoyez le fichier "+chemin);
	if(vitesseBackground<=0)
	    throw new java.lang.RuntimeException("La vitesse de défilement doit être positive. Revoyez le fichier "+chemin);
	if(tailleX<=0 || tailleY<=0)
	    throw new java.lang.RuntimeException("La taille de l'aire de jeu doit être positive. Revoyez le fichier "+chemin);

	// Attributs du joueur
	img = new File(textureJoueur);
	if(!img.exists())
	    throw new java.lang.RuntimeException("Le fichier "+textureJoueur+" n'existe pas. Revoyez le fichier "+chemin);
	/*img = new File(hitBoxJoueur);
	if(!img.exists())
	throw new java.lang.RuntimeException("Le fichier "+hitBoxJoueur+" n'existe pas. Revoyez le fichier "+chemin);*/
	if(pointDeVieJoueur<=0)
	    throw new java.lang.RuntimeException("Le joueur doit avoir au moins 1 point de vie au démarrage du jeu ! Revoyez le fichier "+chemin);
	if(vitesseDeplacementJoueur<=0)
	    throw new java.lang.RuntimeException("Le joueur doit avoir une vitesse de déplcement qu'au moins 1 ! Revoyez le fichier "+chemin);
	if(cadenceTirJoueur<0)
	    throw new java.lang.RuntimeException("La cadence de tir est le temps en millisecondes entre deux tirs. Ce nombre doit donc être positif. Revoyez le fichier "+chemin);
	if(puissanceTirUniqueJoueur<=0)
	    throw new java.lang.RuntimeException("La puissance du tir doit être positive. Revoyez le fichier "+chemin);
	if(puissanceTirParalleleJoueur<=0)
	    throw new java.lang.RuntimeException("La puissance du tir doit être positive. Revoyez le fichier "+chemin);
	if(puissanceTirEventailJoueur<=0)
	    throw new java.lang.RuntimeException("La puissance du tir doit être positive. Revoyez le fichier "+chemin);
	if(vitesseTirJoueur<0)
	    throw new java.lang.RuntimeException("La vitesse de tir est le temps mis par une munition pour parcourir la hauteur de la fenetre. Ce nombre doit donc être positif. Revoyez le fichier "+chemin);
	
	// Cohérence du scénario
	for(Ennemi e : ennemi){
	    ArrayList<Long> interv = e.getTpsIntervalle();
	    for(int i=1;i<interv.size();i++)
		if(interv.get(i-1)>interv.get(i))
		    throw new java.lang.RuntimeException("Incohérence temporelle pour un ennemi dans le fichier "+chemin);
	}
    }

    // Configuration du jeu/niveau
    public int getFps(){
	return fps;
    }
    public String getBackground(){
	return background;
    }
    public int getVitesseBackground(){
	return vitesseBackground;
    }
    public int getTailleX(){
	return tailleX;
    }
    public int getTailleY(){
	return tailleY;
    }

    
    // Attributs du joueur
    public String getTextureJoueur(){
	return textureJoueur;
    }
    public String getHitBoxJoueur(){
	return hitBoxJoueur;
    }
    public int getPointDeVieJoueur(){
	return pointDeVieJoueur;
    }
    public int getVitesseDeplacementJoueur(){
	return vitesseDeplacementJoueur;
    }
    public int getCadenceTirJoueur(){
	return cadenceTirJoueur;
    }
    public int getPuissanceTirUniqueJoueur(){
	return puissanceTirUniqueJoueur;
    }
    public int getPuissanceTirParalleleJoueur(){
	return puissanceTirParalleleJoueur;
    }
    public int getPuissanceTirEventailJoueur(){
	return puissanceTirEventailJoueur;
    }
    public int getVitesseTirJoueur(){
	return vitesseTirJoueur;
    }


    // Ennemis
    public ArrayList<Ennemi> getEnnemis(){
	return ennemi;
    }

}
