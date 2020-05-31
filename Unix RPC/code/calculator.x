struct data {
	float a;
	int Y<>;
	int n;	
};

struct res_nums {
	float a_res;
	int maxmin[2];	
	float Y<>;
};

program CALC_PROG {
		version CALC_VERS {
			struct res_nums Average(data) = 1;
			struct res_nums MaxMin(data) = 2;
			struct res_nums VectorProduct(data) = 3;
	} = 1;
} = 0x23451111;
