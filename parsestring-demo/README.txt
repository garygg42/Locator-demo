Starting the Service
--------------------
Add maven URL into karaf:
	features:addurl mvn:org.examples.parsestring/features/1.1.0/xml

Install example feature in container:
	features:install parsestring-service
for example service

	features:install parsestring-client
for example client

Starting the client
-------------------
in container execute
	parsestring:parse <arg0>
for client, which sends string to parsestring service

	parsestring:loop
for client, which sends random strings in infinity loop to parsestring service