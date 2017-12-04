#!/bin/bash

for seedN in {1..50}
do
	java -cp "./src:./lib/*" -Xms500m -Xss8M Solver -inst ./Data/star2.graph -alg LS2 -seed $seedN -time 120

done
