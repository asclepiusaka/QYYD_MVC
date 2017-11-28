#!/bin/bash


graphFiles=`ls ./Data/ | grep .graph`

for graph in $graphFiles
do
	filename=`echo $graph | cut -d'.' -f1`
	echo $graph $filename
	java -cp "./bin:./lib/*" -Xms500m -Xss8M Solver -inst ./Data/$graph -alg Approx

done

for graph in $graphFiles
do
	filename=`echo $graph | cut -d'.' -f1`
	echo $graph $filename
	java -cp "./bin:./lib/*" -Xms500m -Xss8M Solver -inst ./Data/$graph -alg BnB

done
