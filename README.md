# Système de Réservation d'Hôtel

> Application console en **Java** permettant de gérer les réservations de chambres d'hôtel avec authentification, gestion des chambres et suivi des réservations.

---

## Équipe

| Rôle | Responsabilité |
|------|---------------|
| **Aita Mbengue Sy** | Couche `model/` + couche `service/` + `Main.java` |
| **Aissatou Thiam** | Couche `ui/` (menus & affichage console) |

---

## 📁 Structure du projet

```
hotel/
├── src/sn/projet/reservation
                    │   ├── Main.java                   # Point d'entrée du programme
                    │   ├── model/
                    │   │   ├── User.java               # Utilisateur (login, rôle, mot de passe)
                    │   │   ├── Room.java               # Chambre (numéro, type, prix, statut)
                    │   │   └── Booking.java            # Réservation (id, dates, prix total)
                    │   ├── service/
                    │   │   ├── DataStore.java          # Stockage central en mémoire (ArrayList)
                    │   │   ├── AuthService.java        # Connexion & inscription
                    │   │   ├── RoomService.java        # CRUD chambres
                    │   │   └── BookingService.java     # Réservations, annulations, factures
                    │   └── ui/
                    │       ├── ConsoleHelper.java      # Utilitaires console (saisies, couleurs)
                    │       ├── AdminMenu.java          # Interface administrateur
                    │       └── ClientMenu.java         # Interface client
└── out/                            # Fichiers compilés (.class) — ne pas modifier
```

---

## Prérequis

- **Java JDK 11** ou supérieur
- Vérifiez votre installation :

```bash
java -version
javac -version
```

## Installation & Lancement

### 1. Cloner / télécharger le projet

Placez-vous dans le dossier du projet :

```bash
cd hotel
```

### 2. Créer le dossier de compilation

```bash
# Linux / macOS
mkdir -p out

# Windows (PowerShell)
mkdir out
```

### 3. Compiler

```bash
javac -d out -sourcepath src src/Main.java src/model/*.java src/service/*.java src/ui/*.java
```

### 4. Lancer

```bash
java -cp out Main
```

---

## Comptes par défaut

| Login | Mot de passe | Rôle |
|-------|-------------|------|
| `admin` | `admin` | Administrateur |
| `alice` | `alice123` | Client |
| `bob` | `bob123` | Client |

> Les mots de passe sont stockés sous forme hachée (`String.hashCode()` en hexadécimal).

---

## Menus de l'application

```
=== Système de Réservation d'Hôtel ===
1. Connexion
2. Inscription
3. Quitter
```

```
=== Menu Administrateur ===         =====  Menu Client  =====
1. Gérer les chambres               1. Consulter les chambres disponibles
2. Gérer les réservations           2. Rechercher une chambre
3. Voir tous les utilisateurs       3. Réserver une chambre
4. Déconnexion                      4. Voir mes réservations
                                    5. Annuler une réservation
                                    6. Voir une facture
                                    7. Déconnexion
```

---

## Fonctionnalités

### Authentification
- Connexion admin et client
- Inscription d'un nouveau client
- Mots de passe hachés

### Gestion des chambres *(Admin)*
- Ajouter / modifier / supprimer une chambre
- Types disponibles : `SIMPLE`, `DOUBLE`, `SUITE`
- Statuts : `DISPONIBLE`, `OCCUPEE`

### Réservations *(Client)*
- Consulter les chambres disponibles
- Recherche par type et/ou prix maximum
- Réserver avec vérification des chevauchements de dates
- Annuler une réservation
- Générer et afficher une facture

### Administration des réservations *(Admin)*
- Voir toutes les réservations
- Historique par client
- Forcer l'annulation d'une réservation

---

## Stockage des données

Les données sont conservées **en mémoire** pendant l'exécution via la classe `DataStore`.  
Aucun fichier ni base de données n'est utilisé.

| Données | Collection Java |
|---------|----------------|
| Utilisateurs | `List<User>` |
| Chambres | `List<Room>` |
| Réservations | `List<Booking>` |

> Les données sont réinitialisées à chaque redémarrage du programme.

---

## Architecture

Le projet suit une architecture **en couches** :

```
┌─────────────────────────────┐
│         ui/  (Vue)          │  ← Menus, affichage, saisies
├─────────────────────────────┤
│      service/  (Logique)    │  ← Règles métier, validations
├─────────────────────────────┤
│       model/  (Données)     │  ← Entités : User, Room, Booking
└─────────────────────────────┘
```

---

## Extensions bonus (optionnel)

- [ ] Génération de facture en fichier texte
- [ ] Tarifs saisonniers (haute / basse saison)
- [ ] Recherche avancée (prix min/max)
- [ ] Gestion de paiement fictif

---

## Licence

Projet académique — Usage éducatif uniquement.
