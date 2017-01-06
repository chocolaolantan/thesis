/****************************************
  *File name    : hangalian.c
  *Date         : 2016-12-10
  *Ubdate date  : 2016-12-23
  *Discliption  :
****************************************/
#include <stdio.h>
#include <string.h>
#include <math.h>
#include <malloc.h>

#include "lib/hungarian.h"

const long long max_size = 2000;         // max length of strings
const long long max_w = 50;              // max length of vocabulary entries

int main(int argc, char **argv) {
  FILE *f;
  char st1[max_size];       // target word
  char bestw[10][N][max_size];
  char file_name[max_size], st[10][max_size];
  float dist, len, bestd[10][N], vec[10][max_size], tmp[max_size], tgt[10][max_size], kb[10][N][max_size], mrx[N][N];
  long long words, size, a, b, c, d, e, g, cn, bi[50], besti[50][N];
  float *M;
  char *vocab;

  int y[N];

  if (argc < 2) {
    printf("Usage: ./hangalian <FILE>\nwhere FILE contains word projections in the BINARY FORMAT\n");
    return 0;
  }
  strcpy(file_name, argv[1]);
  f = fopen(file_name, "rb");
  if (f == NULL) {
    printf("Input file not found\n");
    return -1;
  }
  fscanf(f, "%lld", &words);    // vocabraly number
  fscanf(f, "%lld", &size);     // dimention
  printf("words : %lld\nsize : %lld\n", words, size);
  vocab = (char *)malloc((long long)words * max_w * sizeof(char));    // vocab list
  M = (float *)malloc((long long)words * (long long)size * sizeof(float));  // words vector
  if (M == NULL) {
    printf("Cannot allocate memory: %lld MB    %lld  %lld\n", (long long)words * size * sizeof(float) / 1048576, words, size);
    return -1;
  }
  for (b = 0; b < words; b++) {
    a = 0;
    while (1) {
      vocab[b * max_w + a] = fgetc(f);
      if (feof(f) || (vocab[b * max_w + a] == ' ')) break;
      if ((a < max_w) && (vocab[b * max_w + a] != '\n')) a++;
    }
    vocab[b * max_w + a] = 0;
    for (a = 0; a < size; a++) fread(&M[a + b * size], sizeof(float), 1, f);
    len = 0;
    for (a = 0; a < size; a++) len += M[a + b * size] * M[a + b * size];
    len = sqrt(len);
    for (a = 0; a < size; a++) M[a + b * size] /= len;
  }
  fclose(f);
  printf("Close %s\n", file_name);
  while (1) {
    for (b = 0; b < 10; b++) {
      for (a = 0; a < N; a++) bestd[b][a] = 0;
      for (a = 0; a < N; a++) bestw[b][a][0] = '\n';
    }
    printf("Enter two words (EXIT to break): ");
    a = 0;
    while (1) {
      st1[a] = fgetc(stdin);
      if ((st1[a] == '\n') || (a >= max_size - 1)) {
	st1[a] = 0;
	break;
      }
      a++;
    }
    if (!strcmp(st1, "EXIT")) break;
    cn = 0;
    b = 0;
    c = 0;
    while (1) {
      st[cn][b] = st1[c];
      b++;
      c++;
      st[cn][b] = 0;
      if (st1[c] == 0) break;
      if (st1[c] == ' ') {
        cn++;
        b = 0;
        c++;
      }
    }
    cn++;
    if (cn < 2) {
      printf("Only %lld words were entered.. two words are needed at the input to perform the calculation\n", cn);
      continue;
    }
    for (a = 0; a < cn; a++) {
      for (b = 0; b < words; b++) if (!strcmp(&vocab[b * max_w], st[a])) break;
      if (b == words) b = -1;
      bi[a] = b;
      printf("\nWord: %s  Position in vocabulary: %lld\n", st[a], bi[a]);
      if (b == -1) {
        printf("Out of dictionary word!\n");
        break;
      }
    }
    if (b == -1) continue;

    /*********************2つの対象語の近傍単語取得*******************************/
    printf("----------words A-------------------------words B--------------\n");
    for (b = 0; b < cn; b++) {
      for (a = 0; a < size; a++) {
        vec[b][a] = 0;
        tgt[b][a] = 0;
      }

      if (bi[b] == -1) continue;
      for (a = 0; a < size; a++) {
        vec[b][a] += M[a + bi[b] * size];
        tgt[b][a] = M[a + bi[b] * size];
      }

      len = 0;
      for (a = 0; a < size; a++) len += vec[b][a] * vec[b][a];
      len = sqrt(len);
      for (a = 0; a < size; a++) vec[b][a] /= len;
      for (a = 0; a < N-1; a++) bestd[b][a] = -1;
      for (a = 0; a < N-1; a++) besti[b][a] = -1;
      for (a = 0; a < N-1; a++) bestw[b][a][0] = 0;

      for (c = 0; c < words; c++) {
        a = 0;
        for (d = 0; d < cn; d++) if (bi[d] == c) a = -1;
        if (a == -1) continue;
        dist = 0;
        for (a = 0; a < size; a++) dist += vec[b][a] * M[a + c * size];
        for (a = 0; a < N-1; a++) {
          if (dist > bestd[b][a]) {
            for (e = N - 2; e > a; e--) {
              bestd[b][e] = bestd[b][e - 1];
              besti[b][e] = besti[b][e - 1];
	      for (g = 0; g < max_size; g++)
		kb[b][e][g] = kb[b][e-1][g];
              strcpy(bestw[b][e], bestw[b][e - 1]);
            }
            bestd[b][a] = dist;
            besti[b][a] = c;
            for (e = 0; e < size; e++) kb[b][a][e] = M[e + c * size];
            strcpy(bestw[b][a], &vocab[c * max_w]);
            break;
          }
        }
      }
    }
    for (a = 0; a < N; a++)
      printf("\t\t%s\t\t%s\n", bestw[0][a], bestw[1][a]);
    /*****************************************************************/

    /********************Normalization********************************/
    for (b = 0; b < cn; b++) {
        for (a = 0; a < size; a++) tmp[a] = vec[b][a];
        for (a = 0; a < size; a++) {
          for (c = 0; c < N-1; c++) {
            tmp[a] += kb[b][c][a];
          }
          tmp[a] /= N;
        }
      for (a = 0; a < size; a++) {
        tgt[b][a] -= tmp[a];
        for (c = 0; c < N-1; c++) {
          kb[b][c][a] -= tmp[a];
        }
      }
    }
    /*****************************************************************/
    /******************Get Matrix*************************************/
    for (b = 0; b < cn; b++) {
      for (a = N-1; a >= 0; a--) {
	for (g = 0; g < max_size; g++)
	  kb[b][a][g] = kb[b][a-1][g];
      }
      for (a = 0; a < size; a++) {
        kb[b][0][a] = tgt[b][a];
      }
    }
    for (a = 0; a < N; a++) {
      for (b = 0; b < N; b++) {
        mrx[a][b] = 0;
      }
    }
    for (a = 0; a < N; a++) {
      for (b = 0; b < N; b++) {
        for(c = 0; c < size; c++) {
          mrx[a][b] += kb[0][a][c] * kb[1][b][c];
        }
        mrx[a][b] = mrx[a][b] * -1;
      }
    }
    for (a = 0; a < N; a++) {
      for (b = 0; b < N; b++)
        printf("%f  ", mrx[a][b]);
      printf("\n");
    }
    /**********Hungarian Method***************************************/
    printf("----------------------Hangalian--------------------------------\n");
    hungarian(mrx, y);
    printf("\n----------words A-------------------------words B--------------\n");
    for (a = 0; a < N; a++) printf("\t\t%s\t----\t%s\n", bestw[0][a], bestw[1][y[a]]);
  }
  return 0;
}
