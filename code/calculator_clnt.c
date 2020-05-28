/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include <memory.h> /* for memset */
#include "calculator.h"

/* Default timeout can be changed using clnt_control() */
static struct timeval TIMEOUT = { 25, 0 };

struct res_nums *
average_1(data *argp, CLIENT *clnt)
{
	static struct res_nums clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call (clnt, Average,
		(xdrproc_t) xdr_data, (caddr_t) argp,
		(xdrproc_t) xdr_res_nums, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}

struct res_nums *
maxmin_1(data *argp, CLIENT *clnt)
{
	static struct res_nums clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call (clnt, MaxMin,
		(xdrproc_t) xdr_data, (caddr_t) argp,
		(xdrproc_t) xdr_res_nums, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}

struct res_nums *
vectorproduct_1(data *argp, CLIENT *clnt)
{
	static struct res_nums clnt_res;

	memset((char *)&clnt_res, 0, sizeof(clnt_res));
	if (clnt_call (clnt, VectorProduct,
		(xdrproc_t) xdr_data, (caddr_t) argp,
		(xdrproc_t) xdr_res_nums, (caddr_t) &clnt_res,
		TIMEOUT) != RPC_SUCCESS) {
		return (NULL);
	}
	return (&clnt_res);
}
