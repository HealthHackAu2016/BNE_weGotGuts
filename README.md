# BNE_weGotGuts

This repository houses the code and solution developed during the HealthhackAU 2016 event.

## Repository Structure

### Android Application

This application is stored in the `android` folder in this repo.

### API

This application is stored in the `api` folder in this repo.
This a a Lumen (PHP) based API which requires:
  - PHP 5.6+
  - Composer

*We ran this using Apache on an Ubuntu server*

### Database

The database is a MySQL database, with SQL scripts stored in the `database` folder.

### Web (Patient Visualisations)

The patient-focussed web interface is stored in the `web` directory.

This is based on the Hackathon Starter (https://github.com/sahat/hackathon-starter)
It is a NodeJS-based web application and requires:
  - NodeJS
  - MongoDB

*Additional requirements and setup information is available in the  README.md file in the `web` folder*

### Qlik Sense Desktop (Clinician Visualisations)

The clinician-focussed Qlik Sense BI insights platform has its configuration/settings file stored in the `clinicial_visualisation` folder.

### Data Sources

Source data and problem statement and information from the problem owner has been stored in the `data_sources` folder.

## “We’ve Got Guts” Team Members:
 - Mark Promnitz
 - Alex Chaourov      
 - Reuben Peterkim
 - Lisa Stokes
 - Komal Bandi
 - Jake Begun

## Final Thoughts

You will likely find references to wevegotguts.com thoughout this repo. Yes, we registered a domain name :)  

Endpoints we setup were:

 - api.wevegotguts.com
 - www.wevegotguts.com
 - wevegotguts.com

We also used https://letsencrypt.org/ for the HTTPS certificates! 


