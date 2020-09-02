import java.util.LinkedList;

public class HuffmanFixedLength {

	Node root;
	Node curr;
	
	public HuffmanFixedLength() {
		
	}
	
	void prime(byte[] primer) {
		int[] freq = new int[16];
		for(byte b:primer) {
			freq[b/16]++;
			freq[b%16]++;
		}
		LinkedList<Node> list = new LinkedList<Node>();
		for(int i=0;i<freq.length;i++) {
			list.add(new Node(i,freq[i]));
		}
		
		while(list.size()>1) {
			
			for(int n=list.size();n>1;n--) {
				for(int i=0;i<n-1;++i) {
					if(list.get(i).weight>list.get(i+1).weight) {
						Node tmp = list.get(i);
						list.set(i, list.get(i+1));
						list.set(i+1,tmp);
					}
				}
			}
			
			Node l = list.get(0);
			Node r = list.get(1);
			list.remove(1);
			list.remove(0);
			list.add(new Node(l,r));
		}
		root = list.get(0);
		curr = root;
	}
	
	boolean[] compress(byte b) {
		boolean[] p1 = root.encode(b/16);
		boolean[] p2 = root.encode(b%16);
		return cat(p1,p2);
	}
	
	int decompress(boolean b) {
		curr = b?curr.r:curr.l;
		if(curr.r == null || curr.l == null) {
			int tmp = curr.val;
			curr = root;
			return tmp;
		} else {
			return -1;
		}
	}
	
	boolean[] cat (boolean[] a, boolean[] b) {	//concatenate 2 arrays
		boolean ret[] = new boolean[a.length+b.length];
		for(int i=0;i<ret.length;i++) {
			ret[i] = i<a.length?a[i]:b[i-a.length];
		}
		return ret;
	}
	
	class Node {
		boolean[] LEFT = {false};
		boolean[] RIGHT = {true};
		
		int val;
		int weight;
		Node l,r;
		
		public Node(int val, int weight) {
			this.val = val;
			this.weight = weight;
			l = null;
			r = null;
		}
		
		public Node(Node n1, Node n2) {
			l = n1;
			r = n2;
			weight = n1.weight + n2.weight;
			val = 16;
		}
		
				
		boolean[] encode(int val) {
			if(this.val == val) {
				return new boolean[0];
			} else {
				if(l!=null && r != null) {
					boolean[] tmpl = l.encode(val);
					boolean[] tmpr = r.encode(val);
					if(tmpl != null) {
						return cat(LEFT,tmpl);
					}
					if(tmpr != null) {
						return cat(RIGHT,tmpr);
					}
				}
				return null;
			}
		}
		
		public String toString() {
			return "("+val+":"+weight+")";
		}
	}
}
