void insertionSort(int n, int table[], bool comp);
void mergeSortD(int table[], int left, int right);
void mergeSortI(int table[], int left, int right);
void mergeD(int table[], int left, int mid, int right);
void mergeI(int table[], int left, int mid, int right);
void quickSortI(int table[], int left, int right);
void quickSortD(int table[], int left, int right);
int partitionD(int table[], int left, int right);
void swape(int table[], int i, int j);
void swaper(int* a, int* b);
void swaperr(int* a, int* b);
int partI(int* table, int left, int right, int* l_pivot);
int partD(int* table, int left, int right, int* l_pivot);
void dualPivotSortI(int* table, int left, int right);
void dualPivotSortD(int* table, int left, int right);
void countSortI(int array[], int n, int e);
void radixSortI(int array[], int n);
void countSortD(int array[], int n, int e);
void radixSortD(int array[], int n);
int getMax(int array[], int n);