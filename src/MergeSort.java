public class MergeSort {
    public int[][] mergesort(int[][] A, int p, int q) {
        if (p < q) {
            int k = (p + q) / 2;
            mergesort(A, p, k);
            mergesort(A, k + 1, q);
            merge(A, p, k, q);
        }
        return A;
    }

    private void merge(int[][] a, int p, int k, int q) {
        int i = p;
        int j = k + 1;
        int n = 0;

        int[][] B = new int[q - p + 1][0];

        while (i <= k && j <= q)
            B[n++][0] = a[i][0] < a[j][0] ? a[i++][0] : a[j++][0];
        while (i <= k)
            B[n++][0] = a[i++][0];
        while (j <= q)
            B[n++][0] = a[j++][0];

        n = 0;
        for (int m = p; m <= q; m++) {
            a[m][0] = B[n++][0];
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[][] A = {{37, 10, 22,}, {30, 35, 13, 25, 24}};
        for (int[] j : A) {
            System.out.printf("%d", j);
        }

        int[][] B = mergeSort.mergesort(A, 0, A.length - 1);

        System.out.println();
        for (int[] j : B) {
            System.out.printf("%d ", j);
        }
    }
}