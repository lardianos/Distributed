/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

//Calculator Server RPC

#include "calculator.h"

struct res_nums *
average_1_svc(data *argp, struct svc_req *rqstp)
{
	static struct res_nums  result;
	float average=0;
	int n = argp->Y.Y_len;
	for (int i = 0; i < n; i++)	{
		average += argp->Y.Y_val[i];
	}
	average /= n;
	printf("%f\n",average);	
	printf("Server func 1\n");
	result.a_res=average;
	return &result;
}

struct res_nums *
maxmin_1_svc(data *argp, struct svc_req *rqstp)
{
	static struct res_nums  result;
	int n = argp->Y.Y_len;
	int max=argp->Y.Y_val[0],min=argp->Y.Y_val[0];
	for (int i = 0; i < n; i++){
		if (argp->Y.Y_val[i] > max) {
			max = argp->Y.Y_val[i];
		}else if (argp->Y.Y_val[i] < min){
			min = argp->Y.Y_val[i];
		}
	}
	printf("Server func 2\n");
	printf("max %d  min %d\n",max,min);
	result.maxmin[0]=max;
	result.maxmin[1]=min;
	return &result;
}

struct res_nums *
vectorproduct_1_svc(data *argp, struct svc_req *rqstp)
{
	static struct res_nums  result;
	int n = argp->Y.Y_len;
	float a = argp->a;
	result.Y.Y_len = n;
	result.Y.Y_val = (float *) malloc(n*sizeof(float));
	for (int i = 0; i < n; i++){
		result.Y.Y_val[i] = (float) a * (float) argp->Y.Y_val[i];
		printf("%f\n",result.Y.Y_val[i] );
	}

	printf("Server func 3\n");
	return &result;
}
