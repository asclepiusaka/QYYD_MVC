# README
This is a group project for CSE6140 Computational Science and Engineering Algorithm, for solving Minimum Vertex Cover problem.
## Implementation
Four Algorithms are implemented

  * Construction Heuristics with Approximation guarantees
  * Exact algorithm using BnB
  * Local Search using Hill Climbing strategy
  * Local Search using Simulated Annealing strategy

## Run program
in this directory, open a shell and use the following command:

``
java -cp "./bin:./lib/*" -Xms500m -Xss8M Solver -inst ./Data/$graph -alg BnB -time 600 -seed 12
``

-alg and -inst are necessary, if -time or -seed is not provided, the cutoff time value will be default to be 600, seed will be default to be 1.

the order of the latter 4 parameters is not specified.

## dependency:
Several extra library is used and included in /lib.  ``commons-cli-1.4`` for command parsing.
``GLPKSolverPack`` and ``SCPSolver`` for LP problem solving.
