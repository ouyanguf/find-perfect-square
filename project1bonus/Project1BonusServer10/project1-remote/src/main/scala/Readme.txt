COP5615 - Distributed Operating Systems Project 1 -ReadMe
---------------

By
XiangXu 16953764

This is project 1 for COP5615 distributed operating systems.

The result of running scala project1 1,000,000 4 is:
(no solution)
For scala project1 1,000,000 4, the running time and real/(usr+sys) ratio for different values of work unit are given in the table below
 Work unit 	Real/(usr + sys)	Running time
------------------------------------------------------------
10              0.55                    5.45 sec
100             0.60                    2.56 sec
1,000           0.72                    1.44 sec
10,000          0.78                    1.23 sec
100,000         0.80                    1.51 sec
This table we can obtain the ideal work unit for the best running time will be 10,000( that is 100  actors activated). But the parallelism effect is not significant in 1,000,000 data size, So I want to try a bigger data size 100,000,000.
Running time and real/(usr+ sys) ratio for scala project1 100,000,000 for different values of work unit are given in the table below.
To determine the ideal work unit for project1.scala, we tested the running time and the ratio of real/(usr+sys) for different number of actors. The ideal work unit may be the one that utilizes all cores and at the same time gives a lower real running time. For this analysis we used the number 100,000,000 since the communication time is relatively smaller. The values we got were as follows:

Work unit 	Real/(usr + sys)	Running time
------------------------------------------------------------
1,000           0.53                    8.52 sec
10,000          0.55                    4.94 sec
100,000         0.57                    3.77 sec
1,000,000       0.59                    3.47 sec
10,000,000      0.59                    3.09 sec


This table shows that the multithreading nature of our program is more or less the same for all cases - the program utilises both cores all the time. However, the running time is the least for work units 1,000,000 and 10,000,000, - ie for 10 to 100 actors. The reason for such behavior is that if the work unit is too less, then all actors are done soon enough but there is more overhead in message passing. On the other hand, if the work unit is too much then the bottleneck lies in the individual work of the actor. The above numbers give a balance between the two cases.

The largest problem we managed to solve was:
N = 2,000,000,000 using 100 actors since the input String is out of bound

N                 Real/(usr+sys)          Running Time 
----------------------------------------------------------
2,000,000,000      0.516                 45.69 sec
1,000,000,000      0.523                 23.03 sec


BONUS:
By Xiang Xu 16953764, Yang Ou 20126327
We solve the problem on 8 remote machines:
lin114-(01,02,03,04,06,07,08,09) are the clients, while lin114-10 is the Server
8 file fonders for clients and 1 file fonders for Server, which are all based on scala sbt.
The .conf file need to change when it is on different machines.
The largest problem we managed to solve on 8 remote machines:
lin114-(01,02,03,04,06,07,08,09) are the clients, while lin114-10 is the Server
As large as possible until it reaches the maximum Long value allowed by the scala runtime. Below are some of the largest experiment results:

N                   Running Time
-------------------------------------------------------------
2,000,000,000      12.12sec
1,000,000,000      5.31sec

This is about 4 times faster than the single machine version.