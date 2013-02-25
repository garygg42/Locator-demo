Starting the Service
--------------------
Add maven URL into karaf:
	features:addurl mvn:org.examples.parsestring/features/1.0.0/xml

Install example feature in container:
	features:install parsestring

Starting the client
-------------------
in root of this project execute 

	mvn exec:java -pl client
for simple client, which sends 25 random strings and 1 checked exception to parsestring service

	mvn exec:java -pl client-gui
for client with GUI
	
	mvn exec:java -pl client-many-inv
for client, which sends 1000 random strings to parsestring service