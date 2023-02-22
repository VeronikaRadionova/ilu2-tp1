package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private int nbEtals;
	private Marche marche;
	

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.nbEtals = nbEtals;
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
// VILLAGE + MARCHÉ //
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + produit + "s.\n");
		int numeroEtal = marche.trouverEtalLibre();
		marche.etals[numeroEtal].occuperEtal(vendeur, produit, nbProduit);
		chaine.append("Le vendeur " + vendeur.getNom() + " vend des " + produit + "s à l'étal n°" + numeroEtal);
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		
	}
	
	// public Etal rechercherEtal(Gaulois vendeur) {	
	//}
	
	public String partirVendeur(Gaulois vendeur) {
		
	}
	
	
	
	
// MARCHÉ MARCHÉ MARCHÉ //
	
	private static class Marche {
		private Etal[] etals;
		private int nbEtals;
		
		private Marche(int nbEtals) {
			etals = new Etal[nbEtals];
			for (int i = 0; i < nbEtals; i++) {
				this.etals[i] = new Etal();
			}
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		public int trouverEtalLibre() {
			for (int i=0; i < nbEtals; i++) {
				if (!etals[i].isEtalOccupe())
					return i;
			}
			return -1;
		}
		
		public Etal[] trouverEtals(String produit) {
			int nbEtalsProduit = 0;
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].contientProduit(produit))
					nbEtalsProduit++;
			}
			Etal[] etalsProduit = new Etal[nbEtals];
			for (int j = 0; j < nbEtals; j++) {
				if (etals[j].contientProduit(produit))
					etalsProduit[nbEtalsProduit] = etals[j];
			}
			return etalsProduit;
			}

		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < nbEtals; i++) {
				if (etals[i].getVendeur() == gaulois)
					return etals[i];		
			}
			return null;
		}
		
		public String afficherMarche() {
			int nbEtalVide = 0;
			for (int i = 0; i < nbEtals; i++) {
				if (!etals[i].isEtalOccupe())
					return etals[i].getVendeur() + " vend " + etals[i].getQuantite() + etals[i].getProduit();
				else
					nbEtalVide++;
			}
			return "Il reste " + nbEtalVide + " étals non utiliés dans le marché.\n";
		}
		
		
		}
}
		