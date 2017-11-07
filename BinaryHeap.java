import java.util.NoSuchElementException;

//class must use an array based binary heap 
public class BinaryHeap {
	private int[] arr;
	private int size;	
	
	//constructor
	public BinaryHeap(){
		arr = new int[10];
		size = 0;
	}
	//returns the index of the parent
	public int parent(int index){
		return ((index-1)/2);
	}
	public int left_child(int index){
		return ((index*2) + 1);
	}
	public int right_child(int index){
		return ((index*2) + 2);
	}

	public void swap(int[] arr, int index_1, int index_2){
		int temp = arr[index_1];
		arr[index_1] = arr[index_2];
		arr[index_2] = temp;
	}
	
	public void grow_heap(){
		//increase the size of the arr by making a new array with double size
		int [] newArr = new int[(arr.length)*2];
		System.arraycopy(arr, 0, newArr, 0, arr.length);
		arr = newArr;
	}
	
	private void heapify(int index){
		int smallest = index;                   //set min node
		int l_child = left_child(index);        
		int r_child = right_child(index);
		
		if (l_child < size && arr[l_child] < arr[index]){
			//if left child from root/parent is smaller
			smallest = l_child;
		}else{
			//"no change"
			smallest = index;
		}
		if (r_child < size && arr[r_child] < arr[smallest]){
			//if right child from current min node is smaller than current small
			smallest = r_child;
		}
		if (smallest != index){
			//swap until base case
			swap(arr, index, smallest);
			heapify(smallest);
		}
	}
	
	//function adds an int instance to the priority queue
	//return: nothing
	public void add(int n){
		if (arr.length == size){
			grow_heap();
		}
		int index = size;
		arr[size++] = n;
		while (index >= 0 && arr[parent(index)] > arr[index]){
			swap(arr, index, parent(index));
			index = parent(index);
		}
	}
	
	
	//Function removes the highest priority item from the priority queue
	//return: int
	public int remove() throws NoSuchElementException{
		if (size == 0){
			throw new NoSuchElementException();
		}
		int removed = arr[0];
		arr[0] = arr[--size];
		heapify(0);
		return removed;

	}
	
}
