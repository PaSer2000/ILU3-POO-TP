package testsFonctionnels;

import cartes.Attaque;
import cartes.Borne;
import cartes.Botte;
import cartes.Cartes;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Parade;
import cartes.Type;
import jeu.ZoneDeJeu;

public class TestZoneDeJeu {
	public static void main(String[] args) {
//		 TP3 PARTIE 2
		ZoneDeJeu zoneDeJeu = new ZoneDeJeu();
		System.out.println("Deposer carte 25 km");
		zoneDeJeu.deposer(new Borne(25));
		System.out.println("Deposer carte 50 km");
		zoneDeJeu.deposer(new Borne(50));
		System.out.println("Deposer carte 75 km");
		zoneDeJeu.deposer(new Borne(75));
		System.out.println("Total des bornes : " + zoneDeJeu.donnerKmParcourus());

		System.out.println("Limite : " + zoneDeJeu.donnerLimitationVitesse());
		zoneDeJeu.deposer(new DebutLimite());
		System.out.println("Limite : " + zoneDeJeu.donnerLimitationVitesse());
		zoneDeJeu.deposer(new FinLimite());
		System.out.println("Limite : " + zoneDeJeu.donnerLimitationVitesse());

//		 TP3 PARTIE 3
		boolean depotOK = false;
		ZoneDeJeu zoneDeJeu1 = new ZoneDeJeu();
//		 Feu rouge
		System.out.println("Deposer carte Feu rouge");
		depotOK = zoneDeJeu1.estDepotAutorise(Cartes.FEU_ROUGE);
		System.out.println("dépôt ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu1.deposer(Cartes.FEU_ROUGE);
		}
		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 accident
		System.out.println("Deposer carte attaque - accident");
		depotOK = zoneDeJeu1.estDepotAutorise(new Attaque(Type.ACCIDENT));
		System.out.println("dépôt ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu1.deposer(new Attaque(Type.ACCIDENT));
		}
		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 Feu vert
		System.out.println("Deposer carte Feu vert");
		depotOK = zoneDeJeu1.estDepotAutorise(Cartes.FEU_VERT);
		System.out.println("dépôt ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu1.deposer(Cartes.FEU_VERT);
		}
		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 panne d'essence
		System.out.println("Deposer carte attaque - essence");
		depotOK = zoneDeJeu1.estDepotAutorise(new Attaque(Type.ESSENCE));
		System.out.println("dépôt ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu1.deposer(new Attaque(Type.ESSENCE));
		}
		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 roue de secours
		System.out.println("Deposer carte parade - roue de secours");
		depotOK = zoneDeJeu1.estDepotAutorise(new Parade(Type.CREVAISON));
		if (depotOK) {
			zoneDeJeu1.deposer(new Parade(Type.CREVAISON));
		}
		System.out.println("dépôt ok ? " + depotOK);
		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 bidon d'essence
		System.out.println("Deposer carte parade - essence");
		depotOK = zoneDeJeu1.estDepotAutorise(new Parade(Type.ESSENCE));
		if (depotOK) {
			zoneDeJeu1.deposer(new Parade(Type.ESSENCE));
		}
		System.out.println("dépôt ok ? " + depotOK);
		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 Feu vert
		System.out.println("Deposer carte Feu vert");
		depotOK = zoneDeJeu1.estDepotAutorise(Cartes.FEU_VERT);
		System.out.println("dépôt ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu1.deposer(Cartes.FEU_VERT);
		}
		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 D�pot 100 bornes
		System.out.println("Deposer carte borne - 100");
		depotOK = zoneDeJeu1.estDepotAutorise(new Borne(100));
		System.out.println("dépôt ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu1.deposer(new Borne(100));
		}
		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 D�pot limitation de vitesse 50 bornes
		System.out.println("Deposer carte limite - 50");
		depotOK = zoneDeJeu1.estDepotAutorise(new DebutLimite());
		System.out.println("dépôt ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu1.deposer(new DebutLimite());
		}
		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 D�pot 100 bornes
		System.out.println("Deposer carte borne - 100");
		depotOK = zoneDeJeu1.estDepotAutorise(new Borne(100));
		System.out.println("dépôt ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu1.deposer(new Borne(100));
		}
		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 Depot 25 bornes
		System.out.println("Deposer carte borne - 25");
		depotOK = zoneDeJeu1.estDepotAutorise(new Borne(25));
		System.out.println("dépôt ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu1.deposer(new Borne(25));
		}
		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 D�pot fin limitation
		System.out.println("Deposer carte fin limite - 50");
		depotOK = zoneDeJeu1.estDepotAutorise(new FinLimite());
		System.out.println("dépôt ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu1.deposer(new FinLimite());
		}
		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 D�pot 100 bornes
		System.out.println("Deposer carte borne - 100");
		depotOK = zoneDeJeu1.estDepotAutorise(new Borne(100));
		System.out.println("dépôt ok ? " + depotOK);
		if (depotOK) {
			zoneDeJeu1.deposer(new Borne(100));
		}
		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());

//// RESULTAT ATTENDU POUR LA PARTIE 3 (ne pas d�commenter)
//// 			Deposer carte Feu rouge
////			d�p�t ok ? false
////			peut avancer ? false
////			Deposer carte attaque - accident
////			d�p�t ok ? false
////			peut avancer ? false
////			Deposer carte Feu vert
////			d�p�t ok ? true
////			peut avancer ? true
////			Deposer carte attaque - essence
////			d�p�t ok ? true
////			peut avancer ? false
////			Deposer carte parade - roue de secours
////			d�p�t ok ? false
////			peut avancer ? false
////			Deposer carte parade - essence
////			d�p�t ok ? true
////			peut avancer ? false
////			Deposer carte Feu vert
////			d�p�t ok ? true
////			peut avancer ? true
////			Deposer carte borne - 100
////			d�p�t ok ? true
////			peut avancer ? true
////			Deposer carte limite - 50
////			d�p�t ok ? true
////			peut avancer ? true
////			Deposer carte borne - 100
////			d�p�t ok ? false
////			peut avancer ? true
////			Deposer carte borne - 25
////			d�p�t ok ? true
////			peut avancer ? true
////			Deposer carte fin limite - 50
////			d�p�t ok ? true
////			peut avancer ? true
////			Deposer carte borne - 100
////			d�p�t ok ? true
////			peut avancer ? true
//
////		 TP4 Partie 1
//		boolean depotOK = false;
//		 Feu rouge
//		System.out.println("Deposer carte Feu rouge");
//		depotOK = zoneDeJeu1.estDepotAutorise(Cartes.FEU_ROUGE);
//		System.out.println("d�p�t ok ? " + depotOK);
//		if (depotOK) {
//			zoneDeJeu1.deposer(Cartes.FEU_ROUGE);
//		}
//		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 Botte Vehicule prioritaire
//		System.out.println("Deposer carte Vehicule prioritaire");
//		depotOK = zoneDeJeu1.estDepotAutorise(Cartes.PRIORITAIRE);
//		System.out.println("d�p�t ok ? " + depotOK);
//		if (depotOK) {
//			zoneDeJeu1.deposer(Cartes.PRIORITAIRE);
//		}
//		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 accident
//		System.out.println("Deposer carte attaque - accident");
//		depotOK = zoneDeJeu1.estDepotAutorise(new Attaque(Type.ACCIDENT));
//		System.out.println("d�p�t ok ? " + depotOK);
//		if (depotOK) {
//			zoneDeJeu1.deposer(new Attaque(Type.ACCIDENT));
//		}
//		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//	
//		 botte roue de secours
//		System.out.println("Deposer botte - roue de secours");
//		depotOK = zoneDeJeu1.estDepotAutorise(new Botte(Type.CREVAISON));
//		if (depotOK) {
//			zoneDeJeu1.deposer(new Botte(Type.CREVAISON));
//		}
//		System.out.println("d�p�t ok ? " + depotOK);
//		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 botte as du volant
//		System.out.println("Deposer carte as du volant");
//		depotOK = zoneDeJeu1.estDepotAutorise(new Botte(Type.ACCIDENT));
//		if (depotOK) {
//			zoneDeJeu1.deposer(new Botte(Type.ACCIDENT));
//		}
//		System.out.println("d�p�t ok ? " + depotOK);
//		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 Feu vert
//		System.out.println("Deposer carte Feu vert");
//		depotOK = zoneDeJeu1.estDepotAutorise(Cartes.FEU_VERT);
//		System.out.println("d�p�t ok ? " + depotOK);
//		if (depotOK) {
//			zoneDeJeu1.deposer(Cartes.FEU_VERT);
//		}
//		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 D�pot attaque accident
//		System.out.println("Deposer carte accident");
//		depotOK = zoneDeJeu1.estDepotAutorise(new Attaque(Type.ACCIDENT));
//		System.out.println("d�p�t ok ? " + depotOK);
//		if (depotOK) {
//			zoneDeJeu1.deposer(new Attaque(Type.ACCIDENT));
//		}
//		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		 D�pot limitation de vitesse 50 bornes
//		System.out.println("Deposer carte limite - 50");
//		depotOK = zoneDeJeu1.estDepotAutorise(new DebutLimite());
//		System.out.println("d�p�t ok ? " + depotOK);
//		if (depotOK) {
//			zoneDeJeu1.deposer(new DebutLimite());
//		}
//		System.out.println("peut avancer ? " + zoneDeJeu1.peutAvancer());
//		
//		RESULTAT ATTENDU POUR LE TP4 Partie 1
//		Deposer carte Feu rouge
//		d�p�t ok ? false
//		peut avancer ? false
//		Deposer carte Vehicule prioritaire
//		d�p�t ok ? true
//		peut avancer ? true
//		Deposer carte attaque - accident
//		d�p�t ok ? true
//		peut avancer ? false
//		Deposer botte - roue de secours
//		d�p�t ok ? true
//		peut avancer ? false
//		Deposer carte as du volant
//		d�p�t ok ? true
//		peut avancer ? true
//		Deposer carte Feu vert
//		d�p�t ok ? false
//		peut avancer ? true
//		Deposer carte accident
//		d�p�t ok ? false
//		peut avancer ? true
//		Deposer carte limite - 50
//		d�p�t ok ? false
//		peut avancer ? true
	}
}
