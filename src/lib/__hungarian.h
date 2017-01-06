#ifndef __HUNGARIAN_H
#define __HUNGARIAN_H

#define N 5
#define INF 100000000     // just infinity

extern float max(float a, float b);
extern float min(float a, float b);


extern float cost[N][N];          // cost matrix
extern int n, max_match;      // n workers and n jobs
extern float lx[N], ly[N];    // labels of X and Y parts
extern int xy[N];           // xy[x] - vertex that is matched with x,
extern int yx[N];           // yx[y] - vertex that is matched with y
extern bool S[N], T[N];       // sets S and T in algorithm
extern float slack[N];        // as in the algorithm description
extern int slackx[N];       // slackx[y] such a vertex, that
                       // l(slackx[y]) + l(y) - w(slackx[y],y) = slack[y]
extern int prev[N];         // array for memorizing alternating paths

extern void init_labels();
extern void augment();
extern void add_to_tree(float x, float prevx);

#endif
