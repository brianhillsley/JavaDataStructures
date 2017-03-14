package stack;

/**
 * 
 * @author Brian Hillsley
 *
 * @param <T>
 */
public class SortedStack<T extends Comparable> extends Stack<T> {
	
	/**
	 * RISING: Highest value at top of stack. Lowest value at bottom of stack.
	 * FALLING:  Lowest value at top of stack. Highest value at bottom of stack.
	 *
	 */
	public static enum Order {HIGHEST_RISE_TO_TOP, LOWEST_RISE_TO_TOP};
	
	public final Order order;
	
	/**
	 * Constructor for taking an already build Stack and converting it into a SortedStack
	 * @param stack the normal stack (not ordered)
	 * @param order the order to follow
	 */
	public SortedStack(Stack<T> stack, Order order){
		this.order = order;
	}
	
	public SortedStack(Order order){
		super();
		this.order = order;
	}
	
	/**
	 * Push involves both pushing and re-sorting
	 * 
	 * TODO: Should not just simply push the newItem on. Should place it in correct location
	 */
	public void push(T newItem){
		super.push(newItem);
	}
	
	private void sort(){
		if(order == Order.HIGHEST_RISE_TO_TOP){
			sortHighToTop();
		} else {
			sortLowToTop();
		}
	}
	
	public String toString(){
		return super.toString();
	}
	
	private void sortLowToTop(){
		if(this.size()<=1){
			return; // Early exit if the size is 1 or 0
		}
		
		Stack<T> tempStack = new Stack<T>();
		
		while (!this.isEmpty()) {
			T temp = this.pop();
			
			while((!tempStack.isEmpty()) && (temp.compareTo(tempStack.peek()) < 0)){
				this.push(tempStack.pop());
			}
			tempStack.push(temp);
		}
		// Copy all items back to this stack
		while(!(tempStack.isEmpty())){
			this.push(tempStack.pop());
		}
	}
	
	private void sortHighToTop(){
		if(this.size()<=1){
			return; // Early exit if the size is 1 or 0
		}
		
		Stack<T> tempStack = new Stack<T>();
		
		while (!this.isEmpty()) {
			T temp = this.pop();
			
			while((!tempStack.isEmpty()) && (temp.compareTo(tempStack.peek()) > 0)){
				this.push(tempStack.pop());
			}
			tempStack.push(temp);
		}
		// Copy all items back to this stack
		while(!(tempStack.isEmpty())){
			this.push(tempStack.pop());
		}
	}
	
	/**
	 * Using just stacks (no arrays directly) this method checks to see if stack elements are in order.
	 * An empty stack is considered sorted
	 * 
	 * Time Complexity: O(N) where N is the number of elements on the stack
	 * @return true if in order (false otherwise)
	 */
	private boolean isSorted(){
		if(this.isEmpty() || (this.size()==1) ){
			return true;
		}
		
		Stack<T> other = new Stack<T>();
		
		if(order == Order.HIGHEST_RISE_TO_TOP){
			while(!this.isEmpty()){
				T temp = this.pop();
				if(this.peek().compareTo(temp) < 0){
					this.push(temp);
					// restore this stack
					while(!other.isEmpty()){
						this.push(other.pop());
					}
					return false;
				}
				other.push(temp);
			}
			// restore this stack
			while(!other.isEmpty()){
				this.push(other.pop());
			}
			return true; 
		} else { // Order.LOWEST_RISE_TO_TOP
			while(!this.isEmpty()){
				T temp = this.pop();
				if(this.peek().compareTo(temp) > 0){
					this.push(temp);
					// restore this stack
					while(!other.isEmpty()){
						this.push(other.pop());
					}
					return false;
				}
				other.push(temp);
			}
			// restore this stack
			while(!other.isEmpty()){
				this.push(other.pop());
			}
			return true;
		}
	}
	
	public static void main(String[] args){
		int[][] vals = {
				{9, 8, 7, 6, 5, 4, 3, 2, 1},
				{2, 0, 1, 3, 0, 3, 2, 1, 3},
				{1},
				{7,5,2,1,3},
				{0,0,0},
				{4,1,1,4}};
		
		for(int i=0; i<vals.length; i++){
			System.out.println("TEST"+ i);
			// Testing sortHighToTop method
			SortedStack<Integer> ss = new SortedStack<Integer>(Order.HIGHEST_RISE_TO_TOP);
			SortedStack<Integer> ss2 = new SortedStack<Integer>(Order.LOWEST_RISE_TO_TOP);
			
			for(int j=0; j<vals[i].length; j++){
				ss.push(vals[i][j]);
				ss2.push(vals[i][j]);
			}
			
			ss.sort();
			ss2.sort();
			
			if(ss.isSorted()){
				p("ss is sorted.");
			} else {
				p("ss is NOT sorted.");
			}
			
			if(ss2.isSorted()){
				p("ss2 is sorted");
			} else {
				p("ss2 is NOT sorted");
			}
			
			System.out.println("ss: After sorted high to top");
			System.out.println(ss);
			
			System.out.println("ss2: After sorted low to top");
			System.out.println(ss2);
		}
		
		
		
		
	}
	public static void p(Object o){
			System.out.println(o);
	}
	
}
