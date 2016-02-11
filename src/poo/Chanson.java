package poo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Chanson extends Profil implements Comparable<Profil>, Searchable<Chanson>{
	public int id;
	public String titre;
	public String artiste;
	public String album;
	public int annee;
	private Style style1;
	private Style style2;
	private Style style3;
	private String theme;
	private Time duree;
	private int rythme;
	private String paroles;
	private String instrument;

	public Chanson(int idc, String ti, String art, String alb, int a, Style st1, Style st2, Style st3, String th, Time d, int ry, String pa, String i){
		id = idc;
		titre = ti;
		artiste = art;
		album = alb;
		annee = a;
		style1 = st1;
		style2 = st2;
		style3 = st3;
		theme = th;
		duree = d;
		rythme = ry;
		paroles = pa ;
		instrument = i;
	}
	
	public Chanson(){}

	public int getId(){return id;}
	public int getAnnee(){return annee;}
	public String getTitre(){return titre;}
	public String getArtiste(){return artiste;}
	public Time getDuree(){return duree;}
	public Style getSt1(){return style1;}
	public Style getSt2(){return style2;}
	public Style getSt3(){return style3;}
	public String getTheme(){return theme;}
	public int getRythme(){return rythme;}
	public String getParoles(){return paroles;}
	public String getinstru(){return instrument;}
	public String getAlbum(){return album;}
	
	public float comparer_instrument(Chanson c){
		float resultat;
		if(this.instrument.equals(c.instrument))	resultat = 1;
		else resultat = 0;
		
		return resultat;
	}

	/**
	 * Comparaison des styles entre deux chansons/deux profils : <br>
	 * Plusieurs cas : On diff&eacute;rencie selon le nombre d'entrées nulles dans la BDD
	 * @param 	p Le profil dont il faut comparer les styles
	 * @return	le pourcentage de proximit&eacute; des styles
	 */
	public float comparer_styles(Profil p){
		float result = 0;
		int n = 0;
		if(titre.equals(p.getTitre()))	return 1; // Permet d'éviter de trop longs calculs si les chansons sont les mêmes
		
			if(style2==null && style3 == null && p.getSt2() == null && p.getSt3() == null)	n = 1; 
			else if(style2 == null && style3 == null && p.getSt3()==null)					n = 2;
			else if(style3==null && p.getSt2()==null && p.getSt3()==null)					n = 3;
			else if(style3==null && p.getSt3()==null)										n = 4;
			else if(style2==null && style3==null)											n = 5;
			else if(p.getSt2()==null && p.getSt3()==null)									n = 6;
			else if(style3==null) 															n = 7;
			else if(p.getSt3()==null)														n = 8;
			else 																			n = 9;
			
			switch(n){
			case 1 :	result = (style1.comparer(p.getSt1()));	break;
			case 2 :	result = Math.max(style1.comparer(p.getSt1()), style1.comparer(p.getSt2())); break;
			case 3 :	result = Math.max(style1.comparer(p.getSt1()), style2.comparer(p.getSt1())); break;
			case 4 :	result = Math.max(style1.comparer(p.getSt1()), style1.comparer(p.getSt2())) * (float)0.5;
						result +=Math.max(style2.comparer(p.getSt1()), style2.comparer(p.getSt2())) * (float)0.5; break;
			case 5 :	result = Math.max(Math.max(style1.comparer(p.getSt1()), style1.comparer(p.getSt2())), style1.comparer(p.getSt3()));	break;
			case 6 :	result = Math.max(Math.max(style1.comparer(p.getSt1()), style2.comparer(p.getSt1())), style3.comparer(p.getSt1()));	break;
			case 7 :	result = Math.max( Math.max(style1.comparer(p.getSt1()), style1.comparer(p.getSt2())), style1.comparer(p.getSt3()) ) * (float)0.5;
						result += Math.max( Math.max(style2.comparer(p.getSt1()), style2.comparer(p.getSt2())), style2.comparer(p.getSt3()) ) * (float)0.5;	break;
			case 8 :	result = Math.max( Math.max(style1.comparer(p.getSt1()), style2.comparer(p.getSt1())), style3.comparer(p.getSt1()) ) * (float)0.5;
						result += Math.max( Math.max(style1.comparer(p.getSt2()), style2.comparer(p.getSt2())), style3.comparer(p.getSt2()) ) * (float)0.5;	break;
			case 9 :	result = Math.max( Math.max(style1.comparer(p.getSt1()), style1.comparer(p.getSt2())), style1.comparer(p.getSt3()) ) * (float)(1/3);
						result += Math.max( Math.max(style2.comparer(p.getSt1()), style2.comparer(p.getSt2())), style2.comparer(p.getSt3()) ) * (float)(1/3);	
						result += Math.max( Math.max(style3.comparer(p.getSt1()), style3.comparer(p.getSt2())), style3.comparer(p.getSt3()) ) * (float)(1/3); break;
			}
			return result;			
	}
	
	/**
	 * Comparaison des themes ente deux chansons/deux profils<br>
	 * @param p Le profil dont il faut comparer le theme
	 * @return	Le pourcentage de proximit&eacute; : 1 si les themes sont identiques, 0 sinon
	 */
	public float comparer_theme(Profil p) {
		if(titre.equals(p.getTitre()))return 1;
		float result = 0 ;
		try{
			if(theme.equals(p.getTheme()))	result = 1;
		}catch(Exception e){System.out.println("Un des thèmes est null");}
		return result;
	}
	
	/**
	 * Comparaison des dur&eacute;es de deux chansons/deux profils<br>
	 * Calcule la diff&eacute;rence d entre les dur&eacute;es, renvoie un float en fonction de d 
	 * @param p Le profil dont il faut comparer la dur&eacute;e
	 * @return	Le pourcentage de proximit&eacute; : 1 moins 0.005 par seconde de diff&eacuterence 
	 */
	public float comparer_duree(Profil p){
		Duree d = new Duree(this.duree);
		Duree d2 = new Duree(p.getDuree());
		d = d.difference(d2);
		float result=1;
		
		int secondesdifferences = d.getSecondes()+d.getMinutes()*60+d.getHeures()*3600;
			for(int i = 0; i<secondesdifferences; i++)	result-=0.005;
		System.out.println(d.getSecondes()+" "+d.getMinutes());
		if(result<=0) result = 0;
		return result;
 	}
	
	/**
	 * Comparaison des ann&eacute;es entre deux chansons/deux profils
	 * @param p Le profil dont il faut comparer l'ann&eacute;e
	 * @return Le pourcentage de proximit&eacute; : 1 moins 0.1 par ann&eacute;e de diff&eacute;rence, 0 au minimum.
	 */
	public float comparer_annee(Profil p){
		float result = Math.abs(this.annee-p.getAnnee());
		result = 1-result/10;
		if(result<=0)	result = 0;
		return result;
	}
	
	public float comparer_titre(Profil p){
		float result = 0;
		if(p.getTitre().equals(titre)) result = 1;
		else if(titre.contains(p.getTitre())) result = (float)0.75;
		else{
			result = (float)0.75;
			result -= 0.05*distance(titre, p.getTitre());
		}
		return result;
	}
	
	public float comparer_album(Profil p){
		float result = 0;
		if(p.getAlbum().equals(album)) result = 1;
		else if(album.contains(p.getAlbum())) result = (float)0.75;
		else{
			result = (float)0.75;
			result -= 0.05*distance(album, p.getAlbum());
		}
		return result;
	}
	
	public float comparer_artiste(Profil p){
		float result = 0;
		if(p.getArtiste().equals(artiste)) result = 1;
		else if(artiste.contains(p.getArtiste())) result = (float)0.75;
		else{
			result = (float)0.75;
			result -= 0.05*distance(artiste, p.getArtiste());
		}
		return result;
	}
	
	public String toString(){
		return (titre+ " - " + artiste);
	}
	
	@Override
	public float comparer(Profil profil) {
		if(this.equals(profil))return 1;
		float coefficient_style = (float) 0.45;
		float coefficient_theme = (float) 0.30;
		float coefficient_duree = (float) 0.15;
		float coefficient_annee = (float) 0.10;
		System.out.println("Styles : " + comparer_styles(profil));
		System.out.println("themes : " + comparer_theme(profil));
		System.out.println("duree : " + comparer_duree(profil));
		System.out.println("annee : " + comparer_annee(profil));
		return(	coefficient_style * this.comparer_styles(profil)
				+ coefficient_theme * this.comparer_theme(profil)
				+ coefficient_duree * this.comparer_duree(profil)
				+ coefficient_annee * this.comparer_annee(profil));
	}
	
	public boolean equals(Chanson o){
		if(titre.equals(o.getTitre()) && artiste.equals(o.getArtiste()) && album.equals(o.getAlbum()) && annee==o.getAnnee()){
			return true;
		}
		return false;
	}
	
	
	public ArrayList<Chanson> recherche_titre(String s, ArrayList<Chanson> arr) {
		ArrayList<Rech_song> result = new ArrayList<Rech_song>();
		int i = 0;
		
		// On commence la recherche dans la liste des chansons de la BD
		for(Chanson x: arr){
			
			// Si une chanson est trouvée exactement (son nom est dans la BD, casse excluse) on l'ajoute à la liste
			// avec une distance de 0, définie comme la distance minimale possible.
			if(s.equalsIgnoreCase(x.getTitre())){			
				
				result.add(new Rech_song(new Chanson(x.getId(), x.getTitre(), x.getArtiste(), x.getAlbum(), x.getAnnee(), x.getSt1(), x.getSt2(), x.getSt3(), x.getTheme(), x.getDuree(), x.getRythme(), x.getParoles(), x.getinstru()), 0));
				System.out.println("Correspondance exacte\nChanson trouvée : " + result.get(0).a.getTitre() +", " + result.get(0).a.getArtiste() + ".");
				i++;
			} 
			// Sinon, si le nom rentré par l'utilisateur est contenu dans le nom d'une chanson de la BD, mais que ce n'est
			// pas le nom exact, on l'ajoute mais avec une distance de 1, qui est la distance minimale pour 2 chaînes non identiques.
			else if(x.getTitre().contains(s)){			
				
				result.add(new Rech_song(new Chanson(x.getId(), x.getTitre(), x.getArtiste(), x.getAlbum(), x.getAnnee(), x.getSt1(), x.getSt2(), x.getSt3(), x.getTheme(), x.getDuree(), x.getRythme(), x.getParoles(), x.getinstru()), 1));
				System.out.println("Correspondance approximative\nChanson trouvée : " + result.get(i).a.getTitre() +", " + result.get(i).a.getArtiste() + ".");
				i++;
			} 
			// Sinon on calcule la distance de Levenshtein, et si elle n'est pas trop grande, on insère l'artiste dans la liste avec
			// comme distance, la distance de Levenshtein.
			else if(distance(x.getTitre(),s)<10){
				result.add(new Rech_song(new Chanson(x.getId(), x.getTitre(), x.getArtiste(), x.getAlbum(), x.getAnnee(), x.getSt1(), x.getSt2(), x.getSt3(), x.getTheme(), x.getDuree(), x.getRythme(), x.getParoles(), x.getinstru()), distance(x.getTitre(),s)));
				i++;
			}
		}
		
		result.sort(new Comparator<Rech_song>(){
						public int compare(Rech_song r1, Rech_song r2) {
							if(r1.d<r2.d) return -1;
							else if(r1.d>r2.d) return 1;
							return 0;
							}});

		
		ArrayList<Chanson> res = new ArrayList<Chanson>();
		for(Rech_song e: result){
			res.add(e.a);
		}
		
		if(res.isEmpty()) System.out.println("Aucune coorespondance pour \"" + s + "\" dans la base de données.");
		return res;
	}
	
	public int min(int x, int y, int z){
		int mini = x;
		if(x<=y){
			if(x<=z){
				mini = x;
			} else {
				mini = z;
			}
		} else {
			if(y<=z){
				mini = y;
			} else {
				mini = z;
			}
		}
		
		return mini;
	}
	
	public int distance(String a, String b){
		int[][] d = new int[a.length()+1][b.length()+1];
		
		int i, j, cout;
		
		for(i = 0; i<a.length()+1; i++) d[i][0] = i;
		for(j = 0; j<b.length()+1; j++) d[0][j] = j;
		
		for(i = 1; i<a.length()+1;i++){
			for(j = 1; j<b.length()+1;j++){
				if(a.regionMatches(true, i-1, b, j-1, 1)) cout = 0;
				else cout = 1;
				d[i][j] = min(d[i-1][j]+1, d[i][j-1]+1, d[i-1][j-1]+cout);
			}
		}
		
		return d[a.length()][b.length()];
	}

	@Override
	public ArrayList<Chanson> recherche(Profil p, ArrayList<Chanson> arr) {
		ArrayList<Rech_song> result = new ArrayList<Rech_song>();
		ArrayList<Rech_song> tmp = new ArrayList<Rech_song>();
		
		System.out.println(p.getTitre());
		System.out.println(p.getArtiste());
		System.out.println(p.getAlbum());
		System.out.println(p.getAnnee());
		
		
		// recherche selon le titre
		if(!p.getTitre().isEmpty()){
			for(Chanson c : arr){
				result.add(new Rech_song(c, (int)(100*c.comparer_titre(p))));
			}
		}
		
		// recherche selon l'artiste
		if(!p.getArtiste().isEmpty()){
			for(Chanson c : arr){
				tmp.add(new Rech_song(c, (int)(100*c.comparer_artiste(p))));
			}
		}
		
		// réunion des deux
		if(!result.isEmpty()){
			for(Rech_song c : tmp){
				for(Rech_song d : result){
					if(c.a.equals(d.a)) d.d = d.d+c.d;
				}
			}
		}
		
		// recherche selon l'album
		if(!p.getAlbum().isEmpty()){
			for(Chanson c : arr){
				tmp.add(new Rech_song(c, (int)(100*c.comparer_album(p))));
			}
		}
		// réunion des deux
		if(!result.isEmpty()){
			for(Rech_song c : tmp){
				for(Rech_song d : result){
					if(c.a.equals(d.a)) d.d = d.d+c.d;
				}
			}
		}
		
		// recherche selon l'annee
		if(p.getAnnee()!=0){
			for(Chanson c : arr){
				tmp.add(new Rech_song(c, (int)(100*c.comparer_annee(p))));
			}
		}		
		// réunion des deux
		if(!result.isEmpty()){
			for(Rech_song c : tmp){
				for(Rech_song d : result){
					if(c.a.equals(d.a)) d.d = d.d+c.d;
				}
			}
		}
				
		// recherche selon la duree
		for(Chanson c : arr){
			tmp.add(new Rech_song(c, (int)(100*c.comparer_duree(p))));
		}
		
		// réunion des deux
		if(!result.isEmpty()){
			for(Rech_song c : tmp){
				for(Rech_song d : result){
					if(c.a.equals(d.a)) d.d = d.d+c.d;
				}
			}
		}
		
		// recherche selon le style
		if(p.getSt1()!=null){
			for(Chanson c : arr){
				tmp.add(new Rech_song(c, (int)(100*c.comparer_styles(p))));
			}
		}
		// réunion des deux
		if(!result.isEmpty()){
			for(Rech_song c : tmp){
				for(Rech_song d : result){
					if(c.a.equals(d.a)) d.d = d.d+c.d;
				}
			}
		}
		
		// recherche selon le theme
		if(p.getTheme()!=null){
			for(Chanson c : arr){
				tmp.add(new Rech_song(c, (int)(100*c.comparer_theme(p))));
			}
		}
		// réunion des deux
		if(!result.isEmpty()){
			for(Rech_song c : tmp){
				for(Rech_song d : result){
					if(c.a.equals(d.a)) d.d = d.d+c.d;
				}
			}
		}
		
		Collections.sort(result, new Comparator<Rech_song>(){
			public int compare(Rech_song r1, Rech_song r2) {
				if(r1.d<r2.d) return 1;
				else if(r1.d>r2.d) return -1;
				return 0;
				}
			}
		);
		
		ArrayList<Chanson> res = new ArrayList<Chanson>();
		for(Rech_song e: result){
			res.add(e.a);
		}
		
		return res;
	}
}
