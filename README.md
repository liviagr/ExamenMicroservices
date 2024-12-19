# Présentation

## Api-Gateway

Permet de faire la passerelle entre les différents services.
Implémente Hystrix pour gérer les défaillances éventuelles des services.

API documentée avec swagger.


## PatientService

Permet de consulter, créer, mettre à jour et supprimer un patient.
API et modèle documentés avec swagger.

## PractitionerService

Permet de consulter, créer, mettre à jour et supprimer un practitioner.
API et modèle documentés avec swagger.

## MedicalRecord

Permet de consulter un dossier en fonction de son id, de celui du patient ou du practitioner.
Possibiliter d'en créer, modifier ou supprimer.
API et modèle documentés avec swagger.

# Exécution

Chaque service doit être lancé et s'enregistrer sur le serveur eureka.

L'APi est disponible avec le service API-Gateway, qui permet d'accéder à toutes les routes des autres services.
La liste est visible depuis la documentation swagger.

# Docker et Kubernetes

Chacun des services est empaqueté dans son propre conteneur Docker (fichier dockerfile).

Le déploiement peut être fait sur kubernetes (fichier deployement.yml).
