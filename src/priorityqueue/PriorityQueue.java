
package priorityqueue;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PriorityQueue {
    static int MAX_HEAP_SIZE = 100;  // max heap size
    static int heapItems[] = new int[MAX_HEAP_SIZE + 1];
    static int indexValue = 0;
    static int size = 0;
    
    // utility functions 
   // sift up 
    public static void siftUp(int heap[], int n)
    {
        // heap[1] to heap[n-1] contain a heap || 'n' = the heap size
       // sifts up the value in heap[n] so that heap[1..n] contains a heap
        int shiftItem = heap[n];
        int childNode = n;
        int parentNode = childNode / 2;
        while (parentNode > 0) {
            if (shiftItem <= heap[parentNode]) 
            {
                break;
            }
            heap[childNode] = heap[parentNode];
            childNode = parentNode;
            parentNode = childNode / 2;
        }
        heap[childNode] = shiftItem;
    }  // end of sift up function 
    // sift down 
    public static void siftDown(int key, int[] heapItems, int root, int lastIndex) 
    {
        int larger = 2 * root;
        while (larger <= lastIndex) { //while there is at least one child
            if (larger < lastIndex) // there is a right child as well;find the larger
            {
                if (heapItems[larger + 1] > heapItems[larger]) {
                    larger++; //larger hod the index of the larger child
                }
            }
            if (key >= heapItems[larger]) {
                break;
            }
            //key is smaller;promote num[larger]
            heapItems[root] = heapItems[larger];
            root = larger;
            larger = 2 * root;
        }
        heapItems[root] = key;
    }  // end of sift down function 
    // main 
    public static void main(String[] args) throws IOException
    {        
        Scanner sc = new Scanner(new FileReader("heapItems.in"));
        int temp;
        while (sc.hasNextInt()) 
        {
            temp = sc.nextInt();
            if (size < MAX_HEAP_SIZE) 
            {
                heapItems[++size] = temp;
                siftUp(heapItems, size);
            } 
        }
        // insert num
        insertNode(96);
        
        System.out.println("Current heap:");
        for(int i =1;i<size;i++) 
            System.out.printf("%d ", heapItems[i]);
        // extract and serve
        System.out.println("\nHighest priority:"+extract());
         System.out.println("Heap after extract :");
        for(int i =1;i<size;i++) {
            System.out.printf("%d ", heapItems[i]);
        }  
        // delete no serve
        delete(84, size);
        System.out.println("\nHeap after deletion :");
        for(int i =1;i<size;i++) {
            System.out.printf("%d ", heapItems[i]);
        }  
        // change priority 
        changePriority(heapItems,1, 20);
        System.out.println("\nHeap after change priority:");
        for(int i =1;i<size;i++) 
            System.out.printf("%d ", heapItems[i]);
    }  // end of main
    // 1. Remove (serve) the item with the highest priority.
    public static int extract()
    {
        int hold;  // initialize int variable to serve the highest priority data
        if(heapItems==null)  // check if heap is empty 
        {
            System.out.println("Heap is empty.");
        }
        hold = heapItems[1];  // store highest priority in the variable hold
        delete(hold, size);
        
        return hold;
    }  // end of extract function 
    // 2. Add and item with a given priority.
    public static void insertNode(int newHeapItem)  
    {
        if(indexValue<MAX_HEAP_SIZE)  // check to see if the array is not full  
        {
            size++;
            // add new item and sift up 
            heapItems[size] = newHeapItem; 
            siftUp(heapItems, size);
        }
    }  // end of insertNode function 
    // 3. Remove (delete without serving) and item from the queue
    public static void delete(int n, int heapSize)
    {
        if(heapSize==0)  // check to see if heap is empty 
        {
            System.out.println("Heap is empty.");
            return;
        }
        int lastNode = heapItems[heapSize-1];
        //System.out.println("heap length"+heapItems.length);
        for (int i = 1; i < size; i++)  // scan through array 
        {
            if(heapItems[i]==n)  // if the node is found remove it,-1 from size, and shift down 
            {
                heapItems[i] = lastNode;
                size--;
                siftDown(heapItems[i], heapItems, i, size-1);
            }
        }
    }  // end of delete function with no return 
    // 4. Change the priority of an item, adjusting its position based on its new priority
    public static void changePriority(int array[], int i, int nodeValue)  // where 'i' is the index
    {
            array[i] = nodeValue;
            // while the left & right children are greater than the new value at index 'i'
            while((array[i*2] > array[i]) || (array[(i*2)+1] > array[i]))
            { 
                siftDown(array[i], heapItems, i,size-1);
            }
            while ((i!=1 ) &&(array[i / 2] < array[i])) // while the index is not equal 1 and greater than it's parent node
            {
                siftUp(heapItems, i);  // sift up
            }
    } 
    // utility functions 
    // swap function 
    public static void swap(int x, int y)  // swap two indexes 
    {
        int temp;
        temp = heapItems[x];
        heapItems[x] = heapItems[y];
        heapItems[y] = temp;
    }
}  // end of class 
