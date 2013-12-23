COP5615 - Distributed Operating Systems Project 1 -ReadMe
---------------

By
XiangXu 16953764

This is project 1 for COP5615 distributed operating systems.

The result of running scala project1 1,000,000 4 is:
(no solution)
 
Running time and real/(usr+ sys) ratio for scala project1 100,000,000 for different values of work unit are given in the table below.

To determine the ideal work unit for project1.scala, we tested the running time and the ratio of real/(usr+sys) for different number of actors. The ideal work unit may be the one that utilizes all cores and at the same time gives a lower real running time. For this analysis we used the number 100,000,000 since the communication time is relatively smaller. The values we got were as follows:

Work unit 	Real/(usr + sys)	Running time
------------------------------------------------------------
1,000           0.548                   6.8 sec
10,000          0.543                   4.14 sec
100,000         0.546                   3.35 sec
1,000,000       0.545                   3.11 sec
10,000,000      0.550                   3.12 sec


This table shows that the multithreading nature of our program is more or less the same for all cases - the program utilises both cores all the time. However, the running time is the least for work units 1,000,000 and 10,000,000, - ie for 10 to 100 actors. The reason for such behavior is that if the work unit is too less, then all actors are done soon enough but there is more overhead in message passing. On the other hand, if the work unit is too much then the bottleneck lies in the individual work of the actor. The above numbers give a balance between the two cases.

The largest problem we managed to solve was:
N = 20,000,000,000

N                 Real/(usr+sys)          Running Time 
----------------------------------------------------------
20,000,000,000      0.520                 6min 39.52sec
10,000,000,000      0.519                 3min 20.45sec


BONUS:

The largest problem we managed to solve on 8 remote machines:
As large as possible until it reaches the maximum Long value allowed by the scala runtime. Below are some of the largest experiment results:

N                   Running Time
-------------------------------------------------------------
20,000,000,000      2min 31.05sec
10,000,000,000      45sec

This is about 4 times faster than the single machine version.