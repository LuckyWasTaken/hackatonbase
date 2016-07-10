#!/bin/bash

I=0 
while [ 1 ]; do 
I=$(( I + 1)); 

echo "$I"

curl "http://localhost:8080/tv/on"

sleep 2

curl "http://localhost:8080/tv/off"

sleep 2

done 
