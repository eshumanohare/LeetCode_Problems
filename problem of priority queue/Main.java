import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/** Class for buffered reading int and double values */
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
    
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}

class node
{
    long work, boredom, halfwork;

    node(long work, long boredom)
    {
        this.halfwork = (work + 1)/2;
        this.work = work;
        this.boredom = boredom;
    }

    node()
    {
        this.halfwork = -1;
        this.work = -1;
        this.boredom = -1;
    }
}

class MaxHeap
{
    int size;
    int M = (int) 1e5 + 1;
    node[] heap; 

    MaxHeap()
    {
        size = 0;
        heap = new node[4*M];
        for (int i = 0; i < 4*M; ++i)
        {
            heap[i] = new node();
        }
    }

    private int returnParent(int node)
    {
        return (node-1)/2;
    }

    private boolean compare(node n1, node n2)
    {
        if (n1.halfwork > n2.halfwork)
        {
            return true;
        }
        if (n1.halfwork == n2.halfwork && n1.boredom < n2.boredom)
        {
            return true;
        }
        return false;
    }

    public void insert(long work, long boredom)
    {
        heap[size] = new node(work, boredom);
        int curr = size;
        ++size;
        int par = returnParent(curr); 
        while (par != curr)
        {
            if (!compare(heap[curr], heap[par]))
            {
                break;
            }
            node temp = heap[curr];
            heap[curr] = heap[par];
            heap[par] = temp;
            int x = returnParent(par);
            curr = par;
            par = x;
        }
    }

    private void heapify(int curr)
    {
        int left = curr*2 + 1;
        int right = curr*2 + 2;
        int child = left;
        if (compare(heap[right], heap[left]))
        {
            child = right;
        }
        if (!compare(heap[curr], heap[child]))
        {
            node temp = heap[curr];
            heap[curr] = heap[child];
            heap[child] = temp;
            heapify(child);
        }
        return;
    }

    public node pop()
    {
        --size;
        node root = heap[0];
        heap[0] = heap[size];
        heap[size] = new node();
        heapify(0);
        return root;
    }

    public boolean isEmpty()
    {
        if (size == 0)
        {
            return true;
        }
        return false;
    }
}

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Reader.init(System.in);
        int t = Reader.nextInt();
        for (int testcase = 0; testcase < t; ++testcase)
        {
            int n = Reader.nextInt();
            int k = Reader.nextInt();
            long[] w = new long[n];
            long[] b = new long[n];
            long work_left = 0;
            MaxHeap heap = new MaxHeap();
            for (int i = 0; i < n; ++i)
            {
                w[i] = (long) Reader.nextInt();
                work_left += w[i];
            }
            for (int i = 0; i < n; ++i)
            {
                b[i] = (long) Reader.nextInt();
                heap.insert(w[i], b[i]);
            }
            long total_boredom = 0;
            for (int i = 0; i < k; ++i)
            {
                if (heap.isEmpty())
                {
                    break;
                }
                node x = heap.pop();
                long work_done = x.halfwork;
                work_left -= work_done;
                total_boredom += (x.boredom * work_done);
                x.work /= 2;
                if (x.work > 0)
                {
                    heap.insert(x.work, x.boredom);
                }
            }
            System.out.println(work_left + " " + total_boredom);
        }
    }
}