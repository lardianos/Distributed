/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include "calculator.h"

bool_t
xdr_data (XDR *xdrs, data *objp)
{
	register int32_t *buf;

	 if (!xdr_float (xdrs, &objp->a))
		 return FALSE;
	 if (!xdr_array (xdrs, (char **)&objp->Y.Y_val, (u_int *) &objp->Y.Y_len, ~0,
		sizeof (int), (xdrproc_t) xdr_int))
		 return FALSE;
	 if (!xdr_int (xdrs, &objp->n))
		 return FALSE;
	return TRUE;
}

bool_t
xdr_res_nums (XDR *xdrs, res_nums *objp)
{
	register int32_t *buf;

	int i;

	if (xdrs->x_op == XDR_ENCODE) {
		 if (!xdr_float (xdrs, &objp->a_res))
			 return FALSE;
		buf = XDR_INLINE (xdrs, ( 2 ) * BYTES_PER_XDR_UNIT);
		if (buf == NULL) {
			 if (!xdr_vector (xdrs, (char *)objp->maxmin, 2,
				sizeof (int), (xdrproc_t) xdr_int))
				 return FALSE;

		} else {
		{
			register int *genp;

			for (i = 0, genp = objp->maxmin;
				i < 2; ++i) {
				IXDR_PUT_LONG(buf, *genp++);
			}
		}
		}
		 if (!xdr_array (xdrs, (char **)&objp->Y.Y_val, (u_int *) &objp->Y.Y_len, ~0,
			sizeof (float), (xdrproc_t) xdr_float))
			 return FALSE;
		return TRUE;
	} else if (xdrs->x_op == XDR_DECODE) {
		 if (!xdr_float (xdrs, &objp->a_res))
			 return FALSE;
		buf = XDR_INLINE (xdrs, ( 2 ) * BYTES_PER_XDR_UNIT);
		if (buf == NULL) {
			 if (!xdr_vector (xdrs, (char *)objp->maxmin, 2,
				sizeof (int), (xdrproc_t) xdr_int))
				 return FALSE;

		} else {
		{
			register int *genp;

			for (i = 0, genp = objp->maxmin;
				i < 2; ++i) {
				*genp++ = IXDR_GET_LONG(buf);
			}
		}
		}
		 if (!xdr_array (xdrs, (char **)&objp->Y.Y_val, (u_int *) &objp->Y.Y_len, ~0,
			sizeof (float), (xdrproc_t) xdr_float))
			 return FALSE;
	 return TRUE;
	}

	 if (!xdr_float (xdrs, &objp->a_res))
		 return FALSE;
	 if (!xdr_vector (xdrs, (char *)objp->maxmin, 2,
		sizeof (int), (xdrproc_t) xdr_int))
		 return FALSE;
	 if (!xdr_array (xdrs, (char **)&objp->Y.Y_val, (u_int *) &objp->Y.Y_len, ~0,
		sizeof (float), (xdrproc_t) xdr_float))
		 return FALSE;
	return TRUE;
}
