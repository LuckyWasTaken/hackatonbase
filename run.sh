#!/bin/bash

I=0 
while [ 1 ]; do 
I=$(( I + 1)); 

echo "$I"

curl "http://localhost:8080/tv/on"
curl "http://localhost:8080/vent/on"
curl "http://localhost:8080/hv/on"
curl "http://localhost:8080/microwave/on"
curl "http://localhost:8080/fridge/on"

sleep 2

curl "http://localhost:8080/tv/off"
curl "http://localhost:8080/vent/on"
curl "http://localhost:8080/hv/on"

sleep 2

curl "http://localhost:8080/tv/off"
curl "http://localhost:8080/vent/off"
curl "http://localhost:8080/microwave/off"

sleep 2

curl "http://localhost:8080/tv/on"
curl "http://localhost:8080/vent/on"
curl "http://localhost:8080/hv/off"
curl "http://localhost:8080/fridge/off"

sleep 2

done 
