Starting the Service
--------------------
Add maven URL into karaf:
	features:addurl mvn:org.examples.parsestring/features/1.1.0/xml

Install example feature in container:
	features:install parsestring

Starting the client
-------------------
in root of this project execute 
	mvn exec:java -pl client -Dcommandline.args="loop"
for client, which sends random strings in infinity loop to parsestring service

	mvn exec:java -pl client -Dcommandline.args="manual"
for client, which sends string to parsestring service