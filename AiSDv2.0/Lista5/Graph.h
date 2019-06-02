#ifndef GRAPH_H
#define GRAPH_H

class Graph {
	private:
		int ** v1;
		int ** v2;
		int my_k;
		int my_i;
	public:
		Graph(int k, int i);
		~Graph();
		void program();
	protected:
		int my_pow(int i);
		int * my_rand(int max, int i);
		void createV2(int k_2, int i);
};


#endif