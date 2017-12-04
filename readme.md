# README
This is a group project for solving Minimum Vertex Cover problem.
## Implementation
Four Algorithms are implemented
  * Construction Heuristics with Approximation guarantees
  * Exact algorithm using BnB
  * Local Search using Hill Climbing policy
  * Local Search using Simulated Annealing policy

## Run program
in this directory, input

``
java -cp "./bin:./lib/*" -Xms500m -Xss8M Solver -inst ./Data/$graph -alg BnB -time 600 -seed 12
``
-alg and -inst are necessary, if -time or -ssed is not provided, the cutoff time value will be default to be 600, seed will be default to be 1.

the order of the latter 4 parameters is not fixed. 

## dependency:
several extra library is used and included in /lib.  ``commons-cli-1.4`` for command parsing.
``GLPKSolverPack`` and ``SCPSolver`` for LP problem solving.

## some assumption for coding
1. for Vertex index id, let's use index start from 1,leave the first slot in array empty (a[0]);
2. in the current parsing manner, for each Vertex, we have the EdgeList and VertexList corresponding to each other, in other word, Vertex v connect to Vertex w = v.getAdjVertexList.get(i) through Edge e = v.getAdjEdgeList.get(i). 