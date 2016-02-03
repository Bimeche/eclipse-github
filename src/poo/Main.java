package poo;

import ihm.MaFenetre;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.lang.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
public class Main {

	
	
	public static void main(String[] args) {
		int i;
		// INITIALISATIONS
		ArrayList<Genre> liste_genres = init.recuperer_arbre();
		ArrayList<Chanson> liste_chansons = init.recuperer_chansons(liste_genres);
		ArrayList<Artiste> liste_artistes= init.recuperer_artistes();
		// PARTIE GRAPHIQUE
		try {
 		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
 		        if ("Nimbus".equals(info.getName())) {
 		            UIManager.setLookAndFeel(info.getClassName());
 		            break;
 		        }
 		    }
 		} catch (Exception e) {}
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		    MaFenetre fenetre = new MaFenetre((dim.width)/4, (dim.height)/4, 500, 550);
	 		fenetre.setVisible(true);
	 		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 		
	 	Collections.sort(liste_artistes,new Comparator(){

			@Override
			public int compare(Object a1, Object a2) {
				return ((Artiste)a1).getNom().compareTo(((Artiste)a2).getNom());	}
	 	});
	 	
	 

		Genre blues = liste_genres.get(0);
		Genre enfant = liste_genres.get(1);
		Genre classique = liste_genres.get(2);
		Genre country = liste_genres.get(3);
		Genre electronic = liste_genres.get(4);
		Genre folk = liste_genres.get(5);
		Genre disco = liste_genres.get(6);
		Genre jazz = liste_genres.get(7);
		Genre pop_rock = liste_genres.get(8);
		Genre r_and_b = liste_genres.get(9);
		Genre rap = liste_genres.get(10);
		Genre reggae = liste_genres.get(11);

		
		for(i=0; i<liste_chansons.size(); i++){
			System.out.println(liste_chansons.get(i).id-1+ " " + liste_chansons.get(i).titre);
			
		}

		
	}

}

