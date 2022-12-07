## Description :

Un transfer est un transfert d'argent d'un compte emetteur vers un compte bénéficiaire ...

**Besoin métier :** 

Ajouter un nouveau cas d'usage appelé "Deposit". Le Deposit est un dépôt d'agent cash sur un compte donné (Versement d'argent). 

Imaginez que vous allez à une agence avec un montant de 1000DH et que vous transferez ça en spécifiant le RIB souhaité.
 
L'identifiant fonctionnel d'un compte dans ce cas préçis est le RIB.  


## Assignement :

* Le code présente des anomalies de qualité (bonnes pratiques , abstraction , lisibilité ...) et des bugs . 
    * localiser le maximum 
    * Essayer d'améliorer la qualité du code.    
    * Essayer de résoudre les bugs détectés. 
* Implementer le cas d'usage `Deposit` 
  Règles de gestion : 
  - Le montant maximal que je peux déposer par opération est 10000DH

* Ajouter des tests unitaires.  

* **Nice to have** : Ajouter une couche de sécurité 

## How to use 
To build the projet you will need : 
* Java 17
* Maven

Build command : 
```
mvn clean install
```

Run command : 
```
./mvnw spring-boot:run 
## or use any prefered method (IDE , java -jar , docker .....)
```

## How to submit 
* Fork the project into your personal gitlab space .    
* Do the stuff.
* Send us the link.
