#!/bin/bash

I=0 
while [ 1 ]; do 
I=$(( I + 1)); 

echo "$I"

curl "http://localhost:8080/tv/on"
curl "http://localhost:8080/vent/off"
curl "http://localhost:8080/hv/off"

sleep 1

curl "http://localhost:8080/tv/off"
curl "http://localhost:8080/vent/on"
curl "http://localhost:8080/hv/on"

sleep 1

curl "http://localhost:8080/tv/off"
curl "http://localhost:8080/vent/off"

sleep 1

curl "http://localhost:8080/tv/on"
curl "http://localhost:8080/vent/on"
curl "http://localhost:8080/hv/off"

sleep 1

done 
