/* ------------------------BDEDITOR-------------------*/

	Element texture, joueur, hitBox, pointDeVie, vitesseDeplacement, cadenceTir, puissanceTir, TIR_UNIQUE, PARALLELE, EVENTAIL;

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

/*----------------------- MAINEU_TEST-------------------*/

		test.parametreJeu(30, "BONDOUR", 2, 800, 600);
		test.ajoutJoueurXML("BONDOUR", "BONDOUR.hitbox", 2000, 10, 20, 20, 50, 30, 100);
		test.modifierJoueurXML("BONDOUR2", "BONDOUR2.hitbox", 2000, 10, 20, 20, 50, 30, 100);
