package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import poo.Chanson;
import poo.Genre;
import poo.init;

public class PanelComp  extends JPanel{
	private ArrayList<Genre> lg;
	private ArrayList<Chanson> lc;
	private JButton valider;
	private JPanel resultats;
	private Chanson selection1 = null;
	private Chanson selection2 = null;
	public PanelComp(){
		new JPanel();
		setLayout(new FlowLayout());
		JPanel pBContent = new JPanel();
		pBContent.setLayout(new FlowLayout());
			pBContent.setPreferredSize(new Dimension(400, 300));
			TitledBorder title;
			Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			title = BorderFactory.createTitledBorder(loweredetched,"Comparaison");
			pBContent.setBorder(title);
			lg = init.recuperer_arbre();
			lc = init.recuperer_chansons(lg);
			
			Collections.sort(lc,new Comparator(){

				@Override
				public int compare(Object a1, Object a2) {
					return ((Chanson)a1).titre.compareTo(((Chanson)a2).titre);	}
		 	});
			/* On crée les deux listes de selection de chansons */
			
			JPanel liste1 = new JPanel();
			final JList list = new JList(lc.toArray()); 
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			list.setVisibleRowCount(-1);
			JScrollPane listScroller = new JScrollPane(list);
			listScroller.setPreferredSize(new Dimension(350, 100));
			liste1.add(listScroller);
			
			JPanel liste2 = new JPanel();
			final JList list2 = new JList(lc.toArray()); 
			list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list2.setLayoutOrientation(JList.HORIZONTAL_WRAP);
			list2.setVisibleRowCount(-1);
			JScrollPane listScroller2 = new JScrollPane(list2);
			listScroller2.setPreferredSize(new Dimension(350, 100));
			liste2.add(listScroller2);
		pBContent.add(liste1);
		pBContent.add(liste2);
		
		/* On valide et on fait la comparaison */
		
		final JLabel resultlab = new JLabel();
		valider = new JButton("Comparer");
		valider.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(!list2.isSelectionEmpty() && !list.isSelectionEmpty()){
					selection1 = lc.get(list.getSelectedIndex());
					selection2 = lc.get(list2.getSelectedIndex());
					System.out.println(selection1.titre);
					System.out.println(selection2.titre);
					System.out.print(selection1.comparer(selection2));
					resultlab.setText(String.format("Les Chansons sont similaires à %.2f %%!", selection1.comparer(selection2)*100));
				}
			}
		});
		
		pBContent.add(valider);
		resultats = new JPanel();
		resultats.setPreferredSize(new Dimension(400, 100));
		resultats.add(resultlab);
		
		add(BorderLayout.CENTER,pBContent);
		add(BorderLayout.CENTER,resultats);

		
	}
}
