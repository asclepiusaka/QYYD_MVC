#!/bin/bash


graphFiles=`ls ./Data/ | grep .graph`

for graph in $graphFiles
do
	filename=`echo $graph | cut -d'.' -f1`
	echo $graph $filename
	java -cp "./bin:./lib/*" -Xms500m -Xss8M Solver -inst ./Data/$graph -alg BnB -time 600 -seed 233

done

for graph in $graphFiles
do
	filename=`echo $graph | cut -d'.' -f1`
	echo $graph $filename
	java -cp "./bin:./lib/*" -Xms500m -Xss8M Solver -inst ./Data/$graph -alg Approx  -time 600 -seed 233

done


for graph in $graphFiles
do
	filename=`echo $graph | cut -d'.' -f1`
	echo $graph $filename
	java -cp "./bin:./lib/*" -Xms500m -Xss8M Solver -inst ./Data/$graph -alg LS1  -time 600 -seed 233

done


for graph in $graphFiles
do
	filename=`echo $graph | cut -d'.' -f1`
	echo $graph $filename
	java -cp "./bin:./lib/*" -Xms500m -Xss8M Solver -inst ./Data/$graph -alg LS2  -time 600 -seed 233

done
