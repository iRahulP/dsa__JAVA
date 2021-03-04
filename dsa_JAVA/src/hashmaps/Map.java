package hashmaps;

import java.util.ArrayList;

//Map will be generic on keys and values
public class Map<K,V> {
	
	//Generating Bucket Array
	//each entry will have a linked list in Bucket Array
	//create ArrayList with entries of type LinkedList Node.
	
	//initially null entries to ArrayList
	
	//ArrayList of type Node
	ArrayList<MapNode<K,V>> buckets;
	
	//no. of entries which we put in Map and not no. of buckets
	int size;

	int numBuckets;
	
	public Map() {
		numBuckets = 20;
		buckets = new ArrayList<>();
		for(int i = 0; i < numBuckets; i++) {
			buckets.add(null);
		}
	}
	
	
	public int size() {
		return size;
	}
	
	
	public V removeKey(K key) {
		int bucketIndex = getBucketIndex(key);
		MapNode<K,V> head = buckets.get(bucketIndex);
		
		//getting the previous node and setting it with initial value.
		MapNode<K,V> prev = null;
		
		//traversing linked list
		while(head != null) {
			if(head.key.equals(key)) {
				if(prev == null) {
					//need to change bucket entry
					//setting next one instead of head!
					buckets.set(bucketIndex, head.next);
				}
				else {
					prev.next = head.next;
				}
				
				return head.value;
			}
			//routing previous node and next node
			prev = head;
			head = head.next;
		}
		//doesn't exist => maybe key not present :(
		return null;
	}
	
	
	//get index corresponding to key => traverse Ll, match key and return V :)
	public V getValue(K key) {
		int bucketIndex = getBucketIndex(key);
		MapNode<K,V> head = buckets.get(bucketIndex);
		//traversing linked list
		while(head != null) {
			if(head.key.equals(key)) {
				return head.value;
			}
			head = head.next;
		}
		//doesn't exist => maybe key not present :(
		return null;
	} 
	
	
	private int getBucketIndex(K key) {
		//hashcode inherited from Object class
		int hashcode = key.hashCode();
		return hashcode % numBuckets;
	}
	
	
	public void insert(K key, V value) {
		int bucketIndex = getBucketIndex(key);
		
		
		//get head of linked list
		MapNode<K,V> head = buckets.get(bucketIndex);
		//traversing linked list
		while(head != null) {
			//check if key already present? update value : move next{create new node and attach}
			
			//using .equals(){checks content} instead as == will compare memory address
			if(head.key.equals(head)) {
				head.value = value;
				return;
			}
			head = head.next;
		}
		//doesn't exist => create new node and insert at bucket key
		MapNode<K,V> newElementNode = new MapNode<K,V>(key, value);
		head = buckets.get(bucketIndex);
		newElementNode.next = head;
		//set the current bucket key pointer to new element and set next of newElement to earlier head=> chaining correctly
		buckets.set(bucketIndex, newElementNode);
		
	}
	
}
