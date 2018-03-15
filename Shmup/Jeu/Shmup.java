package Jeu;

import MG2D.*;
import MG2D.geometrie.*;

import java.util.Date;
import java.util.ArrayList;

import java.awt.Font;

class Shmup{

    private long tempo;
    private int tailleX;
    private int tailleY;
    private int tailleBarreDeVie;

    private Texture back1, back2;
    private int vitesseBackground;

    private Fenetre f;
    private Clavier clavier;
    private ArrayList<Ennemi> ennemis;
    private boolean gagne,perdu;
    private int score;
    private Joueur t;
    private Texte texteScore;
    private BarreDeVie barreDeVie;
    private ArrayList<TirJoueur> tirJoueur;
    private ArrayList<TirEnnemi> tirsEnnemi;

    private long tpsLancement;
    private long dernierAffichage;

    public Shmup(String ficXML){
	// Chargement complet du programme
	
	// Chargement du scénario
	ScenarioXML sc = new ScenarioXML(ficXML);
	tailleX=sc.getTailleX();
	tailleY=sc.getTailleY();
	tempo=(int)(1000.0/sc.getFps());
	back1 = new Texture(sc.getBackground(),new Point(0,0));
	back2 = new Texture(sc.getBackground(),new Point(0,back1.getHauteur()));
	vitesseBackground = sc.getVitesseBackground();
	
	// Création de la fenêtre
	f = new Fenetre("Shmup",tailleX,tailleY);
	f.setAffichageFPS(true);
	// Récupération du clavier pour une gestion des mouvements
	clavier = f.getClavier();

	// Ajout des background pour le défilement
	f.ajouter(back1);
	f.ajouter(back2);
	
	// Ajout des ennemis dans la fenêtre
	ennemis=sc.getEnnemis();
	for(Ennemi e : ennemis)
	    f.ajouter(e);

	
	perdu = false;
	gagne = false;
	score=0;
	tailleBarreDeVie=(tailleX/4);
	double coeffBarreDeVie=(sc.getPointDeVieJoueur()*1.0)/(tailleBarreDeVie);

	// Création du joueur et positionnement en bas au centre de la fenêtre
	t = new Joueur(sc.getTextureJoueur(), new Point(tailleX/2,10), sc.getPointDeVieJoueur(), sc.getVitesseDeplacementJoueur(), sc.getVitesseTirJoueur(), sc.getCadenceTirJoueur(), sc.getPuissanceTirUniqueJoueur(), sc.getPuissanceTirParalleleJoueur(), sc.getPuissanceTirEventailJoueur(), tailleX, tailleY);
	t.setA(new Point(tailleX/2-t.getLargeur()/2,10));
	f.ajouter(t);

	f.ajouter(new Rectangle(Couleur.BLANC,new Point(0,tailleY-20),new Point(tailleX,tailleY),true));
	texteScore = new Texte(Couleur.NOIR,"SCORE : "+score,new Font("Calibri",Font.TYPE1_FONT,10),new Point(tailleX-100,tailleY-10));
	f.ajouter(texteScore);
	
	Rectangle barreDeVieExterieur = new Rectangle(Couleur.NOIR,new Point(9,tailleY-15), ((int)(t.getPointDeVieMax()/coeffBarreDeVie))+1,11,false);
	f.ajouter(barreDeVieExterieur);

	barreDeVie = new BarreDeVie(t.getPointDeVieMax(), t.getPointDeVie(), coeffBarreDeVie, tailleY);
	f.ajouter(barreDeVie);
	
	tirJoueur = new ArrayList<TirJoueur>();
	tirsEnnemi = new ArrayList<TirEnnemi>();

	// Pour travailler en temps relatif
	// On récupère le temps exact de lancement du programme
	tpsLancement=new Date().getTime();
	dernierAffichage = new Date().getTime();
    }

    public void tempo(){
	// Pause du programme pour arriver à un taux de fps précis
	// On teste en même temps si l'ordinateur est assez puissant
	try{
	    long temp=new Date().getTime();
	    if((temp-dernierAffichage)>tempo){
		System.out.println("Ordinateur pas assez puissant");
		System.exit(0);
	    }
	    Thread.sleep(tempo-(temp-dernierAffichage));
	    
	}catch(Exception e){}
    }

    public void maj(){
	//Boucle principale qui fait touner le jeu 
	// On réinitialise le temps pour connaitre le temps d'exécution total de la boucle principale
	dernierAffichage = new Date().getTime();
	long tpsRelatif=dernierAffichage-tpsLancement;

	// On met à jour la position de l'arrière plan
	back1.setA(new Point(0,(int)(-((tpsRelatif%vitesseBackground)/(vitesseBackground*1.0)*back1.getHauteur()))));
	back2.setA(new Point(0,back1.getB().getY()));

	// On met à jour la position de notre avion
	t.majPosition(clavier.getHaut() || clavier.getZ(),clavier.getBas() || clavier.getS(),clavier.getGauche() || clavier.getQ(),clavier.getDroite() || clavier.getD());

	// Changement du type de tir
	// TODO
	    
	// Si on demande à tirer, on ajoute un tir
	if(clavier.getA()){
	    ArrayList<TirJoueur> listeTir = t.getTir(tpsRelatif);
	    for(TirJoueur tir : listeTir){
		tirJoueur.add(tir);
		f.ajouter(tir);
	    }
	}

	// On met à jour la position de chacun de nos tirs
	for(int i=0;i<tirJoueur.size();i++){
	    tirJoueur.get(i).majPosition(tpsRelatif);
	    if(tirJoueur.get(i).getO().getY()>tailleY){
		f.supprimer(tirJoueur.get(i));
		tirJoueur.remove(i);
		i--;
	    }
	}

	//On met à jour la position de chaque ennemi qui tire s'il y est autorisé
	for(Ennemi e : ennemis){
	    e.majPosition(tpsRelatif);
	    ArrayList<TirEnnemi> listeTir = e.getTir(tpsRelatif,new Point(t.getA().getX()+t.getLargeur()/2,t.getB().getY()));
	    for(TirEnnemi tir : listeTir){
		tirsEnnemi.add(tir);
		f.ajouter(tir);
	    }
	}

	// On met à jour la position de chaque tir ennemi
	for(int i=0;i<tirsEnnemi.size();i++){
	    tirsEnnemi.get(i).majPosition(tpsRelatif);
	    /*Si le tir est sorti de l'aire de jeu, on le supprime*/
	    if(tirsEnnemi.get(i).getO().getX()<0 || tirsEnnemi.get(i).getO().getX()>tailleX){
		f.supprimer(tirsEnnemi.get(i));
		tirsEnnemi.remove(i);
		i--;
	    }else{
		if(tirsEnnemi.get(i).getO().getY()<0 || tirsEnnemi.get(i).getO().getY()>tailleY){
		    f.supprimer(tirsEnnemi.get(i));
		    tirsEnnemi.remove(i);
		    i--;
		}
	    }
	}

	// On teste si on touche un ennemi
	for(int i = 0; i <ennemis.size(); i++){
	    boolean touche=false;
	    if(ennemis.get(i).actif(tpsRelatif) && ennemis.get(i).visible())
		for(int j=0;j<tirJoueur.size();j++)
		    if(!touche)
			if(ennemis.get(i).intersection(tirJoueur.get(j))){
			    ennemis.get(i).touche(tirJoueur.get(j).getPuissance());
			    if(!ennemis.get(i).enVie()){
				score+=ennemis.get(i).getPointsRecompense();
				f.supprimer(ennemis.get(i));
				ennemis.remove(i);
				i--;
				texteScore.setTexte("SCORE : "+score);
			    }
			    f.supprimer(tirJoueur.get(j));
			    tirJoueur.remove(j);
			    j--;
			    touche=true;
			}
	}

	// On regarde si un tir ennemi nous touche
	for(int i=0;i<tirsEnnemi.size();i++)
	    if(t.intersection(tirsEnnemi.get(i))){
		t.touche(tirsEnnemi.get(i).getPuissance());
		f.supprimer(tirsEnnemi.get(i));
		tirsEnnemi.remove(i);
		i--;
		barreDeVie.maj(t.getPointDeVie());
	    }

	if(!t.enVie())
	    perdu=true;

	// On regarde si un ennemi nous touche
	for(Ennemi e : ennemis)
	    if(t.intersection(e))
		perdu=true;

	if(tirsEnnemi.size()==0){
	    boolean tousEnRetraite=true;
	    for(Ennemi e : ennemis)
		if(!e.enRetraite(tpsRelatif))
		    tousEnRetraite=false;
	    if(tousEnRetraite)
		gagne=true;
	}
	    
	// Mise à jour de l'affichage
	f.rafraichir();
    }

    public void afficheStats(){
	f.effacer();
	f.rafraichir();
	Texte messageFin;
	if(perdu)
	    messageFin=new Texte(Couleur.ROUGE,"YOU LOSE !",new Font("Calibri",Font.TYPE1_FONT,20),new Point(tailleX-50,tailleY-20));
	else
	    messageFin=new Texte(Couleur.ROUGE,"Bien Joué !",new Font("Calibri",Font.TYPE1_FONT,20),new Point(tailleX-50,tailleY-20));
	
	messageFin.setA(f.getMilieu());
	f.ajouter(messageFin);
	f.rafraichir();
	
	try{Thread.sleep(3000);}catch(Exception e){}
	
	System.exit(0);
    }

    public boolean getGagne(){
	return gagne;
    }

    public boolean getPerdu(){
	return perdu;
    }

    public int getTailleX(){
	return tailleX;
    }

    public int getTailleY(){
	return tailleY;
    }
}
