/*********************************************************************/
/*                                                                   */
/* Copyright 2012-2017 Rémi Synave, Anthony Desitter,                */
/*                     Nicolas Dubrunfaut, Maxime Langa,             */
/*                     Guillaume Langa                               */
/*                                                                   */
/* This file is part of MG2D.                                        */
/* This library uses javazoom library piece of code                  */
/* http://www.javazoom.net                                           */
/* and can be found with this library (file jlayer1.0.1.tar.gz)      */
/*                                                                   */
/* MG2D is free software: you can redistribute it and/or modify      */
/* it under the terms of the GNU General Public License as published */
/* by the Free Software Foundation, either version 3 of the License, */
/* or (at your option) any later version.                            */
/*                                                                   */
/* MG2D is distributed in the hope that it will be useful,           */
/* but WITHOUT ANY WARRANTY; without even the implied warranty of    */
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the      */
/* GNU General Public License for more details.                      */
/*                                                                   */
/* You should have received a copy of the GNU General Public License */
/* along with MG2D. If not, see <http://www.gnu.org/licenses/>.      */
/*                                                                   */
/*********************************************************************/

package MG2D.audio;

import java.io.File;
import java.io.FileInputStream;


import MG2D.audio.player.advanced.AdvancedPlayer;
import MG2D.audio.player.advanced.PlaybackListener;
import MG2D.audio.player.advanced.PlaybackEvent;


/**
 * Cette classe permet la lecture d'une musique en boucle. Il est impossible d'en changer. Faites donc bien attention avant de la lancer !<br />
 * Cette classe sera réécrite d'ici peu. (avant 2025).
 */


public class Musique extends Thread {

    private static String fichier="";
    private static int nbConfig=0;
    private static Thread enCours=null;
    private AdvancedPlayer player;

    /**
     * Permet de créer le morceau de musique qui sera joué en boucle.<br />
     * Attention, ne fonctionne qu'avec des fichiers mp3.<br />
     * Pour lancer la musique, il faut appeler la méthode lecture.
     * @param fic Le chemin vers le fichier de la musique.
     */
    public Musique(String fic) throws java.lang.RuntimeException{
	try{
	    if(fichier.equals(fic) || nbConfig==0){
		if(nbConfig==0){
		    enCours=this;
		    fichier=new String(fic);
		    nbConfig++;
		}
		FileInputStream fis = new FileInputStream(fichier);
		player = new AdvancedPlayer(fis);

		player.setPlayBackListener(new PlaybackListener(){


			//override unimplemented methods
			@Override
			public void playbackFinished(PlaybackEvent evt){
			    try{
				Musique m = new Musique(fichier);
				enCours=m;
				m.lecture();
			    }
			    catch(Exception ex){System.out.println(ex);}
			}

			@Override
			public void playbackStarted(PlaybackEvent evt){
			}
		    });
	    }
	    else{
		System.out.println("Impossible de lancer deux musiques de fond différentes.");
	    }
	}
	catch(Exception ex){System.out.println(ex);}

    }

    /**
     * Lance la lecture de la musique de fond.
     */
    public void lecture(){
	start();
    }

    /**
     * Stoppe la musique.
     */
    public void arret(){
	player.close();
    }

    @Override
    public void run() {
	try{
	    player.play();
	}
	catch(Exception ex){}
	player.close();
    }


}
