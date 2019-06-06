# Hair Salon.
#### This is a java application that allows the user to record a sighting of an animal. 
#### By **Phil Kipkemboi**
## Description
Once the application runs, the user can add, edit or delete animal, ranger and location details then use the details 
to record animal sightings with each sighting showing the animal name, location name, animal type, date recorded and 
ranger name.

## BDD
| Input           | Output            |
|-----------------|-------------------|
| Animal details | creates a animal |
| Location details  | creates a location  |
| Ranger details | creates a ranger |
| Sighting details  | creates a sighting  |
| edit an animal   | updates an animal  |
| edit a location  | updates a location |
| edit a ranger   | updates a ranger  |

## User Stories
* As a user I should be able to view the sightings.
* As a user I should be add a new sighting.
* As a user I should be able to view animals.
* As a user I should be add, edit and delete animals.
* As a user I should be able to view rangers.
* As a user I should be add, edit and delete rangers.
* As a user I should be able to view locations.
* As a user I should be add, edit and delete locations.


### Setup
* To run the application, first install the java development kit from `https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html`
* Install gradle.
* Next clone the project using `$ git clone https://github.com/verisence/wildlife-tracker` to your preferred folder. 
Checkout to the folder.
* Install Posstgresql using 
`sudo apt-get update 
 sudo apt-get install postgresql postgresql-contrib libpq-dev`
 run
 `sudo -u postgres createuser --superuser $USER`
* Restore the database using
`$ psql name_of_database < schema.sql`
* In the command prompt, navigate to cloned folder and use the following command to run the app `$ gradle run`
* Open the browser and enter the address `localhost:4567`

## Technologies used
* Java.
* Gradle(for unit testing).
* Bootstrap.
* Spark.
* Postgresql
* Material Design Bootstrap.

## License
MIT License

Copyright (c) 2019 Phil Kipkemboi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.