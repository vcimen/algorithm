
    import java.util.*;
    import java.util.stream.IntStream;

    public class sumOfSubset {

        static boolean[][] subset;


        static void show(ArrayList<Integer> list)
        {
            System.out.println(list);
        }

        // A recursive function to print all subsets with the
        // help of subset[][]. cur[] stores current subset.
        static void RecursiveSubset(int Arr_range[], int i, int total, ArrayList<Integer> cur)
        {
            // If we reached end and total is non-zero. We print
            // cur[] only if arr[0] is equal to total OR subset[0][sum]
            // is true.
            if (i == 0 && total != 0 && subset[0][total])
            {
                cur.add(Arr_range[i]);
                show(cur);
                cur.clear();
                return;
            }

            // when total 0
            if (i == 0 && total == 0)
            {
                show(cur);
                cur.clear();
                return;
            }

            // After ignoring current element
            if (subset[i-1][total])
            {
                // Create a new Arraylist to store path
                ArrayList<Integer> n = new ArrayList<>();
                n.addAll(cur);
                RecursiveSubset(Arr_range, i-1, total,n);
            }

        // when total success with current element
            if (total >= Arr_range[i] && subset[i-1][total-Arr_range[i]])
            {
                cur.add(Arr_range[i]);
               RecursiveSubset(Arr_range, i-1, total-Arr_range[i], cur);
            }
        }

        // Prints all subsets of arr[0..len] with total 0.
        static void printSumOfSubsets(int arr[], int len, int total)
        {
            if (len == 0 || total < 0)
                return;

            // Total 0 should be done with 0 elements
            subset = new boolean[len][total + 1];
            for (int i=0; i<len; ++i)
            {
                subset[i][0] = true;
            }

            // Total arr[0] should be successed with one element
            if (arr[0] <= total)
                subset[0][arr[0]] = true;

            // The remaining entries in subset[][]
            for (int i = 1; i < len; ++i)
                for (int j = 0; j < total + 1; ++j)
                    subset[i][j] = (arr[i] <= j) ? (subset[i-1][j] || subset[i-1][j-arr[i]]) : subset[i - 1][j];
            if (subset[len-1][total] == false)
            {
                System.out.println("There are no subsets with" +
                        " sum "+ total);
                return;
            }

            // Let recursively traverse subset[][] to find all
            // paths from subset[len-1][sum]
            ArrayList<Integer> m = new ArrayList<>();
            RecursiveSubset(arr, len-1, total,m);
        }

        // Main method to test the program
        public static void main(String args[])
        {
            int[] Arr_range = IntStream.rangeClosed(1, 67).toArray();
            System.out.println(Arrays.toString(Arr_range));
            int len = Arr_range.length;
            int total = 67;
            printSumOfSubsets(Arr_range, len, total);
        }
    }






