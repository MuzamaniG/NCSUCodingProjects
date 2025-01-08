/**
 * @file trig.c
 * @author Muzamani Gausi (mhgausi)
 * This program reads angles and approximates 
 * their sines, cosines, and tangents using the 
 * Taylor series
 */
#include <stdio.h>
#include <stdlib.h>

/** Approximation of PI.  For now, we're using our own constant for
    this. There's also one provided by the gnu math library, once we
    learn about that. */
#define PI 3.14159265358979323846

/** Max difference between sines and cosines */
#define PRECISION 0.000001

/**
 * Gets an angle from the input and exits for incorrect range
 * @return the angle inputted
*/
double getAngle()
{
  double angle;
  if (scanf("%lf", &angle) != 1 || angle < -PI || angle > PI) {
    printf("Invalid input\n");
    exit(1);
  }
  return angle;
}

/**
 * Calculates the absolute value of the difference
 * between a and b
 * @param a number being subtracted from
 * @param b amount being subtracted 
 */
double difference(double a, double b)
{
  if (a > b) {
    return a - b;
  } else {
    return b - a;
  }
}

/**
 * Prints the table's two header lines
 */
void tableHeader()
{
  printf("terms |         sin |         cos |         tan \n");
  printf("------+-------------+-------------+-------------\n");
}

/** 
 * Prints a row of the table with the number of terms used
 * for the Taylor series approximation, sine, cosine, and tangent
 * @param terms number of terms in Taylor series approximation
 * @param s sine
 * @param c cosine
 * @param t tangent
 */
void tableRow(int terms, double s, double c, double t)
{
  printf("%5d | %11.7f | %11.7f | %11.7f\n", terms, s, c, t);
}

/**
 * Builds the table with the given sin, cosine,
 * and tangent values
 */
int main()
{
  double angle = getAngle();
  tableHeader();
  double sin1 = 0.0, cos1 = 1.0, tan1 = 0.0;
  tableRow(1, sin1, cos1, tan1);
  double sin2 = angle, cos2 = 1.0, tan2 = angle;
  tableRow(2, sin2, cos2, tan2);
  int term = 3;
  double exponent, factorial, termValue;

  do {
    sin1 = sin2;
    cos1 = cos2;
    tan1 = tan2;
    exponent = angle;
    factorial = 1.0;
    for (int i = 2; i < term; i++) {
      factorial *= i;
      exponent *= angle;
    }
    termValue = exponent / factorial;
      switch (term % 4) {
        case 0: 
          sin2 = sin1 - termValue; 
          break;
        case 1: 
          cos2 = cos1 + termValue; 
          break;
        case 2: 
          sin2 = sin1 + termValue; 
          break;
        case 3: 
          cos2 = cos1 - termValue; 
          break;
      }
      tan2 = sin2 / cos2;
      tableRow(term, sin2, cos2, tan2);
      term++;
  }
  while (difference(sin1, sin2) > PRECISION || difference(cos1, cos2) > PRECISION);
  return 0;
}
